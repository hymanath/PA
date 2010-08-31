package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAjaxAction extends ActionSupport implements ServletRequestAware{
	
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
	private String cadreId;
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statesListForACountry = regionServiceDataImp.getStatesByCountry(new Long(jObj.getString("countryId")));
		return Action.SUCCESS;
	}
	
	public String getDistrictsForAState()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		districtsListForACountry = regionServiceDataImp.getDistrictsByStateID(new Long(jObj.getString("stateId")));
		return Action.SUCCESS;
		
	}
	
	public String getConstituenciesForADistrict()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		constituenciesListForADistrict = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("districtId")));
		return Action.SUCCESS;
	}
	
	public String getMandalsForAConstituency()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String reportLevel = jObj.getString("reportLevel");
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		smsResultVO  = cadreManagementService.sendSMSToSelectedCadreCriteria(user.getRegistrationID(), setPartyCadreDetails(jObj), jObj.getString("includeCadreName"), jObj.getString("txtAreaValue"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cadreInfo = cadreManagementService.getCadreDetailsBySearchCriteria(user.getRegistrationID(), setPartyCadreDetails(jObj));
		
		return Action.SUCCESS;
	}
	
	public PartyCadreDetailsVO setPartyCadreDetails(JSONObject jObj)
	{		
		PartyCadreDetailsVO partyCadreDetailsVO = new PartyCadreDetailsVO();
		
		if(jObj.getString("cadreType").equalsIgnoreCase("all"))
			partyCadreDetailsVO.setCadreType(IConstants.ALL);
		else if(jObj.getString("cadreType").equalsIgnoreCase("active"))
			partyCadreDetailsVO.setCadreType(IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		else if(jObj.getString("cadreType").equalsIgnoreCase("normal"))
			partyCadreDetailsVO.setCadreType(IConstants.CADRE_MEMBER_TYPE_NORMAL);
		
		partyCadreDetailsVO.setCadreLevelId(new Long(jObj.getString("reportLevel")));
		partyCadreDetailsVO.setCadreLocationId(new Long(jObj.getString("reportLocationValue")));
		partyCadreDetailsVO.setIsSocialStatus(jObj.getBoolean("socialStatus"));
		
		if(jObj.getBoolean("socialStatus"))
		{
			JSONArray socialStatusArray = jObj.getJSONArray("socialStatusArray");
			
			for(int i=0; i<socialStatusArray.length(); i++)
			{
				JSONObject socialObj = socialStatusArray.getJSONObject(i);
				String categoryId = socialObj.getString("ElmtValue");
				String categoryValue = socialObj.getString("statusValue");
				
				if(categoryValue.equalsIgnoreCase("resevation"))
					partyCadreDetailsVO.setCadreCasteCategory(new CadreCategoryVO(new Long(categoryId)));
				else if(categoryValue.equalsIgnoreCase("education"))
					partyCadreDetailsVO.setCadreEducationQualification(new CadreCategoryVO(new Long(categoryId)));
				else if(categoryValue.equalsIgnoreCase("occupation"))
					partyCadreDetailsVO.setCadreOccupation(new CadreCategoryVO(new Long(categoryId)));
			}
		}
		
		if(jObj.getString("searchType").equalsIgnoreCase("location"))
			partyCadreDetailsVO.setSearchType(IConstants.LOCATION_BASED);
		else if(jObj.getString("searchType").equalsIgnoreCase("level"))
			partyCadreDetailsVO.setSearchType(IConstants.LEVEL_BASED);
		
		partyCadreDetailsVO.setSkillsSearchType(jObj.getString("searchCriteria"));		
		if(!jObj.getString("searchCriteria").equalsIgnoreCase("all"))
		{
			Long sCriteriaValue = new Long(jObj.getString("searchCriteriaValue"));
			if(jObj.getString("searchCriteria").equalsIgnoreCase("committe"))
				partyCadreDetailsVO.setCadreWorkingCommittee(new CadreCategoryVO(new Long(sCriteriaValue)));
			else if(jObj.getString("searchCriteria").equalsIgnoreCase("skills"))
				partyCadreDetailsVO.setCadreSkillSet(new CadreCategoryVO(new Long(sCriteriaValue)));
			else if(jObj.getString("searchCriteria").equalsIgnoreCase("trainingCamps"))
				partyCadreDetailsVO.setCadreTrainingCamps(new CadreCategoryVO(new Long(sCriteriaValue)));
			
		}
		if(jObj.getString("performSearch").equalsIgnoreCase("and"))
			partyCadreDetailsVO.setIsOrSearch(false);
		else if(jObj.getString("performSearch").equalsIgnoreCase("or"))
			partyCadreDetailsVO.setIsOrSearch(true);
		
		return partyCadreDetailsVO;
	}
	
	
	public List<CadreInfo> getDummyCadresInfo()
	{
		List<CadreInfo> cadresList = new ArrayList<CadreInfo>();
		
		CadreInfo cadre1 = new CadreInfo();
		cadre1.setCadreID(new Long(1));
		cadre1.setFirstName("Sai");
		cadre1.setMiddleName("");
		cadre1.setLastName("Krishna");
		cadre1.setMobile("9989876597");
		cadre1.setLandLineNo("2556688");
		cadre1.setEmail("sai.basetti@gmail.com");
		cadre1.setGender("Male");
		
		CadreInfo cadre2 = new CadreInfo();
		cadre2.setCadreID(new Long(2));
		cadre2.setFirstName("Shiva");
		cadre2.setMiddleName("");
		cadre2.setLastName("kumar");
		cadre2.setMobile("9948755741");
		cadre2.setLandLineNo("65659865");
		cadre2.setEmail("rsivakumar08@gmail.com");
		cadre2.setGender("Male");
		
		CadreInfo cadre3 = new CadreInfo();
		cadre3.setCadreID(new Long(3));
		cadre3.setFirstName("Raghavender");
		cadre3.setMiddleName("");
		cadre3.setLastName("Prasad");
		cadre3.setMobile("9989922789");
		cadre3.setLandLineNo("6565987854");
		cadre3.setEmail("raghavenderprasad@gmail.com");
		cadre3.setGender("Male");
		
		CadreInfo cadre4 = new CadreInfo();
		cadre4.setCadreID(new Long(4));
		cadre4.setFirstName("Sandeep");
		cadre4.setMiddleName("");
		cadre4.setLastName("Reddy");
		cadre4.setMobile("9959839263");
		cadre4.setLandLineNo("6565987854");
		cadre4.setEmail("sandeepreddy.at@gmail.com");
		cadre4.setGender("Male");
		
		cadresList.add(cadre1);
		cadresList.add(cadre2);
		cadresList.add(cadre3);
		cadresList.add(cadre4);
		
		
		return cadresList;
	}
}	
