package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AllVotersInAcasteAction extends ActionSupport implements
ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse responce;
	private HttpSession session;
	
	private String publicationDateId;
	private Long hamletId;
	private Long mainId;
	private String caste;
	private String type;
	private String name;
	private String casteStateId;
	private String casteCategory;
	private String typename;
	private Long year;
	private String buildTypes;
	private Long constituencyId;
	private String task;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(final HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponce() {
		return responce;
	}
	public void setResponce(final HttpServletResponse responce) {
		this.responce = responce;
	}
	
	public String getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(final String publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(final Long hamletId) {
		this.hamletId = hamletId;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(final Long mainId) {
		this.mainId = mainId;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(final String caste) {
		this.caste = caste;
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(final String casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(final String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(final String typename) {
		this.typename = typename;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(final Long year) {
		this.year = year;
	}
	public String getBuildTypes() {
		return buildTypes;
	}
	public void setBuildTypes(final String buildTypes) {
		this.buildTypes = buildTypes;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(final Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
	public String execute()
	{
		final HttpSession session = request.getSession();
		final RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.ERROR;
		}
		else
		{
			return Action.SUCCESS;
		}
	}
}
