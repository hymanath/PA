package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class ConstituencySearchAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153325140803872628L;
	private static final Logger LOG = Logger.getLogger(ConstituencySearchAction.class);
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String name;
	private String electionType;
	private String districtName;
	private String stateName;
	
	public String execute() {
			
		LOG.info("In constituency search results action + execute method");
		LOG.info("Name = "+getName());
		LOG.info("Election type = "+getElectionType());
		LOG.info("District Name = "+getDistrictName());
		return SUCCESS;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
