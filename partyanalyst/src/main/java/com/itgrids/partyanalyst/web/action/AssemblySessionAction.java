package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AdminHouseVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAssemblySessionService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssemblySessionAction  extends ActionSupport implements ServletRequestAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220092157888269387L;
	private static final Logger LOG = Logger.getLogger(AssemblySessionAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	public  transient JSONObject jObj;
	private ResultStatus resultStatus;
	private IAssemblySessionService assemblySessionService;
	private List<AdminHouseVO> assemblyVOList;
	private AdminHouseVO adminHouseVO = new AdminHouseVO();
	private String status;
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(final ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(final HttpSession session) {
		this.session = session;
	}


	public String getTask() {
		return task;
	}


	public void setTask(final String task) {
		this.task = task;
	}
    //override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
		
	}
	
	public IAssemblySessionService getAssemblySessionService() {
		return assemblySessionService;
	}


	public void setAssemblySessionService(IAssemblySessionService assemblySessionService) {
		this.assemblySessionService = assemblySessionService;
	}
	
	public List<AdminHouseVO> getAssemblyVOList() {
		return assemblyVOList;
	}

	public void setHouseVOList(List<AdminHouseVO> assemblyVOList) {
		this.assemblyVOList = assemblyVOList;
	}

	public AdminHouseVO getAdminHouseVO() {
		return adminHouseVO;
	}


	public void setAdminHouseVO(AdminHouseVO adminHouseVO) {
		this.adminHouseVO = adminHouseVO;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String execute()
	{
		/*session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			if (!registrationVO.getIsAdmin().equals("true")){
				  return ERROR;
			}
		} 
		else{
			return ERROR;
		}*/
		return Action.SUCCESS;
		
	}
	
	public String getAllElecYears(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getAllElecYears();
			}catch(Exception e){
				LOG.error("Exception occured in getNoOfDaysForSessionDetails() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getAllSessionNames(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getAllSessionNames(jObj.getLong("elctionYearId"),jObj.getString("sessionYear"));
			}catch(Exception e){
				LOG.error("Exception occured in getAllSessionNames() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getSessionDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getNoOfDaysForSession(jObj.getLong("elctionYearId"),jObj.getString("sessionYear"),jObj.getLong("sessionId"),jObj.getString("startDate"),jObj.getString("endDate"));
			}catch(Exception e){
				LOG.error("Exception occured in getNoOfDaysForSession() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getSessionYears(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getSessionYears(jObj.getLong("elctionYearId"));
			}catch(Exception e){
				LOG.error("Exception occured in getSessionYears() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getDates(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getDates(jObj.getLong("elctionYearId"),jObj.getString("sessionYear"),jObj.getLong("sessionId"));
			}catch(Exception e){
				LOG.error("Exception occured in getDates() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getDayWiseDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getDayWiseDetails(jObj.getLong("adminHouseSessionDayId"));
			}catch(Exception e){
				LOG.error("Exception occured in getDayWiseDetails() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String updateMemberDetails(){
		try {
				status = assemblySessionService.updateMemberSpeechAspectDetails(adminHouseVO);
				
			} catch (Exception e) {
				LOG.error("Exception occured in updateMemberDetails() At AssemblySessionAction",e);
			}
			return Action.SUCCESS;
	}
	public String deleteMemberDetails(){
		try {
				status = assemblySessionService.deleteMemberDetails(adminHouseVO);
				
			} catch (Exception e) {
				LOG.error("Exception occured in deleteMemberDetails() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}
	public String getParties(){
	try{
		jObj = new JSONObject(getTask());
		
		assemblyVOList = assemblySessionService.getAllParties();
		}catch(Exception e){
			LOG.error("Exception occured in getSessionYears() At AssemblySessionAction",e);
		}
	return Action.SUCCESS;
	}
	public String getcandidatesByPartyId(){
		try{
			jObj = new JSONObject(getTask());
			
			assemblyVOList = assemblySessionService.getCandidateNameForParty(jObj.getLong("partyId"));
			}catch(Exception e){
				LOG.error("Exception occured in getcandidatesByPartyId() At AssemblySessionAction",e);
			}
		return Action.SUCCESS;
	}

}
