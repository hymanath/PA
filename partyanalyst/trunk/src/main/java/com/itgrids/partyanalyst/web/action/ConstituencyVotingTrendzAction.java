package com.itgrids.partyanalyst.web.action;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyVillageLevelAnalysisVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.ElectionTypeChartVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionDataVOComparator;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyResultVOComparator;
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
	List<BiElectionResultsVO> biElectionResultsVO;
	private Long zptcElectionId; 
	private Long mptcElectionId;
	private String mptcElectionType,zptcElectionType;
	private List<SelectOptionVO> zptcElectionYears;
	private List<SelectOptionVO> mptcElectionYears;
	private String muncipalityElectionType,corporationElectionType;
	private Long muncipalityElectionTypeId,corporationElectionTypeId;
	
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


	public String getMuncipalityElectionType() {
		return muncipalityElectionType;
	}

	public void setMuncipalityElectionType(String muncipalityElectionType) {
		this.muncipalityElectionType = muncipalityElectionType;
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

	public List<BiElectionResultsVO> getBiElectionResultsVO() {
		return biElectionResultsVO;
	}

	public void setBiElectionResultsVO(List<BiElectionResultsVO> biElectionResultsVO) {
		this.biElectionResultsVO = biElectionResultsVO;
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
		String param=null;			    
		param = getTask();
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		String[] arr = new String[4];
		Long constiId =  new Long(jObj.getString("constituencyId"));		
		votesSharing = staticDataService.getPartyVotesPercentageInAConstituency(constiId,jObj.getString("choice"),arr);
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
		
		constituencyOverView = staticDataService.getConstituencyOverview(constiId,constiName);
		
		return Action.SUCCESS;
	}
	

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
		
		
		biElectionResultsVO = biElectionPageService.getMandalWiseResultsForSelectedPartiesInConstituency(constiId);
		
		biElectionResultsMainVO = new BiElectionResultsMainVO();
		biElectionResultsMainVO.setBiElectionResultsMainVO(biElectionResultsVO);
		biElectionResultsMainVO.setChartsListForElectionTypes(getElectionResultsPieChart(constiId, constiName, biElectionResultsVO));
		
		constituencyVO = getVotersShareInMandalsPieChart(constiId);
		biElectionResultsMainVO.setConstituencyVO(constituencyVO);
		
		//biElectionResultsMainVO.setPartyResults(biElectionPageService.getMandalwiseResultsForAllElectionsForSelectedPartiesInConstituency(constiId, 142L));
		return Action.SUCCESS;
	}
	
	public List<ElectionTypeChartVO> getElectionResultsPieChart(Long constiId,String constiName, List<BiElectionResultsVO> biElectionResultsVO)
	{
		List<ElectionTypeChartVO> electionResultsChart = new ArrayList<ElectionTypeChartVO>();
		ElectionTypeChartVO electionTypeChartVOSelectedParties = new ElectionTypeChartVO();
				
		for(BiElectionResultsVO resultsObj :biElectionResultsVO)
		{
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
			ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , colors,true,250,250);
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
			if(resultsInMandals.size() > 0){
				mandalsPartiesChart = "AllMandalsAllParties_"+mandalIds+"OfElectionYear_"+jObj.getString("electionYear")+
													"ElectionType_"+jObj.getString("electionType")+".png";
				String chartPath = context.getRealPath("/") + "charts\\" + mandalsPartiesChart;
				String title = "Mandals Wise "+jObj.getString("electionYear")+" Election Results For All Parties";
				Set<String> partiesInChart = new LinkedHashSet<String>();
				CategoryDataset dataset = createDataSetForLineChart(resultsInMandals, partiesInChart);
				if(dataset.getColumnCount()>1)
				{
					log.debug("dataset.getColumnCount():::::::::::::::::::::::"+dataset.getColumnCount());
					ChartProducer.createLineChart(title,"Mandals", "Percentages",dataset, chartPath,chartHeight,chartWidth,ChartUtils.getLineChartColors(partiesInChart),true);
				} else 	
					{
						log.debug("dataset.getColumnCount():::::::::::::::::::::::"+dataset.getColumnCount());
						ChartProducer.create3DBarChartWithInputParams(title, null, "Mandal","Percentages", null, dataset, chartPath, chartWidth, chartHeight, ChartUtils.getLineChartColors(partiesInChart));
					}
				
			}	
		}
			
		return SUCCESS;
	}
	
	private CategoryDataset createDataSetForLineChart(List<PartyResultVO> resultsInMandals, Set<String> partiesInChart){
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PartyResultVO party:resultsInMandals){
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
			chartNam = chartNam + "_" + elecType + "_" + elecYear;
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
		chartNam = chartNam + "IncludeAlliance" + includeAlliance.toString();
		
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
			  ChartProducer.createLineChart(chartTitle, "Election", "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath,380,920,new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),true);
			  
			  chartColorsAndDataSetVO.setChartName(chartName);
		}
               
	  return Action.SUCCESS;
	}


    public ConstituencyVO getVotersShareInMandalsPieChart(Long constituencyId){
		
		constituencyDetails = new ConstituencyInfoVO();
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId); 
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		
		
		String pieChart = "";
		String pieChartPath = "";
		String title = "";
		String[] chartNames = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String[] extraInfo = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		int i=0;
		
		for(VotersWithDelimitationInfoVO votersInMandalOrAC:constituencyVO.getAssembliesOfParliamentInfo()){
			log.debug("Mandal Id in Action::::::::::::::::::::::::::"+votersInMandalOrAC.getVotersInfoForMandalVO().get(0).getMandalId());
			if(votersInMandalOrAC.getYear().equalsIgnoreCase(IConstants.DELIMITATION_YEAR.toString())){
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
				//ChartProducer.createProblemsPieChart(title, createPieDatasetForVoters(votersInMandalOrAC.getVotersInfoForMandalVO()), pieChartPath, null,true,260,270);
				votersInfoForMandals = getMandalsVotesShare(votersInMandalOrAC.getVotersInfoForMandalVO(),votersInMandalOrAC.getYear());
			    if(votersInfoForMandals != null){
			    	List<VotersWithDelimitationInfoVO> votersInfoVO = new ArrayList<VotersWithDelimitationInfoVO>();
			    	votersInfoVO.add(votersInfoForMandals);
			    	
			    	constituencyVO.setAssembliesOfParliamentInfo(votersInfoVO);
			    }
			
			//chartNames[i++] = pieChart;
			}
		}
		
		constituencyVO.setPieChartNames(chartNames);
		constituencyVO.setExtraInfo(extraInfo);
		
	 return constituencyVO;
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
		ChartProducer.createLineChart("Different Parties Performance In All Elections Of "+tehsilName+" Mandal", 
		"Elections", "Percentages",createDataSetForMandal(partywiseVotesDetailsForMandal.getAllPartiesAllElectionResults(), partiesInChart), 
		chartPath,chartHeight,chartWidth,ChartUtils.getLineChartColors(partiesInChart),true);
		
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
         		if(result.getElectionType() == null || result.getElectionYear() == null || "-1".equalsIgnoreCase(result.getPercentage()))
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
         	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyName(),
            			graphInfo.getElectionYear());	
         }
    	
    	
		return dataset;
	}

}
