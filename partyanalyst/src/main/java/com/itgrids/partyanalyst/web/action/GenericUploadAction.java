/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 24,2010
 */
package com.itgrids.partyanalyst.web.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.service.IGenericUploadService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna
 *
 */
public class GenericUploadAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private File filePath;
	private String countryId;
	private String module;
	
	private static final Logger log = Logger.getLogger(GenericUploadAction.class);
	
	private IGenericUploadService genericUploadService;
	private UploadDataErrorMessageVO uploadResultsVO;
	private Boolean uploadStatus;
	private String loginDetails = "";

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @return the filePath
	 */
	public File getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the genericUploadService
	 */
	public IGenericUploadService getGenericUploadService() {
		return genericUploadService;
	}

	/**
	 * @param genericUploadService the genericUploadService to set
	 */
	public void setGenericUploadService(IGenericUploadService genericUploadService) {
		this.genericUploadService = genericUploadService;
	}

	/**
	 * @return the uploadResultsVO
	 */
	public UploadDataErrorMessageVO getUploadResultsVO() {
		return uploadResultsVO;
	}

	/**
	 * @param uploadResultsVO the uploadResultsVO to set
	 */
	public void setUploadResultsVO(UploadDataErrorMessageVO uploadResultsVO) {
		this.uploadResultsVO = uploadResultsVO;
	}

	/**
	 * @return the uploadStatus
	 */
	public Boolean getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus the uploadStatus to set
	 */
	public void setUploadStatus(Boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	
	/**
	 * @return the loginDetails
	 */
	public String getLoginDetails() {
		return loginDetails;
	}

	/**
	 * @param loginDetails the loginDetails to set
	 */
	public void setLoginDetails(String loginDetails) {
		this.loginDetails = loginDetails;
	}

	public String execute(){
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Upload Action Request ..");
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null){
			
			loginDetails = "Please Login To Upload ..";
			return ERROR;
		}
		
		uploadResultsVO = genericUploadService.interpretDataInExcelAndSetToVO(filePath, IConstants.CADRE, 1L,user.getRegistrationID());
		
		if(uploadResultsVO.getExceptionEncountered() != null)
			uploadStatus = false;
		else uploadStatus = true;
		
	 return Action.SUCCESS;
	}

}
