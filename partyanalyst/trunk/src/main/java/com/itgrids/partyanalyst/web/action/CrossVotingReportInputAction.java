package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportInputAction extends ActionSupport implements ServletRequestAware {

	private List<SelectOptionVO> electionYearList;
	private List<SelectOptionVO> parliamentList;
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

	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		
		electionYearList = new ArrayList<SelectOptionVO>();				
		SelectOptionVO eList1 = new SelectOptionVO();
		eList1.setId(new Long(2004));
		eList1.setName("2004");
		
		SelectOptionVO eList2 = new SelectOptionVO();
		eList2.setId(new Long(2009));
		eList2.setName("2009");		
		
		electionYearList.add(eList1);
		electionYearList.add(eList2);
		setElectionYearList(electionYearList);
		//****
		
		parliamentList = new ArrayList<SelectOptionVO>();		
		SelectOptionVO pList1 = new SelectOptionVO();
		pList1.setId(new Long(408));
		pList1.setName("Nellore");
		
		parliamentList.add(pList1);		
		
		return Action.SUCCESS;
	}

}
