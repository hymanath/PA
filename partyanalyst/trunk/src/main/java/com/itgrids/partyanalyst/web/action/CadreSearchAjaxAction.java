package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCategoryVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.PartyCadreDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SearchListVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAjaxAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	JSONObject jObj = null,respObj=null;
	private String task = null;
	private List<SelectOptionVO> statesListForACountry;
	private List<SelectOptionVO> districtsListForACountry;
	private List<SelectOptionVO> constituenciesListForADistrict;
	private List<SelectOptionVO >mandalsListForAConstituency;
	private List<SelectOptionVO> villagesListForAMandal;
	private IRegionServiceData regionServiceDataImp=null;
	private CadreManagementService cadreManagementService;
	private List<CadreInfo> cadreInfo;
	private SmsResultVO smsResultVO;
	private SearchListVO searchListVO;
	private String cadreId;
	private Long totalSearchCount;
	
	
	public SmsResultVO getSmsResultVO() {
		return smsResultVO;
	}

	public void setSmsResultVO(SmsResultVO smsResultVO) {
		this.smsResultVO = smsResultVO;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<CadreInfo> getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(List<CadreInfo> cadreInfo) {
		this.cadreInfo = cadreInfo;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public List<SelectOptionVO> getMandalsListForAConstituency() {
		return mandalsListForAConstituency;
	}

	public void setMandalsListForAConstituency(
			List<SelectOptionVO> mandalsListForAConstituency) {
		this.mandalsListForAConstituency = mandalsListForAConstituency;
	}

	public List<SelectOptionVO> getVillagesListForAMandal() {
		return villagesListForAMandal;
	}

	public void setVillagesListForAMandal(
			List<SelectOptionVO> villagesListForAMandal) {
		this.villagesListForAMandal = villagesListForAMandal;
	}

	public List<SelectOptionVO> getConstituenciesListForADistrict() {
		return constituenciesListForADistrict;
	}

	public void setConstituenciesListForADistrict(
			List<SelectOptionVO> constituenciesListForADistrict) {
		this.constituenciesListForADistrict = constituenciesListForADistrict;
	}

	public List<SelectOptionVO> getDistrictsListForACountry() {
		return districtsListForACountry;
	}

	public void setDistrictsListForACountry(
			List<SelectOptionVO> districtsListForACountry) {
		this.districtsListForACountry = districtsListForACountry;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<SelectOptionVO> getStatesListForACountry() {
		return statesListForACountry;
	}

	public void setStatesListForACountry(List<SelectOptionVO> statesListForACountry) {
		this.statesListForACountry = statesListForACountry;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public SearchListVO getSearchListVO() {
		return searchListVO;
	}

	public void setSearchListVO(SearchListVO searchListVO) {
		this.searchListVO = searchListVO;
	}

	public Long getTotalSearchCount() {
		return totalSearchCount;
	}

	public void setTotalSearchCount(Long totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}

	public String execute() throws Exception
	{
		
		return Action.SUCCESS;
	}
	
	
	public String getStatesForACountry()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		statesListForACountry = regionServiceDataImp.getStatesByCountry(new Long(jObj.getString("countryId")));
		//statesListForACountry = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		if(statesListForACountry != null && statesListForACountry.size() > 0)
			statesListForACountry.add(0, new SelectOptionVO(0L,"Select State"));
		Collections.sort(statesListForACountry);
		return Action.SUCCESS;
	}
	
	public String getDistrictsForAState()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		districtsListForACountry = regionServiceDataImp.getDistrictsByStateID(new Long(jObj.getString("stateId")));
		
		if(districtsListForACountry != null && districtsListForACountry.size() > 1)
			districtsListForACountry.add(0, new SelectOptionVO(0L,"Select District"));
		
	 return Action.SUCCESS;
	}
	
	public String getConstituenciesForADistrict()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		constituenciesListForADistrict = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("districtId")));
		/*
		 * Modified by ravi 
		 * please refer previous version to check for original code.
		 */ 
		if(constituenciesListForADistrict == null || constituenciesListForADistrict.size() == 0)
			constituenciesListForADistrict.add(0, new SelectOptionVO(0L,"Select Constituency"));
		
		if(constituenciesListForADistrict != null && constituenciesListForADistrict.size() > 1)
			constituenciesListForADistrict.add(0, new SelectOptionVO(0L,"Select Constituency"));
		
		return Action.SUCCESS;
	}
	
	public String getMandalsForAConstituency()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mandalsListForAConstituency = regionServiceDataImp.getMandalsByConstituencyID(new Long(jObj.getString("constituencyId")));
		return Action.SUCCESS;
	}
	
	public String getVillagesForAMandal()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		villagesListForAMandal = cadreManagementService.findVillagesByTehsilID(jObj.getString("mandalId"));
		return Action.SUCCESS;
	}
	
	public String getCadresInfo()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String reportLevel = jObj.getString("reportLevel");
		
		return Action.SUCCESS;
	}
	
	public String sendSMSToSelectedCadres()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<SmsVO> cadresList = new ArrayList<SmsVO>();
		
		JSONArray cadresArray = jObj.getJSONArray("cadreIds"); 
		
		if(cadresArray.length()>0)
		{
			for(int i=0; i<cadresArray.length();i++)
			{
				SmsVO smsvo = new SmsVO(); 
				JSONObject cadreObj = cadresArray.getJSONObject(i);
				smsvo.setCadreId(new Long(cadreObj.getString("cadreId")));
				smsvo.setCadreName(cadreObj.getString("cadreName"));
				smsvo.setMobileNO(cadreObj.getString("cadreMobile"));
				
				cadresList.add(smsvo);
			}
		}
		
		smsResultVO = cadreManagementService.sendSMSToSelectedCadre(user.getRegistrationID(), jObj.getString("includeName"), true, jObj.getString("txtAreaValue"), cadresList);
		return Action.SUCCESS;
	}
	public String sendSMSToCadres()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String message = jObj.getString("txtAreaValue");
		String senderName = jObj.getString("senderName");
		if(!senderName.isEmpty())
		{
			message = message+" "+IConstants.MESSAGE_APPENDER+" "+senderName;
		}
		smsResultVO  = cadreManagementService.sendSMSToSelectedCadreCriteria(user.getRegistrationID(), setPartyCadreDetails(jObj), jObj.getString("includeCadreName"),message);
		return Action.SUCCESS;
	}
	public String getCadresSearch()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cadreInfo = cadreManagementService.getCadreDetailsBySearchCriteria(user.getRegistrationID(), setPartyCadreDetails(jObj));
		
		return Action.SUCCESS;
	}
	
	public PartyCadreDetailsVO setPartyCadreDetails(JSONObject jObj)
	{		
		PartyCadreDetailsVO partyCadreDetailsVO = new PartyCadreDetailsVO();
		
		partyCadreDetailsVO.setCadreName(jObj.getString("cadreName").trim());
		partyCadreDetailsVO.setTaskName(jObj.getString("task").trim());
		
		if(jObj.getString("cadreType").equalsIgnoreCase("all"))
			partyCadreDetailsVO.setCadreType(IConstants.ALL);
		else if(jObj.getString("cadreType").equalsIgnoreCase("active"))
			partyCadreDetailsVO.setCadreType(IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		else if(jObj.getString("cadreType").equalsIgnoreCase("normal"))
			partyCadreDetailsVO.setCadreType(IConstants.CADRE_MEMBER_TYPE_NORMAL);
		
		partyCadreDetailsVO.setCadreLevelId(new Long(jObj.getString("reportLevel")));
		partyCadreDetailsVO.setCadreLocationId(new Long(jObj.getString("reportLocationValue")));
		partyCadreDetailsVO.setIsSocialStatus(jObj.getBoolean("socialStatus"));
		partyCadreDetailsVO.setGenderSearchType(jObj.getString("gender"));
		partyCadreDetailsVO.setBloodGroupId(jObj.getLong("bloodGroupId"));
		partyCadreDetailsVO.setRadioButtonValue(jObj.getString("nameSearchTYpe"));
		
		if(jObj.getString("cadreRegTypeRadioValue").equalsIgnoreCase("allCadres"))
			partyCadreDetailsVO.setRegisterCadreSearchType(IConstants.ALL_CADRES);
		else if(jObj.getString("cadreRegTypeRadioValue").equalsIgnoreCase("registeredByUser"))
			partyCadreDetailsVO.setRegisterCadreSearchType(IConstants.CADRE_REGISTERED_BY_USER);
		else if(jObj.getString("cadreRegTypeRadioValue").equalsIgnoreCase("registeredFromOnline"))
			partyCadreDetailsVO.setRegisterCadreSearchType(IConstants.CADRE_REGISTER_FROM_ONLINE);
		
		if(jObj.getBoolean("socialStatus"))
		{
			JSONArray socialStatusArray = jObj.getJSONArray("socialStatusArray");
			
			for(int i=0; i<socialStatusArray.length(); i++)
			{
				JSONObject socialObj = socialStatusArray.getJSONObject(i);
				JSONArray categoryArray = socialObj.getJSONArray("ElmtValue");
				String categoryValue = socialObj.getString("statusValue");
				
				List<CadreCategoryVO> categoryList = new ArrayList<CadreCategoryVO>();
				for(int j=0;j<categoryArray.length();j++)
				{	
					String cValue = categoryArray.getString(j);
					categoryList.add(new CadreCategoryVO(new Long(cValue)));
				}
				if(categoryValue.equalsIgnoreCase("resevation"))
					partyCadreDetailsVO.setCadreCasteCategory(categoryList);
				else if(categoryValue.equalsIgnoreCase("education"))
					partyCadreDetailsVO.setCadreEducationQualification(categoryList);
				else if(categoryValue.equalsIgnoreCase("occupation"))
					partyCadreDetailsVO.setCadreOccupation(categoryList);
			}
		}
		
		if(jObj.getJSONArray("searchCriteriaArray").length()>0)
		{
			JSONArray searchCriteriaArray = jObj.getJSONArray("searchCriteriaArray");
			
			for(int i=0;i<searchCriteriaArray.length();i++)
			{
				JSONObject criteriaObj = searchCriteriaArray.getJSONObject(i);
				JSONArray categoryArray = criteriaObj.getJSONArray("ElmtValue");
				String categoryValue = criteriaObj.getString("statusValue");
				
				List<CadreCategoryVO> criteriaList = new ArrayList<CadreCategoryVO>();
				
				for(int j=0;j<categoryArray.length();j++)
				{	
					String cValue = categoryArray.getString(j);
					criteriaList.add(new CadreCategoryVO(new Long(cValue)));
				}
				
				Long sCriteriaValue = new Long(jObj.getString("searchCriteriaValue"));
				if(categoryValue.equalsIgnoreCase("committe"))
					partyCadreDetailsVO.setCadreWorkingCommittee(criteriaList);
				else if(categoryValue.equalsIgnoreCase("skills"))
					partyCadreDetailsVO.setCadreSkillSet(criteriaList);
				else if(categoryValue.equalsIgnoreCase("trainingCamps"))
					partyCadreDetailsVO.setCadreTrainingCamps(criteriaList);
				
			}
		}
		
		
		if(jObj.getString("searchType").equalsIgnoreCase("location"))
			partyCadreDetailsVO.setSearchType(IConstants.LOCATION_BASED);
		else if(jObj.getString("searchType").equalsIgnoreCase("level"))
			partyCadreDetailsVO.setSearchType(IConstants.LEVEL_BASED);
		
		//partyCadreDetailsVO.setSkillsSearchType(jObj.getString("searchCriteria"));		
		
		if(jObj.getString("performSearch").equalsIgnoreCase("and"))
			partyCadreDetailsVO.setIsOrSearch(false);
		else if(jObj.getString("performSearch").equalsIgnoreCase("or"))
			partyCadreDetailsVO.setIsOrSearch(true);
		
		return partyCadreDetailsVO;
	}
	
	public String getCadreDetailsForSMS()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		String sortOption = request.getParameter("sort");
		String order = request.getParameter("dir");
		String windowTask = request.getParameter("windowTask");
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer maxResult = Integer.parseInt(request.getParameter("results"));
		
		Long registrationId = user.getParentUserId() == null ? user.getRegistrationID() : user.getParentUserId() ;
		
		String param = null;
		param = getTask();
		
		searchListVO = new SearchListVO();
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cadreInfo = cadreManagementService.getCadreDetailsForSMS(registrationId,setPartyCadreDetails(jObj),windowTask,sortOption,order,startIndex,maxResult);
		
		searchListVO.setTotalSearchCount(new Long(cadreInfo.get(0).getPinCode()));
		
		if(new Long(cadreInfo.get(0).getPinCode()) !=0){
		searchListVO.setCadreInfo(cadreInfo);
		}
		
		else if(searchListVO.getCadreInfo() != null && searchListVO.getCadreInfo().size() > 0)
		{
			CadreInfo cadre = searchListVO.getCadreInfo().get(0);
			searchListVO.getCadreInfo().remove(cadre);
		}
		
		return SUCCESS;
	}

}	
