package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICasteReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CasteReportAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
    private HttpSession session;
	private String task;
	JSONObject jObj;
	private String type;
	private Long constituencyId;
	private ICasteReportService casteReportService;
	private static final Logger LOG = Logger.getLogger(CasteReportAction.class);
	private List<CastVO> casteWiseResult,panchayats;
	private List<VoterHouseInfoVO> voterDetails;
	private ResultStatus resultStatus;
	private  List<SelectOptionVO> consMap;
	private List<Long> constValues;
	private Long topPanchayats;
	private Boolean notConsiderWeights;
	private String notConstiIds;
	private EntitlementsHelper entitlementsHelper;
	
	public Boolean getNotConsiderWeights() {
		return notConsiderWeights;
	}
	public void setNotConsiderWeights(Boolean notConsiderWeights) {
		this.notConsiderWeights = notConsiderWeights;
	}
	public String getNotConstiIds() {
		return notConstiIds;
	}
	public void setNotConstiIds(String notConstiIds) {
		this.notConstiIds = notConstiIds;
	}
	public List<CastVO> getPanchayats() {
		return panchayats;
	}
	public void setPanchayats(List<CastVO> panchayats) {
		this.panchayats = panchayats;
	}
	public List<Long> getConstValues() {
		return constValues;
	}
	public void setConstValues(List<Long> constValues) {
		this.constValues = constValues;
	}
	
	public List<SelectOptionVO> getConsMap() {
		return consMap;
	}
	public void setConsMap(List<SelectOptionVO> consMap) {
		this.consMap = consMap;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<VoterHouseInfoVO> getVoterDetails() {
		return voterDetails;
	}
	public void setVoterDetails(List<VoterHouseInfoVO> voterDetails) {
		this.voterDetails = voterDetails;
	}
	public List<CastVO> getCasteWiseResult() {
		return casteWiseResult;
	}
	public void setCasteWiseResult(List<CastVO> casteWiseResult) {
		this.casteWiseResult = casteWiseResult;
	}
	public ICasteReportService getCasteReportService() {
		return casteReportService;
	}
	public void setCasteReportService(ICasteReportService casteReportService) {
		this.casteReportService = casteReportService;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public JSONObject getjObj() {
		return jObj;
	}


	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public Long getTopPanchayats() {
		return topPanchayats;
	}
	public void setTopPanchayats(Long topPanchayats) {
		this.topPanchayats = topPanchayats;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		
		return Action.SUCCESS;
	}
		
public String getCasteWiseReport()
{
	try{
		Long userId = 0l;
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		else
	    userId = user.getRegistrationID();
		jObj = new JSONObject(getTask());
		
		casteWiseResult = casteReportService.getCasteWiseInfo(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),jObj.getString("type"),userId,jObj.getString("partialchecked"));
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in getCasteWiseReport() method",e);
	}
	return Action.SUCCESS;
}
public String getVoterAddressDetails()
{
	try{
		Long userId = 0l;
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		else
	    userId = user.getRegistrationID();
		jObj = new JSONObject(getTask());
		
		resultStatus =  casteReportService.getVoterAddressDetails(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),userId);
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in getVoterAddressDetails() method",e);
	}
	return Action.SUCCESS;
}
public String loadConst()
{
	try{

		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
	
		String[] notIds = null;
		 if(notConstiIds != null && notConstiIds.trim().length() > 0){
			 notIds = notConstiIds.trim().split(",");
		 }
		 List<Long> notIdsList = new ArrayList<Long>();
		 if(notIds != null && notIds.length > 0){
			 for(String id:notIds){
				 try{
					 notIdsList.add(Long.valueOf(id.trim()));
				 }catch(Exception e){
					 
				 }
			 }
		 }
		consMap=casteReportService.loadConstituenciesForReport(notIdsList);
		
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in loadConst() method",e);
	}
	return Action.SUCCESS;
}

public String getConstXL()
{
	try{
		
		HttpSession session = request.getSession();
		//RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		
		try{
			LOG.info("before   ===="+constValues);
			 if(request.getParameterMap().containsKey("constValues")){
			String[] ids=	 request.getParameterValues("constValues");
			constValues = new ArrayList<Long>();
			for (String string : ids) {
				
				constValues.add(Long.valueOf(string));
			}
			 }
			 LOG.info("after ==="+constValues);
			 boolean consdWeig = false;
			 if(notConsiderWeights != null && notConsiderWeights){
				 consdWeig = true;
			 }
			 String[] notIds = null;
			 if(notConstiIds != null && notConstiIds.trim().length() > 0){
				 notIds = notConstiIds.trim().split(",");
			 }
			 List<Long> notIdsList = new ArrayList<Long>();
			 if(notIds != null && notIds.length > 0){
				 for(String id:notIds){
					 try{
						 notIdsList.add(Long.valueOf(id.trim()));
					 }catch(Exception e){
						 
					 }
				 }
			 }
			resultStatus=casteReportService.generateXL(constValues,topPanchayats,consdWeig,notIdsList);
		 
			 
		   
		}catch(Exception e)
		{
			resultStatus = new ResultStatus();
			resultStatus.setResultCode(1);
		    resultStatus.setMessage(e.getMessage());
		    LOG.error("Exception Occured in getConstXL() method",e);
			return ERROR;
		}
	//	constValues
		return Action.SUCCESS;
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in loadConst() method",e);
	}
	return Action.SUCCESS;
}

public String getPanchayatsInVoterRange()
{
	try{
		Long userId = 0l;
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		else
	    userId = user.getRegistrationID();
		jObj = new JSONObject(getTask());
		
		panchayats =  casteReportService.getPanchayatsInVoterRange(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),userId,jObj.getBoolean("considerPartial"));
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in getVoterAddressDetails() method",e);
	}
	return Action.SUCCESS;
}
public String updatePriority()
{
	try{
		HttpSession session = request.getSession();
		//RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		casteReportService.updatePriority();
		
	}
	catch(Exception e)
	{
		LOG.error("Exception Occured in updatePriority() method",e);
	}
	return Action.SUCCESS;
}

}
