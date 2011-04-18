package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DataApprovalAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DataApprovalAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	JSONObject jObj = null;
	private String task = null;
	private IDataApprovalService dataApprovalService; 
	private ResultStatus rs;
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IDataApprovalService getDataApprovalService() {
		return dataApprovalService;
	}

	public void setDataApprovalService(IDataApprovalService dataApprovalService) {
		this.dataApprovalService = dataApprovalService;
	}	
	
	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}
	
	public String execute() throws Exception {
		return Action.SUCCESS;		
	}
	
	public String ajaxCallHandler() throws Exception{
		session = request.getSession();
		String param = null;
		param = getTask();
		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
		if(jObj.getString("task").equalsIgnoreCase("saveProblemApprovalData"))
		{			
			approvalInfoVO.setReason(jObj.getString("reason"));
			approvalInfoVO.setProblemHistoryId(jObj.getLong("id"));
			approvalInfoVO.setUserId(user.getRegistrationID());
			approvalInfoVO.setIsApproved(jObj.getString("isAccepted"));
			rs = dataApprovalService.saveProblemsApprovalData(approvalInfoVO);
		}
		if(jObj.getString("task").equalsIgnoreCase("checkApprovalStatus"))
		{			
			
			approvalInfoVO.setProblemHistoryId(jObj.getLong("id"));
			approvalInfoVO.setUserId(user.getRegistrationID());
			
			rs = ((IDataApprovalService) dataApprovalService).checkApprovalStatus(approvalInfoVO);
		}
		return Action.SUCCESS;
	}
	
	

}
