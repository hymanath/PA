package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.IBoothResultPopulationService;
import com.itgrids.partyanalyst.service.IParliamentBoothResultPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private IBoothResultPopulationService boothResultPopulationService;
	private IParliamentBoothResultPopulationService parliamentBoothResultPopulationService;
	private HttpServletRequest request;
	private String electionScopeId;
	private String electionYear;
	private String filePath;
	
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
	
	public String getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(String electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	
	public IBoothResultPopulationService getBoothResultPopulationService() {
		return boothResultPopulationService;
	}

	public void setBoothResultPopulationService(
			IBoothResultPopulationService boothResultPopulationService) {
		this.boothResultPopulationService = boothResultPopulationService;
	}

	public IParliamentBoothResultPopulationService getParliamentBoothResultPopulationService() {
		return parliamentBoothResultPopulationService;
	}

	public void setParliamentBoothResultPopulationService(
			IParliamentBoothResultPopulationService parliamentBoothResultPopulationService) {
		this.parliamentBoothResultPopulationService = parliamentBoothResultPopulationService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request  = request;		
	}
	
	public String execute() throws Exception{
		if(Integer.parseInt(electionScopeId.trim()) == 1)
			parliamentBoothResultPopulationService.readExcel(filePath, new Long(electionScopeId.trim()), electionYear);
		else
			boothResultPopulationService.readExcelAndInsertData(electionYear, new Long(electionScopeId.trim()), filePath);
		return SUCCESS;
	}

}
