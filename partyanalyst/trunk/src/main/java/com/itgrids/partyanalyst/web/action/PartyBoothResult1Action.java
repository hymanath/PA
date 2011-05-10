package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult1Action extends ActionSupport  implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String task = null;
	private String partyName;
	private String electionType;
	private String electionYear;
	private String constituencyName;
	private PartyBoothPerformanceVO result;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List<SelectOptionVO> constituencyVOs;
	private HttpServletResponse response;
	private HttpSession session;
	private HttpServletRequest request;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<SelectOptionVO> getConstituencyVOs() {
		return constituencyVOs;
	}

	public void setConstituencyVOs(List<SelectOptionVO> constituencyVOs) {
		this.constituencyVOs = constituencyVOs;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public PartyBoothPerformanceVO getResult() {
		return result;
	}

	public void setResult(PartyBoothPerformanceVO result) {
		this.result = result;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() throws Exception {

		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		System.out.println("In execute = "+electionType+" ,election year =  "+electionYear);
		//constituencyVOs = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(electionYear, new Long(electionType));
		constituencyVOs = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),new Long(electionYear), new Long(electionType));

		return Action.SUCCESS;
	}
	
	public String getParty() throws Exception{
	
		System.out.println("In get party = ");
		constituencyVOs = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(new Long(constituencyName), electionYear);
		return Action.SUCCESS;
	}

	
}
