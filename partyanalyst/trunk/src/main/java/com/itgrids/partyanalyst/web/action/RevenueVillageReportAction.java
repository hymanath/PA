package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.HamletsListWithBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersHeaderDataVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService; 
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class RevenueVillageReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(RevenueVillageReportAction.class);
	private IConstituencyManagementService constituencyManagementService;
	private HamletsListWithBoothsAndVotersVO hamletsListWithBoothsAndVotersVO;
	private PartyElectionVotersHeaderDataVO partyElectionVotersHeaderDataVO;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}
	
	public HamletsListWithBoothsAndVotersVO getHamletsListWithBoothsAndVotersVO() {
		return hamletsListWithBoothsAndVotersVO;
	}

	public void setHamletsListWithBoothsAndVotersVO(
			HamletsListWithBoothsAndVotersVO hamletsListWithBoothsAndVotersVO) {
		this.hamletsListWithBoothsAndVotersVO = hamletsListWithBoothsAndVotersVO;
	}


	public PartyElectionVotersHeaderDataVO getPartyElectionVotersHeaderDataVO() {
		return partyElectionVotersHeaderDataVO;
	}

	public void setPartyElectionVotersHeaderDataVO(
			PartyElectionVotersHeaderDataVO partyElectionVotersHeaderDataVO) {
		this.partyElectionVotersHeaderDataVO = partyElectionVotersHeaderDataVO;
	}
	
	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}
	
	public String execute(){

		log.debug("RevenueVillageReportAction.execute() started.....");
		String revenueVillageStr = request.getParameter("revenueVillageID");
		
		if(revenueVillageStr==null)
			return ERROR;
		Long revenueVillageID = new Long(revenueVillageStr);
		log.debug("revenueVillageID="+revenueVillageID);
		String year  = request.getParameter("year");
		log.debug("year="+year);
		String electionType = request.getParameter("electionType");
		log.debug("electionType="+electionType);
		hamletsListWithBoothsAndVotersVO = constituencyManagementService.getAllHamletBoothInfoForRevenueVillage(revenueVillageID, year, electionType);

		partyElectionVotersHeaderDataVO = delimitationConstituencyMandalService.getPartyElectionVotersForMandal(new Long(revenueVillageID), IConstants.REVENUE_VILLAGE);
		log.debug("RevenueVillageReportAction.java");
		log.debug("partyElectionVotersHeaderDataVO.header.size="+partyElectionVotersHeaderDataVO.getHeader().size());
		log.debug("partyElectionVotersHeaderDataVO.data.size="+partyElectionVotersHeaderDataVO.getData().size());
		
		return SUCCESS;
	}

}
