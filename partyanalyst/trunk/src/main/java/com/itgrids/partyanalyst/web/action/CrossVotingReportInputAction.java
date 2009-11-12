package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;


public class CrossVotingReportInputAction extends ActionSupport implements ServletRequestAware {

	private List<SelectOptionVO> electionYearList;
	private List<SelectOptionVO> partyList;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public List<SelectOptionVO> getElectionYearList() {
		return electionYearList;
	}

	public void setElectionYearList(List<SelectOptionVO> electionYearList) {
		this.electionYearList = electionYearList;
	}

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		
		electionYearList = new ArrayList<SelectOptionVO>();
		partyList =  partyBoothWiseResultsService.getParties();
				
		SelectOptionVO eList1 = new SelectOptionVO();
		eList1.setId(new Long(2004));
		eList1.setName("2004");
		
		electionYearList.add(eList1);
		
		setElectionYearList(electionYearList);
		
		return Action.SUCCESS;
	}


	

}
