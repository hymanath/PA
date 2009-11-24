package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ResultObjectVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportAjaxAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private String electionYear;
	private String parliamentValue;
	private String assemblyValue;
	private String party;
	private String includeAliance;
	private boolean hasAliance;
	private boolean isPartyParticipated;
	private HttpServletRequest request;
	private List<SelectOptionVO> parliamentList;
	private CrossVotingConsolidateVO crossVotingConsolidateVO;
	private ResultObjectVO resultObjectVO; 
	private ICrossVotingEstimationService crossVotingEstimationService;
	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	
	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getIncludeAliance() {
		return includeAliance;
	}

	public void setIncludeAliance(String includeAliance) {
		this.includeAliance = includeAliance;
	}

	public boolean getHasAliance() {
		return hasAliance;
	}

	public void setHasAliance(boolean hasAliance) {
		this.hasAliance = hasAliance;
	}

	public boolean getPartyParticipated() {
		return isPartyParticipated;
	}

	public void setPartyParticipated(boolean isPartyParticipated) {
		this.isPartyParticipated = isPartyParticipated;
	}
	
	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}

	public String getParliamentValue() {
		return parliamentValue;
	}

	public void setParliamentValue(String parliamentValue) {
		this.parliamentValue = parliamentValue;
	}

	public String getAssemblyValue() {
		return assemblyValue;
	}

	public void setAssemblyValue(String assemblyValue) {
		this.assemblyValue = assemblyValue;
	}

	public CrossVotingConsolidateVO getCrossVotingConsolidateVO() {
		return crossVotingConsolidateVO;
	}

	public void setCrossVotingConsolidateVO(
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}
	
	public ResultObjectVO getResultObjectVO() {
		return resultObjectVO;
	}

	public void setResultObjectVO(ResultObjectVO resultObjectVO) {
		this.resultObjectVO = resultObjectVO;
	}
	
	public String execute()throws Exception{
		
		System.out.println("In Execute of ajax");
		
		electionYear = request.getParameter("election");
		party = request.getParameter("party");
		parliamentValue = request.getParameter("parliamentValue");
		assemblyValue = request.getParameter("assemblyValue");
		
		resultObjectVO = new ResultObjectVO();
		System.out.println("year == "+electionYear+",party == "+party+",parliament = "+parliamentValue+",assembly = "+assemblyValue);
		
		if(electionYear != null && party == null && parliamentValue != null && assemblyValue == null)
		{
			System.out.println("IN election year");
			
			parliamentList = new ArrayList<SelectOptionVO>();	
			
			parliamentList = crossVotingEstimationService.getAssembliesForParliament(new Long(parliamentValue.trim()),new Long(electionYear.trim()));
			System.out.println("Parliament Consties List Size $$$$$$$$$"+parliamentList.size());
			
			resultObjectVO.setDataList(parliamentList);	
			
			
		}
		else if(electionYear != null && party == null && parliamentValue == null && assemblyValue != null)
		{
			System.out.println("IN parliament value");
			
			parliamentList = crossVotingEstimationService.getPartiesForConstituency(new Long(assemblyValue), electionYear);
			
			System.out.println("Parliament Consties List Size $$$$$$$$$"+parliamentList.size());
			
			resultObjectVO.setDataList(parliamentList);	
			
		}
		else if(electionYear != null && party != null && parliamentValue != null && assemblyValue != null && includeAliance != null)
		{
			System.out.println("IN assembly values = "+electionYear+"**"+party+"**"+parliamentValue+"**"+assemblyValue);
			
			System.out.println("Before service execution===================IncludeAliance:"+includeAliance);			
			
			crossVotingConsolidateVO = crossVotingEstimationService.getConsolidatedCrossVotingDetails(electionYear, new Long(party.trim()), new Long(assemblyValue.trim()), new Long(parliamentValue.trim()), includeAliance);
			isPartyParticipated = crossVotingConsolidateVO.getPartyPartisipated();
			hasAliance = crossVotingConsolidateVO.getHasAlliance();
			System.out.println("Size = "+crossVotingConsolidateVO.getMandals().size());
			System.out.println("After service execution");
			
			resultObjectVO.setCrossVotingConsolidateVO(crossVotingConsolidateVO);	
		}		
				
		return Action.SUCCESS;
	}

}
