package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hsqldb.lib.HashSet;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.TownshipBoothDetailsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MandalRevenueVillagesElecAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IStaticDataService staticDataService;
	private IBiElectionPageService biElectionPageService;
	private HttpServletRequest request;
	private ServletContext context;
	private String parties;
	private String includeAlliance;
	private String elections;
	private MandalVO mandalVO;
	private String tehsilId;
	private String tehsilName;
	private String chartPath;
	private String resultType;
	private List<TownshipBoothDetailsVO> townshipBoothDetailsVO;
	private IConstituencyPageService constituencyPageService;
	private List<PartyResultVO> partiesResults;
	private Map<String,List<PartyResultVO>> partyResultMap;
	private Map<String,List<String>> partyResultMapPrcnt;
	JSONObject jObj = null;
	private Long constituencyId;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private String task = null;
	private HttpSession session;
	public Map<String, List<String>> getPartyResultMapPrcnt() {
		return partyResultMapPrcnt;
	}

	public void setPartyResultMapPrcnt(Map<String, List<String>> partyResultMapPrcnt) {
		this.partyResultMapPrcnt = partyResultMapPrcnt;
	}

	public Map<String, List<PartyResultVO>> getPartyResultMap() {
		return partyResultMap;
	}

	public void setPartyResultMap(Map<String, List<PartyResultVO>> partyResultMap) {
		this.partyResultMap = partyResultMap;
	}

	public List<PartyResultVO> getPartiesResults() {
		return partiesResults;
	}

	public void setPartiesResults(List<PartyResultVO> partiesResults) {
		this.partiesResults = partiesResults;
	}

	public List<TownshipBoothDetailsVO> getTownshipBoothDetailsVO() {
		return townshipBoothDetailsVO;
	}

	public void setTownshipBoothDetailsVO(
			List<TownshipBoothDetailsVO> townshipBoothDetailsVO) {
		this.townshipBoothDetailsVO = townshipBoothDetailsVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public MandalVO getMandalVO() {
		return mandalVO;
	}

	public void setMandalVO(MandalVO mandalVO) {
		this.mandalVO = mandalVO;
	}
	
	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getParties() {
		return parties;
	}

	public void setParties(String parties) {
		this.parties = parties;
	}

	public String getElections() {
		return elections;
	}

	public void setElections(String elections) {
		this.elections = elections;
	}

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getIncludeAlliance() {
		return includeAlliance;
	}

	public void setIncludeAlliance(String includeAlliance) {
		this.includeAlliance = includeAlliance;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	
	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute(){
		mandalVO = staticDataService.findListOfElectionsAndPartiesInMandal(new Long(tehsilId));
		String cPath = request.getContextPath();
		String chartLocation="";
		tehsilName = staticDataService.getTehsilNameByTehsilId(new Long(tehsilId));
		if(parties != null){
			//List<PartyResultVO> partiesResults = null;
			if("panchayat".equalsIgnoreCase(resultType)){
			 partiesResults = constituencyPageService.
					findPanchayatsWiseResultsInElectionsOfMandal(new Long(tehsilId), parties, elections, new Boolean(includeAlliance));
			 chartPath = "allParties_"+parties+"_AllElections_"+elections+"VillagesWisePerformanceInAllElections_"+tehsilId+"P.png";
			}else{
			 partiesResults = biElectionPageService.
					findRevenueVillageswiseResultsInElectionsOfMandal(new Long(tehsilId), parties, elections, new Boolean(includeAlliance));
			 chartPath = "allParties_"+parties+"_AllElections_"+elections+"VillagesWisePerformanceInAllElections_"+tehsilId+"RV.png";
			}		
			request.setAttribute("checkedType",resultType);
			if(cPath.contains("PartyAnalyst"))
	            chartLocation = context.getRealPath("/")+ "charts\\" + chartPath;
			else
			   chartLocation = IWebConstants.CHART_URL_IN_SERVER + chartPath;
	        //Set<String> partiesInChart = new LinkedHashSet<String>();
	        if(partiesResults.size() > 0)
	        	ChartProducer.createLineChart("", "Elections", "Percentages", createDataset(partiesResults), chartLocation,500,900, null,true );

	       partyResultMap=new HashMap<String, List<PartyResultVO>>();
	        
	        for(PartyResultVO prtyrslts:partiesResults){
	        	String partyName=prtyrslts.getPartyName();
	        	List<PartyResultVO> volist=new ArrayList<PartyResultVO>();
	        	
	        	if(partyResultMap.containsKey(partyName)){
	        		partyResultMap.get(partyName).add(prtyrslts);
	        	}
	        	else{
	        		volist.add(prtyrslts);
	        		partyResultMap.put(partyName, volist);	
	        	}
	        }
	        
	        partyResultMapPrcnt=new HashMap<String,List<String>>();
	        
	        for (Entry<String, List<PartyResultVO>> entry : partyResultMap.entrySet()) {
	        	String key = entry.getKey();
	        	List<String> percentage=new ArrayList<String>();
	        	List<PartyResultVO> value = entry.getValue();
	            for(PartyResultVO aString : value){
	                String prcnt=aString.getVotesPercent();
	                percentage.add(prcnt);
	            }
	            partyResultMapPrcnt.put(key, percentage);
	        }
	        
	        
	       /* Map<String,List<Map<String,String>>> constituencyMap=new HashMap<String, List<Map<String,String>>>();
	                
	        
	        for(PartyResultVO prvo:partiesResults){
	        	String cnstncyName=prvo.getConstituencyName();
	        	String prtyName=prvo.getPartyName();
	        	String vtngPercentage=prvo.getVotesPercent();
	        	HashMap<String,String> prtyVoting=new HashMap<String, String>();
	        	prtyVoting.put(prtyName, vtngPercentage);
	        	
	        	if(constituencyMap.containsKey(cnstncyName)){
	        		List<Map<String,String>>  lst=constituencyMap.get(cnstncyName);
	        		//prtyVoting.put(prtyName, vtngPercentage);
	        		lst.add(prtyVoting);
	        	
	        	}
	        	else{
	        		List<Map<String,String>> prtyVotingNotExist=new ArrayList<Map<String,String>>();
	        		prtyVotingNotExist.add(prtyVoting);
	        		constituencyMap.put(cnstncyName, prtyVotingNotExist);
	        	}
	        }*/
	        
	        
		}
		
		if(elections!=null){
			StringTokenizer st = new StringTokenizer(elections," ",false);
			String electionsIDS="";
			while (st.hasMoreElements()) electionsIDS += st.nextElement();
			  
			createPieChartsForTownshipVotingTrends(new Long(tehsilId),electionsIDS);	
		}
		return SUCCESS;
	}
	
	private void createPieChartsForTownshipVotingTrends(Long tehsilId,String electionIds){
		String cPath = request.getContextPath();
		String chartPath="";
		String elecId = staticDataService.getLatestAssemblyElectionId();
		if("panchayat".equalsIgnoreCase(resultType)){
			townshipBoothDetailsVO = staticDataService.getPanchayatVotingTrendsByMandal(tehsilId,elecId,constituencyPageService.getPartiesResultsInPanchayatsGroupByMandal(tehsilId,new Long(elecId.trim())).get(0).getRevenueVillageElectionVO());	
		}else{			
			townshipBoothDetailsVO = staticDataService.getRevenueVillageVotingTrendsByMandalAndElectionIds(tehsilId,elecId);	
		}
		for(int i=0;i<townshipBoothDetailsVO.size();i++){
			String chartName = townshipBoothDetailsVO.get(i).getChartName();
			if("panchayat".equalsIgnoreCase(resultType)){
				chartName = "P"+chartName;
				townshipBoothDetailsVO.get(i).setChartName(chartName);
			}else{
				chartName = "RV"+chartName;
				townshipBoothDetailsVO.get(i).setChartName(chartName);
			}
			String chartTitle = townshipBoothDetailsVO.get(i).getChartTitle();
			if(cPath.contains("PartyAnalyst"))
			    chartPath = context.getRealPath("/") + "charts\\" + chartName;
			else
			    chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
			if(townshipBoothDetailsVO.get(0).getTownshipVotingTrends().size()<=12){
				ChartProducer.createProblemsPieChart(chartTitle, createPieDatasetForVoters(townshipBoothDetailsVO,i), chartPath , null,true,300,280);	
			}else{
				ChartProducer.createProblemsPieChart(chartTitle, createPieDatasetForVoters(townshipBoothDetailsVO,i), chartPath , null,true,300,450);	
			}			
		}	
	}
	
	private DefaultPieDataset createPieDatasetForVoters(final List<TownshipBoothDetailsVO> townshipBoothDetailsVO,int k) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		BigDecimal percentage;
		
		for(int i=k;i<=k;i++){
			for(int j=0;j<townshipBoothDetailsVO.get(i).getTownshipVotingTrends().size();j++){
				percentage = new BigDecimal(townshipBoothDetailsVO.get(i).getTownshipVotingTrends().get(j).getPercentageOfValidVotes()).setScale(2,BigDecimal.ROUND_HALF_UP);
				dataset.setValue(townshipBoothDetailsVO.get(i).getTownshipVotingTrends().get(j).getTownshipName()+" ["+percentage.toString()+"%]",percentage);	
			}			
		}			
		return dataset;
	}
	
	private CategoryDataset createDataset(List<PartyResultVO> partiesResults) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PartyResultVO partyInfo:partiesResults)
			dataset.addValue(new BigDecimal(partyInfo.getVotesPercent()), partyInfo.getPartyName(), partyInfo.getConstituencyName());
		return dataset;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String getConstituencyByMandalId()
	{
		session = request.getSession();
		String param = null;
		param = getTask();
		try {
			LOG.debug("Entered into getConstituencyByMandalId() method in MandalRevenueVillagesElecAction Action");
			jObj = new JSONObject(param);
			if(jObj.getString("task").equalsIgnoreCase("getConstituencyId"))
			{
				Long maldalId = jObj.getLong("id");
				constituencyId = delimitationConstituencyMandalService.getConstituencyIdBasedOnMandel(maldalId);
			}
		} catch (Exception e) {
			LOG.error("exception raised in getConstituencyByMandalId() method in MandalRevenueVillagesElecAction Action",e);
		}
		return Action.SUCCESS;
	}
	
}
