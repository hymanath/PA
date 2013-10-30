package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResultAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> partyVOs;
	private List<SelectOptionVO> electionTypes,parlConstis,assemblyConstis;
	private List<String> electionYears;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	private ICrossVotingEstimationService crossVotingEstimationService;
	public List<SelectOptionVO> getPartyVOs() {
		return partyVOs;
	}

	public void setPartyVOs(List<SelectOptionVO> partyVOs) {
		this.partyVOs = partyVOs;
	}
	

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public List<String> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	public List<SelectOptionVO> getParlConstis() {
		return parlConstis;
	}

	public void setParlConstis(List<SelectOptionVO> parlConstis) {
		this.parlConstis = parlConstis;
	}

	public List<SelectOptionVO> getAssemblyConstis() {
		return assemblyConstis;
	}

	public void setAssemblyConstis(List<SelectOptionVO> assemblyConstis) {
		this.assemblyConstis = assemblyConstis;
	}

	public String execute()throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return ERROR;
		electionTypes = new ArrayList<SelectOptionVO>();		
			
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long userId = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		
		assemblyConstis = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userId,electionYear,electionTypeId);
		parlConstis = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userId,electionYear,1l);
		
		List<Long> userAccessAssembConsIds = new ArrayList<Long>(0);
		List<Long> userAccessParConsIds = new ArrayList<Long>(0);
		
		if(assemblyConstis != null && assemblyConstis.size() > 0)
		{
			SelectOptionVO electionType2 = new SelectOptionVO();
			electionType2.setId(new Long(2));
			electionType2.setName("Assembly");
			electionTypes.add(electionType2);
			
			for(SelectOptionVO optionVO:assemblyConstis)
			 if(!userAccessAssembConsIds.contains(optionVO.getId()))
				 userAccessAssembConsIds.add(optionVO.getId());
			
		}
		
		if(parlConstis != null && parlConstis.size() > 0)
		{
			SelectOptionVO electionType1 = new SelectOptionVO();
			electionType1.setId(new Long(1));
			electionType1.setName("Parliament");
			electionTypes.add(electionType1);
			
			for(SelectOptionVO optionVO:parlConstis)
			 if(!userAccessParConsIds.contains(optionVO.getId()))
				 userAccessParConsIds.add(optionVO.getId());
		}
		setElectionTypes(electionTypes);
		
		
		session.setAttribute("userAccessAssembConsIds", userAccessAssembConsIds);
		session.setAttribute("userAccessParConsIds", userAccessParConsIds);
		
		
		/*electionYears = new ArrayList<String>();	
		electionYears.add("2009");
		electionYears.add("2008");
		electionYears.add("2006");
		electionYears.add("2004");
		setElectionYears(electionYears);*/
		
		electionYears = crossVotingEstimationService.getElectionYearsForBoothResult();
		System.out.println("before success party Booth results action");
		return SUCCESS;	
		
	}
	
	
	
}
