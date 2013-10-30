package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private File filePath;
	private String electionScopeId;
	private String electionYear;
	private String isValidate;
	private Long publicationDateId;
	private HttpServletRequest request; 
	private IBoothPopulationService boothPopulationService;
	private List<ConstituencyInfoVO> uploadInfo;
	private static final Logger log = Logger.getLogger(BoothUploadAction.class);
    private List<SelectOptionVO> publicationDates;
	public void setFilePath(File filePath) {
		this.filePath = filePath;
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

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public List<ConstituencyInfoVO> getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(List<ConstituencyInfoVO> uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		
		ResultWithExceptionVO resultVO = boothPopulationService.readExcelAndPopulateBoothData(filePath, electionYear, 
				new Long(electionScopeId.trim()), Boolean.parseBoolean(isValidate),publicationDateId);
		Throwable ex = resultVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while Uploading Booth Data ", ex);
			return ERROR;
		}
		
		uploadInfo = (List<ConstituencyInfoVO>) resultVO.getFinalResult();
		return SUCCESS;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

    public String getAllPublicationDates(){
    	publicationDates = 	boothPopulationService.getPublicationDates();
		return SUCCESS;
	}

	public List<SelectOptionVO> getPublicationDates() {
		return publicationDates;
	}

	public void setPublicationDates(List<SelectOptionVO> publicationDates) {
		this.publicationDates = publicationDates;
	}
   
}
