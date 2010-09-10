package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	private List<StateElectionsVO> stateElections;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private IStatePageService statePageService;
	private IRegionServiceData regionServiceDataImp;
	private List<SelectOptionVO> constituenciesList;
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}


	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}


	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	


	public List<StateElectionsVO> getStateElections() {
		return stateElections;
	}


	public void setStateElections(List<StateElectionsVO> stateElections) {
		this.stateElections = stateElections;
	}

	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public JSONObject getJObj() {
		return jObj;
	}


	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public IStatePageService getStatePageService() {
		return statePageService;
	}


	public void setStatePageService(IStatePageService statePageService) {
		this.statePageService = statePageService;
	}

	

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}


	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}


	public String execute()
	{	
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		return Action.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String ajaxCallHandler()
	{
		session = request.getSession();
				
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getRecentElectionsInState"))
		{
			Long stateId = new Long(jObj.getLong("stateId"));
			stateElections = statePageService.getStateElections(stateId);
		}
		if(jObj.getString("task").equalsIgnoreCase("getAllParliamentConstituencies"))
		{			
			constituenciesList = regionServiceDataImp.getAllParliamentConstituencies(1l, 1l);
		}
		return Action.SUCCESS;
	}

}
