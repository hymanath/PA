package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultsPercentageVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionComparisonReportAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */

	private String party;
	private String electionId1;
	private String electionId2;
	private String selectedPartyName;
	private String allianceCheck;
	private ComparedReportVO comparedResultsVO;
	private List<PartyPositionDisplayVO> partyPositionDisplayVO;
	private CandidateDetailsVO constiElecResults;
	JSONObject jObj = null;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private ElectionComparisonReportVO electionComparisonReportVO;
	private IElectionComparisonReportService electionComparisonReportService;
	private IPartyService partyService;
	private IStaticDataService staticDataService;
	private Boolean hasECRPage = new Boolean(false);
	private Long electionType,electionYears2,electionYears1,state;//variables comes from  electionComparisonReportPopUp
	public static final Logger logger = Logger.getLogger(ElectionComparisonReportAction.class);
	private EntitlementsHelper entitlementsHelper;
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Boolean getHasECRPage() {
		return hasECRPage;
	}

	public void setHasECRPage(Boolean hasECRPage) {
		this.hasECRPage = hasECRPage;
	}
	
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getElectionType() {
		return electionType;
	}

	public void setElectionType(Long electionType) {
		this.electionType = electionType;
	}

	public Long getElectionYears2() {
		return electionYears2;
	}

	public void setElectionYears2(Long electionYears2) {
		this.electionYears2 = electionYears2;
	}

	public Long getElectionYears1() {
		return electionYears1;
	}

	public void setElectionYears1(Long electionYears1) {
		this.electionYears1 = electionYears1;
	}

	public String getSelectedPartyName() {
		return selectedPartyName;
	}

	public void setSelectedPartyName(String selectedPartyName) {
		this.selectedPartyName = selectedPartyName;
	}

	public String getAllianceCheck() {
		return allianceCheck;
	}

	public void setAllianceCheck(String allianceCheck) {
		this.allianceCheck = allianceCheck;
	}

	private static final long serialVersionUID = 1L;
	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getElectionId1() {
		return electionId1;
	}

	public void setElectionId1(String electionId1) {
		this.electionId1 = electionId1;
	}

	public String getElectionId2() {
		return electionId2;
	}

	public void setElectionId2(String electionId2) {
		this.electionId2 = electionId2;
	}

	
	
	@JSON (serialize= false )
	public ComparedReportVO getComparedResultsVO() {
		return comparedResultsVO;
	}

	public void setComparedResultsVO(ComparedReportVO comparedResultsVO) {
		this.comparedResultsVO = comparedResultsVO;
	}

	public IPartyService getPartyService() {
		return partyService;
	}

	public void setPartyService(IPartyService partyService) {
		this.partyService = partyService;
	}

	@JSON (serialize= false )
	public ElectionComparisonReportVO getElectionComparisonReportVO() {
		return electionComparisonReportVO;
	}

	public void setElectionComparisonReportVO(
			ElectionComparisonReportVO electionComparisonReportVO) {
		this.electionComparisonReportVO = electionComparisonReportVO;
	}

	public IElectionComparisonReportService getElectionComparisonReportService() {
		return electionComparisonReportService;
	}

	public void setElectionComparisonReportService(
			IElectionComparisonReportService electionComparisonReportService) {
		this.electionComparisonReportService = electionComparisonReportService;
	}
	
	public List<PartyPositionDisplayVO> getPartyPositionDisplayVO() {
		return partyPositionDisplayVO;
	}

	public void setPartyPositionDisplayVO(
			List<PartyPositionDisplayVO> partyPositionDisplayVO) {
		this.partyPositionDisplayVO = partyPositionDisplayVO;
	}


	public CandidateDetailsVO getConstiElecResults() {
		return constiElecResults;
	}

	public void setConstiElecResults(CandidateDetailsVO constiElecResults) {
		this.constiElecResults = constiElecResults;
	}

	private ElectionsComparisonVO electionsComparisonVO = null;
	private List<ElectionComparisonResultVO> electionComparisonResultVO = null;
	private PartyResultsPercentageVO partyResultsPercentageForYear1;
	private PartyResultsPercentageVO partyResultsPercentageForYear2;
	public PartyResultsPercentageVO getPartyResultsPercentageForYear2() {
		return partyResultsPercentageForYear2;
	}

	public void setPartyResultsPercentageForYear2(
			PartyResultsPercentageVO partyResultsPercentageForYear2) {
		this.partyResultsPercentageForYear2 = partyResultsPercentageForYear2;
	}

	private IElectionsComparisonService electionsComparisonService;

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ElectionsComparisonVO getElectionsComparisonVO() {
		return electionsComparisonVO;
	}

	public void setElectionsComparisonVO(ElectionsComparisonVO electionsComparisonVO) {
		this.electionsComparisonVO = electionsComparisonVO;
	}

	public PartyResultsPercentageVO getPartyResultsPercentageForYear1() {
		return partyResultsPercentageForYear1;
	}

	public void setPartyResultsPercentageForYear1(
			PartyResultsPercentageVO partyResultsPercentageForYear1) {
		this.partyResultsPercentageForYear1 = partyResultsPercentageForYear1;
	}

	public IElectionsComparisonService getElectionsComparisonService() {
		return electionsComparisonService;
	}

	public void setElectionsComparisonService(IElectionsComparisonService electionsComparisonService) {
		this.electionsComparisonService = electionsComparisonService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public List<ElectionComparisonResultVO> getElectionComparisonResultVO() {
		return electionComparisonResultVO;
	}

	public void setElectionComparisonResultVO(
			List<ElectionComparisonResultVO> electionComparisonResultVO) {
		this.electionComparisonResultVO = electionComparisonResultVO;
	}

	public String execute()
	{
		
		try{
			
		Boolean hasAlliances = new Boolean(allianceCheck);
		if(logger.isDebugEnabled())
			logger.debug("alliance-->" + allianceCheck);	
		
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null)
			return "showMessage";
		
		if(session != null)
			hasECRPage = entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ECR_DETAILED_ANALYSIS);
				
		if(electionId1==null ||  electionId2==null){
			List result = staticDataService.getListOfElectionIdsForGivenElectionTypeIdAndListOfElectionYears(electionType,electionYears2,electionYears1,state,IConstants.ELECTION_SUBTYPE_MAIN);
			if(result.size() == 0)
				return "dashBoard";
			for(int i=0;i<result.size();i++){
				if(i==0){
					electionId1	= result.get(i).toString();
				}else if(i==1){
					electionId2	= result.get(i).toString();
				}
			}
		}
		electionComparisonReportVO = electionComparisonReportService.getDistrictWiseElectionResultsForAParty(Long.parseLong(getParty()), new Long(electionId1), new Long(electionId2), hasAlliances);
		
		String yearOne = electionComparisonReportVO.getYearOne();
		String yearTwo = electionComparisonReportVO.getYearTwo();
		String electionType = electionComparisonReportVO.getElectionType();
		
        
        	session = request.getSession();
        	
        	String sPath = (String)session.getAttribute("chartPath");
        	
        	logger.warn("Path To Save Charts :" + sPath);
        	
        	String seatsLineChartPath = "";
        	String totalPercentLineChartPath = "";
    		String chartId = party.concat(electionType).concat(yearOne).concat("Comparing_with").concat(yearTwo).concat("_BarChart");
    		String barChartName = "electionsComparisonChart_" + chartId + session.getId()+".png";
            //String chartPath = context.getRealPath("/") + "charts\\" + barChartName;
    		String chartPath = "";
    		
    		if(sPath != null)
    			chartPath = sPath + barChartName;
    		else
    			chartPath = context.getRealPath("/") + "charts\\" + barChartName;    			
            
            String percentageChartId = party.concat(electionType).concat(yearOne).concat("Comparing_with").concat(yearTwo).concat("LineChart");
            String lineChartName = "electionsComparisonChart_" + percentageChartId + session.getId()+".png";
            //String lineChartPath = context.getRealPath("/") + "charts\\" + lineChartName;
            String lineChartPath = "";
            
            if(sPath != null)
            	lineChartPath = sPath + lineChartName;
            else
            	lineChartPath = context.getRealPath("/") + "charts\\" + lineChartName;
            
            String seatsChartId = party.concat(electionType).concat(yearOne).concat("Comparing_with").concat(yearTwo).concat("LineChartSeatsWon");
            String seatsLineChartName = "electionsComparisonChart_" + seatsChartId + session.getId()+".png";
            
            if(sPath != null)
            	seatsLineChartPath = sPath + seatsLineChartName;
            else
            	seatsLineChartPath = context.getRealPath("/") + "charts\\" + seatsLineChartName;
            
            logger.warn("Created Seats Line Chart Path :" + seatsLineChartPath);
                       
            String totalPercentChartId = party.concat(electionType).concat(yearOne).concat("Comparing_with").concat(yearTwo).concat("LineChartTotalPercent");
            String totalPercentLineChartName = "electionsComparisonChart_" + totalPercentChartId + session.getId()+".png";
            
            if(sPath != null)
            	totalPercentLineChartPath = sPath + totalPercentLineChartName;
            else
            	 totalPercentLineChartPath = context.getRealPath("/") + "charts\\" + totalPercentLineChartName;
            
            logger.warn("Created Percentage Line Chart Path :" + totalPercentLineChartPath);
                      
            if(electionComparisonReportVO.getPositionsForYearOne() != null && electionComparisonReportVO.getPositionsForYearTwo() != null){
            	if(hasAlliances==false){
            		PartyPositionsVO partyPositionsVOYear1 = getMainPartyPositions(electionComparisonReportVO.getPositionsForYearOne(),Long.parseLong(getParty()));
                	PartyPositionsVO partyPositionsVOYear2 = getMainPartyPositions(electionComparisonReportVO.getPositionsForYearTwo(),Long.parseLong(getParty()));
                	String label = partyPositionsVOYear1.getPartyName();
                	label = label.concat("  Results").concat("  Graph");
                	ChartProducer.createBarChart(label, "Years", "Seats", createDatasetForBarGraph(yearOne, yearTwo, partyPositionsVOYear1,partyPositionsVOYear2), chartPath);
            	}else{
            		String label = selectedPartyName;
                	label = label.concat("  Results").concat("  Graph");
                	ChartProducer.createBarChart(label, "Years", "Seats", createDatasetForBarGraphConsideringAlliance(yearOne, yearTwo, electionComparisonReportVO.getPositionsForYearOne(),electionComparisonReportVO.getPositionsForYearTwo()), chartPath);
            	}
            	
            	request.setAttribute("barChartName", barChartName);
    			session.setAttribute("barChartName", barChartName);
            }
            
            String xaxis;
            if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType))
            	xaxis = "Districts";
            else
            	xaxis = "States";
            
            ChartProducer.createLineChart("", xaxis, "Votes Percentage", createDatasetForLineChart(electionComparisonReportVO), lineChartPath,300,880, null,true );
            ChartProducer.createLineChart("", xaxis, "Votes Percentage", createDatasetTotalPercentForLineChart(electionComparisonReportVO), totalPercentLineChartPath,300,880, null,true );
            ChartProducer.createLineChart("", xaxis, "Seats Won", createDatasetForSeatsLineChart(electionComparisonReportVO), seatsLineChartPath,300,880, null,true );
            electionComparisonReportVO.setPercentageChart(lineChartName);
            electionComparisonReportVO.setSeatsWonChart(seatsLineChartName);
            electionComparisonReportVO.setTotalPercentChart(totalPercentLineChartName);
        }
		catch(Exception ex){
			ex.printStackTrace();
			
			return "failure";
        }

		return Action.SUCCESS;
	
	}
	
	private CategoryDataset createDatasetForSeatsLineChart(
			ElectionComparisonReportVO electionComparisonReportVO2) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearOne = electionComparisonReportVO.getDistrictWisePartyResultsForYearOne();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearTwo = electionComparisonReportVO.getDistrictWisePartyResultsForYearTwo();
		
		for(DistrictWisePartyResultVO dist1:districtWisePartyResultsForYearOne)
			dataset.addValue(new BigDecimal(dist1.getSeatsWon()), electionComparisonReportVO.getYearOne(),
					dist1.getDistrictName());
		for(DistrictWisePartyResultVO dist2:districtWisePartyResultsForYearTwo)
			dataset.addValue(new BigDecimal(dist2.getSeatsWon()), electionComparisonReportVO.getYearTwo(), 
					dist2.getDistrictName());
		
		return dataset;
	}

	private CategoryDataset createDatasetForLineChart(ElectionComparisonReportVO electionComparisonReportVO){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearOne = electionComparisonReportVO.getDistrictWisePartyResultsForYearOne();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearTwo = electionComparisonReportVO.getDistrictWisePartyResultsForYearTwo();
		
		for(DistrictWisePartyResultVO dist1:districtWisePartyResultsForYearOne)
			dataset.addValue(new BigDecimal(dist1.getVotesPercent()), electionComparisonReportVO.getYearOne(),
					dist1.getDistrictName());
		for(DistrictWisePartyResultVO dist2:districtWisePartyResultsForYearTwo)
			dataset.addValue(new BigDecimal(dist2.getVotesPercent()), electionComparisonReportVO.getYearTwo(), 
					dist2.getDistrictName());
		
		return dataset;
	}
	
	private CategoryDataset createDatasetTotalPercentForLineChart(ElectionComparisonReportVO electionComparisonReportVO){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearOne = electionComparisonReportVO.getDistrictWisePartyResultsForYearOne();
		List<DistrictWisePartyResultVO> districtWisePartyResultsForYearTwo = electionComparisonReportVO.getDistrictWisePartyResultsForYearTwo();
		
		for(DistrictWisePartyResultVO dist1:districtWisePartyResultsForYearOne)
			dataset.addValue(new BigDecimal(dist1.getTotalPercentage()), electionComparisonReportVO.getYearOne(),
					dist1.getDistrictName());
		for(DistrictWisePartyResultVO dist2:districtWisePartyResultsForYearTwo)
			dataset.addValue(new BigDecimal(dist2.getTotalPercentage()), electionComparisonReportVO.getYearTwo(), 
					dist2.getDistrictName());
		
		return dataset;
	}
      			
	public PartyPositionsVO getMainPartyPositions(List<PartyPositionsVO> positions,Long party){
		PartyPositionsVO partyPositions = null;
		for(PartyPositionsVO partyPos:positions){
		if(partyPos.getPartyId().equals(party))
		return partyPos;
		}
	return partyPositions;
	}
	
	
	private CategoryDataset createDatasetForBarGraphConsideringAlliance(String yearOne,String yearTwo,List<PartyPositionsVO> positionsForYearOne,List<PartyPositionsVO> positionsForYearTwo){
		  // row keys...
		 final String category1 =  "Seats Won";
	     final String category2 = "2nd Pos";
	     final String category3 = "3rd Pos";
	     final String category4 = "4th Pos";
        		
        // create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Long pos1=0l,pos2=0l,pos3=0l,pos4=0l;
		for(PartyPositionsVO positions : positionsForYearOne){
			pos1+=positions.getTotalSeatsWon();
			pos2+=positions.getSecondPosWon();
			pos3+=positions.getThirdPosWon();
			pos4+=positions.getFourthPosWon();
		}
		 dataset.addValue(pos1, category1, yearOne);
		 dataset.addValue(pos2, category2, yearOne);
		 dataset.addValue(pos3, category3, yearOne);
		 dataset.addValue(pos4, category4, yearOne);
		 
		 Long position1=0l,position2=0l,position3=0l,position4=0l;
		for(PartyPositionsVO positions : positionsForYearTwo){
			position1+=positions.getTotalSeatsWon();
			position2+=positions.getSecondPosWon();
			position3+=positions.getThirdPosWon();
			position4+=positions.getFourthPosWon();			
		}
		 dataset.addValue(position1, category1, yearTwo);
		 dataset.addValue(position2, category2, yearTwo);
		 dataset.addValue(position3, category3, yearTwo);
		 dataset.addValue(position4, category4, yearTwo); 
		 
	return dataset;
	}
	
	private CategoryDataset createDatasetForBarGraph(String yearOne,String yearTwo,PartyPositionsVO positionsForYearOne,PartyPositionsVO positionsForYearTwo){
		  // row keys...
		 final String category1 =  "Seats Won";
	     final String category2 = "2nd Pos";
	     final String category3 = "3rd Pos";
	     final String category4 = "4th Pos";
	     
      // create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 dataset.addValue(positionsForYearOne.getTotalSeatsWon(), category1, yearOne);
		 dataset.addValue(positionsForYearOne.getSecondPosWon(), category2, yearOne);
		 dataset.addValue(positionsForYearOne.getThirdPosWon(), category3, yearOne);
		 dataset.addValue(positionsForYearOne.getFourthPosWon(), category4, yearOne);
		 
		 dataset.addValue(positionsForYearTwo.getTotalSeatsWon(), category1, yearTwo);
		 dataset.addValue(positionsForYearTwo.getSecondPosWon(), category2, yearTwo);
		 dataset.addValue(positionsForYearTwo.getThirdPosWon(), category3, yearTwo);
		 dataset.addValue(positionsForYearOne.getFourthPosWon(), category4, yearTwo);
		 
	return dataset;
	}
	
	public String getComparedElectionResults(){
		
		String param=null;		
		param=request.getParameter("task");
		logger.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		Long elecIdOne = jObj.getLong("electionIdOne");
		Long elecIdTwo = jObj.getLong("electionIdTwo");
		Long stateOrDistrictId  = jObj.getLong("stateOrDistrictId");
		Long partyId = jObj.getLong("partyId");
		String hasAlliances = jObj.getString("hasAlliance");
		
		logger.debug("Year One:"+elecIdOne);
		logger.debug("Year Two:"+elecIdTwo);
		logger.debug("District Id:"+stateOrDistrictId);
		logger.debug("PartyId Id:"+partyId);
		logger.debug("Has Alliances:"+hasAlliances);
			
		comparedResultsVO = electionComparisonReportService.getComparedElectionResults(new Long(partyId), elecIdOne, elecIdTwo, stateOrDistrictId, new Boolean(hasAlliances));
		
		return Action.SUCCESS;
	}
	
	
	public String getPartyPositionDetails(){
		
		String param=null;		
		param=request.getParameter("task");
		logger.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("getPartyPositionDetails ********* jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = jObj.getLong("electionId");
		Long partyId = jObj.getLong("party");
		Long rank = jObj.getLong("rank");
		
		partyPositionDisplayVO = partyService.getPartyPositionsDetailsInAnElection(electionId, partyId, rank);
		
		return Action.SUCCESS;
	}
	
	public String getConstituencyElectionResults(){
		
		String param=null;		
		param=request.getParameter("task");
		logger.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String constituencyId = jObj.getString("constiId");
		String electionId = jObj.getString("electionId");
		String partyId = jObj.getString("partyId");
		
		logger.debug("ConstituencyId :" + constituencyId);
		logger.debug("electionId :" + electionId);
		logger.debug("partyId :" + partyId);
		
		constiElecResults  = staticDataService.getCompleteElectionResultsForAConstituency(new Long(constituencyId), new Long(electionId), new Long(partyId));
		
		return Action.SUCCESS;
	}


	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	
	
}
