package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.MissedCallsDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreMissedCallCampaignAction  extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private HttpSession session;
	private String 	task;
	private JSONObject	jobj;
	private MissedCallsDetailsVO result;
	private List<MissedCallsDetailsVO> resultList;
	private ICadreRegistrationService cadreRegistrationService;
	@Autowired 
	private IWebServiceHandlerService1 webServiceHandlerService1;
	private String  returnString;
	
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public MissedCallsDetailsVO getResult() {
		return result;
	}

	public void setResult(MissedCallsDetailsVO result) {
		this.result = result;
	}

	public List<MissedCallsDetailsVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<MissedCallsDetailsVO> resultList) {
		this.resultList = resultList;
	}

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreMissedCallCampaignAction");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return Action.INPUT;	
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreMissedCallCampaignAction",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getMissedCallDetails(){
		try{
			jobj = new JSONObject(getTask());			
			Long stateId= jobj.getLong("stateId");
			String startDate=jobj.getString("fromDate");
			String endDate=jobj.getString("toDate");
			result = cadreRegistrationService.getMissedCallDetail(startDate,endDate,stateId);
		}catch(Exception e){
			LOG.error("Exception raised in getMissedCallDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMissedCallDetailsDistrictWise(){
		try{
			jobj = new JSONObject(getTask());			
			Long stateId= jobj.getLong("stateId");
			String startDate=jobj.getString("fromDate");
			String endDate=jobj.getString("toDate");
			
			String task = jobj.getString("task");
		
			resultList = cadreRegistrationService.getMissedCallDetailByDistrict(startDate,endDate,stateId,task);
		}catch(Exception e){
			LOG.error("Exception raised in getMissedCallDetailsDistrictWise ",e);
		}
		return Action.SUCCESS;
	}
	
	public String missedCallDetailsForADistrict(){
		
		try{
			jobj = new JSONObject(getTask());			
			Long districtId= jobj.getLong("districtId");
			String startDateString=jobj.getString("fromDate");
			String endDateString=jobj.getString("toDate");
			
			resultList = cadreRegistrationService.missedCallDetailsForADistrict(districtId,startDateString,endDateString);
		}catch(Exception e){
			LOG.error("Exception raised in getMissedCallDetailsDistrictWise ",e);
		}
		return Action.SUCCESS;	
	
	}
	
	public String cadreMissedCallCampaignUrl(){
		LOG.debug(" in cadreMissedCallCampaign ");
		try{
			MissedCallCampaignVO vo = new MissedCallCampaignVO();
			vo.setRing_time(Long.valueOf(request.getParameter("smscresponse[Ring_time]")));
			vo.setFrom(request.getParameter("smscresponse[from]"));
			vo.setId(1L);
			if((vo.getFrom() != null && vo.getFrom().trim().length() >0 ) && (vo.getRing_time() != null)){
				returnString =  webServiceHandlerService1.saveMissedCallDetails(vo);
				
			}else{
				returnString =  "Invalid Inputs !";
			}
		}catch (Exception e) {
			LOG.error(" Exception in cadreMissedCallCampaign " + e);
		}
		return Action.SUCCESS;
	}
	
	
}
