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
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
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
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> pConstituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villagesList;
	private List<SelectOptionVO> boothsList;
	private List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
	private RegionServiceDataImp regionServiceDataImp;
	private List<SelectOptionVO> socialStatus = new ArrayList<SelectOptionVO>();
	private IProblemManagementService problemManagementService;
	private Long defaultInfluenceRange;
	private Long defaultState = 0l;
	private Long defaultDistrict = 0l;
	private Long defaultConstituency = 0l;
	private Long pConstituencyId;
	
	
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

	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}
	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
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
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}
	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}
	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}
	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}
	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}
	public Long getDefaultInfluenceRange() {
		return defaultInfluenceRange;
	}
	public void setDefaultInfluenceRange(Long defaultInfluenceRange) {
		this.defaultInfluenceRange = defaultInfluenceRange;
	}
	
	public Long getDefaultState() {
		return defaultState;
	}
	public void setDefaultState(Long defaultState) {
		this.defaultState = defaultState;
	}
	public Long getDefaultDistrict() {
		return defaultDistrict;
	}
	public void setDefaultDistrict(Long defaultDistrict) {
		this.defaultDistrict = defaultDistrict;
	}
	public Long getDefaultConstituency() {
		return defaultConstituency;
	}
	public void setDefaultConstituency(Long defaultConstituency) {
		this.defaultConstituency = defaultConstituency;
	}
	
	public List<SelectOptionVO> getPConstituencyList() {
		return pConstituencyList;
	}
	public void setPConstituencyList(List<SelectOptionVO> constituencyList) {
		pConstituencyList = constituencyList;
	}	
	
	public Long getPConstituencyId() {
		return pConstituencyId;
	}
	public void setPConstituencyId(Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}
	
	public String execute() throws Exception {
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());
		
		positionsList = influencingPeopleService.getAllInfluencePeoplePositions();
		positionSize =  positionsList.size();
		//staticParties = staticDataService.getStaticParties();
		Long userStateId = influencingPeopleService.getStateIdOfAUser(accessType,accessValue);
		staticParties = staticDataService.getStaticPartiesListForAState(userStateId != null ? userStateId : 0l);
		staticParties.add(0,new SelectOptionVO(0l,"Select Party"));
		
		occupationsList = staticDataService.getAllOccupations();
		
		influenceRange = influencingPeopleService.getInfluenceRange();
		influenceRange.add(0,new SelectOptionVO(0l,"Select Influence Scope"));
		socialStatus = staticDataService.getAllSocialCategories();
		
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		boothsList = new ArrayList<SelectOptionVO>();
		villagesList = new ArrayList<SelectOptionVO>();
		pConstituencyList = new ArrayList<SelectOptionVO>();
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}
		
		
		if("MLA".equals(accessType))
		{
		
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList.add(list.get(2));
			mandalList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
			mandalList.add(0,new SelectOptionVO(0l,"Select Location"));
			setDefaultState(list.get(0).getId());
			setDefaultDistrict(list.get(1).getId());
			setDefaultConstituency(list.get(2).getId());
			setDefaultInfluenceRange(4l);
			if(windowTask.equalsIgnoreCase("edit"))
			{
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(influencingPeopleBeanVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(influencingPeopleBeanVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(influencingPeopleBeanVO.getConstituency()));
				setDefaultInfluenceRange(new Long(influencingPeopleBeanVO.getInfluencingRange()));
			}					
			
		} 
		
		else if("MP".equals(accessType)){
								
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
						
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			pConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
			setPConstituencyId(accessValue);
			setDefaultInfluenceRange(4l);
			if(windowTask.equalsIgnoreCase("edit"))
			{
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(new Long(influencingPeopleBeanVO.getConstituency()), IConstants.PRESENT_YEAR, null);
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(influencingPeopleBeanVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(influencingPeopleBeanVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(influencingPeopleBeanVO.getConstituency()));
				setDefaultInfluenceRange(new Long(influencingPeopleBeanVO.getInfluencingRange()));
			}
			
		}
		else if("COUNTRY".equals(accessType))
		{
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0l,"Select State"));
			setDefaultInfluenceRange(0l);
			if(windowTask.equalsIgnoreCase("edit"))
			{
				districtList = cadreManagementService.findDistrictsByState(influencingPeopleBeanVO.getState());	
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(influencingPeopleBeanVO.getDistrict()));
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(new Long(influencingPeopleBeanVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(influencingPeopleBeanVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(influencingPeopleBeanVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(influencingPeopleBeanVO.getConstituency()));
				setDefaultInfluenceRange(new Long(influencingPeopleBeanVO.getInfluencingRange()));	
			}
			
		}
		else if("STATE".equals(accessType))
		{
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			setDefaultState(accessValue);
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			setDefaultInfluenceRange(2l);
			if(windowTask.equalsIgnoreCase("edit"))
			{
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(influencingPeopleBeanVO.getDistrict()));
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(new Long(influencingPeopleBeanVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(influencingPeopleBeanVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(influencingPeopleBeanVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(influencingPeopleBeanVO.getConstituency()));
				setDefaultInfluenceRange(new Long(influencingPeopleBeanVO.getInfluencingRange()));
			}
			
		}
		else if("DISTRICT".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			
			stateList.add(list.get(0));
			districtList.add(list.get(1));
			
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			setDefaultInfluenceRange(3l);
			setDefaultState(list.get(0).getId());
			setDefaultDistrict(list.get(1).getId());
			
			if(windowTask.equalsIgnoreCase("edit"))
			{
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(new Long(influencingPeopleBeanVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(influencingPeopleBeanVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(influencingPeopleBeanVO.getMandal()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(influencingPeopleBeanVO.getConstituency()));
				setDefaultInfluenceRange(new Long(influencingPeopleBeanVO.getInfluencingRange()));
			}
			
			
		}
		
		
		session.setAttribute(ISessionConstants.STATES, stateList);
		session.setAttribute(ISessionConstants.DISTRICTS,districtList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES,constituencyList);
		session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
		session.setAttribute(ISessionConstants.MANDALS,mandalList);	
		session.setAttribute(ISessionConstants.VILLAGES,villagesList);
		session.setAttribute(ISessionConstants.BOOTHS,boothsList);
		

		session.setAttribute(ISessionConstants.STATES_C, stateList);
		session.setAttribute(ISessionConstants.DISTRICTS_C,districtList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_C,constituencyList);
		session.setAttribute(ISessionConstants.MANDALS_C,mandalList);	
		session.setAttribute(ISessionConstants.VILLAGES_C,villagesList);
		session.setAttribute(ISessionConstants.BOOTHS_C,boothsList);
				
		session.setAttribute("staticParties", staticParties);
		session.setAttribute("influenceRange",influenceRange);
		session.setAttribute("positionsList",positionsList);
		session.setAttribute(ISessionConstants.SOCIALCATEGORIES,socialStatus);
		session.setAttribute(ISessionConstants.OCCUPATIONS, occupationsList);
		session.setAttribute(ISessionConstants.GENDERS, gender);
	
			
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
	
	public void prepare() throws Exception {
		
		influencingPersonId = request.getParameter("influencingPersonId");
		windowTask = request.getParameter("windowTask");
						
        if( "0".equals(influencingPersonId)) {
        	
        	influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
        	
          } 
        else if(influencingPersonId != null) 
        {	
        	 influencingPeopleBeanVO = influencingPeopleService.getDetailsByInfluencingPersonId(new Long(getInfluencingPersonId()));
        }
	
	}
	
	public Object getModel() {

		return influencingPeopleBeanVO;
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
