package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class InfluencingPeopleAction extends ActionSupport implements
		ServletContextAware, ServletRequestAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	private int positionSize;
	private List<SelectOptionVO> positionsList,staticParties,influenceRange;
	
	public List<SelectOptionVO> getInfluenceRange() {
		return influenceRange;
	}
	public void setInfluenceRange(List<SelectOptionVO> influenceRange) {
		this.influenceRange = influenceRange;
	}
	public int getPositionSize() {
		return positionSize;
	}
	public void setPositionSize(int positionSize) {
		this.positionSize = positionSize;
	}

	private String task = null;
	JSONObject jObj = null;
	
	public List<SelectOptionVO> getPositionsList() {
		return positionsList;
	}
	public void setPositionsList(List<SelectOptionVO> positionsList) {
		this.positionsList = positionsList;
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
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<SelectOptionVO> getStaticParties() {
		return staticParties;
	}
	public void setStaticParties(List<SelectOptionVO> staticParties) {
		this.staticParties = staticParties;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}
	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String execute() throws Exception {
		positionsList = influencingPeopleService.getAllInfluencePeoplePositions();
		positionSize =  positionsList.size();
		staticParties = staticDataService.getStaticParties();
		influenceRange = influencingPeopleService.getInfluenceRange();
		
		session = request.getSession();
		session.setAttribute("staticParties", staticParties);
		session.setAttribute("influenceRange",influenceRange);
		
		return Action.SUCCESS;
	}
	
	public void removeSessionVariablesForInfluencingPeople(){
		try {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.removeAttribute("staticParties");
			session.removeAttribute("influenceRange");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveInfluencePeopleData(){
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
			InfluencingPeopleVO influencingPeople = new InfluencingPeopleVO();
			influencingPeople.setPersonName(jObj.getString("firstName"));		
			influencingPeople.setLastName(jObj.getString("lastName"));
			influencingPeople.setEmail(jObj.getString("email"));
			influencingPeople.setContactNumber(jObj.getString("mobileNumber"));
			influencingPeople.setGender(jObj.getString("gender"));
			influencingPeople.setCast(jObj.getString("cast"));
			influencingPeople.setHamletId(jObj.getLong("hamletId"));
			influencingPeople.setOccupation(jObj.getString("occupation"));
			influencingPeople.setPartyId(jObj.getLong("partyId"));
			influencingPeople.setPosition(jObj.getString("position"));
			influencingPeople.setInfluencingRange(jObj.getString("range"));	
			Long id;
			id = influencingPeopleService.saveInfluencePeople(influencingPeople);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
}
