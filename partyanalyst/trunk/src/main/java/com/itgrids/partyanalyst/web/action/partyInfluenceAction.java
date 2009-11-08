package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IPartyInfluenceService;

public class partyInfluenceAction extends ActionSupport implements ServletContextAware,ServletConfigAware{
	
	
	private String electionType;
	private String statename;
	private String electionYear;
	private String newParty;
	private String partyName;
	
    private IPartyInfluenceService partyInfluenceService;
	
	private List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO;
	private List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO;
	
	public IPartyInfluenceService getPartyInfluenceService() {
		return partyInfluenceService;
	}

	public void setPartyInfluenceService(
			IPartyInfluenceService partyInfluenceService) {
		this.partyInfluenceService = partyInfluenceService;
	}

	
	public List<ConstituencyElectionsDetailedResultVO> getConstituencyElectionsDetailedResultVO() {
		return constituencyElectionsDetailedResultVO;
	}

	public void setConstituencyElectionsDetailedResultVO(
			List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO) {
		this.constituencyElectionsDetailedResultVO = constituencyElectionsDetailedResultVO;
	}

	public List<DistrictWiseConstituencyElectionResultsVO> getDistrictWiseConstituencyElectionResultsVO() {
		return districtWiseConstituencyElectionResultsVO;
	}

	public void setDistrictWiseConstituencyElectionResultsVO(
			List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO) {
		this.districtWiseConstituencyElectionResultsVO = districtWiseConstituencyElectionResultsVO;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getNewParty() {
		return newParty;
	}

	public void setNewParty(String newParty) {
		this.newParty = newParty;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String execute()
	{
		System.out.println("in party Influence Action ******");
		
		System.out.println("##################values = "+electionType+"=="+electionYear+"=="+statename+"=="+newParty+"=="+partyName);
		
		districtWiseConstituencyElectionResultsVO = partyInfluenceService.getPartyInfluenceReportResults(Long.parseLong(electionType), Long.parseLong(partyName), Long.parseLong(newParty), electionYear, true);
		
		if(districtWiseConstituencyElectionResultsVO.size() > 0 && districtWiseConstituencyElectionResultsVO != null)
	     return Action.SUCCESS;
		else
		 return Action.ERROR;
	}

}
