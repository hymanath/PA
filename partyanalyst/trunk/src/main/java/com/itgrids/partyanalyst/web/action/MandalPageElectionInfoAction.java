package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageElectionInfoAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO;
	private VillageDetailsVO villageDetailsVO;
	private MandalInfoVO mandalInfoVO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public ElectionWiseMandalPartyResultListVO getElectionWiseMandalPartyResultListVO() {
		return electionWiseMandalPartyResultListVO;
	}

	public void setElectionWiseMandalPartyResultListVO(
			ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO) {
		this.electionWiseMandalPartyResultListVO = electionWiseMandalPartyResultListVO;
	}
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public MandalInfoVO getMandalInfoVO() {
		return mandalInfoVO;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}

	public VillageDetailsVO getVillageDetailsVO() {
		return villageDetailsVO;
	}

	public void setVillageDetailsVO(VillageDetailsVO villageDetailsVO) {
		this.villageDetailsVO = villageDetailsVO;
	}

	public String execute(){
		
		String mandalID = request.getParameter("MANDAL_ID");
		String mandalName = request.getParameter("MANDAL_NAME");
		List<MandalInfoVO> mandalInfo = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalID);
		for(MandalInfoVO mandalInfoVO : mandalInfo){
			mandalInfoVO.setMandalName(mandalName);
			Throwable ex = mandalInfoVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while retrieving mandal details ", ex);
				return ERROR;
			}
			setMandalInfoVO(mandalInfoVO);
			break;
		}		
		
		villageDetailsVO = delimitationConstituencyMandalService.getVillagesFormMandal(new Long(mandalID));
		villageDetailsVO.setMandalName(mandalName);
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		electionWiseMandalPartyResultListVO = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(new Long(mandalID));
		
		return SUCCESS;
	}

}
