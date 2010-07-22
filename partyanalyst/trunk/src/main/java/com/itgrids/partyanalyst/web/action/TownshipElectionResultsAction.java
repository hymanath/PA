package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
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
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyVillageLevelAnalysisVO;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TownshipElectionResultsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(TownshipElectionResultsAction.class);
	private List<ConstituencyRevenueVillagesVO> townshipWiseElectionResults;
	private IConstituencyPageService constituencyPageService;
	private String electionType;
	private String electionYear;
	private String mandalName;
	private ServletContext context;
	private String windowTask;
	private Long tehsilId;
	private Long electId;
	private List<SelectOptionVO> allElectionYears;
	private IStaticDataService staticDataService;
	private String task;
	JSONObject jObj;
	private IBiElectionPageService biElectionPageService; 
	private List<PartyVillageLevelAnalysisVO> partyVillageLevelAnalysisVO;
	
	public List<PartyVillageLevelAnalysisVO> getPartyVillageLevelAnalysisVO() {
		return partyVillageLevelAnalysisVO;
	}

	public void setPartyVillageLevelAnalysisVO(
			List<PartyVillageLevelAnalysisVO> partyVillageLevelAnalysisVO) {
		this.partyVillageLevelAnalysisVO = partyVillageLevelAnalysisVO;
	}

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getAllElectionYears() {
		return allElectionYears;
	}

	public void setAllElectionYears(List<SelectOptionVO> allElectionYears) {
		this.allElectionYears = allElectionYears;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public List<ConstituencyRevenueVillagesVO> getTownshipWiseElectionResults() {
		return townshipWiseElectionResults;
	}

	public void setTownshipWiseElectionResults(
			List<ConstituencyRevenueVillagesVO> townshipWiseElectionResults) {
		this.townshipWiseElectionResults = townshipWiseElectionResults;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public String getWindowTask() {
		return windowTask;
	}
	

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public Long getTehsilId() {
		return tehsilId;
	}


	public void setElectId(Long electId) {
		this.electId = electId;
	}

	public Long getElectId() {
		return electId;
	}

	public String execute() {
		
		Long mandalId = new Long(request.getParameter("mandalId"));
		Long electionId = new Long(request.getParameter("electionId"));
		electionType = request.getParameter("electionType");
		electionYear = request.getParameter("electionYear");
		mandalName = request.getParameter("mandalName");
		tehsilId = mandalId;
		electId = electionId;
		log.debug("Result::mandalId="+mandalId+" electionId="+electionId);		
		townshipWiseElectionResults = constituencyPageService.getPartiesResultsInVillagesGroupByMandal(mandalId, electionId);
		
		allElectionYears = staticDataService.getAllElectionYearsBasedOnElectionType(electionType);
		 
		for(ConstituencyRevenueVillagesVO constituencyObj:townshipWiseElectionResults){
			String chartName = "partyPerformanceInAllMandalElectionsByRevenueVillages_"+constituencyObj.getConstituencyId()+".png";
	        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
	        Set<String> partiesInChart = new LinkedHashSet<String>();
	        ChartProducer.createLineChartWithThickness("All Parties Performance In "+electionType+" "+electionYear + 
	        		" In "+constituencyObj.getConstituencyName()+" Constituency By Revenue Villages In "+mandalName+" Mandal" , 
	        		"Revenue Villages", "Percentages", createDataset(constituencyObj, partiesInChart), chartPath,600,1000, ChartUtils.getLineChartColors(partiesInChart),true);	
	        constituencyObj.setChartPath(chartName);
		}
		
		return SUCCESS;
	}
	
	private CategoryDataset createDataset(ConstituencyRevenueVillagesVO constituencyObj, Set<String> partiesInChart) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<PartyElectionResultVO> pariesInfo = null;
        List<CandidatePartyInfoVO> candidatesInfo = constituencyObj.getCandidateNamePartyAndStatus();
    	for(RevenueVillageElectionVO villageInfoVO:constituencyObj.getRevenueVillageElectionVO()){
    		pariesInfo = villageInfoVO.getPartyElectionResultVOs();
    		for(int i=0; i<pariesInfo.size(); i++){
    			partiesInChart.add(candidatesInfo.get(i).getParty());
    			if(candidatesInfo.get(i).getParty().equalsIgnoreCase(IConstants.INDIPENDENT))
    				dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty()+" ["+candidatesInfo.get(i).getRank()+"]" , villageInfoVO.getTownshipName());
    			else
    				dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty(), villageInfoVO.getTownshipName());
    		}        		
        }
        return dataset;
    }
	
	public String getTownShipWisePartiesVotesShare()
	{		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String electionType = jObj.getString("electionType");
		String mandalId = jObj.getString("mandalId");
		String rank = jObj.getString("rank");
		String electionYear = jObj.getString("electionYear");
		
		partyVillageLevelAnalysisVO = biElectionPageService.villageLevelPArtyAnalysis(new Long(mandalId), electionType, electionYear, Integer.parseInt(rank));
		
		return Action.SUCCESS;
	}
	
	
}
