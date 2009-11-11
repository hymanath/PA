package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private String filePath;
	private String electionScopeId;
	private String electionYear;
	private HttpServletRequest request; 
	private IBoothPopulationService boothPopulationService;
	
	public IBoothPopulationService getBoothPopulationService() {
		return boothPopulationService;
	}

	public void setBoothPopulationService(
			IBoothPopulationService boothPopulationService) {
		this.boothPopulationService = boothPopulationService;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public String execute() throws Exception{
		System.out.println("File Path::"+filePath);
		System.out.println("Election Year::"+electionYear);
		System.out.println("election Scope Id::"+electionScopeId);
		boothPopulationService.readExcelFileAndPolpulate(filePath, electionYear, new Long(electionScopeId.trim()));
		return SUCCESS;
	}

	public String getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(String electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
}
