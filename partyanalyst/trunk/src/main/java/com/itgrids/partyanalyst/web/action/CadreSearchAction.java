package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAction extends ActionSupport implements ServletRequestAware 
{
	private HttpServletRequest request;
	private List<SelectOptionVO> socialStatus = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> eduStatus = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> partyCommitteesList;
	private List<SelectOptionVO> cadreSkillsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> partyTrainingCampsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
	private IStaticDataService staticDataService; 
	private CadreManagementService cadreManagementService;
	private HttpSession session;
	private String windowTask;
	
	
	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}

	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(List<SelectOptionVO> socialStatus) {
		this.socialStatus = socialStatus;
	}

	public List<SelectOptionVO> getEduStatus() {
		return eduStatus;
	}

	public void setEduStatus(List<SelectOptionVO> eduStatus) {
		this.eduStatus = eduStatus;
	}

	public List<SelectOptionVO> getPartyCommitteesList() {
		return partyCommitteesList;
	}

	public void setPartyCommitteesList(List<SelectOptionVO> partyCommitteesList) {
		this.partyCommitteesList = partyCommitteesList;
	}

	public List<SelectOptionVO> getCadreSkillsList() {
		return cadreSkillsList;
	}

	public void setCadreSkillsList(List<SelectOptionVO> cadreSkillsList) {
		this.cadreSkillsList = cadreSkillsList;
	}

	public List<SelectOptionVO> getPartyTrainingCampsList() {
		return partyTrainingCampsList;
	}

	public void setPartyTrainingCampsList(
			List<SelectOptionVO> partyTrainingCampsList) {
		this.partyTrainingCampsList = partyTrainingCampsList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String execute() throws Exception
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		
		windowTask = request.getParameter("windowTask");
		
		socialStatus = staticDataService.getAllSocialCategories(); 
		socialStatus.add(0, new SelectOptionVO(0L,"All"));
		
		eduStatus = staticDataService.getAllEducationalQualifications();
		eduStatus.add(0, new SelectOptionVO(0L,"All"));
		
		occupationsList = staticDataService.getAllOccupations();
		occupationsList.add(0, new SelectOptionVO(0L,"All"));
		
		if("Party".equals(regVO.getUserType()))
		{
			partyCommitteesList = cadreManagementService.getCommitteesForAParty(regVO.getParty());
			partyCommitteesList.add(0, new SelectOptionVO(0L,"All"));
			
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty());
			partyTrainingCampsList.add(0, new SelectOptionVO(0L,"All"));
			
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty());
			cadreSkillsList.add(0, new SelectOptionVO(0L,"All"));
		}
		
		return Action.SUCCESS;
	}
	

}
