package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class VoterInfoAction extends ActionSupport implements ServletRequestAware{

	private Long hamletId;
	private String year;
	private List<VoterVO> voterDetails;
	private IConstituencyManagementService constituencyManagementService;
	private HttpServletRequest request;
	
	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}

	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}

	public List<VoterVO> getVoterDetails() {
		return voterDetails;
	}

	public void setVoterDetails(List<VoterVO> voterDetails) {
		this.voterDetails = voterDetails;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{
		
		voterDetails = constituencyManagementService.createProblem(hamletId, year);
		System.out.print("^^^^^^^^^^^^^^^^^^^^ Total Voters:::::::::::::"+voterDetails.size());
		return SUCCESS;
	}
	
	
}
