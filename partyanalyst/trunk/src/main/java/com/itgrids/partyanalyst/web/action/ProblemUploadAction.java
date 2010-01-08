/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 6, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.problem.ProblemExcelDataColumnNames;
import com.itgrids.partyanalyst.service.IProblemUploadService;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemUploadAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File filePath;
	private String year;
	private String stateName;
	private String isValidate;
	List<String> corrections;
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private IProblemUploadService problemUploadService;
	
	
	private static final Logger log = Logger.getLogger(ProblemUploadAction.class);
	

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	
	public List<String> getCorrections() {
		return corrections;
	}

	public void setCorrections(List<String> corrections) {
		this.corrections = corrections;
	}
	
	public IProblemUploadService getProblemUploadService() {
		return problemUploadService;
	}

	public void setProblemUploadService(IProblemUploadService problemUploadService) {
		this.problemUploadService = problemUploadService;
	}

	public String execute() throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("In ProblemUpload Action ......");
			log.debug("FileName :" + filePath.getName());
			log.debug("Year :" + year);
			log.debug("StateName :" + stateName);
		}
		String problemSource = ProblemExcelDataColumnNames.PARTYANALYST.getValue();
		
		if(isValidate.equals("true")){
			UploadDataErrorMessageVO uploadDataErrorMessageVO = problemUploadService.readExcelAndInsertDataValidation(filePath, year, stateName,problemSource);
			corrections = uploadDataErrorMessageVO.getCorrections();
		}
		else{
			ResultStatus result = problemUploadService.readExcelAndInsertData(filePath, year, stateName,problemSource);
			if(result.getExceptionEncountered() != null){
				if(log.isDebugEnabled())
					log.debug("Exception Raised------",result.getExceptionEncountered());
				return ERROR;
			}
		}
		
	return SUCCESS;
	}

}
