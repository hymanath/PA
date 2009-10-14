package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.IBoothResultPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private IBoothResultPopulationService boothResultPopulationService;
	private HttpServletRequest request;
	private String districtName;
	private String electionType;
	private String constituencyName;
	private String electionYear;
	private String filePath;
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public IBoothResultPopulationService getBoothResultPopulationService() {
		return boothResultPopulationService;
	}

	public void setBoothResultPopulationService(
			IBoothResultPopulationService boothResultPopulationService) {
		this.boothResultPopulationService = boothResultPopulationService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request  = request;		
	}
	
	public String execute() throws Exception{
		boothResultPopulationService.readExcelAndInsertData(electionYear, constituencyName, electionType, districtName, filePath);
		return SUCCESS;
	}
}
