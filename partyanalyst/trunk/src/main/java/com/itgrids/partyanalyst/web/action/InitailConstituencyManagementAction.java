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

import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class InitailConstituencyManagementAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List<SelectOptionVO> statusList;
	private static final Logger log = Logger.getLogger(InitailConstituencyManagementAction.class);
	private IProblemManagementReportService problemManagementReportService;
	private String accessType;
	private Long accessValue;
	private String task = null;
	JSONObject jObj = null;
	private List<ProblemBeanVO> problemsList;
	private String EXTERNAL_PERSON;
	
	public String getEXTERNAL_PERSON() {
		return EXTERNAL_PERSON;
	}

	public void setEXTERNAL_PERSON(String external_person) {
		EXTERNAL_PERSON = external_person;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<SelectOptionVO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectOptionVO> statusList) {
		this.statusList = statusList;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Long getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}	
	
	public List<ProblemBeanVO> getProblemsList() {
		return problemsList;
	}

	public void setProblemsList(List<ProblemBeanVO> problemsList) {
		this.problemsList = problemsList;
	}

	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
				
		accessType = user.getAccessType();
		accessValue= new Long(user.getAccessValue());
		
		EXTERNAL_PERSON = IConstants.EXTERNAL_PERSON;
		
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		statusList.add(0,new SelectOptionVO(-1l, "Select Status"));
		//String accessType, Long accessValue, Long statusId, int limit
		LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = problemManagementReportService.getRecentProblemsWithInTheRegion(accessType, accessValue, 1l, 10);
		problemsList = locationwiseProblemStatusInfoVO.getRecentProblems();		
		
		return SUCCESS;
	}
	
	public String getProblemDetailsByStatus()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			String status =  jObj.getString("status");
			Long locationId = new Long(jObj.getLong("locationId"));//contains access value
			String accessType = jObj.getString("accessType");	
			problemsList = problemManagementReportService.getProblemsInfoByStatusInALocation(locationId, accessType, user.getRegistrationID(), status);
		
		return SUCCESS;
	}

}
