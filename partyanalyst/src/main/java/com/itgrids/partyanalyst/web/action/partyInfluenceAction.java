package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyInfluenceReportVO;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IPartyInfluenceService;

public class partyInfluenceAction extends ActionSupport implements ServletContextAware,ServletConfigAware{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String electionType;
	private String stateName;
	private String electionYear;
	private String newParty;
	private String partyName;
	private String alliances;
	
    private IPartyInfluenceService partyInfluenceService;
    
    public static final Logger logger = Logger.getLogger(partyInfluenceAction.class);
	
	private List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO;
	private List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO;
	private PartyInfluenceReportVO partyInfluenceReportVO;
	
	public PartyInfluenceReportVO getPartyInfluenceReportVO() {
		return partyInfluenceReportVO;
	}

	public void setPartyInfluenceReportVO(
			PartyInfluenceReportVO partyInfluenceReportVO) {
		this.partyInfluenceReportVO = partyInfluenceReportVO;
	}

	public String getAlliances() {
		return alliances;
	}

	public void setAlliances(String alliances) {
		this.alliances = alliances;
	}

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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	public String execute(){
		
		Boolean hasAlliances = new Boolean(alliances);
		
		if(logger.isDebugEnabled()){
			logger.debug(" ElectionType :" + electionType);
			logger.debug("Impacted Party :" + partyName);
			logger.debug("New Party :" + newParty);
			logger.debug("Election Year :" + electionYear);
			logger.debug("State " + stateName);
			logger.debug("Has Alliances " + hasAlliances);
			
		}
	
		partyInfluenceReportVO = partyInfluenceService.getPartyInfluenceReportResults(Long.parseLong(electionType), Long.parseLong(partyName), Long.parseLong(newParty), electionYear,hasAlliances ,Long.parseLong(stateName));
		
		if(partyInfluenceReportVO != null){
			
			Throwable ex = partyInfluenceReportVO.getExceptionEncountered();
			if(ex != null){
				if(logger.isDebugEnabled())
					logger.debug(" Exception Raised " + ex);
				return ERROR;
				
			}
		}
		
	return Action.SUCCESS;
	}

}
