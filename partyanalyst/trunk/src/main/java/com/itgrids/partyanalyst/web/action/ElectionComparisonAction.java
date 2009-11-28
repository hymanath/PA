package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;
import com.itgrids.partyanalyst.service.IStaticDataService;

public class ElectionComparisonAction extends ActionSupport implements ServletRequestAware,
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SelectOptionVO> statesList;
	private List<SelectOptionVO> partyList;
	private List<SelectOptionVO> electionType;
	private List<SelectOptionVO> yearsList;
	
	
	private HttpServletRequest request;
	private IElectionsComparisonService electionsComparisonService;
	private IStaticDataService staticDataService;
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	
	public List<SelectOptionVO> getElectionType() {
		return electionType;
	}

	public void setElectionType(List<SelectOptionVO> electionType) {
		this.electionType = electionType;
	}
	
	public List<SelectOptionVO> getYearsList() {
		return yearsList;
	}

	public void setYearsList(List<SelectOptionVO> yearsList) {
		this.yearsList = yearsList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}

	public IElectionsComparisonService getElectionsComparisonService() {
		return electionsComparisonService;
	}

	public void setElectionsComparisonService(IElectionsComparisonService electionsComparisonService) {
		this.electionsComparisonService = electionsComparisonService;
	}

	public String execute() throws Exception {
		
		partyList = staticDataService.getStaticParties();
		yearsList = electionsComparisonService.getYearsList();
		
		List<SelectOptionVO> eList = new ArrayList<SelectOptionVO>();
		
		SelectOptionVO assembly = new SelectOptionVO();
		assembly.setId(new Long(2));
		assembly.setName("Assembly");
		
		SelectOptionVO parliament = new SelectOptionVO();
		parliament.setId(new Long(1));
		parliament.setName("Parliament");
		
		eList.add(parliament);
		eList.add(assembly);
		
		this.setElectionType(eList);
		
		List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
		
		SelectOptionVO state1 = new SelectOptionVO();
		state1.setId(new Long(1));
		state1.setName("Andhra Pradesh");
		
		SelectOptionVO state2 = new SelectOptionVO();
		state2.setId(new Long(15));
		state2.setName("Maharastra");
		
		statesList.add(state1);
		statesList.add(state2);
		
		this.setStatesList(statesList);
			
	return Action.SUCCESS;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
}
