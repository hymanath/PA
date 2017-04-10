package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertSubTaskStatusDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLevelDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;

import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskTrackingDAO;
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertDepartmentCommentNew;
import com.itgrids.partyanalyst.model.AlertDepartmentDocumentNew;
import com.itgrids.partyanalyst.model.CustomReport;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

public class AlertManagementSystemService extends AlertService implements IAlertManagementSystemService{

	private final static Logger LOG =  Logger.getLogger(AlertManagementSystemService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IAlertDAO alertDAO;
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO;
	private IAlertDepartmentStatusDAO alertDepartmentStatusDAO;
	private IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO;
	private DateUtilService dateUtilService;
	private IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO;
	private IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO;
	private IGovtDepartmentDesignationOfficerNewDAO govtDepartmentDesignationOfficerNewDAO;
	private IGovtAlertSubTaskDAO govtAlertSubTaskDAO;
	
	private TransactionTemplate transactionTemplate = null;
	private IGovtDepartmentScopeLevelDAO govtDepartmentScopeLevelDAO;
	private IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO;
	private IGovtOfficerSubTaskTrackingDAO govtOfficerSubTaskTrackingDAO;
	private IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO;
	private IAlertSubTaskStatusDAO alertSubTaskStatusDAO;
	private IGovtDepartmentScopeDAO govtDepartmentScopeDAO;
	private IGovtDepartmentDAO govtDepartmentDAO;
	private ActivityService activityService;
	private IAlertDepartmentDocumentNewDAO alertDepartmentDocumentNewDAO;

	

	public IAlertDepartmentDocumentNewDAO getAlertDepartmentDocumentNewDAO() {
		return alertDepartmentDocumentNewDAO;
	}

	public void setAlertDepartmentDocumentNewDAO(IAlertDepartmentDocumentNewDAO alertDepartmentDocumentNewDAO) {
		this.alertDepartmentDocumentNewDAO = alertDepartmentDocumentNewDAO;
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public IGovtDepartmentDAO getGovtDepartmentDAO() {
		return govtDepartmentDAO;
	}

	public void setGovtDepartmentDAO(IGovtDepartmentDAO govtDepartmentDAO) {
		this.govtDepartmentDAO = govtDepartmentDAO;
	}
	public IGovtDepartmentScopeDAO getGovtDepartmentScopeDAO() {
		return govtDepartmentScopeDAO;
	}

	public void setGovtDepartmentScopeDAO(
			IGovtDepartmentScopeDAO govtDepartmentScopeDAO) {
		this.govtDepartmentScopeDAO = govtDepartmentScopeDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public IGovtAlertDepartmentLocationNewDAO getGovtAlertDepartmentLocationNewDAO() {
		return govtAlertDepartmentLocationNewDAO;
	}

	public IAlertDepartmentStatusDAO getAlertDepartmentStatusDAO() {
		return alertDepartmentStatusDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public IGovtDepartmentScopeLevelDAO getGovtDepartmentScopeLevelDAO() {
		return govtDepartmentScopeLevelDAO;
	}

	public IGovtDepartmentWorkLocationDAO getGovtDepartmentWorkLocationDAO() {
		return govtDepartmentWorkLocationDAO;
	}
	public void setGovtDepartmentWorkLocationRelationDAO(
			IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO) {
		this.govtDepartmentWorkLocationRelationDAO = govtDepartmentWorkLocationRelationDAO;
	}

	public void setGovtOfficerSubTaskTrackingDAO(
			IGovtOfficerSubTaskTrackingDAO govtOfficerSubTaskTrackingDAO) {
		this.govtOfficerSubTaskTrackingDAO = govtOfficerSubTaskTrackingDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setGovtDepartmentScopeLevelDAO(
			IGovtDepartmentScopeLevelDAO govtDepartmentScopeLevelDAO) {
		this.govtDepartmentScopeLevelDAO = govtDepartmentScopeLevelDAO;
	}

	public void setGovtDepartmentWorkLocationDAO(
			IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO) {
		this.govtDepartmentWorkLocationDAO = govtDepartmentWorkLocationDAO;
	}

	public IGovtAlertSubTaskDAO getGovtAlertSubTaskDAO() {
		return govtAlertSubTaskDAO;
	}

	public void setGovtAlertSubTaskDAO(IGovtAlertSubTaskDAO govtAlertSubTaskDAO) {
		this.govtAlertSubTaskDAO = govtAlertSubTaskDAO;
	}

	public IAlertAssignedOfficerNewDAO getAlertAssignedOfficerNewDAO() {
		return alertAssignedOfficerNewDAO;
	}


	public IGovtDepartmentDesignationOfficerDetailsNewDAO getGovtDepartmentDesignationOfficerDetailsNewDAO() {
		return govtDepartmentDesignationOfficerDetailsNewDAO;
	}

	public void setGovtDepartmentDesignationOfficerDetailsNewDAO(
			IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO) {
		this.govtDepartmentDesignationOfficerDetailsNewDAO = govtDepartmentDesignationOfficerDetailsNewDAO;
	}

	
	public IGovtDepartmentDesignationOfficerNewDAO getGovtDepartmentDesignationOfficerNewDAO() {
		return govtDepartmentDesignationOfficerNewDAO;
	}

	public void setGovtDepartmentDesignationOfficerNewDAO(
			IGovtDepartmentDesignationOfficerNewDAO govtDepartmentDesignationOfficerNewDAO) {
		this.govtDepartmentDesignationOfficerNewDAO = govtDepartmentDesignationOfficerNewDAO;
	}
	
	
	public IAlertAssignedOfficerTrackingNewDAO getAlertAssignedOfficerTrackingNewDAO() {
		return alertAssignedOfficerTrackingNewDAO;
	}
	public void setAlertAssignedOfficerTrackingNewDAO(
			IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO) {
		this.alertAssignedOfficerTrackingNewDAO = alertAssignedOfficerTrackingNewDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	public void setAlertAssignedOfficerNewDAO(
			IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO) {
		this.alertAssignedOfficerNewDAO = alertAssignedOfficerNewDAO;
	}
	public void setGovtAlertDepartmentLocationNewDAO(
			IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO) {
		this.govtAlertDepartmentLocationNewDAO = govtAlertDepartmentLocationNewDAO;
	}
	public void setAlertDepartmentStatusDAO(
			IAlertDepartmentStatusDAO alertDepartmentStatusDAO) {
		this.alertDepartmentStatusDAO = alertDepartmentStatusDAO;
	}
	public IAlertDepartmentCommentNewDAO getAlertDepartmentCommentNewDAO() {
		return alertDepartmentCommentNewDAO;
	}
	public void setAlertDepartmentCommentNewDAO(IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO) {
		this.alertDepartmentCommentNewDAO = alertDepartmentCommentNewDAO;
	}
	
	//Business Method
	/*
	 * Satnosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStatusWiseAlertOverviewcnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long)
	 */
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
		LOG.info("Entered in getStatusWiseAlertOverviewcnt() method of AlertManagementSystemService{}");
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> totalList = new ArrayList<Object[]>();
			List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status");//for pending status
			if(alertCountList != null && alertCountList.size() > 0){
				totalList.addAll(alertCountList);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null);
			if(alertCountList2 != null && alertCountList2.size() > 0){
				totalList.addAll(alertCountList2);
			}
			setAlertCount(totalList,finalAlertVOs); 
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusWiseAlertOverviewcnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public void setAlertCount(List<Object[]> objList,List<AlertVO> finalAlertVOs){
	    try{
	    	if(objList != null && objList.size() > 0){         
				Long totalAlertCnt = 0l;
				for(Object[] param : objList){
						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[3]);	 
				 }
				 for(Object[] param : objList){
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					 AlertVO VO = getMatchVO1(finalAlertVOs,id);
					 if(VO == null){
						 VO = new AlertVO();
						 VO.setId(id);
						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2])); 
						 VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
						 finalAlertVOs.add(VO); 
					 }else{
					 	  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[3]));
					 }
				}
				//Calculating Percentage
				calculatePercentage(finalAlertVOs,totalAlertCnt);
			}
	    }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setStatusWiseAlertCnt() method of CccDashboardService{}");
	    }
	}
	public void calculatePercentage(List<AlertVO> resuList,Long totalAlertCnt){
		try{
			if(resuList != null && resuList.size() > 0){
				for(AlertVO VO:resuList){
					VO.setPercentage(calculatePercantage(VO.getAlertCnt(), totalAlertCnt));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calculatePercentage() method of CccDashboardService{}");
	    }
	}
	public AlertVO getMatchVO1(List<AlertVO> finalAlertVOs,Long id){
		try{
			if(finalAlertVOs == null || finalAlertVOs.size() ==0)
				return null;
			for(AlertVO VO:finalAlertVOs){
				if(VO.getId().equals(id))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMatchVO() method of CccDashboardService{}");
		}
		return null;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getLevelWiseAlertOverviewCnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long)
	 */
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
		LOG.info("Entered in getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			//get all the alert status and build the template
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",null,null);
			setAlertCount(rtrnObjLst,finalAlertVOs);
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	/*
	 * Santosh(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentStatus()
	 */
	public List<AlertVO> getDepartmentStatus(){	
		List<AlertVO> finalList = new ArrayList<AlertVO>(); 
		try {
			AlertVO VO = new AlertVO();
			VO.setId(1l);
			VO.setName("Pending");
			finalList.add(VO);
			List<Object[]> statusList = alertAssignedOfficerNewDAO.getAssignedStatuses();
			setRequiredData(statusList,finalList);
		} catch (Exception e) {
			LOG.error("Error occured getDepartmentStatus() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	/*
	 * Satnosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentScope()
	 */
	public List<AlertVO> getDepartmentScope(){	
		List<AlertVO> finalList = new ArrayList<AlertVO>(); 
		try {
			List<Object[]> scopeObjList = alertAssignedOfficerNewDAO.getDepartmentScope();
			setRequiredData(scopeObjList,finalList);
		  } catch (Exception e) {
			  LOG.error("Error occured getDepartmentStatus() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	public void setRequiredData(List<Object[]> objList,List<AlertVO> resultList){
		try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertVO VO = new AlertVO();
					   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   resultList.add(VO);
				 }
			 }
		}catch(Exception e){
			LOG.error("Error occured setRequiredData() method of AlertManagementSystemService{}");
		}
  }
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentWiseAlertOverviewCnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.util.List, java.lang.String)
	 */
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType){
		LOG.info("Entered in getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			
			List<Object[]> totalList = new ArrayList<Object[]>();
			if(resultType != null && resultType.equalsIgnoreCase("Status") && (alertStatusIds.size() == 0l || alertStatusIds.contains(1l))){
				List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Department");//for pending status getting department wise status
				if(alertCountList != null && alertCountList.size() > 0){
					totalList.addAll(alertCountList);
				}
			}
			//get all the alert status and build the template
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			if(resultType != null && resultType.equalsIgnoreCase("Status") && (alertStatusIds.size() == 0l || !alertStatusIds.contains(1l))){
				List<Object[]> rtrnObjLst  = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds);
				if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					totalList.addAll(rtrnObjLst);
				}
			}
			if(resultType != null && resultType.equalsIgnoreCase("Department")){
				List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds);
				if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					totalList.addAll(rtrnObjLst);
				}
			}
			setAlertCount(totalList,finalAlertVOs);
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	
	//sandeep
		public ResultStatus updateComment(Long alertId,String comment,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				
				AlertDepartmentCommentNew adcn = new AlertDepartmentCommentNew();
				adcn.setComment(comment);
				adcn.setInsertedBy(userId);
				adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				adcn = alertDepartmentCommentNewDAO.save(adcn);

				List<AlertAssignedOfficerNew> aaonList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
				if(aaonList != null && aaonList.size() > 0){
					AlertAssignedOfficerNew aaon = aaonList.get(0);
					
					//save record in tracking
					saveRecordIntoTracking(aaon,userId,adcn.getAlertDepartmentCommentId()+"",7l);
				}
				rs.setExceptionMsg("success");
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateComment");
			}
			return rs;
		}

		public ResultStatus updateAlertPriority(Long alertId,Long priorityId,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				Integer count = alertDAO.updateAlertPriority(alertId,priorityId,userId,dateUtilService.getCurrentDateAndTime());
				if(count != null && count > 0){
					//save record in tracking
					saveRecordIntoTracking(alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0),userId,priorityId+"",5l);
					
					rs.setExceptionMsg("success");
				}
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateAlertPriority");
			}
			return rs;
		}
		
		public void saveRecordIntoTracking(AlertAssignedOfficerNew aaon,Long userId,String id,Long actiontype){
			try {
				AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
				aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
				aaotn.setAlertId(aaon.getAlertId());
				aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
				aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
				aaotn.setGovtAlertActionTypeId(actiontype);
				
				if(actiontype == 7l)//comment
					aaotn.setAlertDepartmentCommentId(Long.parseLong(id));
				if(actiontype == 5l)
					aaotn.setAlertSeviorityId(Long.parseLong(id));
				if(actiontype == 4l)
					aaotn.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(id));
				if(actiontype == 3l)
					aaotn.setAlertDepartmentDocumentId(Long.parseLong(id));
				
				aaotn.setInsertedBy(userId);
				aaotn.setAlertStatusId(aaon.getAlertStatusId());
				aaotn.setUpdatedBy(userId);
				aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setIsApproved(aaon.getIsApproved());
				alertAssignedOfficerTrackingNewDAO.save(aaotn);
			} catch (Exception e) {
				LOG.error("Exception raised at saveRecordIntoTracking");
			}
			
		}
		
		public ResultStatus updateAlertDueDate(Long alertId ,String date,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				saveRecordIntoTracking(alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0),userId,date,4l);
				rs.setExceptionMsg("success");
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception Occured in AlertManagementSystemService of  updateAlertDueDate() ", e);
			}
			return rs;
		}
		
		public ResultStatus updateAlertStatusComment(Long alertId,Long statusId,String comment,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				Alert alert = alertDAO.get(alertId);
				if(alert != null){
					alert.setAlertStatusId(statusId);
					alert.setUpdatedBy(userId);
					alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alertDAO.save(alert);
				}
				
				AlertDepartmentCommentNew adcn = null;
				if(comment != null && !comment.trim().isEmpty()){
					adcn = new AlertDepartmentCommentNew();
					adcn.setComment(comment);
					adcn.setInsertedBy(userId);
					adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					adcn = alertDepartmentCommentNewDAO.save(adcn);
				}
				
				AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
				aaon.setAlertStatusId(statusId);
				aaon.setUpdatedBy(userId);
				aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				alertAssignedOfficerNewDAO.save(aaon);
				
				AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
				aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
				aaotn.setAlertId(aaon.getAlertId());
				aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
				aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
				aaotn.setGovtAlertActionTypeId(6l);
				aaotn.setAlertStatusId(statusId);
				
				if(adcn != null)
					aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
				
				aaotn.setInsertedBy(userId);
				aaotn.setAlertStatusId(aaon.getAlertStatusId());
				aaotn.setUpdatedBy(userId);
				aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setIsApproved(aaon.getIsApproved());
				alertAssignedOfficerTrackingNewDAO.save(aaotn);
				rs.setExceptionMsg("success");
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception Occured in updateAlertStatusComment of  updateAlertDueDate() ", e);
			}
			return rs;
		}
		
		public ResultStatus uploadDocumentsForAlert(final Map<File, String> mapfiles,final Long alertId,final Long userId){

			final ResultStatus resultStatus = new ResultStatus();
			try {
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
			String folderName = folderCreationForAlertsAttachments();
			CustomReportFile customReportFile = null;
			CustomReport customReport = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			// int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			
			 StringBuilder pathBuilder = new StringBuilder();
			 StringBuilder str ;
			 
			 if(mapfiles != null && mapfiles.size() > 0){
				 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
				 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
					 str = new StringBuilder();
					 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
					 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
						
					 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
					 .append(entry.getValue());
					 str.append(randomNumber).append(".").append(entry.getValue());
					String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
					 
						if(fileCpyStts.equalsIgnoreCase("error")){
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							LOG.error(" Exception Raise in copying file");
							throw new ArithmeticException();
						}
						
						AlertDepartmentDocumentNew addn = new AlertDepartmentDocumentNew();
						addn.setDocument(pathBuilder.toString());
						addn.setInsertedBy(userId);
						addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						addn = alertDepartmentDocumentNewDAO.save(addn);
						
						//save record in tracking
						saveRecordIntoTracking(aaon,userId,addn.getAlertDepartmentDocumentId()+"",3l);
						
				 }
			 }
			
			 resultStatus.setResultCode(0);
			 resultStatus.setResultState(customReportFile.getCustomReportFileId());
			 resultStatus.setMessage("success");
					}
				});
			}catch (Exception e) {
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("failure");
				LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
			}
			return resultStatus;
		
		}
		
		public List<AlertTrackingVO> viewAlertHistory(Long alertId,Long userId){
			List<AlertTrackingVO> finalList = new ArrayList<AlertTrackingVO>(0);
			SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			SimpleDateFormat dateSdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
			try {
				
				List<AlertAssignedOfficerTrackingNew> qryRstList = alertAssignedOfficerTrackingNewDAO.getAlertHistory(alertId);
				
				if(qryRstList != null && qryRstList.size() > 0){
					
					AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
					
					for (AlertAssignedOfficerTrackingNew alertAssignedOfficerTrackingNew : qryRstList) {
						
						String userName = alertAssignedOfficerTrackingNew.getGovtOfficer().getOfficerName()+" - "+alertAssignedOfficerTrackingNew.getGovtOfficer().getMobileNo();
						String designation = alertAssignedOfficerTrackingNew.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName()
								+" & "+alertAssignedOfficerTrackingNew.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName();
						
						AlertTrackingVO matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						if(matchedDateVO == null){
							matchedDateVO = new AlertTrackingVO();
							matchedDateVO.setDate(dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
							finalList.add(matchedDateVO);
						}
						
						matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						AlertTrackingVO matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						if(matchedTimeVO == null){
							matchedTimeVO = new AlertTrackingVO();
							matchedTimeVO.setDate(timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
							matchedDateVO.getTimeList().add(matchedTimeVO);
						}
						
						matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() > 0l && alertAssignedOfficerTrackingNew.getAlertDepartmentDocument() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentDocument().getDocument());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							matchedTimeVO.getAttachementsList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() > 0l && alertAssignedOfficerTrackingNew.getAlertDepartmentComment() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentComment().getComment());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							matchedTimeVO.getCommentList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getDueDate() != null && !alertAssignedOfficerTrackingNew.getDueDate().toString().trim().isEmpty()){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getDueDate().toString());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							matchedTimeVO.getDueDateList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertStatusId() != null && alertAssignedOfficerTrackingNew.getAlertStatusId() > 0l && alertAssignedOfficerTrackingNew.getAlertStatus() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertStatus().getAlertStatus());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							matchedTimeVO.getStatusList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertSeviorityId() != null && alertAssignedOfficerTrackingNew.getAlertSeviorityId() > 0l && alertAssignedOfficerTrackingNew.getAlertSeviority() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertSeviority().getSeverity());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							matchedTimeVO.getPriorityList().add(vo);
						}
						
					}
				}
				
			} catch (Exception e) {
				LOG.error(" Exception Occured in viewAlertHistory() method, Exception - ",e);
			}
			return finalList;
		}
		
		public AlertTrackingVO getMatchedDateVO(List<AlertTrackingVO> voList,String str){
			if(voList != null && voList.size() > 0){
				for (AlertTrackingVO alertTrackingVO : voList) {
					if(alertTrackingVO.getDate().equalsIgnoreCase(str))
						return alertTrackingVO;
				}
			}
			return null;
		}
		
		//sandeep
		
		/*
		 * Hymavathi(non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDistrictOfficerAlertsCountView(java.lang.Long)
		 */
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId){
		
		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
		try{
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
			
			Long govtDepDesigOffcrId = 0l;
			Long govtOffcrId = 0l;
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for( Object[]  obj :list1){
					govtDepDesigOffcrId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					govtOffcrId = commonMethodsUtilService.getLongValueForObject(obj[1]);
					returnVO.setGovtDeptDesigOffcrId(govtDepDesigOffcrId);
					returnVO.setGovtOfficerId(govtOffcrId);
					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
					
				}
			}
			
			/*List<Object[]> desigDeptIds = govtDepartmentDesignationOfficerNewDAO.getDeptAndDesignationNames(govtDepDesigOffcrId);
			
			if(commonMethodsUtilService.isListOrSetValid(desigDeptIds)){
				for( Object[]  obj :desigDeptIds){
					
					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[0]));//designationId
					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[1]));//designationName
					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[2]));//depId
					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[3]));//deptName
					
				}
			}*/
			List<Object[]> myAlertsTodayList = null;
			List<Object[]> myAlertsOverAllList = null;
			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0l && govtOffcrId != null && govtOffcrId.longValue() > 0l){
				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrId,govtOffcrId,"today");
				myAlertsOverAllList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrId,govtOffcrId,"overAll");
			}
			
			List<Long> todayAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrId,govtOffcrId,"today");
			List<Long> overAllAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrId,govtOffcrId,"overAll");
			// My alerts Status wise count
			
			setStatusWiseCount( myAlertsOverAllList, returnVO,"myAlerts",Long.valueOf(myAlertsTodayList.size()),todayAlertIds,overAllAlertIds);
			
			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0l && govtOffcrId != null && govtOffcrId.longValue() > 0l){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"today","mySubTasks");
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"overAll","mySubTasks");
			}
			
			List<Long> todayMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrId,govtOffcrId,"today","mySubTasks");
			List<Long> overAllMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrId,govtOffcrId,"overAll","mySubTasks");
			// My SubTasks Status wise count
			setStatusWiseCount( myAlertsOverAllList, returnVO,"mySubTasks",Long.valueOf(myAlertsTodayList.size()),todayMySubTaskAlertIds,overAllMySubTaskAlertIds);
			
			
			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0l && govtOffcrId != null && govtOffcrId.longValue() > 0l){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"today","myAssignedSubTasks");
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"overAll","myAssignedSubTasks");
			}
			
			List<Long> todayMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrId,govtOffcrId,"today","myAssignedSubTasks");
			List<Long> overAllMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrId,govtOffcrId,"overAll","myAssignedSubTasks");
			// My Assigned SubTasks Status wise count
			setStatusWiseCount( myAlertsOverAllList, returnVO,"myAssignedSubTasks",Long.valueOf(myAlertsTodayList.size()),todayMyAssSubTaskAlertIds,overAllMyAssSubTaskAlertIds);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in AlertManagementSystemService of  getDistrictOfficerAlertsCountView() ", e);
		}
		return returnVO;
	}
	
	public void setStatusWiseCount(List<Object[]> myAlertsOverAllList,DistrictOfficeViewAlertVO returnVO,String type,Long todayCount,
			List<Long> todayAlertIds,List<Long> overAllAlertId){
		try{
			Map<Long,DistrictOfficeViewAlertVO> myAlertsStatusMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
			
			if(commonMethodsUtilService.isListOrSetValid(myAlertsOverAllList)){
				for (Object[] objects : myAlertsOverAllList) {
					DistrictOfficeViewAlertVO vo = myAlertsStatusMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(vo == null){
						vo = new DistrictOfficeViewAlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[3]));
						myAlertsStatusMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), vo);
					}
					vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[2]));
				}
			}
			Long myAlertsOverAllCnt = 0l;
			if(commonMethodsUtilService.isMapValid(myAlertsStatusMap)){
				for (Map.Entry<Long,DistrictOfficeViewAlertVO> entry : myAlertsStatusMap.entrySet()) {
					DistrictOfficeViewAlertVO vo = entry.getValue();
					if(vo != null){
						myAlertsOverAllCnt = myAlertsOverAllCnt+vo.getCount();
						if(type != null && type.equalsIgnoreCase("myAlerts")){
							returnVO.getList1().add(vo);
							returnVO.getList1().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
							returnVO.getList2().add(vo);
							returnVO.getList2().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
							returnVO.getList3().add(vo);
							returnVO.getList3().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}
					}
				}
			}
			
			if(type != null && type.equalsIgnoreCase("myAlerts")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList1())){
					returnVO.getList1().get(0).getTodayAlertIds().addAll(todayAlertIds);
					returnVO.getList1().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList1().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList1()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList2())){
					returnVO.getList2().get(0).getTodayAlertIds().addAll(todayAlertIds);
					returnVO.getList2().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList2().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList2()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList3())){
					returnVO.getList3().get(0).setTodayCount(todayCount);
					returnVO.getList3().get(0).getTodayAlertIds().addAll(todayAlertIds);
					returnVO.getList3().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList3()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in AlertManagementSystemService of  getDistrictOfficerAlertsCountView() ", e);
		}
	}
	/*
	 * Santosh(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDeptListForUser(java.lang.Long)
	 */
	public List<IdAndNameVO> getDeptListForUser(Long userId){   
		try{
			List<IdAndNameVO> list = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO = null;
			List<Object[]> deptList = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			if(deptList != null && deptList.size() > 0){  
				for(Object[] param : deptList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					list.add(idAndNameVO);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getDeptListForUser() method of AlertManagementSystemService",e);
		}
		return null;
	}
	

	public String assigningAlertToOfficer(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					
					//Alert Status Updation
					Alert alert = alertDAO.get(inputvo.getAlertId());
					alert.setAlertStatusId(2l);
					alert.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alert.setUpdatedBy(inputvo.getUserId());
					alert = alertDAO.save(alert);
					
					//Get Department Designation Officer Ids
					Long desigOfficerId = null;
					List<Long> designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationOfficerIdsNew(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId(),
							inputvo.getGovtOfficerId());
					if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
						desigOfficerId = designationOfficerIds.get(0);
					
					//Officer Assigning
					AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
					alertAssignedOfficer.setAlertId(inputvo.getAlertId());
					alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficer.setGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
					alertAssignedOfficer.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficer.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setAlertStatusId(2l);
					alertAssignedOfficer.setIsDeleted("N");
					alertAssignedOfficer.setIsApproved("Y");
					alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);
					
					//Officer Assigning Tracking
					AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(inputvo.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficerTracking.setGovtOfficerId(inputvo.getGovtOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setAlertStatusId(2l);
					alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
					alertAssignedOfficerTracking.setIsApproved("Y");
					alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
					
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
					
					
					return "success";
				}
			});
		} catch (Exception e) {
			LOG.error("Error occured assigningAlertToOfficer() method of AlertManagementSystemService",e);
		}
		return status;
	}
	
	public List<IdNameVO> getDepartmentLevels(Long departmentId){
		List<IdNameVO> resultList = new ArrayList<IdNameVO>();
		try {						
			List<Object[]> levelObj = govtDepartmentScopeLevelDAO.getDepartmentLevels(departmentId);
			if(levelObj != null && levelObj.size()>0){
				for (Object[] param : levelObj) {
					IdNameVO VO = new IdNameVO();
					   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   resultList.add(VO);
				}				 
			}			
			
		} catch (Exception e) {
			LOG.error("Error occured getDepartmentLevels(Long departmentId) method of AlertManagementSystemService",e);
		}
		return resultList;
	}
	
	public List<IdNameVO> getParentLevelsOfLevel(Long departmentId,Long levelId){
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		
		try {
			
			Map<Long,IdNameVO> levelMap = new HashMap<Long, IdNameVO>();
			
			
			List<Object[]> subLevelObj = govtDepartmentScopeLevelDAO.getParentLevelsOfLevel(departmentId,levelId);
			
			if(subLevelObj !=null && subLevelObj.size()>0){
				Set<Long> subLevelIds = new HashSet<Long>();
				for (Object[] param : subLevelObj) {					
					//if(param[0] !=null && !(param[0].equals(levelId))){	
						
						IdNameVO vo = new IdNameVO();						
						vo.setId((Long)param[0]);
						vo.setName(param[1] !=null ? param[1].toString():"");	
						
						//levelList.add(vo);
						levelMap.put((Long)param[0], vo);
						
						subLevelIds.add((Long)param[0]);
					//}
				}
				
				//0.levelId,1.workLocationId,2.LocationName
				List<Object[]> objList = govtDepartmentWorkLocationDAO.getLevelWiseInfo(departmentId,subLevelIds);
				
				setLocationValuesToMap(objList,levelMap);
				
				if(levelMap !=null && levelMap.size()>0){
					finalList.addAll(levelMap.values());
				}
				
			}
			
			
		} catch (Exception e) {
			LOG.error("Error occured getParentLevelsOfLevel(Long departmentId,Long levelId) method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	
	public void setLocationValuesToMap(List<Object[]> objList,Map<Long,IdNameVO> levelMap){
		try {
			
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {
					
					if(obj[0] !=null){
						if((Long)obj[0] == 1l){
							
							IdNameVO mainVo = levelMap.get(obj[0] !=null ? (Long)obj[0]:0l);
							if(mainVo == null){
								mainVo = new IdNameVO();
								
								mainVo.setId((Long)obj[0]);
								levelMap.put((Long)obj[0], mainVo);
							}
							
							if(obj[1] !=null){
								IdNameVO subVo = new IdNameVO();
								
								subVo.setId((Long)obj[1]);
								subVo.setName(obj[2] !=null ? obj[2].toString():"");
								
								mainVo.getIdnameList().add(subVo);
							}
							
						}
					}
					
					
						
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured setLocationValuesToMap() method of AlertManagementSystemService",e);
		}
	}
	
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId){
		LOG.info("Entered in getTotalAlertByStatus() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getTotalAlertByStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,deptId);
			setAlertDtls(alertCoreDashBoardVOs, alertList); 
			//set Subtask into alert logic 
			List<Long> alertIds = new ArrayList<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					alertIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			//get subtask count.
			List<Object[]> subtaskCountList = null;
			if(alertIds != null && alertIds.size() > 0){
				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIds);
			}
			//create a map from alertId and subtask count.
			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
			if(subtaskCountList != null && subtaskCountList.size() > 0){
				for(Object[] param : subtaskCountList){
					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
					}
				}
			}
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertByStatus() method of AlertManagementSystemService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId){
		LOG.info("Entered in getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> levelValuesList = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Long> alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList,govtDeptScopeId,deptId);
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
				setAlertDtls(alertCoreDashBoardVOs, list); 
			}
			//set Subtask into alert logic 
			//get subtask count.
			List<Object[]> subtaskCountList = null;
			if(alertIdSet != null && alertIdSet.size() > 0){
				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdSet);
			}
			//create a map from alertId and subtask count.
			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
			if(subtaskCountList != null && subtaskCountList.size() > 0){
				for(Object[] param : subtaskCountList){
					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
					}
				}
			}
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
		}
		return null;  
	}
	/*
	 * Teja(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDistrictLevelDeptWiseFilterView(java.lang.Long,java.lang.String,java.lang.String,java.lang.int,java.lang.int)
	 */
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long scopeId,String startDateStr,String fromDateStr,String type){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			List<Object[]> scopeDetlsLst = govtDepartmentScopeDAO.getgovtDepatScopeDetails(scopeId);
			Map<Long, List<Long>> deptlevelmap = new HashMap<Long, List<Long>>(0);
			List<Object[]> levelLst = govtDepartmentScopeLevelDAO.govtDepartmentScopeLevelDetails(scopeId);
			if(levelLst != null && levelLst.size() > 0){
				for (Object[] objects : levelLst) {
					if(deptlevelmap.get((Long) objects[0]) == null){
						List<Long> scopeIds = new ArrayList<Long>(0);
						scopeIds.add((Long) objects[1]);
						deptlevelmap.put((Long) objects[0],scopeIds);
					}else{
						deptlevelmap.get((Long) objects[0]).add((Long) objects[1]);
					}
				}
			}
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptNmae-1,scopeId-2,levelName-3,count-4
				List<Object[]> levelWiseCntsLst = alertAssignedOfficerNewDAO.getAlertAssignCountsForDeptWiseDetails(fromDate, toDate);
				if(levelWiseCntsLst != null && levelWiseCntsLst.size() > 0){
					setDistrictLevelDeptWiseFilterView(levelWiseCntsLst,finalVoList,scopeDetlsLst,deptlevelmap);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				//deptId-0,deptNmae-1,scopeId-2,levelName-3,count-4
				List<Object[]> subLevelWiseCntsLst = govtAlertSubTaskDAO.getSubTaskAlertAssignCountsForDeptWiseDetails(fromDate, toDate);
				if(subLevelWiseCntsLst != null && subLevelWiseCntsLst.size() > 0){
					setDistrictLevelDeptWiseFilterView(subLevelWiseCntsLst,finalVoList,scopeDetlsLst,deptlevelmap);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				for (AlertVO vo : finalVoList) {
					if(vo.getSubList2() != null && vo.getSubList2().size() > 0){
						for (AlertVO subVo : vo.getSubList2()){
							   vo.setCategoryCount(vo.getCategoryCount()+subVo.getCount());
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseFilterView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public void setDistrictLevelDeptWiseFilterView(List<Object[]> objLst,List<AlertVO> finalVOList,List<Object[]> scopeDetlsLst,Map<Long, List<Long>> deptlevelmap){
		if(objLst != null && objLst.size() > 0){
			for (Object[] obj : objLst) {
				AlertVO matchedDeptVO = getmatchedVo(finalVOList,(Long)obj[0]);
				if(matchedDeptVO == null){
					matchedDeptVO = new AlertVO(); 
					matchedDeptVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					matchedDeptVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					setScopeDetailsSkeleton(scopeDetlsLst, matchedDeptVO);
					List<Long> scopeIdsList = deptlevelmap.get((Long)obj[0]);
					if(scopeIdsList != null){
						if(scopeIdsList.contains((Long)obj[2])){
							AlertVO matchedScopeVO = getmatchedVo(matchedDeptVO.getSubList2(),(Long)obj[2]);
							if(matchedScopeVO != null){
								matchedScopeVO.setCount((Long) obj[4]);
							}
						}
					}
					finalVOList.add(matchedDeptVO);
				}else{
						AlertVO matchedScopeVo = getmatchedVo(matchedDeptVO.getSubList2(),(Long)obj[2]);
						if(matchedScopeVo != null){
							matchedScopeVo.setCount((Long) obj[4]);
						}
				   }
			   }
		   }
	  }
	public void setScopeDetailsSkeleton(List<Object[]> scopeDetlsLst,AlertVO finalVo){
		if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
			for (Object[] objects : scopeDetlsLst){
				AlertVO vo = new AlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				finalVo.getSubList2().add(vo);
		   }
		}
	}
	public AlertVO getmatchedVo(List<AlertVO> finalVOList,Long deptId){
		if(finalVOList != null && finalVOList.size() > 0){
			for (AlertVO alertVO : finalVOList){
				if(alertVO.getId() != null && alertVO.getId().equals(deptId)){
					return alertVO;
				}
			}
		}
		return null;
	}

	public List<AlertCoreDashBoardVO> groupAlertsTimeWise(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs){
		try{
			Map<Long,ArrayList<AlertCoreDashBoardVO>> groupIdThenAlertListMap = new LinkedHashMap<Long,ArrayList<AlertCoreDashBoardVO>>();
			ArrayList<AlertCoreDashBoardVO> dashBoardVOs = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO  alertCoreDashBoardVO : alertCoreDashBoardVOs){
					
					String alertDateStr = alertCoreDashBoardVO.getCreatedDate();
					Date alertDate = sdf.parse(alertDateStr);
					Long group = getDateGroup(alertDate);
					dashBoardVOs = groupIdThenAlertListMap.get(group);
					if(dashBoardVOs == null){
						dashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
						groupIdThenAlertListMap.put(group, dashBoardVOs);
					}
					dashBoardVOs.add(alertCoreDashBoardVO);
				}
			}
			List<AlertCoreDashBoardVO> list = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO altVO = null;
			DateUtilService dateUtilService = new DateUtilService();
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			String monthStr = getMonthInString(month);
			String dateStr0 = sdf1.format(todayDate);	
			if(groupIdThenAlertListMap != null && groupIdThenAlertListMap.size() > 0){
				for(Entry<Long,ArrayList<AlertCoreDashBoardVO>> entry : groupIdThenAlertListMap.entrySet()){
					altVO = new AlertCoreDashBoardVO();
					if(entry.getKey().longValue() == 1L){
						altVO.setId(entry.getKey());
						altVO.setName("Today Alerts");
						altVO.setCreatedDate(dateStr0);
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 2L){
						altVO.setId(entry.getKey());
						altVO.setName("Week Alerts");
						altVO.setCreatedDate("Past 7 Days");
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 3L){
						altVO.setId(entry.getKey());
						altVO.setName("Month Alerts");
						altVO.setCreatedDate(monthStr+" "+year);
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 4L){
						altVO.setId(entry.getKey());
						altVO.setName("Past Alerts");
						altVO.setCreatedDate("Before "+ monthStr+" "+year);
						altVO.setSubList(entry.getValue());
					}
					
					list.add(altVO);
				}
			}
			if(list != null && list.size() > 0){
				Collections.sort(list, alertDateWiseAscOrder);
			}
				
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public static Comparator<AlertCoreDashBoardVO> alertDateWiseAscOrder = new Comparator<AlertCoreDashBoardVO>() {
    	public int compare(AlertCoreDashBoardVO obj2, AlertCoreDashBoardVO obj1) {
    	Long vo2 = obj2.getId();
    	Long vo1 = obj1.getId();
    	//descending order of percantages.
    	 return vo2.compareTo(vo1);
    	}
     };
	public Long getDateGroup(Date alertDate){
		try{
			DateUtilService dateUtilService = new DateUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//today date
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			String dateStr0 = sdf.format(todayDate);				 
			Long milisecToday = sdf.parse(dateStr0).getTime();
			//date before 7 days
			Date sevevDaysBefore = dateUtilService.getDateBeforeNDays(6);
			String dateStr1 = sdf.format(sevevDaysBefore);
			Long milisecSevevDaysBefore = sdf.parse(dateStr1).getTime();
			//start date of month.
			Date startDateOfMonth = dateUtilService.getStartDateOfMonth();
			String dateStr2 = sdf.format(startDateOfMonth);
			Long milisecStartDateOfMonth = sdf.parse(dateStr2).getTime();
			//last day of privious month.
			Date lastDayOfPreviousMonth = dateUtilService.getLastDayOfPreiviousMonth();
			String dateStr3 = sdf.format(lastDayOfPreviousMonth);
			Long milisecLastDayOfPreviousMonth = sdf.parse(dateStr3).getTime();
			
			if(alertDate.getTime() == milisecToday.longValue()){
				return 1L;
			}else if(alertDate.getTime() <= milisecToday.longValue() && alertDate.getTime() >= milisecSevevDaysBefore.longValue()){
				return 2L;
			}else if(alertDate.getTime() <= milisecToday.longValue() && alertDate.getTime() >= milisecStartDateOfMonth.longValue()){
				return 3L;
			}else{
				return 4L;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			List<Object[]> scopeDetlsLst = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetails(scopeId);
			List<AlertVO> scopeVoList = new ArrayList<AlertVO>(0);
			if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
				for (Object[] objects : scopeDetlsLst) {
					AlertVO vo = new AlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[2]));
					scopeVoList.add(vo);
				}
			}
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptname-1,scopeId-2,level-3,,color-4,count-4
				List<Object[]> deptWiseLocationLvlLList = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseLocationLevelView(fromDate, toDate,deptId);
				if(deptWiseLocationLvlLList != null && deptWiseLocationLvlLList.size() > 0){
					setDeptWiseGraphView(deptWiseLocationLvlLList,finalVoList);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				List<Object[]> deptWiseSubTaskList = govtAlertSubTaskDAO.getDistrictLevelDeptWiseLocationLevelViewForSubtask(fromDate, toDate, deptId);
				if(deptWiseSubTaskList != null && deptWiseSubTaskList.size() > 0){
					setDeptWiseGraphView(deptWiseSubTaskList,finalVoList);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				finalVoList.get(0).setSubList1(scopeVoList);
				for (AlertVO finalVo : finalVoList) {
					if(finalVo.getSubList2() != null && finalVo.getSubList2().size() > 0){
						Long totalCount = 0l;
						for (AlertVO subVo : finalVo.getSubList2()) {
							totalCount = totalCount+subVo.getCount();
						}
						finalVo.setCount(totalCount);
					}
				}
					if(sortingType != null && !sortingType.trim().isEmpty()){
						sortListBasedRequiredType(finalVoList,sortingType);
					}
			   }
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseLocationLevelView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			//statuisId-0,status-1,color-2,shortName-3
			List<Object[]> statusList = alertDepartmentStatusDAO.getAllStatuses();
			List<AlertVO> statusVoList = new ArrayList<AlertVO>(0);
			if(statusList != null && statusList.size() > 0){
				for (Object[] objects : statusList) {
					AlertVO vo = new AlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[2]));
						vo.setSeverityStr(commonMethodsUtilService.getStringValueForObject(objects[3]));//shortname
					statusVoList.add(vo);
				}
			}
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseStatusViewLst = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseStatusOverView(fromDate, toDate,scopeId,deptId);
				if(deptWiseStatusViewLst != null && deptWiseStatusViewLst.size() > 0){
					setDeptWiseGraphView(deptWiseStatusViewLst,finalVoList);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseSubtaskLst = govtAlertSubTaskDAO.getDistrictLevelDeptWiseStatusOverViewForSubTask(fromDate, toDate, scopeId,deptId);
				if(deptWiseSubtaskLst != null && deptWiseSubtaskLst.size() > 0){
					setDeptWiseGraphView(deptWiseSubtaskLst,finalVoList);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				finalVoList.get(0).setSubList1(statusVoList);
				for (AlertVO finalVo : finalVoList) {
					if(finalVo.getSubList2() != null && finalVo.getSubList2().size() > 0){
						Long totalCount = 0l;
						for (AlertVO subVo : finalVo.getSubList2()) {
							totalCount = totalCount+subVo.getCount();
						}
						finalVo.setCount(totalCount);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty()){
					sortListBasedRequiredType(finalVoList,sortingType);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseStatusOverView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public void setDeptWiseGraphView(List<Object[]> objList,List<AlertVO> finalVoList){
		if(objList != null && objList.size() >0){
			for (Object[] obj : objList) {
				
				AlertVO matchedDeptVo = getmatchedVo(finalVoList, (Long)obj[0]);
				if(matchedDeptVo == null){
					matchedDeptVo = new AlertVO();
					matchedDeptVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					matchedDeptVo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				
					AlertVO statusVo = new AlertVO();
					statusVo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					statusVo.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					statusVo.setColor(commonMethodsUtilService.getStringValueForObject(obj[4]));
					statusVo.setCount(commonMethodsUtilService.getLongValueForObject(obj[5]));

					matchedDeptVo.getSubList2().add(statusVo);
					finalVoList.add(matchedDeptVo);
					
				}else{
					AlertVO matchedStatusVo = getmatchedVo(matchedDeptVo.getSubList2(),(Long)obj[2]);
					if(matchedStatusVo == null){
						matchedStatusVo = new AlertVO();
						matchedStatusVo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
						matchedStatusVo.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
						matchedStatusVo.setColor(commonMethodsUtilService.getStringValueForObject(obj[4]));
						matchedStatusVo.setCount(matchedStatusVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[5]));
						matchedDeptVo.getSubList2().add(matchedStatusVo);
					}else{
						matchedStatusVo.setCount(matchedStatusVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[5]));
					}
				}
			}
		}
	}
	
	public List<AlertVO> getGovtDepartmentDetails(){
		 List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();
			if (deptList != null && deptList.size() > 0) {
			 for (Object[] objects : deptList) {
				 AlertVO alertVO = new AlertVO();
				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));			 
				 finalVoList.add(alertVO);
			 }
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
		}
		return finalVoList;
	}
	public List<AlertVO> getGovtDeptScopeDetails(){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			List<Object[]> scopeList = govtDepartmentScopeDAO.getGovtDeptScopeDetails();
			if (scopeList != null && scopeList.size() > 0 ){
				for (Object[] objects : scopeList) {
					AlertVO alertVO = new AlertVO();
					alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalVoList.add(alertVO);
				}				
			}			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
		}		
		return finalVoList;
	}
	
	public  List<IdAndNameVO> getSubOrdinateLevels(Long designationId){
		List<IdAndNameVO> finalVoList = new ArrayList<IdAndNameVO>(0);
		try {
			//0-govtDepartmentScopeId,1-levelName,2-color
			List<Object[]> scopeList = govtDepartmentDesignationOfficerNewDAO.getSubOrdinateLevels(designationId);
			
			if(scopeList != null && scopeList.size() > 0){
				for (Object[] objects : scopeList) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalVoList.add(vo);
				}
		}
	
	}catch (Exception e) {
		LOG.error(" Exception Occured getSubOrdinateLevels() method, Exception - ",e);
	}
	return finalVoList;
		
	}
	
	/*public DistrictOfficeViewAlertVO getAlertDetailsForDistrictOfficer(Long userId,List<Long> departmentIds,Long govtOffcrId,String type,String fromDateStr,
			String toDateStr,Long stateId){
		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
		try{
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> todayList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( todayDate,  todayDate,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(todayList,"today",returnVO);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
			Date last7thDay = cal.getTime();
			List<Object[]> last7dayList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( todayDate,  last7thDay,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(last7dayList,"last7days",returnVO);
			
			
			Calendar c = Calendar.getInstance();
		    int year = c.get(Calendar.YEAR);
		    int month = c.get(Calendar.MONTH);
		    int day = 1;
		    c.set(year, month, day);
		    Date fisrtDayOfCurrentMonth = c.getTime();
			List<Object[]> thisMonthList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( fisrtDayOfCurrentMonth,  todayDate,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(thisMonthList,"currentMonth",returnVO);
			
			Calendar c1 = Calendar.getInstance();
		    int lastMonth = c1.get(Calendar.MONTH)-1;
		    int numOfDaysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		    int day1 = numOfDaysInMonth-1;
		    c.set(year, month, day);
		    Date lastDayOfLastMonth = c.getTime();
		    List<Object[]> beforeCurntMnthList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( lastDayOfLastMonth,  null,  stateId,  departmentIds, levelId, levelValues, type,"pastData");
			setDateWiseAlertDetails(beforeCurntMnthList,"beforThisMonth",returnVO);
			
		}catch (Exception e) {
			LOG.error("Error occured getAlertDetailsForDistrictOfficer() method of AlertManagementSystemService",e);
		}
		return returnVO;
	}*/
	
	public List<DistrictOfficeViewAlertVO> getSubOrdinateAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
			List<Long> desigIds,Long priorityId){
		
		List<DistrictOfficeViewAlertVO> returnList = new ArrayList<DistrictOfficeViewAlertVO>();
		
		try{
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Map<Long,DistrictOfficeViewAlertVO> scopWiseMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
			List<Object[]> list = alertAssignedOfficerNewDAO.getSubOrdinateAlertsDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"totalAlert");
			setScopeDetails(list,scopWiseMap,"totalAlert");
			
			List<Object[]> completedAlerts = alertAssignedOfficerNewDAO.getSubOrdinateAlertsDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"completedAlerts");
			setScopeDetails(completedAlerts,scopWiseMap,"completedAlerts");
			
			List<Object[]> totalTasks = govtAlertSubTaskDAO.getSubOrdinateTasksDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"totalTasks");
			setScopeDetails(totalTasks,scopWiseMap,"totalTasks");
			
			List<Object[]> completedTasks = govtAlertSubTaskDAO.getSubOrdinateTasksDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"completedTasks");
			setScopeDetails(totalTasks,scopWiseMap,"completedTasks");
			
			if(commonMethodsUtilService.isMapValid(scopWiseMap)){
				for(Map.Entry<Long, DistrictOfficeViewAlertVO> entry : scopWiseMap.entrySet()){
					DistrictOfficeViewAlertVO vo = entry.getValue();
					if(commonMethodsUtilService.isListOrSetValid(vo.getList1())){
						for(DistrictOfficeViewAlertVO desigVO :vo.getList1()){
							if(desigVO.getCount() != null && desigVO.getCompletedCnt() != null)
							{
								desigVO.setAlertsPerc(calculatePercantage(desigVO.getCompletedCnt(),desigVO.getCount()));//alertperc
								desigVO.setOverAllCnt(desigVO.getOverAllCnt()+desigVO.getCount());
							}
							if(desigVO.getTaskCnt() != null && desigVO.getTaskCompletedCnt() != null)
							{
								desigVO.setPerc(calculatePercantage(desigVO.getTaskCompletedCnt(),desigVO.getTaskCnt()));//taskPerc
								desigVO.setOverAllCnt(desigVO.getOverAllCnt()+desigVO.getTaskCnt());
							}
							
							
						}
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSubOrdinateAlertsOverview() method of AlertManagementSystemService",e);
		}
		return returnList;
	}
	
	public void setScopeDetails(List<Object[]> list,
			Map<Long,DistrictOfficeViewAlertVO> scopWiseMap,String type){
		
		try{
			if(list != null && list.size() >0){
				for(Object[] obj : list ){
					DistrictOfficeViewAlertVO scopeVO = scopWiseMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(scopeVO == null){
						scopeVO = new DistrictOfficeViewAlertVO();
						scopWiseMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), scopeVO);
					}
					scopeVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					scopeVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					
					DistrictOfficeViewAlertVO desigVo = getMatchVOForSubOrdinate(scopeVO.getList1(),commonMethodsUtilService.getLongValueForObject(obj[2]));
					if(desigVo == null){
						desigVo = new DistrictOfficeViewAlertVO();
						scopeVO.getList1().add(desigVo);
					}
					desigVo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					desigVo.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					if(type != null && type.equalsIgnoreCase("totalAlert")){
						desigVo.setCount(desigVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("completedAlerts")){
						desigVo.setCompletedCnt(desigVo.getCompletedCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("totalTasks")){
						desigVo.setTaskCnt(desigVo.getTaskCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("completedTasks")){
						desigVo.setTaskCompletedCnt(desigVo.getTaskCompletedCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setScopeDetails() method of AlertManagementSystemService",e);
		}
	}
	
	public DistrictOfficeViewAlertVO getMatchVOForSubOrdinate(List<DistrictOfficeViewAlertVO> list,Long id){
		try{
			if(list == null || list.size() ==0)
				return null;
			for(DistrictOfficeViewAlertVO VO:list){
				if(VO.getId().equals(id))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMatchVOForSubOrdinate() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public String  getMonthInString(int month){
		String monthStr = "";
		switch(month){
		case 1:
			monthStr =  "January";
			break;
		case 2:
			monthStr = "February";
			break;
		case 3:
			monthStr = "March";
			break;
		case 4:
			monthStr = "April";
			break;
		case 5:
			monthStr = "May";
			break;
		case 6:
			monthStr = "June";
			break;
		case 7:
			monthStr = "July";
			break;
		case 8:
			monthStr = "August";
			break;
		case 9:
			monthStr = "September";
			break;
		case 10:
			monthStr = "October";
			break;
		case 11:
			monthStr = "November";
			break;
			
		case 12:
			monthStr = "December";
			break;
		}
		return monthStr;
	}
	/*
	 * Swadhin K Lenks
	 * overview
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType,String order){
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			
			List<Object[]> alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCount(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[6]));
					
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
			List<AlertCoreDashBoardVO> innerList = null;
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			AlertCoreDashBoardVO innerVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					innerList = new ArrayList<AlertCoreDashBoardVO>();
					Long total = new Long(0L);
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){  
						innerVO = new AlertCoreDashBoardVO();
						innerVO.setId(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()));
						innerVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
						innerVO.setSevertyColor(lvlIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? lvlIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
						total = total + commonMethodsUtilService.getLongValueForObject(innerEntry.getValue());
						innerVO.setCount(commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
						innerList.add(innerVO);
					}
					alertCoreDashBoardVO.setTotalCount(total);
					alertCoreDashBoardVO.setSubList(innerList);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			if(returnList != null && returnList.size() > 0){
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
					}else{
						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alphabeticalAscSortLvlWise);
					}else{
						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
					}
				}
			}
			System.out.println("HI");  
			return returnList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long scopeId,Long deptId,Long locatonLevelId,Long statusId){
		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
		try {
			List<Long> alertIds = alertAssignedOfficerNewDAO.getAlertIdsForDeptAndLevelId(deptId,locatonLevelId,statusId);
			if(alertIds != null && alertIds.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
				setAlertDtls(finalVoList, list); 
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseFlterClick() method, Exception - ",e);
		}		
		return finalVoList;
	}
	
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			
			List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationsForDepartmentAndLevelLocation(departmentId,levelId,levelValue);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getDesignationsByDepartment() method of AlertManagementSystemService",e);
		}
		return returnList;
	}
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			
			List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getOfficersByDesignationAndLevel(levelId, levelValue, designationId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					
					StringBuilder str = new StringBuilder();
					
					String name="";
					name = obj[1] != null ? obj[1].toString():"";
					if(!name.toString().isEmpty())
						name=name.concat(obj[2] !=null ? " "+" - "+obj[2].toString():"");
					else
						name=obj[2] !=null ? obj[2].toString():"";

					vo.setName(name);
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getOfficersByDesignationAndLevel() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public String assigningSubTaskToOfficer(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					
					//Alert details
					Alert alert = alertDAO.get(inputvo.getAlertId());
					
					Long alertAssignedOfficerId=null;
					List<Long> alertAssignedOfficerIds = alertAssignedOfficerNewDAO.getAlertAssignedOfficerId(inputvo.getAlertId());
					if(alertAssignedOfficerIds !=null && alertAssignedOfficerIds.size()>0)
						alertAssignedOfficerId = alertAssignedOfficerIds.get(0);
					

					//Get Department Designation Officer Ids
					Long desigOfficerId = null;
					List<Long> designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationOfficerIdsNew(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId(),
							inputvo.getGovtOfficerId());
					if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
						desigOfficerId = designationOfficerIds.get(0);
					
					//Subtask Assigning to Officer
					GovtAlertSubTask govtAlertSubTask = new GovtAlertSubTask();
					
					govtAlertSubTask.setAlertId(inputvo.getAlertId());
					govtAlertSubTask.setTitle(alert.getTitle());
					govtAlertSubTask.setDescription(alert.getDescription());
					govtAlertSubTask.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					if(inputvo.getAlertAssignedOfficerId() !=null)
						govtAlertSubTask.setAlertAssignedOfficerId(alertAssignedOfficerId);
					else
						govtAlertSubTask.setAlertAssignedOfficerId(alertAssignedOfficerId);
					
					govtAlertSubTask.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					govtAlertSubTask.setSubTaskGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
					govtAlertSubTask.setCreatedBy(inputvo.getUserId());
					govtAlertSubTask.setUpdatedBy(inputvo.getUserId());
					govtAlertSubTask.setCreatedTime(new DateUtilService().getCurrentDateAndTime());
					govtAlertSubTask.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					
					govtAlertSubTask.setAlertSubTaskStatusId(2l);
					govtAlertSubTask.setIsApproved("Y");
					govtAlertSubTask.setIsDeleted("N");
					
					govtAlertSubTask = govtAlertSubTaskDAO.save(govtAlertSubTask);
					
					//Officer Assigning Tracking
					GovtOfficerSubTaskTracking govtOfficerSubTaskTracking = new GovtOfficerSubTaskTracking();
					
					if(inputvo.getAlertAssignedOfficerId() !=null)
						govtOfficerSubTaskTracking.setAlertAssignedOfficerId(alertAssignedOfficerId);
					else
						govtOfficerSubTaskTracking.setAlertAssignedOfficerId(alertAssignedOfficerId);
					
					govtOfficerSubTaskTracking.setGovtAlertSubTaskId(govtAlertSubTask.getGovtAlertSubTaskId());
					govtOfficerSubTaskTracking.setGovtAlertActionTypeId(1l);
					govtOfficerSubTaskTracking.setAlertSubTaskStatusId(govtAlertSubTask.getAlertSubTaskStatusId());
					govtOfficerSubTaskTracking.setInsertedById(inputvo.getUserId());
					govtOfficerSubTaskTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					govtOfficerSubTaskTracking.setIsDeleted("N");
					
					
					govtOfficerSubTaskTracking = govtOfficerSubTaskTrackingDAO.save(govtOfficerSubTaskTracking);
					
					
					return "success";
				}
			});
		} catch (Exception e) {
			LOG.error("Error occured assigningSubTaskToOfficer() method of AlertManagementSystemService",e);
		}
		return status;
	}
	
	public IdNameVO getGovtSubLevelInfo(Long departmentId,Long LevelId,Long levelValue){
		
		IdNameVO finalVO = new IdNameVO();
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> objList = govtDepartmentWorkLocationRelationDAO.getGovtSubLevelInfo(levelValue);
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {
					
					IdNameVO VO = getGovtSubLevelMatchedVo(returnList,(Long)obj[0]);
					if(VO == null){
						VO = new IdNameVO();
						VO.setId((Long)obj[0]);
						VO.setName(obj[1] !=null ? obj[1].toString():"");
						returnList.add(VO);
					}
					
					IdNameVO subVo = new IdNameVO();					
					subVo.setId((Long)obj[2]);
					subVo.setName(obj[3] !=null ? obj[3].toString():"");
					
					VO.getIdnameList().add(subVo);
					
				}
			}
			
			if(returnList !=null && returnList.size()>0)
				finalVO = returnList.get(0);
			
		} catch (Exception e) {
			LOG.error("Error occured getGovtSubLevelInfo() method of AlertManagementSystemService",e);
		}
		
		return finalVO;
	}
	
	public IdNameVO getGovtSubLevelMatchedVo(List<IdNameVO> returnList,Long levelId){
		try{
			if(returnList == null || returnList.size() ==0)
				return null;
			for(IdNameVO VO:returnList){
				if(VO.getId().equals(levelId))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGovtSubLevelMatchedVo() method of CccDashboardService{}");
		}
		return null;
	}
	public void sortListBasedRequiredType(List<AlertVO> resultList,String sortingType){
		 try{
			if(sortingType != null && sortingType.equalsIgnoreCase("Decending")){
				Collections.sort(resultList, alertDescendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("Ascending")){
				Collections.sort(resultList, alertAscendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalAscending")){
				Collections.sort(resultList, alphabeticalAscendingSort);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalDescending")){
				Collections.sort(resultList, alphabeticalDescendingSort);
			}
		 }catch(Exception e){
			LOG.error("Exception occured  in sortListByRequiredType() in AlertService class ",e);  
		 }
	 }
	public static Comparator<AlertVO> alertDescendingCountWiseSorting = new Comparator<AlertVO>() {
    	public int compare(AlertVO location2, AlertVO location1) {
    	Long count2 = location2.getCount();
    	Long count1 = location1.getCount();
    	//descending order of percantages.
    	 return count1.compareTo(count2);
    	}
     };
     public static Comparator<AlertVO> alertAscendingCountWiseSorting = new Comparator<AlertVO>() {
     	public int compare(AlertVO location2, AlertVO location1) {
     	Long count2 = location2.getCount();
     	Long count1 = location1.getCount();
     	//ascending order of percantages.
     	 return count2.compareTo(count1);
     	}
      };
      public static Comparator<AlertVO> alphabeticalDescendingSort = new Comparator<AlertVO>() {
       	public int compare(AlertVO location2, AlertVO location1) {
        	String name2 = location2.getName();
       	    String name1 = location1.getName();
       	    //descending order of percantages.
       	    return name1.compareTo(name2);
       	}
       };
       public static Comparator<AlertVO> alphabeticalAscendingSort = new Comparator<AlertVO>() {
          	public int compare(AlertVO location2, AlertVO location1) {
          	String name2 = location2.getName();
          	String name1 = location1.getName();
          	//ascending order of percantages.
          	 return name2.compareTo(name1);
          	}
        };
        public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}else{
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			
    			
    			List<Object[]> alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountForStatus(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
    			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    				prepareResultForState(alertList,returnList,sortingType,order);
    				return returnList;
    			}
    			
    			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();
    			Map<Long,LinkedHashMap<Long,Long>> locIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> statusIdAndAlertCountMap = null;
    			
    			if(alertList != null && alertList.size() > 0){ 
    				for(Object[] param : alertList){
    					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
    					statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
    					statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[6]));
    					
    					statusIdAndAlertCountMap = locIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    					if(statusIdAndAlertCountMap == null){
    						statusIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
    						locIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndAlertCountMap);
    					}
    					statusIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
    				}
    			}
    			
    			List<AlertCoreDashBoardVO> innerList = null;
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			AlertCoreDashBoardVO innerVO = null;
    			if(locIdThenStatusIdThenAlertCount != null && locIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					innerList = new ArrayList<AlertCoreDashBoardVO>();
    					Long total = new Long(0L);
    					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){  
    						innerVO = new AlertCoreDashBoardVO();
    						innerVO.setId(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()));
    						innerVO.setName(statusIdAndStatusName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? statusIdAndStatusName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
    						innerVO.setSevertyColor(statusIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? statusIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
    						total = total + commonMethodsUtilService.getLongValueForObject(innerEntry.getValue());
    						innerVO.setCount(commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
    						innerList.add(innerVO);
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					alertCoreDashBoardVO.setSubList(innerList);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			System.out.println("HI");
    			if(returnList != null && returnList.size() > 0){
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
    					}else{
    						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
    					}
    				}
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
    					}else{
    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
    					}
    				}
    			}
    			return returnList;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}
        public List<AlertCoreDashBoardVO> prepareResultForState(List<Object[]> alertList,List<AlertCoreDashBoardVO> returnList,String sortingType,String order){
        	try{
        		
        		Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();    
    			
    			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> statusIdThenAlertCount = null;
    			
        		if(alertList != null && alertList.size() > 0){
        			for(Object[] param : alertList){
        				lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[3]),commonMethodsUtilService.getStringValueForObject(param[4]));
        				statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[5]), commonMethodsUtilService.getStringValueForObject(param[6]));
        				statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[5]), commonMethodsUtilService.getStringValueForObject(param[8]));
    					
        				statusIdThenAlertCount = lvlIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[3]));
    					if(statusIdThenAlertCount == null){
    						statusIdThenAlertCount = new LinkedHashMap<Long,Long>();
    						lvlIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCount);
    					}
    					statusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[5]), commonMethodsUtilService.getLongValueForObject(param[7]));
    				}
        		}
        		List<AlertCoreDashBoardVO> innerList = null;
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			AlertCoreDashBoardVO innerVO = null;
    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					innerList = new ArrayList<AlertCoreDashBoardVO>();
    					Long total = new Long(0L);
    					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){  
    						innerVO = new AlertCoreDashBoardVO();
    						innerVO.setId(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()));
    						innerVO.setName(statusIdAndStatusName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? statusIdAndStatusName.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
    						innerVO.setSevertyColor(statusIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) != null ? statusIdAndColor.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey())) : "");
    						total = total + commonMethodsUtilService.getLongValueForObject(innerEntry.getValue());
    						innerVO.setCount(commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
    						innerList.add(innerVO);
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					alertCoreDashBoardVO.setSubList(innerList);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			System.out.println("HI");
    			if(returnList != null && returnList.size() > 0){
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
    					}else{
    						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
    					}
    				}
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
    					}else{
    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
    					}
    				}
    			}
    			
    			return returnList;
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	return null;
        }
        public static Comparator<AlertCoreDashBoardVO> alertAscendingCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
         	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
         	Long count2 = location2.getTotalCount();
         	Long count1 = location1.getTotalCount();
         	//ascending order of percantages.
         	 return count2.compareTo(count1);
         	}
          };
          public static Comparator<AlertCoreDashBoardVO> alertDescCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
           	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
           	Long count2 = location2.getTotalCount();
           	Long count1 = location1.getTotalCount();
           	//ascending order of percantages.
           	 return count1.compareTo(count2);
           	}
            };
          public static Comparator<AlertCoreDashBoardVO> alphabeticalDescendingSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
           	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
            	String name2 = location2.getName();
           	    String name1 = location1.getName();
           	    //descending order of percantages.
           	    return name1.compareTo(name2);
           	}
           };
           public static Comparator<AlertCoreDashBoardVO> alphabeticalAscSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
              	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
              		String name2 = location2.getName();
              	    String name1 = location1.getName();
              	    //descending order of percantages.
              	    return name2.compareTo(name1);
              	}
              };
}
