package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
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
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertDepartmentComment;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AlertManagementSystemService extends AlertService implements IAlertManagementSystemService{

	private final static Logger LOG =  Logger.getLogger(AlertManagementSystemService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IAlertDAO alertDAO;
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO;
	private IAlertDepartmentStatusDAO alertDepartmentStatusDAO;
	private IAlertDepartmentCommentDAO alertDepartmentCommentDAO;
	private DateUtilService dateUtilService;
	private IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO;
	private IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO;
	private IGovtDepartmentDesignationOfficerNewDAO govtDepartmentDesignationOfficerNewDAO;
	private IGovtAlertSubTaskDAO govtAlertSubTaskDAO;
	
	private TransactionTemplate transactionTemplate = null;
	private IGovtDepartmentScopeLevelDAO govtDepartmentScopeLevelDAO;
	private IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO;
	private IAlertSubTaskStatusDAO alertSubTaskStatusDAO;
	private IGovtDepartmentScopeDAO govtDepartmentScopeDAO;
	private IGovtDepartmentDAO govtDepartmentDAO;
	
	
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
	public IAlertDepartmentCommentDAO getAlertDepartmentCommentDAO() {
		return alertDepartmentCommentDAO;
	}
	public void setAlertDepartmentCommentDAO(IAlertDepartmentCommentDAO alertDepartmentCommentDAO) {
		this.alertDepartmentCommentDAO = alertDepartmentCommentDAO;
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
					 AlertVO VO = getMatchVO(finalAlertVOs,id);
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
	public AlertVO getMatchVO(List<AlertVO> finalAlertVOs,Long id){
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
		public ResultStatus updateAlertStatus(Long alertId,Long statusId,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				List<AlertAssignedOfficerNew> objList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
				
				if(objList !=null && objList.size() > 0){
					AlertAssignedOfficerNew aaon = objList.get(0);
					aaon.setAlertStatusId(statusId);
					alertAssignedOfficerNewDAO.save(aaon);
						
					//save record in tracking
					saveRecordIntoTracking(aaon,userId,0l,6l);
					rs.setExceptionMsg("success");
				}
			} catch (Exception e){
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateAlertStatus");
			}
			return rs;
		}

		public ResultStatus updateComment(Long alertId,String comment,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				
				AlertDepartmentComment adc = new AlertDepartmentComment();
				adc.setComment(comment);
				adc.setInsertedBy(userId);
				adc.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				adc = alertDepartmentCommentDAO.save(adc);

				List<AlertAssignedOfficerNew> aaonList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
				if(aaonList != null && aaonList.size() > 0){
					AlertAssignedOfficerNew aaon = aaonList.get(0);
					
					//save record in tracking
					saveRecordIntoTracking(aaon,userId,adc.getAlertDepartmentCommentId(),7l);
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
					rs.setExceptionMsg("success");
				}
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateAlertPriority");
			}
			return rs;
		}
		
		public void saveRecordIntoTracking(AlertAssignedOfficerNew aaon,Long userId,Long id,Long actiontype){
			AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
			aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
			aaotn.setAlertId(aaon.getAlertId());
			aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
			aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
			aaotn.setGovtAlertActionTypeId(actiontype);
			
			if(actiontype == 7l)//comment
				aaotn.setAlertDepartmentCommentId(id);
			
			aaotn.setInsertedBy(userId);
			aaotn.setAlertStatusId(aaon.getAlertStatusId());
			aaotn.setUpdatedBy(userId);
			aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			aaotn.setIsApproved(aaon.getIsApproved());
			alertAssignedOfficerTrackingNewDAO.save(aaotn);
		}
		//sandeep
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
			
			// My alerts Status wise count
			
			setStatusWiseCount( myAlertsOverAllList, returnVO,"myAlerts",Long.valueOf(myAlertsTodayList.size()));
			
			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0l && govtOffcrId != null && govtOffcrId.longValue() > 0l){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"today","mySubTasks");
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"overAll","mySubTasks");
			}
			
			// My SubTasks Status wise count
			setStatusWiseCount( myAlertsOverAllList, returnVO,"mySubTasks",Long.valueOf(myAlertsTodayList.size()));
			
			
			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0l && govtOffcrId != null && govtOffcrId.longValue() > 0l){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"today","myAssignedSubTasks");
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrId,govtOffcrId,"overAll","myAssignedSubTasks");
			}
			
			// My Assigned SubTasks Status wise count
			setStatusWiseCount( myAlertsOverAllList, returnVO,"myAssignedSubTasks",Long.valueOf(myAlertsTodayList.size()));
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in AlertManagementSystemService of  getDistrictOfficerAlertsCountView() ", e);
		}
		return returnVO;
	}
	
	public void setStatusWiseCount(List<Object[]> myAlertsOverAllList,DistrictOfficeViewAlertVO returnVO,String type,Long todayCount){
		try{
			Map<Long,DistrictOfficeViewAlertVO> myAlertsStatusMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
			
			if(commonMethodsUtilService.isListOrSetValid(myAlertsOverAllList)){
				for (Object[] objects : myAlertsOverAllList) {
					DistrictOfficeViewAlertVO vo = myAlertsStatusMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(vo == null){
						vo = new DistrictOfficeViewAlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						//vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
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
					returnVO.getList1().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList1()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList2())){
					returnVO.getList2().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList2()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList3())){
					returnVO.getList3().get(0).setTodayCount(todayCount);
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
					
					inputvo.setLevelValue(inputvo.getLevelValue() !=null ? inputvo.getLevelValue().longValue():null);
					
					
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
					if(param[0] !=null && !(param[0].equals(levelId))){	
						
						IdNameVO vo = new IdNameVO();						
						vo.setId((Long)param[0]);
						vo.setName(param[1] !=null ? param[1].toString():"");	
						
						//levelList.add(vo);
						levelMap.put((Long)param[0], vo);
						
						subLevelIds.add((Long)param[0]);
					}
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
			
		} catch (Exception e) {
			LOG.error("Error occured setLocationValuesToMap() method of AlertManagementSystemService",e);
		}
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId){
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
			List<Object[]> alertList = alertDAO.getTotalAlertByStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId);
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
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId){
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
			List<Long> alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList);
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
					if(scopeIdsList.contains((Long)obj[2])){
						AlertVO matchedScopeVO = getmatchedVo(matchedDeptVO.getSubList2(),(Long)obj[2]);
						if(matchedScopeVO != null){
							matchedScopeVO.setCount((Long) obj[4]);
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
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId){
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
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseLocationLevelView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId){
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
}
