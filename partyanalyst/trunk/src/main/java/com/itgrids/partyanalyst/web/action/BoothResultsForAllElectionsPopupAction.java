package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.impl.PartyBoothWiseResultsService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultsForAllElectionsPopupAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(BoothResultsForAllElectionsPopupAction.class);
	private BoothPanelVO boothPanelVO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private String boothId;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public BoothPanelVO getBoothPanelVO() {
		return boothPanelVO;
	}

	public void setBoothPanelVO(BoothPanelVO boothPanelVO) {
		this.boothPanelVO = boothPanelVO;
	}	
	
	public String getBoothId() {
		return boothId;
	}

	public void setBoothId(String boothId) {
		this.boothId = boothId;
	}	

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public String execute () 
	{
		ResultWithExceptionVO resultOfBoothPage = partyBoothWiseResultsService.getBoothPageInfo(new Long(boothId));
		if(resultOfBoothPage.getExceptionEncountered() == null){
			boothPanelVO = (BoothPanelVO) resultOfBoothPage.getFinalResult();
			boothPanelVO.setResultPartial(false);
			log.debug("All Elections Info In Booths::"+boothPanelVO.getElections().size());			
		} else {
				boothPanelVO.setResultPartial(true);
			}
	 return SUCCESS;
	}
	
}
