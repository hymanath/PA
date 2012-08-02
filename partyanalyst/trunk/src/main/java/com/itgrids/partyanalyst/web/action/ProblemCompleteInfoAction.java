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

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
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
	private String task;
	JSONObject jObj = null;
	private ResultStatus resultStatus;
	private String avgRatingOfAProblem; 
	private List<CompleteProblemDetailsVO> rateWiseCountOfAProblem;
	private CompleteProblemDetailsVO completeProblemDetailsVO;
	
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
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getAvgRatingOfAProblem() {
		return avgRatingOfAProblem;
	}

	public void setAvgRatingOfAProblem(String avgRatingOfAProblem) {
		this.avgRatingOfAProblem = avgRatingOfAProblem;
	}

	public List<CompleteProblemDetailsVO> getRateWiseCountOfAProblem() {
		return rateWiseCountOfAProblem;
	}

	public void setRateWiseCountOfAProblem(
			List<CompleteProblemDetailsVO> rateWiseCountOfAProblem) {
		this.rateWiseCountOfAProblem = rateWiseCountOfAProblem;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public CompleteProblemDetailsVO getCompleteProblemDetailsVO() {
		return completeProblemDetailsVO;
	}

	public void setCompleteProblemDetailsVO(
			CompleteProblemDetailsVO completeProblemDetailsVO) {
		this.completeProblemDetailsVO = completeProblemDetailsVO;
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
	
	public String ajaxCallHandler()
	{
		
		
		try{
			jObj = new JSONObject(getTask());
		}catch (ParseException e) {
			e.printStackTrace();
			log.error("Exception Occured in ajaxCallHandler() Method in ProblemCompleteInfoAction," +
					" Exception - "+e);
		}
		if(jObj.getString("task").equals("saveProblemRatingDetails"))
		{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			Long userId = null;
			if(regVO == null)
				return Action.SUCCESS;
			userId = regVO.getRegistrationID();
			resultStatus = problemManagementService.saveRatingOfAProblem(userId, jObj.getLong("problemId"),jObj.getString("rating"));
		}
		else if(jObj.getString("task").equals("getAvgProblemRating"))
		{
			completeProblemDetailsVO = problemManagementService.getAverageRatingOfAProblem(jObj.getLong("problemId"));
		}
		else if(jObj.getString("task").equals("rateWiseCountOfAProblem"))
		{
			rateWiseCountOfAProblem = problemManagementService.getRatingWiseCountOfAProblem(jObj.getLong("problemId"));
		}
		return Action.SUCCESS;
	}
	
}
