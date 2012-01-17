package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemCompleteDetailsAction extends ActionSupport implements
ServletRequestAware, ServletContextAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext context;
	private IProblemManagementService problemManagementService;
	private ProblemBeanVO problemBeanVO;
	JSONObject jObj = null;
	private String task;
	private static final Logger log = Logger.getLogger(ProblemCompleteDetailsAction.class);
	private Long problemHistoryId;
	private Boolean logInStatus = null;
	private String userType = null; 
	private HttpSession session;
	private List<ApprovalInfoVO> problemApproovals = new ArrayList<ApprovalInfoVO>(0);
	private IDataApprovalService dataApprovalService;
	private Long userId;
	private boolean hasFileUploadRight;
	
	
	public boolean isHasFileUploadRight() {
		return hasFileUploadRight;
	}

	public void setHasFileUploadRight(boolean hasFileUploadRight) {
		this.hasFileUploadRight = hasFileUploadRight;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
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

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
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

	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}	

	public Boolean getLogInStatus() {
		return logInStatus;
	}

	public void setLogInStatus(Boolean logInStatus) {
		this.logInStatus = logInStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}	
	
	public List<ApprovalInfoVO> getProblemApproovals() {
		return problemApproovals;
	}

	public void setProblemApproovals(List<ApprovalInfoVO> problemApproovals) {
		this.problemApproovals = problemApproovals;
	}	

	public IDataApprovalService getDataApprovalService() {
		return dataApprovalService;
	}

	public void setDataApprovalService(IDataApprovalService dataApprovalService) {
		this.dataApprovalService = dataApprovalService;
	}

	public String execute(){
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		if(user !=null){
			userId = user.getRegistrationID();
			logInStatus = true;
			userType = user.getUserType();
			hasFileUploadRight = dataApprovalService.checkUserFileUploadRight(userId , problemHistoryId);
		}
		else
			logInStatus = false;
		return Action.SUCCESS;
	}
	
	public String getProblemCompleteDetails(){
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		if(log.isDebugEnabled())
			log.debug("Task::"+jObj.getString("task"));
		Long problemHistoryId = jObj.getLong("problemHistoryId");
		log.info("problemHistoryId:"+problemHistoryId);
		
		problemBeanVO = problemManagementService.getProblemCompleteInfo(problemHistoryId);
		return Action.SUCCESS;
	}
	
	public String ajaxCallHandler() throws Exception{
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		
		int resultsCount = Integer.parseInt(request.getParameter("resultsCount"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		
		if(jObj.getString("task").equalsIgnoreCase("getProblemAllComments"))
		{			
			Long id = jObj.getLong("id");
			problemBeanVO = dataApprovalService.getAllProblemComments(id, startIndex,resultsCount);
		}
		return Action.SUCCESS;
	}

	

}
