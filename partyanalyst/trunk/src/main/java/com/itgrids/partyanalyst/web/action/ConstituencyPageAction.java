/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
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
import org.jfree.data.general.PieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.CandidateVotingTrendzCharts;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyNominationsVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzOverviewVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.ElectionTypeChartVO;
import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsTrendzVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IElectionTrendzService;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware,ServletContextAware {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	 
	 private Long constituencyId;   
	 private String constituencyName;	 
	 private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	 private ConstituencyInfoVO constituencyDetails;
	 private ConstituencyVO constituencyVO;	 
	 private CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency;
	 private IProblemManagementReportService problemManagementReportService;
	 private List<ProblemBeanVO> problemBean;
	 private ElectionTrendzReportVO electionTrendzReportVO;
	 private CandidateVotingTrendzCharts candidateVotingTrendzCharts;
	 private IStaticDataService staticDataService;
	 private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	 private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;	
	 private ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO;
	 private List<ConstituencyRevenueVillagesVO> parliamentMandals;
	 private ElectionBasicInfoVO electionBasicInfoVO;
	 private IElectionTrendzService electionTrendzService;
	 private List<SelectOptionVO> electionIdsAndYears;
	 private static final Logger log = Logger.getLogger(ConstituencyPageAction.class);
	 private List<SelectOptionVO> zptcElectionYears;
	 private List<SelectOptionVO> mptcElectionYears;  
	 private List<SelectOptionVO> electionTypes;
	 private Long zptcElectionId; 
	 private Long mptcElectionId;
	 private List<TeshilPartyInfoVO> constituencyWiseAllPartyTrends;
	 JSONObject jObj = null;
	 private String task;
	 HttpServletRequest request;
	 HttpServletResponse response;
	 private MandalAllElectionDetailsVO mandalAllElectionDetailsVO;
	 private String constId,eleType,eleYear,constTYPE;
	 private String chartName,enlargedChartName;
	 private NavigationVO navigationVO;
	 private String mptcElectionType,zptcElectionType,muncipalityElectionType,corporationElectionType;
	 private Long mptcElectionTypeId,zptcElectionTypeId,muncipalityElectionTypeId,corporationElectionTypeId;
	 private EntitlementsHelper entitlementsHelper;
	 private ILocalBodyElectionService localBodyElectionService;
	 
	 private List<SelectOptionVO> municipalElections;
	 private List<SelectOptionVO> greaterElections;
	 private List<SelectOptionVO> corporateElections;

	 private List<SelectOptionVO> allLocalBodyIds,localBodyId;
	 
	 private String forwardTask = null;
	 
	private IAnanymousUserService ananymousUserService;
	private DataTransferVO userDetails;
	private String userType = null;	
	private String mapKey;
	private NavigationVO messageTypes;
	private LocalBodyElectionResultsVO localBodyElectionResults;
	private ConstituencyVO greaterInfo;
	private Long parliamentConstiId;   
	
	private ConstituencyNominationsVO constituencyNominationsVO; 
    private String taskType;
    List<CensusVO> censusVO = new ArrayList<CensusVO>();
    
    private ICommentsDataService commentsDataService;
    private List<SelectOptionVO> constituencyElectionsVO;
    private ConstituencyNominationsVO constituencyAssetsVO;
    private Boolean isSubscribed;
    private IConstituencyManagementService constituencyManagementService;
    private ResultStatus result;
    private Long parliamentCinstiId;
    
    public ResultStatus getResult() {
		return result;
	}
	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}
	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
    
	public Long getParliamentCinstiId() {
		return parliamentCinstiId;
	}
	public void setParliamentCinstiId(Long parliamentCinstiId) {
		this.parliamentCinstiId = parliamentCinstiId;
	}
	public Long getParliamentConstiId() {
		return parliamentConstiId;
	}
	public void setParliamentConstiId(Long parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public List<SelectOptionVO> getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(List<SelectOptionVO> localBodyId) {
		this.localBodyId = localBodyId;
	}
	public ConstituencyVO getGreaterInfo() {
		return greaterInfo;
	}
	public void setGreaterInfo(ConstituencyVO greaterInfo) {
		this.greaterInfo = greaterInfo;
	}
	public LocalBodyElectionResultsVO getLocalBodyElectionResults() {
		return localBodyElectionResults;
	}
	public void setLocalBodyElectionResults(
			LocalBodyElectionResultsVO localBodyElectionResults) {
		this.localBodyElectionResults = localBodyElectionResults;
	}
	public NavigationVO getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(NavigationVO messageTypes) {
		this.messageTypes = messageTypes;
	}
	
	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	public DataTransferVO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(DataTransferVO userDetails) {
		this.userDetails = userDetails;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Long getMptcElectionTypeId() {
		return mptcElectionTypeId;
	}

	public void setMptcElectionTypeId(Long mptcElectionTypeId) {
		this.mptcElectionTypeId = mptcElectionTypeId;
	}

	public Long getZptcElectionTypeId() {
		return zptcElectionTypeId;
	}

	public void setZptcElectionTypeId(Long zptcElectionTypeId) {
		this.zptcElectionTypeId = zptcElectionTypeId;
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

	public String getForwardTask() {
		return forwardTask;
	}

	public void setForwardTask(String forwardTask) {
		this.forwardTask = forwardTask;
	}

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public Long getZptcElectionId() {
		return zptcElectionId;
	}

	public void setZptcElectionId(Long zptcElectionId) {
		this.zptcElectionId = zptcElectionId;
	}

	public ConstituencyNominationsVO getConstituencyNominationsVO() {
		return constituencyNominationsVO;
	}
	public void setConstituencyNominationsVO(
			ConstituencyNominationsVO constituencyNominationsVO) {
		this.constituencyNominationsVO = constituencyNominationsVO;
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

	public List<CensusVO> getCensusVO() {
		return censusVO;
	}
	public void setCensusVO(List<CensusVO> censusVO) {
		this.censusVO = censusVO;
	}
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

	public String getConstTYPE() {
		return constTYPE;
	}

	public void setConstTYPE(String constTYPE) {
		this.constTYPE = constTYPE;
	}

	public String getEnlargedChartName() {
		return enlargedChartName;
	}

	public void setEnlargedChartName(String enlargedChartName) {
		this.enlargedChartName = enlargedChartName;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getConstId() {
		return constId;
	}

	public void setConstId(String constId) {
		this.constId = constId;
	}

	public String getEleType() {
		return eleType;
	}

	public void setEleType(String eleType) {
		this.eleType = eleType;
	}

	public String getEleYear() {
		return eleYear;
	}

	public void setEleYear(String eleYear) {
		this.eleYear = eleYear;
	}

	public MandalAllElectionDetailsVO getMandalAllElectionDetailsVO() {
		return mandalAllElectionDetailsVO;
	}

	public void setMandalAllElectionDetailsVO(
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO) {
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	 private enum ChartType {pollingTrendz, pollingPercent};
	 
	
	 public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
		
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}

	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateDetailsForConstituency() {
			return candidateDetailsForConstituency;
	}

	public void setCandidateDetailsForConstituency(
			CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency) {
		this.candidateDetailsForConstituency = candidateDetailsForConstituency;
	}
	
	 public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public List<TeshilPartyInfoVO> getConstituencyWiseAllPartyTrends() {
		return constituencyWiseAllPartyTrends;
	}

	public void setConstituencyWiseAllPartyTrends(
			List<TeshilPartyInfoVO> constituencyWiseAllPartyTrends) {
		this.constituencyWiseAllPartyTrends = constituencyWiseAllPartyTrends;
	}

	public static Logger getLog() {
		return log;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}

	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
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

	public List<SelectOptionVO> getElectionIdsAndYears() {
		return electionIdsAndYears;
	}

	public void setElectionIdsAndYears(List<SelectOptionVO> electionIdsAndYears) {
		this.electionIdsAndYears = electionIdsAndYears;
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

	HttpSession session;
	private ServletContext context;
	IConstituencyPageService constituencyPageService;
	
    public void setServletContext(ServletContext context) {
	   this.context = context;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	
	public DelimitationConstituencyMandalResultVO getDelimitationConstituencyMandalResultVO() {
		return delimitationConstituencyMandalResultVO;
	}

	public void setDelimitationConstituencyMandalResultVO(
			DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO) {
		this.delimitationConstituencyMandalResultVO = delimitationConstituencyMandalResultVO;
	}

	public IElectionTrendzService getElectionTrendzService() {
		return electionTrendzService;
	}

	public void setElectionTrendzService(
			IElectionTrendzService electionTrendzService) {
		this.electionTrendzService = electionTrendzService;
	}

	public ElectionTrendzReportVO getElectionTrendzReportVO() {
		return electionTrendzReportVO;
	}

	public void setElectionTrendzReportVO(
			ElectionTrendzReportVO electionTrendzReportVO) {
		this.electionTrendzReportVO = electionTrendzReportVO;
	}

	public ElectionBasicInfoVO getElectionBasicInfoVO() {
		return electionBasicInfoVO;
	}

	public void setElectionBasicInfoVO(ElectionBasicInfoVO electionBasicInfoVO) {
		this.electionBasicInfoVO = electionBasicInfoVO;
	}

	public CandidateVotingTrendzCharts getCandidateVotingTrendzCharts() {
		return candidateVotingTrendzCharts;
	}

	public void setCandidateVotingTrendzCharts(
			CandidateVotingTrendzCharts candidateVotingTrendzCharts) {
		this.candidateVotingTrendzCharts = candidateVotingTrendzCharts;
	}

	public ConstituencyRevenueVillagesVO getConstituencyRevenueVillagesVO() {
		return constituencyRevenueVillagesVO;
	}

	public void setConstituencyRevenueVillagesVO(
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO) {
		this.constituencyRevenueVillagesVO = constituencyRevenueVillagesVO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<ConstituencyRevenueVillagesVO> getParliamentMandals() {
		return parliamentMandals;
	}

	public void setParliamentMandals(
			List<ConstituencyRevenueVillagesVO> parliamentMandals) {
		this.parliamentMandals = parliamentMandals;
	}

	public NavigationVO getNavigationVO() {
		return navigationVO;
	}

	public void setNavigationVO(NavigationVO navigationVO) {
		this.navigationVO = navigationVO;
	}

	public ILocalBodyElectionService getLocalBodyElectionService() {
		return localBodyElectionService;
	}

	public void setLocalBodyElectionService(
			ILocalBodyElectionService localBodyElectionService) {
		this.localBodyElectionService = localBodyElectionService;
	}

	public List<SelectOptionVO> getMunicipalElections() {
		return municipalElections;
	}

	public void setMunicipalElections(List<SelectOptionVO> municipalElections) {
		this.municipalElections = municipalElections;
	}

	public List<SelectOptionVO> getGreaterElections() {
		return greaterElections;
	}

	public void setGreaterElections(List<SelectOptionVO> greaterElections) {
		this.greaterElections = greaterElections;
	}

	public List<SelectOptionVO> getCorporateElections() {
		return corporateElections;
	}

	public void setCorporateElections(List<SelectOptionVO> corporateElections) {
		this.corporateElections = corporateElections;
	}

	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}
	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}
	public List<SelectOptionVO> getConstituencyElectionsVO() {
		return constituencyElectionsVO;
	}
	public void setConstituencyElectionsVO(
			List<SelectOptionVO> constituencyElectionsVO) {
		this.constituencyElectionsVO = constituencyElectionsVO;
	}
	public ConstituencyNominationsVO getConstituencyAssetsVO() {
		return constituencyAssetsVO;
	}
	public void setConstituencyAssetsVO(
			ConstituencyNominationsVO constituencyAssetsVO) {
		this.constituencyAssetsVO = constituencyAssetsVO;
	}
	public String execute() throws Exception{
       
		String url = request.getRequestURL().toString();
		String substr = url.substring(7);
		String path = substr.substring(0, substr.indexOf('/')) ;
		String userStatusType = null;
		if(path.equalsIgnoreCase("partyanalyst.com"))
			mapKey = "http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRScRx3TrvnxStTkM4udVhaLbRJhbBQtQ6p3f6vU6rRwFFw_2yEXM9Af3g&sensor=true";
		else
			mapKey = "http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRScRx3TrvnxStTkM4udVhaLbRJhbBQtQ6p3f6vU6rRwFFw_2yEXM9Af3g&sensor=true";
			//mapKey = "http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRSqcWSqDo-rwCiW8VjO_0U_k7HAuxQBSweyAZ1v5ozDSPMDKAFtPwSrGw&sensor=true";
		
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
		
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId);
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		
		//Entilements Check
		checkForRegionAndReportLevelEntitlements(regVO);
		
		constituencyName = constituencyDetails.getConstituencyName();
		
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId); //for building graph use this.
		
		DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO = delimitationConstituencyMandalService.getMandalsForDelConstituency(constituencyId);
		
		Throwable ex = delimitationConstituencyMandalResultVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		log.info("delimitationConstituencyMandalResultVO.getMandals().size()::::"+delimitationConstituencyMandalResultVO.getPresentMandals().size());
		log.info("delimitationConstituencyMandalResultVO..getConstituencyType()::::"+delimitationConstituencyMandalResultVO.getConstituencyType());
		setDelimitationConstituencyMandalResultVO(delimitationConstituencyMandalResultVO);
		Set<String> partiesInChart = null;
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		
		String pieChart = "";
		String pieChartPath = "";
		String title = "";
		String[] chartNames = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String[] extraInfo = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String cPath = request.getContextPath();
		int i=0;
		for(VotersWithDelimitationInfoVO votersInMandalOrAC:constituencyVO.getAssembliesOfParliamentInfo()){
			pieChart = votersInMandalOrAC.getYear()+"_Voters Info for Constituency_"+constituencyVO.getId()+".png";
			//For Charts
			if(cPath.contains("PartyAnalyst"))
			   pieChartPath = context.getRealPath("/")+ "charts\\" + pieChart;
			else
				pieChartPath = IWebConstants.CHART_URL_IN_SERVER + pieChart;
			
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
				ChartProducer.createProblemsPieChart(title, createPieDatasetForVoters(votersInMandalOrAC.getVotersInfoForMandalVO()), pieChartPath, 
						null,true,260,270);
			chartNames[i++] = pieChart;
		}
		
		constituencyVO.setPieChartNames(chartNames);
		constituencyVO.setExtraInfo(extraInfo);
		candidateDetailsForConstituency = constituencyPageService.getCandidateAndPartyInfoForConstituency(constituencyId);
		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		listOfConstituencies.add(constituencyId);
		problemBean = problemManagementReportService.getAllProblemsForGivenLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL).getApprovalProblems();
		
	
		System.out.println("electionTrendzReportVO ============ "+electionTrendzReportVO);
		
		if(problemBean != null)
			System.out.println("problemBean === "+problemBean.size());
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(constituencyId);
		
           if(electionBasicInfoVO.getElectionId() != null){
			
			System.out.println("Inside trendz service call ....");
			electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.
					getElectionTypeId(),electionBasicInfoVO.getElectionYear(),constituencyId,IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
			if(electionTrendzReportVO != null)
			getMapsForVotingTrendz(electionTrendzReportVO);
			electionTrendzReportVO.setPrevElectionYearsInfo(electionTrendzService.getPreviousElectionsInfoForAConstituency(electionBasicInfoVO.
					getElectionYear(), constituencyId));
           }
           	
        chartName = "allPartiesVotingTrendsIn"+constituencyName+"ConstituencyForAllElections_"+constituencyId+".png";
        String chartPath;
        if(cPath.contains("PartyAnalyst"))
            chartPath = context.getRealPath("/")+ "charts\\" + chartName;
        else
        	chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
        
        partiesInChart = new LinkedHashSet<String>();
   		ChartProducer.createLineChart("All Parties Performance In Diff Elections Of "+constituencyName+" Constituency", "Elections", "Percentages", 
   				createDataset(constituencyElectionResultsVO, partiesInChart), chartPath,350,700, ChartUtils.getLineChartColors(partiesInChart),true );
   		
   		enlargedChartName = "enlargedImgOfAllPartiesVotingTrendsIn"+constituencyName+"ConstituencyForAllElections_"+constituencyId+".png";
   		String enlargedChartPath = "";
   		if(cPath.contains("PartyAnalyst"))
   			enlargedChartPath = context.getRealPath("/")+ "charts\\" + enlargedChartName;
   		else
   			enlargedChartPath = IWebConstants.CHART_URL_IN_SERVER + enlargedChartName;
        
        partiesInChart = new LinkedHashSet<String>();
   		ChartProducer.createLineChart("All Parties Performance In Diff Elections Of "+constituencyName+" Constituency", "Elections", "Percentages", 
   				createDataset(constituencyElectionResultsVO, partiesInChart), enlargedChartPath,600,800, ChartUtils.getLineChartColors(partiesInChart) ,true);
   		List<Long> constituencyIds = new ArrayList<Long>();
   		if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
   			constituencyIds.add(constituencyId);
   		}else{
   			ConstituencyInfoVO  constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(constituencyId);
   			if(constituencyInfoVO.getAssembyConstituencies() != null && constituencyInfoVO.getAssembyConstituencies().size() >0){
   				for(SelectOptionVO option:constituencyInfoVO.getAssembyConstituencies())
   					constituencyIds.add(option.getId());
   			}
   		}
		if(constituencyIds.size() >0){
   		    municipalElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.MUNCIPLE_ELECTION_TYPE, 1l,constituencyIds);
   		    corporateElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.CORPORATION_ELECTION_TYPE, 1l,constituencyIds);
   		    greaterElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.GREATER_ELECTION_TYPE, 1l,constituencyIds);
		}
		SelectOptionVO defaultData = new SelectOptionVO(0l, "");
		if(municipalElections == null || municipalElections.size() == 0){
			municipalElections = new ArrayList<SelectOptionVO>();
			municipalElections.add(defaultData);
		}
		if(corporateElections == null || corporateElections.size() == 0){
			corporateElections = new ArrayList<SelectOptionVO>();
			corporateElections.add(defaultData);
		}
		if(greaterElections == null || greaterElections.size() == 0){
			greaterElections = new ArrayList<SelectOptionVO>();
			greaterElections.add(defaultData);
		}
   		allLocalBodyIds = localBodyElectionService.getLatestGHMCElectionIdAndLatestElectionYear(IConstants.GREATER_ELECTION_TYPE).getMessageTypes();
   		localBodyId = localBodyElectionService.getLocalBodyElectionIdsForAConstituency(constituencyId,IConstants.GREATER_ELECTION_TYPE).getMessageTypes();
   		if(localBodyId!=null && localBodyId.size()!=0){
   			greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(allLocalBodyIds.get(0).getId(),constituencyId,0l,0l);
   			if(greaterInfo.getListOfParties()==null){
   				greaterInfo.setListOfParties(getSelectOptionData());
   			}
   			if(greaterInfo.getListOfWards()==null){
   				greaterInfo.setListOfWards(getSelectOptionData());
   			}
   		}else{  
   			greaterInfo = new ConstituencyVO();
   			greaterInfo.setListOfParties(getSelectOptionData());
   			greaterInfo.setListOfWards(getSelectOptionData());
   		}
   		
   		navigationVO = staticDataService.findHirarchiForNavigation(constituencyId, IConstants.CONSTITUENCY_LEVEL);
   		if(navigationVO.getPcsInfo()!= null && navigationVO.getPcsInfo().size() > 0)
   		   parliamentCinstiId = navigationVO.getPcsInfo().get(0).getId();
   		Long startIndex = 0L;
   		String nameString = "";
   		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
   		
   		if(user==null){
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,IConstants.MAX_ANONYMOUS_USER_DISPLAY,0l,IConstants.ALL,startIndex,nameString);	
   		}else{
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,IConstants.MAX_ANONYMOUS_USER_DISPLAY,user.getRegistrationID(),IConstants.ALL,startIndex,nameString);
   		}
   		
   		//	Free User
   		if(user !=null)
   		{
   			Long userId=user.getRegistrationID();
   			isSubscribed=constituencyManagementService.getIsSubscribed(userId,constituencyId);
	   		if(session.getAttribute(IWebConstants.FREE_USER_ROLE) !=null && session.getAttribute(IWebConstants.FREE_USER_ROLE).equals(true)){
				userDetails.setLoginStatus("true");
				userDetails.setUserId(user.getRegistrationID());
				userDetails.setConstituencyId(user.getConstituencyId());
			}else if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true)){
				userDetails.setLoginStatus("true");
				userDetails.setUserId(user.getRegistrationID());
				userDetails.setConstituencyId(user.getConstituencyId());
			}else{
				userDetails.setLoginStatus("false");
			}
   		}
   	//For Both roles
   		if(user !=null)
   		{
   	 
		
   	 if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		userStatusType = IConstants.PARTY_ANALYST_USER;
   	 userDetails.setUserStatusType(userStatusType);
	 if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
		userStatusType = IConstants.FREE_USER;
	 userDetails.setUserStatusType(userStatusType);
	 if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		userStatusType = IConstants.BOTH;
	 userDetails.setUserStatusType(userStatusType);
   		}
      else{
        userStatusType = IConstants.NOT_LOGGED_IN;
        userDetails.setUserStatusType(userStatusType);
		}
	 
	 messageTypes = ananymousUserService.getAllMessageTypes();
		
	
				
   		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			return Action.SUCCESS;
		}
		else
			return Action.ERROR;   	
	}
	
	private void checkForRegionAndReportLevelEntitlements(RegistrationVO regVO){
		
		if(!entitlementsHelper.checkForRegionToViewReport(regVO, IConstants.CONSTITUENCY_LEVEL, constituencyId)){
			constituencyDetails.setHasAnalize(false);
			constituencyDetails.setViewCompletePage(false);
			constituencyDetails.setVotingTrendz(false);
			constituencyDetails.setAnalyzeComments(false);
			constituencyDetails.setPostComments(false);
			return;
		}
		
		if(((regVO != null && !entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.CONSTITUENCY_ANALYSIS)) ||
			(regVO == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CONSTITUENCY_ANALYSIS))))
			constituencyDetails.setHasAnalize(false);
		
		if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.MANDAL_VOTING_TRENDZ)) ||
				(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.MANDAL_VOTING_TRENDZ)))
				constituencyDetails.setViewCompletePage(true);
		
		if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.VOTING_TRENDZ)) ||
				(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.VOTING_TRENDZ)))
				constituencyDetails.setVotingTrendz(true);
		
		if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.COMMETNS_ANALYZE)) ||
				(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.COMMETNS_ANALYZE)))
				constituencyDetails.setAnalyzeComments(true);
		
		if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.COMMENTS_POST)) ||
				(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.COMMENTS_POST)))
				constituencyDetails.setPostComments(true);
	}
	
	public String getGhmcResults(){

		String param=null;		
		param=request.getParameter("task");
		log.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constituencyId");
	
		allLocalBodyIds = localBodyElectionService.getLatestGHMCElectionIdAndLatestElectionYear(IConstants.GREATER_ELECTION_TYPE).getMessageTypes();
   	
		if(jObj.getString("type").equalsIgnoreCase(IConstants.ALL)){
			if(allLocalBodyIds!=null && allLocalBodyIds.size()!=0){
	   			greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(allLocalBodyIds.get(0).getId(),constituencyId,0l,0l);
	   		}else{  
	   			greaterInfo = new ConstituencyVO();
	   			greaterInfo.setListOfParties(getSelectOptionData());
	   			greaterInfo.setListOfWards(getSelectOptionData());
	   		}
		}else if(jObj.getString("type").equalsIgnoreCase("partyWise")){
			if(allLocalBodyIds!=null && allLocalBodyIds.size()!=0){
	   			greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(allLocalBodyIds.get(0).getId(),constituencyId,new Long(jObj.getLong("value")),0l);
	   		}else{  
	   			greaterInfo = new ConstituencyVO();
	   			greaterInfo.setListOfParties(getSelectOptionData());
	   			greaterInfo.setListOfWards(getSelectOptionData());
	   		}
		}else if(jObj.getString("type").equalsIgnoreCase("wardWise")){
			if(allLocalBodyIds!=null && allLocalBodyIds.size()!=0){
	   			greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(allLocalBodyIds.get(0).getId(),constituencyId,new Long(0l),new Long(jObj.getLong("value")));
	   		}else{  
	   			greaterInfo = new ConstituencyVO();
	   			greaterInfo.setListOfParties(getSelectOptionData());
	   			greaterInfo.setListOfWards(getSelectOptionData());
	   		}
		}
   		
		return Action.SUCCESS;
	}
	
	public List<SelectOptionVO> getSelectOptionData(){
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		SelectOptionVO result = new SelectOptionVO();
		result.setId(0l);
		result.setName("");
		list.add(result);
		return list;
	}
	public String getMandalsVotesShareInConstituencyAjax(){
		
		String param=null;		
		param=request.getParameter("task");
		log.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constiId = jObj.getLong("constituencyId");
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constiId);
		
		return Action.SUCCESS;
	}
	
	public String getPartiesPerformanceInDiffElectionsAjax(){
		String param=null;		
		param=request.getParameter("task");
		log.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long constiId = jObj.getLong("constituencyId");
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constiId);
		
		return Action.SUCCESS;
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
	
	private CategoryDataset createDataset(List<ConstituencyElectionResultsVO> allElectionResults, Set<String> partiesInChart) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ElectionResultVO> partiesElectionResults = new ArrayList<ElectionResultVO>();
        int i = 0;
        	if(allElectionResults != null && allElectionResults.size() > 0){
        	for(ConstituencyElectionResultsVO result: allElectionResults){
        		ElectionResultVO partiesElecResultForGraph = new ElectionResultVO();    
        		if(result.getCandidateResultsVO() != null){
        			partiesElecResultForGraph.setPercentage(result.getCandidateResultsVO().getVotesPercentage());
            		partiesElecResultForGraph.setPartyName(result.getCandidateResultsVO().getPartyName());     
            		partiesElecResultForGraph.setPartyShortName(result.getCandidateResultsVO().getPartyShortName()); 
        		}
        		partiesElecResultForGraph.setElectionYear(result.getElectionYear()+" "+result.getElectionType());
        		partiesElectionResults.add(partiesElecResultForGraph);        		
        		for(CandidateOppositionVO oppositionResult: allElectionResults.get(i).getCandidateOppositionList()){
        			ElectionResultVO oppositionPartiesElecResultForGraph = new ElectionResultVO();         			
        			oppositionPartiesElecResultForGraph.setPercentage(oppositionResult.getVotesPercentage());
        			oppositionPartiesElecResultForGraph.setElectionYear(result.getElectionYear()+" "+result.getElectionType());
        			oppositionPartiesElecResultForGraph.setPartyName(oppositionResult.getPartyName());   
        			oppositionPartiesElecResultForGraph.setPartyShortName(oppositionResult.getPartyShortName());
            		partiesElectionResults.add(oppositionPartiesElecResultForGraph);            		
            	}        		
        		i++;
        		partiesElectionResults.add(partiesElecResultForGraph);
        	}
               
       Collections.sort(partiesElectionResults, new ElectionResultComparator());    
        for(ElectionResultVO graphInfo:partiesElectionResults){
        	partiesInChart.add(graphInfo.getPartyShortName());
        	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyShortName(),
           			graphInfo.getElectionYear());	
        }
        	}
        return dataset;
    }

	public void getMapsForVotingTrendz(ElectionTrendzReportVO electionTrendzReportVO){
        
		    if(electionTrendzReportVO.getElectionTrendzOverviewVO() != null){
		    	 try{
		    		 
		    		String constituencyName = electionTrendzReportVO.getConstituencyName();
		    		String stateName = electionTrendzReportVO.getState();
		 			session = request.getSession();
		 			String cPath = request.getContextPath();
		 			String chartId = constituencyName.concat(stateName).concat("BarChart");
		 			String barChartName = "constituencyTrendzChart_" + chartId + session.getId()+".png";
		 			String chartPath="";
		 			String pollingChartPath;
		 			 
		 			if(cPath.contains("PartyAnalyst"))
		 				chartPath = context.getRealPath("/") + "charts\\" + barChartName;
		 			else
		 				chartPath = IWebConstants.CHART_URL_IN_SERVER + barChartName;
		 	       
		 			  	ChartProducer.createALineChartforVotingTrendzNew(constituencyName + " Voting Trendz" ,createDataset2(electionTrendzReportVO.getElectionTrendzOverviewVO().getPartyElectionTrendzVO()),createDataset1(electionTrendzReportVO.getElectionTrendzOverviewVO().getPartyElectionTrendzVO()), chartPath);
		 	        	request.setAttribute("barChartName", barChartName);
		 				session.setAttribute("barChartName", barChartName);
		 				
		 			String pollingChartId = constituencyName.concat(stateName).concat("PieChart");
		 			String pollingChartName = "pollingPercentChart_" + pollingChartId + session.getId()+".png";
		 			
		 			 if(cPath.contains("PartyAnalyst"))
		 				 pollingChartPath = context.getRealPath("/") + "charts\\" + pollingChartName;
		 			 else
		 				pollingChartPath = IWebConstants.CHART_URL_IN_SERVER + pollingChartName;
			 			
		 			List<CategoryDataset> dataset = new ArrayList<CategoryDataset>();
		 	        
		 	        dataset.add(createDatasetForPollingInfo1(electionTrendzReportVO.getElectionTrendzOverviewVO(),ChartType.pollingTrendz));
		 	        dataset.add(createDatasetForPollingInfo2(electionTrendzReportVO.getElectionTrendzOverviewVO(), ChartType.pollingPercent));
		 			
		 			    ChartProducer.createLineChartForPolling("Polling Trendz ..",dataset ,"","", pollingChartPath);
		 			    request.setAttribute("pollingChartName", pollingChartName);
		 				session.setAttribute("pollingChartName", pollingChartName);
		 				
		 			String wonCandChartId = constituencyName.concat(stateName).concat("PieChart");
			 		String wonCandChartName = "wonCandOverallVotesPercentChart_" + wonCandChartId + session.getId()+".png";
			 		String wonCandChartPath = context.getRealPath("/") + "charts\\" + wonCandChartName;
		 				
		 		    String wonCandTotPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getTotalVotesPercent();
		 		    String wonCandMalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallMaleVotesPercent();
		 		    String wonCandFemalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallFemaleVotesPercent();
		 		    String wonCandMaleOrFemalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallMaleOrFemaleVotesPercent();
		 		    		 		    
		 		    String maleLabelForVoting = "Male";
		 		    String femaleLabelForVotingPercent = "Female";
		 		    String maleOrFemaleLabelForVotingPercent = "Male/Female";	
		 		   
		 		        ChartProducer.createPieChart("", createPieDataSet(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,wonCandTotPercent,wonCandMalePercent,wonCandFemalePercent,wonCandMaleOrFemalePercent), wonCandChartPath);
		 		        //ChartProducer.createBarChartForCandidateVotingTrendz("","Trendz","Votes %", createDatasetForCandTrendz(electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getPartyName(),null,wonCandMalePercent,wonCandFemalePercent,wonCandMaleOrFemalePercent), wonCandChartPath);
		 		        request.setAttribute("wonCandChartName", wonCandChartName);
	 				    session.setAttribute("wonCandChartName", wonCandChartName);
		 		    
	 			    String wonCandVotesChartId = constituencyName.concat(stateName).concat("PieChart");
				 	String wonCandVotesChartName = "wonCandVotesPercentChart_" + wonCandVotesChartId + session.getId()+".png";
				 	
				 	String wonCandVotesChartPath ;
				 	if(cPath.contains("PartyAnalyst"))
				 		wonCandVotesChartPath = context.getRealPath("/") + "charts\\" + wonCandVotesChartName;
				 	else
				 		wonCandVotesChartPath = IWebConstants.CHART_URL_IN_SERVER + wonCandVotesChartName;
				 	
				 	String wonCandTotVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getTotalVotesPercent();
		 		    String wonCandMaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getMaleVotesPercentInConstiVotes();
		 		    String wonCandFemaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getFemaleVotesPercentInConstiVotes();
		 		    String wonCandMaleOrFemaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getMaleOrFemaleVotesPercentInConstiVotes();

		 		       ChartProducer.createPieChart("", createPieDataSetForCandidateVotes(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,wonCandTotVotesPercent,wonCandMaleVotesPercent,wonCandFemaleVotesPercent,wonCandMaleOrFemaleVotesPercent), wonCandVotesChartPath);
			           request.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
				       session.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
				       
				       candidateVotingTrendzCharts = new CandidateVotingTrendzCharts();
				       
				       candidateVotingTrendzCharts.setPollingDetailsChart(pollingChartName);
				       candidateVotingTrendzCharts.setVotingTrendzMainChart(barChartName);
				       candidateVotingTrendzCharts.setCandOverallVotesPercent(wonCandVotesChartName);
				       candidateVotingTrendzCharts.setCandVotingTrendz(wonCandChartName);
				       
				       electionTrendzReportVO.getElectionTrendzOverviewVO().setElectionTrendzCharts(candidateVotingTrendzCharts);
	 		  		 	         
		    	 }
		    	 catch(Exception exc){
		    		 exc.printStackTrace();
		    	 }
		    }
	}
	
	private CategoryDataset createDataset1(List<PartyResultsTrendzVO> partyResultsTrendzVO) {
		
	     final String category2 = "Male %";
	     final String category3 = "Female %";
	     final String category4 = "MaleOrFemale %";
       final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       for(PartyResultsTrendzVO result: partyResultsTrendzVO){
       	final String series =  result.getPartyName();
       	dataset.addValue(new Double(result.getMaleVotesPercent()), category2, series);
       	dataset.addValue(new Double(result.getFemaleVotesPercent()), category3, series);
    	dataset.addValue(new Double(result.getMaleAndFemaleVotesPercent()), category4, series);
       }
       return dataset;
       
   }
	
	private CategoryDataset createDataset2(List<PartyResultsTrendzVO> partyResultsTrendzVO) {
		 final String category1 =  "Total %";
	     
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      for(PartyResultsTrendzVO result: partyResultsTrendzVO){
      	final String series =  result.getPartyName();
      	dataset.addValue(new Double(result.getTotalVotesPercent()), category1,series );
       }
      return dataset;
      
  }
	
  private PieDataset createPieDataSet(String maleLabel,String femaleLabel,String maleOrFemaleLabel,String pollingPercent,String malePollPercent,String femalePollPercent,String maleOrFemalePollPercent){
	  DefaultPieDataset dataset = new DefaultPieDataset();
	  dataset.setValue(maleLabel, new Double(malePollPercent));
	  dataset.setValue(femaleLabel, new Double(femalePollPercent));
	  dataset.setValue(maleOrFemaleLabel, new Double(maleOrFemalePollPercent));
	  
	  return dataset;
  }
  
  private PieDataset createPieDataSetForCandidateVotes(String maleLabel,String femaleLabel,String maleOrFemaleLabel,String pollingPercent,String maleVotesPercent,String femaleVotesPercent,String maleOrFemaleVotesPercent){
	  
	  Double totalPercnt = new Double(100) - (new Double(maleVotesPercent) + new Double(femaleVotesPercent) + new Double(maleOrFemaleVotesPercent));
	  DefaultPieDataset dataset = new DefaultPieDataset();
	  dataset.setValue("Other Candidates Votes % Share",totalPercnt);
	  dataset.setValue(maleLabel, new Double(maleVotesPercent));
	  dataset.setValue(femaleLabel, new Double(femaleVotesPercent));
	  dataset.setValue(maleOrFemaleLabel, new Double(maleOrFemaleVotesPercent));
	  
	  return dataset;
  }
  private CategoryDataset createDatasetForPollingInfo1(ElectionTrendzOverviewVO pollingTrendz,ChartType chartType) {
      
      // row keys...
      final String series1 = "Total Voters";
      final String series2 = "Polled Votes";
      
      final String category1 = "Male";
      final String category2 = "Female";
      final String category3 = "Male/Female";
      
      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
      dataset.addValue(pollingTrendz.getMaleVoters(), series1, category1);
      dataset.addValue(pollingTrendz.getFemaleVoters(), series1,category2);
      dataset.addValue(pollingTrendz.getMaleAndFemaleVoters(), series1, category3);
      
      dataset.addValue(pollingTrendz.getMalePolledVotes(), series2, category1);
      dataset.addValue(pollingTrendz.getFemalePolledVotes(), series2, category2);
      dataset.addValue(pollingTrendz.getMaleAndFemalePolledVotes(), series2, category3);
      
      	
      return dataset;
   }
  
  private CategoryDataset createDatasetForPollingInfo2(ElectionTrendzOverviewVO pollingTrendz,ChartType chartType) {
	  final String series3 =  "Polling %";
	  final String category1 = "Male";
      final String category2 = "Female";
      final String category3 = "Male/Female";

	  // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      dataset.addValue(new Double(pollingTrendz.getMalePollingPercent()), series3, category1);
      dataset.addValue(new Double(pollingTrendz.getFemalePollingPercent()), series3, category2);
      dataset.addValue(new Double(pollingTrendz.getMaleAndFemalePollingPercent()), series3, category3);
      
      return dataset;
  }
  
  public IConstituencyPageService getConstituencyPageService() {
	return constituencyPageService;
}

private CategoryDataset createDatasetForCandTrendz(String partyName,String completeVotesPercent,String maleVotesPercent,String femaleVotesPercent,String maleOrFemaleVotesPercent) {
		
	     final String category1 = "Male %";
	     final String category2 = "Female %";
	     final String category3 = "MaleOrFemale %";
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
   
    final String series = partyName;
    dataset.addValue(new Double(maleVotesPercent), category1, series);
    dataset.addValue(new Double(femaleVotesPercent), category2, series);
 	dataset.addValue(new Double(maleOrFemaleVotesPercent), category3, series);
    
    return dataset;
  }

  public String getCandidateVotingTrendz(){
	  
	    String param=null;		
		param=request.getParameter("task");
		log.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
  }
  
    
  public String getNextPrevCandidateVotingTrendz()
  {
		String param = null;
		String candName = "";
		String partyName = "";
		String candTotPercent = "";
		String candMalePercent = "";
		String candFemalePercent = "";
		String candMaleOrFemalePercent = "";
		String maleVotesPercentInConstiVotes = "";
		String femaleVotesPercentInConstiVotes = "";
		String maleOrFemaleVotesPercentInConstiVotes = "";
		String cPath = request.getContextPath();
		
		param = getTask();
		session = request.getSession();
		
		try {
			jObj = new JSONObject(param);
			log.debug("Params :" + jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		candName                = jObj.getString("candidateName");
		partyName               = jObj.getString("partyName");
		candTotPercent          = jObj.getString("totalVotesPercent");
		candMalePercent         = jObj.getString("overallMaleVotesPercent");
		candFemalePercent       = jObj.getString("overallFemaleVotesPercent"); 
		candMaleOrFemalePercent = jObj.getString("overallMaleOrFemaleVotesPercent"); 
		maleVotesPercentInConstiVotes = jObj.getString("maleVotesPercentInConstiVotes"); 
		femaleVotesPercentInConstiVotes = jObj.getString("femaleVotesPercentInConstiVotes");
		maleOrFemaleVotesPercentInConstiVotes = jObj.getString("maleOrFemaleVotesPercentInConstiVotes");
		
		log.debug("Creating Charts For " + candName);
		
		String wonCandChartIdNew = candName.concat("VotingTrendz").concat("PieChart");
 		String wonCandChartNameNew = "candOverallVotesPercentChart_" + wonCandChartIdNew + session.getId() +".png";
 		
 		String wonCandChartPathNew;
 		if(cPath.contains("PartyAnalyst"))
 		    wonCandChartPathNew = context.getRealPath("/") + "charts\\" + wonCandChartNameNew;
 		else
 			wonCandChartPathNew =IWebConstants.CHART_URL_IN_SERVER + wonCandChartNameNew;
				
		    String maleLabelForVoting = "Male";
		    String femaleLabelForVotingPercent = "Female";
		    String maleOrFemaleLabelForVotingPercent = "Male/Female";	
		   
		        //ChartProducer.createBarChartForCandidateVotingTrendz("","Trendz","Votes %", createDatasetForCandTrendz(partyName,null,candMalePercent,candFemalePercent,candMaleOrFemalePercent), wonCandChartPathNew);
		        ChartProducer.createPieChart("", createPieDataSet(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,"0",candMalePercent,candFemalePercent,candMaleOrFemalePercent), wonCandChartPathNew);
		        request.setAttribute("wonCandChartName", wonCandChartNameNew);
			    session.setAttribute("wonCandChartName", wonCandChartNameNew);
			    
		String wonCandVotesChartId = candName.concat("VotingTrendz").concat("PieChart");
	 	String wonCandVotesChartName = "candVotesPercentInConstiChart_" + wonCandVotesChartId + session.getId()+".png";
	 	
	 	String wonCandVotesChartPath = "";
	 	if(cPath.contains("PartyAnalyst"))
	 	   wonCandVotesChartPath = context.getRealPath("/") + "charts\\" + wonCandVotesChartName;
	 	else 
	 		wonCandVotesChartPath = IWebConstants.CHART_URL_IN_SERVER + wonCandVotesChartName;
	 	
	 	   ChartProducer.createPieChart("", createPieDataSetForCandidateVotes(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,candTotPercent,maleVotesPercentInConstiVotes,femaleVotesPercentInConstiVotes,maleOrFemaleVotesPercentInConstiVotes), wonCandVotesChartPath);
           request.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
	       session.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
	       
	       candidateVotingTrendzCharts = new CandidateVotingTrendzCharts();
	       
	       candidateVotingTrendzCharts.setCandOverallVotesPercent(wonCandVotesChartName);
	       candidateVotingTrendzCharts.setCandVotingTrendz(wonCandChartNameNew);
		
		return Action.SUCCESS;
  }
  
  public String getVotingTrendzForElectionYears() throws Exception{
	  
	    String param=null;		
	    String districtId = "";
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(new Long(232));
		if(electionBasicInfoVO.getElectionId() != null){
			
			System.out.println("Inside trendz service call ....");
			electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.getElectionTypeId(),electionBasicInfoVO.getElectionYear(),new Long(232),IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
			
			if(electionTrendzReportVO != null)
				getMapsForVotingTrendz(electionTrendzReportVO);
		}
		return Action.SUCCESS;
  }

  public String getConstituencyElectionYears(){
	  
	  	String param=null;		
	    param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	  
	  electionIdsAndYears = staticDataService.getElectionIdsAndYearsForConstituency(jObj.getLong("constituencyId"));
	  return SUCCESS;
  }
  
  public String getPartyWiseConstituencyZptcOrMptcElectionTrends(){
	  if(task != null){
	  	String param=null;		
	    param = getTask();		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
			
			if(jObj.getString("task").equalsIgnoreCase("getZptcElectionResults")){
				try{
					constituencyWiseAllPartyTrends = constituencyPageService.getPartyWiseZptcOrMptcElectionDataForAConstituency(jObj.getLong("constituencyId"),jObj.getString("electionYear"),IConstants.ZPTC_ELECTION_TYPE,jObj.getString("constituencyType"));
					createPieChartForElectionTypeNElectionYear(constituencyWiseAllPartyTrends,jObj.getString("electionYear"),IConstants.ZPTC_ELECTION_TYPE);
					}catch(Exception ex){
					log.debug("No data is available...");
				}
			}else if(jObj.getString("task").equalsIgnoreCase("getMptcElectionResults")){
				try{
					constituencyWiseAllPartyTrends = constituencyPageService.getPartyWiseZptcOrMptcElectionDataForAConstituency(jObj.getLong("constituencyId"),jObj.getString("electionYear"),IConstants.MPTC_ELECTION_TYPE,jObj.getString("constituencyType"));
					createPieChartForElectionTypeNElectionYear(constituencyWiseAllPartyTrends,jObj.getString("electionYear"),IConstants.MPTC_ELECTION_TYPE);
					}catch(Exception ex){
					log.debug("No data is available...");
				}
			}			
			}catch(ParseException e) {
			e.printStackTrace();
			}
	  }
	  return SUCCESS;
}
  
	public void createPieChartForElectionTypeNElectionYear(List<TeshilPartyInfoVO> result,String electionYear,String electionType)
	{		
		String chartName = result.get(0).getChartName();
		String localChart = null;
		String chartPath = "";
		String cPath = request.getContextPath();
		if(cPath.contains("PartyAnalyst"))
	      chartPath = context.getRealPath("/") + "charts\\" + chartName;	
		else
			chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
		
		Double otherPartyVotesPercent = 0D;
		ElectionTypeChartVO electionTypeChartVO = new ElectionTypeChartVO();
		String chartTitle = ""+electionType+" - "+electionYear;
		final DefaultPieDataset dataset = new DefaultPieDataset();
		Set<Color> color = new LinkedHashSet<Color>();
		String chartType = "selectedParties";
		Color[] colors = null;
		
		if(chartType.equalsIgnoreCase("selectedParties"))
			colors = new Color[5];
		int j=0;
		for(int i=0; i<result.size(); i++ )
		{		
			String partyName = result.get(i).getPartyName(); 
			Double votesPercent = Double.valueOf(result.get(i).getPercentageOfVotesWonByParty());
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
					dataset.setValue(partyName+" ["+new BigDecimal(votesPercent).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"%]",Double.parseDouble(new BigDecimal(votesPercent).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));	
			
				}	
				else
				{
					otherPartyVotesPercent+=votesPercent;
				}				
			}
		}
		if(chartType.equalsIgnoreCase("selectedParties")){			
			BigDecimal	otherPartyVotes = new BigDecimal(otherPartyVotesPercent).setScale(2, BigDecimal.ROUND_HALF_UP);			
			dataset.setValue("Oth*"+"-"+otherPartyVotes.toString()+"%",otherPartyVotes);
			colors[j] = IConstants.DEFAULT_COLOR;
			ChartProducer.createLabeledPieChart(chartTitle, dataset, chartPath , colors,true,250,250);
			localChart = chartName;
		}
		
		electionTypeChartVO.setChartName(localChart);
		electionTypeChartVO.setElectionType(electionType);
		electionTypeChartVO.setElectionYear(electionYear);	
			
	}
	
	public String getCandidateWiseConstituencyZptcOrMptcElectionTrends(){

	    if(task != null){
			try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));

		if(jObj.getString("task").equalsIgnoreCase("getAllCandidates")){
			try{				
				mandalAllElectionDetailsVO = constituencyPageService.getAllTehsilElectionLevelWinnersForAConstituency(jObj.getLong("constituencyId"),jObj.getString("candidateDetailsType") ,jObj.getLong("partyId"),jObj.getString("electionType"),jObj.getString("electionYear"),jObj.getString("constTYPE"));			
				}catch(Exception ex){
				System.out.println("Error.. ");
				log.debug("No data is available...");
			}
		}else if(jObj.getString("task").equalsIgnoreCase("getWinners")){
			try{
				mandalAllElectionDetailsVO = constituencyPageService.getAllTehsilElectionLevelWinnersForAConstituency(jObj.getLong("constituencyId"),jObj.getString("candidateDetailsType") ,jObj.getLong("partyId"),jObj.getString("electionType"),jObj.getString("electionYear"),jObj.getString("constTYPE"));
				}catch(Exception ex){
				log.debug("No data is available...");
			}
		}else if(jObj.getString("task").equalsIgnoreCase("getPartyWise")){
			try{
				mandalAllElectionDetailsVO = constituencyPageService.getAllTehsilElectionLevelWinnersForAConstituency(jObj.getLong("constituencyId"),jObj.getString("candidateDetailsType") ,jObj.getLong("partyId"),jObj.getString("electionType"),jObj.getString("electionYear"),jObj.getString("constTYPE"));
				}catch(Exception ex){
				log.debug("No data is available...");
			}
		}else if(jObj.getString("task").equalsIgnoreCase("getParties")){
			try{
				mandalAllElectionDetailsVO = constituencyPageService.getAllTehsilElectionLevelWinnersForAConstituency(jObj.getLong("constituencyId"),jObj.getString("candidateDetailsType") ,jObj.getLong("partyId"),jObj.getString("electionType"),jObj.getString("electionYear"),jObj.getString("constTYPE"));
				}catch(Exception ex){
				log.debug("No data is available...");
			}
		}	
	    }
	  return SUCCESS;
	}

	public String getCensusDetailsForAConstituency()
	{
	   if(task != null)
	   {
		   try{
				jObj = new JSONObject(getTask());
			} catch (ParseException e) {
				e.printStackTrace();
			}	
	
			Long constituencyId      = Long.parseLong(jObj.getString("constituencyId"));
			Long censusYear          = Long.parseLong(jObj.getString("censusYear"));
			Long delimitationYear    = Long.parseLong(jObj.getString("delimitationYear"));
			Long censusSelectedIndex = Long.parseLong(jObj.getString("seletedIndex"));
			String censusText        = jObj.getString("seletedText");
			String electionYear      = jObj.getString("electionYear");
			String constituencyType  = jObj.getString("constituencyType");
		try{
		    if(constituencyType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
		    {
			    censusVO = constituencyPageService.getCensusDetailsForAssemblyConstituency(constituencyId,delimitationYear,censusYear);
				constituencyRevenueVillagesVO = constituencyPageService.getMandalElectionInfoForAssemblyConstituencyForCensus(constituencyId,electionYear,IConstants.ASSEMBLY_ELECTION_TYPE);
				
				List<String> censusFieldList = new ArrayList<String>();
				censusFieldList.add(censusText);
				if(censusVO != null && censusVO.size() > 0)
				{
					censusVO.get(0).setCensusFields(censusFieldList);
					censusVO.get(0).setCensusSelectedIndex(censusSelectedIndex);
				}
				constituencyRevenueVillagesVO.setCensusVO(censusVO);
		    }
		    else if(constituencyType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			{
		    	List<CensusVO> censusVOList = null;
		    	censusVOList = constituencyPageService.getCensusDetailsForAParliamentConstituency(constituencyId,Long.parseLong(electionYear),delimitationYear,censusYear);
		    	constituencyRevenueVillagesVO = constituencyPageService.getConstituencyElecResults(constituencyId,electionYear,false);
		    	
		    	/*if(censusVOList != null && constituencyRevenueVillagesVO != null && censusVOList.size() >0 && constituencyRevenueVillagesVO.getMissingConstituencies().size() > 0)
		    	{
		    		censusVOList = constituencyPageService.removeMissingConstituencies(censusVOList,constituencyRevenueVillagesVO);
		    	}*/
		    	censusVO = constituencyPageService.setCensusVO(censusVOList,constituencyRevenueVillagesVO);
		    	
		    	List<String> censusFieldList = new ArrayList<String>();
				censusFieldList.add(censusText);
				if(censusVO != null && censusVO.size() > 0)
				{
					censusVO.get(0).setCensusFields(censusFieldList);
					censusVO.get(0).setCensusSelectedIndex(censusSelectedIndex);
				}
				constituencyRevenueVillagesVO.setCensusVO(censusVO);
		    }
		   }
			catch(Exception ex){
			log.debug("No data is available...");
		  }
	   }
	   
	   return SUCCESS;
	}
	
	public String getConstituencyAssemblyWiseResults(){
		 
		 try {
			 jObj = new JSONObject(getTask());
				System.out.println("jObj = "+jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 constituencyId=jObj.getLong("conId");
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId);
		return SUCCESS;
	}
	
  public String getParliamentConstituencyAssemblyWiseResults(){
	  
	  String param = getTask();
	  String cPath = request.getContextPath();
	  
	  try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String chartTitle = null,detailedChartTitle = null;
		String chartName = null,detailedChartName = null;
		String domainAxisName = null,detailedDomainAxisName = null;
		int chartHeight = jObj.getInt("chartHeight");
		int chartWidth = jObj.getInt("chartWidth"); 
		String includeOthers = (String)jObj.getString("others");
				
		constituencyRevenueVillagesVO = constituencyPageService.getConstituencyElecResults(jObj.getLong("constituencyId")
				, jObj.getString("electionYear"),new Boolean(includeOthers));
		if(constituencyRevenueVillagesVO != null && constituencyRevenueVillagesVO.getCandidateNamePartyAndStatus() != null && constituencyRevenueVillagesVO.getCandidateNamePartyAndStatus().size() > 0 &&
				constituencyRevenueVillagesVO.getConstituencyOrMandalWiseElectionVO() != null && constituencyRevenueVillagesVO.getConstituencyOrMandalWiseElectionVO().size() > 0){
		if(constituencyRevenueVillagesVO.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			chartName = "partyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			chartTitle = "Mandal Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			domainAxisName = "Mandals";

			detailedChartName = "detailedPartyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			detailedChartTitle = "Mandal Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			detailedDomainAxisName = "Mandals";
		}else{
			chartName = "partyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			chartTitle = "Assembly Constituencies Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			domainAxisName = "Assembly Constituencies";
			
			detailedChartName = "detailedPartyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			detailedChartTitle = "Assembly Constituencies Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			detailedDomainAxisName = "Assembly Constituencies";
		}
		
		Set<String> partiesInChart = null;
		
		String chartPath;
		String detailedChartPath;
		if(cPath.contains("PartyAnalyst"))
			 chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		else
			 chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;

        constituencyRevenueVillagesVO.setChartPath(chartName);
        partiesInChart = new LinkedHashSet<String>();
        ChartProducer.createLineChart(chartTitle, domainAxisName, "Percentages", createDataset(constituencyRevenueVillagesVO, partiesInChart), chartPath, chartHeight, chartWidth, ChartUtils.getLineChartColors(partiesInChart),true);
        
        if(cPath.contains("PartyAnalyst"))
        	 detailedChartPath = context.getRealPath("/")+ "charts\\" + detailedChartName;
        else
        	detailedChartPath = IWebConstants.CHART_URL_IN_SERVER + detailedChartName;

        constituencyRevenueVillagesVO.setDetailedChartPath(detailedChartName);
        partiesInChart = new LinkedHashSet<String>();
        ChartProducer.createLineChart(chartTitle, detailedDomainAxisName, "Percentages", createDataset(constituencyRevenueVillagesVO, partiesInChart), detailedChartPath,600,800, ChartUtils.getLineChartColors(partiesInChart),true);   
		}
		
	  return SUCCESS;
  }
  
  private CategoryDataset createDataset(ConstituencyRevenueVillagesVO constituencyObj, Set<String> partiesInChart) {
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      List<PartyElectionResultVO> pariesInfo = null;
      List<CandidatePartyInfoVO> candidatesInfo = constituencyObj.getCandidateNamePartyAndStatus();
  	  for(ConstituencyOrMandalWiseElectionVO constiInfoVO:constituencyObj.getConstituencyOrMandalWiseElectionVO()){
  		pariesInfo = constiInfoVO.getPartyElectionResultVOs();
  		for(int i=0; i<pariesInfo.size(); i++){
  			try{
  				partiesInChart.add(candidatesInfo.get(i).getParty()+"["+candidatesInfo.get(i).getRank()+"]");
  	  			dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty()+"["+candidatesInfo.get(i).getRank()+"]", constiInfoVO.getLocationName());	
  	  			pariesInfo.get(i).setVotesPercent(new BigDecimal(pariesInfo.get(i).getVotesPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP));
  			}catch (Exception e) {
  				//e.printStackTrace();
			}
  		}        		
      }
      return dataset;
  }
	
  public String getAssemblyRelatedParliamentsMandalResults(){
	   String cPath = request.getContextPath();
	   
	  try{
		  jObj = new JSONObject(getTask());
		  System.out.println("jObj = "+jObj);
	  }catch (ParseException e) {
		e.printStackTrace();
	  }
	  String chartTitle = "",detailedChartTitle = null;
	  String chartPath = "",detailedChartPath = null;
	  String chartName = "",detailedChartName = null;
	  String domainAxisName = "Mandals",detailedDomainAxisName = null;
	    String censusYear          = jObj.getString("censusYear");
		String delimitationYear    = jObj.getString("delimitationYear");
		String censusSelectedIndex = jObj.getString("seletedIndex");
		String censusText        = jObj.getString("seletedText");
		if(delimitationYear != null && delimitationYear.trim().length()>0 && censusYear != null && censusYear.trim().length() > 0){
	      censusVO = constituencyPageService.getCensusDetailsForAssemblyConstituency(jObj.getLong("constituencyId"),new Long(delimitationYear),new Long(censusYear));
	      constituencyRevenueVillagesVO = constituencyPageService.getMandalElectionInfoForAConstituencyForCensus(jObj.getLong("constituencyId"),jObj.getString("electionYear"),IConstants.PARLIAMENT_ELECTION_TYPE, false);
	      List<String> censusFieldList = new ArrayList<String>();
			censusFieldList.add(censusText);
			if(censusVO != null && censusVO.size() > 0)
			{
				censusVO.get(0).setCensusFields(censusFieldList);
				censusVO.get(0).setCensusSelectedIndex(new Long(censusSelectedIndex));
			}
			constituencyRevenueVillagesVO.setCensusVO(censusVO);
			 return SUCCESS;
		}else{
	      constituencyRevenueVillagesVO = constituencyPageService.getMandalElectionInfoForAConstituency(jObj.getLong("constituencyId"),jObj.getString("electionYear"),IConstants.PARLIAMENT_ELECTION_TYPE, false);
		}
	  if(constituencyRevenueVillagesVO != null){
		  chartTitle = "Mandal Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" Parliament Constituency In "+jObj.getString("electionYear");
		  chartName = "mandalWiseParliamentElectionsResults_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			
		  if(cPath.contains("PartyAnalyst"))
		      chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			else
		      chartPath = IWebConstants.CHART_URL_IN_SERVER+ chartName;

		  Set<String> partiesInChart = null;
		  partiesInChart = new LinkedHashSet<String>();
		  ChartProducer.createLineChart(chartTitle, domainAxisName, "Percentages", createDataset(constituencyRevenueVillagesVO, partiesInChart), chartPath,350,700, ChartUtils.getLineChartColors(partiesInChart),true);
		  constituencyRevenueVillagesVO.setChartPath(chartName);
		  
		  detailedChartTitle = "Mandal Wise Election Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" Parliament Constituency In "+jObj.getString("electionYear");
		  detailedChartName = "detailedMandalWiseParliamentElectionsResults_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
		  
		  if(cPath.contains("PartyAnalyst"))
		   detailedChartPath = context.getRealPath("/")+ "charts\\" + detailedChartName;
		  else
		   detailedChartPath = IWebConstants.CHART_URL_IN_SERVER + detailedChartName;

		   partiesInChart = new LinkedHashSet<String>();
		  ChartProducer.createLineChart(detailedChartTitle, detailedDomainAxisName, "Percentages", createDataset(constituencyRevenueVillagesVO, partiesInChart), detailedChartPath,600,800, ChartUtils.getLineChartColors(partiesInChart),true);
		  constituencyRevenueVillagesVO.setDetailedChartPath(detailedChartName);
	  }
	 
	  return SUCCESS;
 }
  
  public String getCandidateNominationDetails(){
	  
	  try{
		  jObj = new JSONObject(getTask());
		  System.out.println("jObj = "+jObj);
	  }catch (ParseException e) {
		e.printStackTrace();
	  }
	  
	  Long constituencyId = jObj.getLong("constituencyId");
	  
	  constituencyNominationsVO = constituencyPageService.getCandidateNominationCompleteDetailsInConstituencyForLatestElection(constituencyId);
	  
	  return Action.SUCCESS;
  }
  
  
  public String getConstituencyElectionYearsWithAssets(){
	  
	  try{
		  jObj = new JSONObject(getTask());
		  System.out.println("jObj = "+jObj);
	  }catch (ParseException e) {
		e.printStackTrace();
	  }
	  
	  Long constituencyId = jObj.getLong("constituencyId");
	  
	  constituencyElectionsVO = commentsDataService.getElectionYearsForConstituency(constituencyId, true);
	 	  
	  return Action.SUCCESS;
  }
  
  public String getConstituencyAssetsAndLiabilities()
  {
	  
	  try{
		  jObj = new JSONObject(getTask());
		  System.out.println("jObj = "+jObj);
	  }catch (ParseException e) {
		e.printStackTrace();
	  }
	  
	  Long constiElecId = jObj.getLong("constiElecId");
	  
	  constituencyAssetsVO = constituencyPageService.getCandidateNominations(constiElecId);
	  
	  return Action.SUCCESS;
  }
  public String setEmailAlertsForUser(){
		
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			 if(jObj.getString("task").equalsIgnoreCase("constituencySubscriptionDetails"))
			 {
				 
				 if(regVO != null){
					 Long id=jObj.getLong("id");
					 Long userId=regVO.getRegistrationID();
					 result = constituencyManagementService.subscriberDetails(id,userId);
				 }
			 }
			  else if  (jObj.getString("task").equalsIgnoreCase("constituencyUnsubscriptionDetails"))
			  {
				  if(regVO != null)
				  {
					  Long id=jObj.getLong("id");
					  Long userId=regVO.getRegistrationID();
					  result = constituencyManagementService.unSubscriptionDetails(id,userId);
				  }
			  }
			} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

  
  
}
