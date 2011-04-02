package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.service.IPartyRebelCandidatesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Servlet implementation class AddRebelsAction
 */
public class AddRebelsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Log log = LogFactory.getLog(PartyPerformanceAction.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
	private ServletContext context;
	
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> states;
	private List<SelectOptionVO> parties;
	private List<SelectOptionVO> years;
	private List<SelectOptionVO> candidates;
	private List<SelectOptionVO> constituencies;
	private List<SelectOptionVO> rebelCandidates;
	private ICandidateSearchService candidateSearchService;
	private IPartyRebelCandidatesService partyRebelCandidatesService;
	
	
	public void setPartyRebelCandidatesService(
			IPartyRebelCandidatesService partyRebelCandidatesService) {
		this.partyRebelCandidatesService = partyRebelCandidatesService;
	}

	public void setCandidateSearchService(
			ICandidateSearchService candidateSearchService) {
		this.candidateSearchService = candidateSearchService;
	}

	public List<SelectOptionVO> getRebelCandidates() {
		return rebelCandidates;
	}

	public void setRebelCandidates(List<SelectOptionVO> rebelCandidates) {
		this.rebelCandidates = rebelCandidates;
	}

	@JSON (serialize= false )
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public List<SelectOptionVO> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<SelectOptionVO> candidates) {
		this.candidates = candidates;
	}

	@JSON (serialize= false )
	public List<SelectOptionVO> getStates() {
		return states;
	}

	@JSON (serialize= false )
	public List<SelectOptionVO> getParties() {
		return parties;
	}

	@JSON (serialize= false )
	public List<SelectOptionVO> getYears() {
		return years;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}

	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}

	public void setYears(List<SelectOptionVO> years) {
		this.years = years;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws JRException {
		log.debug("AddRebels ... excute started...");
		
		Map<String, String> params = request.getParameterMap();
		
		Long defaultElectionTypeId = new Long(2);
		
		String param = null;
		defaultElectionTypeId = new Long(2);
		
		
		if(params.containsKey("type")){
			param = request.getParameter("type");
		} else if(params.containsKey("stateId") && params.size() == 1){
			param = request.getParameter("stateId");
			setConstituencies(getStaticDataService().getConstituencies(new Long(param)));
			return Action.SUCCESS;
		} 
		
		if(param != null) {
			defaultElectionTypeId = new Long(param);
		}
		
		setStates(getStaticDataService().getStates(defaultElectionTypeId));
		setYears(getStaticDataService().getElectionIdsAndYears(defaultElectionTypeId));
		setParties(getStaticDataService().getStaticParties());
		setConstituencies(getStaticDataService().getConstituencies(new Long(getStates().get(0).getId())));
		Long stateId = getStates().get(0).getId();
		Long partyId = getParties().get(0).getId();
		Long electionId = getYears().get(0).getId();
		setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
		setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		//setCandidates(candidateSearchService.getCandidateNamesAndIds());
		
		//setRebelCandidates(new ArrayList<SelectOptionVO>());
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getJSON() throws JRException {
		log.debug("getCandidates Ajax action started...");
		Map<String, String> params = request.getParameterMap();
	
		if(params.containsKey("stateId")) {
			Long electionId = new Long(request.getParameter("electionId"));
			Long partyId = new Long(request.getParameter("partyId"));
			Long stateId = new Long(request.getParameter("stateId"));
			setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
			setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		}
		
		return Action.SUCCESS;
	}

	public String addPartyRebels() throws JRException {
		log.debug("addPartyRebels action started...");
		
		Map<String, String> params = request.getParameterMap();
		Long defaultElectionTypeId = new Long(2);
		Long stateId = new Long(1);
		Long electionId = null;
		Long partyId = null;
		
		// Need to get JSON from jsp and set the list to rebelsList below.
		if(params != null) {
			electionId = new Long(request.getParameter("year"));
			partyId = new Long(request.getParameter("party"));
			stateId = new Long(request.getParameter("state"));
		
			if(params.containsKey("rebels")) {
				StringTokenizer rebels = new StringTokenizer(request.getParameter("rebels"), ",");
				List<Long> rebelsList = new ArrayList<Long>();
				while(rebels.hasMoreTokens()) {
					rebelsList.add(new Long(rebels.nextToken()));
				}
				partyRebelCandidatesService.savePartyRebelCandidates(stateId, partyId, electionId, rebelsList);
			}
			defaultElectionTypeId = new Long(request.getParameter("electionType"));
		}
		
		setStates(getStaticDataService().getStates(defaultElectionTypeId));
		setYears(getStaticDataService().getElectionIdsAndYears(defaultElectionTypeId));
		setParties(getStaticDataService().getStaticParties());
		setConstituencies(getStaticDataService().getConstituencies(stateId));
		setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
		setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		
		return Action.SUCCESS;
	}
	@JSON (serialize= false )
	private IStaticDataService getStaticDataService() {
		return staticDataService;
	}
}