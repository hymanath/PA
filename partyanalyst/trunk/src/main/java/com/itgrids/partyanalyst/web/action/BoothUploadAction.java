package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private String filePath;
	private String electionScopeId;
	private String electionYear;
	private String isValidate;
	private HttpServletRequest request; 
	private List<String> corrections;
	private IBoothPopulationService boothPopulationService;
	private IBoothDataValidationService boothDataValidationService;
	private static final Logger log = Logger.getLogger(BoothUploadAction.class);

	
	public IBoothDataValidationService getBoothDataValidationService() {
		return boothDataValidationService;
	}

	public void setBoothDataValidationService(
			IBoothDataValidationService boothDataValidationService) {
		this.boothDataValidationService = boothDataValidationService;
	}
	
	public List<String> getCorrections() {
		return corrections;
	}

	public void setCorrections(List<String> corrections) {
		this.corrections = corrections;
	}
	
	public String getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(String electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	
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
		System.out.println("isValidate Id::"+isValidate);
		if(isValidate.equals("true")){
			corrections = boothDataValidationService.readBoothDataExcelFileAndPolpulate(filePath, electionYear, new Long(electionScopeId.trim()));
			System.out.println("corrections:"+corrections.size());
		}else{
			ResultStatus resultVO = boothPopulationService.readExcelFileAndPolpulate(filePath, electionYear, new Long(electionScopeId.trim()));
			Throwable ex = resultVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while Uploading Booth Data ", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	}

}
