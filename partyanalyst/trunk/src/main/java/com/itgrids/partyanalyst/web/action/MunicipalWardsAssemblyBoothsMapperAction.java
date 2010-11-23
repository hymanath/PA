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
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBoothMapperService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private ResultWithExceptionVO resultWithExceptionVO; 
	private String windowTask;
	private Long constituencyId;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private IRegionServiceData regionServiceDataImp;
	
	
	
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

	public ResultWithExceptionVO getResultWithExceptionVO() {
		return resultWithExceptionVO;
	}

	public void setResultWithExceptionVO(ResultWithExceptionVO resultWithExceptionVO) {
		this.resultWithExceptionVO = resultWithExceptionVO;
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

	public String execute() throws Exception {
		stateList = new ArrayList<SelectOptionVO>(0);
		districtList = new ArrayList<SelectOptionVO>(0);
		constituencyList = new ArrayList<SelectOptionVO>(0);
		
		if("update".equals(windowTask) && constituencyId != 0l)
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(constituencyId);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList.add(list.get(2));
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
	
	public void saveBoothsInAssemblyConstituency()
	{
		String param = null;
		param = getTask();

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
			resultWithExceptionVO = boothMapperService.saveBoothLocalElectionBodyMappingInfo(mappedIdsList,mappedIdsListToModify, mappedlocationId, isMapToWard);
		
		if(isAssemblyLevelMap)
			resultWithExceptionVO = boothMapperService.saveAssemblyLocalBodyMappingInfo(lebId, mappedIdsList,mappedIdsListToModify, mappedlocationId, year, isMapToWard);
	}
	
}
