/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 13, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
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

/**
 * @author Sai Krishna
 *
 */
public class CreateLocalGroupAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware,ModelDriven, Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(CreateLocalGroupAction.class);

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ServletContext context;
	
	private IInfluencingPeopleService influencingPeopleService;
	private CadreManagementService cadreManagementService;
	private IStaticDataService staticDataService;
	private RegionServiceDataImp regionServiceDataImp;
	
	private List<SelectOptionVO> groupScopes = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> groupCategories;

	private List<SelectOptionVO> groupNames;
	private List<SelectOptionVO> designations;
	JSONObject jObj =null;
	private String task = null;
	private List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> districtsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> constituenciesList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> pConstituencyList  = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> mandalsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> villagesList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
	private String savedStatusMsg = "";
	private String name;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String groupCategory;
	private String groupName;
	private String designation;	
	
	private String localUserGroupId;
	private String windowTask;
	private LocalUserGroupDetailsVO localUserGroupDetailsVO;
	
	String accessType = "";
	Long accessValue = 0L;
	Long defaultGroupScope = 0L;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGroupCategory() {
		return groupCategory;
	}
	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public List<SelectOptionVO> getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(List<SelectOptionVO> groupNames) {
		this.groupNames = groupNames;
	}
	public List<SelectOptionVO> getDesignations() {
		return designations;
	}
	public void setDesignations(List<SelectOptionVO> designations) {
		this.designations = designations;
	}
			
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}
	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	public List<SelectOptionVO> getGroupScopes() {
		return groupScopes;
	}
	public void setGroupScopes(List<SelectOptionVO> groupScopes) {
		this.groupScopes = groupScopes;
	}
	public List<SelectOptionVO> getGroupCategories() {
		return groupCategories;
	}
	public void setGroupCategories(List<SelectOptionVO> groupCategories) {
		this.groupCategories = groupCategories;
	}
	public String getSavedStatusMsg() {
		return savedStatusMsg;
	}
	public void setSavedStatusMsg(String savedStatusMsg) {
		this.savedStatusMsg = savedStatusMsg;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public Long getAccessValue() {
		return accessValue;
	}
	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}
	public Long getDefaultGroupScope() {
		return defaultGroupScope;
	}
	public void setDefaultGroupScope(Long defaultGroupScope) {
		this.defaultGroupScope = defaultGroupScope;
	}
	public String getLocalUserGroupId() {
		return localUserGroupId;
	}
	public void setLocalUserGroupId(String localUserGroupId) {
		this.localUserGroupId = localUserGroupId;
	}
	public String getWindowTask() {
		return windowTask;
	}
	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}
	public LocalUserGroupDetailsVO getLocalUserGroupDetailsVO() {
		return localUserGroupDetailsVO;
	}
	public void setLocalUserGroupDetailsVO(
			LocalUserGroupDetailsVO localUserGroupDetailsVO) {
		this.localUserGroupDetailsVO = localUserGroupDetailsVO;
	}	
	
	public List<SelectOptionVO> getPConstituencyList() {
		return pConstituencyList;
	}
	public void setPConstituencyList(List<SelectOptionVO> constituencyList) {
		pConstituencyList = constituencyList;
	}
	public String execute(){
		
		if(log.isDebugEnabled())
			log.debug("Create A Local Group Action ..");
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		
		//Get Group Categories
        String grCategoryId = request.getParameter("grCategoryId");
		
		if(grCategoryId.equalsIgnoreCase("0") || grCategoryId == null)
		    groupCategories = influencingPeopleService.getLocalGroupCategoriesList(regVO.getRegistrationID());
		else
			groupCategories = influencingPeopleService.getLocalGroupCategoriesList(regVO.getRegistrationID(),new Long(grCategoryId));
		
		session.setAttribute(ISessionConstants.USER_GROUP_CATEGORIES,groupCategories);
		
		 accessType =regVO.getAccessType();
		 accessValue = new Long(regVO.getAccessValue());
		//Get Scopes Data
			setGroupScopesData();
			
		if("MLA".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			statesList.add(list.get(0));			
			districtsList.add(list.get(1));
			constituenciesList.add(list.get(2));
			
			mandalsList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
			mandalsList.add(0,new SelectOptionVO(0l,"Select Location"));
			
			if(windowTask.equalsIgnoreCase("edit"))
			{
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(localUserGroupDetailsVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(localUserGroupDetailsVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(localUserGroupDetailsVO.getConstituency()));
			}
			
			
			setDefaultGroupScope(4L);
		}
		
		else if("MP".equals(accessType)){
			
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
						
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			statesList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituenciesList = constituencyInfoVO.getAssembyConstituencies();
			constituenciesList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			pConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
			setDefaultGroupScope(4L);
			if(windowTask.equalsIgnoreCase("edit"))
			{
				mandalsList = regionServiceDataImp.getSubRegionsInConstituency(new Long(localUserGroupDetailsVO.getConstituency()), IConstants.PRESENT_YEAR, null);
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(localUserGroupDetailsVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(localUserGroupDetailsVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(localUserGroupDetailsVO.getConstituency()));				
			}
			
		}
		
		else if("COUNTRY".equals(accessType))
		{
			statesList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			statesList.add(0,new SelectOptionVO(0l,"Select State"));
			
			if(windowTask.equalsIgnoreCase("edit"))
			{
				districtsList = cadreManagementService.findDistrictsByState(localUserGroupDetailsVO.getState());	
				constituenciesList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(localUserGroupDetailsVO.getDistrict()));
				mandalsList = regionServiceDataImp.getSubRegionsInConstituency(new Long(localUserGroupDetailsVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(localUserGroupDetailsVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(localUserGroupDetailsVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(localUserGroupDetailsVO.getConstituency()));

			}
			setDefaultGroupScope(0L);
		}
		else if("STATE".equals(accessType))
		{
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			
			statesList.add(obj2);
			districtsList = staticDataService.getDistricts(accessValue);
			districtsList.add(0,new SelectOptionVO(0l,"Select District"));
			
			if(windowTask.equalsIgnoreCase("edit"))
			{
				constituenciesList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(localUserGroupDetailsVO.getDistrict()));
				mandalsList = regionServiceDataImp.getSubRegionsInConstituency(new Long(localUserGroupDetailsVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(localUserGroupDetailsVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(localUserGroupDetailsVO.getMandal()),new Long(IConstants.PRESENT_YEAR),new Long(localUserGroupDetailsVO.getConstituency()));
			}
			
			setDefaultGroupScope(2L);
		}
		else if("DISTRICT".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			
			statesList.add(list.get(0));
			districtsList.add(list.get(1));
			
			constituenciesList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituenciesList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			
			if(windowTask.equalsIgnoreCase("edit"))
			{
				mandalsList = regionServiceDataImp.getSubRegionsInConstituency(new Long(localUserGroupDetailsVO.getConstituency()), IConstants.PRESENT_YEAR,"currentAdd");
				villagesList = getRegionServiceDataImp().getHamletsOrWards(new Long(localUserGroupDetailsVO.getMandal()), IConstants.PRESENT_YEAR);
				boothsList = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(localUserGroupDetailsVO.getMandal()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(localUserGroupDetailsVO.getConstituency()));
			}
			
			setDefaultGroupScope(3L);
		}
		if(windowTask.equalsIgnoreCase("edit"))
		{
			setDefaultGroupScope(new Long(localUserGroupDetailsVO.getLocalUserGroupId()));
		}
		
		session.setAttribute(ISessionConstants.STATES, statesList);
		session.setAttribute(ISessionConstants.DISTRICTS,districtsList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES,constituenciesList);
		session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
		session.setAttribute(ISessionConstants.MANDALS,mandalsList);	
		session.setAttribute(ISessionConstants.VILLAGES,villagesList);
		session.setAttribute("boothsList",boothsList);
		
		return Action.SUCCESS;
	}
	
	public void setGroupScopesData(){
		
		groupScopes.add(new SelectOptionVO(2l,"STATE"));
		if(!"MP".equals(accessType))
			groupScopes.add(new SelectOptionVO(3l,"DISTRICT"));
		groupScopes.add(new SelectOptionVO(4l,"CONSTITUENCY"));
		groupScopes.add(new SelectOptionVO(5l,"MANDAL"));
		groupScopes.add(new SelectOptionVO(6l,"VILLAGE"));
		groupScopes.add(new SelectOptionVO(7l,"MUNICIPAL-CORP-GMC"));
		groupScopes.add(new SelectOptionVO(8l,"WARD"));
		groupScopes.add(new SelectOptionVO(9l,"BOOTH"));
		session.setAttribute(ISessionConstants.USER_GROUP_SCOPES,groupScopes);
	}
	
	public String createLocalGroupMemberPopup()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		String grCategoryId = request.getParameter("groupCategoryId");
		String groupId = request.getParameter("groupId");
		
		if(grCategoryId.equalsIgnoreCase("0") || grCategoryId == null){
			groupCategories = influencingPeopleService.getLocalGroupCategoriesList(userId);
			
			groupNames = new ArrayList<SelectOptionVO>();
					
			if(groupCategories != null && groupCategories.size() > 0)
				  groupNames.addAll(influencingPeopleService.getLocalGroupsByCategoryAndUser(groupCategories.get(0).getId(), userId));
					
			designations = new ArrayList<SelectOptionVO>();
			
			if(groupCategories != null && groupCategories.size() > 0)
			designations.addAll(influencingPeopleService.getDesignationsByCategoryAndUser(groupCategories.get(0).getId(), userId));
			
		}else{
			
			groupCategories = influencingPeopleService.getGroupCategoryByCategoryId(new Long(grCategoryId));
			
			if(groupId.equalsIgnoreCase("0") || groupId == null)
			    groupNames = influencingPeopleService.getLocalGroupsByCategoryAndUser(new Long(grCategoryId), userId);
			else
				groupNames = influencingPeopleService.getLocalGroupDetailsByGroupId(new Long(groupId));
			
			designations = influencingPeopleService.getDesignationsByCategoryAndUser(new Long(grCategoryId), userId);
			
			
		}
		
		session.setAttribute("groupCategories", groupCategories);
		session.setAttribute("groupNames", groupNames);
		session.setAttribute("designations", designations);
		
		return Action.SUCCESS;
	}
	
	public String addNewDesignation()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String desigName = jObj.getString("desigName");
		String desigDesc = jObj.getString("desigDesc");
		String categoryId = jObj.getString("categoryId");
		
		designations = influencingPeopleService.saveNewDesignationForACategory(new Long(categoryId), userId, desigName, desigDesc);
		session.setAttribute("designations", designations);
		
		return Action.SUCCESS;
	}
	
	public String getGroupNamesByCategory()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String categoryId = jObj.getString("categoryId");
		groupNames = influencingPeopleService.getLocalGroupsByCategoryAndUser(new Long(categoryId), userId);
		session.setAttribute("groupNames", groupNames);
		
		return Action.SUCCESS;
	}
	
	public String getDesignationsByCategory()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String categoryId = jObj.getString("categoryId");
		designations = influencingPeopleService.getDesignationsByCategoryAndUser(new Long(categoryId), userId);
		session.setAttribute("designations", designations);
		
		return Action.SUCCESS;
	}
	
	public String createLocalGroupMember()
	{
		
		return Action.SUCCESS;
	}
	
	public String addNewGroupCategory(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		try {
			jObj = new JSONObject(getTask());
			String group = jObj.getString("group");
			groupCategories = influencingPeopleService.saveNewGroupCatagory(group,userId);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Object getModel() {
		
		return localUserGroupDetailsVO;
	}
	
	public void prepare() throws Exception {
		
		localUserGroupId = request.getParameter("localUserGroupId");
		windowTask = request.getParameter("windowTask");
								
        if( "0".equals(localUserGroupId)) {
        	
        	localUserGroupDetailsVO = new LocalUserGroupDetailsVO();
        	
          } 
        else if(localUserGroupId != null) 
        {	
        	localUserGroupDetailsVO = influencingPeopleService.getLocalUserGroupDetailsById(new Long(getLocalUserGroupId()));
        	localUserGroupDetailsVO.setWindowTask(windowTask);
        	// prepopulateLocations(localUserGroupDetailsVO);
        	        	
        }
		
	}
	
}
