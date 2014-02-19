package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICasteReportService;
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
	private static final Logger log = Logger.getLogger(CasteReportAction.class);
	private List<CastVO> casteWiseResult;
	private List<VoterHouseInfoVO> voterDetails;
	private ResultStatus resultStatus;
	
	
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
		
		casteWiseResult = casteReportService.getCasteWiseInfo(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),jObj.getString("type"),userId);
		
	}
	catch(Exception e)
	{
		log.error("Exception Occured in getCasteWiseReport() method",e);
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
		log.error("Exception Occured in getVoterAddressDetails() method",e);
	}
	return Action.SUCCESS;
}

}
