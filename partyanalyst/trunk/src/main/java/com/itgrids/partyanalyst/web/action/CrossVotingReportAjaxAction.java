package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;

public class CrossVotingReportAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String electionYear;
	private String party;
	private String parliamentValue;
	private String assemblyValue;
	private HttpServletRequest request;
	private List<SelectOptionVO> parliamentList;
	private CrossVotingConsolidateVO crossVotingConsolidateVO;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}

	public String getParliamentValue() {
		return parliamentValue;
	}

	public void setParliamentValue(String parliamentValue) {
		this.parliamentValue = parliamentValue;
	}

	public String getAssemblyValue() {
		return assemblyValue;
	}

	public void setAssemblyValue(String assemblyValue) {
		this.assemblyValue = assemblyValue;
	}

	public CrossVotingConsolidateVO getCrossVotingConsolidateVO() {
		return crossVotingConsolidateVO;
	}

	public void setCrossVotingConsolidateVO(
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}

	public String execute()throws Exception{
		
		System.out.println("In Execute of ajax");
		
		electionYear = request.getParameter("election");
		party = request.getParameter("party");
		parliamentValue = request.getParameter("parliamentValue");
		assemblyValue = request.getParameter("assemblyValue");

		System.out.println("year == "+electionYear+",party == "+party+",parliament = "+parliamentValue+",assembly = "+assemblyValue);
		
		if(electionYear != null && party != null && parliamentValue == null && assemblyValue == null)
		{
			System.out.println("IN election year");
			parliamentList = new ArrayList<SelectOptionVO>();
			SelectOptionVO pList1 = new SelectOptionVO();
			pList1.setId(new Long(1));
			pList1.setName("Nellore");
			
			SelectOptionVO pList2 = new SelectOptionVO();
			pList2.setId(new Long(2));
			pList2.setName("Rangareddy");
			
			parliamentList.add(pList1);
			parliamentList.add(pList2);

		}
		else if(electionYear == null && party == null && parliamentValue != null && assemblyValue == null)
		{
			System.out.println("IN parliament value");
		}
		else if(electionYear == null && party == null && parliamentValue == null && assemblyValue != null)
		{
			System.out.println("IN assembly value");
			
		}
		
				
				
		return Action.SUCCESS;
	}

	
}
