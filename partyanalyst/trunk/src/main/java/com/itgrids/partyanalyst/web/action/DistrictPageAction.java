package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.formula.functions.Request;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DistrictPageAction extends ActionSupport implements ServletRequestAware{

	private String districtId;
	private String districtName;
	private HttpServletRequest request;
	
	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute() throws Exception
	{
		districtId = request.getParameter("districtId");
		districtName = request.getParameter("districtName");
		
		this.setDistrictName(districtName);
		this.setDistrictId(districtId);
		
		System.out.println("District Id = "+districtId+" & District Name = "+districtName);
		return Action.SUCCESS;
	}

}
