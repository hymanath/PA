package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyElectionResultsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PartyElectionResultsAction.class);
	private HttpServletRequest request;	
	private ServletContext context;
	private IStaticDataService staticDataService;
	private Long electionId;
	private Long partyId;
	private Long rank;
	private String partyName;
	private String electionType;
	private String stateName;
	private String electionYear;
	private ElectionResultPartyVO electionResultPartyVO;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public void setElectionResultPartyVO(ElectionResultPartyVO electionResultPartyVO) {
		this.electionResultPartyVO = electionResultPartyVO;
	}

	public ElectionResultPartyVO getElectionResultPartyVO() {
		return electionResultPartyVO;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionType() {
		return electionType;
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

	public String execute () throws Exception 
	{
		if(log.isInfoEnabled())
		{
			log.debug("Entered in to Action");
			log.debug("electionId::::::::::::"+electionId);
			log.debug("partyId:::::::::::"+partyId);
			log.debug("rank:::::::::::"+rank);
			
		}
		electionResultPartyVO = new ElectionResultPartyVO();
		electionResultPartyVO = staticDataService.getElectionResultForAPartyInAnElection(electionId, partyId, rank);		
		return Action.SUCCESS;
	}
	
	

}
