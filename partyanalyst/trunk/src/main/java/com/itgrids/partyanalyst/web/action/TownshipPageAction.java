package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.opensymphony.xwork2.ActionSupport;

public class TownshipPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private HttpServletRequest request;
	private ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private String townshipName;
	private ServletContext context;
	private Long townshipId;
	private Set<SelectOptionVO> partiesInTownship;
	private IStaticDataService staticDataService;
	private static final Logger log = Logger.getLogger(RevenueVillageReportAction.class);
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public Set<SelectOptionVO> getPartiesInTownship() {
		return partiesInTownship;
	}

	public void setPartiesInTownship(Set<SelectOptionVO> partiesInTownship) {
		this.partiesInTownship = partiesInTownship;
	}

	public Long getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public ElectionWiseMandalPartyResultListVO getElectionWiseMandalPartyResultListVO() {
		return electionWiseMandalPartyResultListVO;
	}

	public void setElectionWiseMandalPartyResultListVO(
			ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO) {
		this.electionWiseMandalPartyResultListVO = electionWiseMandalPartyResultListVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{
		townshipId = new Long(request.getParameter("TOWNSHIP_ID"));
		townshipName = request.getParameter("TOWNSHIP_NAME");
		electionWiseMandalPartyResultListVO = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(townshipId, "Township");
		List<PartyResultVO> acPcElectionResultsForParties = electionWiseMandalPartyResultListVO.getAllPartiesAllElectionResults();
		
		partiesInTownship = staticDataService.getAllPartiesParticipatedInRevenueVillage(townshipId);
		log.debug("Total Parties In Revenue Village::"+partiesInTownship.size());
		String chartName = "allPartiesPerformanceInAllRVElections_"+townshipId+"_"+townshipName+".png";
        //String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
        String chartPath = chartProducerURL+ chartName;
    	ChartProducer.createLineChart("All Parties Performance In Diff Elections Of "+townshipName+" Revenue Village", "Elections", "Percentages", createDataset(acPcElectionResultsForParties), chartPath,260,600, null,false);
		
		return SUCCESS;
	}

	private CategoryDataset createDataset(List<PartyResultVO> allElectionResults) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ElectionResultVO> partiesElectionResults = new ArrayList<ElectionResultVO>();
        ElectionResultVO partiesElecResultForGraph = null;
        
        for(PartyResultVO partyResultVO:allElectionResults)
        	for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
        		partiesElecResultForGraph = new ElectionResultVO();
        		partiesElecResultForGraph.setPercentage(result.getPercentage());
        		partiesElecResultForGraph.setElectionYear(result.getElectionYear()+" "+result.getElectionType());
        		partiesElecResultForGraph.setPartyName(partyResultVO.getPartyName());
        		partiesElectionResults.add(partiesElecResultForGraph);
        	}
        
        Collections.sort(partiesElectionResults, new ElectionResultComparator());
        
        for(ElectionResultVO graphInfo:partiesElectionResults)
           	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyName(),
           			graphInfo.getElectionYear());
        return dataset;
    }
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
