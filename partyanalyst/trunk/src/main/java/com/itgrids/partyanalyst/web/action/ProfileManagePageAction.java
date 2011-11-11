package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ProfileManagePageAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 2619726916593528832L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Long candidateId;
	private EntitlementsHelper entitlementsHelper;
	private List<SelectOptionVO> candidatesList;
	
	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}

	private ICandidateDetailsService candidateDetailsService;
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute()  {
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
			return IConstants.NOT_LOGGED_IN;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
			return ERROR;
		
		candidatesList = candidateDetailsService.getCandidatesOfAUser(user.getRegistrationID());
		
		if(candidatesList == null)
			candidatesList = new ArrayList<SelectOptionVO>(0);
		
		return SUCCESS;
	}

}
