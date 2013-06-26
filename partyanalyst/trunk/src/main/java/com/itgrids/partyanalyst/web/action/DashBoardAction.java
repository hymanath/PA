package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@SuppressWarnings("serial")
public class DashBoardAction extends ActionSupport implements ServletRequestAware{

	
	private static final org.apache.log4j.Logger log = Logger.getLogger(HomePageAction.class); 
	
	private HttpServletRequest request;
	private List<SelectOptionVO> statesList,statesListForLocalBodyElection;
	private IStaticDataService staticDataService;
	private HttpSession session;
	
	
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}


	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}


	public List<SelectOptionVO> getStatesListForLocalBodyElection() {
		return statesListForLocalBodyElection;
	}


	public void setStatesListForLocalBodyElection(
			List<SelectOptionVO> statesListForLocalBodyElection) {
		this.statesListForLocalBodyElection = statesListForLocalBodyElection;
	}


	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


	public String execute()
	{	
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(new Long(5)); 
		
		if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
			statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));

		
		return Action.SUCCESS;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

}
