package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LocationsHierarchyAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2319254812860276099L;
	private static final Logger log = Logger.getLogger(LocationsHierarchyAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private IRegionServiceData regionServiceDataImp;
	JSONObject jObj = null;
	private String task = null;
	private List<SelectOptionVO> regionsList;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<SelectOptionVO> getRegionsList() {
		return regionsList;
	}

	public void setRegionsList(List<SelectOptionVO> regionsList) {
		this.regionsList = regionsList;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute() throws Exception {
			
		return Action.SUCCESS;
		
	}
	public String ajaxCallHandler() throws Exception{
		session = request.getSession();
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		if(jObj.getString("task").equalsIgnoreCase("statesInCountry"))
		{
			List<SelectOptionVO> states = getRegionServiceDataImp().getStatesByCountry(1l); 
			states.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(states);
		} else if(jObj.getString("task").equalsIgnoreCase("districtsInState"))
		{
			Long stateId = jObj.getLong("id");
			List<SelectOptionVO> districts = getRegionServiceDataImp().getDistrictsByStateID(stateId);
			districts.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(districts);
		} else if(jObj.getString("task").equalsIgnoreCase("constituenciesInDistrict"))
		{
			Long districtId = jObj.getLong("id");
			List<SelectOptionVO> constituencies = getRegionServiceDataImp().getConstituenciesByDistrictID(districtId);
			constituencies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(constituencies);
		} else if(jObj.getString("task").equalsIgnoreCase("subRegionsInConstituency"))
		{
			Long constituencyId = jObj.getLong("id");
			List<SelectOptionVO> subRegions = getRegionServiceDataImp().getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR);
			subRegions.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(subRegions);
		}  else if(jObj.getString("task").equalsIgnoreCase("hamletsOrWardsInRegion"))
		{
			Long locationId = jObj.getLong("id");
			List<SelectOptionVO> hamletsOrWards = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
			hamletsOrWards.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(hamletsOrWards);
		}
		return Action.SUCCESS;
	
	}

}
