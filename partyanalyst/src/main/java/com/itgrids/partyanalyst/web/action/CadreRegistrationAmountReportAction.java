package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreRegAmountDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegistrationAmountReportAction extends ActionSupport{

	private static final Logger         		LOG = Logger.getLogger(CadreRegistrationAmountReportAction.class);
	private HttpSession 						session;
	private HttpServletRequest                  request;
	private JSONObject jObj;
	private String task; 
	private List<CadreAmountDetailsVO> cadreAmountList;
	private ICadreRegAmountDetailsService cadreRegAmountDetailsService;
	
	

	public ICadreRegAmountDetailsService getCadreRegAmountDetailsService() {
		return cadreRegAmountDetailsService;
	}
	public void setCadreRegAmountDetailsService(
			ICadreRegAmountDetailsService cadreRegAmountDetailsService) {
		this.cadreRegAmountDetailsService = cadreRegAmountDetailsService;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	
	
	public List<CadreAmountDetailsVO> getCadreAmountList() {
		return cadreAmountList;
	}
	public void setCadreAmountList(List<CadreAmountDetailsVO> cadreAmountList) {
		this.cadreAmountList = cadreAmountList;
	}
	public String execute(){
		try {
			LOG.info("Entered into execute method in CadreRegistrationAmountReportAction Action");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
		} catch (Exception e) {
			LOG.info("Entered into execute method in  CadreRegistrationAmountReportAction Action");
		}
		return Action.SUCCESS;
	}
	public String getCadreRegAmountReport()
	{
		try{
			request = ServletActionContext.getRequest();
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String reportValue = request.getParameter("reportValue");
			
			cadreAmountList = cadreRegAmountDetailsService.getCadreRegAmountDetails(startDate,endDate,reportValue);
			
		}
		catch(Exception e){
			LOG.info("Entered into getCadreRegAmountReport method in  CadreRegistrationAmountReportAction Action");
		}
		return Action.SUCCESS;
	}
	
	
}
