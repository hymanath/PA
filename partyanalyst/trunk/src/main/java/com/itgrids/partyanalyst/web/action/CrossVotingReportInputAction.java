package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportInputAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
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
		
		
		electionYearList.add(new SelectOptionVO(2009l, "2009"));
		electionYearList.add(new SelectOptionVO(2004l, "2004"));
		
		return Action.SUCCESS;
	}

}
