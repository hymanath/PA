package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemAssigningAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -2691859423522316985L;
	private HttpServletRequest request;
	JSONObject jObj = null;
	private String task;
	private Long probHistoryId;
	private Long cadreId;
	private Long dept;
	private Long problemType;
	private Long problemResolvingRegionId;
	private List<SelectOptionVO> deptScopesList;
	
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
	
	
	public List<SelectOptionVO> getDeptScopesList() {
		return deptScopesList;
	}

	public void setDeptScopesList(List<SelectOptionVO> deptScopesList) {
		this.deptScopesList = deptScopesList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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
	
	public String ajaxCallHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		deptScopesList = new ArrayList<SelectOptionVO>(0);
		
		if(jObj.getString("task").equalsIgnoreCase("getProblemResolvingDeptScopes"))
		{
			//Here retrive the dept scopes
		}
		else if(jObj.getString("task").equalsIgnoreCase("getDepartmentCategories"))
		{
			//here retrive the Department Categories
		}
		else if(jObj.getString("task").equalsIgnoreCase("getDepartments"))
		{
			//here retrive the Departments
		}
		
		return SUCCESS;
	}
	
 }
