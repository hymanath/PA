package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
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
public class AddRebelsAction extends ActionSupport implements ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Log LOG = LogFactory.getLog(AddRebelsAction.class);

	transient private HttpServletRequest request;

	private ServletContext context;
	
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> states;
	private List<SelectOptionVO> parties;
	private List<SelectOptionVO> years;
	private List<SelectOptionVO> candidates;
	private List<SelectOptionVO> constituencies;
	private List<SelectOptionVO> rebelCandidates;
	transient private ICandidateSearchService candidateSearchService;
	transient private IPartyRebelCandidatesService partyRebelCandidatesService;
	private static final String STATEID = "stateId";
	
	public void setPartyRebelCandidatesService(final 
			IPartyRebelCandidatesService partyRebelCandidatesService) {
		this.partyRebelCandidatesService = partyRebelCandidatesService;
	}

	public void setCandidateSearchService(final 
			ICandidateSearchService candidateSearchService) {
		this.candidateSearchService = candidateSearchService;
	}

	public List<SelectOptionVO> getRebelCandidates() {
		return rebelCandidates;
	}

	public void setRebelCandidates(final List<SelectOptionVO> rebelCandidates) {
		this.rebelCandidates = rebelCandidates;
	}

	//@JSON (serialize= false )
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(final List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public List<SelectOptionVO> getCandidates() {
		return candidates;
	}

	public void setCandidates(final List<SelectOptionVO> candidates) {
		this.candidates = candidates;
	}

	//@JSON (serialize= false )
	public List<SelectOptionVO> getStates() {
		return states;
	}

	//@JSON (serialize= false )
	public List<SelectOptionVO> getParties() {
		return parties;
	}

	//@JSON (serialize= false )
	public List<SelectOptionVO> getYears() {
		return years;
	}

	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setStates(final List<SelectOptionVO> states) {
		this.states = states;
	}

	public void setParties(final List<SelectOptionVO> parties) {
		this.parties = parties;
	}

	public void setYears(final List<SelectOptionVO> years) {
		this.years = years;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(final ServletContext context) {
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws JRException {
		LOG.debug("AddRebels ... excute started...");
		
		final Map<String, String> params = request.getParameterMap();
		
		Long defaultElectionTypeId = 2l;
		
		String param = null;		
		
		if(params.containsKey("type")){
			param = request.getParameter("type");
		} else if(params.containsKey(STATEID) && params.size() == 1){
			param = request.getParameter(STATEID);
			setConstituencies(getStaticDataService().getConstituencies(Long.valueOf(param),null));
			return Action.SUCCESS;
		} 
		
		if(param != null) {
			defaultElectionTypeId = Long.valueOf(param);
		}
		
		setStates(getStaticDataService().getStates(defaultElectionTypeId));
		setYears(getStaticDataService().getElectionIdsAndYears(defaultElectionTypeId));
		setParties(getStaticDataService().getStaticParties());
		setConstituencies(getStaticDataService().getConstituencies(Long.valueOf(getStates().get(0).getId()),null));
		final Long stateId = getStates().get(0).getId();
		final Long partyId = getParties().get(0).getId();
		final Long electionId = getYears().get(0).getId();
		setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
		setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		//setCandidates(candidateSearchService.getCandidateNamesAndIds());
		
		//setRebelCandidates(new ArrayList<SelectOptionVO>());
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getJSON() throws JRException {
		LOG.debug("getCandidates Ajax action started...");
		Map<String, String> params = request.getParameterMap();
	
		if(params.containsKey(STATEID)) {
			final Long electionId =  Long.valueOf(request.getParameter("electionId"));
			final Long partyId = Long.valueOf(request.getParameter("partyId"));
			final Long stateId = Long.valueOf(request.getParameter(STATEID));
			setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
			setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		}
		
		return Action.SUCCESS;
	}

	public String addPartyRebels() throws JRException {
		LOG.debug("addPartyRebels action started...");
		
		Map<String, String> params = request.getParameterMap();
		Long defaultElectionTypeId = 2l;
		Long stateId = 1l;
		Long electionId = null;
		Long partyId = null;
		
		// Need to get JSON from jsp and set the list to rebelsList below.
		if(params != null) {
			electionId = Long.valueOf(request.getParameter("year"));
			partyId = Long.valueOf(request.getParameter("party"));
			stateId = Long.valueOf(request.getParameter("state"));
		
			if(params.containsKey("rebels")) {
				final StringTokenizer rebels = new StringTokenizer(request.getParameter("rebels"), ",");
				final List<Long> rebelsList = new ArrayList<Long>();
				while(rebels.hasMoreTokens()) {
					rebelsList.add(Long.valueOf(rebels.nextToken()));
				}
				partyRebelCandidatesService.savePartyRebelCandidates(stateId, partyId, electionId, rebelsList);
			}
			defaultElectionTypeId = Long.valueOf(request.getParameter("electionType"));
		}
		
		setStates(getStaticDataService().getStates(defaultElectionTypeId));
		setYears(getStaticDataService().getElectionIdsAndYears(defaultElectionTypeId));
		setParties(getStaticDataService().getStaticParties());
		setConstituencies(getStaticDataService().getConstituencies(stateId,null));
		setCandidates(candidateSearchService.getNominatedPartyCandidates(stateId, partyId, electionId));
		setRebelCandidates(partyRebelCandidatesService.findByPartyIdAndElectionId(partyId, electionId));
		
		return Action.SUCCESS;
	}
	//@JSON (serialize= false )
	private IStaticDataService getStaticDataService() {
		return staticDataService;
	}
}