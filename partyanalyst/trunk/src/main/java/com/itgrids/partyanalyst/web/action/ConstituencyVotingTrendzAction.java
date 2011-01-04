package com.itgrids.partyanalyst.web.action;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlliancePartiesInElection;
import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyMandalVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.ElectionTypeChartVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersVO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.helper.Constants;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionDataVOComparator;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.itgrids.partyanalyst.utils.ElectionResultPartyVOByElectionType;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyResultVOPercentComparator;
import com.itgrids.partyanalyst.utils.VotersInfoForMandalVOComparator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyVotingTrendzAction extends ActionSupport
implements ServletRequestAware, ServletResponseAware, ServletContextAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConstituencyVotingTrendzAction.class);
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private ServletContext context;
	private Long zptcElectionId; 
	private Long mptcElectionId;
	private String mptcElectionType,zptcElectionType;
	private List<SelectOptionVO> zptcElectionYears;
	private List<SelectOptionVO> mptcElectionYears;
	private String muncipalityElectionType,corporationElectionType;
	private Long muncipalityElectionTypeId,corporationElectionTypeId;
	private ElectionWiseMandalPartyResultListVO urbanRuralResultsVO;
	private IBiElectionPageService biElectionPageService;
	private IConstituencyPageService constituencyPageService;
	private IStaticDataService staticDataService;
	private String task;
	JSONObject jObj;
	
	private String districtId;
	private String constiId;
	private String constiName;
	private List<SelectOptionVO> electionTypes;
	private BiElectionResultsMainVO biElectionResultsMainVO;
	private List<BiElectionDistrictVO> districtsAndConsts;
	private List<SelectOptionVO> staticPartiesList;
	private MandalVO mandalVO;	
	private ConstituencyInfoVO constituencyDetails;
	private ConstituencyVO constituencyVO;
	private VotersWithDelimitationInfoVO votersInfoForMandals;
	private List<PartyResultVO> votesSharing;
	private String mandalsPartiesChart;	
	private TeshilPartyInfoVO localMuncipalElections;
	private TeshilPartyInfoVO localCorporationElections;	
	private ElectionTrendzReportVO constituencyOverView;
	private ElectionWiseMandalPartyResultListVO partywiseVotesDetailsForMandal;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private List<PartyResultsInfoVO> contestingCands;
	private PartyElectionResultsVO electionResultsInConsti;
	private List<TeshilPartyInfoVO> zptcMptcResultsInMandal;
	private ConstituencyRevenueVillagesVO chartResultVO;
	private List<PartyElectionVotersVO> partiesElecsResults;
	private String windowType;

	public String getMuncipalityElectionType() {
		return muncipalityElectionType;
	}

	public void setMuncipalityElectionType(String muncipalityElectionType) {
		this.muncipalityElectionType = muncipalityElectionType;
	}

	public List<PartyResultsInfoVO> getContestingCands() {
		return contestingCands;
	}

	public void setContestingCands(List<PartyResultsInfoVO> contestingCands) {
		this.contestingCands = contestingCands;
	}

	public String getCorporationElectionType() {
		return corporationElectionType;
	}

	public void setCorporationElectionType(String corporationElectionType) {
		this.corporationElectionType = corporationElectionType;
	}

	public Long getMuncipalityElectionTypeId() {
		return muncipalityElectionTypeId;
	}

	public void setMuncipalityElectionTypeId(Long muncipalityElectionTypeId) {
		this.muncipalityElectionTypeId = muncipalityElectionTypeId;
	}

	public Long getCorporationElectionTypeId() {
		return corporationElectionTypeId;
	}

	public void setCorporationElectionTypeId(Long corporationElectionTypeId) {
		this.corporationElectionTypeId = corporationElectionTypeId;
	}

	public TeshilPartyInfoVO getLocalMuncipalElections() {
		return localMuncipalElections;
	}

	public void setLocalMuncipalElections(TeshilPartyInfoVO localMuncipalElections) {
		this.localMuncipalElections = localMuncipalElections;
	}

	public PartyElectionResultsVO getElectionResultsInConsti() {
		return electionResultsInConsti;
	}

	public void setElectionResultsInConsti(
			PartyElectionResultsVO electionResultsInConsti) {
		this.electionResultsInConsti = electionResultsInConsti;
	}

	public TeshilPartyInfoVO getLocalCorporationElections() {
		return localCorporationElections;
	}

	public void setLocalCorporationElections(
			TeshilPartyInfoVO localCorporationElections) {
		this.localCorporationElections = localCorporationElections;
	}

	private List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO;	
	private ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO;
	private ChartColorsAndDataSetVO chartColorsAndDataSetVO;
	
	
	public List<PartyResultVO> getVotesSharing() {
		return votesSharing;
	}

	public void setVotesSharing(List<PartyResultVO> votesSharing) {
		this.votesSharing = votesSharing;
	}

	public ElectionTrendzReportVO getConstituencyOverView() {
		return constituencyOverView;
	}

	public void setConstituencyOverView(ElectionTrendzReportVO constituencyOverView) {
		this.constituencyOverView = constituencyOverView;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public VotersWithDelimitationInfoVO getVotersInfoForMandals() {
		return votersInfoForMandals;
	}

	public void setVotersInfoForMandals(
			VotersWithDelimitationInfoVO votersInfoForMandals) {
		this.votersInfoForMandals = votersInfoForMandals;
	}

	public ConstituencyRevenueVillagesVO getChartResultVO() {
		return chartResultVO;
	}

	public void setChartResultVO(ConstituencyRevenueVillagesVO chartResultVO) {
		this.chartResultVO = chartResultVO;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}

	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
	}
	
	public Long getZptcElectionId() {
		return zptcElectionId;
	}

	public void setZptcElectionId(Long zptcElectionId) {
		this.zptcElectionId = zptcElectionId;
	}

	public Long getMptcElectionId() {
		return mptcElectionId;
	}

	public void setMptcElectionId(Long mptcElectionId) {
		this.mptcElectionId = mptcElectionId;
	}

	public String getMptcElectionType() {
		return mptcElectionType;
	}

	public void setMptcElectionType(String mptcElectionType) {
		this.mptcElectionType = mptcElectionType;
	}

	public String getZptcElectionType() {
		return zptcElectionType;
	}

	public void setZptcElectionType(String zptcElectionType) {
		this.zptcElectionType = zptcElectionType;
	}

	public List<SelectOptionVO> getZptcElectionYears() {
		return zptcElectionYears;
	}

	public void setZptcElectionYears(List<SelectOptionVO> zptcElectionYears) {
		this.zptcElectionYears = zptcElectionYears;
	}

	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}

	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
	}

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getConstiId() {
		return constiId;
	}

	public void setConstiId(String constiId) {
		this.constiId = constiId;
	}

	public String getConstiName() {
		return constiName;
	}

	public void setConstiName(String constiName) {
		this.constiName = constiName;
	}	
	

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public BiElectionResultsMainVO getBiElectionResultsMainVO() {
		return biElectionResultsMainVO;
	}

	public void setBiElectionResultsMainVO(
			BiElectionResultsMainVO biElectionResultsMainVO) {
		this.biElectionResultsMainVO = biElectionResultsMainVO;
	}	

	public List<BiElectionDistrictVO> getDistrictsAndConsts() {
		return districtsAndConsts;
	}

	public void setDistrictsAndConsts(List<BiElectionDistrictVO> districtsAndConsts) {
		this.districtsAndConsts = districtsAndConsts;
	}

	public void setStaticPartiesList(List<SelectOptionVO> staticPartiesList) {
		this.staticPartiesList = staticPartiesList;
	}

	public List<SelectOptionVO> getStaticPartiesList() {
		return staticPartiesList;
	}	

	public MandalVO getMandalVO() {
		return mandalVO;
	}

	public void setMandalVO(MandalVO mandalVO) {
		this.mandalVO = mandalVO;
	}

	public String getMandalsPartiesChart() {
		return mandalsPartiesChart;
	}

	public void setMandalsPartiesChart(String mandalsPartiesChart) {
		this.mandalsPartiesChart = mandalsPartiesChart;
	}	

	public ElectionWiseMandalPartyResultListVO getPartywiseVotesDetailsForMandal() {
		return partywiseVotesDetailsForMandal;
	}

	public void setPartywiseVotesDetailsForMandal(
			ElectionWiseMandalPartyResultListVO partywiseVotesDetailsForMandal) {
		this.partywiseVotesDetailsForMandal = partywiseVotesDetailsForMandal;
	}
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}	

	public List<ElectionWiseMandalPartyResultVO> getMptcZptcElectionResultsVO() {
		return mptcZptcElectionResultsVO;
	}

	public void setMptcZptcElectionResultsVO(
			List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO) {
		this.mptcZptcElectionResultsVO = mptcZptcElectionResultsVO;
	}
	
	public ElectionWiseMandalPartyResultListVO getElectionWiseMandalPartyResultListVO() {
		return electionWiseMandalPartyResultListVO;
	}

	public void setElectionWiseMandalPartyResultListVO(
			ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO) {
		this.electionWiseMandalPartyResultListVO = electionWiseMandalPartyResultListVO;
	}	

	public ChartColorsAndDataSetVO getChartColorsAndDataSetVO() {
		return chartColorsAndDataSetVO;
	}

	public void setChartColorsAndDataSetVO(
			ChartColorsAndDataSetVO chartColorsAndDataSetVO) {
		this.chartColorsAndDataSetVO = chartColorsAndDataSetVO;
	}	

	public List<TeshilPartyInfoVO> getZptcMptcResultsInMandal() {
		return zptcMptcResultsInMandal;
	}

	public void setZptcMptcResultsInMandal(
			List<TeshilPartyInfoVO> zptcMptcResultsInMandal) {
		this.zptcMptcResultsInMandal = zptcMptcResultsInMandal;
	}

	public ElectionWiseMandalPartyResultListVO getUrbanRuralResultsVO() {
		return urbanRuralResultsVO;
	}

	public void setUrbanRuralResultsVO(
			ElectionWiseMandalPartyResultListVO urbanRuralResultsVO) {
		this.urbanRuralResultsVO = urbanRuralResultsVO;
	}

	public List<PartyElectionVotersVO> getPartiesElecsResults() {
		return partiesElecsResults;
	}

	public void setPartiesElecsResults(
			List<PartyElectionVotersVO> partiesElecsResults) {
		this.partiesElecsResults = partiesElecsResults;
	}
	
	public String getWindowType() {
		return windowType;
	}

	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}

	public String execute() throws Exception
	{
		List<Long> constituencyIdsList = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder();
		districtsAndConsts = biElectionPageService.getBiElectionConstituenciesDistrictWise();
		for(BiElectionDistrictVO biElectionDistrictVO: districtsAndConsts)
		{	
			sb.append(biElectionDistrictVO.getDistrictId());
			for(SelectOptionVO obj:biElectionDistrictVO.getConstituenciesList())
				constituencyIdsList.add(obj.getId());						
		}
		mptcElectionType = IConstants.MPTC_ELECTION_TYPE;
		zptcElectionType = IConstants.ZPTC_ELECTION_TYPE;
		muncipalityElectionType = IConstants.MUNCIPLE_ELECTION_TYPE;
		corporationElectionType = IConstants.CORPORATION_ELECTION_TYPE;
		electionTypes = staticDataService.getAllElectionTypes();
		for(SelectOptionVO eleTypes : electionTypes){
			if(eleTypes.getName().equalsIgnoreCase(mptcElectionType)){
				mptcElectionId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(zptcElectionType)){
				zptcElectionId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(muncipalityElectionType)){
				muncipalityElectionTypeId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(corporationElectionType)){
				corporationElectionTypeId = eleTypes.getId();
			}
		}
		zptcElectionYears = staticDataService.getAllElectionYearsForATeshil(zptcElectionId);
		
		mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(mptcElectionId);
		mandalVO = staticDataService.findListOfElectionsAndPartiesInMandal(0L);
		staticPartiesList = mandalVO.getPartiesInMandal();
		//votesSharing = staticDataService.getPartyVotesShareInConstituency(Long.parseLong(constiId),1);
		return SUCCESS;
	}   
	
	public String getMuncipalElections(){
		String param=null;			    
		param = getTask();
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	
		Long constiId =  new Long(jObj.getString("constituencyId"));	
		localMuncipalElections = staticDataService.getLocalElectionDetailsForAConstituency(constiId,IConstants.MUNCIPLE_ELECTION_TYPE);	
		return SUCCESS;
	}
	
	public String getCorporationElections(){
		String param=null;			    
		param = getTask();
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	
		Long constiId =  new Long(jObj.getString("constituencyId"));		
		localCorporationElections = staticDataService.getLocalElectionDetailsForAConstituency(constiId,IConstants.CORPORATION_ELECTION_TYPE);	
		return SUCCESS;
	}
	
	public String getAllPartiesVotesSharing(){
		
		String chartNam = "";
		String param=null;			    
		param = getTask();
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		Long constiId =  new Long(jObj.getString("constituencyId"));
		String constiName = jObj.getString("constiName");
		
		String selectedChoices[];
		List<SelectOptionVO> selectdElections = null;
		
		String getAllString = (String)jObj.getString("getAll");
		if(getAllString.equals("all")){
			
			log.debug(" Inside All Elections Block ...");
			selectdElections = new ArrayList<SelectOptionVO>();
			selectdElections.add(new SelectOptionVO(new Long(1),IConstants.ASSEMBLY_ELECTION_TYPE));
			selectdElections.add(new SelectOptionVO(new Long(2),IConstants.PARLIAMENT_ELECTION_TYPE));
			
			selectdElections.add(new SelectOptionVO(new Long(3),IConstants.MPTC_ELECTION_TYPE));
			selectdElections.add(new SelectOptionVO(new Long(4),IConstants.ZPTC_ELECTION_TYPE));
			
			chartNam = chartNam + "_" + "ALL";
			selectedChoices = null;
		}
		else if(jObj.getLong("flag") != 1){
			JSONArray choices = jObj.getJSONArray("choices");
			selectedChoices = new String[choices.length()];
			
			
				log.debug(" Inside Some Elections Block ...");
				selectdElections = new ArrayList<SelectOptionVO>();
				for(int i=0; i<choices.length(); i++){
					selectedChoices[i] = (String)choices.get(i);
					
					String electionType = (String)choices.get(i);
					if(electionType.equalsIgnoreCase("AC"))
						selectdElections.add(new SelectOptionVO(new Long(i),IConstants.ASSEMBLY_ELECTION_TYPE));
					else if(electionType.equalsIgnoreCase("PC"))
						selectdElections.add(new SelectOptionVO(new Long(i),IConstants.PARLIAMENT_ELECTION_TYPE));
					else if(electionType.equalsIgnoreCase("MPTC"))
						selectdElections.add(new SelectOptionVO(new Long(i),IConstants.MPTC_ELECTION_TYPE));
					else if(electionType.equalsIgnoreCase("ZPTC"))
						selectdElections.add(new SelectOptionVO(new Long(i),IConstants.ZPTC_ELECTION_TYPE));
					
					chartNam = chartNam + "_" + electionType;
				}	
			
		}else{
			selectedChoices = null;
		}
			
		votesSharing = staticDataService.getPartyVotesPercentageInAConstituency(constiId,jObj.getString("getAll"),selectedChoices);

				
		//for chart changes
		
		List<ElectionResultPartyVO> electionResList = getConstituencyElectionResultsChart(new Long(constiId));
        
        if(electionResList != null && electionResList.size() > 0){
			
        	  String chartTitle = " All Parties Performance In "+constiName + " Constituency ";
			  String chartName = "constituencyElectionsResults"+"_"+constiName+"_"+constiId+chartNam+".png";
			  String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			  
			  chartColorsAndDataSetVO = createDatasetForChart(electionResList,selectdElections);
			  ChartProducer.createLineChartWithThickness(chartTitle, "Election", "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath,500,1000,new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),true);
			  
			  chartColorsAndDataSetVO.setChartName(chartName);
			  if(votesSharing != null && votesSharing.size() > 0)
		        	votesSharing.get(0).setChartName(chartName);
		}
        
     return SUCCESS;
	}
	public String getVotesOverViewInAConstituency() throws Exception
	{
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Long constiId =  new Long(jObj.getString("constituencyId"));
		String constiName = jObj.getString("constiName");
		List <ConstituencyElectionResultsVO> electionResultsVO;
		
		constituencyOverView = staticDataService.getConstituencyOverview(constiId,constiName);
		constituencyOverView.setByeElectionVotesPercentage(getByeElectionVotesPercentage(constiName));
		
		constituencyOverView.setVotesPercentageDifferene(new BigDecimal(constituencyOverView.getByeElectionVotesPercentage() - new Double(constituencyOverView.getLatestElectionYearsTotalVotesPercentage())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		Double polledVotes = (constituencyOverView.getPresentYearTotalVoters()*constituencyOverView.getByeElectionVotesPercentage()/100);
		constituencyOverView.setPresentYearTotalPolledVotes(polledVotes.longValue());
		//contestingCands = getContestingCandidateDetails(constiName);
		//constituencyOverView.setContestingCands(contestingCands);
		electionResultsVO = biElectionPageService.getMainPartiesResultsInConstituency(constiId, constiName);
		if(electionResultsVO != null && electionResultsVO.size() > 0)
		   getConstituencyResultCharts(electionResultsVO,constiName);	
		constituencyOverView.setElectionResultsVO(electionResultsVO);
		return Action.SUCCESS;
	}
	
	/*
	 * Method to create chart for constituency results
	 */
	public void getConstituencyResultCharts(List<ConstituencyElectionResultsVO> electionResultsVO,String constiName){
		log.debug("Inside chart building method...");
		Set<String> partiesInChart = new LinkedHashSet<String>();
		try{ 
			for(ConstituencyElectionResultsVO results:electionResultsVO){
				if(results.getResultsFlag()){
					List<CandidateOppositionVO> candResList = results.getCandidateOppositionList();
					if(candResList != null && candResList.size() > 0){
						String chartName = "ByeElection_For_"+results.getElectionType()+"_"+results.getElectionYear()+"_piechartFor_"+constiName+".png";
						String chartPath = context.getRealPath("/") + "charts\\" + chartName;
						String chartTitle = ""+results.getElectionType()+" - "+results.getElectionYear();
						final DefaultPieDataset dataset = new DefaultPieDataset();
						for(CandidateOppositionVO candRes:candResList){
							String partyName = candRes.getPartyName(); 
							Double votesPercent = new Double(candRes.getVotesPercentage());
							log.debug(" party Name ==== "+partyName+", votes Percent = "+votesPercent);	
							partiesInChart.add(partyName);					
							dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);
						}
						results.setChartName(chartName);
						ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , null,true,300,300);
					}
					
				}
				else 
					results.setChartName(null);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" +ex);
		}
	}
	
	private Double getByeElectionVotesPercentage(String constiName)
	{
		Double pollingPercentage = null;
		if(constiName.equalsIgnoreCase(Constants.SIRCILLA)){
			pollingPercentage = Constants.SIRCILLA_PRESENET_VOTES_PERCENT;
		}
		
		else if(constiName.equalsIgnoreCase(Constants.SIRPUR)){
			pollingPercentage = Constants.SIRPUR_PRESENET_VOTES_PERCENT;	
		}
		
       else if(constiName.equalsIgnoreCase(Constants.CHENNUR)){
    	   pollingPercentage = Constants.CHENNUR_PRESENET_VOTES_PERCENT;
		}
		
       else if(constiName.equalsIgnoreCase(Constants.MANCHERIAL)){
    	   pollingPercentage = Constants.MANCHERIAL_PRESENET_VOTES_PERCENT;
		   }
       else if(constiName.equalsIgnoreCase(Constants.YELLAREDDY)){
    	   pollingPercentage = Constants.YELLAREDDY_PRESENET_VOTES_PERCENT;
  	   }
       else if(constiName.equalsIgnoreCase(Constants.NIZAMABAD_URBAN)){
    	   pollingPercentage = Constants.NIZAMABAD_URBAN_PRESENET_VOTES_PERCENT;
 	   }
	
       else if(constiName.equalsIgnoreCase(Constants.KORATLA)){
    	   pollingPercentage = Constants.KORATLA_PRESENET_VOTES_PERCENT;
	   }
       else if(constiName.equalsIgnoreCase(Constants.DHARMAPURI)){
    	   pollingPercentage = Constants.DHARMAPURI_PRESENET_VOTES_PERCENT;
	       }
       else if(constiName.equalsIgnoreCase(Constants.VEMULAWADA)){
    	   pollingPercentage = Constants.VEMULAWADA_PRESENET_VOTES_PERCENT;
  	   }
       else if(constiName.equalsIgnoreCase(Constants.HUZURABAD)){
    	   pollingPercentage = Constants.HUZURABAD_PRESENET_VOTES_PERCENT;
       }
       else if(constiName.equalsIgnoreCase(Constants.SIDDIPET)){
    	   pollingPercentage = Constants.SIDDIPET_PRESENET_VOTES_PERCENT;
       }
       else if(constiName.equalsIgnoreCase(Constants.WARANGAL_WEST)){
    	   pollingPercentage = Constants.WARANGAL_WEST_PRESENET_VOTES_PERCENT;
       }else
    	   pollingPercentage = 0.0;
		return pollingPercentage;
	}
	

	@SuppressWarnings({ "unchecked", "unchecked" })
	public String getMandalsVotingTrendz() throws Exception
	{
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Long districtId = new Long(jObj.getString("districtId"));
		Long constiId =  new Long(jObj.getString("constituencyId"));
		String constiName = jObj.getString("constiName");
		
		biElectionResultsMainVO  = biElectionPageService.getMandalWiseResultsForSelectedPartiesInConstituency(constiId);		
		/*DataTransferVO charts = constituencyPageService.getPreviousAndPresentElectionYearsGraphsForAConstituency(constiId);
		String allElecPartiesGraph = "ByeElectionConsti_"+constiId+"_allElecPartiesGraph.png";
		String pres2010LineChartName = null;
		if(charts.getLatestYearChart() != null)
		pres2010LineChartName = "ByeElectionConsti_"+constiId+"_mandalwiseChartYear_2010.png";
		String presLineChartName = "ByeElectionConsti_"+constiId+"_mandalwiseChartYear_"+IConstants.PRESENT_ELECTION_YEAR+".png";
		String prevLineChartName = "ByeElectionConsti_"+constiId+"_mandalwiseChartYear_"+IConstants.PREVIOUS_ELECTION_YEAR+".png";
		biElectionResultsMainVO.setAssemblyResultsChartForPresentYear(presLineChartName);
		biElectionResultsMainVO.setAssemblyResultsChartForPreviousYear(prevLineChartName);
		biElectionResultsMainVO.setAssemblyResultsChartForLatestYear(pres2010LineChartName);
		
		Set<String> partiesInChart = null;
		CategoryDataset dataset = null;
		String allElecPartiesGraphPath = context.getRealPath("/") + "charts\\" + allElecPartiesGraph;
		String pres2010ChartPath = context.getRealPath("/") + "charts\\" + pres2010LineChartName;
		String presChartPath = context.getRealPath("/") + "charts\\" + presLineChartName;
		String prevChartPath = context.getRealPath("/") + "charts\\" + prevLineChartName;
		
		if(biElectionResultsMainVO.getAllPartiesElecInfo() != null){
			partiesInChart = new LinkedHashSet<String>();
			ChartProducer.createLineChartWithThickness("All Parties Performance In All Elections Of "+constiName+" Assembly Constituency",
					"Elections", "Percentages",createDataSetForRuralUrban(biElectionResultsMainVO.getAllPartiesElecInfo().
							getAllPartiesAllElectionResults(), partiesInChart),	allElecPartiesGraphPath,400,800,
							ChartUtils.getLineChartColors(partiesInChart),true);	
		}
		
		partiesInChart = new LinkedHashSet<String>();
		dataset = createDataSetForPresentYear((ConstituencyRevenueVillagesVO)charts.getPresentYearChart(), partiesInChart);
		
		if(dataset.getColumnCount() > 1)
			ChartProducer.createLineChartWithThickness("Mandalwise Results For "+constiName+" Assembly In "+IConstants.PRESENT_ELECTION_YEAR,
				"Mandals", "Percentages",dataset, presChartPath,400,800,ChartUtils.getLineChartColors(partiesInChart),true);
		else
			ChartProducer.create3DBarChartWithInputParams("Mandalwise Results For "+constiName+" Assembly In "+IConstants.PRESENT_ELECTION_YEAR,
					null, "Mandal","Percentages", null, dataset, presChartPath, 800, 400, ChartUtils.getLineChartColors(partiesInChart),true);
		
		//for 2010 bye election
		
		if(charts.getLatestYearChart() != null){
		partiesInChart = new LinkedHashSet<String>();
		dataset = createDataSetForPresentYear((ConstituencyRevenueVillagesVO)charts.getLatestYearChart(), partiesInChart);
		
		if(dataset.getColumnCount() > 1)
			ChartProducer.createLineChart("Mandalwise Results For "+constiName+" Assembly In 2010",
				"Mandals", "Percentages",dataset, pres2010ChartPath,400,800,ChartUtils.getLineChartColors(partiesInChart),true);
		else
			ChartProducer.create3DBarChartWithInputParams("Mandalwise Results For "+constiName+" Assembly In 2010",
					null, "Mandal","Percentages", null, dataset, pres2010ChartPath, 800, 400, ChartUtils.getLineChartColors(partiesInChart),true);
		}
		
		partiesInChart = new LinkedHashSet<String>();
		if(((List<PartyResultVO>)charts.getPreviousYearChart()).size() > 0){
			dataset = createDataSetForPreviousYear((List<PartyResultVO>)charts.getPreviousYearChart(), partiesInChart);
			if(dataset.getColumnCount() > 1)
			ChartProducer.createLineChartWithThickness("Mandalwise Results For "+constiName+" Assembly Region In "+IConstants.PREVIOUS_ELECTION_YEAR,
					"Mandals", "Percentages",createDataSetForPreviousYear((List<PartyResultVO>)charts.getPreviousYearChart(), partiesInChart), 
					prevChartPath,400,800,ChartUtils.getLineChartColors(partiesInChart),true);
			else
			ChartProducer.create3DBarChartWithInputParams("Mandalwise Results For "+constiName+" Assembly Region In "+IConstants.PREVIOUS_ELECTION_YEAR,
					null, "Mandal","Percentages", null, dataset, prevChartPath, 800, 400, ChartUtils.getLineChartColors(partiesInChart),true);
				
		}
		
		if(biElectionResultsMainVO.getBiElectionResultsMainVO() != null){
			biElectionResultsMainVO.setChartsListForElectionTypes(getElectionResultsPieChart(constiId, constiName, biElectionResultsMainVO.getBiElectionResultsMainVO()));
		}
		else
			//getPieChartsInfo(biElectionResultsMainVO, constiId);*/
		constituencyVO = getVotersShareInMandalsPieChart(constiId);
		biElectionResultsMainVO.setConstituencyVO(constituencyVO);
		
		return Action.SUCCESS;
	}
	
	private CategoryDataset createDataSetForRuralUrban(List<PartyResultVO> partiesResults, Set<String> partiesInChart){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PartyResultVO party:partiesResults)
			for(ElectionResultVO election:party.getElectionWiseResults())
				if(!election.getPercentage().equalsIgnoreCase("-1")){
					partiesInChart.add(party.getPartyName());
					dataset.addValue(new BigDecimal(election.getPercentage()), party.getPartyName(), election.getElectionYearAndType());	
				}
		return dataset;
	}
	
	private CategoryDataset createDataSetForPreviousYear(
			List<PartyResultVO> previousYearChart, Set<String> partiesInChart) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Collections.sort(previousYearChart, new PartyResultVOPercentComparator());
		for(PartyResultVO resultVO:previousYearChart){
			partiesInChart.add(resultVO.getPartyName());
			dataset.addValue(new BigDecimal(resultVO.getVotesPercent()), resultVO.getPartyName(), resultVO.getTehsilName());
		}
		return dataset;
	}

	private CategoryDataset createDataSetForPresentYear(
			ConstituencyRevenueVillagesVO presentYearChart, Set<String> partiesInChart) {
		 final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	      List<PartyElectionResultVO> pariesInfo = null;
	      List<CandidatePartyInfoVO> candidatesInfo = presentYearChart.getCandidateNamePartyAndStatus();
	  	  for(ConstituencyOrMandalWiseElectionVO constiInfoVO:presentYearChart.getConstituencyOrMandalWiseElectionVO()){
	  		pariesInfo = constiInfoVO.getPartyElectionResultVOs();
	  		for(int i=0; i<pariesInfo.size(); i++){
	  			partiesInChart.add(candidatesInfo.get(i).getParty()+"["+candidatesInfo.get(i).getRank()+"]");
	  			dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty()+"["+candidatesInfo.get(i).getRank()+"]", constiInfoVO.getLocationName());	
	  		}        		
	      }
	  	  
	      return dataset;
	}

	private void getPieChartsInfo(BiElectionResultsMainVO biElectionResultsMainVO, Long constituencyId) {
		
		for(ElectionDataVO dataVO:biElectionResultsMainVO.getUrbanRuralConstiResults())
			for(ConstituencyMandalVO constiMandal:dataVO.getConstituencyMandalInfo())
				if(constiMandal.getIsTotal() == true)
					dataVO.setElectionPieChart(produceChartAndVO(constiMandal.getPartiesReslts(), dataVO, constituencyId));
					
	}

	private String produceChartAndVO(List<PartyInfoVO> partiesReslts, ElectionDataVO elecVO, Long constituencyId) {
		ElectionTypeChartVO chartVO = new ElectionTypeChartVO();
		chartVO.setElectionType(elecVO.getElectionType());
		chartVO.setElectionYear(elecVO.getElectionYear());
		String chartName = "ByeElection_AllElecs"+elecVO.getElectionType()+"_"+elecVO.getElectionYear()+"_piechartFor_Consti_"+constituencyId+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		Color[] colors = new Color[partiesReslts.size()];
		String chartTitle = ""+elecVO.getElectionType()+" - "+elecVO.getElectionYear();
		final DefaultPieDataset dataset = new DefaultPieDataset();
		int j=0;
		for(int i=0; i<partiesReslts.size(); i++ )
		{		
			String partyName = partiesReslts.get(i).getPartyShortName(); 
			Double votesPercent = new Double(partiesReslts.get(i).getPercentageOfVotes().toString());
			log.debug(" party Name ==== "+partyName+", votes Percent = "+votesPercent);	
								
				if(partyName.equals(IConstants.INC))
				{
					colors[j++]=IConstants.INC_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}				
				else
				if(partyName.equals(IConstants.PRP))
				{
					colors[j++]=IConstants.PRP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}			
				else
				if(partyName.equals(IConstants.TDP))
				{
					colors[j++]=IConstants.TDP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}	
				else
				if(partyName.equals(IConstants.TRS))
				{
					colors[j++]=IConstants.TRS_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}										
				else
				if(partyName.equals(IConstants.BJP))
				{
					colors[j++]=IConstants.BJP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}else
				if(partyName.equals(IConstants.OTHERS))
				{
					colors[j++]=IConstants.IND_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}					
					
				dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);	
		}
		chartVO.setChartName(chartName);
		ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , colors,true,300,300);
		return chartName;
	}
	
	//creating dataset and chart for mandal pie chart
	public String getPieChartForMPTCandZPTCAjax(List<TeshilPartyInfoVO> resultsForDataset,String electionType,String electionYear,Long mandalId){
		
		String chartName = "ByeElection_For_"+electionType+"_"+electionYear+"_piechartFor_Mandal_"+mandalId+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		Color[] colors = new Color[resultsForDataset.size()+1];
		String chartTitle = ""+electionType+" - "+electionYear;
		final DefaultPieDataset dataset = new DefaultPieDataset();
		int j=0;
		Boolean flag = false;
		for(int i=0; i<resultsForDataset.size(); i++)
		{		
			String partyName = "";
			if(flag == true){
				partyName = IConstants.OTHERS;
				i--;
			}
			else
			    partyName = resultsForDataset.get(i).getPartyName(); 
			
			if(partyName.equals(IConstants.TRS) || 
					partyName.equals(IConstants.INC) || 
					partyName.equals(IConstants.TDP) ||
					partyName.equals(IConstants.BJP) || 
					partyName.equals(IConstants.OTHERS))
			{
				Double votesPercent = new Double(0);
				if(flag == true)
					votesPercent = new BigDecimal(resultsForDataset.get(0).getOthersPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				else
			        votesPercent = new BigDecimal(resultsForDataset.get(i).getPercentageOfVotesWonByParty()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
			    log.debug(" party Name ==== "+partyName+", votes Percent = "+votesPercent);	
								
				if(partyName.equals(IConstants.INC))
				{
					colors[j++]=IConstants.INC_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}				
				else
				if(partyName.equals(IConstants.PRP))
				{
					colors[j++]=IConstants.PRP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}			
				else
				if(partyName.equals(IConstants.TDP))
				{
					colors[j++]=IConstants.TDP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}	
				else
				if(partyName.equals(IConstants.TRS))
				{
					colors[j++]=IConstants.TRS_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}										
				else
				if(partyName.equals(IConstants.BJP))
				{
					colors[j++]=IConstants.BJP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}else
				if(partyName.equals(IConstants.OTHERS))
				{
					colors[j++]=IConstants.IND_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}					
					
				dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);	
						
			}
			if(i == 0 && flag == false)
				flag = true;
			else flag = false;
		}
		
		ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , colors,true,300,300);
		return chartName;
	}

	public List<ElectionTypeChartVO> getElectionResultsPieChart(Long constiId,String constiName, List<BiElectionResultsVO> biElectionResultsVO)
	{
		List<ElectionTypeChartVO> electionResultsChart = new ArrayList<ElectionTypeChartVO>();
		ElectionTypeChartVO electionTypeChartVOSelectedParties = new ElectionTypeChartVO();
				
		for(BiElectionResultsVO resultsObj :biElectionResultsVO)
		{
			if(resultsObj.getBiElectionResultsVO() != null){
			List<ElectionResultsForMandalVO> results = resultsObj.getBiElectionResultsVO();
			for(ElectionResultsForMandalVO electionResultsForMandalVO:results)
				{
					if(electionResultsForMandalVO.getPartyResultsSum() != null){
						electionTypeChartVOSelectedParties = createPieChartForElectionTypeNElectionYear(constiId,electionResultsForMandalVO,"selectedParties");
						if(electionTypeChartVOSelectedParties != null)
							electionResultsChart.add(electionTypeChartVOSelectedParties);	
					}
				
				}
			}
			else if(resultsObj.getBiElectionResultsVO() == null){
				return null;
			}
		}	
		return electionResultsChart;
	}
	
	public ElectionTypeChartVO createPieChartForElectionTypeNElectionYear(Long constiId,ElectionResultsForMandalVO result,String chartType)
	{		
		String chartName = "Election_Result_"+result.getElectionType()+"_"+result.getElectionYear()+"_"+constiId+"_piechart"+".png";
		String localChart = null;
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;		
		Double otherPartyVotesPercent = 0D;
		ElectionTypeChartVO electionTypeChartVO = new ElectionTypeChartVO();
		String chartTitle = ""+result.getElectionType()+" - "+result.getElectionYear();
		final DefaultPieDataset dataset = new DefaultPieDataset();
		Color[] colors = null;
		
		if(chartType.equalsIgnoreCase("selectedParties"))
			colors = new Color[result.getPartyResultsSum().size()];
		log.debug(" results size ==== "+result.getPartyResultsSum());		
		int j=0;
		for(int i=0; i<result.getPartyResultsSum().size(); i++ )
		{		
			String partyName = result.getPartyResultsSum().get(i).getPartyName(); 
			Double votesPercent = Double.valueOf(result.getPartyResultsSum().get(i).getPercentage());
			log.debug(" party Name ==== "+partyName+", votes Percent = "+votesPercent);	
						
			 if(chartType.equalsIgnoreCase("selectedParties"))
			{
				if(partyName.equalsIgnoreCase(IConstants.INC) || partyName.equalsIgnoreCase(IConstants.TDP) || partyName.equalsIgnoreCase(IConstants.TRS) || partyName.equalsIgnoreCase(IConstants.BJP) 
						|| partyName.equals(IConstants.PRP))
				{				
					if(partyName.equals(IConstants.INC))
					{
						colors[j++]=IConstants.INC_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}				
					else
					if(partyName.equals(IConstants.PRP))
					{
						colors[j++]=IConstants.PRP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}			
					else
					if(partyName.equals(IConstants.TDP))
					{
						colors[j++]=IConstants.TDP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}	
					else
					if(partyName.equals(IConstants.TRS))
					{
						colors[j++]=IConstants.TRS_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}										
					else
					if(partyName.equals(IConstants.BJP))
					{
						colors[j++]=IConstants.BJP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}					
					
					dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);	
				}	
				else
				{
					otherPartyVotesPercent+=votesPercent;
				}
				
			}
		}
		
		if(chartType.equalsIgnoreCase("selectedParties")){			
			BigDecimal	otherPartyVotes = new BigDecimal(otherPartyVotesPercent).setScale(2, BigDecimal.ROUND_HALF_UP);			
			dataset.setValue("Others"+" ["+otherPartyVotes.toString()+"%]",otherPartyVotes);
			colors[j] = IConstants.DEFAULT_COLOR;
			ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , colors,true,300,300);
			localChart = chartName;
		}
		electionTypeChartVO.setChartName(localChart);
		electionTypeChartVO.setElectionType(result.getElectionType());
		electionTypeChartVO.setElectionYear(result.getElectionYear());
		return electionTypeChartVO;
			
	}

	
	
	
	public List<ElectionResultPartyVO> getConstituencyElectionResultsChart(Long constiId){
		
		log.debug(" Inside getConstituencyElectionResultsChart Method ");
		
		List<ElectionResultPartyVO> resultsList = staticDataService.getAllMandalElectionInformationForAConstituency(constiId,0);
		List<ElectionResultPartyVO> resultsMainList = new ArrayList<ElectionResultPartyVO>();
		
		if(resultsList != null && resultsList.size() > 0){
			for(ElectionResultPartyVO result:resultsList){
				resultsMainList.add(getOtherPartiesGroupedResult(result));
			}
		}

		return resultsMainList;
	}
	
	
	
	public ElectionResultPartyVO getOtherPartiesGroupedResult(ElectionResultPartyVO elecResult){
		
		ElectionResultPartyVO electionResult = new ElectionResultPartyVO();
		if(elecResult != null){
			electionResult.setElectionType(elecResult.getElectionType());
			electionResult.setElectionYear(elecResult.getElectionYear());
			
			Long votesEarned = new Long(0);
			Long validVotes = new Long(0);
			Double votesPercent = new Double(0);
			Long totVotesEarned = new Long(0);
			List<CandidateElectionResultVO> candResultsInElection = new ArrayList<CandidateElectionResultVO>();
			
			for(CandidateElectionResultVO candResult:elecResult.getCandidateElectionResultsVO()){
			   	
				//For main party's TDP,TRS,INC,BJP ....
				if(candResult.getPartyName().equalsIgnoreCase(IConstants.INC) 
			   			|| candResult.getPartyName().equalsIgnoreCase(IConstants.TDP) 
			   			|| candResult.getPartyName().equalsIgnoreCase(IConstants.TRS)
			   			|| candResult.getPartyName().equalsIgnoreCase(IConstants.BJP)
			   			|| candResult.getPartyName().equalsIgnoreCase(IConstants.PRP)){
			   		
					candResultsInElection.add(candResult);
			   		
			   		if(candResult.getTotalVotesEarned() != null)
			   		totVotesEarned+=candResult.getTotalVotesEarned();
			   	}
				//For Others
			   	else{
			   		if(candResult.getTotalVotesEarned() != null && candResult.getTotalValidVotes() != null){
			   		votesEarned+=candResult.getTotalVotesEarned();
			   		totVotesEarned+=candResult.getTotalVotesEarned();
			   		}
			   	}
			}
			
			//others results
			if(!votesEarned.equals(new Long(0))){
				CandidateElectionResultVO candResForOthers = new CandidateElectionResultVO();
				candResForOthers.setPartyName("Others");
				candResForOthers.setTotalVotesEarned(votesEarned);
				candResForOthers.setTotalValidVotes(totVotesEarned);
			
				candResForOthers.setVotesPercentage(new BigDecimal(votesEarned.doubleValue()/totVotesEarned.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				candResultsInElection.add(candResForOthers);
			}
			if((elecResult.getElectionType().equals(IConstants.ASSEMBLY_ELECTION_TYPE) 
					|| elecResult.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE)) 
					&& !elecResult.getElectionYear().equals("2008") 
					&& !elecResult.getElectionYear().equals("2006")){
			List<CandidateElectionResultVO> candResultsInElec = staticDataService.getProcessedAlliancePartiesResults(candResultsInElection,elecResult.getElectionType(),elecResult.getElectionYear());
			if(candResultsInElec != null && candResultsInElec.size() > 0)
				candResultsInElection = candResultsInElec;
			}
			electionResult.setCandidateElectionResultsVO(candResultsInElection);
		}
		
	 return electionResult;
	}
	
	public ChartColorsAndDataSetVO createDatasetForChart(List<ElectionDataVO> elecdetails,List<String> partys,List<ElectionResultPartyVO> elecResultsList,Boolean includeAllianc){
		 
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		   Set<Color> colorsSet = new LinkedHashSet<Color>();
		   Collections.sort(elecdetails, new ElectionDataVOComparator());
		   
		   try{
		   //change the position of 2010 AC and 2009 AC
		   if(elecdetails != null && elecdetails.size() > 0){
		   int j=0;
		   Boolean flag = false;
		   for(int i=0;i<elecdetails.size();i++){
			   
			   if(elecdetails.get(i).getElectionType().equals("Parliament") && elecdetails.get(i).getElectionYear().equals("2009") && 
					   elecdetails.get(i+1).getElectionType().equals("Assembly") && elecdetails.get(i+1).getElectionYear().equals("2009")){
				   flag = true;
				   j=i;
				   break;
			   }
		   }
		   if(flag == true){
		   ElectionDataVO vo1 = elecdetails.get(j);
		   ElectionDataVO vo2 = elecdetails.get(j+1);
		   elecdetails.set(j, vo2);
		   elecdetails.set(j+1, vo1);
		   
		   }
		   }
		   }catch(Exception ex){
			   ex.printStackTrace();
			   log.debug(" Exception Raised :" + ex);
		   }
		   
		  	   for(ElectionDataVO electionData:elecdetails){
		  		   
		  		    for(String party:partys){
					   CandidateElectionResultVO candidateElecResults = null;
					   Boolean flag = false;
					   if(includeAllianc == true){						   
						 log.debug(" Allianc True ..." + party + " ... " + electionData.getElectionYear() + " .... " + electionData.getElectionType());
						 if(electionData.getAllianceParties() != null && electionData.getAllianceParties().size() > 0){
						 AlliancePartiesInElection alliancPartysGrp = getPartiesInAlliance(party,electionData.getAllianceParties());
						 if(alliancPartysGrp != null && alliancPartysGrp.getParties().size() > 0)
						  for(SelectOptionVO partysLi:alliancPartysGrp.getParties()){
							 candidateElecResults = getCandidateResultsForChartData(elecResultsList,partysLi.getName(),electionData.getElectionYear(),electionData.getElectionType());
							 if(candidateElecResults != null && candidateElecResults.getVotesPercentage() != null && candidateElecResults.getPartyName() != null && electionData.getElectionYear() != null && electionData.getElectionType() != null){
							  flag = true;	 
							  dataset.addValue(new BigDecimal(candidateElecResults.getVotesPercentage()), alliancPartysGrp.getGroupName(), electionData.getElectionYear()+" "+electionData.getElectionType());
							 }
						   }
						  }
					   }
					   
					  
					   if(includeAllianc == false || flag == false){
						   
						   log.debug(" Allianc False ..." + party + " ... " + electionData.getElectionYear() + " .... " + electionData.getElectionType());
					      candidateElecResults = getCandidateResultsForChartData(elecResultsList,party,electionData.getElectionYear(),electionData.getElectionType());
					   
					    if(candidateElecResults != null && candidateElecResults.getVotesPercentage() != null && candidateElecResults.getPartyName() != null && electionData.getElectionYear() != null && electionData.getElectionType() != null){
						  dataset.addValue(new BigDecimal(candidateElecResults.getVotesPercentage()), candidateElecResults.getPartyName(), electionData.getElectionYear()+" "+electionData.getElectionType());
						  
						  if(IConstants.TDP.equalsIgnoreCase(candidateElecResults.getPartyName()))
  						  {	colorsSet.add(IConstants.TDP_COLOR);
  							log.debug("TDP ADDED");
  						  }else if(IConstants.INC.equalsIgnoreCase(candidateElecResults.getPartyName())){
  							colorsSet.add(IConstants.INC_COLOR);
  							log.debug("INC ADDED");
  						  }
  						  else if(IConstants.TRS.equalsIgnoreCase(candidateElecResults.getPartyName())){
  							colorsSet.add(IConstants.TRS_COLOR);
  							log.debug("TRS ADDED");
  						  }else if(IConstants.BJP.equalsIgnoreCase(candidateElecResults.getPartyName())){
    							colorsSet.add(IConstants.BJP_COLOR);
      							log.debug("BJP ADDED");
      					  } else if(IConstants.PRP.equalsIgnoreCase(candidateElecResults.getPartyName())){
  							colorsSet.add(IConstants.PRP_COLOR);
  							log.debug("BJP ADDED");
      					  } else if("Others".equalsIgnoreCase(candidateElecResults.getPartyName())){
  							colorsSet.add(Color.BLACK);
  							log.debug("Others ADDED");
      					  }
					   }
					   }
				   }
			   }
		  	 chartColorsAndDataSetVO.setDataSet(dataset);
	         chartColorsAndDataSetVO.setColorsSet(colorsSet);	   
     return chartColorsAndDataSetVO;
	}
	
	
	public ChartColorsAndDataSetVO createDatasetForChart(List<ElectionResultPartyVO> elecResultsList,List<SelectOptionVO> electionType){
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		Set<Color> colorsSet = new LinkedHashSet<Color>();
		   
	    List<ElectionResultPartyVO> elecResultsForChart = new ArrayList<ElectionResultPartyVO>();
		   if(elecResultsList != null && elecResultsList.size() > 0 && electionType != null && electionType.size() > 0){
			   for(SelectOptionVO elecTyp:electionType){
				   
				   //Collections.sort(elecResultsList, new ElectionResultPartyVOByElectionType());
				   // candidateElecResults = getCandidateResultsForChartData(elecResultsList,String party,String elecType);
				   for(ElectionResultPartyVO candResults:elecResultsList){
						if(candResults.getElectionType().equalsIgnoreCase(elecTyp.getName())){
							List<CandidateElectionResultVO> candRes = candResults.getCandidateElectionResultsVO();
							if(candRes != null && candRes.size() > 0)
							//Collections.sort(candRes, new CandidateElecResultComparatorByParty());
							for(CandidateElectionResultVO candidateElecResults:candRes){
								ElectionResultPartyVO resultVOForChart = new ElectionResultPartyVO();
								
								resultVOForChart.setVotesShareRange(new BigDecimal(candidateElecResults.getVotesPercentage()).toString());
								resultVOForChart.setPartyShortName(candidateElecResults.getPartyName());
								if(candResults.getElectionType().equals(IConstants.MPTC_ELECTION_TYPE) || 
										candResults.getElectionType().equals(IConstants.ZPTC_ELECTION_TYPE))
								resultVOForChart.setElectionType("."+candResults.getElectionType());
								else
								resultVOForChart.setElectionType(candResults.getElectionType());	
								resultVOForChart.setElectionYear(candResults.getElectionYear());
								
								elecResultsForChart.add(resultVOForChart);
							}
						}
					}
				   
			  }
			   
			   
			   //add to dataset
			   if(elecResultsForChart != null && elecResultsForChart.size() > 0){
				   
				   Collections.sort(elecResultsForChart,new ElectionResultPartyVOByElectionType());
				   Map<ElectionResultPartyVO,List<ElectionResultPartyVO>> chartResMap = new HashMap<ElectionResultPartyVO,List<ElectionResultPartyVO>>();
				   Set<ElectionResultPartyVO> resultSet = null;
				   List<ElectionResultPartyVO> resultList = null;
				   
                   for(ElectionResultPartyVO res:elecResultsForChart){
					   
					   if(StringUtils.contains(res.getElectionType(), '.'))
						   res.setElectionType(StringUtils.remove(res.getElectionType(), '.'));
					   
					   ElectionResultPartyVO elecData = new ElectionResultPartyVO();
					   elecData.setElectionType(res.getElectionType());
					   elecData.setElectionYear(res.getElectionYear());
					   
					   if(chartResMap.isEmpty() || !chartResMap.containsKey(elecData)){
						   List<ElectionResultPartyVO> partyRes = new ArrayList<ElectionResultPartyVO>();
						   partyRes.add(res);
						   chartResMap.put(elecData, partyRes);
					   }
					   else if(chartResMap.containsKey(elecData)){
						   List<ElectionResultPartyVO> partyRes = chartResMap.get(elecData);
						   partyRes.add(res);
						   chartResMap.put(elecData, partyRes);
					   }
                   }
                   
                   
                   
				   try{
					   
					   resultSet = chartResMap.keySet();
					   resultList = new ArrayList<ElectionResultPartyVO>(resultSet);
					   Collections.sort(resultList,new ElectionResultPartyVOByElectionType());
					   
					   Boolean flag = false;
					   int j=0;
					   for(int i=0;i<resultList.size()-1;i++){
						   						   
						   if(resultList.get(i).getElectionType().equalsIgnoreCase("Parliament") && resultList.get(i).getElectionYear().equalsIgnoreCase("2009") && 
								   resultList.get(i+1).getElectionType().equalsIgnoreCase("Assembly") && resultList.get(i+1).getElectionYear().equalsIgnoreCase("2009")){
							   flag = true;
							   j=i;
							   break;
						   }
					   }
					   if(flag == true){
						   ElectionResultPartyVO vo1 = resultList.get(j);
						   ElectionResultPartyVO vo2 = resultList.get(j+1);
						   
						   resultList.set(j, vo2);
						   resultList.set(j+1, vo1);
						   
					   }
				   
				   }catch(Exception ex){
					   ex.printStackTrace();
					   log.debug("Exception Raised :" + ex);
				   }
				   for(ElectionResultPartyVO resList:resultList){
				   for(ElectionResultPartyVO res:chartResMap.get(resList)){
					   
					  /* if(StringUtils.contains(res.getElectionType(), '.'))
						   res.setElectionType(StringUtils.remove(res.getElectionType(), '.'));*/
					   
					   dataset.addValue(new BigDecimal(res.getVotesShareRange()), res.getPartyShortName(), res.getElectionYear()+" "+res.getElectionType());
						  
						  if(IConstants.TDP.equalsIgnoreCase(res.getPartyShortName()))
						  {	colorsSet.add(IConstants.TDP_COLOR);
							log.debug("TDP ADDED");
						  }else if(IConstants.INC.equalsIgnoreCase(res.getPartyShortName())){
							colorsSet.add(IConstants.INC_COLOR);
							log.debug("INC ADDED");
						  }
						  else if(IConstants.TRS.equalsIgnoreCase(res.getPartyShortName())){
							colorsSet.add(IConstants.TRS_COLOR);
							log.debug("TRS ADDED");
						  }else if(IConstants.BJP.equalsIgnoreCase(res.getPartyShortName())){
							colorsSet.add(IConstants.BJP_COLOR);
							log.debug("BJP ADDED");
	 					  } else if(IConstants.PRP.equalsIgnoreCase(res.getPartyShortName())){
								colorsSet.add(IConstants.PRP_COLOR);
								log.debug("BJP ADDED");
	 					  } else if("Others".equalsIgnoreCase(res.getPartyShortName())){
								colorsSet.add(Color.BLACK);
								log.debug("Others ADDED");
	 					  }
						  
						  
				   }
				   }
			   }
		   }
		   chartColorsAndDataSetVO.setDataSet(dataset);
	       chartColorsAndDataSetVO.setColorsSet(colorsSet);
		   
     return chartColorsAndDataSetVO;
	}
	
	
	public AlliancePartiesInElection getPartiesInAlliance(String party,List<AlliancePartiesInElection> alliancList){
		
		if(alliancList != null && alliancList.size() > 0){
		for(AlliancePartiesInElection group:alliancList){
			for(SelectOptionVO parties:group.getParties()){
				if(parties.getName().equalsIgnoreCase(party))
					return group;
			}
		}
		}
	 return null;
	}
	
	public CandidateElectionResultVO getCandidateResultsForChartData(List<ElectionResultPartyVO> elecResultsList,String party,String elecYear,String elecType){
		
		log.debug(" Inside getCandidateResultsForChartData method ...");
		
		CandidateElectionResultVO candidateResults = null;
		if(elecResultsList != null && party != null && elecYear != null && elecType != null){
			candidateResults = new CandidateElectionResultVO();
			for(ElectionResultPartyVO candResults:elecResultsList){
				if(candResults.getElectionType().equalsIgnoreCase(elecType) && candResults.getElectionYear().equalsIgnoreCase(elecYear)){
					List<CandidateElectionResultVO> candRes = candResults.getCandidateElectionResultsVO();
					for(CandidateElectionResultVO candResult:candRes){
						if(candResult.getPartyName().equalsIgnoreCase(party)){
							return candResult;
						}
					}
				}
			}
		}
		
	 return candidateResults;
	}
	
	//for chart manipulation based on selection of AC or PC or MPTC or ZPTC
    public CandidateElectionResultVO getCandidateResultsForChartData(List<ElectionResultPartyVO> elecResultsList,String party,String elecType){
		
		log.debug(" Inside getCandidateResultsForChartData method ...");
		
		CandidateElectionResultVO candidateResults = null;
		if(elecResultsList != null && party != null && elecType != null){
			candidateResults = new CandidateElectionResultVO();
			for(ElectionResultPartyVO candResults:elecResultsList){
				if(candResults.getElectionType().equalsIgnoreCase(elecType)){
					List<CandidateElectionResultVO> candRes = candResults.getCandidateElectionResultsVO();
					for(CandidateElectionResultVO candResult:candRes){
						if(candResult.getPartyName().equalsIgnoreCase(party)){
							return candResult;
						}
					}
				}
			}
		}
		
	 return candidateResults;
	}
	
	public String getLineChartForMandalsAndPartiesInElection(){
		try {
			jObj=new JSONObject(getTask());
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONArray array = jObj.getJSONArray("mandalIds");
		int chartHeight = jObj.getInt("chartHeight");
		int chartWidth = jObj.getInt("chartWidth");
		
		StringBuilder mandalIds = new StringBuilder();
		
		for(int i=0; i<array.length(); i++)
			mandalIds.append(",").append(array.get(i));
		
		if(mandalIds.length() > 0 ){
			List<PartyResultVO> resultsInMandals = constituencyPageService.getMandalsResultsInAnElection(mandalIds.substring(1).toString(), 
					jObj.getString("electionYear"), jObj.getString("electionType"));
			
			chartResultVO = constituencyPageService.getMandalsResultsInAnElectionForChart(mandalIds.substring(1).toString(), 
					jObj.getString("electionYear"), jObj.getString("electionType"));
			if(resultsInMandals.size() > 0){
				mandalsPartiesChart = "AllMandalsAllParties_"+mandalIds+"OfElectionYear_"+jObj.getString("electionYear")+
													"ElectionType_"+jObj.getString("electionType")+".png";
				String chartPath = context.getRealPath("/") + "charts\\" + mandalsPartiesChart;
				String title = "Mandals Wise Assembly "+jObj.getString("electionYear")+" Election Results For All Parties";
				Set<String> partiesInChart = new LinkedHashSet<String>();
				CategoryDataset dataset = createDataSetForLineChart(resultsInMandals, partiesInChart);
				if(dataset.getColumnCount()>1)
				{
					log.debug("dataset.getColumnCount():::::::::::::::::::::::"+dataset.getColumnCount());
					ChartProducer.createLineChartWithThickness(title,"Mandals", "Percentages",dataset, chartPath,chartHeight,chartWidth,ChartUtils.getLineChartColors(partiesInChart),true);
				} else 	
					{
						log.debug("dataset.getColumnCount():::::::::::::::::::::::"+dataset.getColumnCount());
						ChartProducer.create3DBarChartWithInputParams(title, null, "Mandal","Percentages", null, dataset, chartPath, chartWidth, chartHeight, ChartUtils.getLineChartColors(partiesInChart),true);
					}
			}	
			
			chartResultVO.setChartPath(mandalsPartiesChart);
		}
			
		return SUCCESS;
	}
	
	private CategoryDataset createDataSetForLineChart(List<PartyResultVO> resultsInMandals, Set<String> partiesInChart){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PartyResultVO party:resultsInMandals){
			if(party.getVotesPercent().equalsIgnoreCase("0.0"))
				continue;
			dataset.addValue(new BigDecimal(party.getVotesPercent()), party.getPartyName(), party.getConstituencyName());
			partiesInChart.add(party.getPartyName());
		}
		return dataset;
	}
	
	public String getConstVotingTrendzChart() throws Exception
	{
		String chartNam = "";
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String constiId = jObj.getString("constituencyId");
		String constiName = jObj.getString("constituencyName"); 
		String includeAlliance = jObj.getString("alliances"); 
		
		List<ElectionDataVO> electnDataList = new ArrayList<ElectionDataVO>();
		List<String> partys = new ArrayList<String>();
		JSONArray electionData = jObj.getJSONArray("electionTypeArr");
		
		int listSize = electionData.length();
		for(int i = 0; i< listSize; i++)
		{
			JSONObject elecObj = electionData.getJSONObject(i);
			
			String elecType = elecObj.getString("type");
			String elecYear = elecObj.getString("year");
		
			ElectionDataVO elecData = new ElectionDataVO();
			elecData.setElectionType(elecType);
			elecData.setElectionYear(elecYear);
			
			List<AlliancePartiesInElection> allianceParties = staticDataService.getAlliancGroupAndPartiesInAnElection(elecType,elecYear);
			if(allianceParties != null && allianceParties.size() > 0){
				elecData.setAllianceParties(allianceParties);
			}
			
			electnDataList.add(elecData);
			//chartNam = chartNam + "_" + elecType + "_" + elecYear;
			log.debug("Election Type " + elecType + "Year " + elecYear);
		}	
		
        JSONArray partyArr = jObj.getJSONArray("partiesArr");
		String partyNames[] = new String[partyArr.length()];
        
		int parListSize = partyArr.length();
		for(int i = 0; i< parListSize; i++)
		{
			//JSONObject partyObj = partyArr.getJSONObject(i);
			partyNames[i] = (String)partyArr.get(i);
		  
		}
		for(int j=0;j<partyNames.length;j++){
			partys.add(partyNames[j]);
			log.debug("Party :" + partyNames[j]);
			chartNam = chartNam + "_" + partyNames[j];
		}
		chartNam = chartNam + "_and_" + electnDataList.size() +" Elections" + "IncludeAlliance" + includeAlliance.toString();
		
        List<ElectionResultPartyVO> electionResList = getConstituencyElectionResultsChart(new Long(constiId));
        
        if(electionResList != null && electionResList.size() > 0){
			
        	  String chartTitle = "";
        	  if(new Boolean(includeAlliance) == true)
        		  chartTitle = " All Parties Performance In "+constiName + " Constituency With Alliances";
        	  else if(new Boolean(includeAlliance) == false)
        		  chartTitle = " All Parties Performance In "+constiName + " Constituency ";
			  String chartName = "constituencyElectionsResults"+"_"+constiName+"_"+constiId+"_"+chartNam+".png";
			  String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			  chartColorsAndDataSetVO = createDatasetForChart(electnDataList,partys,electionResList,new Boolean(includeAlliance));
			  ChartProducer.createLineChartWithThickness(chartTitle, "Election", "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath,550,950,new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),true);
			  
			  chartColorsAndDataSetVO.setChartName(chartName);
		}
               
	  return Action.SUCCESS;
	}


    public ConstituencyVO getVotersShareInMandalsPieChart(Long constituencyId){
		
		constituencyDetails = new ConstituencyInfoVO();
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId); 
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		List<VotersWithDelimitationInfoVO> votersInfoVO = new ArrayList<VotersWithDelimitationInfoVO>();
		
		String pieChart = "";
		String pieChartPath = "";
		String title = "";
		String[] chartNames = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String[] extraInfo = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		int i=0;
		
		for(VotersWithDelimitationInfoVO votersInMandalOrAC:constituencyVO.getAssembliesOfParliamentInfo()){
			if(votersInMandalOrAC.getVotersInfoForMandalVO().size() == 0)
				continue;
			log.debug("Mandal Id in Action::::::::::::::::::::::::::"+votersInMandalOrAC.getVotersInfoForMandalVO().get(0).getMandalId());
			
			pieChart = votersInMandalOrAC.getYear()+"_Voters Info for Constituency_"+constituencyVO.getId()+"In Bi-Elections"+".png";
			pieChartPath = context.getRealPath("/")+ "charts\\" + pieChart;
			if(votersInMandalOrAC.getYear().equalsIgnoreCase(IConstants.DELIMITATION_YEAR.toString())){
				if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
					title = "Each Mandal Voters Share* After Delimitation";
				else
					title = "Each Assembly Voters Share* After Delimitation";
				extraInfo[i] = "* Based On 2009 Elections Data";
			}
			else{
				if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
					title = "Each Mandal Voters Share** Before Delimitation";
				else
					title = "Each Assembly Voters Share** Before Delimitation";
				extraInfo[i] = "** Based On 2004 Elections Data";
			}
			
				
			if(votersInMandalOrAC.getVotersInfoForMandalVO().size() > 0)
				ChartProducer.createLabeledPieChart(title, createPieDatasetForVoters(votersInMandalOrAC.getVotersInfoForMandalVO()), pieChartPath, null,true,450,450);
				
			    votersInfoForMandals = getMandalsVotesShare(votersInMandalOrAC.getVotersInfoForMandalVO(),votersInMandalOrAC.getYear());
			    if(votersInfoForMandals != null){
			    	votersInfoVO.add(votersInfoForMandals);
			    }
			
			chartNames[i++] = pieChart;
			
		}
		constituencyVO.setAssembliesOfParliamentInfo(votersInfoVO);
		constituencyVO.setPieChartNames(chartNames);
		constituencyVO.setExtraInfo(extraInfo);
		
	 return constituencyVO;
	}
    
    private DefaultPieDataset createPieDatasetForVoters(List<VotersInfoForMandalVO> votersInfoForMandalVO) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		Long totalVotes = 0l;
		BigDecimal percentage;
		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO)
			totalVotes += new Long(votersInMandalOrAC.getTotalVoters());

		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO){
			percentage = new BigDecimal(new Long(votersInMandalOrAC.getTotalVoters())*100.0/totalVotes).setScale(2,BigDecimal.ROUND_HALF_UP);
			dataset.setValue(votersInMandalOrAC.getMandalName()+" ["+percentage.toString()+"%]",percentage);
		}
			
		return dataset;
	}
    
    public VotersWithDelimitationInfoVO getMandalsVotesShare(List<VotersInfoForMandalVO> votersInfoForMandalVO,String year){
    	
    	VotersWithDelimitationInfoVO votersInfo = null;
    	if(votersInfoForMandalVO != null && votersInfoForMandalVO.size() > 0){
    		votersInfo = new VotersWithDelimitationInfoVO();
    		Long totalVotes = 0l;
    		BigDecimal percentage;
    		
    		votersInfo.setYear(year);
    		List<VotersInfoForMandalVO> votersInfoForMandal = new ArrayList<VotersInfoForMandalVO>();
    		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO)
    			totalVotes += new Long(votersInMandalOrAC.getTotalVoters());

    		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO){
    			percentage = new BigDecimal(new Long(votersInMandalOrAC.getTotalVoters())*100.0/totalVotes).setScale(2,BigDecimal.ROUND_HALF_UP);
    			//dataset.setValue(votersInMandalOrAC.getMandalName()+" ["+percentage.toString()+"%]",percentage);
    			VotersInfoForMandalVO votersInf = new VotersInfoForMandalVO();
    			votersInf.setMandalId(votersInMandalOrAC.getMandalId());
    			votersInf.setMandalName(votersInMandalOrAC.getMandalName());
    			votersInf.setPercent(percentage.toString());
    			votersInf.setTotVoters(new BigDecimal(votersInMandalOrAC.getTotalVoters()));
    			votersInfoForMandal.add(votersInf);
    		}	
    		Collections.sort(votersInfoForMandal, new VotersInfoForMandalVOComparator());
    		votersInfo.setVotersInfoForMandalVO(votersInfoForMandal);
    	}
     return votersInfo;
    }
    
    public String getAllPartiesVotesSharingInMandal()
    {
    	String param=null;			    
		param = getTask();
		
    	try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long tehsilId = jObj.getLong("tehsilId");
		int chartHeight = jObj.getInt("chartHeight");
		int chartWidth = jObj.getInt("chartWidth");
		String tehsilName = jObj.getString("tehsilName");
		partywiseVotesDetailsForMandal = partyBoothWiseResultsService.getAllElectionsResultsInAMandal(tehsilId);
		String chartName = "AllElectionsInMandal_ForByElecPage_"+tehsilId+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		Set<String> partiesInChart = new LinkedHashSet<String>();
		partywiseVotesDetailsForMandal.setChartName(chartName);
		ChartProducer.createLineChartWithThickness("Different Parties Performance In All Elections Of "+tehsilName+" Mandal", 
		"Elections", "Percentages",createDataSetForMandal(partywiseVotesDetailsForMandal.getAllPartiesAllElectionResults(), partiesInChart), 
		chartPath,400,950,ChartUtils.getLineChartColors(partiesInChart),true);
		
		int i=0;
		for(PartyResultVO party:partywiseVotesDetailsForMandal.getAllPartiesAllElectionResults()){
			if(party.getPartyName().equalsIgnoreCase(IConstants.OTHERS)){
				partywiseVotesDetailsForMandal.getAllPartiesAllElectionResults().remove(i);
				break;
			}
			i++;
		}

    	return SUCCESS;
    }
    
    private CategoryDataset createDataSetForMandal(
			List<PartyResultVO> allPartiesAllElectionResults,
			Set<String> partiesInChart) {
    	final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
    	List<ElectionResultVO> partiesElectionResults = new ArrayList<ElectionResultVO>();
        ElectionResultVO partiesElecResultForGraph = null;
        
    	for(PartyResultVO partyResultVO:allPartiesAllElectionResults)
         	for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
         		if(result.getElectionType() == null || result.getElectionYear() == null || Float.parseFloat(result.getPercentage()) < 0)
         			continue;
         		partiesElecResultForGraph = new ElectionResultVO();
         		partiesElecResultForGraph.setPercentage(result.getPercentage());
         		partiesElecResultForGraph.setElectionYear(result.getElectionYearAndType());
         		partiesElecResultForGraph.setPartyName(partyResultVO.getPartyName());
         		partiesElectionResults.add(partiesElecResultForGraph);
         	}
         
         Collections.sort(partiesElectionResults, new ElectionResultComparator());
         
         for(ElectionResultVO graphInfo:partiesElectionResults){
         	partiesInChart.add(graphInfo.getPartyName());
         	if(StringUtils.contains(graphInfo.getElectionYear(), '.'))
         		graphInfo.setElectionYear(StringUtils.remove(graphInfo.getElectionYear(), '.'));
         	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyName(),
            			graphInfo.getElectionYear());	
         }
    	
		return dataset;
	}
    
    public List<PartyResultsInfoVO> getContestingCandidateDetails(String constituencyName){
    	
    	List<PartyResultsInfoVO> contestingCand = new ArrayList<PartyResultsInfoVO>();
    	if(constituencyName != null){
    		
    		PartyResultsInfoVO resultVOOne = new PartyResultsInfoVO();
    		if(constituencyName.equalsIgnoreCase(Constants.NIZAMABAD_URBAN))
    			resultVOOne.setPartyName(IConstants.BJP);
    		else
    		    resultVOOne.setPartyName(IConstants.TRS);
    		PartyResultsInfoVO resultVOTwo = new PartyResultsInfoVO();
    		resultVOTwo.setPartyName(IConstants.INC);
    		PartyResultsInfoVO resultVOThr = new PartyResultsInfoVO();
    		resultVOThr.setPartyName(IConstants.TDP);
    		
    		if(constituencyName.equalsIgnoreCase(Constants.SIRCILLA)){
    			
    			resultVOOne.setCandidateName(Constants.SIRCILLA_TRS);
    			resultVOTwo.setCandidateName(Constants.SIRCILLA_INC);
    			resultVOThr.setCandidateName(Constants.SIRCILLA_TDP);
    			
    		}
    		
    		else if(constituencyName.equalsIgnoreCase(Constants.SIRPUR)){
    			
    			resultVOOne.setCandidateName(Constants.SIRPUR_TRS);
    			resultVOTwo.setCandidateName(Constants.SIRPUR_INC);
    			resultVOThr.setCandidateName(Constants.SIRPUR_TDP);
    		}
    		
           else if(constituencyName.equalsIgnoreCase(Constants.CHENNUR)){
    			
    			resultVOOne.setCandidateName(Constants.CHENNUR_TRS);
    			resultVOTwo.setCandidateName(Constants.CHENNUR_INC);
    			resultVOThr.setCandidateName(Constants.CHENNUR_TDP);
    		}
    		
           else if(constituencyName.equalsIgnoreCase(Constants.MANCHERIAL)){
   			
   			resultVOOne.setCandidateName(Constants.MANCHERIAL_TRS);
   			resultVOTwo.setCandidateName(Constants.MANCHERIAL_INC);
   			resultVOThr.setCandidateName(Constants.MANCHERIAL_TDP);
   		   }
           else if(constituencyName.equalsIgnoreCase(Constants.YELLAREDDY)){
      			
      			resultVOOne.setCandidateName(Constants.YELLAREDDY_TRS);
      			resultVOTwo.setCandidateName(Constants.YELLAREDDY_INC);
      			resultVOThr.setCandidateName(Constants.YELLAREDDY_TDP);
      	   }
           else if(constituencyName.equalsIgnoreCase(Constants.NIZAMABAD_URBAN)){
     			
     			resultVOOne.setCandidateName(Constants.NIZAMABAD_URBAN_BJP);
     			resultVOTwo.setCandidateName(Constants.NIZAMABAD_URBAN_INC);
     			resultVOThr.setCandidateName(Constants.NIZAMABAD_URBAN_TDP);
     	   }
    	
           else if(constituencyName.equalsIgnoreCase(Constants.KORATLA)){
    			
    			resultVOOne.setCandidateName(Constants.KORATLA_TRS);
    			resultVOTwo.setCandidateName(Constants.KORATLA_INC);
    			resultVOThr.setCandidateName(Constants.KORATLA_TDP);
    	   }
           else if(constituencyName.equalsIgnoreCase(Constants.DHARMAPURI)){
   			
   			resultVOOne.setCandidateName(Constants.DHARMAPURI_TRS);
   			resultVOTwo.setCandidateName(Constants.DHARMAPURI_INC);
   			resultVOThr.setCandidateName(Constants.DHARMAPURI_TDP);
   	       }
           else if(constituencyName.equalsIgnoreCase(Constants.VEMULAWADA)){
      			
  			resultVOOne.setCandidateName(Constants.VEMULAWADA_TRS);
  			resultVOTwo.setCandidateName(Constants.VEMULAWADA_INC);
  			resultVOThr.setCandidateName(Constants.VEMULAWADA_TDP);
      	   }
           else if(constituencyName.equalsIgnoreCase(Constants.HUZURABAD)){
     			
     			resultVOOne.setCandidateName(Constants.HUZURABAD_TRS);
     			resultVOTwo.setCandidateName(Constants.HUZURABAD_INC);
     			resultVOThr.setCandidateName(Constants.HUZURABAD_TDP);
           }
           else if(constituencyName.equalsIgnoreCase(Constants.SIDDIPET)){
     			
     			resultVOOne.setCandidateName(Constants.SIDDIPET_TRS);
     			resultVOTwo.setCandidateName(Constants.SIDDIPET_INC);
     			resultVOThr.setCandidateName(Constants.SIDDIPET_TDP);
           }
           else if(constituencyName.equalsIgnoreCase(Constants.WARANGAL_WEST)){
    			
    			resultVOOne.setCandidateName(Constants.WARANGAL_WEST_TRS);
    			resultVOTwo.setCandidateName(Constants.WARANGAL_WEST_INC);
    			resultVOThr.setCandidateName(Constants.WARANGAL_WEST_TDP);
           }
    		contestingCand.add(resultVOOne);
    		contestingCand.add(resultVOTwo);
    		contestingCand.add(resultVOThr);
    	}
     return contestingCand;
    }
    public String getMandalLocalElections()
    {
    	String param=null;			    
		param = getTask();
		
    	try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long tehsilId = jObj.getLong("tehsilId");
		String electionType = jObj.getString("electionType");
		String electionYear = jObj.getString("electionYear");
		zptcMptcResultsInMandal = partyBoothWiseResultsService.getMPTCandZPTCResultsInAMandalForAElection(tehsilId, electionType, electionYear);
		String chartName = getPieChartForMPTCandZPTCAjax(zptcMptcResultsInMandal, electionType, electionYear, tehsilId);
		if(zptcMptcResultsInMandal.size()>0)
			zptcMptcResultsInMandal.get(0).setChartName(chartName);
		return SUCCESS;
    }
    
    public String getUrbanRuralResults(){
    	try {
			jObj=new JSONObject(getTask());
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constituencyId"); 
		JSONArray partiesJson = jObj.getJSONArray("partiesArr");
		JSONArray elecTypeAndYearJson = jObj.getJSONArray("electionTypeArr");
		JSONArray elecTypeJson = jObj.getJSONArray("selectedElectionArr");
		Boolean isElecTypeOnly = jObj.getBoolean("isElectionType");
		Boolean isAlliance = jObj.getBoolean("alliances");
		String constiName = jObj.getString("constituencyName");
		String chartName = jObj.getString("chartName");
		JSONObject elecObj = null;
		Set<String> parties = new HashSet<String>();
		Set<String> elecTypeOrYear = new HashSet<String>();
		String partyName = "";
		String type = "";
		if((!isElecTypeOnly) && elecTypeAndYearJson != null){
			for(int i = 0; i < elecTypeAndYearJson.length(); i++)
			{
				elecObj = elecTypeAndYearJson.getJSONObject(i);
				String elecType = elecObj.getString("type");
				String elecYear = elecObj.getString("year");
				elecTypeOrYear.add(elecYear+" "+elecType);
			}	
		}else if((isElecTypeOnly) && elecTypeJson != null){
			for(int i = 0; i < elecTypeJson.length(); i++)
			{
				type = (String)elecTypeJson.get(i);
				if(type.equalsIgnoreCase("All")){
					elecTypeOrYear = new HashSet<String>();
					break;
				}
				elecTypeOrYear.add(type);
			}	
		}
		
		for(int i = 0; i < partiesJson.length(); i++){
			partyName = (String)partiesJson.get(i);
			if(partyName.equalsIgnoreCase("Others"))
				parties.add(IConstants.OTHERS);
			else
				parties.add(partyName);
		}
			
		
		urbanRuralResultsVO = biElectionPageService.getResultsOfRuralUrbanAreaBeasedOnSelection(constituencyId, parties, elecTypeOrYear, isElecTypeOnly, isAlliance);
		
		String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		Set<String> partiesInChart = new LinkedHashSet<String>();

		if(urbanRuralResultsVO.getAllPartiesAllElectionResults().size() > 0){
			ChartProducer.createLineChartWithThickness("Different Parties Performance In Different Elections Of "+constiName+
					" Assembly Constituency Region","Elections", "Percentages",
					createDatasetForPartiesResult(urbanRuralResultsVO.getAllPartiesAllElectionResults(), partiesInChart), 
					chartPath,500,950,ChartUtils.getLineChartColors(partiesInChart),true);
			urbanRuralResultsVO.setChartName(chartName);	
		}

	   	return SUCCESS;
    }

	private CategoryDataset createDatasetForPartiesResult(
			List<PartyResultVO> allPartiesAllElectionResults, Set<String> partiesInChart) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(PartyResultVO party:allPartiesAllElectionResults)
        	for(ElectionResultVO election:party.getElectionWiseResults()){
        		if(!election.getPercentage().equalsIgnoreCase("-1")){
        			partiesInChart.add(party.getPartyName());
        			dataset.addValue(new BigDecimal(election.getPercentage()), party.getPartyName(),
        					election.getElectionYearAndType());
        		}
        	}
      
		return dataset;
	}

	public String buildBoothwiseElectionsResultsForConstituency(){
		try {
			jObj=new JSONObject(getTask());
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constiId"); 
		ConstituencyVO constituencyInfo = partyBoothWiseResultsService.getBoothwiseResultsOfTwoElectionsForAConstituency(constituencyId);
		partiesElecsResults = constituencyInfo.getPartiesCombinedResults();
		return SUCCESS;
	}
	
}
