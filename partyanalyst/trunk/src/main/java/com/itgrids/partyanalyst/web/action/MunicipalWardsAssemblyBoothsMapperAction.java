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
import com.itgrids.partyanalyst.service.IBoothMapperService;
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

	public String execute() throws Exception {
		
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
		
		Long lebId = jObj.getLong("lebId");
		String year = jObj.getString("year");
		
		List<Long> mappedIdsList = new ArrayList<Long>();
		
		for(int i=0; i < boothsOrWardsOrLEBIds.length(); i++)
			mappedIdsList.add(new Long((String)boothsOrWardsOrLEBIds.get(i)));
		
		if(isBoothLevelMap)
			resultWithExceptionVO = boothMapperService.saveBoothLocalElectionBodyMappingInfo(mappedIdsList, mappedlocationId, isMapToWard);
		
		if(isAssemblyLevelMap)
			resultWithExceptionVO = boothMapperService.saveAssemblyLocalBodyMappingInfo(lebId, mappedIdsList, mappedlocationId, year, isMapToWard);
	}
	
}
