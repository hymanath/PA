package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyWiseCensusMappingAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final long serialVersionUID = 2372580021958522256L;
	private HttpServletRequest request;
	private ServletContext context;
	private List<SelectOptionVO> statesListForCensus;
	JSONObject jObj = null;
	private String task;
	private CensusVO censusVO;
	private IConstituencyPageService constituencyPageService;


	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public CensusVO getCensusVO() {
		return censusVO;
	}

	public void setCensusVO(CensusVO censusVO) {
		this.censusVO = censusVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	
	public List<SelectOptionVO> getStatesListForCensus() {
		return statesListForCensus;
	}

	public void setStatesListForCensus(List<SelectOptionVO> statesListForCensus) {
		this.statesListForCensus = statesListForCensus;
	}

	public String execute()
	{
		statesListForCensus = new ArrayList<SelectOptionVO>();		
		statesListForCensus.add(new SelectOptionVO(1L,"Andhra Pradesh"));
			
		return Action.SUCCESS;
	}
	
	public String censusMappingAjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long stateId        = jObj.getLong("stateId");
		Long districtId     = jObj.getLong("districtId");
		String reportLevel  = jObj.getString("reportLevel");
		Long censusYear           = jObj.getLong("yearValue");
		
		censusVO = constituencyPageService.mapConstituencyWiseCensusDetails(stateId,districtId,2009l,censusYear,reportLevel);
		
		return SUCCESS;
	}

}
