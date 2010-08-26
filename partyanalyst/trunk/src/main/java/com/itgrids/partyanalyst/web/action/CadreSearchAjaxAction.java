package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAjaxAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
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
	private String cadreId;
	
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
		
		return Action.SUCCESS;
	}
	public String getCadresSearch()
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
		String locationValue = jObj.getString("locationValue");
		String cadreType = jObj.getString("cadreType");
		String searchCriteria = jObj.getString("searchCriteria");
		String searchCriteriaValue = jObj.getString("searchCriteriaValue");
		
		
		cadreInfo = getDummyCadresInfo();
		return Action.SUCCESS;
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
