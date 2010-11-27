package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class BoothResultUploadAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private IBoothPopulationService boothPopulationService;
	private HttpServletRequest request;
	private String electionScopeId;
	private String electionYear;
	private File filePath;
	private String isValidate;
	private List<ConstituencyInfoVO> uploadInfo;
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
	
	public List<ConstituencyInfoVO> getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(List<ConstituencyInfoVO> uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		
		ResultWithExceptionVO resultVO = boothPopulationService.readExcelAndPopulateBoothResult(electionYear, 
				new Long(electionScopeId.trim()), filePath, Boolean.parseBoolean(isValidate));
		Throwable ex = resultVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while Uploading Booth Result ", ex);
			return ERROR;
		}	

		uploadInfo = (List<ConstituencyInfoVO>) resultVO.getFinalResult();

		return SUCCESS;
	}

}
