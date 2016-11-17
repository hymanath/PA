package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringVerificationVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserPerformanceVO;
import com.itgrids.partyanalyst.model.Job;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FieldMonitoringAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(FieldMonitoringAction.class);
	
	//instance variables
		private HttpServletRequest request;
		private HttpSession	session;
		private JSONObject jObj;
		private String task;
		private List<IdAndNameVO> idAndNameVOList;  
		private ResultStatus resultStatus; 
		private FieldMonitoringVO fieldMonitoringVO;
		private List<FieldMonitoringVO> fieldMonitoringList;
		private List<FieldMonitoringIssueVO> fieldMonitoringIssueVOList;
		private List<DataMonitoringVerificationVO> dataMonitoringVerificationVOList;
		private List<CadreRegUserVO> constituencyList;
		private List<CadreRegUserVO> usersList;
		private List<UserPerformanceVO> userPerformanceList;
		
	//Attributes
	   private IFieldMonitoringService fieldMonitoringService;
	
	 //implementation methods
	   public void setServletRequest(HttpServletRequest request) {
			this.request = request;
		}
	   public HttpSession getSession() {
			return session;
		}
	
	
		public void setSession(HttpSession session) {
			this.session = session;
		}


	//setters and getters.
	   public void setFieldMonitoringService(IFieldMonitoringService fieldMonitoringService) {
			this.fieldMonitoringService = fieldMonitoringService;
	   }
	   public JSONObject getjObj() {
		   return jObj;
	   }
	   public void setjObj(JSONObject jObj) {
			this.jObj = jObj;
	   }
	   public String getTask() {
			return task;
	   }
	   public void setTask(String task) {
		  this.task = task;
	   }
	public List<IdAndNameVO> getIdAndNameVOList() {
		return idAndNameVOList;
	}

	public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
		this.idAndNameVOList = idAndNameVOList;
	}
	public FieldMonitoringVO getFieldMonitoringVO() {
		return fieldMonitoringVO;
	}
	public void setFieldMonitoringVO(FieldMonitoringVO fieldMonitoringVO) {
		this.fieldMonitoringVO = fieldMonitoringVO;
	}
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<FieldMonitoringIssueVO> getFieldMonitoringIssueVOList() {
		return fieldMonitoringIssueVOList;
	}

	public void setFieldMonitoringIssueVOList(
			List<FieldMonitoringIssueVO> fieldMonitoringIssueVOList) {
		this.fieldMonitoringIssueVOList = fieldMonitoringIssueVOList;
	}
	public List<FieldMonitoringVO> getFieldMonitoringList() {
		return fieldMonitoringList;
	}
	public void setFieldMonitoringList(List<FieldMonitoringVO> fieldMonitoringList) {
		this.fieldMonitoringList = fieldMonitoringList;
	}
	public List<DataMonitoringVerificationVO> getDataMonitoringVerificationVOList() {
		return dataMonitoringVerificationVOList;
	}
	public void setDataMonitoringVerificationVOList(
			List<DataMonitoringVerificationVO> dataMonitoringVerificationVOList) {
		this.dataMonitoringVerificationVOList = dataMonitoringVerificationVOList;
	}
	public List<CadreRegUserVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<CadreRegUserVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<CadreRegUserVO> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<CadreRegUserVO> usersList) {
		this.usersList = usersList;
	}
	public List<UserPerformanceVO> getUserPerformanceList() {
		return userPerformanceList;
	}
	public void setUserPerformanceList(List<UserPerformanceVO> userPerformanceList) {
		this.userPerformanceList = userPerformanceList;
	}
	
	
	//Business methods
	public String execute(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		boolean noaccess = false;
		if(regVO == null)
			return Action.INPUT;
		
		if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
		{
			entitlements = regVO.getEntitlements();
			 if(!(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD".trim())))
			 {
			        noaccess = true ;  
			 }
		}
		
		return Action.SUCCESS;    
	}
	
	public String webMonitoringDashboard(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		boolean noaccess = false;
		if(regVO == null)
			return Action.INPUT;
		
		if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
		{
			entitlements = regVO.getEntitlements();
			 if(!(entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim())))
			 {
			        noaccess = true ;  
			 }
		}
		
		return Action.SUCCESS;    
	}
	
	public String fieldMonitoringNew(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		List<String> entitlements = null;
		boolean noaccess = false;
		if(regVO == null)
			return Action.INPUT;
		
		Long userId = regVO.getRegistrationID();
		
		//constituencyList = fieldMonitoringService.getCadreRegUserAssignedConstituencies(userId);
		//usersList = fieldMonitoringService.getCadreRegUserAssignedUsers(userId);
		
		if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
		{
			entitlements = regVO.getEntitlements();
			 if(!(entitlements.contains("CADRE_FIELD_MONITORING".trim())))
			 {
			        noaccess = true ;  
			 }
		}
		
		return Action.SUCCESS;    
	}
	
	public String getUserAssignedConstituencies(){
		
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long districtId = jObj.getLong("districtId");
			String userType = null;
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
				 {
				       userType = "dashboard";
				 }
			}
			
			constituencyList = fieldMonitoringService.getCadreRegUserAssignedConstituencies(userId,userType,districtId);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
	
	public String getCadreRegUserAssignedDistricts(){
		
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			//Long districtId = jObj.getLong("districtId");
			String userType = null;
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
				 {
				       userType = "dashboard";
				 }
			}
			
			constituencyList = fieldMonitoringService.getCadreRegUserAssignedDistricts(userId,userType);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
	
	public String getUserAssignedUsers(){
		
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			List<String> entitlements = null;
			/*JSONArray constArr = jObj.getJSONArray("constituencyId");  
			List<Long> constIds = new ArrayList<Long>();
			for( int i=0;i<constArr.length();i++){
				constIds.add(Long.valueOf(constArr.getString(i)));
			}*/
			Long constituencyId = jObj.getLong("constituencyId");
			String userType = null;
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
				 {
				       userType = "dashboard";
				 }
			}
			
			constituencyList = fieldMonitoringService.getCadreRegUserAssignedUsers(userId,constituencyId,userType);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}

	public String getVendors(){
		
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			idAndNameVOList =fieldMonitoringService.getVendors(stateId);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
	
	public String getVendorDistricts(){
		
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long vendorId = jObj.getLong("vendorId");
			idAndNameVOList =fieldMonitoringService.getVendorDistricts(stateId,vendorId);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendorDistricts()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
	
	public String getVendorConstituencies(){
		
		try {
			jObj = new JSONObject(getTask());
			Long vendorId = jObj.getLong("vendorId");
			Long districtId = jObj.getLong("districtId");
			idAndNameVOList =fieldMonitoringService.getVendorConstituencies(vendorId,districtId);
		} catch (Exception e) {
			LOG.error("Exception raised at getVendorConstituencies()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
     public String getCadreRegIssueType(){
		
		try {
			
			idAndNameVOList =fieldMonitoringService.getCadreRegIssueType();
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreRegIssueType()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
    public String saveFieldIssue()
     {
     	try
     	{
     		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
     		Long userId = null;
     		if(regVO != null){
     			userId = regVO.getRegistrationID();
     		}
     		
     		jObj = new JSONObject(getTask());
     		FieldMonitoringIssueVO  inputVO = new FieldMonitoringIssueVO();  
     		inputVO.setIssueTypeId(jObj.getLong("issueTypeId"));
     		inputVO.setCadreSurveyUserId(jObj.getLong("cadreSurveyUserId"));
     		inputVO.setTabUserInfoId(jObj.getLong("tabUserInfoId"));
     		inputVO.setDescription(jObj.getString("description"));
     		inputVO.setLoginUserId(userId);       
     		inputVO.setConstituencyId(jObj.getLong("constituencyId"));
     		inputVO.setMandalId(jObj.getString("mandal"));
     		inputVO.setFirstname(jObj.getString("name"));
     		inputVO.setMobileNo(jObj.getString("mobileNumber"));
     	    resultStatus = fieldMonitoringService.saveFieldIssue(inputVO);
     	}catch(Exception e)
     	{
     		LOG.error("Exception Occured in saveFieldIssue() in FieldMonitoringAction ",e);
     	}
     	return Action.SUCCESS;
     }

public String getTabUsersDetailsByVendorAndLocation(){
    	try {
    		jObj = new JSONObject(getTask());
    		Long vendorId = jObj.getLong("vendorId");
    		String fromDateStr = jObj.getString("fromDate");
    		String toDateStr = jObj.getString("toDate");
    		String locationType = jObj.getString("locationType");
    		Long locationVal = jObj.getLong("locationVal");
    		
    		fieldMonitoringVO = fieldMonitoringService.getTabUsersDetailsByVendorAndLocation(vendorId, fromDateStr, toDateStr, locationType, locationVal);
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return Action.SUCCESS;
    }

public String getTabUsersDetailsByVendorAndLocationNew(){
	try {
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		Long loginUserId = regVO.getRegistrationID();
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDate");
		String toDateStr = jObj.getString("toDate");
		/*JSONArray constArr = jObj.getJSONArray("constituencyId");  
		List<Long> constIds = new ArrayList<Long>();
		for( int i=0;i<constArr.length();i++){
			constIds.add(Long.valueOf(constArr.getString(i)));
		}
		JSONArray usersArr = jObj.getJSONArray("userId");  
		List<Long> userIds = new ArrayList<Long>();
		for( int i=0;i<usersArr.length();i++){
			userIds.add(Long.valueOf(usersArr.getString(i)));
		}*/
		Long constituencyId = jObj.getLong("constituencyId");
		Long userId = jObj.getLong("userId");
		Long districtId = jObj.getLong("districtId");
		
		fieldMonitoringVO = fieldMonitoringService.getTabUsersDetailsByVendorAndLocationNew(loginUserId, constituencyId, userId, fromDateStr, toDateStr, districtId);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return Action.SUCCESS;
}

public String getIssueStatusWiseCounts(){
	
	try {
		jObj = new JSONObject(getTask());
		String fromDateStr = jObj.getString("fromDate");
		String toDateStr = jObj.getString("toDate");
		String  task = jObj.getString("task");
		Long stateId = jObj.getLong("stateId");
		idAndNameVOList =fieldMonitoringService.getIssueStatusWiseCounts(fromDateStr,toDateStr,task,stateId);
	} catch (Exception e) {
		LOG.error("Exception raised at getIssueStatusWiseCounts()  of FieldMonitoringAction", e);
	}

    return Action.SUCCESS;
}
public String getIssueTypeWiseCounts(){

try {
	jObj = new JSONObject(getTask());
	String fromDateStr = jObj.getString("fromDate");
	String toDateStr = jObj.getString("toDate");
	Long stateId = jObj.getLong("stateId");
	idAndNameVOList =fieldMonitoringService.getIssueTypeWiseCounts(fromDateStr,toDateStr,stateId);
} catch (Exception e) {
	LOG.error("Exception raised at getIssueTypeWiseCounts()  of FieldMonitoringAction", e);
}

return Action.SUCCESS;
}
public String getConstituencyByVendor(){
	
	try {
		jObj = new JSONObject(getTask());
		Long fieldVendorId = jObj.getLong("fieldVendorId");
		idAndNameVOList =fieldMonitoringService.getConstituencyByVendor(fieldVendorId);
	} catch (Exception e) {
		LOG.error("Exception raised at getConstituencyByVendor()  of FieldMonitoringAction", e);
	}

    return Action.SUCCESS;
}
	public String getOverAllDataCollectorsDetails(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long loginUserId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			Long stateId = jObj.getLong("stateId");
			Long districtId = jObj.getLong("districtId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long userId = jObj.getLong("userId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
		
			
			fieldMonitoringVO = fieldMonitoringService.getAllDataCollectorsDetails(loginUserId,stateId,districtId,constituencyId,userId,fromDateStr,toDateStr);
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllDataCollectorsDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusWiseIssuesDetails(){
		try {
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long issueTypeId = jObj.getLong("issueType");
			Long issueStatusId = jObj.getLong("issueStatus");
			Long stateId = jObj.getLong("stateId");
			
			fieldMonitoringList = fieldMonitoringService.getStatusWiseIssuesDetails(fromDateStr,toDateStr,issueTypeId,issueStatusId,stateId);
		} catch (Exception e) {
			LOG.error("Exception raised at getStatusWiseIssuesDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getIssuesForATabUserByStatus(){
		try {
			jObj = new JSONObject(getTask());
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserInfoId = jObj.getLong("tabUserInfoId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long issueStatusId = jObj.getLong("issueStatusId");
			Long vendorId = jObj.getLong("vendor");
			String locationType = jObj.getString("locationType");
			Long locationVal = jObj.getLong("locationVal"); 
			
			fieldMonitoringIssueVOList = fieldMonitoringService.getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,fromDateStr,toDateStr,issueStatusId,vendorId,locationType,locationVal);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesForATabUserByStatus()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getIssuesForATabUserByStatusNew(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserInfoId = jObj.getLong("tabUserInfoId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long issueStatusId = jObj.getLong("issueStatusId");
			Long stateId = jObj.getLong("stateId");
			
			fieldMonitoringIssueVOList = fieldMonitoringService.getIssuesForATabUserByStatusNew(cadreSurveyUserId,tabUserInfoId,fromDateStr,toDateStr,issueStatusId,userId,stateId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesForATabUserByStatus()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getIssuesCountsForATabUserNew(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserInfoId = jObj.getLong("tabUserInfoId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			
			
			fieldMonitoringIssueVOList = fieldMonitoringService.getIssuesCountsForATabUserByStatusNew(cadreSurveyUserId,tabUserInfoId,fromDateStr,toDateStr,userId,stateId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesCountsForATabUser()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getIssuesCountsForATabUser(){
		try {
			jObj = new JSONObject(getTask());
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserInfoId = jObj.getLong("tabUserInfoId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long vendorId = jObj.getLong("vendor");
			String locationType = jObj.getString("locationType");
			Long locationVal = jObj.getLong("locationVal"); 
			
			fieldMonitoringIssueVOList = fieldMonitoringService.getIssuesCountsForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,fromDateStr,toDateStr,vendorId,locationType,locationVal);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesCountsForATabUser()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateStatusToACadreRegIssue(){
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
     		Long loginUserId = null;
     		if(regVO != null){
     			loginUserId = regVO.getRegistrationID();
     		}
     		
			jObj = new JSONObject(getTask());
			
			Long cadreRegIssueId = jObj.getLong("cadreRegIssueId");
			String description = jObj.getString("description");
			Long newStatusId = jObj.getLong("newStatusId");
			
			resultStatus = fieldMonitoringService.updateStatusToACadreRegIssue(cadreRegIssueId,description,newStatusId,loginUserId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesForATabUserByStatus()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String trackingRegIssueByRegIssueId(){
		try {
			
			
			jObj = new JSONObject(getTask());
			
			Long cadreRegIssueId = jObj.getLong("cadreRegIssueId");
			//Long stateId = jObj.getLong("stateId");
			
			fieldMonitoringIssueVOList = fieldMonitoringService.trackingRegIssueByRegIssueId(cadreRegIssueId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at trackingRegIssueByRegIssueId()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}

  public String getCadreRegIssueStatusType(){
		
		try {
			
			idAndNameVOList =fieldMonitoringService.getCadreRegIssueStatusType();
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreRegIssueStatusType()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
  public String getDistrictWiseIssueTypesCount(){
		try {
			
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
   		Long loginUserId = null;
   		if(regVO != null){
   			loginUserId = regVO.getRegistrationID();
   		}*/
   		
			jObj = new JSONObject(getTask());
			/*JSONArray stateIds = jObj.getJSONArray("stateIds");  
			List<Long> stateIdList = new ArrayList<Long>();
			for( int i=0;i<stateIds.length();i++){
				stateIdList.add(Long.valueOf(stateIds.getString(i)));
			}*/
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long issueStatusId = jObj.getLong("issueStatusId");
			Long stateId = jObj.getLong("stateId");
			
			idAndNameVOList = fieldMonitoringService.getDistrictWiseIssueTypesCount(fromDateStr,toDateStr,issueStatusId,stateId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseIssueTypesCount()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  
  public String getLocationWiseDetailedOverViewDetails(){
		try {
			
			jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			String locationType = jObj.getString("locationType");
			Long locationVal = jObj.getLong("locationVal");
			
			dataMonitoringVerificationVOList = fieldMonitoringService.getLocationWiseDetailedOverViewDetails(fromDateStr,toDateStr,locationType,locationVal);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseDetailedOverViewDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  public String getUserPerformanceDetailsByUser(){
		try {
			
			jObj = new JSONObject(getTask());
			
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserId = jObj.getLong("tabUserInfoId");
			
			userPerformanceList = fieldMonitoringService.getUserPerformanceDetailsByUser(cadreSurveyUserId, tabUserId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getUserPerformanceDetailsByUser()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  
  public String getFieldMonitoringUserWiseDetails(){
		try {
			
			/*jObj = new JSONObject(getTask());
			
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			Long tabUserId = jObj.getLong("tabUserInfoId");*/
			
			fieldMonitoringList = fieldMonitoringService.getFieldMonitoringUserWiseDetails(null);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getFieldMonitoringUserWiseDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  
  public String fieldMonitoringReport(){
	  try {
		  session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			boolean noaccess = false;
			if(regVO == null)
				return Action.INPUT;
			
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(!(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD".trim())))
				 {
				        noaccess = true ;  
				 }
			}
			
	} catch (Exception e) {
		LOG.error("Exception raised at fieldMonitoringReport()  of FieldMonitoringAction", e);
	}
	  return Action.SUCCESS;	
  }
  public String getDataCollectorsPerformanceDetails(){
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long loginUserId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			Long districtId = jObj.getLong("districtId");
			Long stateId = jObj.getLong("stateId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			fieldMonitoringVO = fieldMonitoringService.getDataCollectorsPerformanceDetails(loginUserId, districtId,stateId,constituencyId,cadreSurveyUserId,startDate,endDate);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDataCollectorsPerformanceDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  public String getDistrictByStateId(){
		
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long stateTypeId = jObj.getLong("stateTypeId");
			idAndNameVOList =fieldMonitoringService.getDistrictByStateId(stateId,stateTypeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictByStateId()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
  
  public String getConstituenciesByStateForStateTypeId(){
		
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long stateTypeId = jObj.getLong("stateTypeId");
			Long districtId = jObj.getLong("districtId");
			idAndNameVOList =fieldMonitoringService.getConstituenciesByStateForStateTypeId(stateId, stateTypeId, districtId);
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituenciesByStateForStateTypeId()  of FieldMonitoringAction", e);
		}
	
	    return Action.SUCCESS;
	}
  
  public String getConstituencyIssueWiseOverAllDetails(){
		try {
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long issueTypeId = jObj.getLong("issueType");
			Long issueStatusId = jObj.getLong("issueStatus");
			Long stateId = jObj.getLong("stateId");
			
			fieldMonitoringList = fieldMonitoringService.getConstituencyIssueWiseOverAllDetails(fromDateStr,toDateStr,issueTypeId,issueStatusId,stateId);
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituencyIssueWiseOverAllDetails()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  public String getDataCollectorsConstituencyWiseCount(){
		try {
			/*session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			Long loginUserId = regVO.getRegistrationID();*/
			
			fieldMonitoringList = fieldMonitoringService.getDataCollectorsConstituencyWiseCount();
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDataCollectorsDistrictWiseCountAction()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  public String getConstituencyWiseTodayAndOverAllCounts(){
		try {
			jObj = new JSONObject(getTask());
			
			String type = jObj.getString("type");
			Long stateId = jObj.getLong("stateId");
			String sortType = jObj.getString("sortType");
			
			idAndNameVOList = fieldMonitoringService.getConstituencyWiseTodayAndOverAllCounts(type,stateId,sortType);
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituencyWiseTodayAndOverAllCounts()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
  
  public String mediaScreenParty(){
	  return Action.SUCCESS;
  }
  
  public String saveUserPerformanceDetails(){
	  try{
		  	session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			Long loginUserId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			resultStatus = fieldMonitoringService.saveCaderSurveyUserPerformanceDetails(loginUserId,jObj.getLong("cadreSurveyUserId"),jObj.getLong("performanceTypeId"),jObj.getString("comment"));
		  
	  }catch(Exception e){
		  LOG.error("Exception raised at saveUserPerformanceDetails()  of FieldMonitoringAction", e);
	  }
	  return Action.SUCCESS;
  }
  
  public String getPerformnanceTypeList(){
	   try{
		   idAndNameVOList=fieldMonitoringService.getcadrePerformnanceTypeList();
	   }catch(Exception e){
		   LOG.error("Exception raised at getcadrePerformnanceTypeList()  of FieldMonitoringAction", e);
	   }
	   return Action.SUCCESS;
   }
  
  public String getcadrePerfrmanceList(){
	   try{
		   jObj = new JSONObject(getTask());
		   
		   idAndNameVOList=fieldMonitoringService.getcadrePerfrmanceList(jObj.getLong("cadreSurveyId"));
	   }catch(Exception e){
		   LOG.error("Exception raised at getcadrePerfrmanceList()  of FieldMonitoringAction", e);
	   }
	   return Action.SUCCESS;
  }
  public String mediaScreenPartyForTS(){
	  return Action.SUCCESS;
  }
  
  public String getDistrictWiseTodayAndOverAllCounts(){
		try {
			jObj = new JSONObject(getTask());
			
			String type = jObj.getString("type");
			Long stateId = jObj.getLong("stateId");
			String sortType = jObj.getString("sortType");
			
			idAndNameVOList = fieldMonitoringService.getDistrictWiseTodayAndOverAllCounts(type,stateId,sortType);
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseTodayAndOverAllCounts()  of FieldMonitoringAction", e);
		}
		return Action.SUCCESS;
	}
}
