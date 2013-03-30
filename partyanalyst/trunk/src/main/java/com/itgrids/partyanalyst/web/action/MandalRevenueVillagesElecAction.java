package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.TownshipBoothDetailsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
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

	public String execute(){
		mandalVO = staticDataService.findListOfElectionsAndPartiesInMandal(new Long(tehsilId));
		String cPath = request.getContextPath();
		String chartLocation="";
		if(parties != null){
			List<PartyResultVO> partiesResults = null;
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
	

}
