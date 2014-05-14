package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IInfectedBoothsService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

	/*
	 * 	This Action Class is for Getting Infected Booths for Selected Constituency
	 * 	@author	SASI
	 * 	@since	MARCH 28th	2014	
	 *	@version 1.0
	 * 
	 * */

public class InfectedBoothsAction  extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> constituencyList,assemblyConstis,parlConstis,electionYearList,aConstituencyList;
	private List<PartyPositionVO> output;
	private EffectedBoothsResponse effectedBooths; 
	
	
	@Autowired IInfectedBoothsService infectedBoothsService;
	@Autowired ICrossVotingEstimationService crossVotingEstimationService;
	@Autowired IStrategyModelTargetingService strategyModelTargetingService;
	
	
	
	
	

	public EffectedBoothsResponse getEffectedBooths() {
		return effectedBooths;
	}


	public void setEffectedBooths(EffectedBoothsResponse effectedBooths) {
		this.effectedBooths = effectedBooths;
	}


	public List<PartyPositionVO> getOutput() {
		return output;
	}


	public void setOutput(List<PartyPositionVO> output) {
		this.output = output;
	}


	public JSONObject getjObj() {
		return jObj;
	}


	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}
	


	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}


	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}


	public List<SelectOptionVO> getAssemblyConstis() {
		return assemblyConstis;
	}


	public void setAssemblyConstis(List<SelectOptionVO> assemblyConstis) {
		this.assemblyConstis = assemblyConstis;
	}


	public List<SelectOptionVO> getParlConstis() {
		return parlConstis;
	}


	public void setParlConstis(List<SelectOptionVO> parlConstis) {
		this.parlConstis = parlConstis;
	}
	


	public List<SelectOptionVO> getaConstituencyList() {
		return aConstituencyList;
	}


	public void setaConstituencyList(List<SelectOptionVO> aConstituencyList) {
		this.aConstituencyList = aConstituencyList;
	}


	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	
	public String execute(){
		
		session = request.getSession();
		
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		
		constituencyList = user.getUserAccessVoterConstituencies();
		assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
		parlConstis = (List<SelectOptionVO>)session.getAttribute("parlConstis");
		
		List<String> years = crossVotingEstimationService.getElectionYearsForBoothResult();
		electionYearList = new ArrayList<SelectOptionVO>();
		if(years != null && years.size() > 0)
		{  
			for(String year : years)
				electionYearList.add(new SelectOptionVO(Long.parseLong(year.trim()), year));
		}
		
		return Action.SUCCESS;
	}
	
	
	public String getAssemblyConstisForParl(){
		LOG.debug("Entered into getAssemblyConstisForParl of InfectedBoothsAction");
		try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long constituencyId  = jObj.getLong("constituencyId");
			Long year            = jObj.getLong("year");
			aConstituencyList = crossVotingEstimationService.getAssembliesForParliament(constituencyId,year);
		}catch(Exception e){
			LOG.error("Exception Raised in getAssemblyConstisForParl of InfectedBoothsAction"+e);
		}
		return Action.SUCCESS;
	}
	
	public String getInfectedBoothsForSelectedConstituency(){
		/*try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long constituencyId  = jObj.getLong("constituencyId");
			List<PartyPositionVO> pv = strategyModelTargetingService.getPanchayatCategoriesForInfectedBooths(constituencyId);
		}catch(Exception e){
			LOG.error("Exception Raised in getAssemblyConstisForParl of InfectedBoothsAction"+e);
		}
		return Action.SUCCESS;*/
		
		try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long constituencyId  = jObj.getLong("constituencyId");
			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
			effectedBooths = strategyModelTargetingService.getPanchayatCategoriesForInfectedBooths(constituencyId,path);
			
		}catch(Exception e){
			LOG.error("Exception Raised in getAssemblyConstisForParl of InfectedBoothsAction"+e);
		}
		return Action.SUCCESS;
		
	}
	
	
}
