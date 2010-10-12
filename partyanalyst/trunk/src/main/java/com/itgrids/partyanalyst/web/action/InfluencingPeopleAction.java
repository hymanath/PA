package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InfluencingPeopleAction extends ActionSupport implements
		ServletContextAware, ServletRequestAware, ModelDriven, Preparable {

	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private CadreManagementService cadreManagementService;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	private int positionSize;
	private List<SelectOptionVO> positionsList,staticParties,influenceRange;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;
	private String windowTask,influencingPersonId;
	private List<String> gender = new ArrayList<String>();
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
	private RegionServiceDataImp regionServiceDataImp;
	private List<SelectOptionVO> socialStatus = new ArrayList<SelectOptionVO>();
	
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

	
	public void setInfluencingPeopleBeanVO(InfluencingPeopleBeanVO influencingPeopleBeanVO) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO;
	}
	public InfluencingPeopleBeanVO getInfluencingPeopleBeanVO() {
		return influencingPeopleBeanVO;
	}
	public String getWindowTask() {
		return windowTask;
	}
	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}
	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}
	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	public String getInfluencingPersonId() {
		return influencingPersonId;
	}
	public void setInfluencingPersonId(String influencingPersonId) {
		this.influencingPersonId = influencingPersonId;
	}
	
	public void setGender(List<String> gender) {
		this.gender = gender;
	}
	public List<SelectOptionVO> getSocialStatus() {
		return socialStatus;
	}
	public void setSocialStatus(List<SelectOptionVO> socialStatus) {
		this.socialStatus = socialStatus;
	}
	public List<String> getGender() {
		return gender;
	}
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}
	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public String execute() throws Exception {
		
		positionsList = influencingPeopleService.getAllInfluencePeoplePositions();
		positionSize =  positionsList.size();
		staticParties = staticDataService.getStaticParties();
		occupationsList = staticDataService.getAllOccupations();
		influenceRange = influencingPeopleService.getInfluenceRange();
		socialStatus = staticDataService.getAllSocialCategories(); 
		
		stateList = new ArrayList<SelectOptionVO>();
		stateList = cadreManagementService.findStatesByCountryID("1");
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}
				
		session = request.getSession();
		session.setAttribute("staticParties", staticParties);
		session.setAttribute("influenceRange",influenceRange);
		session.setAttribute("positionsList",positionsList);
		session.setAttribute(ISessionConstants.SOCIALCATEGORIES,socialStatus);
		session.setAttribute(ISessionConstants.OCCUPATIONS, occupationsList);
		session.setAttribute(ISessionConstants.GENDERS, gender);
		session.setAttribute(ISessionConstants.STATES, stateList);
		
		
		if(windowTask.equals(IConstants.CREATE_NEW))
		{	
			session.setAttribute(ISessionConstants.DISTRICTS, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.CONSTITUENCIES, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.MANDALS, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
					 
			session.setAttribute(ISessionConstants.DISTRICTS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.MANDALS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.VILLAGES_O, new ArrayList<SelectOptionVO>());
		}	
		
		return Action.SUCCESS;
	}
	
	public void removeSessionVariablesForInfluencingPeople(){
		try {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.removeAttribute("staticParties");
			session.removeAttribute("influenceRange");
			session.removeAttribute("positionsList");
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

	public Object getModel() {

		return influencingPeopleBeanVO;
	}

	public void prepare() throws Exception {
		
		influencingPersonId = request.getParameter("influencingPersonId");
		windowTask = request.getParameter("windowTask");
						
        if( "0".equals(influencingPersonId)) {
        	
        	influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
        	
          } 
        else if(influencingPersonId != null) 
        {	
        	 influencingPeopleBeanVO = influencingPeopleService.getDetailsByInfluencingPersonId(new Long(getInfluencingPersonId()));
        	 prepopulateLocations(influencingPeopleBeanVO);
        }
	
	}	
	
	public void prepopulateLocations(InfluencingPeopleBeanVO  influencingPeopleBeanVO)
    {
    		session = request.getSession();
    		
    		List<SelectOptionVO> districtNames_c=cadreManagementService.findDistrictsByState(influencingPeopleBeanVO.getState());			
    		session.setAttribute(ISessionConstants.DISTRICTS, districtNames_c);
    		
    		List<SelectOptionVO> constituencynames_c=regionServiceDataImp.getConstituenciesByDistrictID(new Long(influencingPeopleBeanVO.getDistrict()));	
			session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
			
			List<SelectOptionVO> subRegions_c = regionServiceDataImp.getSubRegionsInConstituency(new Long(influencingPeopleBeanVO.getConstituency()), IConstants.PRESENT_YEAR,null);
			session.setAttribute(ISessionConstants.MANDALS, subRegions_c);
			
			List<SelectOptionVO> villageList_c = new ArrayList<SelectOptionVO>();
			session.setAttribute(ISessionConstants.VILLAGES, villageList_c);
	    }
	
	public void deleteInfluencingPeople()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		Long influencingPeopleId = jObj.getLong("influencingPeopleId");
		
		Integer rows = influencingPeopleService.deleteInfluencingPeople(influencingPeopleId);
		Log.debug("rows:"+rows);
		
	}
}
