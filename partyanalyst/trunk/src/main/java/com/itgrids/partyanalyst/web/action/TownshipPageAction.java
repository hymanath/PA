package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
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
		for(PartyResultVO partyResultVO:acPcElectionResultsForParties){
			String chartName = "partyPerformanceInAllRVElections_"+townshipId+"_"+partyResultVO.getPartyId()+".png";
	        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
	    	ChartProducer.createBarChart(partyResultVO.getPartyName()+" Performance In Diff Elections Of "+townshipName+" Revenue Village", "Election Years", "Percentages", createDataset(partyResultVO), chartPath);
		}
		
		return SUCCESS;
	}

	private CategoryDataset createDataset(PartyResultVO partyResultVO) {
        final String series1 = IConstants.ASSEMBLY_ELECTION_TYPE;
        final String series2 = IConstants.PARLIAMENT_ELECTION_TYPE;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
        	if(result.getElectionType().equals(series1))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series1, result.getElectionYear());
        	else if(result.getElectionType().equals(series2))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series2, result.getElectionYear());
        }
        return dataset;
    }
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
