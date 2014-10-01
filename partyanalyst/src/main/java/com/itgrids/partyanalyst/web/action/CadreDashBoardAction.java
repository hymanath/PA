package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.opensymphony.xwork2.Action;

public class CadreDashBoardAction implements ServletRequestAware {

	private HttpServletRequest request;
	
	private ICadreDashBoardService cadreDashBoardService;
	
	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		
		return Action.SUCCESS;
	}
	
	public String getDashBoardBasicInfo(){
		
		
		return Action.SUCCESS;
	}
	
    public String getRecentlyRegisteredCadresInfo(){
		
		
		return Action.SUCCESS;
	}
    
   public String getAssemblyWiseCompletedPercentage(){
		
		
		return Action.SUCCESS;
	}
   
   public String getDistrictWiseCompletedPercentage(){
		
		
		return Action.SUCCESS;
	}
}
