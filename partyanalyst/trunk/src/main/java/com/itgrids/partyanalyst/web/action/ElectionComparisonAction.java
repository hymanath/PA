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
		
		statesList = electionsComparisonService.getStatesList();
		partyList = electionsComparisonService.getPartiesList();
		yearsList = electionsComparisonService.getYearsList();
		
		List<SelectOptionVO> eList = new ArrayList<SelectOptionVO>();
		
		SelectOptionVO parliament = new SelectOptionVO();
		parliament.setId(new Long(1));
		parliament.setName("Parliament");
		
		SelectOptionVO assembly = new SelectOptionVO();
		assembly.setId(new Long(2));
		assembly.setName("Assembly");
		
		eList.add(parliament);
		eList.add(assembly);
		
		this.setElectionType(eList);
		
		
		
		return Action.SUCCESS;
	}
}
