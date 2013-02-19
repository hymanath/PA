/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private Long localBodyElectionTypeId; 
	private List<SelectOptionVO> localBodys;
	private List<SelectOptionVO> localBodyTypes;
	private List<PartyElectionResultsInConstituencyVO> partyElectionResultsInConstituencyVO;
	private LocalBodyElectionResultsVO localBodyElectionResults;
	private TeshilPartyInfoVO teshilPartyInfoVO;
	private ConstituencyVO greaterInfo;
	private List<ProblemBeanVO> problemBean;		
	private IProblemManagementReportService problemManagementReportService;
	
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}

	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}

	public List<PartyElectionResultsInConstituencyVO> getPartyElectionResultsInConstituencyVO() {
		return partyElectionResultsInConstituencyVO;
	}

	public void setPartyElectionResultsInConstituencyVO(
			List<PartyElectionResultsInConstituencyVO> partyElectionResultsInConstituencyVO) {
		this.partyElectionResultsInConstituencyVO = partyElectionResultsInConstituencyVO;
	}
		
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}

	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
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

	public TeshilPartyInfoVO getTeshilPartyInfoVO() {
		return teshilPartyInfoVO;
	}

	public void setTeshilPartyInfoVO(TeshilPartyInfoVO teshilPartyInfoVO) {
		this.teshilPartyInfoVO = teshilPartyInfoVO;
	}

	public ConstituencyVO getGreaterInfo() {
		return greaterInfo;
	}

	public void setGreaterInfo(ConstituencyVO greaterInfo) {
		this.greaterInfo = greaterInfo;
	}

	public String execute(){
		
		localBodyElectionResults = localBodyElectionService.getLocalBodyElectionResultsByLocalBodyTypeAndYear(localBodyId, stateId);
		if(localBodyElectionResults != null){
			if(localBodyElectionResults.getMuncipalityVO() != null){
				String chartName = generateChartForResultsInElection(localBodyElectionResults.getMuncipalityVO(),localBodyElectionResults.getLocalBodyRegion(),localBodyElectionResults.getLocalBodyElectionYear(),localBodyElectionResults.getLocalBodyElectionType(),localBodyId);
				localBodyElectionResults.setHighLevelChart(chartName);
			}
		}
		List<Long> listOfLocalBodyElections = new ArrayList<Long>();
		listOfLocalBodyElections.add(localBodyId);
		problemBean = problemManagementReportService.getAllProblemsForGivenLocation(listOfLocalBodyElections,IConstants.MUNICIPAL_CORP_GMC).getApprovalProblems();
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
	
	public String getWardWiseElectionResults()
	{
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
		Long localBodyId = new Long(jObj.getString("localBodyId"));
		Long localBodyElectionId = new Long(jObj.getString("localBodyElectionId"));
		Long elmtId = new Long(jObj.getString("elmtId"));
		
		String taskType = jObj.getString("taskType");
		
		if(taskType.equalsIgnoreCase("all"))
			partyElectionResultsInConstituencyVO = localBodyElectionService.getLocalBodyElectionResultsInAnElection(localBodyId, stateId, localBodyElectionId);
		else if(taskType.equalsIgnoreCase("partyWise"))
			partyElectionResultsInConstituencyVO = localBodyElectionService.getLocalBodyElectionResultsForAPartyInAnElection(localBodyId, stateId, localBodyElectionId,elmtId);
		else if(taskType.equalsIgnoreCase("wardWise"))
			partyElectionResultsInConstituencyVO = localBodyElectionService.getLocalBodyElectionResultsForAWardInAnElection(localBodyId, stateId, localBodyElectionId,elmtId);
			
		return Action.SUCCESS;
	}
	public String generateChartForResultsInElection(List<TeshilPartyInfoVO> partyResultsVO,String localBodyName,String electionYear,String localBodyType,Long localBodyId){
		
		if(log.isDebugEnabled())
			log.debug(" Inside generateChartForResultsInElection Method ..");
		
		session = request.getSession();
		String cPath = request.getContextPath();
		String chartPath;
				
		String chartId = localBodyName.concat("_LocalBodyElection").concat("_For_").concat(electionYear);
		//String chartName = localBodyType+ chartId + session.getId()+".png";
		String chartName = localBodyType+ localBodyId +".png";
		if(cPath.contains("PartyAnalyst"))
		   chartPath = context.getRealPath("/") + "charts\\" + chartName;
		else
		   chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
		
		String title = "All Parties Performance In ".concat(localBodyName).concat(" ").concat(localBodyType).concat(" In ").concat(electionYear);
		ChartProducer.createLineChart(title, "", "", createDataSetForChart(partyResultsVO,localBodyName,electionYear,localBodyType), chartPath,280,650, null,true );
		
		return chartName;
	}
	
	private CategoryDataset createDataSetForChart(List<TeshilPartyInfoVO> partyResultsVO,String localBodyName,String electionYear,String localBodyType){
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "1st Position";
		String series2 = "2nd Position";
		String series3 = "3rd Position";
		//String series4 = "Nth Position";
		
		if(partyResultsVO != null && partyResultsVO.size() > 0){
			for(TeshilPartyInfoVO resultsList:partyResultsVO){
				dataset.addValue(resultsList.getPartyWonSeats(), resultsList.getPartyName(), series1);
				dataset.addValue(resultsList.getPartySecndPos(), resultsList.getPartyName(), series2);
				dataset.addValue(resultsList.getPartyThirdPos(), resultsList.getPartyName(), series3);
				//dataset.addValue(resultsList.getPartyNthPos(), resultsList.getPartyName(), series4);
			}
		}
	 return dataset;
	}
	
	public String getLocalElectionBodyOverallResults(){
		String param = null;
		param = getTask();
		String cPath = request.getContextPath(); 
		
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		}catch (ParseException e) {
			e.printStackTrace();
			log.error("Exception Raised :" + e);
		}
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long localBodyElectionId = jObj.getLong("localBodyElectionId");
		if(localBodyElectionId == null || localBodyElectionId.longValue() == 0){
			teshilPartyInfoVO = new TeshilPartyInfoVO();
			return SUCCESS;
		}
		teshilPartyInfoVO = localBodyElectionService.getMuncipalOrCorporationElectionsResultsForAnAssembly(localBodyElectionId, constituencyId);
		String title = "";
		String pieChartName = "";
		String pieChartPath = "";
		if(teshilPartyInfoVO.getMuncipalityVO() != null)
		for(TeshilPartyInfoVO lebParty:teshilPartyInfoVO.getMuncipalityVO()){
			title = "All Parties Performance In "+lebParty.getMuncipalityName()+" In "+lebParty.getLatestMuncipalElectionYear();
			pieChartName = "lebInfo_"+lebParty.getMuncipalityName()+"_"+lebParty.getLatestMuncipalElectionYear()+".png";
			
			if(cPath.contains("PartyAnalyst"))
				pieChartPath = context.getRealPath("/")+ "charts\\" + pieChartName;
			else
				pieChartPath = IWebConstants.CHART_URL_IN_SERVER + pieChartName;
			
			if(lebParty.getMuncipalityVO().size() > 0)
				ChartProducer.createProblemsPieChart(title, createPieDatasetForVoters(lebParty.getMuncipalityVO()), pieChartPath, 
						null,true,260,270);
			lebParty.setChartName(pieChartName);
		}
		
		return SUCCESS;
	}
	
	public String getGreaterElectionsOverallResults(){
		String param = null;
		param = getTask();
		String cPath = request.getContextPath(); 
		String chartPath;
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		}catch (ParseException e) {
			e.printStackTrace();
			log.error("Exception Raised :" + e);
		}
		Set<String> partiesInChart = null;
		Long constituencyId = jObj.getLong("constituencyId");
		Long localBodyElectionId = jObj.getLong("localBodyElectionId");
		greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(localBodyElectionId, constituencyId,0l,0l);
		if(greaterInfo.getLocalElectionsInfo() != null){
			for(MandalVO electionInfoVO:greaterInfo.getLocalElectionsInfo()){
				String lebChartName = "localElectionBodiesChart"+electionInfoVO.getName()+"_"+electionInfoVO.getId()+".png";
				
				if(cPath.contains("PartyAnalyst"))
					 chartPath = context.getRealPath("/")+ "charts\\" + lebChartName;
				else
					 chartPath = IWebConstants.CHART_URL_IN_SERVER + lebChartName;
				
		        partiesInChart = new LinkedHashSet<String>();
		   		if(electionInfoVO.getWardwiseResultsForParty().size() > 0)
		   			ChartProducer.createLineChart("All Parties Performance In GHMC Elections Of "+electionInfoVO.getElectionYear(), "Wards", "Percentages", 
		   				createDatasetForLEB(electionInfoVO, partiesInChart), chartPath,350,700, ChartUtils.getLineChartColors(partiesInChart),true );
		   		electionInfoVO.setChartName(lebChartName);
			}
		}
		return SUCCESS;
	}
	
	
	private CategoryDataset createDatasetForLEB(MandalVO electionInfoVO,
			Set<String> partiesInChart) {
		 final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 for(PartyElectionResultsVO ward:electionInfoVO.getWardwiseResultsForParty()){
			 partiesInChart.add(ward.getPartyName());
			 dataset.addValue(new BigDecimal(ward.getVotesPercentage()), ward.getPartyName(),
					 ward.getConstituencyName());
		 }
		 return dataset;
	}
	
	private DefaultPieDataset createPieDatasetForVoters(List<TeshilPartyInfoVO> wards) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(TeshilPartyInfoVO ward:wards)
			dataset.setValue(ward.getPartyName(), new BigDecimal(ward.getPercentageOfVotesWonByParty()));
			
		return dataset;
	}
	
}
