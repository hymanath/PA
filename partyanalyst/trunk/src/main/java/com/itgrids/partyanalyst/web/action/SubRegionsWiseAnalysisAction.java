package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;

public class SubRegionsWiseAnalysisAction implements ServletRequestAware{
	
	private Long id;
	private Long publicationDateId;
	private String type;
	private String publicationYear;
	private String  buildType;
	private Long constituencyId;
	private String typeName;
	private HttpServletRequest request;
	
	 public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getPublicationDateId() {
		return publicationDateId;
	}


	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPublicationYear() {
		return publicationYear;
	}


	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}


	public String getBuildType() {
		return buildType;
	}


	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String execute(){
		 
		 return Action.SUCCESS;
	 }


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
}
