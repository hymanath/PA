package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;

@SuppressWarnings("serial")
public class CadresInfoAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	
	private String region;
	private String regionId;	
	private String cadreType;
	private HttpServletRequest request;
	private HttpSession session;
	private List<CadreRegionInfoVO> cadreRegionInfo;
	private List<CadreInfo> cadreInfo; 
	
	private CadreManagementService cadreManagementService;
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public String getCadreType() {
		return cadreType;
	}

	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	
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
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		region = request.getParameter("cadreRegion");
		regionId = request.getParameter("cadreId");
		cadreType = request.getParameter("cadreType");
		this.setRegion(region);
		this.setRegionId(regionId);
		this.setCadreType(cadreType);
		
		System.out.println("level = "+region);
		System.out.println("level id = "+regionId);
		System.out.println("cadre Type = "+cadreType);
		
		cadreRegionInfo = new ArrayList<CadreRegionInfoVO>();
		cadreInfo = new ArrayList<CadreInfo>();
		
		if(cadreType.equalsIgnoreCase("TotalCadre"))
		{
			System.out.println("In if total cadre");
			if(region.equalsIgnoreCase("Country"))
			{
				System.out.println("Inside if block level = "+region);			
				
				cadreRegionInfo = cadreManagementService.getCountryAllStatesCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("State"))
			{
				System.out.println("Inside if block level = "+region);
				
				cadreRegionInfo = cadreManagementService.getStateAllDistrictsCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("District"))
			{		
				System.out.println("Inside if block level = "+region);
				
				cadreRegionInfo = cadreManagementService.getDistrictAllMandalsCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("MLA"))
			{
				System.out.println("Inside if block level = "+region);

				cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("Mandal"))
			{
				System.out.println("Inside if block level = "+region);

				cadreRegionInfo = cadreManagementService.getMandalAllVillagesCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("Village"))
			{
				
				cadreInfo = cadreManagementService.getCadresByVillage(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}
		}
		else if(cadreType.equalsIgnoreCase("ZeroLevelCadre"))
		{
			System.out.println("In if Zero level cadre");
		}
		else if(cadreType.equalsIgnoreCase("RegionLevelCadre"))
		{
			System.out.println("In if Region level cadre");
			cadreInfo = cadreManagementService.getCadresByCadreLevel(region, userID);
			
			this.setCadreInfo(cadreInfo);
			/*
			if(region.equalsIgnoreCase("State"))
			{
			}
			else if(region.equalsIgnoreCase("District"))
			{		
			}
			else if(region.equalsIgnoreCase("Constituency"))
			{
			}
			else if(region.equalsIgnoreCase("Mandal"))
			{
			}
			else if(region.equalsIgnoreCase("Village"))
			{
				
				cadreInfo = cadreManagementService.getCadresByVillage(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}*/
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
