package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FieldMonitoringAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(FieldMonitoringAction.class);
	
	//instance variables
		private HttpServletRequest request;
		private JSONObject jObj;
		private String task;
		private List<IdAndNameVO> idAndNameVOList;  
		private ResultStatus resultStatus; 
		private FieldMonitoringVO fieldMonitoringVO;
		private List<FieldMonitoringVO> fieldMonitoringList;
		private List<FieldMonitoringIssueVO> fieldMonitoringIssueVOList;
		
	//Attributes
	   private IFieldMonitoringService fieldMonitoringService;
	
	 //implementation methods
	   public void setServletRequest(HttpServletRequest request) {
			this.request = request;
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

	//Business methods
	public String execute(){
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

public String getIssueStatusWiseCounts(){
	
	try {
		jObj = new JSONObject(getTask());
		String fromDateStr = jObj.getString("fromDate");
		String toDateStr = jObj.getString("toDate");
		idAndNameVOList =fieldMonitoringService.getIssueStatusWiseCounts(fromDateStr,toDateStr);
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
	idAndNameVOList =fieldMonitoringService.getIssueTypeWiseCounts(fromDateStr,toDateStr);
} catch (Exception e) {
	LOG.error("Exception raised at getStatusWiseIssueTypeCount()  of FieldMonitoringAction", e);
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
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			
			fieldMonitoringVO = fieldMonitoringService.getOverAllDataCollectorsDetails(fromDateStr,toDateStr);
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
			
			fieldMonitoringList = fieldMonitoringService.getStatusWiseIssuesDetails(fromDateStr,toDateStr,issueTypeId,issueStatusId);
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
			
			fieldMonitoringIssueVOList = fieldMonitoringService.getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,fromDateStr,toDateStr,issueStatusId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getIssuesForATabUserByStatus()  of FieldMonitoringAction", e);
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
}
