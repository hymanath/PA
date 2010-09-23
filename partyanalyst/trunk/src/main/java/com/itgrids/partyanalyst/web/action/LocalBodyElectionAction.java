/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LocalBodyElectionAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LocalBodyElectionAction.class);
	private IStaticDataService staticDataService;
	private ILocalBodyElectionService localBodyElectionService;
	
	private String task = null;
	JSONObject jObj = null;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	
	private Long stateId;
	private Long localBodyId;
	private List<SelectOptionVO> localBodys;
	private List<SelectOptionVO> localBodyTypes;
	
	private LocalBodyElectionResultsVO localBodyElectionResults;
		
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	public ILocalBodyElectionService getLocalBodyElectionService() {
		return localBodyElectionService;
	}

	public void setLocalBodyElectionService(
			ILocalBodyElectionService localBodyElectionService) {
		this.localBodyElectionService = localBodyElectionService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public List<SelectOptionVO> getLocalBodys() {
		return localBodys;
	}

	public void setLocalBodys(List<SelectOptionVO> localBodys) {
		this.localBodys = localBodys;
	}

	public List<SelectOptionVO> getLocalBodyTypes() {
		return localBodyTypes;
	}

	public void setLocalBodyTypes(List<SelectOptionVO> localBodyTypes) {
		this.localBodyTypes = localBodyTypes;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getLocalBodyId() {
		return localBodyId;
	}

	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}

	public LocalBodyElectionResultsVO getLocalBodyElectionResults() {
		return localBodyElectionResults;
	}

	public void setLocalBodyElectionResults(
			LocalBodyElectionResultsVO localBodyElectionResults) {
		this.localBodyElectionResults = localBodyElectionResults;
	}

	public String execute(){
		
		localBodyElectionResults = localBodyElectionService.getLocalBodyElectionResultsByLocalBodyTypeAndYear(localBodyId, stateId);
		if(localBodyElectionResults != null){
			if(localBodyElectionResults.getMuncipalityVO() != null){
				String chartName = generateChartForResultsInElection(localBodyElectionResults.getMuncipalityVO(),localBodyElectionResults.getLocalBodyRegion(),localBodyElectionResults.getLocalBodyElectionYear(),localBodyElectionResults.getLocalBodyElectionType());
				localBodyElectionResults.setHighLevelChart(chartName);
			}
		}
		
		return Action.SUCCESS;
	}
	
	public String getLocalBodiesTypesInAState(){
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		}catch (ParseException e) {
			e.printStackTrace();
			log.error("Exception Raised :" + e);
		}
		
		Long stateId = new Long(jObj.getString("stateId"));
		localBodyTypes = staticDataService.getLocalBodyElectionTypesInAState(stateId);                                          
				
		return Action.SUCCESS;
	}
	
	
	public String getLocalBodiesInAState(){
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		}catch (ParseException e) {
			e.printStackTrace();
			log.error("Exception Raised :" + e);
		}
		
		Long stateId = new Long(jObj.getString("stateId"));
		Long typeId = new Long(jObj.getString("electionTypeId"));
		localBodys = staticDataService.getLocalBodysInAStateByType(stateId, typeId);
		
		return Action.SUCCESS;
	}
	
	public String generateChartForResultsInElection(List<TeshilPartyInfoVO> partyResultsVO,String localBodyName,String electionYear,String localBodyType){
		
		if(log.isDebugEnabled())
			log.debug(" Inside generateChartForResultsInElection Method ..");
		
		session = request.getSession();
				
		String chartId = localBodyName.concat("_LocalBodyElection").concat("_For_").concat(electionYear);
		String chartName = localBodyType+ chartId + session.getId()+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		
		ChartProducer.createLineChart("", "", "", createDataSetForChart(partyResultsVO,localBodyName,electionYear,localBodyType), chartPath,280,700, null,true );
		
		return chartName;
	}
	
	private CategoryDataset createDataSetForChart(List<TeshilPartyInfoVO> partyResultsVO,String localBodyName,String electionYear,String localBodyType){
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "1st Position";
		String series2 = "2nd Position";
		String series3 = "3rd Position";
		String series4 = "Nth Position";
		
		
		if(partyResultsVO != null && partyResultsVO.size() > 0){
			for(TeshilPartyInfoVO resultsList:partyResultsVO){
				dataset.addValue(resultsList.getPartyWonSeats(), resultsList.getPartyName(), series1);
				dataset.addValue(resultsList.getPartySecndPos(), resultsList.getPartyName(), series2);
				dataset.addValue(resultsList.getPartyThirdPos(), resultsList.getPartyName(), series3);
				dataset.addValue(resultsList.getPartyNthPos(), resultsList.getPartyName(), series4);
			}
		}
	 return dataset;
	}
	
}
