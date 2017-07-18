package com.rwss.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rwss.dao.IRwsBacterialTestDAO;
import com.rwss.dao.IRwsMinAssetViewDAO;
import com.rwss.dao.IRwsMinConstituencyViewDAO;
import com.rwss.dao.IRwsMinHabViewDAO;
import com.rwss.dao.IRwsMinHandpumpsViewDAO;
import com.rwss.dao.IRwsMinSchemeSourcesViewDAO;
import com.rwss.dao.IRwsMinShallowHandpumpsViewDAO;
import com.rwss.dao.IRwsMinWorksAdminViewDAO;
import com.rwss.dao.IRwsMinWorkscompViewDAO;
import com.rwss.dao.IRwsPhysicalchemtestDAO;
import com.rwss.dto.BasicVO;
import com.rwss.dto.InputVO;
import com.rwss.dto.LocationVO;
import com.rwss.dto.StatusVO;
import com.rwss.model.RwsMinAssetView;
import com.rwss.model.RwsMinConstituencyView;
import com.rwss.model.RwsMinHabView;
import com.rwss.model.RwsMinWorksAdminView;
import com.rwss.model.RwsMinWorkscompView;
import com.rwss.service.IHabitationDetailsService;
import com.rwss.utils.CommonMethodsUtilService;
import com.rwss.utils.IConstants;

@Transactional  
@Service
public class HabitationDetailsService implements IHabitationDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(HabitationDetailsService.class);

	@Autowired
	private IRwsMinHabViewDAO rwsMinHabViewDAO;

	@Autowired
	private IRwsMinAssetViewDAO rwsMinAssetViewDAO;

	@Autowired
	private IRwsMinWorksAdminViewDAO rwsMinWorksAdminViewDAO;

	@Autowired
	private IRwsPhysicalchemtestDAO rwsPhysicalchemtestDAO;

	@Autowired
	private IRwsBacterialTestDAO rwsBacterialTestDAO;

	@Autowired
	private IRwsMinWorkscompViewDAO rwsMinWorkscompViewDAO;

	@Autowired 
	private IRwsMinConstituencyViewDAO rwsMinConstituencyViewDAO;
	
	@Autowired
	  private IRwsMinHandpumpsViewDAO rwsMinHandpumpsViewDAO;
	  
	@Autowired
	private IRwsMinShallowHandpumpsViewDAO rwsMinShallowHandpumpsViewDAO;
	  
	@Autowired
	private IRwsMinSchemeSourcesViewDAO rwsMinSchemeSourcesViewDAO;

	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();

	/**
	 * @param : InputVO inputVo which contain year,locationType,filterType,filterValue
	 * @return : List<LocationVO> 
	 * @Description : This service is used to get Habitation details status wise based on location type. 
	 * @author : Santosh Kumar Verma
	 * @since 12-JUNE-2017
	 */	
	@Override
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO) {
		List<LocationVO> resultList = new ArrayList<LocationVO>(0);
		try {

			LOG.info("Entered into getHabitationCoverageByStatusByLocationType() in HabitationDetailsService class");
			
			if(inputVO.getYear() != null && !inputVO.getYear().trim().isEmpty()){
				inputVO.getStressedHabitationYearsList().add(inputVO.getYear());
			}else if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
				Long fromYear = Long.parseLong(inputVO.getFromDateStr().split("-")[2]);
				Long toYear = Long.parseLong(inputVO.getToDateStr().split("-")[2]);
				
				for (Long i = fromYear; i <= toYear; i++) {
					inputVO.getStressedHabitationYearsList().add(i.toString());
				}
			}
			
			if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring( inputVO.getYear().length() - 2));// taking last 2 digit from year
			}
			
			List<Object[]> locationHabitationObjLst = rwsMinHabViewDAO.getHabitationCoverageByStatusByLocationType(inputVO);
			List<Object[]> streedHabObjLst = rwsMinHabViewDAO.getStressedHabitationCountLocationWise(inputVO, "");

			Map<String, LocationVO> locationMap = null;
			if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("state")) {// getting state level data

				Map<String, Long> streeHabMap = setStressedHabitationData(streedHabObjLst, "locationWise");
				resultList = setStateLevelData(locationHabitationObjLst,streeHabMap);
				return resultList;

			} else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")) { // getting mandal level data

				List<String> statusList = rwsMinHabViewDAO.getAllCoverageStatus();
				Map<String, Map<String, Long>> mandalLevelStrssedMap = preparedStrssedHabMandalLevelData(streedHabObjLst);
				resultList = prepareMandalLevelData(locationHabitationObjLst,mandalLevelStrssedMap, statusList);
				return resultList;

			} else { // for constituency and district

				Map<String, Long> streeHabMap = setStressedHabitationData(streedHabObjLst, "locationWise");
				List<String> statusList = rwsMinHabViewDAO.getAllCoverageStatus();
				locationMap = setLocationWiseHabitationDtls(locationHabitationObjLst, streeHabMap, statusList);
			}
			if (locationMap != null && locationMap.size() > 0) {
				resultList.addAll(locationMap.values());
				locationMap.clear();
			}
			// calculating percentage
			if (resultList != null && resultList.size() > 0) {
				for (LocationVO lctnVO : resultList) {
					if (lctnVO.getStatusList() != null && lctnVO.getStatusList().size() > 0) {
						for (StatusVO statusVO : lctnVO.getStatusList()) {
							statusVO.setPercentage(calculatePercantage(statusVO.getCount(), lctnVO.getTotalCount()));
						}
					}
				}
			}
			if(resultList.size()>0){
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
		} catch (Exception e) {
			LocationVO statusVO = new LocationVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
			LOG.error("Error occured at getHabitationCoverageByStatusByLocationType() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return resultList;
	}

	private Map<String, Map<String, Long>> preparedStrssedHabMandalLevelData(List<Object[]> objList) {
		try {

			Map<String, Map<String, Long>> stressedHabMap = new HashMap<>(0);
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					Map<String, Long> mandalMap = stressedHabMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
					if (mandalMap == null) {
						mandalMap = new HashMap<>();
						stressedHabMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), mandalMap);
					}
					mandalMap.put(commonMethodsUtilService.getStringValueForObject(param[1]),commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			return stressedHabMap;
		} catch (Exception e) {
			LOG.error("Error occured at preparedStrssedHabMandalLevelData() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}

	private List<LocationVO> prepareMandalLevelData(List<Object[]> objList,	Map<String, Map<String, Long>> stressedHabMap,List<String> statusList) {
		List<LocationVO> resultList = new ArrayList<>(0);
		try {
			Map<String, Map<String, LocationVO>> districtMap = new LinkedHashMap<>();
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					Map<String, LocationVO> mandalMap = districtMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
					if (mandalMap == null) {
						mandalMap = new LinkedHashMap<>();
						districtMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), mandalMap);
					}
					LocationVO mandalVO = mandalMap.get(commonMethodsUtilService.getStringValueForObject(param[1]));
					if (mandalVO == null) {
						mandalVO = new LocationVO();
						mandalVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[1]));
						mandalVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
						mandalVO.setStressedHabitationCount(0l);
						mandalVO.setTotalCount(0l);
						mandalVO.setStatusList(getStatusList(statusList));
						mandalMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), mandalVO);
						if (stressedHabMap != null) {
							if (stressedHabMap.get(commonMethodsUtilService.getStringValueForObject(param[0])) != null) {
								Map<String, Long> stressedMandalMap = stressedHabMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
								if (stressedMandalMap.get(mandalVO.getLocationId()) != null) {
									mandalVO.setStressedHabitationCount(stressedMandalMap.get(mandalVO.getLocationId()));
								}
							}
						}
					}
					String status = commonMethodsUtilService.getStringValueForObject(param[3]);
					StatusVO statusVO = getMatchVO(mandalVO.getStatusList(),status);
					if (statusVO != null) {
						statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
						mandalVO.setTotalCount(mandalVO.getTotalCount()+ statusVO.getCount());
					}

				}
			}
			// Calculate Percentage
			if (districtMap != null && districtMap.size() > 0) {
				for (Entry<String, Map<String, LocationVO>> distEntry : districtMap.entrySet()) {
					if (distEntry.getValue() != null && distEntry.getValue().size() > 0) {
						for (Entry<String, LocationVO> locationEntry : distEntry.getValue().entrySet()) {
							if (locationEntry.getValue().getStatusList() != null && locationEntry.getValue().getStatusList().size() > 0) {
								for (StatusVO statusVO : locationEntry.getValue().getStatusList()) {
									statusVO.setPercentage(calculatePercantage(statusVO.getCount(), locationEntry.getValue().getTotalCount()));
								}
							}
						}
					}
				}
			}
			// Prepare Final Result List
			if (districtMap != null && districtMap.size() > 0) {

				for (Entry<String, Map<String, LocationVO>> distEntry : districtMap.entrySet()) {
					LocationVO districtVO = new LocationVO();
					districtVO.setDistrictId(distEntry.getKey());
					if (distEntry.getValue() != null && distEntry.getValue().size() > 0) {
						districtVO.setSubList(new ArrayList<>(distEntry.getValue().values()));
					}
					resultList.add(districtVO);
				}

			}
			if(resultList.size()>0){
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return resultList;
		} catch (Exception e) {
			LOG.error("Error occured at preparedStrssedHabMandalLevelData() in HabitationDetailsService class",	e);
			LocationVO statusVO = new LocationVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
			return resultList;
		}
	}

	public Map<String, LocationVO> setLocationWiseHabitationDtls(List<Object[]> objList, Map<String, Long> streeHabMap,List<String> statusList) {
		try {

			Map<String, LocationVO> locationMap = new LinkedHashMap<String, LocationVO>();

			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String locationValue = commonMethodsUtilService.getStringValueForObject(param[0]);
					LocationVO locationVO = locationMap.get(locationValue);
					if (locationVO == null) {
						locationVO = new LocationVO();
						locationVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[0]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.setStressedHabitationCount(0l);
						locationVO.setTotalCount(0l);
						if (streeHabMap != null) {
							if (streeHabMap.get(locationVO.getLocationId()) != null) {
								locationVO.setStressedHabitationCount(streeHabMap.get(locationVO.getLocationId()));
							}
						}
						locationVO.setStatusList(getStatusList(statusList));
						locationMap.put(locationVO.getLocationId(), locationVO);
					}
					String status = commonMethodsUtilService.getStringValueForObject(param[2]);
					StatusVO statusVO = getMatchVO(locationVO.getStatusList(),status);
					if (statusVO != null) {
						statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						locationVO.setTotalCount(locationVO.getTotalCount()+ statusVO.getCount());
					}
				}
			}
			return locationMap;
		} catch (Exception e) {
			LOG.error("Error occured at setLocationWiseHabitationDtls() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}

	public List<StatusVO> getStatusList(List<String> sttsLst) {
		List<StatusVO> statusList = new ArrayList<StatusVO>();
		try {
			if (sttsLst != null && sttsLst.size() > 0) {
				for (String status : sttsLst) {
					StatusVO statusVO = new StatusVO();
					statusVO.setStatus(status);
					statusList.add(statusVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getStatusList() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return statusList;
	}

	public StatusVO getMatchVO(List<StatusVO> list, String name) {
		try {
			if (list == null || list.size() == 0)
				return null;

			for (StatusVO statusVO : list) {
				if (statusVO.getStatus().equalsIgnoreCase(name.trim())) {
					return statusVO;
				}
			}

		} catch (Exception e) {
			LOG.error("Error occured at getMatchVO() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}

	public List<LocationVO> setStateLevelData(List<Object[]> statusObjList, Map<String, Long> streeHabMap) {
		List<LocationVO> resultList = new ArrayList<LocationVO>(0);
		try {
			
			LocationVO locationVO = new LocationVO();
			locationVO.setLocationId("01");
			locationVO.setLocationName("Andhra Pradesh");
			locationVO.setStatusList(new ArrayList<StatusVO>());
			locationVO.setStressedHabitationCount(0l);
			locationVO.setTotalCount(0l);
			if (statusObjList != null && statusObjList.size() > 0) {
				for (Object[] param : statusObjList) {
					StatusVO statusVO = new StatusVO();
					statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[0]));
					statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					// Calculating total count
					locationVO.setTotalCount(locationVO.getTotalCount()+ statusVO.getCount());
					locationVO.getStatusList().add(statusVO);
				}
				resultList.add(locationVO);
			}
			if (streeHabMap != null && streeHabMap.size() > 0) {
				for (Entry<String, Long> entry : streeHabMap.entrySet()) {
					if (entry.getValue() != null) {
						locationVO.setStressedHabitationCount(locationVO.getStressedHabitationCount()+ entry.getValue());
					}
				}
			}
			//calculating percentage
			if (resultList != null && resultList.size() > 0) {
				for (LocationVO lctnVO : resultList) {
					if (lctnVO.getStatusList() != null && lctnVO.getStatusList().size() > 0) {
						for (StatusVO statusVO : lctnVO.getStatusList()) {
							statusVO.setPercentage(calculatePercantage(statusVO.getCount(), lctnVO.getTotalCount()));
						}
					}
				}
			}
			if(resultList.size()>0){
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return resultList;
		} catch (Exception e) {
			LOG.error("Error occured at setStateLevelData() in HabitationDetailsService class",e);
			LocationVO statusVO = new LocationVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
		    return resultList;
			
		}
	}

	public Map<String, Long> setStressedHabitationData(List<Object[]> stressedObjLst, String type) {
		try {
			Map<String, Long> streeHabMap = new HashMap<String, Long>();
			if (stressedObjLst != null && stressedObjLst.size() > 0) {
				for (Object[] param : stressedObjLst) {
					if (type.equalsIgnoreCase("locationWise")|| type.equalsIgnoreCase("state")) {
						streeHabMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
					} else if (type.equalsIgnoreCase("statusWise")) {
						streeHabMap.put(commonMethodsUtilService.getStringValueForObject(param[1]),commonMethodsUtilService.getLongValueForObject(param[2]));
					} else if (type.equalsIgnoreCase("mandal")) {
						streeHabMap.put(commonMethodsUtilService.getStringValueForObject(param[2]),commonMethodsUtilService.getLongValueForObject(param[3]));
					}
				}
			}
			return streeHabMap;
		} catch (Exception e) {
			LOG.error("Error occured at setStressedHabitationData() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}

	public Double calculatePercantage(Long subCount, Long totalCount) {
		Double d = 0.0d;
		if (subCount.longValue() > 0l && totalCount.longValue() == 0l)
			LOG.error("Sub Count Value is " + subCount+ " And Total Count Value  " + totalCount);

		if (totalCount.longValue() > 0l) {
			d = new BigDecimal(subCount * 100.0 / totalCount).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return d;
	}
	/**
	 * @param : InputVO inputVo which contain fromDateStr,toDateStr
	 * @return : List<BasicVO> 
	 * @Description : This service is used to get Asset details 
	 * @author : Santosh Kumar Verma
	 * @since 12-JUNE-2017
	 */	
	@Override
	public String getAssetInfo(InputVO inputVO) {
		List<LocationVO> resultList = new ArrayList<LocationVO>(0);
		JSONObject jsonobj = new JSONObject();
		try {
			LOG.info("Entered into getAssetInfo() in HabitationDetailsService class");

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			} else if (inputVO.getYear() != null && inputVO.getYear().length() > 0) {
				Long year = Long.valueOf(inputVO.getYear());
				Long priviousYear = year - 1;
				inputVO.setFromDate(sdf.parse("01-04-" + priviousYear));
				inputVO.setToDate(sdf.parse("01-04-" + year));

			}
			List<Object[]> assetTypeObjList = rwsMinAssetViewDAO.getAssetTypeCountDetails(inputVO);
			List<String> typeOfAssetsList = rwsMinAssetViewDAO.getAvliableAssets();
			if (assetTypeObjList != null && assetTypeObjList.size() > 0) {
				for (Object[] param : assetTypeObjList) {
					LocationVO assetTypeVO = new LocationVO();
					assetTypeVO.setAssetType(commonMethodsUtilService.getStringValueForObject(param[0]));
					assetTypeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY) || inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.DISTRICT) ||
							inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
						assetTypeVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[2]));
						assetTypeVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
						if(inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
						assetTypeVO.setDistrictId(commonMethodsUtilService.getStringValueForObject(param[4]));
						assetTypeVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]));
						}
					}else if(inputVO.getLocationType().trim().equalsIgnoreCase("state")){
						assetTypeVO.setLocationId("01");
						assetTypeVO.setLocationName("Andra Pradesh");
					}
					resultList.add(assetTypeVO);
				}
			}
			jsonobj.put("assetTypeList", resultList);
			jsonobj.put("distinctAssetsList", typeOfAssetsList);
			jsonobj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			
			return jsonobj.toString();
		} catch (Exception e) {
			jsonobj.remove("assetListData");
			jsonobj.remove(IConstants.STATUS);
			LOG.error("Error occured at getAssetInfo() in HabitationDetailsService class",e);
			jsonobj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jsonobj.put(IConstants.ERROR_MESSAGE,e.getLocalizedMessage());
			return jsonobj.toString();
		}
	}
	/**
	 * @param : InputVO inputVo which contain year
	 * @return : BasicVO  
	 * @Description : This service is used to get per day water supply  details in MLD format 
	 * @author : Sanjay
	 * @since 13-JUNE-2017
	 */
	@Override
	public String gethabitationWatersupplyDetails(InputVO inputVO) {
		
		try {
			JSONObject jsonObj = new JSONObject();
			LOG.info("Entered into gethabitationsupplyDetails() in HabitationDetailsService class");
			BigDecimal safeLpcd =null;
			BigDecimal unsafeLpcd = null;
			if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
			}
		    if(IConstants.SUPPLY_TYPE_SAFE == "safe"){
			BigDecimal supplyWaterByHandpumps = rwsMinHandpumpsViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_SAFE );
			BigDecimal supplyWaterByShallowPumps = rwsMinShallowHandpumpsViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_SAFE);
			BigDecimal supplyWaterBySubSurface = rwsMinSchemeSourcesViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_SAFE,"SUBSURFACE SOURCE");
			if(supplyWaterByHandpumps ==null){
				supplyWaterByHandpumps = new BigDecimal(0);
			}else if (supplyWaterByShallowPumps ==null ){
				supplyWaterByShallowPumps= new BigDecimal(0);
			}else if(supplyWaterBySubSurface ==null ){
				supplyWaterBySubSurface= new BigDecimal(0);
			}
				
			BigDecimal safe1= supplyWaterByHandpumps.add(supplyWaterByShallowPumps);
			safeLpcd = safe1.add(supplyWaterBySubSurface);
			}
		    if( IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase("un-safe")){
		    	BigDecimal supplyWaterByHandpumps = rwsMinHandpumpsViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_UNSAFE );
		    	BigDecimal supplyWaterByShallowPumps = rwsMinShallowHandpumpsViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_UNSAFE);
		    	BigDecimal supplyWaterBySubSurface = rwsMinSchemeSourcesViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_UNSAFE,"SUBSURFACE SOURCE");
		    	if(supplyWaterByHandpumps == null){
					supplyWaterByHandpumps = new BigDecimal(0);
				}else if (supplyWaterByShallowPumps == null ){
					supplyWaterByShallowPumps= new BigDecimal(0);
				}else if(supplyWaterBySubSurface == null ){
					supplyWaterBySubSurface= new BigDecimal(0);
				}
		    	BigDecimal safe1= supplyWaterByHandpumps.add(supplyWaterByShallowPumps);
		    	unsafeLpcd = safe1.add(supplyWaterBySubSurface);
		    }
		    
			BigDecimal supplySafeWaterBySurface = rwsMinSchemeSourcesViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_SAFE,"SURFACE SOURCE");
	    	BigDecimal supplyUnsafeWaterBySurface = rwsMinSchemeSourcesViewDAO.gethabitationWatersupplyDetails(inputVO,IConstants.SUPPLY_TYPE_UNSAFE,"SURFACE SOURCE");
	    	
	    	if(supplySafeWaterBySurface == null){
	    		supplySafeWaterBySurface = new BigDecimal(0);
			}else if (supplyUnsafeWaterBySurface == null ){
				supplyUnsafeWaterBySurface= new BigDecimal(0);
			}

			BigDecimal Mld = new BigDecimal(1000000);
		
			// 1mld = 1000000liters
			//ground
			jsonObj.put("groundWaterSafeMLD",safeLpcd.divide(Mld, 2, RoundingMode.CEILING));
			jsonObj.put("groundWaterUnSafeMLD",unsafeLpcd.divide(Mld, 2, RoundingMode.CEILING));
			//surface
			jsonObj.put("surfaceWaterSafeMLD",supplySafeWaterBySurface.divide(Mld, 2, RoundingMode.CEILING));
			jsonObj.put("surfaceWaterUnSafeMLD",supplyUnsafeWaterBySurface.divide(Mld, 2, RoundingMode.CEILING));
			//total
			jsonObj.put("totalSafeWaterInMLD", safeLpcd.divide(Mld, 2, RoundingMode.CEILING).add(supplySafeWaterBySurface.divide(Mld, 2, RoundingMode.CEILING)));
			jsonObj.put("totalUnSafeWaterInMLD", unsafeLpcd.divide(Mld, 2, RoundingMode.CEILING).add(supplyUnsafeWaterBySurface.divide(Mld, 2, RoundingMode.CEILING)));

			jsonObj.put(IConstants.STATUS,IConstants.RESULT_SUCCESS);

			return jsonObj.toString();
		} catch (Exception e) {
			JSONObject objectError = new JSONObject();
			LOG.error("Error occured at gethabitationsupplyDetails() in HabitationDetailsService class",e);
			objectError.put("status", IConstants.RESULT_FAILURE);
			objectError.put("exceptionMessage", e.getLocalizedMessage());
			return objectError.toString();
		}

	}
	/**
	 * @param : InputVO inputVo which contain locationType,filterType,filterValue
	 * @return : List<LocationVO> 
	 * @Description : This service is used to get Location based on selection. 
	 * @author : Santosh Kumar Verma
	 * @since 13-JUNE-2017
	 */	
	@Override
	public List<LocationVO> getLocationBasedOnSelection(InputVO inputVO) {
		List<LocationVO> locationList = new ArrayList<LocationVO>(0);
		try {
			LOG.info("Entered into getLocationBasedOnSelection() in HabitationDetailsService class");

			List<Object[]> rtrnLocationObjLst = rwsMinHabViewDAO.getLocationBasedOnSelection(inputVO);
			if (rtrnLocationObjLst != null && rtrnLocationObjLst.size() > 0) {
				for (Object[] param : rtrnLocationObjLst) {
					LocationVO locationVO = new LocationVO();
					locationVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationList.add(locationVO);
					locationList.get(0).setStatus(IConstants.RESULT_SUCCESS);

				}
			}
			if(locationList.size()>0){
				locationList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
		} catch (Exception e) {
			LOG.error("Error occured at getLocationBasedOnSelection() in HabitationDetailsService class",e);
			LocationVO statusVO = new LocationVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			locationList.add(statusVO);
		}
		return locationList;
	}
	/**
	 * @param : InputVO inputVo which contain year,locationType,filterType,filterValue
	 * @return : LocationVO  
	 * @Description : This service is used to get stress habitation details based on location selection. 
	 * @author : Santosh Kumar Verma
	 * @since 13-JUNE-2017
	 */	
	@Override
	public LocationVO getStressedHabitationInfoInALocation(InputVO inputVO) {

	    LocationVO resutVO = new LocationVO();
	    try {
	      LOG.info("Entered into getStressedHabitationInfoInALocation() in HabitationDetailsService class");
	      
	      if(inputVO.getYear() != null && !inputVO.getYear().trim().isEmpty()){
	    	  inputVO.getStressedHabitationYearsList().add(inputVO.getYear());
	      }else if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
	    	  Long fromYear = Long.parseLong(inputVO.getFromDateStr().split("-")[2]);
	    	  Long toYear = Long.parseLong(inputVO.getToDateStr().split("-")[2]);
	    	  
	    	  for (Long i = fromYear; i <= toYear; i++) {
	    		  inputVO.getStressedHabitationYearsList().add(i.toString());
	    	  }
	      }
	      
	      if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
	        inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
	      }
	      
	      
	      
	      List<Object[]> locationHabitationObjLst = rwsMinHabViewDAO.getHabitationCoverageByStatusByLocationType(inputVO);
	      List<Object[]> streedHabObjLst = rwsMinHabViewDAO.getStressedHabitationCountLocationWise(inputVO,"statusWise");

	      // Getting stressed habitation count
	      Map<String, Long> streeHabMap = null;
	      if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("state")) {

	         streeHabMap = setStressedHabitationData(streedHabObjLst,"state");
	         resutVO = getStateLevelAllHabAndStressedHabData(locationHabitationObjLst, streeHabMap);
	         return resutVO;

	      } else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")) {
	        streeHabMap = setStressedHabitationData(streedHabObjLst,"mandal");
	      } else {
	        streeHabMap = setStressedHabitationData(streedHabObjLst,"statusWise");
	      }

	      if (locationHabitationObjLst != null && locationHabitationObjLst.size() > 0) {
	        for (Object[] param : locationHabitationObjLst) {
	          if (resutVO.getLocationId() == null) {
	            if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")) {
	              resutVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[1]));
	              resutVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
	              resutVO.setStatusList(new ArrayList<StatusVO>());
	            } else { // for district and constituency
	              resutVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[0]));
	              resutVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
	              resutVO.setStatusList(new ArrayList<StatusVO>());
	            }
	          }
	          StatusVO statusVO = new StatusVO();
	          statusVO.setStressedHabitationCount(0l);
	          if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")) {
	            statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
	            statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
	          } else {
	            statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[2]));
	            statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));

	          }
	          if (streeHabMap != null) {
	            if (streeHabMap.get(statusVO.getStatus()) != null) {
	              statusVO.setStressedHabitationCount(streeHabMap.get(statusVO.getStatus())); // Calculating percentage
	              statusVO.setPercentage(calculatePercantage(statusVO.getStressedHabitationCount(),statusVO.getCount()));
	            }
	          }
	          resutVO.getStatusList().add(statusVO);
	        }
	      }
	      resutVO.setStatus(IConstants.RESULT_SUCCESS);
	    } catch (Exception e) {
	      LOG.error("Error occured at getStressedHabitationInfoInALocation() in HabitationDetailsService class",e);
	      resutVO.setStatus(IConstants.RESULT_FAILURE);
	      resutVO.setExceptionMessage(e.getLocalizedMessage());
	    }
	    return resutVO;
	}
	/**
	 * @param : InputVO inputVo which contain fromDateStr,toDateStr
	 * @return : List<BasicVO>  
	 * @Description : This service is used to get scheme details 
	 * @author : Sanjay
	 * @since 13-JUNE-2017
	 */
	@Override
	public List<BasicVO> getSchemesDataByDate(InputVO inputVo) {
		List<BasicVO> resultList = new ArrayList<BasicVO>(0);
		try {

			LOG.info("Entered into getSchemesDataByYear() in HabitationDetailsService class");

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			if (inputVo.getFromDateStr() != null && inputVo.getFromDateStr().length() > 0 && inputVo.getToDateStr() != null && inputVo.getToDateStr().length() > 0) {
				inputVo.setFromDate(sdf.parse(inputVo.getFromDateStr()));
				inputVo.setToDate(sdf.parse(inputVo.getToDateStr()));
			} else if (inputVo.getYear() != null && inputVo.getYear().length() > 0) {
				Long year = Long.valueOf(inputVo.getYear());
				Long priviousYear = year - 1;
				inputVo.setFromDate(sdf.parse("01-04-" + priviousYear));
				inputVo.setToDate(sdf.parse("01-04-" + year));

			}
			List<Object[]> schemeObjLst = rwsMinWorksAdminViewDAO.getSchemesDataByDate(inputVo);
			if (schemeObjLst != null && schemeObjLst.size() > 0) {
				for (Object[] param : schemeObjLst) {
					BasicVO schemeVO = new BasicVO();
					schemeVO.setAssetType(commonMethodsUtilService.getStringValueForObject(param[0]));
					schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					resultList.add(schemeVO);
				}
			}
			
			if(resultList.size()>0){
			  resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return resultList;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemesDataByYear() in HabitationDetailsService class",e);
			BasicVO basicVO = new BasicVO();			
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			basicVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(basicVO);
			return resultList;
		}
	}
	/**
	 * @param : InputVO inputVo which contain year
	 * @return : LocationVO  
	 * @Description : This service is used to get stress habitation details based on location selection. 
	 * @author : sanjay
	 * @since 13-JUNE-2017
	 */
	@Override
	public BasicVO getLabTestDetails(InputVO inputVO) {
		BasicVO resultVO = new BasicVO();
		try {
			Long physicalTestCount = rwsPhysicalchemtestDAO.getPhysicalchemtestCountYearWise(inputVO);
			Long bacterialTestCount = rwsBacterialTestDAO.getBacterialTestCountYearWise(inputVO);
			resultVO.setPhysicalTestCount(physicalTestCount);
			resultVO.setBacterialTestCount(bacterialTestCount);
			resultVO.setStatus(IConstants.RESULT_SUCCESS);
		} catch (Exception e) {
			LOG.error("Error occured at getLabTestDetails() in HabitationDetailsService class",e);
			resultVO.setStatus(IConstants.RESULT_FAILURE);
			resultVO.setExceptionMessage(e.getLocalizedMessage());
		}
		return resultVO;
	}

	/**
	 * @param : InputVO inputVo which contain Fromdatestr and todatestr
	 * @return : List<BasicVO>  
	 * @Description : This service is used to get schemeWise work details. 
	 * @author : sanjay
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<LocationVO> getSchemeWiseWorkDetails(InputVO inputVO) {
		
		List<LocationVO> locationList = new ArrayList<LocationVO>(0);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			if(inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() >0){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}else if(inputVO.getYear() != null && inputVO.getYear().length() > 0){
				Long year = Long.valueOf(inputVO.getYear());
				Long priviousYear = year-1;
				inputVO.setFromDate(sdf.parse("01-04-"+ priviousYear));
				inputVO.setToDate(sdf.parse("01-04-"+year));
				
			}

			List<Object[]> objList = new ArrayList<>(0);

			List<Object[]>  workComissionObjLst = rwsMinWorkscompViewDAO.getSchemeWiseWorkDetails(inputVO, IConstants.WORK_COMMISSIONED);
			List<Object[]>  workCompletedObjLst = rwsMinWorkscompViewDAO.getSchemeWiseWorkDetails(inputVO, IConstants.WORK_COMPLETION);
			List<Object[]>  workOngoingObjLst = rwsMinWorksAdminViewDAO.getSchemeWiseWorkDetails(inputVO, IConstants.WORK_GROUNDED);
			List<Object[]>  workNotGroundingObjLst = rwsMinWorksAdminViewDAO.getSchemeWiseWorkDetails(inputVO, IConstants.WORK_NOTGROUNDED );

			objList.addAll(workComissionObjLst);
			objList.addAll(workCompletedObjLst);
			objList.addAll(workOngoingObjLst);
			objList.addAll(workNotGroundingObjLst);
			
			List<String> assetTypesObjList = rwsMinWorksAdminViewDAO.getAllAssetTypes();
			
			List<LocationVO> assetTypesList = new ArrayList<LocationVO>(0);
			if(assetTypesObjList !=null && assetTypesObjList.size()>0){
				for (String string : assetTypesObjList) {
					LocationVO assetVo  = new LocationVO();
					assetVo.setAssetType(string.trim());// pws/cpws/...
					assetVo.setWorkComissionedCount(0l);
					assetVo.setWorkCompletedCount(0l);
					assetVo.setWorkNotGroundedCount(0l);
					assetVo.setWorkOngoingCount(0l);
					assetTypesList.add(assetVo);
				}
			}
			Map<String, LocationVO> locationMap = null;
			if(inputVO.getLocationType()!=null && inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
				
				locationList =prepareMandalWorkLevelData(objList, assetTypesList);
				locationList = setmandallevelRequiredDateIntoList(workComissionObjLst,locationList,"comissioned");
				locationList = setmandallevelRequiredDateIntoList(workCompletedObjLst,locationList,"completed");
				locationList = setmandallevelRequiredDateIntoList(workOngoingObjLst,locationList,"ongoing");
				locationList = setmandallevelRequiredDateIntoList(workNotGroundingObjLst,locationList,"notGrounded");
			
			}else if(inputVO.getLocationType()!=null && inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.DISTRICT)|| inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)
					||  inputVO.getLocationType().trim().equalsIgnoreCase(IConstants.STATE)){
				locationMap = setLocationbasedList(objList,assetTypesList );
				if (locationMap != null && locationMap.size() > 0) {
					locationList.addAll(locationMap.values());
					locationMap.clear();
				}
				//locationList = getLocationList(objList,assetTypesList);//getting all schemes
				locationList = setRequiredDataDistrictIntoList(workComissionObjLst,locationList,"comissioned");
				locationList = setRequiredDataDistrictIntoList(workCompletedObjLst,locationList,"completed");
				locationList = setRequiredDataDistrictIntoList(workOngoingObjLst,locationList,"ongoing");
				locationList = setRequiredDataDistrictIntoList(workNotGroundingObjLst,locationList,"notGrounded");
			}
			if(locationList.size()>0){
				locationList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
		}catch(Exception e){
			LocationVO basicVO = new LocationVO();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in HabitationDetailsService class",e);
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			basicVO.setExceptionMessage(e.getLocalizedMessage());
			locationList.add(basicVO);
		}
		return  locationList;
	}
		
	public List<LocationVO> setRequiredDateIntoList(List<Object[]> objList,List<LocationVO> schemeList, String type) {
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String schemName = commonMethodsUtilService.getStringValueForObject(param[0]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[1]);
					LocationVO schemeMatchVO = getSchemeMatchVO(schemeList,schemName);
					if (schemeMatchVO != null) {
						schemeMatchVO.setLocationId("01");
						schemeMatchVO.setLocationName("Andra Pradesh");
						if (type.equalsIgnoreCase("comissioned")) {
							schemeMatchVO.setWorkComissionedCount(count);
						} else if (type.equalsIgnoreCase("completed")) {
							schemeMatchVO.setWorkCompletedCount(count);
						} else if (type.equalsIgnoreCase("ongoing")) {
							schemeMatchVO.setWorkOngoingCount(count);
						} else if (type.equalsIgnoreCase("notGrounded")) {
							schemeMatchVO.setWorkNotGroundedCount(count);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in HabitationDetailsService class",e);
			
			e.printStackTrace();
		}
		return schemeList;
	}
	
	//district 

	public List<LocationVO> setRequiredDataDistrictIntoList(List<Object[]> objList,List<LocationVO> finalLocationList, String type) {
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String schemName = commonMethodsUtilService.getStringValueForObject(param[0]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[1]);
					LocationVO mainVo = getSchemeMatchVOSub(finalLocationList,param[2] !=null ? param[2].toString():null);
					if(mainVo != null){
						LocationVO schemeMatchVO = getSchemeMatchVO(mainVo.getSubList(),schemName);
						if (schemeMatchVO != null) {
							if (type.equalsIgnoreCase("comissioned")) {
								schemeMatchVO.setWorkComissionedCount(count);
							} else if (type.equalsIgnoreCase("completed")) {
								schemeMatchVO.setWorkCompletedCount(count);
							} else if (type.equalsIgnoreCase("ongoing")) {
								schemeMatchVO.setWorkOngoingCount(count);
							} else if (type.equalsIgnoreCase("notGrounded")) {
								schemeMatchVO.setWorkNotGroundedCount(count);
							}
						}

					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in HabitationDetailsService class",e);
			
			e.printStackTrace();
		}
		return finalLocationList;
	}
	//assetType,count,mandalId,mandalName,districtId(SuperLocation),distName
	public List<LocationVO> setmandallevelRequiredDateIntoList(List<Object[]> objList,List<LocationVO> finalLocationList, String type) {
		
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					//String schemName = commonMethodsUtilService.getStringValueForObject(param[0]);
					//Long count = commonMethodsUtilService.getLongValueForObject(param[1]);
					
					for (LocationVO locationVO : finalLocationList) {
						
						LocationVO mainVo = getSchemeMatchVOSub(finalLocationList,param[4] !=null ? param[4].toString():null);						
						if(mainVo !=null){
							LocationVO subVo = 	getSchemeMatchVOSub(mainVo.getSubList(),param[2] !=null ? param[2].toString():null);
							if(subVo != null){
								LocationVO schemeMatchVO = getSchemeMatchVO(subVo.getSubList(),commonMethodsUtilService.getStringValueForObject(param[0]));
								
								if (schemeMatchVO != null) {
									if (type.equalsIgnoreCase("comissioned")) {
										schemeMatchVO.setWorkComissionedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
									} else if (type.equalsIgnoreCase("completed")) {
										schemeMatchVO.setWorkCompletedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
									} else if (type.equalsIgnoreCase("ongoing")) {
										schemeMatchVO.setWorkOngoingCount(commonMethodsUtilService.getLongValueForObject(param[1]));
									} else if (type.equalsIgnoreCase("notGrounded")) {
										schemeMatchVO.setWorkNotGroundedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
									}
								}
							
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in HabitationDetailsService class",e);
			
			e.printStackTrace();
		}
		return finalLocationList;
	}

	public LocationVO getSchemeMatchVO(List<LocationVO> locationList, String name) {
		try {
			if (locationList == null || locationList.size() == 0)
				return null;

			for (LocationVO schemeVO : locationList) {
				if (schemeVO.getAssetType().equalsIgnoreCase(name.trim())) {
					return schemeVO;
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeMatchVO() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}
	
	public LocationVO getSchemeMatchVOSub(List<LocationVO> subLocationList, String id) {
		try {
			if (subLocationList == null || subLocationList.size() == 0)
				return null;

			for (LocationVO subVo : subLocationList) {
				if (id !=null && !id.trim().isEmpty() && subVo.getLocationId().equalsIgnoreCase(id.trim())) {
					return subVo;
				}
				
				
			}
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeMatchVOSub() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @param : InputVO inputVo which year
	 * @return : List<RwsMinHabView> 
	 * @Description : This service is used to get all habitation details
	 * @author : santosh kumar verma
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<RwsMinHabView> getAllHabitationDetails(InputVO inputVO) {
		try {
			LOG.info("Entered into getAllHabitationDetails() in HabitationDetailsService class");
			if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
			}
			List<RwsMinHabView> list = rwsMinHabViewDAO.getAllHabitationDetails(inputVO);
			return list;
		} catch (Exception e) {
			LOG.error("Error occured at getAllHabitationDetails() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * description : This service is used to get all district,contituency and mandal
	 * @author : santosh kumar verma
	 * @return : List<RwsMinConstituencyView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<RwsMinConstituencyView> getAllHabitationConstitencyData(){
		try{

			LOG.info("Entered into getAllHabitationConstitencyData() in HabitationDetailsService class");
			List<RwsMinConstituencyView> list = rwsMinConstituencyViewDAO.getAll(); 
			return list;
		}catch(Exception e){
			LOG.error("Error occured at getAllHabitationConstitencyData() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *@Description : This service is used to get all assets info details.
	 * @author : santosh kumar verma
	 * @return : List<RwsMinAssetView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<RwsMinAssetView> getAllAssestDetails(){
		try{
			LOG.info("Entered into getAllAssestDetails() in HabitationDetailsService class");
			List<RwsMinAssetView> list = rwsMinAssetViewDAO.getAll(); 
			return list;
		}catch(Exception e){
			LOG.error("Error occured at getAllAssestDetails() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * @Description : This service is used to get all work Complition details.
	 * @author : sanjeev
	 * @return : List<RwsMinWorkscompView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<RwsMinWorkscompView> getAllWorkComplitionDetails(){
		try{
			LOG.info("Entered into getAllWorkComplitionDetails() in HabitationDetailsService class");
			List<RwsMinWorkscompView> list = rwsMinWorkscompViewDAO.getAll(); 
			return list;
		}catch(Exception e){
			LOG.error("Error occured at getAllWorkComplitionDetails() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * @Description : This service is used to get All Work Admin Details.
	 * @author : sanjeev
	 * @return : List<RwsMinWorksAdminView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public List<RwsMinWorksAdminView> getAllWorkAdminDetails(){
		try{
			LOG.info("Entered into getAllWorkAdminDetails() in HabitationDetailsService class");
			List<RwsMinWorksAdminView> list = rwsMinWorksAdminViewDAO.getAll(); 
			return list;
		}catch(Exception e){
			LOG.error("Error occured at getAllWorkAdminDetails() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;

	}

	public BasicVO setGroundWaterSourceData(List<Object[]> objList) {
		BasicVO groundWaterSourceVO = new BasicVO();
		try {
			groundWaterSourceVO.setGroundWaterSourceCount(0l);
			groundWaterSourceVO.setGroundWaterSourceTotalLpdCount(0l);
			groundWaterSourceVO.setSurfaceWaterSourceCount(0l);
			groundWaterSourceVO.setSurfaceWaterSourceTotalLpdCount(0l);
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					groundWaterSourceVO.setGroundWaterSourceCount(groundWaterSourceVO.getGroundWaterSourceCount()
									+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			return groundWaterSourceVO;
		} catch (Exception e) {
			LOG.error("Error occured at getGroundWaterSourceData() in HabitationDetailsService class",e);
			return groundWaterSourceVO;
		}
	}
	/**
	 * @Description : This service is used to get All targets and achievements Details.
	 * @author : sanjeev
	 * @return : List<RwsMinWorksAdminView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public String getKPIDeatils(InputVO inputVO) {
		JSONObject resultobj = new JSONObject();
		try{
			Double perecentage=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mmm-yy");
			if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			} else if (inputVO.getYear() != null && inputVO.getYear().length() > 0) {
				Long year = Long.valueOf(inputVO.getYear());
				Long priviousYear = year - 1;
				inputVO.setFromDate(sdf.parse("01-04-" + priviousYear));
				inputVO.setToDate(sdf.parse("01-04-" + year));

			}
			// for State Level
			List<Object[]> targetData =  rwsMinWorksAdminViewDAO.getStateLevelKPIDeatils(inputVO, IConstants.TARGET_ALL);
			List<Object[]> acheieveMentsData =  rwsMinWorksAdminViewDAO.getStateLevelKPIDeatils(inputVO,IConstants.TARGET_COMPLETED);
			if(inputVO.getLocationType().equalsIgnoreCase("state")){
			perecentage = targetAndAcheievementCalculatePercentage(targetData, acheieveMentsData);	
			}
			resultobj.put("targetData", targetData);
			resultobj.put("acheieveMentsData", acheieveMentsData);
			resultobj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			resultobj.put("percentage", perecentage);
		
			return resultobj.toString();
		}catch(Exception e){
			resultobj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			resultobj.put("exceptionMessage", e.getLocalizedMessage());
			LOG.error("Error occured at getKPIDeatils() in HabitationDetailsService class",e);
			return resultobj.toString();
		}
	}
	
	public LocationVO getStateLevelAllHabAndStressedHabData(List<Object[]> statusObjList,Map<String, Long> stressedHabMap) {
	    LocationVO resultVO = new LocationVO();
	    try {
	      resultVO.setLocationId("01");
	      resultVO.setLocationName("Andhra Pradesh");
	      resultVO.setStatusList(new ArrayList<StatusVO>());
	      if (statusObjList != null && statusObjList.size() > 0) {
	        for (Object[] param : statusObjList) {
	          StatusVO statusVO = new StatusVO();
	          statusVO.setStressedHabitationCount(0l);
	          statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[0]));
	          statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[1]));
	           if(stressedHabMap != null){
	             if(stressedHabMap.get(commonMethodsUtilService.getStringValueForObject(param[0])) != null){
	               statusVO.setStressedHabitationCount(stressedHabMap.get(commonMethodsUtilService.getStringValueForObject(param[0])));
	             }
	           }
	           //calculating percentage
	           statusVO.setPercentage(calculatePercantage(statusVO.getStressedHabitationCount(), statusVO.getCount()));
	           resultVO.getStatusList().add(statusVO);
	        }
	      }
	      resultVO.setStatus(IConstants.RESULT_SUCCESS);
	    } catch (Exception e) {
	      LOG.error("Error occured at getStateLevelAllHabAndStressedHabData() in HabitationDetailsService class",e);
	      resultVO.setStatus(IConstants.RESULT_FAILURE);
	      resultVO.setExceptionMessage(e.getLocalizedMessage());
	    }
	    return resultVO;
	  }
	
	private Double targetAndAcheievementCalculatePercentage(List<Object[]> stateLevelTargetData, List<Object[]> stateLevelAcheieveMentsData) {
		Long totalAcheievement = 0l;
		Long totalTarget =0l;
		
		if(stateLevelTargetData!=null && stateLevelTargetData.size()>0 && stateLevelAcheieveMentsData != null && stateLevelAcheieveMentsData.size()>0 ){
		for(Object[] param : stateLevelAcheieveMentsData){
			totalAcheievement = totalAcheievement+commonMethodsUtilService.getLongValueForObject(param[3]);
		}
		for(Object[] targetparam : stateLevelTargetData){
			totalTarget = totalTarget+commonMethodsUtilService.getLongValueForObject(targetparam[3]);
		}
		 Double percentage =calculatePercantage(totalAcheievement,totalTarget);
		return percentage;
		}else{
			return 0.0;
		}
		
	}
	/**
	 * @Description : This service is used to get All targets and achievements Details.
	 * @author : sanjeev
	 * @return : List<RwsMinWorksAdminView> 
	 * @since 14-JUNE-2017
	 */
	@Override
	public String getStressedKPIDeatils(InputVO inputVO) {
		JSONObject resultobj = new JSONObject();
		try{
			Long year =null ;
			Double perecentage=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			
			if(inputVO.getFromDateStr()!= null && !inputVO.getFromDateStr().trim().isEmpty()  && inputVO.getToDateStr()!= null && !inputVO.getToDateStr().trim().isEmpty()){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}else if(inputVO.getYear() != null && inputVO.getYear().length() > 0){
				year = Long.valueOf(inputVO.getYear());
				Long fromYear = year - 1;
				inputVO.setFromDate(sdf.parse("01-04-" + fromYear));
				inputVO.setToDate(sdf.parse("01-04-" + year));
				for (Long i = fromYear; i <= year; i++) {
					inputVO.getStressedHabitationYearsList().add(i.toString());
				}
			}
			if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
				Long fromYear = Long.parseLong(inputVO.getFromDateStr().split("-")[2]);
				Long toYear = Long.parseLong(inputVO.getToDateStr().split("-")[2]);
				for (Long i = fromYear; i <= toYear; i++) {
					inputVO.getStressedHabitationYearsList().add(i.toString());
				}
			}
			
			// for State Level
			List<Object[]> stressedHabtationTargetData =  rwsMinWorkscompViewDAO.getStressedKPIDeatils(inputVO, IConstants.TARGET_ALL);
			List<Object[]> stressedHabtationAcheieveMentsData =  rwsMinWorkscompViewDAO.getStressedKPIDeatils(inputVO,IConstants.TARGET_COMPLETED);
			if(inputVO.getLocationType().equalsIgnoreCase("state")){
				perecentage = targetAndAcheievementCalculatePercentage(stressedHabtationTargetData, stressedHabtationAcheieveMentsData);	
			}
			resultobj.put("stressedHabtationTargetData", stressedHabtationTargetData);
			resultobj.put("stressedHabtationAcheieveMentsData", stressedHabtationAcheieveMentsData);
			resultobj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			resultobj.put("percentage", perecentage);
		
			return resultobj.toString();
		}catch(Exception e){
			LOG.error("Error occured at getKPIDeatils() in HabitationDetailsService class",e);
			resultobj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			resultobj.put("exceptionMessage", e.getCause());
			return resultobj.toString();
		}
	}
	
	/**
	 * @Description : This service is used to get All Work Admin Details.
	 * @author : sanjeev
	 * @return : List<RwsMinWorksAdminView> 
	 * @since 02-JULY-2017
	 */
	// new changes
	@Override
	public String getWaterSourceDeatils(InputVO inputVo) {
		JSONObject object = new JSONObject();
	    try{
	      LOG.info("Entered into getWaterSourceDeatils() in HabitationDetailsService class");

	      if(IConstants.SUPPLY_TYPE_SAFE == "safe"){
	    
	    	  Long handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils(inputVo,IConstants.SUPPLY_TYPE_SAFE);
	    	  Long shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils(inputVo,IConstants.SUPPLY_TYPE_SAFE);
	    	  Long subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SUBSURFACE SOURCE",IConstants.SUPPLY_TYPE_SAFE);
		      
		      Long safeSurfaceWaterSourceCount = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SURFACE SOURCE",IConstants.SUPPLY_TYPE_SAFE);
		      Long safeGroundWaterSourceCount= handpumpsObjArr+shallowHandpumpObjArr+subSurfaceSourcesObjArr;
		      object.put("safeSurfaceWaterSourceCount", safeSurfaceWaterSourceCount);
		      object.put("safeGroundWaterSourceCount", safeGroundWaterSourceCount);
		      
	      }
	      String totalWaterSourceCount= "total";
		if(totalWaterSourceCount.equalsIgnoreCase("total")){
	    	 
	    	  Long handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils(inputVo,totalWaterSourceCount);
	    	  Long shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils(inputVo,totalWaterSourceCount );
	    	  Long subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SUBSURFACE SOURCE",totalWaterSourceCount);
	      
	      Long totalSurfaceWaterSourceCount = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SURFACE SOURCE",totalWaterSourceCount);
	      Long  totalGroundWaterSourceCount = handpumpsObjArr+shallowHandpumpObjArr+subSurfaceSourcesObjArr;
	     

	      object.put("totalSurfaceWaterSourceCount", totalSurfaceWaterSourceCount);
	      object.put("totalGroundWaterSourceCount", totalGroundWaterSourceCount);
	       
		      
	      }
	      if( IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase("un-safe")){
		    	 
	      Long handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils(inputVo,IConstants.SUPPLY_TYPE_UNSAFE);
	      Long shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils(inputVo,IConstants.SUPPLY_TYPE_UNSAFE );
	      Long subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SUBSURFACE SOURCE",IConstants.SUPPLY_TYPE_UNSAFE);
	      
	       Long unSafeSurfaceWaterSourceCount = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils(inputVo,"SURFACE SOURCE",IConstants.SUPPLY_TYPE_UNSAFE);
	       Long unSafeGroundWaterSourceCount =handpumpsObjArr+shallowHandpumpObjArr+subSurfaceSourcesObjArr;
	     
	       object.put("unSafeSurfaceWaterSourceCount", unSafeSurfaceWaterSourceCount);
	       object.put("unSafeGroundWaterSourceCount",unSafeGroundWaterSourceCount);
		      
	      }
	
	      object.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);

	      return object.toString();
		} catch (Exception e) {
			JSONObject objectError = new JSONObject();
			LOG.error("Error occured at getWaterSourceDeatils() in HabitationDetailsService class",e);
			objectError.put("status", IConstants.RESULT_FAILURE);
			objectError.put("exceptionMessage", e.getLocalizedMessage());
			return object.toString();
		}
	   
	  }
	
	private List<LocationVO> prepareMandalWorkLevelData(List<Object[]> objList,	List<LocationVO> statusList) {
		
		List<LocationVO> resultList = new ArrayList<>(0);
		try {
			Map<String, Map<String, LocationVO>> districtMap = new LinkedHashMap<>();
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					Map<String, LocationVO> mandalMap = districtMap.get(commonMethodsUtilService.getStringValueForObject(param[4]));
					if (mandalMap == null) {
						mandalMap = new LinkedHashMap<>();
						districtMap.put(commonMethodsUtilService.getStringValueForObject(param[4]), mandalMap);
					}
					LocationVO mandalVO = mandalMap.get(commonMethodsUtilService.getStringValueForObject(param[2]));
					if (mandalVO == null) {
						mandalVO = new LocationVO();
						mandalVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[2]));
						mandalVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
						mandalVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]));
						
						if(statusList !=null && statusList.size()>0){
							for (LocationVO vo : statusList) {
								LocationVO assetVo  = new LocationVO();
								assetVo.setAssetType(vo.getAssetType());// pws/cpws/...
								assetVo.setWorkComissionedCount(0l);
								assetVo.setWorkCompletedCount(0l);
								assetVo.setWorkNotGroundedCount(0l);
								assetVo.setWorkOngoingCount(0l);
								mandalVO.getSubList().add(assetVo)	;							
								
							}
						}
						
						mandalMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), mandalVO);

					}
				}
			}
			
			// Prepare Final Result List
			if (districtMap != null && districtMap.size() > 0) {

				for (Entry<String, Map<String, LocationVO>> distEntry : districtMap.entrySet()) {
					LocationVO districtVO = new LocationVO();
					districtVO.setLocationId(distEntry.getKey());
					if (distEntry.getValue() != null && distEntry.getValue().size() > 0) {
						districtVO.setSubList(new ArrayList<>(distEntry.getValue().values()));
					}
					resultList.add(districtVO);
				}

			}
			if(resultList.size()>0){
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return resultList;
		} catch (Exception e) {
			LOG.error("Error occured at preparedStrssedHabMandalLevelData() in HabitationDetailsService class",	e);
			LocationVO statusVO = new LocationVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
			return resultList;
		}
	}
	
	public Map<String, LocationVO> setLocationbasedList(List<Object[]> objList,List<LocationVO> assetTypesList) {
		try {

			Map<String, LocationVO> locationMap = new LinkedHashMap<String, LocationVO>();

			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String locationValue = commonMethodsUtilService.getStringValueForObject(param[2]);
					LocationVO locationVO = locationMap.get(locationValue);
					if (locationVO == null) {
							locationVO = new LocationVO();
							locationVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[2]));
							locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
							
							if(assetTypesList !=null && assetTypesList.size()>0){
								for (LocationVO vo : assetTypesList) {
									LocationVO assetVo  = new LocationVO();
									assetVo.setAssetType(vo.getAssetType());// pws/cpws/...
									assetVo.setWorkComissionedCount(0l);
									assetVo.setWorkCompletedCount(0l);
									assetVo.setWorkNotGroundedCount(0l);
									assetVo.setWorkOngoingCount(0l);
									locationVO.getSubList().add(assetVo);
								}
							}
						}
						locationMap.put(locationVO.getLocationId(), locationVO);
					}
					
			}
			
			return locationMap;
		} catch (Exception e) {
			LOG.error("Error occured at setLocationWiseHabitationDtls() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<LocationVO> getWaterSourceDeatils2(InputVO inputVo) {
		Map<String, LocationVO> locationMap = new HashMap<String, LocationVO>(0);
		List<LocationVO> locationList = new ArrayList<LocationVO>(0);
		Map<String, Map<String, LocationVO>> districtMap = new LinkedHashMap<>();
		try {
			
			LOG.info("Entered into getWaterSourceDeatils() in HabitationDetailsService class");
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			if (inputVo.getFromDateStr() != null && inputVo.getFromDateStr().length() > 0 && inputVo.getToDateStr() != null && inputVo.getToDateStr().length() > 0) {
				inputVo.setFromDate(sdf.parse(inputVo.getFromDateStr()));
				inputVo.setToDate(sdf.parse(inputVo.getToDateStr()));
			} else if (inputVo.getYear() != null && inputVo.getYear().length() > 0) {
				Long year = Long.valueOf(inputVo.getYear());
				Long priviousYear = year - 1;
				inputVo.setFromDate(sdf.parse("01-04-" + priviousYear));
				inputVo.setToDate(sdf.parse("01-04-" + year));

			}
			if (IConstants.SUPPLY_TYPE_SAFE == "safe") {

				List<Object[]> handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils2(inputVo,IConstants.SUPPLY_TYPE_SAFE);
				List<Object[]> shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils2(inputVo,IConstants.SUPPLY_TYPE_SAFE);
				List<Object[]> subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SUBSURFACE SOURCE",IConstants.SUPPLY_TYPE_SAFE);
				List<Object[]> safeSurfaceWaterSourceObjArr= rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SURFACE SOURCE",IConstants.SUPPLY_TYPE_SAFE);
				
				List<Object[]> groundSafeList = new ArrayList<Object[]>();
				
				groundSafeList.addAll(handpumpsObjArr);
				groundSafeList.addAll(shallowHandpumpObjArr);
				groundSafeList.addAll(subSurfaceSourcesObjArr);
				
				if(inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
					districtMap= prepareMandalWaterData(groundSafeList,districtMap, "groundSafe");
					districtMap = prepareMandalWaterData(safeSurfaceWaterSourceObjArr, districtMap,"surfaceSafe");
					
				} else {
					locationMap = setLocationbasedWaterList(groundSafeList,locationMap, "groundSafe");
					locationMap = setLocationbasedWaterList(safeSurfaceWaterSourceObjArr, locationMap,"surfaceSafe");
				}

			}
			String totalWaterSourceCount = "total";
			if (totalWaterSourceCount.equalsIgnoreCase("total")) {

				List<Object[]> handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils2(inputVo, totalWaterSourceCount);
				List<Object[]> shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils2(inputVo, totalWaterSourceCount);
				List<Object[]> subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SUBSURFACE SOURCE",totalWaterSourceCount);
				List<Object[]> totalSurfaceWaterSourceCount = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SURFACE SOURCE",totalWaterSourceCount);
				
				List<Object[]> groundTotalList = new ArrayList<Object[]>();
				groundTotalList.addAll(handpumpsObjArr);
				groundTotalList.addAll(shallowHandpumpObjArr);
				groundTotalList.addAll(subSurfaceSourcesObjArr);

				if(inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
					districtMap= prepareMandalWaterData(groundTotalList,districtMap,"groundTotal");
					districtMap = prepareMandalWaterData(totalSurfaceWaterSourceCount, districtMap,"surfaceTotal");
					
				} else {
					locationMap = setLocationbasedWaterList(groundTotalList,locationMap,"groundTotal");
					locationMap = setLocationbasedWaterList(totalSurfaceWaterSourceCount,locationMap,"surfaceTotal");

				}


			}
			if (IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase("un-safe")) {
				List<Object[]> handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatils2(inputVo,IConstants.SUPPLY_TYPE_UNSAFE);
				List<Object[]> shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatils2(inputVo,IConstants.SUPPLY_TYPE_UNSAFE);
				List<Object[]> subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SUBSURFACE SOURCE",IConstants.SUPPLY_TYPE_UNSAFE);
				
				List<Object[]> groundUnSafeList = new ArrayList<Object[]>();
				groundUnSafeList.addAll(handpumpsObjArr);
				groundUnSafeList.addAll(shallowHandpumpObjArr);
				groundUnSafeList.addAll(subSurfaceSourcesObjArr);
				
				List<Object[]> unSafeSurfaceWaterSourceCount = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatils2(inputVo, "SURFACE SOURCE",IConstants.SUPPLY_TYPE_UNSAFE);
				if(inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
					districtMap= prepareMandalWaterData(groundUnSafeList,districtMap,"unSafeGround");
					districtMap = prepareMandalWaterData(unSafeSurfaceWaterSourceCount, districtMap,"unsafeSurface");
					
				} else {
					locationMap = setLocationbasedWaterList(groundUnSafeList,locationMap,"unSafeGround");
					locationMap = setLocationbasedWaterList(unSafeSurfaceWaterSourceCount,locationMap,"unsafeSurface");

				}

			}
			if (locationMap != null && locationMap.size() > 0) {
				locationList.addAll(locationMap.values());
				locationMap.clear();
			}
			if (districtMap != null && districtMap.size() > 0) {

				for (Entry<String, Map<String, LocationVO>> distEntry : districtMap.entrySet()) {
					LocationVO districtVO = new LocationVO();
					districtVO.setLocationId(distEntry.getKey());
					if (distEntry.getValue() != null && distEntry.getValue().size() > 0) {
						districtVO.setSubList(new ArrayList<>(distEntry.getValue().values()));
					}
					locationList.add(districtVO);
				}

			}
			if(locationList.size()>0){
				locationList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return locationList;
		} catch (Exception e) {
			LOG.error("Error occured at getWaterSourceDeatils() in HabitationDetailsService class",e);
			LocationVO basicVO = new LocationVO();
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			basicVO.setExceptionMessage(e.getLocalizedMessage());
			locationList.add(basicVO);
			return locationList;
		}

	}
	
	public Map<String, LocationVO> setLocationbasedWaterList(List<Object[]> objList, Map<String, LocationVO> locationMap, String supplyType) {
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String locationValue = commonMethodsUtilService.getStringValueForObject(param[1]);
					LocationVO locationVO = locationMap.get(locationValue);
					if (locationVO == null) {
						locationVO = new LocationVO();
						locationMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), locationVO);
						locationVO.setSafeGroundCount(0l);
						locationVO.setSafeSurfaceCount(0l);
						locationVO.setUnSafeGroundCount(0l);
						locationVO.setUnSafeSurfaceCount(0l);
						locationVO.setGroundTotalCount(0l);
						locationVO.setSurfaceTotalCount(0l);
					}
					locationVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
					
					if(supplyType.trim().equalsIgnoreCase("groundSafe")){
						locationVO.setSafeGroundCount(locationVO.getSafeGroundCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(supplyType.trim().equalsIgnoreCase("surfaceSafe")){
						locationVO.setSafeSurfaceCount(locationVO.getSafeSurfaceCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(supplyType.trim().equalsIgnoreCase("unSafeGround")){
						locationVO.setUnSafeGroundCount(locationVO.getUnSafeGroundCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(supplyType.trim().equalsIgnoreCase("unsafeSurface")){
						locationVO.setUnSafeSurfaceCount(locationVO.getUnSafeSurfaceCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(supplyType.trim().equalsIgnoreCase("groundTotal")){
						locationVO.setGroundTotalCount(locationVO.getGroundTotalCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(supplyType.trim().equalsIgnoreCase("surfaceTotal")){
						locationVO.setSurfaceTotalCount(locationVO.getSurfaceTotalCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			return locationMap;
		} catch (Exception e) {
			LOG.error("Error occured at setLocationWiseHabitationDtls() in HabitationDetailsService class",e);
			e.printStackTrace();
		}
		return null;
	}

private Map<String, Map<String, LocationVO>>  prepareMandalWaterData(List<Object[]> objList,Map<String, Map<String, LocationVO>> districtMap, String supplyType) {

	try {
		if (objList != null && objList.size() > 0) {
			for (Object[] param : objList) {
				Map<String, LocationVO> mandalMap = districtMap.get(commonMethodsUtilService.getStringValueForObject(param[3]));
				if (mandalMap == null) {
					mandalMap = new LinkedHashMap<>();
					districtMap.put(commonMethodsUtilService.getStringValueForObject(param[3]), mandalMap);
				}
				LocationVO mandalVO = mandalMap.get(commonMethodsUtilService.getStringValueForObject(param[1]));
				if (mandalVO == null) {
					mandalVO = new LocationVO();
					mandalVO.setLocationId(commonMethodsUtilService.getStringValueForObject(param[1]));
					mandalMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), mandalVO);
					mandalVO.setSafeGroundCount(0l);
					mandalVO.setSafeSurfaceCount(0l);
					mandalVO.setUnSafeGroundCount(0l);
					mandalVO.setUnSafeSurfaceCount(0l);
					mandalVO.setGroundTotalCount(0l);
					mandalVO.setSurfaceTotalCount(0l);
				}
				mandalVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
				mandalVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				if(supplyType.trim().equalsIgnoreCase("groundSafe")){
					mandalVO.setSafeGroundCount(mandalVO.getSafeGroundCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}else if(supplyType.trim().equalsIgnoreCase("surfaceSafe")){
					mandalVO.setSafeSurfaceCount(mandalVO.getSafeSurfaceCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}else if(supplyType.trim().equalsIgnoreCase("unSafeGround")){
					mandalVO.setUnSafeGroundCount(mandalVO.getUnSafeGroundCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}else if(supplyType.trim().equalsIgnoreCase("unsafeSurface")){
					mandalVO.setUnSafeSurfaceCount(mandalVO.getUnSafeSurfaceCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}else if(supplyType.trim().equalsIgnoreCase("groundTotal")){
					mandalVO.setGroundTotalCount(mandalVO.getGroundTotalCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}else if(supplyType.trim().equalsIgnoreCase("surfaceTotal")){
					mandalVO.setSurfaceTotalCount(mandalVO.getSurfaceTotalCount()+ commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
		}
		return districtMap;
	} catch (Exception e) {
		LOG.error("Error occured at preparedStrssedHabMandalLevelData() in HabitationDetailsService class",	e);
	
		return districtMap;
	}
}

}
