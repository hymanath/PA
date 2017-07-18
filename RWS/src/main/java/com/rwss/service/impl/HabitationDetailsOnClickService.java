package com.rwss.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.rwss.dto.HabitationDetailsVO;
import com.rwss.dto.InputVO;
import com.rwss.dto.LocationVO;
import com.rwss.service.IHabitationDetailsOnClickService;
import com.rwss.utils.CommonMethodsUtilService;
import com.rwss.utils.IConstants;

@Service
@Transactional
public class HabitationDetailsOnClickService implements IHabitationDetailsOnClickService {

	private static final Logger LOG = LoggerFactory.getLogger(HabitationDetailsOnClickService.class);

	
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
	
	JSONObject jsonObj;

	/**
	 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
	 * @return : String  
	 * @Description : This service is used to get Habitation Details based on status and location selection
	 * @author : sanjay kumar
	 * @since 29-JUNE-2017
	 */	
	@Override
	public String getOnclickWorkDetails(InputVO inputVO) {
		jsonObj= new JSONObject();
		try{
			LOG.info("Entered into getOnclickWorkDetails() in HabitationDetailsService class");

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
			List<Object[]> object =null;
			if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMPLETION) || inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMMISSIONED) ){
				object=rwsMinWorkscompViewDAO.getOnclickWorkDetails(inputVO);
			}else if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_GROUNDED) || inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_NOTGROUNDED) ){
				object=rwsMinWorksAdminViewDAO.getOnclickWorkDetails(inputVO);
			}
			List<HabitationDetailsVO> habitationDetailsList = new ArrayList<HabitationDetailsVO>(0);
			if(object != null){
				for(Object[] param : object){
					HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
					habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
					habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
					habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
					habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
					habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
					habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
					habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
					habitationDetailsVO.setWorkId(commonMethodsUtilService.getStringValueForObject(param[8]));
					habitationDetailsVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[9]));
					if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMPLETION)){
						habitationDetailsVO.setGroundingDate(commonMethodsUtilService.getStringValueForObject(param[10].toString()));
						habitationDetailsVO.setCompletionDate(commonMethodsUtilService.getStringValueForObject(param[11].toString()));
						habitationDetailsVO.setSacntionedAmount(commonMethodsUtilService.getStringValueForObject(param[13]));
						habitationDetailsVO.setAssestType(commonMethodsUtilService.getStringValueForObject(param[14]));
					}else if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMMISSIONED)){
						habitationDetailsVO.setGroundingDate(commonMethodsUtilService.getStringValueForObject(param[10].toString()));
						habitationDetailsVO.setCommssionedDate(commonMethodsUtilService.getStringValueForObject(param[12].toString()));
						habitationDetailsVO.setSacntionedAmount(commonMethodsUtilService.getStringValueForObject(param[13]));
						habitationDetailsVO.setAssestType(commonMethodsUtilService.getStringValueForObject(param[14]));
					}else if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_GROUNDED)){
						habitationDetailsVO.setGroundingDate(commonMethodsUtilService.getStringValueForObject(param[10].toString()));
						habitationDetailsVO.setTargetDate(commonMethodsUtilService.getStringValueForObject(param[11].toString()));
						habitationDetailsVO.setSacntionedAmount(commonMethodsUtilService.getStringValueForObject(param[12]));
						habitationDetailsVO.setAssestType(commonMethodsUtilService.getStringValueForObject(param[13]));
					}
					
					habitationDetailsList.add(habitationDetailsVO);
				}
			}
			jsonObj.remove(IConstants.ERROR_MESSAGE);
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			jsonObj.put("onClickWorksList", habitationDetailsList);
			return jsonObj.toString();
		}catch(Exception e){
			LOG.error("Error occured at getOnclickWorkDetails() in HabitationDetailsService class",e);
			jsonObj.remove("onClickWorksList");
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jsonObj.put(IConstants.ERROR_MESSAGE, e.getMessage());
			return jsonObj.toString();
			
		}
		
	}

	/**
	 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
	 * @return : String  
	 * @Description : This service is used to get Habitation Details based on status and location selection
	 * @author : sanjay kumar
	 * @since 29-JUNE-2017
	 */	
	@Override
	public String getOnclickTargetsAcheievementsDetails(InputVO inputVO) {
		
		jsonObj = new JSONObject();
		try {
			LOG.info("Entered into getOnclickTargetsAcheievementsDetails() in HabitationDetailsService class");

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
			
			List<Object[]> object=rwsMinWorkscompViewDAO.getOnclickTargetsAcheievementsDetails(inputVO);
			
			List<HabitationDetailsVO> habitationDetailsList = new ArrayList<HabitationDetailsVO>(0);
			if(object != null){
				for(Object[] param : object){
					HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
					habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
					habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
					habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
					habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
					habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
					habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
					habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
					habitationDetailsVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[8]));
					
					habitationDetailsList.add(habitationDetailsVO);
				}
			}
			if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
				inputVO.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinWorkscompViewDAO.getOnclickTargetsAcheievementsDetails(inputVO);
				if (rtrnOverAllDtsObjLst != null) {
					jsonObj.put("totalCount", Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
			jsonObj.remove(IConstants.ERROR_MESSAGE);
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			jsonObj.put("onClickWorksList", habitationDetailsList);
			return jsonObj.toString();
		}catch(Exception e){
			LOG.error("Error occured at getOnclickTargetsAcheievementsDetails() in HabitationDetailsService class",e);

			jsonObj.remove("onClickWorksList");
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jsonObj.put(IConstants.ERROR_MESSAGE, e.getMessage());
			return jsonObj.toString();
			
		}
		
	
	}
	/**
	 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
	 * @return : String  
	 * @Description : This service is used to get Habitation Details based on status and location selection
	 * @author : sanjay kumar
	 * @since 29-JUNE-2017
	 */	
	
	@Override
	public String getOnclickStrssedTargetsAcheievementsDetails(InputVO inputVO) {
		
		jsonObj = new JSONObject();
		try {
			LOG.info("Entered into getOnclickStrssedTargetsAcheievementsDetails() in HabitationDetailsService class");

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			if(inputVO.getYear() != null && !inputVO.getYear().trim().isEmpty()){
				inputVO.getStressedHabitationYearsList().add(inputVO.getYear());
			}else if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
				Long fromYear = Long.parseLong(inputVO.getFromDateStr().split("-")[2]);
				Long toYear = Long.parseLong(inputVO.getToDateStr().split("-")[2]);
				for (Long i = fromYear; i <= toYear; i++) {
					inputVO.getStressedHabitationYearsList().add(i.toString());
				}
			}
			
			if(inputVO.getFromDateStr()!= null && !inputVO.getFromDateStr().trim().isEmpty()  && inputVO.getToDateStr()!= null && !inputVO.getToDateStr().trim().isEmpty()){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}else if(inputVO.getYear() != null && inputVO.getYear().length() > 0){
				Long year = Long.valueOf(inputVO.getYear());
				Long priviousYear = year - 1;
				inputVO.setFromDate(sdf.parse("01-04-" + priviousYear));
				inputVO.setToDate(sdf.parse("01-04-" + year));
				for (Long i = priviousYear; i <= year; i++) {
					inputVO.getStressedHabitationYearsList().add(i.toString());
				}
			}
			
			List<Object[]> object = rwsMinWorksAdminViewDAO.getOnclickStrssedTargetsAcheievementsDetails(inputVO);
			List<HabitationDetailsVO> habitationDetailsList = new ArrayList<HabitationDetailsVO>(0);
			if(object != null){
				for(Object[] param : object){
					HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
					habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
					habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
					habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
					habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
					habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
					habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
					habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
					habitationDetailsVO.setToatlPorpualtionCovered(commonMethodsUtilService.getLongValueForObject(param[8]));
					
					habitationDetailsList.add(habitationDetailsVO);
				}
			}
			if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
				inputVO.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinWorksAdminViewDAO.getOnclickStrssedTargetsAcheievementsDetails(inputVO);
				if (rtrnOverAllDtsObjLst != null) {
					jsonObj.put("totalCount", Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
			jsonObj.remove(IConstants.ERROR_MESSAGE);
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
			jsonObj.put("onClickWorksList", habitationDetailsList);
			return jsonObj.toString();
		}catch(Exception e){
			LOG.error("Error occured at getOnclickTargetsAcheievementsDetails() in HabitationDetailsService class",e);
			jsonObj.remove("onClickWorksList");
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jsonObj.put(IConstants.ERROR_MESSAGE, e.getMessage());
			return jsonObj.toString();
			
		}
		
	}
	/**
	 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
	 * @return : LocationVO  
	 * @Description : This service is used to get Habitation Details based on status and location selection
	 * @author : Santosh Kumar Verma
	 * @since 29-JUNE-2017
	 */	
	@Override
	public List<HabitationDetailsVO> getHabitationDetailsByStatusByLocationType(InputVO inputVO) {
		List<HabitationDetailsVO> resultList = new ArrayList<HabitationDetailsVO>(0);
		try {
			LOG.info("Entered into getHabitationDetailsByStatusByLocationType() in HabitationDetailsService class");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}else if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
			} 
			
			List<Object[]> rtrnHabDtsObjLst = rwsMinHabViewDAO.getHabitationDetailsByStatusByLocationType(inputVO);
			if (rtrnHabDtsObjLst != null && rtrnHabDtsObjLst.size() > 0) {
				for (Object[] param : rtrnHabDtsObjLst) {
					HabitationDetailsVO habDtlsVO = getLocationDetails(param);//setting location details
					habDtlsVO.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(param[8]));
					resultList.add(habDtlsVO);
				}
			}
			// Taking OverAll Records first time only for build Pagination in UI
			if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
				inputVO.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinHabViewDAO.getHabitationDetailsByStatusByLocationType(inputVO);
				if (rtrnOverAllDtsObjLst != null && resultList.size() > 0) {
					resultList.get(0).setTotalCount(Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
			if (resultList != null && resultList.size() > 0) {
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}

		} catch (Exception e) {
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			LOG.error("Error occured at getHabitationDetailsByStatusByLocationType() in HabitationDetailsService class",e);
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
		}
		return resultList;
	}
	/**
	 * @param : InputVO inputVo which contain fromDateStr,toDateStr,filterType,filterValue,assetType,startValue,endValue
	 * @return : List<HabitationDetailsVO>
	 * @Description : This service is used to get Asset details based on selection.
	 * @author : Santosh Kumar Verma
	 * @since 29-JUNE-2017
	 */	
	@Override
	public List<HabitationDetailsVO> getAssetDetailsByAssetType(InputVO inputVO) {
		List<HabitationDetailsVO> resultList = new ArrayList<HabitationDetailsVO>(0);
		try {
			LOG.info("Entered into getAssetDetailsByAssetType() in HabitationDetailsService class");

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
			
			List<Object[]> assetDtlsObjList = rwsMinAssetViewDAO.getAssestDetailsByAssetType(inputVO);
			if (assetDtlsObjList != null && assetDtlsObjList.size() > 0) {
				for (Object[] param : assetDtlsObjList) {
					HabitationDetailsVO assestVO = getLocationDetails(param); //setting location details
					assestVO.setAssestCode(commonMethodsUtilService.getStringValueForObject(param[8]));
					assestVO.setAssestName(commonMethodsUtilService.getStringValueForObject(param[9]));
					assestVO.setAssestType(commonMethodsUtilService.getStringValueForObject(param[10]));
					assestVO.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(param[11]));
					assestVO.setAssestCost(commonMethodsUtilService.getStringValueForObject(param[12]));
					resultList.add(assestVO);
				}
			}
			// Taking OverAll Records first time only for build Pagination in UI
			if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
				inputVO.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinAssetViewDAO.getAssestDetailsByAssetType(inputVO);
				if (rtrnOverAllDtsObjLst != null && resultList.size() > 0) {
					resultList.get(0).setTotalCount(Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
			if(resultList.size()>0){
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
		} catch (Exception e) {
			LOG.error("Error occured at getAssetDetailsByAssetType() in HabitationDetailsService class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
		}
		return resultList;
	}
	/**
	 * @param : InputVO inputVo which contain fromDateStr,toDateStr,filterType,filterValue,assetTypeList,startValue,endValue
	 * @return : List<HabitationDetailsVO>  
	 * @Description : This service is used to get scheme details 
	 * @author : Santosh Kumar Verma
	 * @since 29-JUNE-2017
	 */
	@Override
	public List<HabitationDetailsVO> getSchemeDetailsByTypeOfAssestName(InputVO inputVo) {
		List<HabitationDetailsVO> resultList = new ArrayList<HabitationDetailsVO>(0);
		try {

			LOG.info("Entered into getSchemeDetailsByTypeOfAssestName() in HabitationDetailsService class");

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
			List<Object[]> schemDtlsObjLst = rwsMinWorksAdminViewDAO.getSchemeDetailsByTypeOfAssestName(inputVo);
			if (schemDtlsObjLst != null && schemDtlsObjLst.size() > 0) {
				for (Object[] param : schemDtlsObjLst) {
					HabitationDetailsVO habitationVO  = getLocationDetails(param); //setting location details
					habitationVO.setWorkId(commonMethodsUtilService.getStringValueForObject(param[8]));
					habitationVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[9]));
					habitationVO.setAssestType(commonMethodsUtilService.getStringValueForObject(param[10]));
					resultList.add(habitationVO);
				}
			}
			// Taking OverAll Records first time only for build Pagination in UI
			if (inputVo.getStartValue() != null && inputVo.getStartValue() == 0) {
				inputVo.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinWorksAdminViewDAO.getSchemeDetailsByTypeOfAssestName(inputVo);
				if (rtrnOverAllDtsObjLst != null && resultList.size() > 0) {
					resultList.get(0).setTotalCount(Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
						
			if(resultList.size()>0){
			  resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}
			return resultList;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeDetailsByTypeOfAssestName() in HabitationDetailsService class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();			
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			resultList.add(statusVO);
			return resultList;
		}
	}
	
	 public HabitationDetailsVO getLocationDetails(Object[] param){
		 HabitationDetailsVO locationVO = new HabitationDetailsVO();
		 try{
			 if(param != null && param.length > 0){
				 locationVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
				 locationVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 locationVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
				 locationVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
				 locationVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
				 locationVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
				 locationVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
				 locationVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
			 }
		 }catch(Exception e){
			 LOG.error("Error occured at setLocationDetails() in HabitationDetailsService class",e);
		 }
		 return locationVO;
	 }
	 /**
		 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
		 * @return : String  
		 * @Description : This service is used to get Habitation Details based on status and location selection
		 * @author : sanjay kumar
		 * @since 29-JUNE-2017
		 */	
	@Override
	public String getOnclickHabitationsupplyDetails(InputVO inputVO) {
		
		jsonObj = new JSONObject();
		try {
			LOG.info("Entered into getOnclickHabitationsupplyDetails() in HabitationDetailsService class");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
			}else if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().trim().isEmpty() && inputVO.getToDateStr() != null && !inputVO.getToDateStr().trim().isEmpty()){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}
			
			if(inputVO.getType()!=null && inputVO.getType().trim().length()>0 && inputVO.getStartValue()!= null && inputVO.getEndValue()!= null ){
				List<Object[]> object=rwsMinHabViewDAO.getOnclickHabitationsupplyDetails(inputVO);
				
				
				List<HabitationDetailsVO> habitationDetailsList = new ArrayList<HabitationDetailsVO>(0);
				if(object != null){
					for(Object[] param : object){
						HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
						habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
						habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
						habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
						habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
						habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
						habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
						habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
						habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
						habitationDetailsVO.setLpcd((BigDecimal) (param[8] != null ? param[8] : 0));
						
						habitationDetailsList.add(habitationDetailsVO);
					}
				}
				// Taking OverAll Records first time only for build Pagination in UI
				if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
					inputVO.setEndValue(0);
					List<Object[]> rtrnOverAllDtsObjLst = rwsMinHabViewDAO.getOnclickHabitationsupplyDetails(inputVO);
					if (rtrnOverAllDtsObjLst != null && habitationDetailsList.size() > 0) {
						jsonObj.put("totalCount",Long.valueOf(rtrnOverAllDtsObjLst.size()));
					}
				}
				
				jsonObj.remove(IConstants.ERROR_MESSAGE);
				jsonObj.put(IConstants.STATUS, IConstants.RESULT_SUCCESS);
				jsonObj.put("onClickWorksList", habitationDetailsList);
				
			}else{
				if(inputVO.getType() ==null){
					throw new Exception("Type is Mandatory");
				}if (inputVO.getStartValue()== null){
					 throw new Exception("startValue is Mandatory");
				}if(inputVO.getEndValue()== null){
					 throw new Exception("endValue is Mandatory");
				}
			}
			return jsonObj.toString();
		}catch(Exception e){
			LOG.error("Error occured at getOnclickHabitationsupplyDetails() in HabitationDetailsService class",e);
			jsonObj.remove("onClickWorksList");
			jsonObj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jsonObj.put(IConstants.ERROR_MESSAGE, e.getMessage());
			return jsonObj.toString();
			
		}
	}

	/**
	 * @Description : This service is used to get Location wise water source.
	 * @author : Swadhin K Lenka
	 * @since 29-JUNE-2017
	 */
	@Override
	public List<HabitationDetailsVO> getWaterSourceDeatilsLocationWise(InputVO inputVo) {
		List<HabitationDetailsVO> finalVo = new ArrayList<HabitationDetailsVO>(0);
		List<Object[]> surfaceSourcesObjArr = null;
		List<Object[]> groundWaterDetails = new ArrayList<Object[]>();
		try{
			
			LOG.info("Entered into getWaterSourceDeatilsLocationWise() in HabitationDetailsService class");

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
			if(inputVo.getStatus() != null && inputVo.getStatus().trim().equalsIgnoreCase("ground") || inputVo.getStatus() != null && inputVo.getStatus().trim().equalsIgnoreCase("total")){
				List<Object[]> handpumpsObjArr = rwsMinHandpumpsViewDAO.getWaterSourceDeatilsGroupByLocation(inputVo);
				List<Object[]> shallowHandpumpObjArr = rwsMinShallowHandpumpsViewDAO.getWaterSourceDeatilsGroupByLocation(inputVo);
				List<Object[]> subSurfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatilsGroupByLocation(inputVo,"SUBSURFACE SOURCE");

				groundWaterDetails.addAll(handpumpsObjArr);
				groundWaterDetails.addAll(subSurfaceSourcesObjArr);
				groundWaterDetails.addAll(shallowHandpumpObjArr);
				
				for (Object[] param : groundWaterDetails) {
					if(param!=null){
						HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
						habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
						habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
						habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
						habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
						habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
						habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
						habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
						habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
						habitationDetailsVO.setAssestCode(commonMethodsUtilService.getStringValueForObject(param[9]));
						//habitationDetailsVO.set(commonMethodsUtilService.getStringValueForObject(param[9]));
						finalVo.add(habitationDetailsVO);
					}
					
				}
				
			}if(inputVo.getStatus() != null && inputVo.getStatus().trim().equalsIgnoreCase("surface") || inputVo.getStatus() != null && inputVo.getStatus().trim().equalsIgnoreCase("total")){
				 surfaceSourcesObjArr = rwsMinSchemeSourcesViewDAO.getWaterSourceDeatilsGroupByLocation(inputVo,"SURFACE SOURCE");
					for (Object[] param : surfaceSourcesObjArr) {
						if(param!=null){
							HabitationDetailsVO habitationDetailsVO = new HabitationDetailsVO();
							habitationDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[0]));
							habitationDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
							habitationDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[2]));
							habitationDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
							habitationDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[4]));
							habitationDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[5]));
							habitationDetailsVO.setHabitationCode(commonMethodsUtilService.getStringValueForObject(param[6]));
							habitationDetailsVO.setHabitationName(commonMethodsUtilService.getStringValueForObject(param[7]));
							habitationDetailsVO.setAssestCode(commonMethodsUtilService.getStringValueForObject(param[9]));
							//habitationDetailsVO.set(commonMethodsUtilService.getStringValueForObject(param[9]));
							finalVo.add(habitationDetailsVO);
						}
						
					}
			}
			if(finalVo.size()>0){
				finalVo.get(0).setStatus(IConstants.RESULT_SUCCESS);
				}
			return finalVo;
		}catch(Exception e){
			LOG.error("Error occured at getWaterSourceDeatilsLocationWise() in HabitationDetailsService class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			finalVo.add(statusVO);
			return finalVo;
		}
	}
	
	/**
	 * @param : InputVO inputVo which contain year,filterType,filterValue,statusList,startValue,endValue
	 * @return : LocationVO  
	 * @Description : This service is used to get Stressed Habitation Details based on status and location selection
	 * @author : Santosh Kumar Verma
	 * @since 30-JUNE-2017
	 */	
	@Override
	public List<HabitationDetailsVO> getStressedHabitationDetailsByStatusByLocationType(InputVO inputVO) {
		List<HabitationDetailsVO> resultList = new ArrayList<HabitationDetailsVO>(0);
		try {
			LOG.info("Entered into getStressedHabitationDetailsByStatusByLocationType() in HabitationDetailsService class");

			if (inputVO != null && inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
				inputVO.setYear(inputVO.getYear().substring(inputVO.getYear().length() - 2));// taking last 2 digit from year
			}
			List<Object[]> rtrnHabDtsObjLst = rwsMinHabViewDAO.getStressedHabitationDetailsByStatusByLocationType(inputVO);
			if (rtrnHabDtsObjLst != null && rtrnHabDtsObjLst.size() > 0) {
				for (Object[] param : rtrnHabDtsObjLst) {
					HabitationDetailsVO habDtlsVO = getLocationDetails(param);//setting location details
					habDtlsVO.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(param[8]));
					resultList.add(habDtlsVO);
				}
			}
			// Taking OverAll Records first time only for build Pagination in UI
			if (inputVO.getStartValue() != null && inputVO.getStartValue() == 0) {
				inputVO.setEndValue(0);
				List<Object[]> rtrnOverAllDtsObjLst = rwsMinHabViewDAO.getStressedHabitationDetailsByStatusByLocationType(inputVO);
				if (rtrnOverAllDtsObjLst != null && resultList.size() > 0) {
					resultList.get(0).setTotalCount(Long.valueOf(rtrnOverAllDtsObjLst.size()));
				}
			}
			if (resultList != null && resultList.size() > 0) {
				resultList.get(0).setStatus(IConstants.RESULT_SUCCESS);
			}

		} catch (Exception e) {
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			LOG.error("Error occured at getStressedHabitationDetailsByStatusByLocationType() in HabitationDetailsService class",e);
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
		}
		return resultList;
	}


}
