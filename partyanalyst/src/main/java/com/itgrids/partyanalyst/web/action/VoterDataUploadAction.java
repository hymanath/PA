package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.service.IVoterDataUploadService;
import com.opensymphony.xwork2.ActionSupport;

public class VoterDataUploadAction extends ActionSupport implements ServletRequestAware{
	
	private String stateId;
	private String electionTypeId;
	private String electionYear;
	private File filePath; 
	private HttpServletRequest request;
	private String validateData;
	private List<String> corrections;
	private IVoterDataUploadService voterDataUploadService;
	private IBoothDataValidationService boothDataValidationService;
	private String publicationDate;
	private List<SelectOptionVO> publicationDatesList;
	

	private static final Logger log = Logger.getLogger(VoterDataUploadAction.class);
	
	public List<SelectOptionVO> getPublicationDatesList() {
		return publicationDatesList;
	}

	public void setPublicationDatesList(List<SelectOptionVO> publicationDatesList) {
		this.publicationDatesList = publicationDatesList;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getValidateData() {
		return validateData;
	}

	public void setValidateData(String validateData) {
		this.validateData = validateData;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(String electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public IVoterDataUploadService getVoterDataUploadService() {
		return voterDataUploadService;
	}

	public void setVoterDataUploadService(
			IVoterDataUploadService voterDataUploadService) {
		this.voterDataUploadService = voterDataUploadService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public String execute()throws Exception{
				
		if(validateData.equals("true")){
			UploadDataErrorMessageVO errors = boothDataValidationService.readVoterExcelDataAndValidate(filePath, electionYear, new Long(stateId), new Long(electionTypeId) , publicationDate);
			corrections = errors.getCorrections();
			return SUCCESS;
		}
		ResultStatus result = voterDataUploadService.readExcelAndInsertData(filePath, electionYear, new Long(stateId), new Long(electionTypeId) , publicationDate);
		if(result.getExceptionEncountered() != null){
			if(log.isDebugEnabled())
				log.debug("Exception Raised------",result.getExceptionEncountered());
			return ERROR;
		}
		else
			return SUCCESS;
	}
}
