package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private IBoothPopulationService boothPopulationService;
	private IBoothDataValidationService boothDataValidationService;
	private HttpServletRequest request;
	private String electionScopeId;
	private String electionYear;
	private File filePath;
	private String isValidate;
	private List<String> corrections;
	private static final Logger log = Logger.getLogger(BoothResultUploadAction.class);

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}
	
	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public List<String> getCorrections() {
		return corrections;
	}

	public void setCorrections(List<String> corrections) {
		this.corrections = corrections;
	}
	
	public IBoothDataValidationService getBoothDataValidationService() {
		return boothDataValidationService;
	}

	public void setBoothDataValidationService(
			IBoothDataValidationService boothDataValidationService) {
		this.boothDataValidationService = boothDataValidationService;
	}
	
	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	
	public String getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(String electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	
	public IBoothPopulationService getBoothPopulationService() {
		return boothPopulationService;
	}

	public void setBoothPopulationService(
			IBoothPopulationService boothPopulationService) {
		this.boothPopulationService = boothPopulationService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request  = request;		
	}
	
	public String execute() throws Exception{
		if(isValidate.equals("true")){
			if(Integer.parseInt(electionScopeId.trim()) == 1)
				corrections = boothDataValidationService.readParliamentBoothResultExcelAndPopulate(filePath, electionYear, new Long(1));
			else
				corrections = boothDataValidationService.readAssemblyBoothResultExcelAndPopulate(filePath, electionYear, new Long(electionScopeId.trim()));
		}else{
			ResultStatus resultVO = boothPopulationService.readExcelAndPopulateBoothResult(electionYear, new Long(electionScopeId.trim()), filePath);
			Throwable ex = resultVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while Uploading Booth Result ", ex);
				return ERROR;
			}	
		}
		return SUCCESS;
	}

}
