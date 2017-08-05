package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IDepartmentDiseasesInfoDAO;
import com.itgrids.dto.DiseasesVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.service.IHealthMedicalAndFamilyWelfareService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.SetterAndGetterUtilService;
@Service
@Transactional
public class HealthMedicalAndFamilyWelfareService implements IHealthMedicalAndFamilyWelfareService {
	private static final Logger LOG = Logger.getLogger( DrainsService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IDepartmentDiseasesInfoDAO departmentDiseasesInfoDAO;
	@Autowired
	private SetterAndGetterUtilService setterAndGetterUtilService;
	/*
	 * Author : Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountDiseasesWise(java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public List<DiseasesVO> getCaseCountDiseasesWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountDiseasesWise(startDate,endDate,diseasesIdList,deptIdList);
			Date today = dateUtilService.getCurrentDateAndTime();
			List<Object[]> todayDiseasesList = departmentDiseasesInfoDAO.getCaseCountDiseasesWise(today,today,diseasesIdList,deptIdList);
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			
			//only diseases wise count
			buildCaseCountDiseasesWise(diseasesVOs,diseasesList,todayDiseasesList);
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountDiseasesWise() ",e);
		}
		return null;
	}
	@SuppressWarnings("static-access")
	public void buildCaseCountDiseasesWise(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,List<Object[]> todayDiseasesList){
		try{
			List<DiseasesVO> tempVOList = new ArrayList<DiseasesVO>();
			DiseasesVO diseasesVO = null;
			DiseasesVO diseasesVOTemp = null;
			Long totalCount = 0L;
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
					tempVOList.add(diseasesVO);
				}
			}
			
			//total count
			diseasesVO = new DiseasesVO();
			diseasesVO.setId(0L);
			diseasesVO.setName("Dengue & Malaria Cases");
			diseasesVO.setCount(totalCount);
			diseasesVOs.add(diseasesVO);
			
			//Malaria count
			diseasesVO = new DiseasesVO();
			diseasesVO.setId(1L);
			diseasesVO.setName("Malaria");
			diseasesVO.setTodayCount(0L);
			diseasesVOTemp = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(tempVOList, "id", "1");
			if(diseasesVOTemp != null){
				diseasesVO.setCount(diseasesVOTemp.getCount());
				diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(diseasesVOTemp.getCount(),totalCount));
			}else{
				diseasesVO.setPercent(0.0D);
				diseasesVO.setCount(0L);
			}
			diseasesVOs.add(diseasesVO);
			
			//Dengue count
			diseasesVO = new DiseasesVO();
			diseasesVO.setId(2L);
			diseasesVO.setName("Dengue");
			diseasesVO.setTodayCount(0L);
			diseasesVOTemp = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(tempVOList, "id", "2");
			if(diseasesVOTemp != null){
				diseasesVO.setCount(diseasesVOTemp.getCount());
				diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(diseasesVOTemp.getCount(),totalCount));
			}else{
				diseasesVO.setPercent(0.0D);
				diseasesVO.setCount(0L);
			}
			diseasesVOs.add(diseasesVO);
			
			//set deseases wise case count on today.
			Long todayTotal = 0L;
			if(todayDiseasesList != null && todayDiseasesList.size() > 0){
				for(Object[] param : todayDiseasesList){
					diseasesVOTemp = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs, "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
					diseasesVOTemp.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					todayTotal = todayTotal + commonMethodsUtilService.getLongValueForObject(param[2]);
				}
			}
			//set today total
			diseasesVOTemp = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs, "id", "0");
			diseasesVOTemp.setTodayCount(todayTotal);
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountDiseasesWise() ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountLocationWise(java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.Long)
	 */
	@Override
	public List<DiseasesVO> getCaseCountLocationWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long scope,Long locationId){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			String locationLevelIdStr = "";
 			String locationIdStr = locationId.toString();
 			
 			locationLevelIdStr =locationIdStr.substring(0, 1);
 			
			locationIdStr = locationIdStr.substring(1);
			
			if(locationIdStr != null && locationIdStr.trim().length() > 0){
				locationId = Long.parseLong(locationIdStr);
			}
			
			Long locationLevelId = Long.parseLong(locationLevelIdStr);
			
			if(locationLevelId.longValue()== 9L){
				locationLevelId = 10L;
			}
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountLocationWise(startDate,endDate,diseasesIdList,deptIdList,scope,locationLevelId,locationId);
			Date today = dateUtilService.getCurrentDateAndTime();
			List<Object[]> diseasesListToday = departmentDiseasesInfoDAO.getCaseCountLocationWise(today,today,diseasesIdList,deptIdList,scope,locationLevelId,locationId);
			
			//only diseases wise count
			List<DiseasesVO> diseasesVOs = buildCaseCountLocationWise(diseasesList,diseasesListToday,scope);
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountLocationWise() ",e);
		}
		return null;
	}
	@SuppressWarnings("static-access")
	public List<DiseasesVO> buildCaseCountLocationWise(List<Object[]> diseasesList,List<Object[]> diseasesListToday,Long scopeId){
		try{
			Map<Long,List<DiseasesVO>> diseasesWiseLocMap = new HashMap<Long,List<DiseasesVO>>();
			DiseasesVO diseasesVO = null;
			DiseasesVO tempDiseasesVO = null;
			//create Id and name map.
			Map<Long,String> locIdAndNameMap = new HashMap<Long,String>();
			initializeVOForCalculation(diseasesWiseLocMap,locIdAndNameMap,diseasesList,scopeId);
			//build template
			List<DiseasesVO> diseasesVOs2 = new ArrayList<DiseasesVO>();
			if(locIdAndNameMap != null && locIdAndNameMap.size() > 0){
				for(Entry<Long,String> param : locIdAndNameMap.entrySet()){
					diseasesVO = new DiseasesVO();
					diseasesVO.setId(param.getKey());
					diseasesVO.setName(param.getValue());
					
					if(diseasesWiseLocMap != null){
						if(diseasesWiseLocMap.get(1L) != null && setterAndGetterUtilService.getMatchedVOfromList(diseasesWiseLocMap.get(1L), "id", diseasesVO.getId().toString()) != null){
							tempDiseasesVO = (DiseasesVO)setterAndGetterUtilService.getMatchedVOfromList(diseasesWiseLocMap.get(1L), "id", diseasesVO.getId().toString());
						}else{
							tempDiseasesVO = (DiseasesVO)setterAndGetterUtilService.getMatchedVOfromList(diseasesWiseLocMap.get(2L), "id", diseasesVO.getId().toString());
						}
					}
					
					diseasesVO.setDistrictId(tempDiseasesVO.getDistrictId());
					diseasesVO.setDistrictName(tempDiseasesVO.getDistrictName());
					diseasesVO.setParliamentId(tempDiseasesVO.getParliamentId());
					diseasesVO.setParliamentName(tempDiseasesVO.getParliamentName());
					diseasesVO.setConstituencyId(tempDiseasesVO.getConstituencyId());
					diseasesVO.setConstituencyName(tempDiseasesVO.getConstituencyName());
					diseasesVO.setMandalId(tempDiseasesVO.getMandalId());
					diseasesVO.setMandalName(tempDiseasesVO.getMandalName());
					diseasesVO.setPanchayatId(tempDiseasesVO.getPanchayatId());
					diseasesVO.setPanchayatName(tempDiseasesVO.getPanchayatName());
					//total
					diseasesVO.setTotal(0L);
					diseasesVO.setTodayTotal(0L);
					diseasesVO.setTotalPercent(0.0D);
					//Dengue
					diseasesVO.setDengueToday(0L);
					diseasesVO.setDengueTotal(0L);
					diseasesVO.setDenguePercent(0.0D);
					//Malaria
					diseasesVO.setMalariaToday(0L);
					diseasesVO.setMalariaTotal(0L);
					diseasesVO.setMalariaPercent(0.0D);
					
					diseasesVOs2.add(diseasesVO);
				}
			}
			
			List<DiseasesVO> malariaList = new ArrayList<DiseasesVO>();
			DiseasesVO tempDiseasesVo = null;
			//push malaria count
			if(diseasesWiseLocMap != null && diseasesWiseLocMap.size() > 0){
				if(diseasesWiseLocMap.get(1L) != null && diseasesWiseLocMap.get(1L).size() > 0){
					malariaList = diseasesWiseLocMap.get(1L);
					for(DiseasesVO param : malariaList){
						tempDiseasesVo = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs2, "id", param.getId().toString());
						tempDiseasesVo.setMalariaTotal(param.getCount());
					}
				}
			}
			//push dengue count
			if(diseasesWiseLocMap != null && diseasesWiseLocMap.size() > 0){
				if(diseasesWiseLocMap.get(2L) != null && diseasesWiseLocMap.get(2L).size() > 0){
					malariaList = diseasesWiseLocMap.get(2L);
					for(DiseasesVO param : malariaList){
						tempDiseasesVo = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs2, "id", param.getId().toString());
						tempDiseasesVo.setDengueTotal(param.getCount());
					}
				}
			}
			//push today count for dengue and malaria->diseasesListToday
			Map<Long,List<DiseasesVO>> diseasesWiseLocMapForToday = new HashMap<Long,List<DiseasesVO>>();
			Map<Long,String> locIdAndNameMapForToday = new HashMap<Long,String>();
			initializeVOForCalculation(diseasesWiseLocMapForToday,locIdAndNameMapForToday,diseasesListToday,scopeId);
			
			//push malaria count today
			if(diseasesWiseLocMapForToday != null && diseasesWiseLocMapForToday.size() > 0){
				if(diseasesWiseLocMapForToday.get(1L) != null && diseasesWiseLocMapForToday.get(1L).size() > 0){
					malariaList = diseasesWiseLocMapForToday.get(1L);
					for(DiseasesVO param : malariaList){
						tempDiseasesVo = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs2, "id", param.getId().toString());
						tempDiseasesVo.setMalariaToday(param.getCount());
						tempDiseasesVo.setMalariaPercent(commonMethodsUtilService.calculatePercantage(tempDiseasesVo.getMalariaToday(),tempDiseasesVo.getMalariaTotal()));
					}
				}
			}
			
			//push dengue count today
			if(diseasesWiseLocMapForToday != null && diseasesWiseLocMapForToday.size() > 0){
				if(diseasesWiseLocMapForToday.get(2L) != null && diseasesWiseLocMapForToday.get(2L).size() > 0){
					malariaList = diseasesWiseLocMapForToday.get(2L);
					for(DiseasesVO param : malariaList){
						tempDiseasesVo = (DiseasesVO) setterAndGetterUtilService.getMatchedVOfromList(diseasesVOs2, "id", param.getId().toString());
						tempDiseasesVo.setDengueToday(param.getCount());
						tempDiseasesVo.setDenguePercent(commonMethodsUtilService.calculatePercantage(tempDiseasesVo.getDengueToday(),tempDiseasesVo.getDengueTotal()));
					}
				}
			}
			
			//push total count
			if(diseasesVOs2 != null && diseasesVOs2.size() > 0){
				for(DiseasesVO param : diseasesVOs2){
					param.setTodayTotal(param.getMalariaToday() + param.getDengueToday());
					param.setTotal(param.getMalariaTotal() + param.getDengueTotal());
					param.setTotalPercent(commonMethodsUtilService.calculatePercantage(param.getTodayTotal(),param.getTotal()));
					if(param.getTotal().longValue() >= 70){
						param.setColor("red");
					}else if(param.getTotal().longValue() >= 31){
						param.setColor("yellow");
					}else{
						param.setColor("green");
					}
				}
			}
			return diseasesVOs2;
			
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountLocationWise() ",e);
		}
		return null;
	}
	public void initializeVOForCalculation(Map<Long,List<DiseasesVO>> diseasesWiseLocMap,Map<Long,String> locIdAndNameMap,List<Object[]> diseasesList,Long scopeId){
		try{
			List<DiseasesVO> tempList = null;
			DiseasesVO diseasesVO = null;
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[4]));
					diseasesVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[5]));
					diseasesVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[6]));
					diseasesVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[7]));
					diseasesVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[8]));
					diseasesVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[9]));
					diseasesVO.setMandalId(commonMethodsUtilService.getLongValueForObject(param[10]));
					diseasesVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[11]));
					diseasesVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[12]));
					diseasesVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[13]));
					if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getDistrictId());
						diseasesVO.setName(diseasesVO.getDistrictName());
						locIdAndNameMap.put(diseasesVO.getDistrictId(), diseasesVO.getDistrictName());
					}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getParliamentId());
						diseasesVO.setName(diseasesVO.getParliamentName());
						locIdAndNameMap.put(diseasesVO.getParliamentId(), diseasesVO.getParliamentName());
					}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getConstituencyId());
						diseasesVO.setName(diseasesVO.getConstituencyName());
						locIdAndNameMap.put(diseasesVO.getConstituencyId(), diseasesVO.getConstituencyName());
					}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getMandalId());
						diseasesVO.setName(diseasesVO.getMandalName());
						locIdAndNameMap.put(diseasesVO.getMandalId(), diseasesVO.getMandalName());
					}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getPanchayatId());
						diseasesVO.setName(diseasesVO.getPanchayatName());
						locIdAndNameMap.put(diseasesVO.getPanchayatId(), diseasesVO.getPanchayatName());
					}
					
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
					
					tempList = diseasesWiseLocMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(tempList == null){
						tempList = new ArrayList<DiseasesVO>();
						tempList.add(diseasesVO);
					}else{
						tempList.add(diseasesVO);
					}
					diseasesWiseLocMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), tempList);
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,initializeVOForCalculation() ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountDateWise(java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.String)
	 */
	@Override
	public List<DiseasesVO> getCaseCountDateWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String rangeType){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountDateWise(startDate,endDate,diseasesIdList,deptIdList);
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			if(diseasesList != null && diseasesList.size() > 0){
				buildCaseCountDateWise(diseasesVOs,diseasesList,rangeType,startDate,endDate);
			}
			
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountDateWise() ",e);
		}
		return null;
	}
	public void buildCaseCountDateWise(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,String rangeType,Date startDate, Date endDate ){
		try{
			if(rangeType != null && !rangeType.trim().isEmpty() && rangeType.trim().length() > 0){
				if(rangeType.trim().equalsIgnoreCase("day")){
					buildDayWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}else if(rangeType.trim().equalsIgnoreCase("week")){
					buildWeekWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}else if(rangeType.trim().equalsIgnoreCase("month")){
					buildMonthWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountDateWise() ",e);
		}
	}
	public void buildDayWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for day
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(String param : dayList){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param);
				diseasesVO.setFromDate(param);
				diseasesVO.setToDate(param);
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(param.getName()) != null){
						param.setCount(dateAndCaseCountMap.get(param.getName()));
						param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
					}
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	public void buildWeekWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for week
			LinkedHashMap<String,List<String>> weekAndDaysMap = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
			
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(Entry<String,List<String>> param : weekAndDaysMap.entrySet()){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param.getKey());
				diseasesVO.setFromDate(param.getValue().get(0));
				diseasesVO.setToDate(param.getValue().get(param.getValue().size()-1));
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					Long weekCount = 0L;
					for(String dateParam : weekAndDaysMap.get(param.getName())){
						if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(dateParam) != null){
							weekCount = weekCount + dateAndCaseCountMap.get(dateParam);
						}
					}
					param.setCount(weekCount);
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	public void buildMonthWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for week
			LinkedHashMap<String,List<String>> monthAndDaysMap = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			String fromDateStr = sdf1.format(fromDate);
			String toDateStr = sdf1.format(toDate);
			monthAndDaysMap = getMonthWeekAndDaysList(fromDateStr, toDateStr,"month");
			
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(Entry<String,List<String>> param : monthAndDaysMap.entrySet()){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param.getKey());
				diseasesVO.setFromDate(param.getValue().get(0));
				diseasesVO.setToDate(param.getValue().get(param.getValue().size()-1));
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					Long weekCount = 0L;
					for(String dateParam : monthAndDaysMap.get(param.getName())){
						if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(dateParam) != null){
							weekCount = weekCount + dateAndCaseCountMap.get(dateParam);
						}
					}
					param.setCount(weekCount);
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	@SuppressWarnings("static-access")
	public LinkedHashMap<String,List<String>> getMonthWeekAndDaysList(String startDate,String endDate,String type){
		LinkedHashMap<String,List<String>> returnDays = new LinkedHashMap<String, List<String>>();
    	try{
		
		List<String> wkDays = new ArrayList<String>();
		List<String> daysArr = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		if(type != null && type.trim().equalsIgnoreCase("month")){
		 List<String> mntDays = departmentDiseasesInfoDAO.getMonthAndYear(sdf.parse(startDate),sdf.parse(endDate));
			int i = 1;
			for (String string : mntDays) {
				Date dateee = sdf1.parse(string);
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				Date monthStart = cal.getTime();
				
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date monthEnd = cal.getTime();
				 
				cal.setTime(sdf.parse(startDate));
				Date  strDate = cal.getTime();
				cal.setTime(sdf.parse(endDate));
				Date  edDate = cal.getTime();
				
				if(i == 1){
					if(strDate.compareTo(monthStart) > 0){
						monthStart = strDate;
					}
				}
				if(i == mntDays.size()){
					if(monthEnd.compareTo(edDate) > 0){
						monthEnd = edDate;
					}
				}
				
				daysArr = dateUtilService.getDaysBetweenDatesStringFormat(monthStart,monthEnd);
				returnDays.put(string,daysArr);
				i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("week")){
			wkDays  = commonMethodsUtilService.getBetweenWeeks(sdf.parse(startDate),sdf.parse(endDate),"yyyy-MM-dd");
			int i=1;
				for (String string : wkDays) {
					String[] days = string.split("to");
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(days[0]),sdf.parse(days[1]));
					returnDays.put("week"+i,daysArr);
					i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("today")){
			List<String> noOfDays = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(startDate),sdf.parse(endDate));
			int i=1;
				for (String string : noOfDays) {
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(string),sdf.parse(string));
					returnDays.put("day"+i, daysArr);
					i++;
				}
		   }
    	}catch(Exception e){
    		LOG.error("Error occured getMonthWeekAndDays() method of HealthMedicalAndFamilyWelfareService",e);
    	}
		
		return returnDays;
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getLocationDtlsRankWise(java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	public List<DiseasesVO> getLocationDtlsRankWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList){
		try{
			
			DiseasesVO diseasesVO = null;
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			Long scopeId = 5L;
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getLocationDtlsRankWise(startDate,endDate,diseasesIdList,deptIdList,scopeId);
			
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[3]));
					diseasesVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[4]));
					diseasesVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[5]));
					diseasesVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[6]));
					diseasesVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
					diseasesVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[8]));
					diseasesVO.setMandalId(commonMethodsUtilService.getLongValueForObject(param[9]));
					diseasesVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[10]));
					diseasesVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[11]));
					diseasesVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[12]));
					if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getDistrictId());
						diseasesVO.setName(diseasesVO.getDistrictName());
					}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getParliamentId());
						diseasesVO.setName(diseasesVO.getParliamentName());
					}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getConstituencyId());
						diseasesVO.setName(diseasesVO.getConstituencyName());
					}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getMandalId());
						diseasesVO.setName(diseasesVO.getMandalName());
					}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
						diseasesVO.setId(diseasesVO.getPanchayatId());
						diseasesVO.setName(diseasesVO.getPanchayatName());
					}
					
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVOs.add(diseasesVO);
				}
			}
			return diseasesVOs;
		}catch(Exception e){
			LOG.error("Error occured getLocationDtlsRankWise() method of HealthMedicalAndFamilyWelfareService",e);
		}
		return null;
	}
	/*
 	 * Swadhin K Lenka
 	 * (non-Javadoc)
 	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getAllSubLocationsBySuperLocationId(com.itgrids.dto.InputVO)
 	 */
 	@Override
 	public List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long superLocationId){
 		try{
 			List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
 			LocationFundDetailsVO locationFundDetailsVO = null;
 			List<Object[]> locationList = null;
 			String lvlIdStr = "";
 			Date sDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
 			Date eDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
 			
 			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
 			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
 			
 			String superLocationLevelIdStr = "";
 			String superLocationIdStr = superLocationId.toString();
 			
 			superLocationLevelIdStr = superLocationIdStr.substring(0, 1);
 			
			superLocationIdStr = superLocationIdStr.substring(1);
			
			if(superLocationIdStr != null && superLocationIdStr.trim().length() > 0){
				superLocationId = Long.parseLong(superLocationIdStr);
			}
			
			Long superLocationLevelId = Long.parseLong(superLocationLevelIdStr);
			
			if(superLocationLevelId.longValue()== 9L){
				superLocationLevelId = 10L;
			}
			
			if(superLocationLevelId != null && superLocationLevelId.longValue() == 1L){//get parliaments
				locationList = departmentDiseasesInfoDAO.getAllParliamentByStateId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = "9";
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = departmentDiseasesInfoDAO.getAllDistrictByStateId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = IConstants.DISTRICT_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = departmentDiseasesInfoDAO.getAllConstituencyByDistrictId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = departmentDiseasesInfoDAO.getAllTehsilByConstituencyId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = IConstants.MANDAL_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				locationList = departmentDiseasesInfoDAO.getAllPanchayatByTehsilId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = IConstants.VILLAGE_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = departmentDiseasesInfoDAO.getAllConstituencyByParliamentConstId(sDate,eDate,superLocationId,diseasesIdList,deptIdList);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();  
			}	
			if(locationList != null && locationList.size() > 0){
				for(Object[] param : locationList){
					locationFundDetailsVO = new LocationFundDetailsVO();
					String locId = commonMethodsUtilService.getStringValueForObject(param[0]);
					locId = lvlIdStr.concat(locId);
					locationFundDetailsVO.setId(Long.parseLong(locId));
					 String nameStr=commonMethodsUtilService.getStringValueForObject(param[1]);
						locationFundDetailsVO.setName(commonMethodsUtilService.toConvertStringToTitleCase(nameStr));
						detailsVOs.add(locationFundDetailsVO);
				}
			}
			return detailsVOs;
 		}catch(Exception e){
 			LOG.error("Exception Occurred in getAllSubLocationsBySuperLocationId() of FundManagementDashboardService ", e);
 			return null;
 		}
 	}
}
