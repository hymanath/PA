package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalRevenueVillagesElecAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private IStaticDataService staticDataService;
	private IBiElectionPageService biElectionPageService;
	private HttpServletRequest request;
	private ServletContext context;
	private String parties;
	private String elections;
	private MandalVO mandalVO;
	private String tehsilId;
	private String chartPath; 
	
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

	public String execute(){
		mandalVO = staticDataService.findListOfElectionsAndPartiesInMandal(new Long(tehsilId));
		if(parties != null){
			List<PartyResultVO> partiesResults = biElectionPageService.
			findRevenueVillageswiseResultsInElectionsOfMandal(new Long(tehsilId), parties, elections, null);
			
			chartPath = "allParties_"+parties+"_AllElections_"+elections+"VillagesWisePerformanceInAllElections_"+tehsilId+".png";
	        String chartLocation = context.getRealPath("/")+ "charts\\" + chartPath;
	        if(partiesResults.size() > 0)
	        	ChartProducer.createLineChart("", "Elections", "Percentages", createDataset(partiesResults), chartLocation,300,900, null );
		}
		return SUCCESS;
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
