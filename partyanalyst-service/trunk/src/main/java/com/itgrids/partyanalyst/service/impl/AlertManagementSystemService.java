package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
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
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertSubTaskStatusDAO;
import com.itgrids.partyanalyst.dao.IEditionsDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLevelDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskTrackingDAO;
import com.itgrids.partyanalyst.dao.INewsPaperDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.FilterSectionVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertDepartmentCommentNew;
import com.itgrids.partyanalyst.model.AlertDepartmentDocumentNew;
import com.itgrids.partyanalyst.model.AlertSubTaskStatus;
import com.itgrids.partyanalyst.model.CustomReport;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO;
	private IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO;
	private IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO;

	private IAlertStatusDAO alertStatusDAO; 
	private IAlertSeverityDAO alertSeverityDAO; 
	private IAlertCategoryDAO alertCategoryDAO;
	private IEditionsDAO editionsDAO; 
	private ITvNewsChannelDAO tvNewsChannelDAO;
	private INewsPaperDAO newsPaperDAO;
	
	
	public IAlertSubTaskStatusDAO getAlertSubTaskStatusDAO() {
		return alertSubTaskStatusDAO;
	}

	public void setAlertSubTaskStatusDAO(
			IAlertSubTaskStatusDAO alertSubTaskStatusDAO) {
		this.alertSubTaskStatusDAO = alertSubTaskStatusDAO;
	}

	public IAlertSeverityDAO getAlertSeverityDAO() {
		return alertSeverityDAO;
	}

	public void setAlertSeverityDAO(IAlertSeverityDAO alertSeverityDAO) {
		this.alertSeverityDAO = alertSeverityDAO;
	}

	public IAlertCategoryDAO getAlertCategoryDAO() {
		return alertCategoryDAO;
	}

	public void setAlertCategoryDAO(IAlertCategoryDAO alertCategoryDAO) {
		this.alertCategoryDAO = alertCategoryDAO;
	}

	public IEditionsDAO getEditionsDAO() {
		return editionsDAO;
	}

	public void setEditionsDAO(IEditionsDAO editionsDAO) {
		this.editionsDAO = editionsDAO;
	}

	public ITvNewsChannelDAO getTvNewsChannelDAO() {
		return tvNewsChannelDAO;
	}

	public void setTvNewsChannelDAO(ITvNewsChannelDAO tvNewsChannelDAO) {
		this.tvNewsChannelDAO = tvNewsChannelDAO;
	}

	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}

	public IGovtDepartmentDesignationDAO getGovtDepartmentDesignationDAO() {
		return govtDepartmentDesignationDAO;
	}

	public void setGovtDepartmentDesignationDAO(IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO) {
		this.govtDepartmentDesignationDAO = govtDepartmentDesignationDAO;
	}

	public IGovtDepartmentDesignationOfficerDetailsDAO getGovtDepartmentDesignationOfficerDetailsDAO() {
		return govtDepartmentDesignationOfficerDetailsDAO;
	}

	public void setGovtDepartmentDesignationOfficerDetailsDAO(
			IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO) {
		this.govtDepartmentDesignationOfficerDetailsDAO = govtDepartmentDesignationOfficerDetailsDAO;
	}

	public IGovtDepartmentDesignationHierarchyDAO getGovtDepartmentDesignationHierarchyDAO() {
		return govtDepartmentDesignationHierarchyDAO;
	}

	public void setGovtDepartmentDesignationHierarchyDAO(IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO) {
		this.govtDepartmentDesignationHierarchyDAO = govtDepartmentDesignationHierarchyDAO;
	}

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
	
	public INewsPaperDAO getNewsPaperDAO() {
		return newsPaperDAO;
	}

	public void setNewsPaperDAO(INewsPaperDAO newsPaperDAO) {
		this.newsPaperDAO = newsPaperDAO;
	}

	//Business Method
	/*
	 * Satnosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStatusWiseAlertOverviewcnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long)
	 */
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList){
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
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> totalList = new ArrayList<Object[]>();
			List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status",calCntrIdList);//for pending status
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
			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null,calCntrIdList);
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
	public void calculatePerc(List<AlertVO> resuList,Long totalAlertCnt){
		try{
			if(resuList != null && resuList.size() > 0){
				for(AlertVO VO:resuList){
					if(VO.getSubList2() != null && VO.getSubList2().size() > 0){
						for (AlertVO alertVO : VO.getSubList2()) {
							alertVO.setPercentage(calculatePercantage(alertVO.getAlertCnt(), totalAlertCnt));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calculatePercentage() method of CccDashboardService{}");
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
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList){
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
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
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
			List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",null,null,calCntrIdList);
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
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType,List<Long> calCntrIdList){
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
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			
			List<Object[]> totalList = new ArrayList<Object[]>();
			if(resultType != null && resultType.equalsIgnoreCase("Status") && (alertStatusIds.size() == 0l || alertStatusIds.contains(1l))){
				List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Department",calCntrIdList);//for pending status getting department wise status
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
				List<Object[]> rtrnObjLst  = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds,calCntrIdList);
				if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					totalList.addAll(rtrnObjLst);
				}
			}
			if(resultType != null && resultType.equalsIgnoreCase("Department")){
				List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds,calCntrIdList);
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
		public ResultStatus updateComment(final Long alertId,final String comment,final Long userId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
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
					}
				});
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateComment");
			}
			return rs;
		}

		public ResultStatus updateAlertPriority(final Long alertId,final Long priorityId,final Long userId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						Integer count = alertDAO.updateAlertPriority(alertId,priorityId,userId,dateUtilService.getCurrentDateAndTime());
						if(count != null && count > 0){
							//save record in tracking
							saveRecordIntoTracking(alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0),userId,priorityId+"",5l);
							
							rs.setExceptionMsg("success");
						}
					}
				});
				
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
		
		public ResultStatus updateAlertStatusComment(final Long alertId,final Long statusId,final String comment,final Long userId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						Alert alert = alertDAO.get(alertId);
						if(alert != null && statusId != null && statusId.longValue()>0L){
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
						if(statusId == 8l || statusId == 9l)
							aaon.setIsApproved("N");
						aaon.setAlertStatusId(statusId);
						aaon.setUpdatedBy(userId);
						aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						alertAssignedOfficerNewDAO.save(aaon);
						
						AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
							if(statusId == 8l || statusId == 9l)
								aaon.setIsApproved("N");
						aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
						aaotn.setAlertId(aaon.getAlertId());
						aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
						aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
						aaotn.setGovtAlertActionTypeId(6l);
						if(statusId != null && statusId.longValue()>0L)
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
					}
				});	
				
				
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
			CustomReport customReport = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			// int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			
			 
			 StringBuilder str ;
			 
			 if(mapfiles != null && mapfiles.size() > 0){
				 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
				 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
					 str = new StringBuilder();
					 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
					 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
					 StringBuilder pathBuilder = new StringBuilder();
					  pathBuilder.append("alerts_attachments/").append(randomNumber).append(".").append(entry.getValue());
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
			
			 resultStatus.setExceptionMsg("success");
					}
				});
			}catch (Exception e) {
				resultStatus.setExceptionMsg("failure");
				LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
			}
			return resultStatus;
		
		}
		
		public List<AlertTrackingVO> viewAlertHistory(Long alertId){
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
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() > 0l 
								 && alertAssignedOfficerTrackingNew.getAlertDepartmentDocument() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentDocument().getDocument());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							matchedTimeVO.getAttachementsList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() > 0l && alertAssignedOfficerTrackingNew.getAlertDepartmentComment() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentComment().getComment());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							matchedTimeVO.getCommentList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getDueDate() != null && !alertAssignedOfficerTrackingNew.getDueDate().toString().trim().isEmpty()){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getDueDate().toString());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							matchedTimeVO.getDueDateList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertStatusId() != null && alertAssignedOfficerTrackingNew.getAlertStatusId() > 0l && alertAssignedOfficerTrackingNew.getAlertStatus() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertStatus().getAlertStatus());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							matchedTimeVO.getStatusList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertSeviorityId() != null && alertAssignedOfficerTrackingNew.getAlertSeviorityId() > 0l && alertAssignedOfficerTrackingNew.getAlertSeviority() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertSeviority().getSeverity());
							vo.setUserName(userName);
							vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
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
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList){
		
		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
		try{
			Date fromDate = null;
		      Date toDate = null;
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
		        fromDate = sdf.parse(startDate);
		        toDate = sdf.parse(endDate);
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
			returnVO.setLevelValues(levelValues);
			returnVO.setLevelId(levelId);
			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
			
			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
			List<Long> govtOffcrIds = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for( Object[]  obj :list1){
					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
					returnVO.setGovtOfficerIds(govtOffcrIds);
					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
					
				}
			}
			
			List<Object[]> myAlertsTodayList = null;
			List<Object[]> myAlertsOverAllList = null;
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrIds,govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList);
				myAlertsOverAllList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList);
				//List<Long> todayAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"today");
				//List<Long> overAllAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll");
				// My alerts Status wise count
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"myAlerts",Long.valueOf(myAlertsTodayList.size()));
			}
			
				
			
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"today","mySubTasks",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList);
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll","mySubTasks",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList);
				//List<Long> todayMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today","mySubTasks");
				//List<Long> overAllMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll","mySubTasks");
				// My SubTasks Status wise count
				
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"mySubTasks",Long.valueOf(myAlertsTodayList.size()));
			}
			
			
			
			
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"today","myAssignedSubTasks",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList);
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll","myAssignedSubTasks",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList);

				//List<Long> todayMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today","myAssignedSubTasks");
				//List<Long> overAllMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll","myAssignedSubTasks");
				// My Assigned SubTasks Status wise count
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"myAssignedSubTasks",Long.valueOf(myAlertsTodayList.size()));
				
			}
			
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
					//returnVO.getList1().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList1().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList1().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList1()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList2())){
					//returnVO.getList2().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList2().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList2().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList2()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList3())){
					returnVO.getList3().get(0).setTodayCount(todayCount);
					//returnVO.getList3().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList3().get(0).getOverAllAlertIds().addAll(overAllAlertId);
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
					//check whether the alert is asigned to somebody or not, if already assigned delete that assignment
					Long assingedId = alertAssignedOfficerNewDAO.getAssignedDtls(inputvo.getAlertId());
					
					if(assingedId != null){
						AlertAssignedOfficerNew alertAssignedOfficer2 = new AlertAssignedOfficerNew();
						alertAssignedOfficer2 = alertAssignedOfficerNewDAO.get(assingedId);
						alertAssignedOfficer2.setAlertId(inputvo.getAlertId());
						alertAssignedOfficer2.setGovtDepartmentDesignationOfficerId(desigOfficerId);
						alertAssignedOfficer2.setGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
						alertAssignedOfficer2.setInsertedBy(inputvo.getUserId());
						alertAssignedOfficer2.setUpdatedBy(inputvo.getUserId());
						alertAssignedOfficer2.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficer2.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficer2.setAlertStatusId(2l);
						alertAssignedOfficer2.setIsDeleted("N");
						alertAssignedOfficer2.setIsApproved("Y");  
						alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer2);
					}else{
						alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);
					}
					
					
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
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIdList){
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
			List<Object[]> alertList = alertDAO.getTotalAlertByStatusNew(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,deptId,calCntrIdList);
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
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList){
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
			List<Long> alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList);
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
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long userId,String startDateStr,String fromDateStr,String type){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
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
	
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long userId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> scopeDetlsLst = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetails(scopeId);
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptname-1,scopeId-2,level-3,,color-4,count-4
				List<Object[]> deptWiseLocationLvlLList = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseLocationLevelView(fromDate, toDate,deptId);
				if(deptWiseLocationLvlLList != null && deptWiseLocationLvlLList.size() > 0){
					setDeptWiseGraphView(deptWiseLocationLvlLList,finalVoList,scopeDetlsLst);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				List<Object[]> deptWiseSubTaskList = govtAlertSubTaskDAO.getDistrictLevelDeptWiseLocationLevelViewForSubtask(fromDate, toDate, deptId);
				if(deptWiseSubTaskList != null && deptWiseSubTaskList.size() > 0){
					setDeptWiseGraphView(deptWiseSubTaskList,finalVoList,scopeDetlsLst);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
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
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long userId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType,Long levelId){
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
			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseStatusViewLst = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseStatusOverView(fromDate, toDate,scopeId,deptId,levelId);
				if(deptWiseStatusViewLst != null && deptWiseStatusViewLst.size() > 0){
					setDeptWiseGraphView(deptWiseStatusViewLst,finalVoList,statusList);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseSubtaskLst = govtAlertSubTaskDAO.getDistrictLevelDeptWiseStatusOverViewForSubTask(fromDate, toDate, scopeId,deptId,levelId);
				if(deptWiseSubtaskLst != null && deptWiseSubtaskLst.size() > 0){
					setDeptWiseGraphView(deptWiseSubtaskLst,finalVoList,statusList);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
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
	public void setDeptWiseGraphView(List<Object[]> objList,List<AlertVO> finalVoList,List<Object[]> tempList){
		if(objList != null && objList.size() >0){
			for (Object[] obj : objList) {
				AlertVO matchedDeptVo = getmatchedVo(finalVoList, (Long)obj[0]);
				if(matchedDeptVo == null){
					matchedDeptVo = new AlertVO();
					matchedDeptVo.setSubList2(getTemplate(tempList));
					matchedDeptVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					matchedDeptVo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				
					if(matchedDeptVo.getSubList2() != null && matchedDeptVo.getSubList2().size() > 0){
						for (AlertVO  vo : matchedDeptVo.getSubList2()) {
							if(vo.getId().equals((Long)obj[2])){
								vo.setCount(commonMethodsUtilService.getLongValueForObject(obj[5]));
							}
						}
						
					}
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
			//0-govtDepartmentScopeId,1-levelName
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
			setScopeDetails(completedTasks,scopWiseMap,"completedTasks");
			
			if(commonMethodsUtilService.isMapValid(scopWiseMap)){
				for(Map.Entry<Long, DistrictOfficeViewAlertVO> entry : scopWiseMap.entrySet()){
					DistrictOfficeViewAlertVO vo = entry.getValue();
					returnList.add(vo);
					if(commonMethodsUtilService.isListOrSetValid(vo.getList1())){
						for(DistrictOfficeViewAlertVO locationVO :vo.getList1()){
							if(commonMethodsUtilService.isListOrSetValid(locationVO.getList2())){
								for(DistrictOfficeViewAlertVO desigVO :locationVO.getList2()){
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
					
					DistrictOfficeViewAlertVO locationVO = getMatchVOForSubOrdinate(scopeVO.getList1(),commonMethodsUtilService.getLongValueForObject(obj[5]));
					if(locationVO == null){
						locationVO = new DistrictOfficeViewAlertVO();
						scopeVO.getList1().add(locationVO);
					}
					locationVO.setId(commonMethodsUtilService.getLongValueForObject(obj[5]));
					locationVO.setName(commonMethodsUtilService.getStringValueForObject(obj[6]));
					
					DistrictOfficeViewAlertVO desigVo = getMatchVOForSubOrdinate(locationVO.getList2(),commonMethodsUtilService.getLongValueForObject(obj[2]));
					if(desigVo == null){
						desigVo = new DistrictOfficeViewAlertVO();
						locationVO.getList2().add(desigVo);
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
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
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
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
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
			return returnList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void buildStatusWiseTemplate(AlertCoreDashBoardVO alertCoreDashBoardVO,Map<Long,String> lvlIdAndLvlName,Map<Long,String> lvlIdAndColor){
		try{
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO coreDashBoardVO = null;
			if(lvlIdAndLvlName != null && lvlIdAndLvlName.size() > 0 && lvlIdAndColor != null &&  lvlIdAndColor.size() > 0){
				for(Entry<Long,String> entry : lvlIdAndLvlName.entrySet()){
					coreDashBoardVO = new AlertCoreDashBoardVO();
					coreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(entry.getKey()));
					coreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(entry.getValue()));
					coreDashBoardVO.setSevertyColor(lvlIdAndColor.get(entry.getKey()) != null ? lvlIdAndColor.get(entry.getKey()) : "");
					alertCoreDashBoardVOs.add(coreDashBoardVO);
				}
				alertCoreDashBoardVO.setSubList(alertCoreDashBoardVOs);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long userId,Long deptId,Long locatonLevelId,
			Long statusId,String type,String fromDateStr,String toDateStr,
			Long desigDeptOfficerId,Long officerId){
		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
		List<Long> alertIds = null;
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
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
			if(type.equalsIgnoreCase("alert")){
					alertIds = alertAssignedOfficerNewDAO.getAlertIdsForDeptAndLevelId(deptId,locatonLevelId,statusId,fromDate,toDate,desigDeptOfficerId,officerId,levelId);
			}else if(type.equalsIgnoreCase("subTask")){
				alertIds = govtAlertSubTaskDAO.getAlertIdsForDeptAndLevelId(deptId,locatonLevelId,statusId,fromDate,toDate,desigDeptOfficerId,officerId,levelId);
			}
			
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
					
					try {
						//Alert details
						//Alert alert = alertDAO.get(inputvo.getAlertId());
						
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
						govtAlertSubTask.setTitle(inputvo.getTitle());
						govtAlertSubTask.setDescription(inputvo.getTitle());
						govtAlertSubTask.setGovtDepartmentDesignationOfficerId(desigOfficerId);
						govtAlertSubTask.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(inputvo.getDueDate()));
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
						
						govtAlertSubTask.setAlertSubTaskStatusId(1l); // default status Notified
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
					} catch (Exception e) {
						LOG.error("Error occured assigningSubTaskToOfficer() method of AlertManagementSystemService",e);
					}
					return "failure";
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
			LOG.error("Error occured getGovtSubLevelMatchedVo() method of AlertManagementSystemService{}");
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
			LOG.error("Exception occured  in sortListByRequiredType() in AlertManagementSystemService class ",e);  
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
        /*
         * Swadhin K Lenka
         * status
         * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCountStatusWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
         */
        public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			
    			if(electronicIdList == null && printIdList == null){
    				electronicIdList = new ArrayList<Long>();
    				printIdList = new ArrayList<Long>();
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
    			}/*else{
    				
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}*/
    			
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
    			
    			List<Object[]> alertList =null;
    			if(alertType != null && alertType.equalsIgnoreCase("alert")){
    				 alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountForStatus(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
    			}else if(alertType != null && alertType.equalsIgnoreCase("subAlert")){
   				 	 alertList = govtAlertSubTaskDAO.getDistrictOfficerSubTaskAlerts(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,"status");
    			}
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
    			
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(locIdThenStatusIdThenAlertCount != null && locIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
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
    			return returnList;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}
        /*
         * Swadhin K Lenka
         */
        public List<AlertCoreDashBoardVO> prepareResultForState(List<Object[]> alertList,List<AlertCoreDashBoardVO> returnList,String sortingType,String order){
        	try{
        		
        		Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();    
    			
    			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> statusIdThenAlertCount = null;
    			
    			
    			Set<Long> deptScopeIds = new HashSet<Long>();
    			Set<Long> statusIds = new HashSet<Long>();
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
    				}
    			}
    			
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					statusIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
    				}
    			}
    			List<Object[]> statusIdDtlsList = null;
    			if(statusIds != null && statusIds.size() > 0){
    			 statusIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));
        		}
    			
    			List<Object[]> deptScopeIdDtlsList = null;
    			if(deptScopeIds != null && deptScopeIds.size() >0){
    				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
    			}
    			
    			
    			
    			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
    				for(Object[] param : deptScopeIdDtlsList){
    					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
    				}  
    			}
    			if(statusIdDtlsList != null && statusIdDtlsList.size() > 0){
    				for(Object[] param : statusIdDtlsList){
        				statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
        				statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
    				}  
    			}
    			
    			
    			
        		if(alertList != null && alertList.size() > 0){
        			for(Object[] param : alertList){
        				statusIdThenAlertCount = lvlIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[3]));
    					if(statusIdThenAlertCount == null){
    						statusIdThenAlertCount = new LinkedHashMap<Long,Long>();
    						lvlIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCount);
    					}
    					statusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
    				}
        		}
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
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
              public List<IdNameVO> getDeptListForMultiLvl(Long userId){
                  try{
                    List<Object[]> deptList= alertAssignedOfficerNewDAO.getDeptList(userId);
                    List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>(); 
                    List<Long> deptIds = new ArrayList<Long>();
                    IdNameVO idNameVO = null;
                    if(deptList != null && deptList.size() > 0){
                      for(Object[] param : deptList){
                        deptIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
                        idNameVO = new IdNameVO();
                        idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
                        idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
                        idNameVOs.add(idNameVO);
                      }
                    }
                    Map<Long,ArrayList<Long>> deptIdAndLocationScopeIds = new LinkedHashMap<Long,ArrayList<Long>>();
                    ArrayList<Long> locationScopeIds = null;
                    if(deptIds != null && deptIds.size() > 0){
                      List<Object[]> list = govtDepartmentScopeLevelDAO.getAllScopesOfAllDeptInAscOrder(deptIds);
                      if(list != null && list.size() > 0){
                        for(Object[] param : list){
                          locationScopeIds = deptIdAndLocationScopeIds.get(commonMethodsUtilService.getLongValueForObject(param[0]));
                          if(locationScopeIds == null){
                            locationScopeIds = new ArrayList<Long>();
                            deptIdAndLocationScopeIds.put(commonMethodsUtilService.getLongValueForObject(param[0]),locationScopeIds);
                          }
                          locationScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
                        }
                      }
                    }
                    
                    if(idNameVOs != null && idNameVOs.size() > 0){
                      for(IdNameVO param : idNameVOs){
                        if(deptIdAndLocationScopeIds != null && deptIdAndLocationScopeIds.get(param.getId()) != null){
                          if(deptIdAndLocationScopeIds.get(param.getId()).size() > 4){//change for no of level here
                            param.setLocationScopeIds(deptIdAndLocationScopeIds.get(param.getId()).subList(0, 4));//change for no of level here
                          }else{
                            param.setLocationScopeIds(deptIdAndLocationScopeIds.get(param.getId()));  
                          }
                        }  
                      }
                    }
                    return idNameVOs;
                  }catch(Exception e){
                    e.printStackTrace();
                  }
                  return null;
                }

           public List<AlertCoreDashBoardVO> getDistrictOfficerAlertDetails(Long govtDeptGovtOffrId,Long govtOffrcrId,String countType,String alertType, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList){
        		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
        		try {
        			List<Long> alertIdList = null;
        			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>();
        			govtDepDesigOffcrIds.add(govtDeptGovtOffrId);
        			List<Long> govtOffcrIds = new ArrayList<Long>();
        			govtOffcrIds.add(govtOffrcrId);
        			if(alertType != null && alertType.equalsIgnoreCase("alert")){
        				 alertIdList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,countType,printIdsList,electronicIdsList,calCntrIdList);
        			}else {
       				 	alertIdList = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,countType,alertType,printIdsList,electronicIdsList,calCntrIdList);
        			}
        			if(alertIdList != null && alertIdList.size() > 0){
        				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
        				setAlertDtls(finalVoList, list); 
        			}
        			setSubListCount(finalVoList, alertIdList);
        		} catch (Exception e) {
        			LOG.error(" Exception Occured in getDistrictOfficerAlertDetails() method, Exception - ",e);
        		}		
        		return finalVoList;
        	}
           public ResultStatus senedSMSTOAlertAssignedOfficer(Long designationId,Long govtOfficerId,String mobileNo,Long alertId){
              	ResultStatus rs = new ResultStatus();
              	try {
              		GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
              		
              		//get asigned officer dept, alert title
              		//0-title,1-deptId,2-deptName
              		Object[] objArr = alertDAO.getAlertDetailsForSMS(alertId);
              		if(objArr != null){
              			String message = "Alert is assigned to you,Please follow up and resolve.\nTitle : "+objArr[0].toString()+" \nDept"+objArr[2].toString();
              			govtSMSAPIService.senedSMSForGovtAlert(mobileNo,message);
              		}
              		
              		//get parent designation Id
              		List<Long> parentDesigIds = govtDepartmentDesignationHierarchyDAO.getParentDepartment(designationId);
              		if(parentDesigIds != null && parentDesigIds.size() > 0){
              			//get high level officer mobile nums
              			List<String> mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getHigherOfficerMobileNums(parentDesigIds);
              			
              			if(mobilenums != null && mobilenums.size() > 0){
              				String message = "Alert is assigned to "+objArr[2].toString()+" - "+govtDepartmentDesignationDAO.getDepartmentDetails(designationId)+" - "+ mobileNo+".\n Please follow up.";
              				String mobileNums = "";
              				for (String string : mobilenums) {
              					mobileNums = mobileNums.equalsIgnoreCase("")?string:mobileNums+","+string;
              				}
              				govtSMSAPIService.senedSMSForGovtAlert(mobileNums,message);
              			}
              		}
              		
              		
              		rs.setExceptionMsg("success");
              	} catch (Exception e) {
              		rs.setExceptionMsg("failure");
              		LOG.error("Error occured senedSMSTOAlertAssignedOfficer() method of AlertManagementSystemService{}");
              	}
              	return rs;
              }    
              
              public List<AlertTrackingVO> getAlertStatusHistory(Long alertId){
              	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
              	try {
              		//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
       				List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getAlertStatusHistory(alertId);
       				
       				if(objList != null && objList.size() > 0){
       					for (Object[] objects : objList) {
       						AlertTrackingVO vo = new AlertTrackingVO();
       						vo.setStatus(objects[0] != null ? objects[0].toString():"");
       						vo.setComment(objects[1] != null ? objects[1].toString():"");
       						vo.setDate(objects[2] != null ? objects[2].toString():"");
       						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[4] != null ? objects[4].toString():"");
       						vo.setDesignation(objects[5] != null ? objects[5].toString():""+" - "+objects[6] != null ? objects[6].toString():"");
       						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
       						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
       						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
       						voList.add(vo);
       					}
       				}
       			} catch (Exception e) {
       				LOG.error("Error occured getAlertStatusHistory() method of AlertManagementSystemService{}");
       			}
              	return voList;
              }
              
              
              public List<AlertCoreDashBoardVO> getDistrictOfficerScopesWiseAlerts(String fromDateStr, String toDateStr, Long stateId, Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType){
          		try{
          			
          			Date fromDate = null;
        			Date toDate = null;
        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
        				fromDate = sdf.parse(fromDateStr);
        				toDate = sdf.parse(toDateStr);
        			}
        			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
        			
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
          			
          			List<Object[]> alertList =null;
          			if(alertType != null && alertType.equalsIgnoreCase("alert")){
          				 alertList = alertAssignedOfficerNewDAO.getDistrictOfficerScopesWiseAlerts(fromDate,toDate,stateId,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
          			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
         				 	 alertList = govtAlertSubTaskDAO.getDistrictOfficerSubTaskAlerts(fromDate,toDate,stateId,null,null,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,"scopes");
          			}
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
        			
        			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
        			if(locIdThenStatusIdThenAlertCount != null && locIdThenStatusIdThenAlertCount.size() > 0){
        				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenStatusIdThenAlertCount.entrySet()){
        					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
        					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
        					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
        					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
        					Long total = new Long(0L);
        					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
        						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
        							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
        							total = total + outerEntry.getValue().get(boardVO.getId());
        						}
        					}
        					alertCoreDashBoardVO.setTotalCount(total);
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
        			return returnList;
          			
          		}catch(Exception e){
          			e.printStackTrace();
          		}
          		return null;
          	}
           public List<GovtDepartmentVO> getAssignedOfficersDetails(Long alertId){
       		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
       		try {
       			List<Object[]> list = alertAssignedOfficerNewDAO.getAssignedOfficersDetails(alertId);
       			if(list != null && !list.isEmpty()){
       				for (Object[] obj : list) {
       					GovtDepartmentVO vo = new GovtDepartmentVO();
       					
       					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
       					vo.setName(obj[1] != null ? obj[1].toString():"");
       					vo.setDepartment(obj[2] != null ? obj[2].toString():"");
       					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
       					vo.setDesignation(obj[4] != null ? obj[4].toString():"");
       					
       					returnList.add(vo);
       				}
       			}
       		} catch (Exception e) {
       			LOG.error("Error occured getAssignedOfficersDetails() method of CccDashboardService",e);
       		}
       		return returnList;
       	}
            
            public List<IdNameVO> getDepartmentSubLevels(Long departmentId,Long parentLevelId){
            	List<IdNameVO> returnList = new ArrayList<IdNameVO>();
            	try {
            		
            		List<Object[]> objList = govtDepartmentScopeLevelDAO.getDepartmentSubLevels(departmentId,parentLevelId);
    	        		
    	        	if(objList !=null && objList.size()>0){
    	        		for (Object[] obj : objList) {
    	        			IdNameVO VO = new IdNameVO();
    						VO.setId((Long)obj[0]);
    						VO.setName(obj[1] !=null ? obj[1].toString():"");
    						returnList.add(VO);
    					}
    	        	}
    			
    			} catch (Exception e) {
    				e.printStackTrace();
    				LOG.error("Error occured getDepartmentSubLevels() method of AlertManagementSystemService class ");
    			}
            	return returnList;        	
            }
            
            public List<IdNameVO> getChildLevelValuesForSubTask(Long departmentId,Long parentLevelId,List<Long> parentLevelValues,Long levelId){
            	List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
            	try {
            		
            		Map<Long,IdNameVO> levelMap = new HashMap<Long, IdNameVO>();
            		
            		//ScopeId,levelName
            		List<Object[]> subLevelObj = govtDepartmentScopeLevelDAO.getParentLevelsOfLevel(departmentId,levelId);
            		
            		if(subLevelObj !=null && subLevelObj.size()>0){
            				Set<Long> subLevelIds = new HashSet<Long>();
            				for (Object[] param : subLevelObj) {		
            					if(parentLevelId <= (Long)param[0]){
            						IdNameVO vo = new IdNameVO();						
            						vo.setId((Long)param[0]);
            						vo.setName(param[1] !=null ? param[1].toString():"");	
            						
            						levelMap.put((Long)param[0], vo);
            						
            						subLevelIds.add((Long)param[0]);
            					}
            						
            				}
            				
            				List<Object[]> levelValueObj = govtDepartmentWorkLocationDAO.getParentLevelValuesListInfo(parentLevelValues);
            				
            				List<IdNameVO> parentList = new ArrayList<IdNameVO>(); 
            				
            				parentList = setParentListInfo(parentList,levelValueObj);
            				
            				
            				//0.levelId,1.workLocationId,2.LocationName
            				List<Object[]> objValueList = govtDepartmentWorkLocationDAO.getLevelWiseInfo(departmentId,subLevelIds);
            				
            				if(objValueList !=null && objValueList.size()>0){
            					setLocationValuesToLevelMap(objValueList,levelMap,parentList,parentLevelId);
            				}
            				
            				if(levelMap !=null && levelMap.size()>0){
            					finalList.addAll(levelMap.values());
            				}
            				
            		}
            		
            		
    			} catch (Exception e) {
    				LOG.error("Error occured getChildLevelValuesForSubTask() method of CccDashboardService{}");
    			}
            	
            	return finalList;
            	
            }
            
            public List<IdNameVO> setParentListInfo(List<IdNameVO> parentList,List<Object[]> levelValueObj ){
            	        	
            	try {
    				
            		if(levelValueObj !=null && levelValueObj.size()>0){
            			for (Object[] param : levelValueObj) {
    						
            				IdNameVO Vo = new IdNameVO();
            				Vo.setId((Long)param[0]);//workLocationId
            				Vo.setName(param[1] !=null ? param[1].toString():"");
            				
            				parentList.add(Vo);
    					}
            		}
            		
    			} catch (Exception e) {
    				LOG.error("Error occured setParentListInfo() method of CccDashboardService{}");
    			}
            	return parentList;
            } 
            
            
            public void setLocationValuesToLevelMap(List<Object[]> objList,Map<Long,IdNameVO> levelMap,List<IdNameVO> parentList,
            		Long parentLevelId){
        		try {
        			
        			if(objList !=null && objList.size()>0){
        				for (Object[] obj : objList) {    					
        					if(obj[0] !=null){    						
        						if(parentLevelId.equals((Long)obj[0])){
        							
        							IdNameVO mainVo = levelMap.get(obj[0] !=null ? (Long)obj[0]:0l);
        							if(mainVo == null){
        								mainVo = new IdNameVO();    								
        								mainVo.setId((Long)obj[0]);
        								levelMap.put((Long)obj[0], mainVo);
        							}
        							
        							if(obj[1] !=null){    								
        								IdNameVO subVo = getGovtSubLevelMatchedVo(parentList,(Long)obj[1]);    								
        								if(subVo !=null){
        									mainVo.getIdnameList().add(subVo);
        								}
        							}    							
        						}
        					}
        				}
        			}
        			
        		} catch (Exception e) {
        			LOG.error("Error occured setLocationValuesToLevelMap() method of AlertManagementSystemService",e);
        		}
        	}
            public List<AlertVO> getAllDivisionDetails(Long districtId){
         List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
     		try {
	    		List<Object[]> divisionList = govtDepartmentWorkLocationDAO.getAllDivisionDetails(districtId);
	    		if (divisionList != null && divisionList.size() > 0) {
           			for (Object[] objects : divisionList) {
           				 AlertVO alertVO = new AlertVO();
           				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
           				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
           				 finalVoList.add(alertVO);
           				}					
   				}				
   			} catch (Exception e) {
   				LOG.error(" Exception Occured in getAllDivisionDetails() method, Exception - ",e);
   			}    	
           	return finalVoList;
           }
           public List<AlertVO> getAllSubDivisionDetails(Long divisionId){
               List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
              	try {
              		List<Object[]> subDivisionList = govtDepartmentWorkLocationDAO.getAllSubDivisionDetails(divisionId);
              		if (subDivisionList != null && subDivisionList.size() > 0) {
              			for (Object[] objects : subDivisionList) {
              				 AlertVO alertVO = new AlertVO();
              				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
              				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
              				 finalVoList.add(alertVO);
              				}					
      				}				
      			} catch (Exception e) {
      				LOG.error(" Exception Occured in getAllSubDivisionDetails() method, Exception - ",e);
      			}    	
              	return finalVoList;
              }
           
             
         	public ResultStatus updateSubTaskComment(final Long subTaskId,final String comment,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					AlertDepartmentCommentNew adcn = new AlertDepartmentCommentNew();
        					adcn.setComment(comment);
        					adcn.setInsertedBy(userId);
        					adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        					adcn = alertDepartmentCommentNewDAO.save(adcn);

        					GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        					
        					if(gast != null){
        						//save record in tracking
        						saveRecordIntoSubTaskTracking(gast,userId,adcn.getAlertDepartmentCommentId()+"",7l);
        					}
        					rs.setExceptionMsg("success");
        				}
        			});
        			
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskComment");
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskStatusComment(final Long subTaskId,final Long statusId,final String comment,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					
        					GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        					if(gast != null && statusId != null && statusId.longValue()>0L){
        						gast.setAlertSubTaskStatusId(statusId);
        						gast.setUpdatedBy(userId);
        						gast.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
        						govtAlertSubTaskDAO.save(gast);
        					}
        					
        					AlertDepartmentCommentNew adcn = null;
        					if(comment != null && !comment.trim().isEmpty()){
        						adcn = new AlertDepartmentCommentNew();
        						adcn.setComment(comment);
        						adcn.setInsertedBy(userId);
        						adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        						adcn = alertDepartmentCommentNewDAO.save(adcn);
        					}
        					
        					GovtOfficerSubTaskTracking gostt = new GovtOfficerSubTaskTracking();
        					gostt.setAlertAssignedOfficerId(gast.getAlertAssignedOfficerId());
        					gostt.setGovtAlertSubTaskId(gast.getGovtAlertSubTaskId());
        					gostt.setGovtAlertActionTypeId(6l);
        					gostt.setAlertSubTaskStatusId(statusId);
        					gostt.setAlertSeverityId(gostt.getAlertSeverityId());
        					
        					if(adcn != null)
        						gostt.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
        					
        					gostt.setInsertedById(userId);
        					gostt.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        					gostt.setIsDeleted("N");
        					govtOfficerSubTaskTrackingDAO.save(gostt);
        					
        					rs.setExceptionMsg("success");
        				}
        			});	
        			
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception Occured in updateAlertStatusComment of  updateSubTaskStatusComment() ", e);
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskPriority(final Long subTaskId,final Long priorityId,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					Integer count = govtAlertSubTaskDAO.updateSubTaskPriority(subTaskId,priorityId,userId,dateUtilService.getCurrentDateAndTime());
        					if(count != null && count > 0){
        						//save record in tracking
        						saveRecordIntoSubTaskTracking(govtAlertSubTaskDAO.get(subTaskId),userId,priorityId+"",5l);
        						
        						rs.setExceptionMsg("success");
        					}
        				}
        			});
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskPriority");
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskDueDate(final Long subTaskId,final String dueDate,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					Integer count;
        					try {
        						count = govtAlertSubTaskDAO.updateSubTaskDueDate(subTaskId,new SimpleDateFormat("dd/MM/yyyy").parse(dueDate),userId,dateUtilService.getCurrentDateAndTime());
        						if(count != null && count > 0){
        							//save record in tracking
        							saveRecordIntoSubTaskTracking(govtAlertSubTaskDAO.get(subTaskId),userId,dueDate,4l);
        							
        							rs.setExceptionMsg("success");
        						}
        					} catch (ParseException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        				}
        			});
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskPriority");
        		}
        		return rs;
        	}
        	
        	public void saveRecordIntoSubTaskTracking(GovtAlertSubTask gast,Long userId,String id,Long actionType){
        		try {
        			GovtOfficerSubTaskTracking gostt = new GovtOfficerSubTaskTracking();
        			gostt.setAlertAssignedOfficerId(gast.getAlertAssignedOfficerId());
        			gostt.setGovtAlertSubTaskId(gast.getGovtAlertSubTaskId());
        			gostt.setGovtAlertActionTypeId(actionType);
        			gostt.setAlertSubTaskStatusId(gast.getAlertSubTaskStatusId());
        			gostt.setAlertSeverityId(gostt.getAlertSeverityId());
        			
        			if(actionType == 7l)
        				gostt.setAlertDepartmentCommentId(Long.parseLong(id));
        			if(actionType == 5l)
        				gostt.setAlertSeverityId(Long.parseLong(id));
        			if(actionType == 4l)
        				gostt.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(id));
        			if(actionType == 3l)
        				gostt.setAlertDepartmentDocumentId(Long.parseLong(id));
        			
        			gostt.setInsertedById(userId);
        			gostt.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        			gostt.setIsDeleted("N");
        			govtOfficerSubTaskTrackingDAO.save(gostt);
        		} catch (Exception e) {
        			LOG.error("Exception raised at saveRecordIntoSubTaskTracking");
        		}
        	}
        	public ResultStatus uploadDocumentsForSubTask(final Map<File, String> mapfiles,final Long subTaskId,final Long userId){

        		final ResultStatus resultStatus = new ResultStatus();
        		try {
        			
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        		String folderName = folderCreationForAlertsAttachments();
        		//CustomReportFile customReportFile = null;
        		//CustomReport customReport = null;
        		
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
        			GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        			 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
        				 str = new StringBuilder();
        				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
        				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
        				 pathBuilder.append("alerts_attachments/"+randomNumber+"."+entry.getValue());
        				// pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
        				// .append(entry.getValue());
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
        					saveRecordIntoSubTaskTracking(gast,userId,addn.getAlertDepartmentDocumentId()+"",3l);
        					
        			 }
        		 }
        		
        		 resultStatus.setResultCode(0);
        		// resultStatus.setResultState(customReportFile.getCustomReportFileId());
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
        	
        	public List<AlertTrackingVO> getSubTaskDetails(Long alertId){
        		List<AlertTrackingVO> returnList = null;
        		try {
        			List<Long> alertIds = new ArrayList<Long>(0);
        			alertIds.add(alertId);
					List<Long> subTasksAlertIdsList = govtAlertSubTaskDAO.getSubTasksIdsList(alertIds);
					if(commonMethodsUtilService.isListOrSetValid(subTasksAlertIdsList)){
						returnList = new ArrayList<AlertTrackingVO>(0);
						for (Long subtaskId : subTasksAlertIdsList) {
							returnList.addAll(viewSubTaskHistory(subtaskId));							
						}
					}
				} catch (Exception e) {
					LOG.error(" Exception Occured in getSubTaskDetails() method, Exception - ",e);
				}        		
        		return returnList;
        	}
        	public List<AlertTrackingVO> viewSubTaskHistory(Long subTaskId){
        		List<AlertTrackingVO> finalList = new ArrayList<AlertTrackingVO>(0);
        		SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        		SimpleDateFormat dateSdf = new SimpleDateFormat("dd-MM-yyyy");
        		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
        		try {
        			
        			List<GovtOfficerSubTaskTracking> qryRstList = govtOfficerSubTaskTrackingDAO.getModelForSubTask(subTaskId);
        			
        			if(qryRstList != null && qryRstList.size() > 0){
        				
        				GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        				
        				for (GovtOfficerSubTaskTracking govtOfficerSubTaskTracking : qryRstList) {
        					
        					String userName = govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getOfficerName()+" - "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getMobileNo();
        					String designation = govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName()
        							+" & "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName();
        					
        					AlertTrackingVO matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					if(matchedDateVO == null){
        						matchedDateVO = new AlertTrackingVO();
        						matchedDateVO.setUserName(userName);
        						matchedDateVO.setDesignation(designation);
        						matchedDateVO.setTitle(gast.getTitle().toString());
        						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
        						matchedDateVO.setDate(dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        						finalList.add(matchedDateVO);
        					}
        					
        					matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					AlertTrackingVO matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					if(matchedTimeVO == null){
        						matchedTimeVO = new AlertTrackingVO();
        						matchedTimeVO.setDate(timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        						matchedDateVO.setTitle(gast.getTitle().toString());
        						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
        						matchedDateVO.setUserName(userName);
        						matchedDateVO.setDesignation(designation);
        						matchedDateVO.getTimeList().add(matchedTimeVO);
        					}
        					
        					matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					if(govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentDocument() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentDocument().getDocument());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getAttachementsList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertDepartmentCommentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentCommentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentComment() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentComment().getComment());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getCommentList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getDueDate() != null && !govtOfficerSubTaskTracking.getDueDate().toString().trim().isEmpty()){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getDueDate().toString());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getDueDateList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() > 0l && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSubTaskStatus().getStatus());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getStatusList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertSeverityId() != null && govtOfficerSubTaskTracking.getAlertSeverityId() > 0l && govtOfficerSubTaskTracking.getAlertSeverity() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSeverity().getSeverity());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
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
        	public List<AlertTrackingVO> getSubTaskStatusHistory(Long subTaskId){
        		List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
        		try {
        			//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
        			List<Object[]> objList = govtOfficerSubTaskTrackingDAO.getSubTaskStatusHistory(subTaskId);
        			
        			if(objList != null && objList.size() > 0){
        				for (Object[] objects : objList) {
        					AlertTrackingVO vo = new AlertTrackingVO();
        					vo.setStatus(objects[0] != null ? objects[0].toString():"");
        					vo.setComment(objects[1] != null ? objects[1].toString():"");
        					vo.setDate(objects[2] != null ? objects[2].toString():"");
        					vo.setUserName((objects[3] != null ? objects[3].toString():"")+""+(objects[4] != null ? " - "+objects[4].toString():""));
        					vo.setDesignation((objects[5] != null ? objects[5].toString():"")+""+(objects[6] != null ? " - "+objects[6].toString():""));
        					voList.add(vo);
        				}
        			}
        		} catch (Exception e) {
        			LOG.error("Error occured getSubTaskStatusHistory() method of AlertManagementSystemService{}");
        		}
        		return voList;
        	} 
        	
        public List<AlertTrackingVO> getSubTaskInfoForAlert(Long alertId){
        	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0); 
        	try {
        		List<Object[]> objList = govtAlertSubTaskDAO.getSubTaskInfoForAlert(alertId);
        		Map<Long,AlertTrackingVO> tempMap = new LinkedHashMap<Long, AlertTrackingVO>(0);
        		
        		if(objList != null && objList.size() > 0){
        			List<Long> subTaskIds = new ArrayList<Long>(0);
        			for (Object[] obj : objList) {
        				
        				AlertTrackingVO vo = new AlertTrackingVO();
        				vo.setUserId(commonMethodsUtilService.getLongValueForObject(obj[0]));
        				vo.setUserName(commonMethodsUtilService.getStringValueForObject(obj[1]));
        				
        				vo.setAlertId(commonMethodsUtilService.getLongValueForObject(obj[0]));
        				vo.setTitle(commonMethodsUtilService.getStringValueForObject(obj[1]));
        				vo.setDate(commonMethodsUtilService.getStringValueForObject(obj[3]));
        				tempMap.put((Long)obj[0], vo);
        				subTaskIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					}
        			
        			List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
        			if(officersList != null && officersList.size() > 0){
        				for (Object[] objects : officersList) {
							if(tempMap.get((Long)objects[0]) != null){
								AlertTrackingVO vo  = tempMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
								if(vo != null){
									if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty())
										vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
											 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3])));
									vo.setUserName(commonMethodsUtilService.getStringValueForObject(objects[4]));
									vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[5]));
									vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[6]));
									vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[7]));
									vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
								}
							}
						}
        			}
        			
        			
        			List<Object[]> objectsList = govtOfficerSubTaskTrackingDAO.getCommentsForSubTasks(subTaskIds);
        			if(objectsList != null && objectsList.size() > 0){
        				for (Object[] objects : objectsList) {
							if(tempMap.get((Long)objects[0]) != null){
								tempMap.get((Long)objects[0]).setCount((Long)objects[1]);
							}
						}
        			}
        			
        		}
        		
        		if(tempMap != null && tempMap.size() > 0){
        			voList.addAll(tempMap.values());
        		}
			} catch (Exception e) {
				LOG.error("Error occured getSubTaskInfoForAlert() method of AlertManagementSystemService");
			}	
        	return voList;
        } 
        	
        public List<AlertTrackingVO> getCommentsForAlert(Long alertId){
        	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
        	try {
				List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getCommentsForAlert(alertId);
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						AlertTrackingVO vo = new AlertTrackingVO();
   						vo.setComment(objects[1] != null ? objects[1].toString():"");
   						vo.setDate(objects[2] != null ? objects[2].toString():"");
   						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[3] != null ? objects[1].toString():"");
   						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
   						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
   						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
   						voList.add(vo);
   						
						//voList.add(vo);
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured getCommentsForAlert() method of AlertManagementSystemService");
			}
        	return voList;
        }
         /*
      	 * Swadhin K Lenka
      	 * overview  click
      	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
      	 */
      	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountOnClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long locationId, Long childLocationId,String category){
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
      			}/*else{
      				electronicIdList.add(0L);
      				printIdList.add(0L);
      			}*/
      			
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
    			List<Long> alertList = null;
    			if(category != null && category.trim().isEmpty() && !category.trim().equalsIgnoreCase("overview")){
    				alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountOnClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeIdList,parentGovtDepartmentScopeId,locationId,childLocationId);

    			}else{
    				alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountForStatusForClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeIdList,parentGovtDepartmentScopeId,locationId,childLocationId);

    			}
      			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
      			if(alertList != null && alertList.size() > 0){
    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
    				setAlertDtls(alertCoreDashBoardVOs, list); 
    			}
      			return alertCoreDashBoardVOs;    
      			
      		}catch(Exception e){
      			e.printStackTrace();    
      		}
      		return null;
      	}
      	public List<IdNameVO> getStatusCompletionInfoForSubTask(Long alertId,Long subTaskId,Long userId){
      		try{
      			List<IdNameVO> finalList = new ArrayList<IdNameVO>();
      			
      			GovtAlertSubTask govtAlertSubTask = govtAlertSubTaskDAO.get(subTaskId);
      			Long currentStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
      			//get all govt dept desig off ids
        		List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
        		
        		//get govt dept desig off id by alertId
        		Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
        		
        		//to check whether the logedin user is owner of the subtask or not.
        		
        		Long govtDeptDesigOfficerId2 = govtAlertSubTaskDAO.getGovtDeptDesigOfficerIdBySubTaskId(subTaskId);
        		
        		String userType = "";
        		String isAccess = "";
        		if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
        			userType ="alertOwnner";
        			if(govtAlertSubTask.getAlertSubTaskStatusId() != null && govtAlertSubTask.getAlertSubTaskStatusId().longValue() == 3L){
        				isAccess = "true";
        			}
        			
        			List<AlertSubTaskStatus> objList = alertSubTaskStatusDAO.getAll();
        			if(objList != null && objList.size() > 0){
        				for (AlertSubTaskStatus param : objList) {
        					if(currentStatusId.longValue() > param.getAlertSubTaskStatusId().longValue()){
	        					IdNameVO VO = new IdNameVO();
	        					VO.setId(param.getAlertSubTaskStatusId());
	                			VO.setName(param.getStatus());
	                			finalList.add(VO);
        					}
    					}
        			}
        			
        		}else if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId2 != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId2)){
        			userType ="subTaskOwnner";
        		}else{
        			userType ="other";
        		}
        		Long alertSubStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
        		
        		
    			
    			if(finalList != null && finalList.size() > 0){
    				IdNameVO vo = finalList.get(0);
    				vo.setDeptName(govtAlertSubTask.getAlertAssignedOfficer().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName());
    				vo.setAssignedByOfficerStr(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getOfficerName());
    				vo.setAssignedOfficerStr(govtAlertSubTask.getSubTaskGovtOfficer().getOfficerName());
    				vo.setMobileNo(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo() != null ?govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo():"");
    				vo.setDesignation(govtAlertSubTask.getAlertAssignedOfficer().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName());
    				vo.setAlertId(subTaskId);
    				vo.setDescription(govtAlertSubTask.getAlert().getDescription());
    				vo.setTitle(govtAlertSubTask.getTitle());
    				vo.setDateStr(govtAlertSubTask.getCreatedTime() != null ? govtAlertSubTask.getCreatedTime().toString().substring(0, 10):"");
    				vo.setDueDateStr(govtAlertSubTask.getDueDate() != null ? govtAlertSubTask.getDueDate().toString().substring(0, 10):"");
    				vo.setUserStatus(userType);
    				vo.setIsAccess(isAccess);
    				vo.setStatus(govtAlertSubTask.getAlertSubTaskStatus().getStatus());
    				vo.setStatusId(commonMethodsUtilService.getLongValueForObject(alertSubStatusId));
    				vo.setCategoryId(govtAlertSubTask.getAlert().getAlertCategoryId());
    				
    				
    				List<Long> subTaskIds = new ArrayList<Long>(0);
    				subTaskIds.add(vo.getAlertId());
    				List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
        			if(officersList != null && officersList.size() > 0){
	    				for (Object[] objects : officersList) {
								if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty()){
									vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
										 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3])));
								}
								
								if(!commonMethodsUtilService.getStringValueForObject(objects[8]).isEmpty()){
									vo.getSubList().add(commonMethodsUtilService.getStringValueForObject(objects[8]));
								}

							}
					}
        			
        			
    			}
    			return 	finalList;
      		}catch(Exception e){
      			e.printStackTrace();
      		}
      		return null;
      	}
      	public List<IdNameVO>  getStatusCompletionInfo(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId){
        	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
        	try {
        		
        		Alert alert  = alertDAO.get(alertId);
        		
        		String userType = null;
        		//whether this alert is belongs to same logedin user or not.
        		//get all govt dept desig off ids
        		List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
        		
        		//get govt dept desig off id by alertId
        		Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
        		
        		//whether this alert is belongs to just subordinate or not.
        		
        		//get all govt dept desig ids
        		List<Long> govtDeptDesigIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigIdListByUserId(userId);
        		//get govt dept desig id by alertId
        		Long govtDeptDesigId2 = alertAssignedOfficerNewDAO.getGovtDeptDesigIdListByUserId(alertId); 
        		
        		//now check
        		List<Object[]> list2 = null;
        		if(govtDeptDesigIdList != null && govtDeptDesigIdList.size() > 0 && govtDeptDesigId2 != null && govtDeptDesigId2.longValue() > 0L){
        			list2 = govtDepartmentDesignationHierarchyDAO.getChildDesigData(govtDeptDesigIdList,govtDeptDesigId2);
        		}
        		
        		//to check same level designation.
        		//by alert id take scope.
        		Long govtDeptScopeIdForAlert = alertAssignedOfficerNewDAO.getGovtDeptScopeIdForAlert(alertId);
        		//by user Id take scope.
        		List<Long> govtDeptScopeIdsForUserId = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptScopeIdsForUserId(userId);
        		
        		//to wheck whether he/she is an admin or not.
        		Long userCount = govtDepartmentDesignationOfficerNewDAO.getUserIdCount(userId);
        		String userStatus = "";
        		if(userCount != null && userCount.longValue() > 0L){
        			userStatus = "admin";
        		}else{
        			userStatus = "officer";
        		}
        		if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
        			userType ="own";
        			
        			List<Object[]> objList = alertDepartmentStatusDAO.getAlertGovtDepartmentStatus(alert.getGovtDepartmentId());
        			if(objList != null && objList.size() > 0){
        				for (Object[] objects : objList) {
        					IdNameVO VO = new IdNameVO();
        					VO.setId((Long)objects[0]);
                			VO.setName(objects[1].toString());
                			VO.setDateStr(objects[2] != null ? objects[2].toString():"");
                			finalList.add(VO);
						}
        			}
        			
        		}else if(list2 != null && list2.size() > 0){ 
        			
        			userType = "subUser";
        			
        			if(alert.getAlertStatusId().longValue() == 4l || alert.getAlertStatusId().longValue() == 11l || alert.getAlertStatusId().longValue() == 12l){//Completed Status  
        				//userType = "subUserStatus";
        				List<Object[]> listObj = alertStatusDAO.getAlertStatusInfoForReOpen();
            			if(listObj !=null && listObj.size()>0){
            				for (Object[] objects : listObj) {
    							IdNameVO vo = new IdNameVO();
    							vo.setId((Long)objects[0]);
    							vo.setName(objects[1].toString());
    							vo.setDateStr(objects[2] != null? objects[2].toString():null);
    							finalList.add(vo);
    						}
            			}
        			}else{
        				IdNameVO vo = new IdNameVO();        			
            			finalList.add(vo);            			
        			}  
        			
        		}else if(govtDeptScopeIdsForUserId != null && govtDeptScopeIdsForUserId.size() > 0 && govtDeptScopeIdForAlert != null && govtDeptScopeIdsForUserId.contains(govtDeptScopeIdForAlert)){
        			userType = "same";
        			IdNameVO vo = new IdNameVO();          			
        			finalList.add(vo);
        		}
        		else{
        			userType = "other";        			
        			IdNameVO vo = new IdNameVO();        			
        			finalList.add(vo);
        		}
        		
        		
        		if(finalList != null && finalList.size() > 0){
        			finalList.get(0).setApplicationStatus(userType+" - "+alert.getAlertStatusId());
        			finalList.get(0).setUserStatus(userStatus);
        		}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Error occured getStatusCompletionInfo() method of AlertManagementSystemService",e);
			}
        	return finalList;
        }
      	      	
      	public AlertVO getSubTaskFullDetails(Long subTaskId){
      		AlertVO finalVO = new AlertVO();
      		try {
      			GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
      			if(gast != null){
      				finalVO.setSubTaskId(gast.getGovtAlertSubTaskId());
      				finalVO.setAlertId(gast.getAlertId());
      				finalVO.setTitle(gast.getTitle());
      				finalVO.setDesc(gast.getDescription() != null ? gast.getDescription():"");
      				finalVO.setSeverity(gast.getAlertSeverityId() != null ? gast.getAlertSeverityId() : null);
      				finalVO.setDueDate(gast.getCreatedTime() != null ? gast.getDueDate().toString():"");
      				finalVO.setStatusId(gast.getAlertSubTaskStatusId());
      				finalVO.setDate1(new SimpleDateFormat("dd/MM/yyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse((gast.getCreatedTime().toString()))));
      				if(gast.getAlert().getAlertCategoryId() == 2l)
      					finalVO.setImageUrl(gast.getAlert().getImageUrl());
      				
      				//get sub task comment details
      				List<Object[]> objList = govtOfficerSubTaskTrackingDAO.getCommentDetialsForSubTasks(subTaskId);
      				if(objList != null && objList.size() > 0){
      					for (Object[] objects : objList) {
							IdNameVO voIn = new IdNameVO();
							voIn.setId((Long)objects[0]);
							voIn.setName(objects[1].toString());
							voIn.setDateStr(objects[3].toString());
							finalVO.getIdNamesList().add(voIn);
						}
      				}
      				
      				
      			}
			} catch (Exception e) {
				LOG.error("Error occured getSubTaskFullDetails() method of AlertManagementSystemService",e);
			}
      		return finalVO;
      	}
      	
      	public List<KeyValueVO> getDocumentsForAlert(Long alertId){
      		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
      		try {
				List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getDocumentsForAlert(alertId);
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						KeyValueVO vo = new KeyValueVO();
						vo.setId((Long)objects[0]);
						vo.setName(objects[1].toString());
						voList.add(vo);
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured getDocumentsForAlert() method of AlertManagementSystemService",e);
			}
      		return voList;
      	}
      	
      	//district officer graphical click click
     	public List<AlertCoreDashBoardVO> getDistrictLevelWiseClick(String fromDateStr, String toDateStr, Long stateId, 
				                             List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				                            Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
				                          Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String group,String searchType,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIdList,List<Long> sublevels){
    		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
    		try {
    			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levlId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levlId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
       			List<Long> deptScopeIdList = new ArrayList<Long>();
       			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
       				for(Object [] param : childDeptScopeIdList){
       					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
       				}
       			}
       			
       			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
    				deptScopeIdList.clear();
					deptScopeIdList.addAll(sublevels);
				}
    			List<Long> alertIds = null;
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
   				if(alertType != null && alertType.equalsIgnoreCase("alert"))
   						alertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues
   								,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,statusId,govtDeprtMentScopeId,calCntrIdList);
   				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
   						alertIds = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationGovtDeptScopeWiseSubTaskCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,
   								parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
       				if(alertType != null && alertType.equalsIgnoreCase("alert"))
       					alertIds = alertAssignedOfficerNewDAO.getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
       					alertIds = govtAlertSubTaskDAO.getDivisionWorkLocationGovtDeptScopeWiseSubTaskDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
       				if(alertType != null && alertType.equalsIgnoreCase("alert"))
       					alertIds = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
       					alertIds = govtAlertSubTaskDAO.getSubDivisionWorkLocationDeptScopeWiseSubTaskCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       			}	
    		 if(alertIds != null && alertIds.size() > 0){
    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
    				setAlertDtls(finalVoList, list); 
    			}	
    		  setSubListCount(finalVoList, alertIds); 
    			
    		} catch (Exception e) {
    			LOG.error(" Exception Occured in getDistrictLevelWiseClick() method, Exception - ",e);
    		}		
    		return finalVoList;
    	}

          /*
  		 * Teja(non-Javadoc)
  		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#stateLevelDeptOfficerStatusOverview()
  		 */
          public List<AlertVO> stateLevelDeptOfficerStatusOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
          		LOG.info("Entered in stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
          		try{
          			
          			Date fromDate = null;
          			Date toDate = null;
          			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
          				fromDate = sdf.parse(fromDateStr);
          				toDate = sdf.parse(toDateStr);
          			}
          			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
          			//get alert status count and and create a map of alertStatusId and its count
          			List<Object[]> totalList = new ArrayList<Object[]>();
          			List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status",null);//for pending status
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
          			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null,null);
          			if(alertCountList2 != null && alertCountList2.size() > 0){
          				totalList.addAll(alertCountList2);
          			}
          			setAlertCount(totalList,finalAlertVOs); 
          			return finalAlertVOs; 
          		}catch(Exception e){
          			e.printStackTrace();
          			LOG.error("Error occured stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
          		}
          		return null;
          	}
          public List<AlertVO> stateLevelDeptOfficerLocationLevelOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
      		LOG.info("Entered in stateLevelDeptOfficerLocationLevelOverview() method of AlertManagementSystemService{}");
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
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
      			List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",null,null,null);
      			setAlertCount(rtrnObjLst,finalAlertVOs);
      			return finalAlertVOs; 
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Error occured stateLevelDeptOfficerLocationLevelOverview() method of AlertManagementSystemService{}");
      		}
      		return null;
      	}
        public DistrictOfficeViewAlertVO getIASOfficerMyAlertsCountView(Long userId){
      		
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
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> myAlertsTodayList = null;
      			List<Object[]> myAlertsCompletedList = null;
      			List<Object[]> myAlertsStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size() >0){
      				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"today");
      				myAlertsCompletedList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"completed");
      				myAlertsStatusList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds);
      			}
      			setIASOfficerStatusWiseCountView(myAlertsTodayList,myAlertsCompletedList,myAlertsStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	}
        public DistrictOfficeViewAlertVO getIASOfficerMySubTasksCountView(Long userId){
      		
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
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> mySubTasksTodayList = null;
      			List<Object[]> mySubTasksCompletedList = null;  
      			List<Object[]> mySubTasksStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0 && govtOffcrIds != null && govtOffcrIds.size() >0){
      				mySubTasksTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCountsView(govtDepDesigOffcrIds, govtOffcrIds,"today");
      				mySubTasksCompletedList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCountsView(govtDepDesigOffcrIds,govtOffcrIds,"completed");
      				mySubTasksStatusList = govtAlertSubTaskDAO.getDistrictOfficerMySubTasksStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds);
      			}
      			setIASOfficerStatusWiseCountView(mySubTasksTodayList,mySubTasksCompletedList,mySubTasksStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	} 
       public DistrictOfficeViewAlertVO getIASOfficerMyAssignedSubTasksCountView(Long userId){
      		
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
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> myAssignedSubTasksTodayList = null;
      			List<Object[]> myAssignedSubTasksCompletedList = null;
      			List<Object[]> myAssignedSubTasksStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() >0 && govtOffcrIds != null && govtOffcrIds.size() > 0){
      				myAssignedSubTasksTodayList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksCountsView(govtDepDesigOffcrIds, govtOffcrIds,"today");
      				myAssignedSubTasksCompletedList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksCountsView(govtDepDesigOffcrIds,govtOffcrIds,"completed");
      				myAssignedSubTasksStatusList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds);
      			}
      			setIASOfficerStatusWiseCountView(myAssignedSubTasksTodayList,myAssignedSubTasksCompletedList,myAssignedSubTasksStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	} 
      	public void setIASOfficerStatusWiseCountView(List<Object[]> myAlertsTodayList,List<Object[]> myAlertsCompletedList,
      			List<Object[]> statusList,
      			DistrictOfficeViewAlertVO returnVO,String alertType){
      		try{
      			List<DistrictOfficeViewAlertVO> todayFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			List<DistrictOfficeViewAlertVO> completedFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			List<DistrictOfficeViewAlertVO> statusFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			
      			if(myAlertsTodayList != null && myAlertsTodayList.size() > 0){
      				for (Object[] objects : myAlertsTodayList) {
      					DistrictOfficeViewAlertVO todayVo = new DistrictOfficeViewAlertVO();
      						todayVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
      						todayVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
      						todayVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
      					todayFinalList.add(todayVo);
					}
      			}
      			
      			if(myAlertsCompletedList != null && myAlertsCompletedList.size() > 0){
      				for (Object[] objects : myAlertsCompletedList) {
      					DistrictOfficeViewAlertVO completedVo = new DistrictOfficeViewAlertVO();
      						completedVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
      						completedVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
      						completedVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
      					completedFinalList.add(completedVo);
					}
      			}
      			if(statusList != null && statusList.size() >0){
      				for (Object[] param : statusList) {
      				DistrictOfficeViewAlertVO statusVo = new DistrictOfficeViewAlertVO();
      					statusVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
  						statusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
  						statusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
  						statusVo.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
  					statusFinalList.add(statusVo);
					}
      			}
      			//if(alertType !=null && alertType.isEmpty() && alertType.equalsIgnoreCase("MyAlerts")){
      				//if(commonMethodsUtilService.isListOrSetValid(returnVO.getList1())){
      					DistrictOfficeViewAlertVO vo = new DistrictOfficeViewAlertVO();
      					vo.setSubList1(todayFinalList);
      					vo.setSubList2(completedFinalList);
      					vo.setSubList3(statusFinalList);
      					
      					returnVO.getList1().add(vo);
      				//}
      				if(returnVO.getList1() != null && returnVO.getList1().size() > 0){
      					for (DistrictOfficeViewAlertVO districtOfficeViewAlertVO : returnVO.getList1()){
      						Long totalCount = 0l;
      						if(districtOfficeViewAlertVO.getSubList3() != null && districtOfficeViewAlertVO.getSubList3().size() > 0){
      								for (DistrictOfficeViewAlertVO statusFinalVo : districtOfficeViewAlertVO.getSubList3()) {
      									totalCount = totalCount + statusFinalVo.getCount();
									}
      							}
      							districtOfficeViewAlertVO.setOverAllCnt(totalCount);//TotalCount
  							  if(districtOfficeViewAlertVO.getSubList3() != null && districtOfficeViewAlertVO.getSubList3().size() > 0){
								for (DistrictOfficeViewAlertVO statusVo : districtOfficeViewAlertVO.getSubList3()) {
									statusVo.setPerc(calculatePercantage(statusVo.getCount(),districtOfficeViewAlertVO.getOverAllCnt()));
								}
      					      }
      						}
      					}
      			//	}
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  setIASOfficerStatusWiseCountView() ", e);
      		}
      	}
      	public List<AlertVO> getTemplate(List<Object[]> tempList){
      		List<AlertVO> finalList = new ArrayList<AlertVO>(0);
			if(tempList != null && tempList.size() > 0){
				for (Object[] objects : tempList) {
					AlertVO vo = new AlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[2]));
						finalList.add(vo);
				}
			}
			return finalList;
      	}
      	/*
         * Swadhin K Lenka
         * overview and Status new
         * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCountStatusWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
         */
        public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(String fromDateStr, String toDateStr, Long stateId, 
        									List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
        									Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
        									Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,List<Long> calCntrIdList,List<Long> sublevels){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}/*else{
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}*/
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					KeyValueVO sublevel = new KeyValueVO();
    					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
    					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
    					subLevels.add(sublevel);
    				}
    			}
    			
    			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
    				deptScopeIdList.clear();
					deptScopeIdList.addAll(sublevels);
				}
    			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
    			List<Object[]> alertList = null; 
    			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
        				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
                			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
                			prepareResultForState(alertList,returnList,sortingType,order);
            				return returnList;
        				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
        					if(alertType != null && alertType.equalsIgnoreCase("alert")){
        						alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
        					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
        						alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
        					}
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
            				
            				if(alertType != null && alertType.equalsIgnoreCase("alert")){
            					alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
            					alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
            				if(alertType != null && alertType.equalsIgnoreCase("alert")){
            					alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
            					alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}
            			}
        			}else{
        				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L || parentGovtDepartmentScopeId.longValue() == 5L){
                			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
                			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,null,calCntrIdList);
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
                			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,null,calCntrIdList);
            			}
        			}
        			
    			}
    			
    			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
    			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
    			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
    			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
    			
    			Set<Long> deptScopeIds = new HashSet<Long>();
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
    				}
    			}
    			List<Object[]> deptScopeIdDtlsList = null;
    			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
    				deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));
    			}else if(deptScopeIds != null && deptScopeIds.size() >0){ 
    				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
    			}
    			
    			
    			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
    				for(Object[] param : deptScopeIdDtlsList){
    					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
    					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));

    				}  
    			}
    			
    			if(alertList != null && alertList.size() > 0){   
    				for(Object[] param : alertList){
    					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
    					
    					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
    					if(levelIdAndAlertCountMap == null){
    						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
    						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
    					}
    					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
    				}
    			}
    			
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			
    			if(returnList != null && returnList.size() > 0){
    				returnList.get(0).getSubLevels().addAll(subLevels);
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
      //Regarding filter. district
        public List<IdNameVO> getDistIdListForDistFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
      		try{
      			
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			if(printIdList != null && printIdList.size() > 0){  
      				if(electronicIdList != null && electronicIdList.size() == 0){
      					electronicIdList.add(0L);
      				}
      			}else if(electronicIdList != null && electronicIdList.size() > 0){
      				if(printIdList != null && printIdList.size() == 0){
      					printIdList.add(0L);
      				}
      			}/*else{
      				electronicIdList.add(0L);
      				printIdList.add(0L);
      			}*/
      			
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
      			
      			List<Object[]> alertList = null;
      			if(alertType != null && alertType.equalsIgnoreCase("alert"))
    			 alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,group,searchType,calCntrIdList);
      			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
      				alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,group,searchType,calCntrIdList);	
      			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
      			
      		}catch(Exception e){
      			e.printStackTrace();
      		}
      		return null;
        }
        //Regarding filter. division->district
        public List<IdNameVO> getDistIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}/*else{
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}*/
    			
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
    			List<Object[]> alertList = null;
    			if(alertType != null && alertType.equalsIgnoreCase("alert"))
    			   alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,"true",group,searchType,calCntrIdList);
    			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
    				alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,"true",group,searchType,calCntrIdList);
    			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}
        //Regarding filter. division->division
        public List<IdNameVO> getDivisionIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    		try{
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}/*else{
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}*/
    			
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
    			List<Object[]> alertList = null;
    			if(alertType != null && alertType.equalsIgnoreCase("alert"))
    			   alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,"true",group,searchType,calCntrIdList);
    			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
    			   alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,"true",group,searchType,calCntrIdList);
    			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
      }
      //Regarding filter. division->sub division
        //1
      //Regarding filter. district->sub division
        public List<IdNameVO> getDistrictIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
       	 try{
       		
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
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
			List<Object[]> alertList = null;
			if(alertType != null && alertType.equalsIgnoreCase("alert"))
			    alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList);
			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
				alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList);
			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
			if(alertList != null && alertList.size() > 0){
  				for(Object[] param : alertList){
  					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
  				}
  			}
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
          		  IdNameVO idNameVO = null;
          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
          				  idNameVO = new IdNameVO();
          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
          				  idNameVOs.add(idNameVO);
          			  }
          		  }
          		  return idNameVOs;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
       	
        }
        //2
      //Regarding filter. division->sub division
       public List<IdNameVO> getDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    	   try{
   
   			Date fromDate = null;
   			Date toDate = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
   				fromDate = sdf.parse(fromDateStr);
   				toDate = sdf.parse(toDateStr);
   			}
   			if(printIdList != null && printIdList.size() > 0){  
   				if(electronicIdList != null && electronicIdList.size() == 0){
   					electronicIdList.add(0L);
   				}
   			}else if(electronicIdList != null && electronicIdList.size() > 0){
   				if(printIdList != null && printIdList.size() == 0){
   					printIdList.add(0L);
   				}
   			}/*else{
   				electronicIdList.add(0L);
   				printIdList.add(0L);
   			}*/
   			
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
   			
   			List<Object[]> alertList = null;
			if(alertType != null && alertType.equalsIgnoreCase("alert"))
			   alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,null,"true",group,searchType,calCntrIdList);
			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
			 alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList);
   			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
   			if(alertList != null && alertList.size() > 0){
     				for(Object[] param : alertList){
     					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
     				}
     			}
   			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
             		  IdNameVO idNameVO = null;
             		  if(idAndNameMap != null && idAndNameMap.size() > 0){
             			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
             				  idNameVO = new IdNameVO();
             				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
             				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
             				  idNameVOs.add(idNameVO);
             			  }
             		  }
             		  return idNameVOs;
   			
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return null;
       }

        //3
      //Regarding filter. sub division->sub division
        public List<IdNameVO> getSubDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,Long divisionWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
        	try{
        	
   			Date fromDate = null;
   			Date toDate = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
   				fromDate = sdf.parse(fromDateStr);
   				toDate = sdf.parse(toDateStr);
   			}
   			if(printIdList != null && printIdList.size() > 0){  
   				if(electronicIdList != null && electronicIdList.size() == 0){
   					electronicIdList.add(0L);
   				}
   			}else if(electronicIdList != null && electronicIdList.size() > 0){
   				if(printIdList != null && printIdList.size() == 0){
   					printIdList.add(0L);
   				}
   			}/*else{
   				electronicIdList.add(0L);
   				printIdList.add(0L);
   			}*/
   			
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
   			
   			List<Object[]> alertList = null;
			if(alertType != null && alertType.equalsIgnoreCase("alert"))
   			 alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,"true",group,searchType,calCntrIdList);
			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
			alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList);
   			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
   			if(alertList != null && alertList.size() > 0){
     				for(Object[] param : alertList){
     					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[5]), commonMethodsUtilService.getStringValueForObject(param[6]));
     				}
     			}
   			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
             		  IdNameVO idNameVO = null;
             		  if(idAndNameMap != null && idAndNameMap.size() > 0){
             			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
             				  idNameVO = new IdNameVO();
             				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
             				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
             				  idNameVOs.add(idNameVO);
             			  }
             		  }
             		  return idNameVOs;
   			
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return null;
    }
    	public List<AlertVO> getAllDistrictDetails(Long departmentId){
            List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
        		try {
        		List<Object[]> districtList = govtDepartmentWorkLocationDAO.getAllDistrictDetails(departmentId);
        		if (districtList != null && districtList.size() > 0) {
              			for
              			(Object[] objects : districtList) {  
              				 AlertVO alertVO = new AlertVO();
              				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
              				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
              				 finalVoList.add(alertVO);
              				}					
      				}				
      			} catch (Exception e) {
      				LOG.error(" Exception Occured in getAllDistrictDetails() method, Exception - ",e);
      			}    	
              	return finalVoList;
          } 
    	 public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, 
    				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
    				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
    				Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,String searchType,List<Long> sublevels){
                   try{
                	   Date fromDate = null;
           			   Date toDate = null;
           			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
           				fromDate = sdf.parse(fromDateStr);
           				toDate = sdf.parse(toDateStr);
           			}
           			if(printIdList != null && printIdList.size() > 0){  
           				if(electronicIdList != null && electronicIdList.size() == 0){
           					electronicIdList.add(0L);
           				}
           			}else if(electronicIdList != null && electronicIdList.size() > 0){
           				if(printIdList != null && printIdList.size() == 0){
           					printIdList.add(0L);
           				}
           			}/*else{
           				electronicIdList.add(0L);
           				printIdList.add(0L);
           			}*/
           			
           			List<Long> levelValues = new ArrayList<Long>();    
           			Long levelId = 0L;
           			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
           			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
           				for(Object[] param : lvlValueAndLvlIdList){
           					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
           					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
           				}
           			}
           			
           			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
           			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
           			List<Long> deptScopeIdList = new ArrayList<Long>();
           			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
           				for(Object [] param : childDeptScopeIdList){
           					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
           					KeyValueVO sublevel = new KeyValueVO();
        					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
        					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
        					subLevels.add(sublevel);
           				}
           			}
           			
           			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
        				deptScopeIdList.clear();
    					deptScopeIdList.addAll(sublevels);
    				}
           			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
           			List<Object[]> alertList = null;  
           			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
           				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
                   			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,null);
                   			prepareResultForState(alertList,returnList,sortingType,order);
               				return returnList;
           				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
           					if(alertType != null && alertType.equalsIgnoreCase("alert"))
               				alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,searchType,null);
           					else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
           					 alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,searchType,null);
               			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
               				if(alertType != null && alertType.equalsIgnoreCase("alert"))
                   			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,searchType,null);
               				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
               				alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,searchType,null);
               			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
               				if(alertType != null && alertType.equalsIgnoreCase("alert"))
                   			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,searchType,null);
               				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
               				alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,searchType,null);
               			}
           			}else{
           				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L || parentGovtDepartmentScopeId.longValue() == 5L){
                   			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,null);
               			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
                   			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,null,null);
               			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
                   			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,null,null);
               			}
           			}
           			

           			
           			
           			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
           			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
           			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
           			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
           			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
           			
           			Set<Long> deptScopeIds = new HashSet<Long>();
           			if(alertList != null && alertList.size() > 0){
           				for(Object[] param : alertList){
           					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
           				}
           			}
           			List<Object[]> deptScopeIdDtlsList = null;
           			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size()>0){
           				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
           				//deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));
           			}/*else if(deptScopeIds != null && deptScopeIds.size()>0){ 
           				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
           			}*/
           			
           			
           			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
           				for(Object[] param : deptScopeIdDtlsList){
           					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
           					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));

           				}  
           			}
           			
           			if(alertList != null && alertList.size() > 0){   
           				for(Object[] param : alertList){
           					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
           					
           					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
           					if(levelIdAndAlertCountMap == null){
           						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
           						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
           					}
           					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
           				}
           			}
           			
           			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
           			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
           				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
           					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
           					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
           					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
           					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
           					Long total = new Long(0L);
           					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
           						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
           							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
           							total = total + outerEntry.getValue().get(boardVO.getId());
           						}
           					}
           					alertCoreDashBoardVO.setTotalCount(total);
           					returnList.add(alertCoreDashBoardVO);
           				}
           			}
           			
           			if(returnList != null && returnList.size() > 0){
           				returnList.get(0).getSubLevels().addAll(subLevels);
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

        /*
		 * Teja(non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#stateLevelDeptOfficerDepartmentWiseAlertsView()
		 */
        public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsView(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
        		LOG.info("Entered in stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
        		try{
        			
        			Date fromDate = null;
        			Date toDate = null;
        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
        				fromDate = sdf.parse(fromDateStr);
        				toDate = sdf.parse(toDateStr);
        			}
        			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
        			//get alert status count and and create a map of alertStatusId and its count
        			List<Object[]> totalList = new ArrayList<Object[]>();
        			/*List<Object[]> alertCountList = alertDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status");//for pending status
        			if(alertCountList != null && alertCountList.size() > 0){
        				totalList.addAll(alertCountList);
        			}*/
        			List<Long> levelValues = new ArrayList<Long>();    
        			Long levelId = 0L;
        			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
        			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
        				for(Object[] param : lvlValueAndLvlIdList){
        					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
        					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
        				}
        			}
        			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.stateLevelDeptOfficerDepartmentWiseAlertsView(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null);
        			if(alertCountList2 != null && alertCountList2.size() > 0){
        				totalList.addAll(alertCountList2);
        			}//Teja
        			setAlertCountDetailsforDepartmentWise(totalList,finalAlertVOs); 
        			return finalAlertVOs; 
        		}catch(Exception e){
        			e.printStackTrace();
        			LOG.error("Error occured stateLevelDeptOfficerDepartmentWiseAlertsView() method of AlertManagementSystemService{}");
        		}
        		return null;
        	}
        public void setAlertCountDetails(List<Object[]> objList,List<AlertVO> finalAlertVOs){
    	    try{
    	    	if(objList != null && objList.size() > 0){         
    				Long totalAlertCnt = 0l;
    				for(Object[] param : objList){
    						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[5]);	 
    				 }
    				 for(Object[] param : objList){
    					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
    					 AlertVO VO = getMatchVO1(finalAlertVOs,id);
    					 if(VO == null){
    						 VO = new AlertVO();
    						 VO.setStatusId(id);
    						 VO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
    						 VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
    						 VO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//deptId
    						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//dept name 
    						 VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[5]));
    					  finalAlertVOs.add(VO); 
    					 }else{
    					 	  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
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
        //For status over view sub task for state level page
        public List<AlertVO> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
      		LOG.info("Entered in stateLevelDeptOfficerLocationLevelOverviewBySubTasks() method of AlertManagementSystemService{}");
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
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
      			List<Object[]> rtrnObjLst = govtAlertSubTaskDAO.stateLevelDeptOfficerLocationLevelOverviewBySubTasks(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",null,null);
      			setAlertCount(rtrnObjLst,finalAlertVOs);
      			return finalAlertVOs; 
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Error occured stateLevelDeptOfficerLocationLevelOverviewBySubTasks() method of AlertManagementSystemService{}");
      		}
      		return null;
      	}
	 public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseAlertClick(Long govtDeptDesigOffceId,Long govtOffceId,Long statusId,String formDateStr,String toDateStr,String clickType,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
			List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
			try {
			
				Date fromDate = null; 
	   			Date toDate = null;
	   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   			if(formDateStr != null && formDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	   				fromDate = sdf.parse(formDateStr);
	   				toDate = sdf.parse(toDateStr);
	   			}
				List<Long> alertIds = null;
				if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() > 0l && govtOffceId != null && govtOffceId.longValue() > 0l){
				 if(clickType != null && clickType.equalsIgnoreCase("alert"))
				  alertIds = alertAssignedOfficerNewDAO.getDistrictOffrAlertsIds(govtDeptDesigOffceId,govtOffceId,fromDate,toDate,statusId,printIdsList,electronicIdsList,calCntrIdList);
				 else if(clickType != null && clickType.equalsIgnoreCase("mySubTasks"))
				  alertIds = govtAlertSubTaskDAO.getDistrictOffcrSubTasksAlertIds(govtDeptDesigOffceId,govtOffceId,"mySubTasks",fromDate,toDate,statusId,printIdsList,electronicIdsList,calCntrIdList);
				 else if(clickType != null && clickType.equalsIgnoreCase("myAssignedSubTasks"))
				  alertIds = govtAlertSubTaskDAO.getDistrictOffcerSubTsksAlertIds(govtDeptDesigOffceId,govtOffceId,"myAssignedSubTasks",fromDate,toDate,statusId,printIdsList,electronicIdsList,calCntrIdList);
				}
				if(alertIds != null && alertIds.size() > 0){
					List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
					
					setAlertDtls(finalVoList, list); 
				}
				setSubListCount(finalVoList, alertIds);
				
			} catch (Exception e) {
				LOG.error(" Exception Occured in getDistrictLevelDeptWiseAlertClick() method, Exception - ",e);
			}		
			return finalVoList;
		}
	 public void setSubListCount(List<AlertCoreDashBoardVO> finalVoList,List<Long> alertIds){
		 try{
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
				if(finalVoList != null && finalVoList.size() > 0){
					for(AlertCoreDashBoardVO alertCoreDashBoardVO : finalVoList){
						if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
							alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
						}
					}
				}
		 }catch (Exception e) {
				LOG.error(" Exception Occured in setSubListCount() method, Exception - ",e);
			}
		
	 }
	//For state level page
     public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
 		LOG.info("Entered in stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick() method of AlertManagementSystemService{}");
 		try{
 			
 			Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
 			//get alert status count and and create a map of alertStatusId and its count
 			List<Object[]> totalList = new ArrayList<Object[]>();
 			/*List<Object[]> alertCountList = alertDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status");//for pending status
 			if(alertCountList != null && alertCountList.size() > 0){
 				totalList.addAll(alertCountList);
 			}*/
 			List<Long> levelValues = new ArrayList<Long>();    
 			Long levelId = 0L;
 			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
 			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
 				for(Object[] param : lvlValueAndLvlIdList){
 					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
 				}
 			}
 			List<Object[]> alertCountList2 = govtAlertSubTaskDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null);
 			if(alertCountList2 != null && alertCountList2.size() > 0){
 				totalList.addAll(alertCountList2);
 			}
 			setAlertCountDetails(totalList,finalAlertVOs); 
 			return finalAlertVOs; 
 		}catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Error occured stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
 		}
 		return null;
 	}

	 public List<GrievanceAlertVO> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,String fromDateStr,String toDateStr,Long statusId){
			List<GrievanceAlertVO> finalVoList = new ArrayList<GrievanceAlertVO>(0);
		try {
			Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			
			List<Object[]> GovtGrivenceDetails = alertDAO.getGovtGrievanceAlertDetails(mobileNo,locatoinType,locationId,fromDate,toDate,statusId);
			 if(GovtGrivenceDetails != null &&  GovtGrivenceDetails.size()>0){
				 for(Object[] param : GovtGrivenceDetails){
					 GrievanceAlertVO Vo = new  GrievanceAlertVO();
					 Vo.setDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					 Vo.setTime(commonMethodsUtilService.getStringValueForObject(param[1]).substring(11, 16));
					 Vo.setTitle(commonMethodsUtilService.getStringValueForObject(param[2]));
					 Vo.setDescription(commonMethodsUtilService.getStringValueForObject(param[3]));
					 Vo.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[4]));
					 Vo.setProblem(commonMethodsUtilService.getStringValueForObject(param[5]));
					 Vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[6]));
					 Vo.setCreatedBy(commonMethodsUtilService.getStringValueForObject(param[7]));
					 
					 Vo.setDistrict(commonMethodsUtilService.getStringValueForObject(param[8]));
					 Vo.setAssembly(commonMethodsUtilService.getStringValueForObject(param[9]));
					 Vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[10]));
					 Vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[11]));
					 Vo.setHamlet(commonMethodsUtilService.getStringValueForObject(param[12]));
					 Vo.setLeb(commonMethodsUtilService.getStringValueForObject(param[13]));
					 Vo.setWard(commonMethodsUtilService.getStringValueForObject(param[14]));
					 Vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[15]));
					 Vo.setAlertId(commonMethodsUtilService.getLongValueForObject(param[16]));
					 
					 finalVoList.add(Vo);
				 }
			 }
							
			} catch (Exception e) {
				LOG.error(" Exception Occured in getGovtGrievanceAlertDetails() method, Exception - ",e);
			}		
			return finalVoList;
		}
	 public void setAlertCountDetailsforDepartmentWise(List<Object[]> objList,List<AlertVO> finalAlertVOs){
 	    try{
 	    	if(objList != null && objList.size() > 0){         
 				Long totalAlertCnt = 0l;
 				for(Object[] param : objList){
 						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[5]);	 
 				 }
 				 for(Object[] param : objList){
 					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
 					AlertVO matchedDeptVo = getmatchedVo(finalAlertVOs,(Long)param[3]);
 					if(matchedDeptVo == null){
 						matchedDeptVo = new AlertVO();
 						matchedDeptVo.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//deptId
 						matchedDeptVo.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//dept name 
 						
 						AlertVO statusVo = new AlertVO();
 						statusVo.setId(id);//statusId
 						statusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//statusNAme
 						statusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
 						statusVo.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[5]));
 						
 						matchedDeptVo.getSubList2().add(statusVo);
 						finalAlertVOs.add(matchedDeptVo);
 					}else{
 						AlertVO matchedStatusVo = getmatchedVo(finalAlertVOs,(Long)param[0]);
 						if( matchedStatusVo == null){
 							matchedStatusVo = new AlertVO();
 							matchedStatusVo.setId(id);//statusId
 	 						matchedStatusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//statusName
 	 						matchedStatusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
 	 						matchedStatusVo.setAlertCnt(matchedStatusVo.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
 	 						
 	 						matchedDeptVo.getSubList2().add(matchedStatusVo);
 						}else{
 							matchedStatusVo.setAlertCnt(matchedStatusVo.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
 						}
 					}
 				}
 				//Calculating Percentage
 				calculatePerc(finalAlertVOs,totalAlertCnt);
 			}
 	    }catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Error occured setStatusWiseAlertCnt() method of AlertManagementSystemService{}");
 	    }
 	}
	 public FilterSectionVO getFilterSectionAlertDetails(){
		 FilterSectionVO filterVo =new FilterSectionVO();
		try {
			List<Object[]> scopeIds = govtDepartmentScopeDAO.getFilterSectionDetailsOnScopeIds();
			 setFilterSectionAlertDetails(scopeIds,filterVo,"scopes");
			List<Object[]> severityIds = alertSeverityDAO.getFilterSectionDetailsOnSeverity();
			 setFilterSectionAlertDetails(severityIds,filterVo,"severity");
			List<Object[]> categoryIds = alertCategoryDAO.getAllCategory();
			 setFilterSectionAlertDetails(categoryIds,filterVo,"category");
			List<Object[]> editionsIds = newsPaperDAO.getNewPaperList();
			 setFilterSectionAlertDetails(editionsIds,filterVo,"editions");
			List<Object[]> tvNewsChannelIds = tvNewsChannelDAO.getAllElectrinicMedia();
			 setFilterSectionAlertDetails(tvNewsChannelIds,filterVo,"tvNewsChannel");
			} catch (Exception e) {
				LOG.error(" Exception Occured in getFilterSectionAlertDetails() method, Exception - ",e);
			}		
			return filterVo;
		}
	 public void setFilterSectionAlertDetails(List<Object[]> scopeIds,FilterSectionVO filterVo,String names)
	 { 
		 List<FilterSectionVO> list =new ArrayList<FilterSectionVO>();
		 if(scopeIds!= null && scopeIds.size()>0){
			 for(Object[] param : scopeIds){
				 FilterSectionVO Vo = new  FilterSectionVO();
				 Vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 Vo.setName(commonMethodsUtilService.getStringValueForObject(param[1])); 
				 list.add(Vo);
				
			 }
		 }
		 if(names.equalsIgnoreCase("scopes")){
			 filterVo.getScopesList().addAll(list); 
		 }
		 if(names.equalsIgnoreCase("severity")){
			 filterVo.getSeverityList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("category")){
			 filterVo.getCategoryList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("editions")){
			 filterVo.getEditionsList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("tvNewsChannel")){
			 filterVo.getTvNewsChannelList().addAll(list);
		 }
		 
	 }
	 public String getDesignationForUser(Long userId){
			String officerName = null;
			String desgnationName = null;
			String officerNameAnddesgnationName = null;
			try {
				List<Object[]> officerList = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationsNameForUser(userId);
				if(commonMethodsUtilService.isListOrSetValid(officerList)){
					officerName = (String) officerList.get(0)[0];
					desgnationName = (String) officerList.get(0)[1];
				}
					officerNameAnddesgnationName = officerName+"-"+desgnationName;
			} catch (Exception e) {
				LOG.error("Error occured getDesignationForUser() method of AlertManagementSystemService",e);
			}
			return officerNameAnddesgnationName;
		}
	 public DistrictOfficeViewAlertVO getDeptDetails(Long userId){
			DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
			 try {
				 List<Long> levelValues = new ArrayList<Long>();    
	   			Long levelId = 0L;
	   			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	   			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	   				for(Object[] param : lvlValueAndLvlIdList){
	   					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	   					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	   				}
	   			}
	   			returnVO.setLevelValues(levelValues);
				returnVO.setLevelId(levelId);
	   			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
	   			
	   			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
	   			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
	   			List<Long> departmentIds = new ArrayList<Long>(0);
	   			List<String> departmentNames = new ArrayList<String>(0);
	   			List<Long> designationIds = new ArrayList<Long>(0);
	   			List<String> designationNames = new ArrayList<String>(0);
	   			if(commonMethodsUtilService.isListOrSetValid(list1)){
	   				for( Object[]  obj :list1){
	   					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
	   					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
	   					departmentIds.add(commonMethodsUtilService.getLongValueForObject(obj[3]));
	   					departmentNames.add(commonMethodsUtilService.getStringValueForObject(obj[4]));
	   					designationIds.add(commonMethodsUtilService.getLongValueForObject(obj[5]));
	   					designationNames.add(commonMethodsUtilService.getStringValueForObject(obj[6]));
	   					
	   					returnVO.setDeptIds(departmentIds);
	   					returnVO.setDepartmentNames(departmentNames);
	   					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
	   					returnVO.setGovtOfficerIds(govtOffcrIds);
	   					returnVO.setTodayAlertIds(designationIds);//designationIds
	   					returnVO.setDesignationNames(designationNames);
	   					
	   				}
	   			}
			} catch (Exception e) {
				LOG.error("Error occured getDeptDetails() method of AlertManagementSystemService",e);
			}
			 return returnVO;
		 }
		 public List<AlertCoreDashBoardVO> getStateLevelAlertclickView(Long deptId,Long statusId,String type,
				 Long govtDeptGovtOffrId,Long govtOffrcrId,String serachType){
	 		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
	 		try {
	 			List<Long> alertIdList = null;
	 			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>();
	 			govtDepDesigOffcrIds.add(govtDeptGovtOffrId);
	 			List<Long> govtOffcrIds = new ArrayList<Long>();
	 			govtOffcrIds.add(govtOffrcrId);
	 			if(type != null && type.equalsIgnoreCase("alert")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = alertAssignedOfficerNewDAO.getStateLevelAlertclickViewAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptId,statusId);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = alertAssignedOfficerNewDAO.getStateLevelAlertclickViewAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptId,statusId);
	 				}
	 			}else if(type.equalsIgnoreCase("subtask")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAlertclickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptId,statusId);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAlertclickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptId,statusId);
	 				}
	 			}else if(type.equalsIgnoreCase("assignSubTask")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAssignedAlertClickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptId,statusId);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAssignedAlertClickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptId,statusId);
	 				}
	 			}
	 			if(alertIdList != null && alertIdList.size() > 0){
	 				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
	 				setAlertDtls(finalVoList, list); 
	 			}
	 			setSubListCount(finalVoList, alertIdList);
	 		} catch (Exception e) {
	 			LOG.error(" Exception Occured in getStateLevelAlertclickView() method, Exception - ",e);
	 		}		
	 		return finalVoList;
	 	}
		 /*
	      	 * Swadhin K Lenka
	      	 * state overview  and status click
	      	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long) 
	      	 */
	      	public List<AlertCoreDashBoardVO> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds){
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
	      			}/*else{
	      				electronicIdList.add(0L);
	      				printIdList.add(0L);
	      			}*/
	      			
	      			List<Long> levelValues = new ArrayList<Long>();    
	      			Long levelId = 0L;
	      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	      				for(Object[] param : lvlValueAndLvlIdList){
	      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	      				}
	      			}
	      			
	    			List<Long> alertList = null;
	    			
	    			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeId,parentGovtDepartmentScopeId, statusId, calCntrIds);

	      			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
	      			if(alertList != null && alertList.size() > 0){
	    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
	    				setAlertDtls(alertCoreDashBoardVOs, list); 
	    			}
	      			//set Subtask into alert logic 
	    			
	    			//get subtask count.
	    			List<Object[]> subtaskCountList = null;
	    			if(alertList != null && alertList.size() > 0){
	    				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertList);
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
	      		}
	      		return null;
	      	}
	      	public List<AlertCoreDashBoardVO> getStateLevelDeptWiseFlterClick(Long userId,List<Long> deptIds,Long locatonLevelId,
	    			Long statusId,String type,String fromDateStr,String toDateStr,
	    			Long desigDeptOfficerId,Long officerId){
	    		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
	    		List<Long> alertIds = null;
	    		try {
	    			Date fromDate = null;
	    			Date toDate = null;
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	    				fromDate = sdf.parse(fromDateStr);
	    				toDate = sdf.parse(toDateStr);
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
	    			if(type.equalsIgnoreCase("alert")){
	    				if(statusId == 1l){
	    					alertIds = alertDAO.getStateLevelDeptWiseFlterClick(deptIds,statusId,fromDate,toDate);
	    				}else{
	    					alertIds = alertAssignedOfficerNewDAO.getStateLevelDeptWiseFlterClick(deptIds,locatonLevelId,statusId,fromDate,toDate,desigDeptOfficerId,officerId,levelId);
	    				}
	    				
	    			}else if(type.equalsIgnoreCase("subTask")){
	    				alertIds = govtAlertSubTaskDAO.getStateLevelDeptWiseFlterClick(deptIds,locatonLevelId,statusId,fromDate,toDate,desigDeptOfficerId,officerId,levelId);
	    			}
	    			
	    			if(alertIds != null && alertIds.size() > 0){
	    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
	    				setAlertDtls(finalVoList, list); 
	    			}
	    		} catch (Exception e) {
	    			LOG.error(" Exception Occured in getStateLevelDeptWiseFlterClick() method, Exception - ",e);
	    		}		
	    		return finalVoList;
	    	}
	      	
	      	public List<IdNameVO> getGovtAllDepartmentDetails(){
	      		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
	      		try {
	      			
	      			List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtAllDepartmentDetails();
	      			if(list !=null && list.size()>0){
	      				for (Object[] objects : list) {							
	      					IdNameVO vo = new IdNameVO();
	      					vo.setId((Long)objects[0]);
	      					vo.setName(objects[1] !=null ? objects[1].toString():"");
	      					
	      					finalList.add(vo);
						}
	      			}
					
				} catch (Exception e) {
					LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
				}
	      		return finalList;
	      	}
	      	
  			public List<IdNameVO>  getStatusCompletionInfoNew(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId){
  			            List<IdNameVO> finalList = new ArrayList<IdNameVO>();
  			            try {
  			              
  			              Alert alert  = alertDAO.get(alertId);
  			              
  			              String userType = null;
  			              //whether this alert is belongs to same logedin user or not.
  			              //get all govt dept desig off ids
  			              List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
  			              
  			              //get govt dept desig off id by alertId
  			              Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
  			              
  			              //whether this alert is belongs to just subordinate or not.
  			              
  			              //get all govt dept desig ids
  			              List<Long> govtDeptDesigIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigIdListByUserId(userId);
  			              //get govt dept desig id by alertId
  			              Long govtDeptDesigId2 = alertAssignedOfficerNewDAO.getGovtDeptDesigIdListByUserId(alertId); 
  			              
  			              //now check
  			              List<Object[]> list2 = null;
  			              if(govtDeptDesigIdList != null && govtDeptDesigIdList.size() > 0 && govtDeptDesigId2 != null && govtDeptDesigId2.longValue() > 0L){
  			                list2 = govtDepartmentDesignationHierarchyDAO.getChildDesigDataNew(govtDeptDesigIdList,govtDeptDesigId2);
  			              }
  			              
  			              //to check same level designation.
  			              //by alert id take scope.
  			              Long govtDeptScopeIdForAlert = alertAssignedOfficerNewDAO.getGovtDeptScopeIdForAlert(alertId);
  			              //by user Id take scope.
  			              List<Long> govtDeptScopeIdsForUserId = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptScopeIdsForUserId(userId);
  			              
  			              //to wheck whether he/she is an admin or not.
  			              Long userCount = govtDepartmentDesignationOfficerNewDAO.getUserIdCount(userId);
  			              String userStatus = "";
  			              if(userCount != null && userCount.longValue() > 0L){
  			                userStatus = "admin";
  			              }else{
  			                userStatus = "officer";  
  			              }
  			              if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
  			                userType ="own";
  			                
  			                List<Object[]> objList = alertDepartmentStatusDAO.getAlertGovtDepartmentStatus(alert.getGovtDepartmentId());
  			                if(objList != null && objList.size() > 0){
  			                  for (Object[] objects : objList) {
  			                    IdNameVO VO = new IdNameVO();
  			                    VO.setId((Long)objects[0]);
  			                        VO.setName(objects[1].toString());
  			                        VO.setDateStr(objects[2] != null ? objects[2].toString():"");
  			                        finalList.add(VO);
  			              }
  			                }
  			                
  			              }else if(list2 != null && list2.size() > 0){ 
  			                
  			                userType = "subUser";
  			                
  			                if(alert.getAlertStatusId().longValue() == 4l || alert.getAlertStatusId().longValue() == 11l || alert.getAlertStatusId().longValue() == 12l){//Completed Status  
  			                  //userType = "subUserStatus";
  			                  List<Object[]> listObj = alertStatusDAO.getAlertStatusInfoForReOpen();
  			                    if(listObj !=null && listObj.size()>0){
  			                      for (Object[] objects : listObj) {
  			                    IdNameVO vo = new IdNameVO();
  			                    vo.setId((Long)objects[0]);
  			                    vo.setName(objects[1].toString());
  			                    vo.setDateStr(objects[2] != null? objects[2].toString():null);
  			                    finalList.add(vo);
  			                  }
  			                    }
  			                }else{

  			                	IdNameVO vo = new IdNameVO();              
  			                    finalList.add(vo);                  
  			                }  
  			                
  			              }else if(govtDeptScopeIdsForUserId != null && govtDeptScopeIdsForUserId.size() > 0 && govtDeptScopeIdForAlert != null && govtDeptScopeIdsForUserId.contains(govtDeptScopeIdForAlert)){
  			                userType = "same";
  			                IdNameVO vo = new IdNameVO();                
  			                finalList.add(vo);
  			              }
  			              else{
  			                userType = "subUser";              
  			                IdNameVO vo = new IdNameVO();              
  			                finalList.add(vo);
  			              }
  			              
  			              
  			              if(finalList != null && finalList.size() > 0){
  			                finalList.get(0).setApplicationStatus(userType+" - "+alert.getAlertStatusId());
  			                finalList.get(0).setUserStatus(userStatus);
  			              }
  			          
  			        } catch (Exception e) {
  			          e.printStackTrace();
  			          LOG.error("Error occured getStatusCompletionInfo() method of AlertManagementSystemService",e);
  			        }
  			            return finalList;
  			  }
}      	
