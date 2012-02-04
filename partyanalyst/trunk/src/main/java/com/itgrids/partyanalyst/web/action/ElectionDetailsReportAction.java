package com.itgrids.partyanalyst.web.action;

import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
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

import com.itgrids.partyanalyst.dto.AlliancePartyDistrictResultsVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStateRegionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionDetailsReportAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(ElectionDetailsReportAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private ElectionResultsReportVO electionCompleteDetailsVO;
	private String electionId;
	private String stateID;
	private String electionType;
	private Long electionTypeId;
	private String stateName;
	private String year;
	private ServletContext context;
	private IElectionReportService electionReportService;
	private IStaticDataService staticDataService;
	private IPartyStrengthService partyStrengthService;
	private HttpSession session;
	private List<SelectOptionVO> electionYears;
	private List<SelectOptionVO> partiesList;	
	private IStateRegionService stateRegionService;	
	private StateElectionsVO partyResultsInRegionVO; 
	private PartyPositionsVO partyPositionsVO = new PartyPositionsVO();
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	private List<PartyElectionResultVO> partyElectionResultVO;
	private Boolean hasDeatiledAnalysis = false;
	private EntitlementsHelper entitlementsHelper;
	
	public Boolean getHasDeatiledAnalysis() {
		return hasDeatiledAnalysis;
	}

	public void setHasDeatiledAnalysis(Boolean hasDeatiledAnalysis) {
		this.hasDeatiledAnalysis = hasDeatiledAnalysis;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setPartyPositionsVO(PartyPositionsVO partyPositionsVO) {
		this.partyPositionsVO = partyPositionsVO;
	}

	public PartyPositionsVO getPartyPositionsVO() {
		return partyPositionsVO;
	}
	public IPartyStrengthService getPartyStrengthService() {
		return partyStrengthService;
	}

	public void setPartyStrengthService(IPartyStrengthService partyStrengthService) {
		this.partyStrengthService = partyStrengthService;
	}

	public StateElectionsVO getPartyResultsInRegionVO() {
		return partyResultsInRegionVO;
	}

	public void setPartyResultsInRegionVO(StateElectionsVO partyResultsInRegionVO) {
		this.partyResultsInRegionVO = partyResultsInRegionVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ElectionResultsReportVO getElectionCompleteDetailsVO() {
		return electionCompleteDetailsVO;
	}

	public void setElectionCompleteDetailsVO(
			ElectionResultsReportVO electionCompleteDetailsVO) {
		this.electionCompleteDetailsVO = electionCompleteDetailsVO;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static Logger getLog() {
		return log;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public ServletContext getContext() {
		return context;
	}

	public IElectionReportService getElectionReportService() {
		return electionReportService;
	}

	public void setElectionReportService(
			IElectionReportService electionReportService) {
		this.electionReportService = electionReportService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}

	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}

	public IStateRegionService getStateRegionService() {
		return stateRegionService;
	}

	public void setStateRegionService(IStateRegionService stateRegionService) {
		this.stateRegionService = stateRegionService;
	}
    
	public List<PartyElectionResultVO> getPartyElectionResultVO() {
		return partyElectionResultVO;
	}

	public void setPartyElectionResultVO(
			List<PartyElectionResultVO> partyElectionResultVO) {
		this.partyElectionResultVO = partyElectionResultVO;
	}

	public String execute() throws Exception {
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		
		if(regVO != null && entitlementsHelper.checkForEntitlementToViewReport(regVO,IConstants.ELECTION_RESULT_REPORT_DETAILED_ANALYSIS))
			hasDeatiledAnalysis = true;
		
		electionYears = new ArrayList<SelectOptionVO>();
		partiesList = new ArrayList<SelectOptionVO>();

		if (electionType != null
				&& electionType.equals(IConstants.ASSEMBLY_ELECTION_TYPE)
				|| electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)) {
			try {
				electionYears = staticDataService.getElectionYearsBasedOnStateIdAndElecTypeId(
						 new Long(stateID) ,electionTypeId);
				electionYears.add(0, new SelectOptionVO(0l, "Select Year"));
				partiesList = partyStrengthService.getAllPartiesData(new Long(stateID));
				partiesList.add(0, new SelectOptionVO(0l, "Select A Party"));
			} catch (Exception e) {
				partiesList = null;
				electionYears = null;
				log
						.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}
		} else if (electionType != null && electionType.equals(IConstants.ZPTC)
				|| electionType.equals(IConstants.MPTC)) {
			try {
				electionYears = staticDataService
						.getAllElectionYearsForATeshil(electionTypeId);
				electionYears.add(0, new SelectOptionVO(0l, "Select Year"));
				partiesList = staticDataService.getAllPartiesForAnElectionYear(
						year, electionType);
				partiesList.add(0, new SelectOptionVO(0l, "Select A Party"));
			} catch (Exception e) {
				partiesList = null;
				electionYears = null;
				log
						.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}
		}

		return Action.SUCCESS;
	}

	public String ajaxCallHandler() {
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if (log.isDebugEnabled())
				log.debug(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jObj.getString("task").equalsIgnoreCase("electionsBasicInfo")) {
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			String electionType = jObj.getString("electionType");
		}
		if (jObj.getString("task").equalsIgnoreCase("getResultForAnElection")) {
			session = request.getSession();
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			Long stateId = new Long(jObj.getString("stateID"));
			String electionType = jObj.getString("electionType");
			String year = jObj.getString("year");
			Long electionId = jObj.getLong("electionId");

			electionCompleteDetailsVO = electionReportService
					.getBasicResultsForAnElection(electionType, year, stateId,
							IConstants.VOTES_PERCENT_MARGIN);
			
			electionCompleteDetailsVO.setElectionBasicVotersData(electionReportService.getVotersDataOfTwoElections(electionId));
			if (electionCompleteDetailsVO != null) {
				if (electionCompleteDetailsVO.getResultStatus().getResultCode() == ResultCodeMapper.FAILURE)
					return Action.ERROR;

				
				//call service to get if state has regions or not
				if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
					electionCompleteDetailsVO.setHasRegions(false);
				}
				else{
				Boolean hasRegions = stateRegionService.getStateRegionAvailability(stateId);
				electionCompleteDetailsVO.setHasRegions(hasRegions);
				}
				
			}
		}
		if (jObj.getString("task").equalsIgnoreCase("getPartyGenderInfo")) {
			partyElectionResultVO = electionReportService.getPartyBasicDetailsWithGenderInfoForAnElection(jObj.getLong("electionId"));
		}
		if (jObj.getString("task").equalsIgnoreCase("getConstituencyAreaTypeWiseResult")) {
			partyElectionResultVO = electionReportService.getPartyElectionResultWithConstituencyAreaType(jObj.getLong("electionId"));
		}
		if (jObj.getString("task").trim().equalsIgnoreCase("TopVotesGained") || jObj.getString("task").trim().equalsIgnoreCase("HighestMarginGained") || jObj.getString("task").trim().equalsIgnoreCase("LowestMarginGained") || jObj.getString("task").trim().equalsIgnoreCase("TopVotesGainedPerc")) {
			partyElectionResultVO = electionReportService.getTopVotesMarginVotesDetails(jObj.getLong("electionId"),jObj.getInt("maxResult"),jObj.getString("task").trim());
			
		}
		return Action.SUCCESS;

	}

	/*
	 * method to create line chart for alliancParties
	 */
	public String createLineChartForAlliancParties(
			AlliancePartyResultsVO alliancParties) {
		String chartName = null;
		String cPath = request.getContextPath();
		String alliancePartiesChartPath;
		try {
			String alliancePartiesChartId = electionCompleteDetailsVO
					.getElectionType().concat(
							electionCompleteDetailsVO.getElectionYear())
					.concat(alliancParties.getAllianceGroupName()).concat(
							"Election_Results").concat("LineChart");
			String alliancePartiesChartName = "alliancPartyElectionResults_"
					+ alliancePartiesChartId + session.getId() + ".png";
			if(cPath.contains("PartyAnalyst"))
			      alliancePartiesChartPath = context.getRealPath("/")+ "charts\\" + alliancePartiesChartName;
			else
				  alliancePartiesChartPath = chartProducerURL + alliancePartiesChartName;
			
			ChartProducer.createLineChart("", "", "Seats",
					createDataSetForAlliancPartyOverallResults(alliancParties
							.getPartiesInAlliance(), "BarChart", null),
					alliancePartiesChartPath, 300, 600, null, true);
			request.setAttribute("alliancePartiesChartName",
					alliancePartiesChartName);
			session.setAttribute("alliancePartiesChartName",
					alliancePartiesChartName);
			chartName = alliancePartiesChartName;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
	}

	/*
	 * method to create line chart for alliancParties for district level
	 */
	public String createLineChartForAlliancPartiesForDistrictLevel(
			AlliancePartyDistrictResultsVO alliancParties) {
		String chartName = null;
		String alliancePartiesChartPath;
		List<Color> colors = new ArrayList<Color>();
		try {
			String cPath = request.getContextPath();
			String alliancePartiesChartId = electionCompleteDetailsVO
					.getElectionType().concat(
							electionCompleteDetailsVO.getElectionYear())
					.concat(alliancParties.getAllianceGroupName()).concat(
							"Election_Results_DistrictWise")
					.concat("LineChart");
			String alliancePartiesChartName = "alliancPartyElectionResultsDistrictWise_"
					+ alliancePartiesChartId + session.getId() + ".png";
			
			if(cPath.contains("PartyAnalyst"))
			    alliancePartiesChartPath = context.getRealPath("/")+ "charts\\" + alliancePartiesChartName;
			else
		        alliancePartiesChartPath = chartProducerURL+alliancePartiesChartName;
			CategoryDataset categoryDataset = createDataSetForPartyDistrictwiseResults(
					alliancParties.getPartiesInAlliance(), colors);
			log
					.debug("createLineChartForAlliancPartiesForDistrictLevel Colors::"
							+ colors.size());
			ChartProducer.createLineChart("", "", "Seats", categoryDataset,
					alliancePartiesChartPath, 300, 800, colors, true);
			request.setAttribute("alliancePartiesChartName",
					alliancePartiesChartName);
			session.setAttribute("alliancePartiesChartName",
					alliancePartiesChartName);
			chartName = alliancePartiesChartName;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
	}

	/*
	 * method to create line chart for for district level overall results for
	 * parties
	 */
	public String createLineChartForPartiesWithDistrictLevelResults(
			List<DistrictWisePartyPositionsVO> allPartiesResults,
			String chartType, String title, int height, int width) {
		String chartName = null;
		String cPath = request.getContextPath();
		String partyDistrictResultsChartPath;
		List<Color> colors = new ArrayList<Color>();
		try {
			String partyDistrictResultsChartId = electionCompleteDetailsVO
					.getElectionType().concat(
							electionCompleteDetailsVO.getElectionYear())
					.concat("Election_Results_Districtwise")
					.concat("LineChart").concat(chartType);
			String partyDistrictResultsChartName = "partyDistrictResults_"
					+ partyDistrictResultsChartId + session.getId() + ".png";
			
			if(cPath.contains("PartyAnalyst"))
			   partyDistrictResultsChartPath = context.getRealPath("/")+ "charts\\" + partyDistrictResultsChartName;
			else
			   partyDistrictResultsChartPath = chartProducerURL + partyDistrictResultsChartName;

			CategoryDataset categoryDataset = createDataSetForPartyDistrictwiseResults(
					allPartiesResults, colors);
			log
					.debug("createLineChartForPartiesWithDistrictLevelResults Colors::"
							+ colors.size());
			ChartProducer.createLineChart(title, "", "Votes Percentage",
					categoryDataset, partyDistrictResultsChartPath, height,
					width, colors, true);
			request.setAttribute("partyDistrictResultsChartName",
					partyDistrictResultsChartName);
			session.setAttribute("partyDistrictResultsChartName",
					partyDistrictResultsChartName);

			chartName = partyDistrictResultsChartName;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
	}

	/*
	 * creating line chart for overall results
	 */
	public String createLineChartForStateLevelResults(
			List<PartyPositionsVO> allPartiesResults, String chartType,
			String title) {
		log.debug("in create line graph method");
		String chartName = null;
		String cPath = request.getContextPath();
		String allPartiesChartPath;
		List<Color> colors = new ArrayList<Color>();
		try {
			String allPartiesChartId = electionCompleteDetailsVO
					.getElectionType().concat(
							electionCompleteDetailsVO.getElectionYear())
					.concat("Overall").concat("Election_Results").concat(
							"All_Parties_LineChart").concat(chartType);
			String allPartiesChartName = "alliancPartyElectionResults_"+ allPartiesChartId + session.getId() + ".png";
			if(cPath.contains("PartyAnalyst"))
			    allPartiesChartPath = context.getRealPath("/") + "charts\\"+ allPartiesChartName;
			else
			   allPartiesChartPath = chartProducerURL+allPartiesChartName;

			ChartProducer.createLineChart(title, "", "Seats",
					createDataSetForAlliancPartyOverallResults(
							allPartiesResults, "LineChart", colors),
					allPartiesChartPath, 300, 800, colors, true);
			log.debug("Line Chart Colors ::::::::::::::::::::::::::::::::::::"
					+ colors.size());
			request.setAttribute("allPartiesChartName", allPartiesChartName);
			session.setAttribute("allPartiesChartName", allPartiesChartName);
			chartName = allPartiesChartName;

		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}

		return chartName;
	}

	/*
	 * creating dataset for complete results
	 */
	private CategoryDataset createDatasetForPartyResults(
			List<PartyPositionsVO> allPartiesResults) {

		int i = 0;
		final String category1 = "Seats Won";
		final String category2 = "2nd Pos";
		final String category3 = "3rd Pos";
		final String category4 = "4th Pos";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (PartyPositionsVO result : allPartiesResults) {

			if (i > 8) {
				if (!result.getPartyName().equals("TDP")
						&& !result.getPartyName().equals("TRS")
						&& !result.getPartyName().equals("CPI")
						&& !result.getPartyName().equals("CPM")
						&& !result.getPartyName().equals("BJP")
						&& !result.getPartyName().equals("PRP")) {
					i++;
					continue;
				}
			}
			final String series = result.getPartyName();
			dataset.addValue(new Double(result.getTotalSeatsWon()), category1,
					series);
			dataset.addValue(new Double(result.getSecondPosWon()), category2,
					series);
			dataset.addValue(new Double(result.getThirdPosWon()), category3,
					series);
			dataset.addValue(new Double(result.getFourthPosWon()), category4,
					series);
			i++;
		}
		return dataset;
	}

	/*
	 * creating dataset for districtwise results
	 */
	private CategoryDataset createDataSetForPartyDistrictwiseResults(
			List<DistrictWisePartyPositionsVO> allPartiesResults,
			List<Color> colors) {
		log.debug("in  createDataSetForPartyDistrictwiseResults");
		int i = 0;

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (DistrictWisePartyPositionsVO parties : allPartiesResults) {
			if (i > 8) {

				if (!parties.getPartyName().equals("TDP")
						&& !parties.getPartyName().equals("TRS")
						&& !parties.getPartyName().equals("CPI")
						&& !parties.getPartyName().equals("CPM")
						&& !parties.getPartyName().equals("BJP")
						&& !parties.getPartyName().equals("PRP")) {
					log.debug("In Continue");
					i++;
					continue;
				}
			}
			final String category = parties.getPartyName();
			for (PartyPositionsInDistrictVO districtResults : parties
					.getPartyResultsInDistricts()) {
				dataset.addValue(new Double(districtResults
						.getCompleteVotesPercent()), category, districtResults
						.getDistrictName());
			}
			i++;
		}
		return dataset;
	}

	/*
	 * creating dataset for alliancParties overall results
	 */
	private CategoryDataset createDataSetForAlliancPartyOverallResults(
			List<PartyPositionsVO> alliancPartiesResults, String chartType,
			List<Color> colors) {
		log.debug("createDataSetForAlliancPartyOverallResults");
		final String series1 = "Seats Won";
		final String series2 = "2nd Pos";
		final String series3 = "3rd Pos";
		final String series4 = "4th Pos";
		final String series5 = "Nth Pos";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (PartyPositionsVO parties : alliancPartiesResults) {
			final String category = parties.getPartyName();
			if (chartType.equals("LineChart")) {
				if (category.equals("IND"))
					continue;
				if (IConstants.TDP.equalsIgnoreCase(parties.getPartyName())) {
					colors.add(IConstants.TDP_COLOR);
					log.debug("TDP ADDEd");
				}

				else if (IConstants.INC
						.equalsIgnoreCase(parties.getPartyName())) {
					colors.add(IConstants.INC_COLOR);
					log.debug("INC ADDEd");
				} else if (IConstants.BJP.equalsIgnoreCase(parties
						.getPartyName())) {
					colors.add(IConstants.BJP_COLOR);
					log.debug("BJP ADDEd");
				} else if (IConstants.PRP.equalsIgnoreCase(parties
						.getPartyName())) {
					colors.add(IConstants.PRP_COLOR);
					log.debug("PRP ADDEd");
				} else if (IConstants.TRS.equalsIgnoreCase(parties
						.getPartyName())) {
					colors.add(IConstants.TRS_COLOR);
					log.debug("TRS ADDEd");
				} else if (IConstants.AIMIM.equalsIgnoreCase(parties
						.getPartyName())) {
					colors.add(IConstants.AIMIM_COLOR);
					log.debug("AIMIM ADDEd");
				} else if (IConstants.CPI.equalsIgnoreCase(parties
						.getPartyName())) {
					colors.add(IConstants.CPI_COLOR);
					log.debug("CPI ADDEd");
				} else {
					colors.add(null);
					log.debug("Default ADDEd");
				}
			}

			dataset.addValue(new Long(parties.getTotalSeatsWon()), category,
					series1);
			dataset.addValue(new Long(parties.getSecondPosWon()), category,
					series2);
			dataset.addValue(new Long(parties.getThirdPosWon()), category,
					series3);
			dataset.addValue(new Long(parties.getFourthPosWon()), category,
					series4);
			dataset.addValue(new Long(parties.getNthPosWon()), category,
					series5);

		}
		return dataset;
	}
	
	public String getRegionWisePartyElectionResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long electionId = jObj.getLong("electionId");
		Long stateId = jObj.getLong("stateID");
		
		partyResultsInRegionVO = stateRegionService.getRegionWisePartyResultsInState(stateId, electionId);
		
		return Action.SUCCESS;
	}

	

}
