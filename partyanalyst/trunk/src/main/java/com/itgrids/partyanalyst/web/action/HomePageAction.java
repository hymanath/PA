package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class HomePageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private List<SelectOptionVO> statesList;
	private IRegionServiceData regionServiceDataImp;
	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}


	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}


	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}


	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	
	public String execute()
	{
		statesList = regionServiceDataImp.getStatesByCountry(new Long(1));
		return Action.SUCCESS;
	}
	

}
