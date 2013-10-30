package com.itgrids.partyanalyst.web.action;

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
		includeAliance = request.getParameter("includeAliance");
		resultObjectVO = new ResultObjectVO();
		
		System.out.println("year == "+electionYear+",party == "+party+",parliament = "+parliamentValue+",assembly = "+assemblyValue+", alliance = "+includeAliance);
		if(electionYear != null && party == null && parliamentValue == null && assemblyValue == null){
			System.out.println("IN parliament if");
			parliamentList = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(electionYear.trim(), new Long(1));
			System.out.println("Parliament Constituencies Size::"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		
		if(electionYear != null && party == null && parliamentValue != null && assemblyValue == null)
		{
			System.out.println("IN election year");
			parliamentList = crossVotingEstimationService.getAssembliesForParliament(new Long(parliamentValue.trim()), new Long(electionYear.trim()));
			System.out.println("Assemblies Consties List Size $$$$$$$$$"+parliamentList.size());			
			resultObjectVO.setDataList(parliamentList);				
		}
		else if(electionYear != null && party == null && parliamentValue == null && assemblyValue != null)
		{
			System.out.println("IN parliament value");
			parliamentList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(new Long(assemblyValue), electionYear);
			System.out.println("Parties List Size $$$$$$$$$"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		else if(electionYear != null && party == null && parliamentValue != null && assemblyValue != null)
		{
			System.out.println(" For Participated Parties In both Assembly and Parliament Elections");
			parliamentList = crossVotingEstimationService.getPartiesForAcAndPcElections(new Long(assemblyValue), electionYear, new Long(parliamentValue.trim()));
			System.out.println("Parties List Size $$$$$$$$$"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		else if(electionYear != null && party != null && parliamentValue != null && assemblyValue != null && includeAliance != null)
		{
			System.out.println("IN assembly values = "+electionYear+"**"+party+"**"+parliamentValue+"**"+assemblyValue);
			crossVotingConsolidateVO = crossVotingEstimationService.getConsolidatedCrossVotingDetails(electionYear, new Long(party.trim()), new Long(assemblyValue.trim()), new Long(parliamentValue.trim()), includeAliance);
			resultObjectVO.setCrossVotingConsolidateVO(crossVotingConsolidateVO);	
		}		
				
		return Action.SUCCESS;
	}

	

}
