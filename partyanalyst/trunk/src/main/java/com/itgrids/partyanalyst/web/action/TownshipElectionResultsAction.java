package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class TownshipElectionResultsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

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
		
		for(ConstituencyRevenueVillagesVO constituencyObj:townshipWiseElectionResults){
			String chartName = "partyPerformanceInAllMandalElectionsByRevenueVillages_"+constituencyObj.getConstituencyId()+".png";
	        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
	        ChartProducer.createLineChart("All Parties Performance In "+electionType+" "+electionYear + 
	        		" In "+constituencyObj.getConstituencyName()+" Constituency By Revenue Villages In "+mandalName+" Mandal" , 
	        		"Revenue Villages", "Percentages", createDataset(constituencyObj), chartPath,260,700, null);	
	        constituencyObj.setChartPath(chartName);
		}
		
		return SUCCESS;
	}
	
	private CategoryDataset createDataset(ConstituencyRevenueVillagesVO constituencyObj) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<PartyElectionResultVO> pariesInfo = null;
        List<CandidatePartyInfoVO> candidatesInfo = constituencyObj.getCandidateNamePartyAndStatus();
    	for(RevenueVillageElectionVO villageInfoVO:constituencyObj.getRevenueVillageElectionVO()){
    		pariesInfo = villageInfoVO.getPartyElectionResultVOs();
    		for(int i=0; i<pariesInfo.size(); i++){
    			if(candidatesInfo.get(i).getParty().equalsIgnoreCase(IConstants.INDIPENDENT))
    				dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty()+" ["+candidatesInfo.get(i).getRank()+"]" , villageInfoVO.getTownshipName());
    			else
    				dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty(), villageInfoVO.getTownshipName());
    		}        		
        }
        return dataset;
    }

	
	
}
