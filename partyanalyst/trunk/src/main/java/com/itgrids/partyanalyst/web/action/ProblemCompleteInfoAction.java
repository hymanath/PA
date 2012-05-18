package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemCompleteInfoAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private IProblemManagementService problemManagementService;
	private ProblemBeanVO problemBeanVO;
	private static final Logger log = Logger.getLogger(ProblemCompleteInfoAction.class);
	private Long problemHistoryId;
	private Boolean logInStatus = false;
	private String userType = null;
	private boolean hasFileUploadRight;
	private IDataApprovalService dataApprovalService;
	
	
	public IDataApprovalService getDataApprovalService() {
		return dataApprovalService;
	}

	public void setDataApprovalService(IDataApprovalService dataApprovalService) {
		this.dataApprovalService = dataApprovalService;
	}

	public boolean isHasFileUploadRight() {
		return hasFileUploadRight;
	}

	public void setHasFileUploadRight(boolean hasFileUploadRight) {
		this.hasFileUploadRight = hasFileUploadRight;
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
	public Long getProblemHistoryId() {
		return problemHistoryId;
	}
	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
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
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}
	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}
	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}
	public void setServletContext(ServletContext context) {
		this.context = context ;
	}
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	public String execute()
	{
		Long userId = null;
		try{
			
			session = request.getSession();
			
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO != null)
			{
				userId = regVO.getRegistrationID();
				logInStatus = true;
				userType = regVO.getUserType();
				hasFileUploadRight = dataApprovalService.checkUserFileUploadRight(userId , problemHistoryId);
				
			}
			else
				logInStatus = false;
			
			problemBeanVO = problemManagementService.getProblemCompleteInfoOfAFreeUserProblem(problemHistoryId,userId);
			
			if(problemBeanVO != null && problemBeanVO.getExceptionEncountered() == null)
				return SUCCESS;
			else
				return ERROR;
			
			}catch (Exception e){
				log.error("Exception occured in getProblemCompleteInfo() method , Exception is - "+e);
				return ERROR;
			}
			
			
	}
	
}
