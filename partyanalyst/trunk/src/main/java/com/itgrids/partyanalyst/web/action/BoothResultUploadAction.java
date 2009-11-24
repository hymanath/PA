package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.service.IBoothResultPopulationService;
import com.itgrids.partyanalyst.service.IParliamentBoothResultPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private IBoothResultPopulationService boothResultPopulationService;
	private IParliamentBoothResultPopulationService parliamentBoothResultPopulationService;
	private IBoothDataValidationService boothDataValidationService;
	private HttpServletRequest request;
	private String electionScopeId;
	private String electionYear;
	private String filePath;
	private String isValidate;
	private List<String> corrections;
	private static final Logger log = Logger.getLogger(BoothResultUploadAction.class);

	
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
		if(isValidate.equals("true")){
			if(Integer.parseInt(electionScopeId.trim()) == 1)
				corrections = boothDataValidationService.readParliamentBoothResultExcelAndPopulate(filePath, electionYear, new Long(1));
			else
				corrections = boothDataValidationService.readAssemblyBoothResultExcelAndPopulate(filePath, electionYear, new Long(electionScopeId.trim()));
		}else{
			if(Integer.parseInt(electionScopeId.trim()) == 1){
				ResultStatus resultVO = parliamentBoothResultPopulationService.readExcel(filePath, new Long(electionScopeId.trim()), electionYear);
				Throwable ex = resultVO.getExceptionEncountered();
				if(ex!=null){
					log.error("exception raised while Uploading Booth Result ", ex);
					return ERROR;
				}
			}
			else{
				ResultStatus resultVO = boothResultPopulationService.readExcelAndInsertData(electionYear, new Long(electionScopeId.trim()), filePath);
				Throwable ex = resultVO.getExceptionEncountered();
				if(ex!=null){
					log.error("exception raised while Uploading Booth Result ", ex);
					return ERROR;
				}
			}		
		}
		return SUCCESS;
	}

}
