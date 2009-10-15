package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.functions.Request;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;;

public class CadresInfoAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private String region;
	private String regionId;	
	private HttpServletRequest request;
	private HttpSession session;
	private List<CadreRegionInfoVO> cadreRegionInfo;
	private List<CadreInfo> cadreInfo; 
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public List<CadreRegionInfoVO> getCadreRegionInfo() {
		return cadreRegionInfo;
	}

	public void setCadreRegionInfo(List<CadreRegionInfoVO> cadreRegionInfo) {
		this.cadreRegionInfo = cadreRegionInfo;
	}

	public List<CadreInfo> getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(List<CadreInfo> cadreInfo) {
		this.cadreInfo = cadreInfo;
	}
	
	public String execute() throws Exception{
		
	region=request.getParameter("cadreRegion");
	regionId=request.getParameter("cadreId");
	this.setRegion(region);
	this.setRegionId(regionId);
	System.out.println("level = "+region);
	System.out.println("level id = "+regionId);
	
	cadreRegionInfo = new ArrayList<CadreRegionInfoVO>();
	cadreInfo = new ArrayList<CadreInfo>();
	
		if(region.equalsIgnoreCase("Country"))
		{
			System.out.println("Inside if block level = "+region);			
			
			CadreRegionInfoVO cinfo1=new CadreRegionInfoVO();
			cinfo1.setRegion("State");
			cinfo1.setRegionId(new Long(1));
			cinfo1.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo2=new CadreRegionInfoVO();
			cinfo2.setRegion("State");
			cinfo2.setRegionId(new Long(2));
			cinfo2.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo3=new CadreRegionInfoVO();
			cinfo3.setRegion("State");
			cinfo3.setRegionId(new Long(1));
			cinfo3.setCadreCount(new Long(1));	
			
			cadreRegionInfo.add(cinfo1);
			cadreRegionInfo.add(cinfo2);
			cadreRegionInfo.add(cinfo3);
			
			this.setCadreRegionInfo(cadreRegionInfo);
		}
		else if(region.equalsIgnoreCase("State"))
		{
			System.out.println("Inside if block level = "+region);
			
			CadreRegionInfoVO cinfo1=new CadreRegionInfoVO();
			cinfo1.setRegion("District");
			cinfo1.setRegionId(new Long(1));
			cinfo1.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo2=new CadreRegionInfoVO();
			cinfo2.setRegion("District");
			cinfo2.setRegionId(new Long(2));
			cinfo2.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo3=new CadreRegionInfoVO();
			cinfo3.setRegion("District");
			cinfo3.setRegionId(new Long(1));
			cinfo3.setCadreCount(new Long(1));	
			
			cadreRegionInfo.add(cinfo1);
			cadreRegionInfo.add(cinfo2);
			cadreRegionInfo.add(cinfo3);
			
			this.setCadreRegionInfo(cadreRegionInfo);
		}
		else if(region.equalsIgnoreCase("District"))
		{		
			System.out.println("Inside if block level = "+region);
			
			CadreRegionInfoVO cinfo1=new CadreRegionInfoVO();
			cinfo1.setRegion("Constituency");
			cinfo1.setRegionId(new Long(1));
			cinfo1.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo2=new CadreRegionInfoVO();
			cinfo2.setRegion("Constituency");
			cinfo2.setRegionId(new Long(2));
			cinfo2.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo3=new CadreRegionInfoVO();
			cinfo3.setRegion("Constituency");
			cinfo3.setRegionId(new Long(1));
			cinfo3.setCadreCount(new Long(1));	
			
			cadreRegionInfo.add(cinfo1);
			cadreRegionInfo.add(cinfo2);
			cadreRegionInfo.add(cinfo3);
			
			this.setCadreRegionInfo(cadreRegionInfo);
		}
		else if(region.equalsIgnoreCase("Constituency"))
		{
			System.out.println("Inside if block level = "+region);
			
			CadreRegionInfoVO cinfo1=new CadreRegionInfoVO();
			cinfo1.setRegion("Mandal");
			cinfo1.setRegionId(new Long(1));
			cinfo1.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo2=new CadreRegionInfoVO();
			cinfo2.setRegion("Mandal");
			cinfo2.setRegionId(new Long(2));
			cinfo2.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo3=new CadreRegionInfoVO();
			cinfo3.setRegion("Mandal");
			cinfo3.setRegionId(new Long(1));
			cinfo3.setCadreCount(new Long(1));	
			
			cadreRegionInfo.add(cinfo1);
			cadreRegionInfo.add(cinfo2);
			cadreRegionInfo.add(cinfo3);
			
			this.setCadreRegionInfo(cadreRegionInfo);
		}
		else if(region.equalsIgnoreCase("Mandal"))
		{
			System.out.println("Inside if block level = "+region);
			
			CadreRegionInfoVO cinfo1=new CadreRegionInfoVO();
			cinfo1.setRegion("Village");
			cinfo1.setRegionId(new Long(1));
			cinfo1.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo2=new CadreRegionInfoVO();
			cinfo2.setRegion("Village");
			cinfo2.setRegionId(new Long(2));
			cinfo2.setCadreCount(new Long(1));
			
			CadreRegionInfoVO cinfo3=new CadreRegionInfoVO();
			cinfo3.setRegion("Village");
			cinfo3.setRegionId(new Long(1));
			cinfo3.setCadreCount(new Long(1));	
			
			cadreRegionInfo.add(cinfo1);
			cadreRegionInfo.add(cinfo2);
			cadreRegionInfo.add(cinfo3);
			
			this.setCadreRegionInfo(cadreRegionInfo);
		}
		else if(region.equalsIgnoreCase("Village"))
		{
			CadreInfo c1=new CadreInfo();
			c1.setFirstName("first");
			c1.setMiddleName("middle");
			c1.setLastName("last");
			c1.setGender("Male");
			c1.setMobileNo("9959988988");
			c1.setLandLineNo("04027056696");
			c1.setEmail("email@email.com");
			c1.setCadreLevel("Consituency");
			c1.setBooth("booth");
			c1.setVillage("village");
			c1.setMandal("mandal");
			c1.setDistrict("district");
			c1.setState("state");
			
			CadreInfo c2=new CadreInfo();
			c2.setFirstName("aaa");
			c2.setMiddleName("bbb");
			c2.setLastName("ccc");
			c2.setGender("Male");
			c2.setMobileNo("9959988977");
			c2.setLandLineNo("04027056690");
			c2.setEmail("email@gmail.com");
			c2.setCadreLevel("Consituency");
			c2.setBooth("booth");
			c2.setVillage("village");
			c2.setMandal("mandal");
			c2.setDistrict("district");
			c2.setState("state");
			
			cadreInfo.add(c1);
			cadreInfo.add(c2);
			
			this.setCadreInfo(cadreInfo);
		}
				
		return Action.SUCCESS;		
	}

	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
