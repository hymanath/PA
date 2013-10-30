package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
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
import com.itgrids.partyanalyst.dto.ProblemSearchVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemSearchAction extends ActionSupport implements ServletRequestAware , ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private static final Logger log = Logger.getLogger(ProblemSearchAction.class);
	private IProblemManagementService problemManagementService;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> selectOptionList;
	private List<ProblemBeanVO> problemBeanVOList;
	private ProblemSearchVO problemSearchVO;
	private List<CompleteProblemDetailsVO> problemDetails;
	CompleteProblemDetailsVO completeProblemDetailsVO = null;
	public List<CompleteProblemDetailsVO> getProblemDetails() {
		return problemDetails;
	}
	public void setProblemDetails(List<CompleteProblemDetailsVO> problemDetails) {
		this.problemDetails = problemDetails;
	}

	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public ProblemSearchVO getProblemSearchVO() {
		return problemSearchVO;
	}
	public void setProblemSearchVO(ProblemSearchVO problemSearchVO) {
		this.problemSearchVO = problemSearchVO;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
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
	public void setServletContext(ServletContext context) {
		this.context = context;	
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}
	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
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
	
	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}
	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}
	
	public List<ProblemBeanVO> getProblemBeanVOList() {
		return problemBeanVOList;
	}
	public void setProblemBeanVOList(List<ProblemBeanVO> problemBeanVOList) {
		this.problemBeanVOList = problemBeanVOList;
	}
	
	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try{
		
		jObj = new JSONObject(getTask());
		    if(jObj.getString("task").equalsIgnoreCase("getStates"))
			{
				selectOptionList = problemManagementService.getStates();
				
			}
			else if(jObj.getString("task").equalsIgnoreCase("getDistrictsForASelectedState"))
			{
				selectOptionList = 	problemManagementService.getDistrictsByAStateID(jObj.getLong("stateId"));
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getProblemsPostedUsersList"))
			{
				selectOptionList = problemManagementService.getProblemPostedUserDetails();
			}
			else if(jObj.getString("task").equalsIgnoreCase("getProblemtypes"))
			{
				selectOptionList = problemManagementService.getProblemTypes();
			}
			else if(jObj.getString("task").equalsIgnoreCase("deleteProblemDetails"))
			{
				resultStatus = problemManagementService.deleteProblemDetails(jObj.getLong("problemId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getProblemDetailsForUpdate"))
			{
				problemDetails = problemManagementService.getProblemDetailsForUpdate(jObj.getLong("problemId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("updateProblemDetails"))
			{
				problemDetails = new ArrayList<CompleteProblemDetailsVO>();
				completeProblemDetailsVO = new CompleteProblemDetailsVO();
				completeProblemDetailsVO.setProblemTitle(jObj.getString("problemtitle"));
				completeProblemDetailsVO.setProblemDesc(jObj.getString("problemDesc"));
				completeProblemDetailsVO.setExistingFrom(jObj.getString("existingFrom"));
				completeProblemDetailsVO.setProblemTypeId(jObj.getLong("problemType"));
				
				problemDetails.add(completeProblemDetailsVO);
				resultStatus = problemManagementService.updateProblemDetails(jObj.getLong("problemId"),problemDetails);
			}
		    
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in AjaxHandler() Method in ProblemSearchAction class , Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getProblemDetailsForFreeUser()
	{
		try{
			jObj = new JSONObject(getTask());
			int maxIndex = Integer.parseInt(request.getParameter("resultsCount"));
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			problemSearchVO = new ProblemSearchVO();
			problemSearchVO.setScopeAll(jObj.getBoolean("scopeAll"));
			problemSearchVO.setStatusAll(jObj.getBoolean("statusAll"));
			problemSearchVO.setTypeAll(jObj.getBoolean("typeAll"));
			problemSearchVO.setUsersAll(jObj.getBoolean("usersAll"));
			
			if(!jObj.getString("scopeId").equalsIgnoreCase("null"))
			problemSearchVO.setScopeId(jObj.getLong("scopeId"));
			
			if(!jObj.getString("locationValue").equalsIgnoreCase("null"))
			problemSearchVO.setLocationValue(jObj.getLong("locationValue"));
			if(!jObj.getString("statusId").equalsIgnoreCase("null"))
			problemSearchVO.setStatusId(jObj.getLong("statusId"));
			if(!jObj.getString("typeId").equalsIgnoreCase("null"))
			problemSearchVO.setTypeId(jObj.getLong("typeId"));
			if(!jObj.getString("userId").equalsIgnoreCase("null"))
			problemSearchVO.setUserId(jObj.getLong("userId"));
			
			problemBeanVOList = problemManagementService.getProblemDetailsForFreeUser(problemSearchVO,startIndex,maxIndex);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getProblemDetailsForFreeUser() Method , Exception - "+e);
		}
		return Action.SUCCESS;
	}

}
