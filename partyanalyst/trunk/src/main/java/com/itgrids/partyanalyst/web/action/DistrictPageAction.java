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
import javax.servlet.http.HttpSession;
import javax.swing.Icon;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DistrictPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String districtId;
	private String districtName;
	private SelectOptionVO stateDetails;
	private HttpServletRequest request;
	private ServletContext context;
	private IStaticDataService staticDataService;	
	private IRegionServiceData regionServiceDataImp;
	private ConstituenciesStatusVO constituenciesStatusVO;
	private List<SelectOptionVO> constituencies,electionTypes ;
	private List<MandalVO> mandals ;
	private MandalAllElectionDetailsVO mptcElectionDetails;
	private MandalAllElectionDetailsVO zptcElectionDetails;
	private MandalAllElectionDetailsVO candidateTrendzReportVO;
	private String constituencyId;
	private final Logger log = Logger.getLogger(DistrictPageAction.class);
	private List<CandidateDetailsVO> candidateDetailsVO;
	private List newConstituencies;
	private List<SelectOptionVO> electionYears,mptcElectionYears,muncipalElectionYears;
	private String task = null,electionYear=null,electionType=null;
	JSONObject jObj = null;
	private CandidateDetailsVO  parliamentCandidateDetailsVo;
	private List<TeshilPartyInfoVO> partyDetails,getMptcPartyDetails;
	private TeshilPartyInfoVO getmuncipalPartyDetails,getCorporationPartyDetails;
	private String disId,eleType,eleYear;
	private DistrictWisePartyResultVO districtWisePartyResultVO; 
	private List<SelectOptionVO> electionsInDistrict;
	private DistrictWisePartyResultVO allPartiesPositionsInElection;
	private String muncipalityErrorMsg;
	private String mptcElectionType,zptcElectionType,muncipalityElectionType,corporationElectionType;
	private Long mptcElectionTypeId,zptcElectionTypeId,muncipalityElectionTypeId,corporationElectionTypeId;
	private NavigationVO navigationVO;
	private List<ProblemBeanVO> problemBean;
	private IProblemManagementReportService problemManagementReportService;
	private IAnanymousUserService ananymousUserService;
	private DataTransferVO userDetails;
	private HttpSession session;
	private String userType = null;	
	private NavigationVO messageTypes;
	
	
	
	public NavigationVO getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(NavigationVO messageTypes) {
		this.messageTypes = messageTypes;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
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
	public SelectOptionVO getStateDetails() {
		return stateDetails;
	}
	public void setStateDetails(SelectOptionVO stateDetails) {
		this.stateDetails = stateDetails;
	}
	public TeshilPartyInfoVO getGetCorporationPartyDetails() {
		return getCorporationPartyDetails;
	}
	public void setGetCorporationPartyDetails(
			TeshilPartyInfoVO getCorporationPartyDetails) {
		this.getCorporationPartyDetails = getCorporationPartyDetails;
	}
	public Long getCorporationElectionTypeId() {
		return corporationElectionTypeId;
	}
	public void setCorporationElectionTypeId(Long corporationElectionTypeId) {
		this.corporationElectionTypeId = corporationElectionTypeId;
	}
	public String getMuncipalityErrorMsg() {
		return muncipalityErrorMsg;
	}
	public void setMuncipalityErrorMsg(String muncipalityErrorMsg) {
		this.muncipalityErrorMsg = muncipalityErrorMsg;
	}
	public String getDisId() {
		return disId;
	}
	public List<SelectOptionVO> getMuncipalElectionYears() {
		return muncipalElectionYears;
	}
	public void setMuncipalElectionYears(List<SelectOptionVO> muncipalElectionYears) {
		this.muncipalElectionYears = muncipalElectionYears;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getEleType() {
		return eleType;
	}
	public TeshilPartyInfoVO getGetmuncipalPartyDetails() {
		return getmuncipalPartyDetails;
	}
	public void setGetmuncipalPartyDetails(TeshilPartyInfoVO getmuncipalPartyDetails) {
		this.getmuncipalPartyDetails = getmuncipalPartyDetails;
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
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public List<TeshilPartyInfoVO> getGetMptcPartyDetails() {
		return getMptcPartyDetails;
	}
	public MandalAllElectionDetailsVO getCandidateTrendzReportVO() {
		return candidateTrendzReportVO;
	}
	public void setCandidateTrendzReportVO(
			MandalAllElectionDetailsVO candidateTrendzReportVO) {
		this.candidateTrendzReportVO = candidateTrendzReportVO;
	}
	public void setGetMptcPartyDetails(List<TeshilPartyInfoVO> getMptcPartyDetails) {
		this.getMptcPartyDetails = getMptcPartyDetails;
	}
	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}
	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
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
	public List<TeshilPartyInfoVO> getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(List<TeshilPartyInfoVO> partyDetails) {
		this.partyDetails = partyDetails;
	}
	
	public CandidateDetailsVO getParliamentCandidateDetailsVo() {
		return parliamentCandidateDetailsVo;
	}
	public void setParliamentCandidateDetailsVo(
			CandidateDetailsVO parliamentCandidateDetailsVo) {
		this.parliamentCandidateDetailsVo = parliamentCandidateDetailsVo;
	}

	public MandalAllElectionDetailsVO getMptcElectionDetails() {
		return mptcElectionDetails;
	}

	public void setMptcElectionDetails(
			MandalAllElectionDetailsVO mptcElectionDetails) {
		this.mptcElectionDetails = mptcElectionDetails;
	}
	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}
	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}
	public MandalAllElectionDetailsVO getZptcElectionDetails() {
		return zptcElectionDetails;
	}

	public void setZptcElectionDetails(
			MandalAllElectionDetailsVO zptcElectionDetails) {
		this.zptcElectionDetails = zptcElectionDetails;
	}
	public List getNewConstituencies() {
		return newConstituencies;
	}

	public void setNewConstituencies(List newConstituencies) {
		this.newConstituencies = newConstituencies;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public List<CandidateDetailsVO> getCandidateDetailsVO() {
		return candidateDetailsVO;
	}

	public void setCandidateDetailsVO(List<CandidateDetailsVO> candidateDetailsVO) {
		this.candidateDetailsVO = candidateDetailsVO;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public ConstituenciesStatusVO getConstituenciesStatusVO() {
		return constituenciesStatusVO;
	}

	public void setConstituenciesStatusVO(
			ConstituenciesStatusVO constituenciesStatusVO) {
		this.constituenciesStatusVO = constituenciesStatusVO;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<MandalVO> getMandals() {
		return mandals;
	}

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}

	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public DistrictWisePartyResultVO getDistrictWisePartyResultVO() {
		return districtWisePartyResultVO;
	}
	public void setDistrictWisePartyResultVO(
			DistrictWisePartyResultVO districtWisePartyResultVO) {
		this.districtWisePartyResultVO = districtWisePartyResultVO;
	}
	
	public List<SelectOptionVO> getElectionsInDistrict() {
		return electionsInDistrict;
	}
	public void setElectionsInDistrict(List<SelectOptionVO> electionsInDistrict) {
		this.electionsInDistrict = electionsInDistrict;
	}
	
	public DistrictWisePartyResultVO getAllPartiesPositionsInElection() {
		return allPartiesPositionsInElection;
	}
	public void setAllPartiesPositionsInElection(
			DistrictWisePartyResultVO allPartiesPositionsInElection) {
		this.allPartiesPositionsInElection = allPartiesPositionsInElection;
	}
	public NavigationVO getNavigationVO() {
		return navigationVO;
	}
	public void setNavigationVO(NavigationVO navigationVO) {
		this.navigationVO = navigationVO;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	
	public String execute() throws Exception
	{
		districtId = request.getParameter("districtId");
		districtName = request.getParameter("districtName");
		mptcElectionType = IConstants.MPTC_ELECTION_TYPE;
		zptcElectionType = IConstants.ZPTC_ELECTION_TYPE;
		muncipalityElectionType = IConstants.MUNCIPLE_ELECTION_TYPE;
		corporationElectionType = IConstants.CORPORATION_ELECTION_TYPE;
		electionTypes = staticDataService.getAllElectionTypes();
		for(SelectOptionVO eleTypes : electionTypes){
			if(eleTypes.getName().equalsIgnoreCase(mptcElectionType)){
				mptcElectionTypeId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(zptcElectionType)){
				zptcElectionTypeId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(muncipalityElectionType)){
				muncipalityElectionTypeId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(corporationElectionType)){
				corporationElectionTypeId = eleTypes.getId();
			}
		}
		
		stateDetails = staticDataService.getStateOfADistrict(new Long(districtId));
		constituenciesStatusVO = staticDataService.getConstituenciesWinnerInfo(Long.parseLong(districtId));	
        if(constituenciesStatusVO != null && constituenciesStatusVO.getElectionYear() != null)
		electionYear = constituenciesStatusVO.getElectionYear();
        if(constituenciesStatusVO != null && constituenciesStatusVO.getElectionType() != null)
		electionType = constituenciesStatusVO.getElectionType();
		mandals = staticDataService.getMandalsForDistrict(Long.parseLong(districtId));
		if(constituenciesStatusVO != null && constituenciesStatusVO.getParliamentConstituencies() != null)
		parliamentCandidateDetailsVo = staticDataService.getAllParliamentWinningCandidatesForADistrict(Long.parseLong(districtId),
				constituenciesStatusVO.getParliamentConstituencies());
		if(mandals == null){
				if(log.isDebugEnabled())
					log.error("Failed to get Mandal Data");
				return Action.ERROR;
		}

		log.debug("District Id = "+districtId+" & District Name = "+districtName);
		
		navigationVO = staticDataService.findHirarchiForNavigation(new Long(districtId), IConstants.DISTRICT_LEVEL);
		
		List<Long> listOfDistricts = new ArrayList<Long>();
		listOfDistricts.add(Long.parseLong(districtId));
		problemBean = problemManagementReportService.getAllProblemsForGivenLocation(listOfDistricts,IConstants.DISTRICT_LEVEL).getApprovalProblems();
			
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfDistricts,IConstants.DISTRICT_LEVEL,IConstants.MAX_ANONYMOUS_USER_DISPLAY,0l,IConstants.ALL);
		}else{
			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfDistricts,IConstants.DISTRICT_LEVEL,IConstants.MAX_ANONYMOUS_USER_DISPLAY,user.getRegistrationID(),IConstants.ALL);		
		}
		//Free User
		
		if(user!=null && user.getUserStatus() != null && user.getUserStatus().toString().equalsIgnoreCase(IConstants.FREE_USER)){
			userDetails.setLoginStatus("true");
			userDetails.setUserId(user.getRegistrationID());
		}else{
			userDetails.setLoginStatus("false");
		}
		
		messageTypes = ananymousUserService.getAllMessageTypes();
		return Action.SUCCESS;
	
	}
		
	public String getMptcAndZptcInfoDistrictAction(){
		if(task != null){
			try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));
		
			if(jObj.getString("task").equalsIgnoreCase("getAllElectionYears"))
			{
			try{
				electionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("eleType")));		
			}catch(Exception e){
				log.debug("No data is available...");
			}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllMptcElectionYears"))
			{
				try{
				mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("eleType")));
			}catch(Exception e){
				log.debug("No data is available...");
			}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartyDetails"))
			{
				try{
				partyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),jObj.getString("electionYear"),new Long(jObj.getString("districtId")));
				}catch(Exception e){
					log.debug("No data is available...");
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getMptcPartyDetails"))
			{
				try{
					getMptcPartyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),jObj.getString("electionYear"),new Long(jObj.getString("districtId")));
				}catch(Exception e){
					log.debug("No data is available...");
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllZptcParties"))
			{
				electionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("electionTypeId")));
				try{
					partyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),electionYears.get(0).getId().toString(),new Long(jObj.getString("districtId")));
				}catch(Exception e){
					log.debug("No data is available...");
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllMptcParties"))
			{
				electionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("electionTypeId")));
				try{
					getMptcPartyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),electionYears.get(0).getId().toString(),new Long(jObj.getString("districtId")));
				}catch(Exception e){
					log.debug("No data is available...");															
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllMuncipalElectionYears"))
			{
				try{
				muncipalElectionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("eleType")));
			}catch(Exception e){
				log.debug("No data is available...");
			}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getmuncipalPartyDetails"))
			{
				try{
					getmuncipalPartyDetails = staticDataService.getAllPartyTrendsForAllMuncipalitiesInADistrict(jObj.getString("electionType"),new Long(jObj.getString("districtId")));
					if(getmuncipalPartyDetails.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
						muncipalityErrorMsg = "Data is Not Available.";
					}else if(getmuncipalPartyDetails.getResultStatus().getResultCode()==ResultCodeMapper.DATA_NOT_FOUND){
						muncipalityErrorMsg = "Failed to retrive data.";
					}
				}catch(Exception e){
					log.debug("No data is available...");
				}		
			}	
			else if(jObj.getString("task").equalsIgnoreCase("getCorporationPartyDetails"))
			{
				try{
					getCorporationPartyDetails = staticDataService.getAllPartyTrendsForAllMuncipalitiesInADistrict(jObj.getString("electionType"),new Long(jObj.getString("districtId")));
					if(getmuncipalPartyDetails.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
						muncipalityErrorMsg = "Data is Not Available.";
					}else if(getmuncipalPartyDetails.getResultStatus().getResultCode()==ResultCodeMapper.DATA_NOT_FOUND){
						muncipalityErrorMsg = "Failed to retrive data.";
					}
				}catch(Exception e){
					log.debug("No data is available...");
				}	
			}
		}		
		return SUCCESS;  
	}	
	
	public String getCandidateTrendzForDistrict(){
		if(task != null){
			try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));
			if(jObj.getString("task").equalsIgnoreCase("getAllCandidates"))
			{
				try{
					if(jObj.getString("electionType").equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){
						candidateTrendzReportVO = staticDataService.getAllZptcsCandidatesForADistrictForSelectedYear(new Long(jObj.getString("districtId")),jObj.getString("electionYear"),0);
					}else{
						candidateTrendzReportVO = staticDataService.getAllMptcsCandidatesForADistrictForSelectedYear(new Long(jObj.getString("districtId")),jObj.getString("electionYear"),0);
					}
				}catch(Exception e){
					log.debug("No data is available...");															
				}
				
			}
			else if(jObj.getString("task").equalsIgnoreCase("getWinners"))
			{
				try{
					if(jObj.getString("electionType").equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){
						candidateTrendzReportVO = staticDataService.getAllZptcWinnerForADistrictForLatestYear(new Long(jObj.getString("districtId")),jObj.getString("electionYear"));
					}else{
						candidateTrendzReportVO = staticDataService.getAllMptcWinnerForADistrictForLatestYear(new Long(jObj.getString("districtId")),jObj.getString("electionYear"));
					}
				}catch(Exception e){
					log.debug("No data is available...");															
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartyWise"))
			{
				try{
					if(jObj.getString("electionType").equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){
						candidateTrendzReportVO = staticDataService.getAllZptcsMptcsForADistrictForAPartyForSelectedYear(jObj.getLong("districtId"),
								jObj.getString("electionYear"),jObj.getLong("partyId"),0,0, IConstants.ZPTC_ELECTION_TYPE);
					}else{
						candidateTrendzReportVO = staticDataService.getAllZptcsMptcsForADistrictForAPartyForSelectedYear(jObj.getLong("districtId"),
								jObj.getString("electionYear"),jObj.getLong("partyId"),0,0, IConstants.MPTC_ELECTION_TYPE);
					}
				}catch(Exception e){
					log.debug("No data is available...");															
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getParties")){
				try{
					candidateTrendzReportVO = staticDataService.getAllPartiesForAParticularElection(new Long(jObj.getString("districtId")),jObj.getString("electionType"),jObj.getString("electionYear"));
				}catch(Exception e){
					log.debug("No data is available...");															
				}
			}		
		}
		return SUCCESS; 
	}
	
	public String getDistrictWiseAllElectionResultsForAllParties(){
		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		districtWisePartyResultVO = staticDataService.getDistrictWiseElectionReport(jObj.getLong("electionTypeId"),jObj.getLong("districtId"));
		List<PartyResultVO> allElectionResults = districtWisePartyResultVO.getPartyElectionResultsList();
		if(allElectionResults.size() == 0)
			return SUCCESS;
		String chartName = "allPartiesDistrictWisePerformanceIn"+jObj.getString("electionType")+"Elections_"+jObj.getLong("districtId")+"_"+jObj.getLong("electionTypeId")+".png";
        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
        districtWisePartyResultVO.setChartPath(chartName);
        String electionType = jObj.getString("electionType");
        //Set<Color> colorsSet = null;
        
        if(electionType.equalsIgnoreCase("Select Election Type"))
        	electionType = "All ";
        //colorsSet = new LinkedHashSet<Color>();
        ChartColorsAndDataSetVO chartColorsAndDataSetVO1 = createDataset(allElectionResults);
         
        ChartProducer.createLineChart("All Parties Performance In "+electionType+" Elections Of "+jObj.getString("districtName")
        		+" District", "Elections", "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO1.getDataSet(), chartPath, 260, 700, new ArrayList<Color>(chartColorsAndDataSetVO1.getColorsSet()),false);	
		
		//For Detailed Chart
        String detailedChartName = "detailedChartForAllPartiesDistrictWisePerformanceIn"+jObj.getString("electionType")+"Elections_"+jObj.getLong("districtId")+"_"+jObj.getLong("electionTypeId")+".png";
        String detailedChartPath = context.getRealPath("/")+ "charts\\" + detailedChartName;
        districtWisePartyResultVO.setDetailedChartPath(detailedChartName);
        String detailedChartElectionType = jObj.getString("electionType");
        if(detailedChartElectionType.equalsIgnoreCase("Select Election Type"))
        	detailedChartElectionType = "All ";
        //colorsSet = new LinkedHashSet<Color>();
        ChartColorsAndDataSetVO chartColorsAndDataSetVO2 = createDataset(allElectionResults);
        ChartProducer.createLineChart("All Parties Performance In "+detailedChartElectionType+" Elections Of "+jObj.getString("districtName")
        		+" District", "Elections", "Percentages", (DefaultCategoryDataset) chartColorsAndDataSetVO2.getDataSet(), detailedChartPath, 600, 800, new ArrayList<Color>(chartColorsAndDataSetVO2.getColorsSet()),false);	

        
        return SUCCESS;
	}
	
	public String getElectionScopesOfDistrict(){
		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long districtID = new Long(jObj.getString("distId"));
		
		//electionsInDistrict = staticDataService.getAllElectionScopes();
		electionsInDistrict = staticDataService.getAllElectionScopes(districtID);
		electionsInDistrict.add(0, new SelectOptionVO(0l, "Select Election Type"));
		return SUCCESS;
	}
	
	private ChartColorsAndDataSetVO createDataset(List<PartyResultVO> allElectionResults) {
		ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		Set<Color> colorsSet = new LinkedHashSet<Color>();
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ElectionResultVO> partiesElectionResults = new ArrayList<ElectionResultVO>();
        ElectionResultVO partiesElecResultForGraph = null;
        colorsSet = new LinkedHashSet<Color>();
        int i=0;
        for(PartyResultVO partyResultVO:allElectionResults){
        	for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
        		partiesElecResultForGraph = new ElectionResultVO();
        		partiesElecResultForGraph.setPercentage(result.getPercentage());
        		partiesElecResultForGraph.setElectionYear(result.getElectionYear()+" "+result.getElectionType());
        		partiesElecResultForGraph.setPartyName(partyResultVO.getPartyName());
        		partiesElectionResults.add(partiesElecResultForGraph);
        	}
        	if(++i == 10)
        		break; 
        }
        
                
        Collections.sort(partiesElectionResults, new ElectionResultComparator());
        
        for(ElectionResultVO graphInfo:partiesElectionResults){
        	
        	if(IConstants.TDP.equalsIgnoreCase(graphInfo.getPartyName()))
			{	colorsSet.add(IConstants.TDP_COLOR);
				log.debug("TDP ADDED");
			}
			
        	else
        		if(IConstants.INC.equalsIgnoreCase(graphInfo.getPartyName()))
        		{	colorsSet.add(IConstants.INC_COLOR);
        		log.debug("INC ADDEd");
        		}
            	else
            		if(IConstants.BJP.equalsIgnoreCase(graphInfo.getPartyName()))
            		{	colorsSet.add(IConstants.BJP_COLOR);
            			log.debug("BJP ADDEd");
            		}
                	else
                		if(IConstants.PRP.equalsIgnoreCase(graphInfo.getPartyName()))
                    		{colorsSet.add(IConstants.PRP_COLOR);
                    		log.debug("PRP ADDEd");
                    		}
                    	else
                    		if(IConstants.TRS.equalsIgnoreCase(graphInfo.getPartyName()))
                        		{
                    			colorsSet.add(IConstants.TRS_COLOR);
                    			log.debug("TRS ADDEd");
                    			}
                    		else
	                    		if(IConstants.AIMIM.equalsIgnoreCase(graphInfo.getPartyName()))
	                        		{
	                    			colorsSet.add(IConstants.AIMIM_COLOR);
	                    			log.debug("AIMIM ADDEd");
	                    			}
	                    		else
		                    		if(IConstants.CPI.equalsIgnoreCase(graphInfo.getPartyName()))
		                        		{
		                    			colorsSet.add(IConstants.CPI_COLOR);
		                    			log.debug("CPI ADDEd");
		                    			}
                    		else
		                    	{colorsSet.add(null);
		                    	log.debug("Default ADDEd");
		                    	}
        	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyName(),
           			graphInfo.getElectionYear());
        }
        chartColorsAndDataSetVO.setDataSet(dataset);
        chartColorsAndDataSetVO.setColorsSet(colorsSet);
        return chartColorsAndDataSetVO;
    }
	
	public String getAllElectionsInDistrict(){
		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		electionsInDistrict = staticDataService.getAllElectionsInDistrict(jObj.getLong("districtId"));
		return SUCCESS;
	}
	
	public String getAllPartiesPositionsInDistrictElection(){
		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Set<Color> colorsSet = new LinkedHashSet<Color>();
	
		allPartiesPositionsInElection = staticDataService.getAllPartiesPositionsInDistrictElection(jObj.getLong("electionId"), jObj.getLong("districtId"));
		List<PartyPositionsVO> partyPositions = allPartiesPositionsInElection.getPartiesPositionsInElection();
		String chartName = "allPartiesDistrictWisePositionsInElection_"+jObj.getLong("districtId")+"_"+jObj.getLong("electionId")+".png";
        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
        ChartColorsAndDataSetVO chartColorsAndDataSetVO = createDatasetForPartyPositions(partyPositions);
        allPartiesPositionsInElection.setPasitionsChart(chartName);
        ChartProducer.createLineChart("All Parties Positions In "+jObj.getString("electionTypeYear")+" Of "+jObj.getString("districtName")
        		+" District", "Positions", "No. Of Seats", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath, 260, 400, new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),false);
		return SUCCESS;
	}

	private ChartColorsAndDataSetVO createDatasetForPartyPositions(List<PartyPositionsVO> PartyPositionsVO) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
        Set<Color> colorsSet = new LinkedHashSet<Color>();
        int i=0;
        for(PartyPositionsVO partyPositionsVO:PartyPositionsVO){
        	dataset.addValue(partyPositionsVO.getTotalSeatsWon(), partyPositionsVO.getPartyName(),"Seats Won");
        	dataset.addValue(partyPositionsVO.getSecondPosWon(), partyPositionsVO.getPartyName(),"2nd Pos");
        	dataset.addValue(partyPositionsVO.getThirdPosWon(), partyPositionsVO.getPartyName(),"3rd Pos");
        	dataset.addValue(partyPositionsVO.getFourthPosWon(), partyPositionsVO.getPartyName(),"4th Pos");
        	if(IConstants.INC.equalsIgnoreCase(partyPositionsVO.getPartyName()))
    		{	colorsSet.add(IConstants.INC_COLOR);
    		log.debug("INC ADDEd");
    		}
        	else
        		if(IConstants.BJP.equalsIgnoreCase(partyPositionsVO.getPartyName()))
        		{	colorsSet.add(IConstants.BJP_COLOR);
        		log.debug("BJP ADDEd");
        		}
            	else
            		if(IConstants.PRP.equalsIgnoreCase(partyPositionsVO.getPartyName()))
                		{colorsSet.add(IConstants.PRP_COLOR);
                		log.debug("PRP ADDEd");
                		}
                	else
                		if(IConstants.TRS.equalsIgnoreCase(partyPositionsVO.getPartyName()))
                    		{
                			colorsSet.add(IConstants.TRS_COLOR);
                			log.debug("TRS ADDEd");
                			}
                		else
                    		if(IConstants.AIMIM.equalsIgnoreCase(partyPositionsVO.getPartyName()))
                        		{
                    			colorsSet.add(IConstants.AIMIM_COLOR);
                    			log.debug("AIMIM ADDEd");
                    			}
                    		else
	                    		if(IConstants.CPI.equalsIgnoreCase(partyPositionsVO.getPartyName()))
	                        		{
	                    			colorsSet.add(IConstants.CPI_COLOR);
	                    			log.debug("CPI ADDEd");
	                    			}
                		else
	                    	{colorsSet.add(null);
	                    	log.debug("Default ADDEd");
	                    	}
        	if(++i == 10)
        		break; 
        }
        chartColorsAndDataSetVO.setDataSet(dataset);
        chartColorsAndDataSetVO.setColorsSet(colorsSet);
        return chartColorsAndDataSetVO;
    }
}
