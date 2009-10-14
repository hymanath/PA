package com.itgrids.partyanalyst.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.functions.Request;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadresInfoAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private String region;
	private Map<String,Long> regionMap;
	private HttpServletRequest request;
	private HttpSession session;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public Map<String, Long> getRegionMap() {
		return regionMap;
	}

	public void setRegionMap(Map<String, Long> regionMap) {
		this.regionMap = regionMap;
	}
	
	public String execute() throws Exception{
		
	region=request.getParameter("cadreRegion");
	this.setRegion(region);
	System.out.println("level = "+region);
	
	regionMap = new LinkedHashMap<String, Long>();
	if(region.equalsIgnoreCase("Country"))
	{
		System.out.println("Inside if block level = "+region);
		regionMap.put("State1", new Long(1));
		regionMap.put("State2", new Long(1));
		regionMap.put("State3", new Long(1));
		this.setRegionMap(regionMap);
	}
		
		System.out.println("Region map = "+regionMap);
		return Action.SUCCESS;
		
	}

	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
