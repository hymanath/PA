package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class RevenueVillagePartyAllElecAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private HttpServletRequest request;
	private ServletContext context;
	private IConstituencyPageService constituencyPageService;
	private ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO;
	private String partyId;
	private String partyName;
	private String mandalName; 
	private String tehsilId;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	
	public ConstituencyRevenueVillagesVO getConstituencyRevenueVillagesVO() {
		return constituencyRevenueVillagesVO;
	}

	public void setConstituencyRevenueVillagesVO(
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO) {
		this.constituencyRevenueVillagesVO = constituencyRevenueVillagesVO;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String execute() throws Exception{
		String cPath = request.getContextPath();
		constituencyRevenueVillagesVO = constituencyPageService.
		getRevenuevillagesWiseElectionResultsOfPartyInMandal(Long.valueOf(partyId), Long.valueOf(tehsilId));
		
		String chartName = "partyPerformanceInOneMandalInAllElectionsByRevenueVillages_partyId"+partyId+"_terhsilId_"+tehsilId+".png";
		String chartPath = "";
		
		if(cPath.contains("PartyAnalyst"))
			chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		else 
			chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
        if(constituencyRevenueVillagesVO.getElectionInfoByLocations().size() > 0)
        	ChartProducer.createLineChart("" , "Revenue Villages", "Percentages", createDataset(constituencyRevenueVillagesVO), chartPath,300,880, null,false);
        constituencyRevenueVillagesVO.setChartPath(chartName);
        return SUCCESS;
		
	}

	private CategoryDataset createDataset(ConstituencyRevenueVillagesVO constituencyObj) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(ElectionWiseMandalPartyResultVO villageInfoVO:constituencyObj.getElectionInfoByLocations())
    		for(PartyElectionResultVO partyInfo:villageInfoVO.getPartyResultsInElection())
    			if(partyInfo.getConstiId() != 0)
    				dataset.addValue(new BigDecimal(partyInfo.getVotesPercentage()), villageInfoVO.getElectionYear()+" "+villageInfoVO.getElectionType(), partyInfo.getTownshipName());	
        return dataset;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
