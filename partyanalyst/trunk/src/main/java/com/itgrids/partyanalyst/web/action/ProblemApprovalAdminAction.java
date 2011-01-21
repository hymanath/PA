package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemApprovalAdminAction extends ActionSupport implements ServletRequestAware, ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ProblemApprovalAdminAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private IDataApprovalService dataApprovalService; 
	private List<ApprovalInfoVO> approvalInfovo = new ArrayList<ApprovalInfoVO>(0);
	private ServletContext context;
	
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
	
	public List<ApprovalInfoVO> getApprovalInfovo() {
		return approvalInfovo;
	}

	public void setApprovalInfovo(List<ApprovalInfoVO> approvalInfovo) {
		this.approvalInfovo = approvalInfovo;
	}

	public String execute() throws Exception 
	
	{
		return Action.SUCCESS;		
	}
	
	
	public String dataApprovalajaxCallHandler() throws Exception{
		session = request.getSession();
		String rparam = null;
		rparam = getTask();
		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		log.debug("Task::"+jObj.getString("task"));
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		
		
		
	
		
		approvalInfovo= dataApprovalService.userApprovalDetailsbetweenDates(fromDate,toDate);
		if(log.isInfoEnabled())
			log.info("userapprovaldetailsSize:"+approvalInfovo.size());

		
		return Action.SUCCESS;
	}
	public String problemcontrolApprovals()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
				
		String task = jObj.getString("task");
		JSONArray jArray = jObj.getJSONArray("checkedElmts");
		List<Long> approvalDetailsIds = new ArrayList<Long>();
		
	
		
		String approvedStatus=null;
		approvedStatus = jObj.getString("status");
		
			
		for (int i = 0; i < jArray.length(); i++) 
		{
			approvalDetailsIds.add(new Long(jArray.getString(i)));		
		}
		
		approvalInfovo = dataApprovalService.scrutinizePostedApprovals(approvalDetailsIds, approvedStatus);
		
		return Action.SUCCESS;
	}

}
