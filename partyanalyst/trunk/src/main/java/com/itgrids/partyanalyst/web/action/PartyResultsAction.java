package com.itgrids.partyanalyst.web.action;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.components.ActionError;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.impl.PartyResultService;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Mohan
 *
 */
public class PartyResultsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String countrySelectName;
	private String stateSelectName;
	private String districtSelectName;
	private String constituencySelectName;
	private String partySelectName;
	private String reportLevel;
	private String selectedLocationName;
	private String electionType;
	private PartyResultService partyResultService;
	private List<PartyResultInfoVO> partyResultInfoVOs;
	private String selectedElectionTypeName;
	private ServletContext context;
	private String selectedPartyShortName;
	private Long selectedPartyId;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	
	private static final org.apache.log4j.Logger log = Logger.getLogger(PartyResultsAction.class);
	
	public String getSelectedLocationName() {
		return selectedLocationName;
	}

	public void setSelectedLocationName(String selectedLocationName) {
		this.selectedLocationName = selectedLocationName;
	}

	private enum ChartType {yearVsSeats, yearVsVotesPerc };
	
	public List<PartyResultInfoVO> getPartyResultInfoVOs() {
		return partyResultInfoVOs;
	}

	public void setPartyResultInfoVOs(List<PartyResultInfoVO> partyResultInfoVOs) {
		this.partyResultInfoVOs = partyResultInfoVOs;
	}

	
	public String getSelectedPartyShortName() {
		return selectedPartyShortName;
	}

	public void setSelectedPartyShortName(String selectedPartyShortName) {
		this.selectedPartyShortName = selectedPartyShortName;
	}

	public String getSelectedElectionTypeName() {
		return selectedElectionTypeName;
	}

	public void setSelectedElectionTypeName(String selectedElectionTypeName) {
		this.selectedElectionTypeName = selectedElectionTypeName;
	}

	public String getReportLevel() {
		return reportLevel;
	}
 
	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public String getPartySelectName() {
		return partySelectName;
	}
	
	public void setPartySelectName(String partySelectName) {
		this.partySelectName = partySelectName;
	}
	public String getCountrySelectName() {
		return countrySelectName;
	}


	public void setCountrySelectName(String countrySelectName) {
		this.countrySelectName = countrySelectName;
	}


	public void setPartyResultService(PartyResultService partyResultService) {
		this.partyResultService = partyResultService;
	}
	
	public String getStateSelectName() {
		return stateSelectName;
	}


	public void setStateSelectName(String stateSelectName) {
		this.stateSelectName = stateSelectName;
	}

	public String getDistrictSelectName() {
		return districtSelectName;
	}

	public void setDistrictSelectName(String districtSelectName) {
		this.districtSelectName = districtSelectName;
	}

	public String getConstituencySelectName() {
		return constituencySelectName;
	}

	public void setConstituencySelectName(String constituencySelectName) {
		this.constituencySelectName = constituencySelectName;
	}	
	
	public Long getSelectedPartyId() {
		return selectedPartyId;
	}

	public void setSelectedPartyId(Long selectedPartyId) {
		this.selectedPartyId = selectedPartyId;
	}

	public String execute() {

		
		try{
			
			HttpSession session = request.getSession();
			
			if(session.getAttribute(IConstants.USER) == null)
				return IConstants.NOT_LOGGED_IN;
			
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String reportError = rb.getString("noResults");
		
		System.out.println("IN Party Results action ");
		setCountrySelectName("India");
		
		ElectionScopeLevelEnum level = ElectionScopeLevelEnum.CONSTITUENCY_LEVEL;

		if(partySelectName == null || electionType == null)
			return "dashBoard";
			
		Long partyId = new Long(partySelectName);
		Long electionId = new Long(electionType);
		Long countryId = new Long(1);

		Long stateId = null;		
		Long districtId = null;
		Long constituencyId = null;
		StringBuffer chartId = new StringBuffer(countryId.toString());
		chartId.append(partyId.toString());
		chartId.append(electionId.toString());
		//request.setAttribute("selectedPartyShortName", getSelectedPartyShortName());
		//request.setAttribute("selectedElectionTypeName", getSelectedElectionTypeName());
		
		if(reportLevel.equals("Country")){
			level = ElectionScopeLevelEnum.COUNTRY_LEVEL;
		}
		else if(reportLevel.equals("State")){
			level = ElectionScopeLevelEnum.STATE_LEVEL;
			stateId = new Long(getStateSelectName());
			chartId.append("S" + stateId.toString());
		}
		else if(reportLevel.equals("District")){
			level = ElectionScopeLevelEnum.DISTRICT_LEVEL;
			stateId = new Long(getStateSelectName());
			districtId = new Long(districtSelectName);
			chartId.append("D" + districtId.toString());
		}
		else if(reportLevel.equals("Constituency")){
			level = ElectionScopeLevelEnum.CONSTITUENCY_LEVEL;
			stateId = new Long(getStateSelectName());
			constituencyId = new Long(constituencySelectName);
			chartId.append("C" + constituencyId.toString());
		}
		
		Boolean alliances = new Boolean(request.getParameter("alliances"));
		System.out.println("Alliances :" + alliances);
		//Boolean hasAlliances = true;
		//List<PartyResultInfoVO> partyResultInfoVOList = partyResultService.getPartyResultsInfo(partySelectName, electionType, "1", stateSelectName, 
		//		districtSelectName, constituencySelectName, level);
		
		List<PartyResultInfoVO> partyResultInfoVOList = partyResultService.getPartyResultsInfo(selectedPartyShortName, selectedPartyId, electionId, countryId, stateId, 
				districtId,constituencyId ,level,alliances);
		//PartyResultInfoVO getPartyResultsInfo();
	
		setPartyResultInfoVOs(partyResultInfoVOList);
		
		if(partyResultInfoVOList.size()==0){
			//addFieldError("errormsg",getText("noResults"));
			
			addActionError(reportError);
			
              
			return "error";
		}
			
	
		session = request.getSession();
		String cPath = request.getContextPath();
		 String chartPath="";
		String chartName = "partyResultsChart_" + chartId.toString() + session.getId() + ".png";
		
		if(cPath.contains("PartyAnalyst"))
           chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		else
		   chartPath = chartProducerURL + chartName;
		
        request.setAttribute("chartName", chartName);
        List<CategoryDataset> dataset = new ArrayList<CategoryDataset>();
        
        dataset.add(createDataset(partyResultInfoVOList, ChartType.yearVsSeats));
        dataset.add(createDataset(partyResultInfoVOList, ChartType.yearVsVotesPerc));
        ChartProducer.createLineChart("Party Results - Year Vs Participated Seats & Year Vs Seats Won", dataset, "Year", "No. of Seats", chartPath);
		//ChartProducer.createLineChart("Party Results - Year Vs Participated Seats & Year Vs Seats Won", yearVsSeatsDataset, "Year", "No. of Seats", yearVsSeatsChart);
		//ChartProducer.createLineChart("Party Results - Year Vs Votes Percentage", yearVsVotesPercDataset, "Year", "Percentage of Votes", yearVsVotesPercChart);
        
		}
		catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			return "failure";
			
		}
		
	 return SUCCESS;
		
	}

	
	@SuppressWarnings("unchecked")
	private CategoryDataset createDataset(List<PartyResultInfoVO> partyResultInfoVOList, ChartType chartType) {
        
        // row keys...
        final String series1 = "Seats participated";
        final String series2 = "Seats Won";
        final String series3 =  "Percentage of Votes";
        
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     
        for(PartyResultInfoVO result: partyResultInfoVOList){
        	PartyInfoVO partyInfo = result.getPartyInfoVO();
        	if(chartType.equals(ChartType.yearVsSeats)) {
        		dataset.addValue(partyInfo.getSeatsParticipated(), series1, partyInfo.getElectionYear());
        		dataset.addValue(partyInfo.getSeatsWin(), series2, partyInfo.getElectionYear());
        	} else {
        		dataset.addValue(partyInfo.getPercentageOfVotes(), series3, partyInfo.getElectionYear());
        	}
        }
     
     
        return dataset;
        
    }
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
