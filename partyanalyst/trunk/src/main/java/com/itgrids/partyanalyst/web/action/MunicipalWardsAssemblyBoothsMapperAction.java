package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBoothMapperService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MunicipalWardsAssemblyBoothsMapperAction extends ActionSupport implements ServletRequestAware,ServletContextAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LocationsHierarchyAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	JSONObject jObj = null;
	private String task = null;
	private List<ConstituencyBoothInfoVO> boothsInAssembly;
	private IBoothMapperService boothMapperService;
	private ResultStatus resultWithExceptionVO;
	
	private String windowTask;
	private Long constituencyId;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> parliamentConstituencyList;
	private IRegionServiceData regionServiceDataImp;
	private CadreManagementService cadreManagementService;
	private IStaticDataService staticDataService;
	
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	
	
	public List<ConstituencyBoothInfoVO> getBoothsInAssembly() {
		return boothsInAssembly;
	}

	public void setBoothsInAssembly(List<ConstituencyBoothInfoVO> boothsInAssembly) {
		this.boothsInAssembly = boothsInAssembly;
	}	

	public IBoothMapperService getBoothMapperService() {
		return boothMapperService;
	}

	public void setBoothMapperService(IBoothMapperService boothMapperService) {
		this.boothMapperService = boothMapperService;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}	

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
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

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}	

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}	

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	// to pre select constituency list element in the page
	public Long getDefaultConstituency() {
		return this.constituencyId;
	}
	
	public ResultStatus getResultWithExceptionVO() {
		return resultWithExceptionVO;
	}

	public void setResultWithExceptionVO(ResultStatus resultWithExceptionVO) {
		this.resultWithExceptionVO = resultWithExceptionVO;
	}

	public String execute() throws Exception {
		stateList = new ArrayList<SelectOptionVO>(0);
		districtList = new ArrayList<SelectOptionVO>(0);
		constituencyList = new ArrayList<SelectOptionVO>(0);
		parliamentConstituencyList = new ArrayList<SelectOptionVO>(0);
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());
		
		
		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList.add(list.get(2));			
						
		}else if("COUNTRY".equals(accessType))
		{
			log.debug("Access Type = Country ****");
			stateList = regionServiceDataImp.getStatesByCountry(accessValue);
			stateList.add(0,new SelectOptionVO(0l,"Select State"));			
			
		}else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			stateList.add(obj2);
			districtList = regionServiceDataImp.getDistrictsByStateID(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			

			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));
			districtList.add(list.get(1));
			constituencyList = regionServiceDataImp.getConstituenciesByAreaTypeInDistrict(accessValue, "RURAL");
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));			
			
		} else if("MP".equals(accessType)){
			log.debug("Access Type = MP ****");
			
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
		}		
		return Action.SUCCESS;		
	}
	
	public String getBoothsInAssemblyConstituency()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long id = jObj.getLong("id");
		boothsInAssembly = boothMapperService.getBoothOfAssemblyByYear(id, IConstants.DELIMITATION_YEAR);
		return Action.SUCCESS;
	}
	
	public String saveBoothsInAssemblyConstituency()
	{
		String param = null;
		param = getTask();
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Boolean isAssemblyLevelMap = jObj.getBoolean("isAssemblyToLocal");
		Boolean isBoothLevelMap = jObj.getBoolean("isBoothToLocal");
		Boolean isMapToWard = jObj.getBoolean("isWard");
		
		Long mappedlocationId = jObj.getLong("mappedlocationId");
		JSONArray boothsOrWardsOrLEBIds = jObj.getJSONArray("listOfIds");
		JSONArray boothsOrWardsOrLEBIdsToModify = jObj.getJSONArray("listOfModificationIds");
		
		Long lebId = jObj.getLong("lebId");
		String year = jObj.getString("year");
		
		List<Long> mappedIdsList = new ArrayList<Long>();
		List<Long> mappedIdsListToModify = new ArrayList<Long>();
		
		for(int i=0; i < boothsOrWardsOrLEBIds.length(); i++)
			mappedIdsList.add(new Long((String)boothsOrWardsOrLEBIds.get(i)));
		if(boothsOrWardsOrLEBIdsToModify.length()>0)
			for(int j=0; j < boothsOrWardsOrLEBIdsToModify.length(); j++)
				mappedIdsListToModify.add(new Long((String)boothsOrWardsOrLEBIdsToModify.get(j)));
		
		
		if(isBoothLevelMap)
			resultWithExceptionVO = boothMapperService.saveBoothLocalElectionBodyMappingInfo(mappedIdsList,mappedIdsListToModify, mappedlocationId, isMapToWard, regVO.getRegistrationID());
		
		if(isAssemblyLevelMap)
			resultWithExceptionVO = boothMapperService.saveAssemblyLocalBodyMappingInfo(lebId, mappedIdsList,mappedIdsListToModify, mappedlocationId, year, isMapToWard, regVO.getRegistrationID());
		return Action.SUCCESS;
	}
	
}
