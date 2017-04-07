package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertDepartmentComment;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AlertManagementSystemService implements IAlertManagementSystemService{

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
			setAlertCount(totalList,finalAlertVOs,"Status"); 
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusWiseAlertOverviewcnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public void setAlertCount(List<Object[]> objList,List<AlertVO> finalAlertVOs,String type){
	    try{
	    	if(objList != null && objList.size() > 0){         
				Long totalAlertCnt = 0l;
				for(Object[] param : objList){
					 if(type.equalsIgnoreCase("Status")){
						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[3]);	 
					 }else{
						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[2]);						 
					 }
				}
				for(Object[] param : objList){
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					 AlertVO VO = getMatchVO(finalAlertVOs,id);
					 if(VO == null){
						 VO = new AlertVO();
						 VO.setId(id);
						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 if(type.equalsIgnoreCase("Status")){
						  VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2])); 
						  VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
						 }else{
						  VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
						 }
						 finalAlertVOs.add(VO); 
					 }else{
					  if(type.equalsIgnoreCase("Status")){
						  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[3]));
						 }else{
						  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[2]));
						 } 
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
			setAlertCount(rtrnObjLst,finalAlertVOs,"Other");
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	
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
			setAlertCount(totalList,finalAlertVOs,"Other");
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
						vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
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
							returnVO.getList1().get(0).setOverAllCnt(myAlertsOverAllCnt);
							returnVO.getList1().add(vo);
						}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
							returnVO.getList2().get(0).setOverAllCnt(myAlertsOverAllCnt);
							returnVO.getList2().add(vo);
						}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
							returnVO.getList3().get(0).setOverAllCnt(myAlertsOverAllCnt);
							returnVO.getList3().add(vo);
						}
					}
					
				}
			}
			
			if(type != null && type.equalsIgnoreCase("myAlerts")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList1())){
					returnVO.getList1().get(0).setCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList1()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList2())){
					returnVO.getList2().get(0).setCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList2()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList3())){
					returnVO.getList3().get(0).setCount(todayCount);
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
}
