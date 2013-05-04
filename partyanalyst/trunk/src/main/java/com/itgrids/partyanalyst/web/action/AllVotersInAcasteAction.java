package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AllVotersInAcasteAction extends ActionSupport implements
ServletRequestAware ,ServletContextAware{
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse responce;
	private HttpSession session;
	
	private String publicationDateId;
	private Long hamletId;
	private Long mainId;
	private String caste;
	private String type;
	private String Name;
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
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setServletContext(ServletContext context) {
		 this.context =context;	
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletResponse getResponce() {
		return responce;
	}
	public void setResponce(HttpServletResponse responce) {
		this.responce = responce;
	}
	
	public String getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(String publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(String casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getBuildTypes() {
		return buildTypes;
	}
	public void setBuildTypes(String buildTypes) {
		this.buildTypes = buildTypes;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
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
