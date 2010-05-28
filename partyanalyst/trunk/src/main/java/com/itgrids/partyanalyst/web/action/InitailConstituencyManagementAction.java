package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
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

	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
				
		accessType = user.getAccessType();
		accessValue= new Long(user.getAccessValue());
		
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		statusList.add(0,new SelectOptionVO(0l, "Select Status"));
		
		return SUCCESS;
	}

}
