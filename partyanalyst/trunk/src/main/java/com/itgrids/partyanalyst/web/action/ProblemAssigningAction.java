package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ProblemAssigningAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -2691859423522316985L;
	private HttpServletRequest request;
	private Long probHistoryId;
	private Long cadreId;
	private Long dept;
	private Long problemType;
	private Long problemResolvingRegionId;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public Long getProbHistoryId() {
		return probHistoryId;
	}

	public void setProbHistoryId(Long probHistoryId) {
		this.probHistoryId = probHistoryId;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public Long getDept() {
		return dept;
	}

	public void setDept(Long dept) {
		this.dept = dept;
	}

	public Long getProblemType() {
		return problemType;
	}

	public void setProblemType(Long problemType) {
		this.problemType = problemType;
	}

	public Long getProblemResolvingRegionId() {
		return problemResolvingRegionId;
	}

	public void setProblemResolvingRegionId(Long problemResolvingRegionId) {
		this.problemResolvingRegionId = problemResolvingRegionId;
	}
	
	
	public String execute() throws Exception{
		
		System.out.println("========================");
		System.out.println(this.probHistoryId);
		System.out.println(this.cadreId);
		System.out.println(this.dept);
		System.out.println(this.problemResolvingRegionId);
		System.out.println(this.problemType);
		System.out.println("========================");
		
		return SUCCESS;
	}
	

}
