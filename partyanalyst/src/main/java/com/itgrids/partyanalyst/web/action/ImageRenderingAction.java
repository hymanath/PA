package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ImageRenderingAction extends ActionSupport implements	ServletRequestAware {

	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private List<LocationWiseBoothDetailsVO> locationVOList;
	private ILocationDashboardService locationDashboardService;
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public List<LocationWiseBoothDetailsVO> getLocationVOList() {
		return locationVOList;
	}

	public void setLocationVOList(List<LocationWiseBoothDetailsVO> locationVOList) {
		this.locationVOList = locationVOList;
	}

	public ILocationDashboardService getLocationDashboardService() {
		return locationDashboardService;
	}

	public void setLocationDashboardService(
			ILocationDashboardService locationDashboardService) {
		this.locationDashboardService = locationDashboardService;
	}

	public String imageRendering()
	{
		try {
			
			final HttpSession session = request.getSession();
			
		/*	final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			*///}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	
	}
	
	public String getConstituenciesByDistrictForWordCloud(){
		try{
			locationVOList = new ArrayList<LocationWiseBoothDetailsVO>();
			jObj = new JSONObject(getTask());
			for (int i=0; i<jObj.getJSONArray("districtId").length() ;i++) {
				List<LocationWiseBoothDetailsVO> locationList= locationDashboardService.getAllConstituenciesByDistrictforWordCloud(jObj.getJSONArray("districtId").get(i).toString().trim());
				locationVOList.addAll(locationList);
			}
		}catch(Exception e){
			LOG.error("Exception Occured In getAllDistricts method "+e);			
		}
		return Action.SUCCESS;
	}
	
}
