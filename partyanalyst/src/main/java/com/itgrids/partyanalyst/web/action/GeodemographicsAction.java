package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.api.service.IGeodemographicService;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GeodemographicsAction extends ActionSupport implements ServletRequestAware {

	private final static Logger LOG = Logger.getLogger(GeodemographicsAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	private IGeodemographicService geodemographicService;
	private List<SelectOptionVO> result;
	
	

	public List<SelectOptionVO> getResult() {
		return result;
	}
	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}
	public void setGeodemographicService(
			IGeodemographicService geodemographicService) {
		this.geodemographicService = geodemographicService;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String execute(){
		return Action.SUCCESS;
	}
	public String getDistrictsDetails(){
			try{
				jObj = new JSONObject(getTask());
		
				result = geodemographicService.getDistricts(Long.valueOf(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select District"));
		}catch(Exception e){
			LOG.error("Exception Occured in getDistrictsDetails() in GeodemographicsAction ",e);
		}
		return SUCCESS;
	}
	public String getConstituenciesForADistrict()
	{
		try {
			jObj = new JSONObject(getTask());
			result = geodemographicService.getConstituenciesByDistrictID(Long.valueOf(jObj.getString("districtId")));
			 
			if(result == null || result.size() == 0)
				result.add(0, new SelectOptionVO(0L,"Select Constituency"));
			
			if(result != null && result.size() > 1)
				result.add(0, new SelectOptionVO(0L,"Select Constituency"));
		} catch (ParseException e) {
			LOG.error("Exception Occured in getConstituenciesForADistrict() in GeodemographicsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getMandalsForAConstituency(){
		try {
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			result = geodemographicService.getMandalsByConstituencyIDFromBooth(constituencyId);
		} catch (ParseException e) {
			LOG.error("Exception Occured in getMandalsForAConstituency() in GeodemographicsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getVillagesForMandalId()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long mandalId = jObj.getLong("mandalId");
		result = geodemographicService.getVillagesForMandalId(mandalId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getVillagesForMandalId() in GeodemographicsAction ",e);
		}
	 return Action.SUCCESS;
	}
	  public String getAllBoothsForPanchayat(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long panchayatId = jObj.getLong("panchayatId");
			  result = geodemographicService.getBoothsList(panchayatId);
			  
		  }catch(Exception e){
			  LOG.error("Entered into getAllBoothsForPanchayat method in GeodemographicsAction....");
		  }
		  return Action.SUCCESS;
	  }
}
