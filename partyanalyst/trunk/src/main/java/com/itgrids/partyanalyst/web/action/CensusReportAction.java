package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CensusReportAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	private HttpServletRequest request;
	private List<SelectOptionVO> states;
	private IStaticDataService staticDataService; 
	private List<String> years;
	JSONObject jObj = null;
	private String task;
	private IElectionService electionService;
	private List<CensusVO> censusRange;
	private List<ConstituencyElectionResultsVO> constituencyElectionResults;
	String chartName = null;
	private ServletContext context;
	
	private static final Logger log = Logger.getLogger(BiElectionAction.class);
	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults() {
		return constituencyElectionResults;
	}

	public void setConstituencyElectionResults(
			List<ConstituencyElectionResultsVO> constituencyElectionResults) {
		this.constituencyElectionResults = constituencyElectionResults;
	}

	public List<CensusVO> getCensusRange() {
		return censusRange;
	}

	public void setCensusRange(List<CensusVO> censusRange) {
		this.censusRange = censusRange;
	}

	public IElectionService getElectionService() {
		return electionService;
	}

	public void setElectionService(IElectionService electionService) {
		this.electionService = electionService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStates() {
		return states;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String execute()
	{
		states = new ArrayList<SelectOptionVO>();		
		states.add(new SelectOptionVO(1L,"Andhra Pradesh"));
		
		setYears(getStaticDataService().getElectionYears(2L, false));
		
		return Action.SUCCESS;
	}
	
	public String getCensusInfoInAState()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer censusId = jObj.getInt("censusValue");
		
		censusRange = electionService.getConstituencyCensusDetails(censusId);
		
		return Action.SUCCESS;
	}
	
	public String getPartiesPerformanceInCensusReport()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Long> locationIds = new ArrayList<Long>();
		
		StringBuilder sb = new StringBuilder();
		String year = jObj.getString("yearValue");
		JSONArray jArray = jObj.getJSONArray("idsList");
		
		for (int i = 0; i < jArray.length(); i++) {
			locationIds.add(new Long(jArray.get(i).toString()));
			sb.append(jArray.get(i)).append(",");
		}
		
		ElectionDataVO electionDataVO = electionService.findAssemblyConstituenciesResultsByConstituencyIds(year, locationIds);
		constituencyElectionResults = electionDataVO.getConstituenciesResults();
		
		//chartName = createResultsLineChart(constituencyElectionResults, sb, year);
		return Action.SUCCESS;	
	
	}
	
	public String createResultsLineChart(List<ConstituencyElectionResultsVO> asseblyDetails,StringBuilder sb, String year)
	{
		String chartName = null;
		try{
		
 		String lineChartName = "bielections in _" +sb+"_forYear_"+year+".png";
        String chartPath = context.getRealPath("/") + "charts\\" + lineChartName;
        String title = "";
        Set<String> partiesInChart = new LinkedHashSet<String>();
		ChartProducer.createLineChartWithThickness(title,"Constituencies","Votes Percentage", createDataSetForGraph(asseblyDetails, partiesInChart),chartPath,600,920, ChartUtils.getLineChartColors(partiesInChart),true);
		chartName = lineChartName;
	
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
		
	}
	
	private CategoryDataset createDataSetForGraph(List<ConstituencyElectionResultsVO> asseblyDetails, Set<String> partiesInChart){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(ConstituencyElectionResultsVO consti:asseblyDetails)
			for(PartyResultsVO category:consti.getPartyResultsVO())
			{
				partiesInChart.add(category.getPartyName());
				dataset.addValue(new BigDecimal(category.getPercentage()),category.getPartyName(),consti.getConstituencyName());
			}
		return dataset;
	}

	
}
