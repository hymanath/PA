package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CastWiseElectionVotersVO;
import com.itgrids.partyanalyst.dto.GenderAgeWiseVotersVO;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class HamletReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	//private HamletBoothVotersListVO hamletBoothVotersListVO;
	private CastWiseElectionVotersVO castWiseElectionVoters;
	private GenderAgeWiseVotersVO genderAgeWiseVoters;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private static final Logger LOG = Logger.getLogger(RevenueVillageReportAction.class);
	private String hamletName;
	
	/*public HamletBoothVotersListVO getHamletBoothVotersListVO() {
		return hamletBoothVotersListVO;
	}

	public void setHamletBoothVotersListVO(
			HamletBoothVotersListVO hamletBoothVotersListVO) {
		this.hamletBoothVotersListVO = hamletBoothVotersListVO;
	}
*/
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public void setCastWiseElectionVoters(
			CastWiseElectionVotersVO castWiseElectionVoters) {
		this.castWiseElectionVoters = castWiseElectionVoters;
	}
	public CastWiseElectionVotersVO getCastWiseElectionVoters() {
		return castWiseElectionVoters;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}
	
	public GenderAgeWiseVotersVO getGenderAgeWiseVoters() {
		return genderAgeWiseVoters;
	}

	public void setGenderAgeWiseVoters(GenderAgeWiseVotersVO genderAgeWiseVoters) {
		this.genderAgeWiseVoters = genderAgeWiseVoters;
	}
	
	public String getHamletName() {
		return hamletName;
	}

	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}

	public String execute(){
		LOG.debug("HamletReportAction.java");
		String strHamletID = request.getParameter("hamletID");
		if(strHamletID==null)
			return ERROR;
		Long hamletID = Long.valueOf(strHamletID);
		String year  = request.getParameter("year");
		LOG.debug("year="+year);
		String electionType = request.getParameter("electionType");
		LOG.debug("electionType="+electionType);
		hamletName = request.getParameter("hamletName");
		castWiseElectionVoters = delimitationConstituencyMandalService.findCastWiseVoterForHamlet(hamletID, year, electionType);
		
		genderAgeWiseVoters = delimitationConstituencyMandalService.findAgeWiseVotersForHamlet(hamletID, year, electionType);
		/*hamletBoothVotersListVO = constituencyManagementService.findAllBoothVotersForHamlet(hamletID, year, electionType);
		if(hamletBoothVotersListVO.getExceptionEncountered()!=null)
			LOG.error("Exception occurred while retrieving Hamlets booth voters:",hamletBoothVotersListVO.getExceptionEncountered());*/
		
		
		//hamletsListWithBoothsAndVotersVO = constituencyManagementService.getAllHamletBoothInfoForRevenueVillage(revenueVillageID, year, electionType);

		//partyElectionVotersHeaderDataVO = delimitationConstituencyMandalService.getPartyElectionVotersForMandal(revenueVillageID, IConstants.REVENUE_VILLAGE);
		
		//castWiseElectionVoters = delimitationConstituencyMandalService.findCastWiseVoterForRevenueVillage(revenueVillageID, year, electionType);
		
		//genderAgeWiseVoters = delimitationConstituencyMandalService.findAgeWiseVotersForRevenueVillage(revenueVillageID, year, electionType);
		
		//LOG.debug("partyElectionVotersHeaderDataVO.header.size="+partyElectionVotersHeaderDataVO.getHeader().size());
		//LOG.debug("partyElectionVotersHeaderDataVO.data.size="+partyElectionVotersHeaderDataVO.getData().size());
		
		return SUCCESS;
	}

}
