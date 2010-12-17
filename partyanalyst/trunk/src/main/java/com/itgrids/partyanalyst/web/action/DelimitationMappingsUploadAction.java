/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13,2010
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

import com.itgrids.partyanalyst.dto.DelimitationMappingUploadVO;
import com.itgrids.partyanalyst.service.IDelimitationMappingsUploadService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna
 *
 */
public class DelimitationMappingsUploadAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private static final Logger log = Logger.getLogger(DelimitationMappingsUploadAction.class);
	
	private File filePath;
	private String country;
	private String censusYear;
	private String delimitationYear;
	
	private IDelimitationMappingsUploadService delimitationMappingsUploadService;
	private DelimitationMappingUploadVO delimitationMappingUploadVO;

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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the censusYear
	 */
	public String getCensusYear() {
		return censusYear;
	}

	/**
	 * @param censusYear the censusYear to set
	 */
	public void setCensusYear(String censusYear) {
		this.censusYear = censusYear;
	}

	/**
	 * @return the delimitationYear
	 */
	public String getDelimitationYear() {
		return delimitationYear;
	}

	/**
	 * @param delimitationYear the delimitationYear to set
	 */
	public void setDelimitationYear(String delimitationYear) {
		this.delimitationYear = delimitationYear;
	}

	/**
	 * @return the delimitationMappingsUploadService
	 */
	public IDelimitationMappingsUploadService getDelimitationMappingsUploadService() {
		return delimitationMappingsUploadService;
	}

	/**
	 * @param delimitationMappingsUploadService the delimitationMappingsUploadService to set
	 */
	public void setDelimitationMappingsUploadService(
			IDelimitationMappingsUploadService delimitationMappingsUploadService) {
		this.delimitationMappingsUploadService = delimitationMappingsUploadService;
	}

	/**
	 * @return the delimitationMappingUploadVO
	 */
	public DelimitationMappingUploadVO getDelimitationMappingUploadVO() {
		return delimitationMappingUploadVO;
	}

	/**
	 * @param delimitationMappingUploadVO the delimitationMappingUploadVO to set
	 */
	public void setDelimitationMappingUploadVO(
			DelimitationMappingUploadVO delimitationMappingUploadVO) {
		this.delimitationMappingUploadVO = delimitationMappingUploadVO;
	}

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
	
	public String execute(){
		
		delimitationMappingUploadVO = delimitationMappingsUploadService.fetchDelimitationDataFromExcel(filePath, delimitationYear, country);
		delimitationMappingUploadVO.setDelimitationYear(this.delimitationYear);
		
		if(delimitationMappingUploadVO != null){
			System.out.println(" Row :" + delimitationMappingUploadVO.getCurrentRow());
			System.out.println(" Sheet :" + delimitationMappingUploadVO.getCurrentSheet());
			
			if(delimitationMappingUploadVO.getMappedConstituencies() != null && delimitationMappingUploadVO.getMappedConstituencies().size() > 0){
				System.out.println(" Total constituencies Mapped :" + delimitationMappingUploadVO.getMappedConstituencies().size());
				for(String consti:delimitationMappingUploadVO.getMappedConstituencies()){
				 System.out.print(consti + " , ");
				}
				System.out.println("  ");
			}else{
				System.out.println(" Total Constituencies Mapped is 0");
			}
			if(delimitationMappingUploadVO.getExceptionEncountered() == null)
			    System.out.println(" Mapped And Saved Successfully Without Any Exceptions .. ");
			else
				System.out.println("Exception Raised :" + delimitationMappingUploadVO.getExceptionEncountered());
		}
		
		return Action.SUCCESS;
	}

}
