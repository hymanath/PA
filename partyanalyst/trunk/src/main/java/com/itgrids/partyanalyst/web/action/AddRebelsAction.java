package com.itgrids.partyanalyst.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	
	
	
	public List<SelectOptionVO> getRebelCandidates() {
		return rebelCandidates;
	}

	public void setRebelCandidates(List<SelectOptionVO> rebelCandidates) {
		this.rebelCandidates = rebelCandidates;
	}

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public List<SelectOptionVO> getStates() {
		return states;
	}

	public List<SelectOptionVO> getParties() {
		return parties;
	}

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
		String year = null;
		Long partyId = null;
		Long defaultElectionTypeId = new Long(2);
		
		setStates(getStaticDataService().getStates(defaultElectionTypeId));
		setYears(getStaticDataService().getElectionIdsAndYears(defaultElectionTypeId));
		setParties(getStaticDataService().getParties());
		setCandidates(new ArrayList<SelectOptionVO>());
		setConstituencies(getStaticDataService().getConstituencies(new Long(getStates().get(0).getId())));
		setRebelCandidates(new ArrayList<SelectOptionVO>());
		return Action.SUCCESS;
	}

	private IStaticDataService getStaticDataService() {
		return staticDataService;
	}
}