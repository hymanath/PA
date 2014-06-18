package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ResultObjectVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportAjaxAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CrossVotingReportAjaxAction.class);
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
		
		LOG.info("In Execute of ajax");
		
		electionYear = request.getParameter("election");
		party = request.getParameter("party");
		parliamentValue = request.getParameter("parliamentValue");
		assemblyValue = request.getParameter("assemblyValue");
		includeAliance = request.getParameter("includeAliance");
		resultObjectVO = new ResultObjectVO();
		
		LOG.info("year == "+electionYear+",party == "+party+",parliament = "+parliamentValue+",assembly = "+assemblyValue+", alliance = "+includeAliance);
		if(electionYear != null && party == null && parliamentValue == null && assemblyValue == null){
			LOG.info("IN parliament if");
			parliamentList = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(electionYear.trim(), Long.valueOf(1));
			LOG.info("Parliament Constituencies Size::"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		
		if(electionYear != null && party == null && parliamentValue != null && assemblyValue == null)
		{
			LOG.info("IN election year");
			parliamentList = crossVotingEstimationService.getAssembliesForParliament(Long.valueOf(parliamentValue.trim()), Long.valueOf(electionYear.trim()));
			LOG.info("Assemblies Consties List Size $$$$$$$$$"+parliamentList.size());			
			resultObjectVO.setDataList(parliamentList);				
		}
		else if(electionYear != null && party == null && parliamentValue == null && assemblyValue != null)
		{
			LOG.info("IN parliament value");
			parliamentList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(Long.valueOf(assemblyValue), electionYear);
			LOG.info("Parties List Size $$$$$$$$$"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		else if(electionYear != null && party == null && parliamentValue != null && assemblyValue != null)
		{
			LOG.info(" For Participated Parties In both Assembly and Parliament Elections");
			parliamentList = crossVotingEstimationService.getPartiesForAcAndPcElections(Long.valueOf(assemblyValue), electionYear, Long.valueOf(parliamentValue.trim()));
			LOG.info("Parties List Size $$$$$$$$$"+parliamentList.size());
			resultObjectVO.setDataList(parliamentList);	
		}
		else if(electionYear != null && party != null && parliamentValue != null && assemblyValue != null && includeAliance != null)
		{
			LOG.info("IN assembly values = "+electionYear+"**"+party+"**"+parliamentValue+"**"+assemblyValue);
			crossVotingConsolidateVO = crossVotingEstimationService.getConsolidatedCrossVotingDetails(electionYear, Long.valueOf(party.trim()), Long.valueOf(assemblyValue.trim()), Long.valueOf(parliamentValue.trim()), includeAliance);
			resultObjectVO.setCrossVotingConsolidateVO(crossVotingConsolidateVO);	
		}		
				
		return Action.SUCCESS;
	}

	

}
