package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.DataVerificationVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VotersAnalysisAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private ConstituencyManagementVO constituencyManagementVO;
	
	

	private HttpSession session;
	
	private String task;
	JSONObject jObj;
	
	private RegionServiceDataImp regionServiceDataImp;
	
	private IVotersAnalysisService votersAnalysisService;
	
	private List<SelectOptionVO> namesList;
	
	private static final Logger log = Logger.getLogger(VotersAnalysisAction.class);

	private VotersInfoForMandalVO votersInfo;
	
	private ImportantFamiliesInfoVo importantFamiliesInfoVo;
	
	private ProblemBeanVO problemBean;
	
    private IVoterReportService voterReportService;

	private List<VoterHouseInfoVO> votersFamilyInfo;
	
	private PartyVotesEarnedVO partyVotesEarnedVO;
	
	private List<PartyVotesEarnedVO> partyVotesEarnedVOList;
	
	public List<VotersDetailsVO> countList;
	
	private List<ProblemBeanVO> problemDetails;
	
	private ProblemBeanVO problemBeanVO;
	
	private List<VotersInfoForMandalVO> previousDetailsList;
	
	private List<SelectOptionVO> electionYearsList; 
	
	private CrossVotingConsolidateVO crossVotingConsolidateVO;
	
	private Long parliamentConstituencyId;
	
	private List<SelectOptionVO> userCategoriesList;
	
	private List<InfluencingPeopleVO> influencingPeopleList;
	
	private ResultStatus resultStatus;
	
	private List<InfluencingPeopleBeanVO> influencingPeopleCount;
	
	private SelectOptionVO selectOptionVO;
	
	private CadreManagementService cadreManagementService;
	
	private Long assemblyLocalEleBodyId;
	
	private IVoterModificationService voterModificationService;
	
    private EntitlementsHelper entitlementsHelper;
    
    private String isLocalityExist;
    
    private List<VoterCastInfoVO> castList;
	
    private List<VotersDetailsVO> ageRangeList;
    
    private IUserVoterService userVoterService;
	
    private Long latestPublicationId;
    
    private List<MandalInfoVO> mandalInfoVOsList;
    
    private List<SelectOptionVO> resultData,ageDetailsList;
    
    private VoterInfo voterInfo;
    
    private List<VotersDetailsVO> casteDetailsVO;
    private DataVerificationVO dataVerificationVO;
	private List<OptionVO> ageList;
	
	private Long constituencyId;
	
	private Long mandalId,panchayatId,boothId;
	private String type;
    
	public List<VotersDetailsVO> getCasteDetailsVO() {
		return casteDetailsVO;
	}

	public void setCasteDetailsVO(List<VotersDetailsVO> casteDetailsVO) {
		this.casteDetailsVO = casteDetailsVO;
	}

	public VoterInfo getVoterInfo() {
		return voterInfo;
	}

	public void setVoterInfo(VoterInfo voterInfo) {
		this.voterInfo = voterInfo;
	}

	public List<VotersDetailsVO> getAgeRangeList() {
		return ageRangeList;
	}

	public void setAgeRangeList(List<VotersDetailsVO> ageRangeList) {
		this.ageRangeList = ageRangeList;
	}

	public IUserVoterService getUserVoterService() {
		return userVoterService;
	}

	public void setUserVoterService(IUserVoterService userVoterService) {
		this.userVoterService = userVoterService;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public List<InfluencingPeopleBeanVO> getInfluencingPeopleCount() {
		return influencingPeopleCount;
	}

	public void setInfluencingPeopleCount(
			List<InfluencingPeopleBeanVO> influencingPeopleCount) {
		this.influencingPeopleCount = influencingPeopleCount;
	}

	
	private List<VotersInfoForMandalVO> previousDetailsListForHamlet;
	
	
	

	public List<VotersInfoForMandalVO> getPreviousDetailsListForHamlet() {
		return previousDetailsListForHamlet;
	}

	public void setPreviousDetailsListForHamlet(
			List<VotersInfoForMandalVO> previousDetailsListForHamlet) {
		this.previousDetailsListForHamlet = previousDetailsListForHamlet;
	}

	public List<InfluencingPeopleVO> getInfluencingPeopleList() {
		return influencingPeopleList;
	}

	public void setInfluencingPeopleList(
			List<InfluencingPeopleVO> influencingPeopleList) {
		this.influencingPeopleList = influencingPeopleList;
	}
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}


	public List<SelectOptionVO> getUserCategoriesList() {
		return userCategoriesList;
	}

	public void setUserCategoriesList(List<SelectOptionVO> userCategoriesList) {
		this.userCategoriesList = userCategoriesList;
	}

	public List<VotersInfoForMandalVO> getPreviousDetailsList() {
		return previousDetailsList;
	}

	public void setPreviousDetailsList(List<VotersInfoForMandalVO> previousDetailsList) {
		this.previousDetailsList = previousDetailsList;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}


	private IProblemManagementService problemManagementService;
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	
	public List<ProblemBeanVO> getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(List<ProblemBeanVO> problemDetails) {
		this.problemDetails = problemDetails;
	}

	public List<SelectOptionVO> getNamesList() {
		return namesList;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public void setNamesList(List<SelectOptionVO> namesList) {
		this.namesList = namesList;
	}
	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}	
	private List<SelectOptionVO> publicationData;
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
		ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	

	public List<SelectOptionVO> getPublicationData() {
		return publicationData;
	}

	public void setPublicationData(List<SelectOptionVO> publicationData) {
		this.publicationData = publicationData;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	
	public PartyVotesEarnedVO getPartyVotesEarnedVO() {
		return partyVotesEarnedVO;
	}
	public void setPartyVotesEarnedVO(PartyVotesEarnedVO partyVotesEarnedVO) {
		this.partyVotesEarnedVO = partyVotesEarnedVO;
	}
	
	public List<PartyVotesEarnedVO> getPartyVotesEarnedVOList() {
		return partyVotesEarnedVOList;
	}
	public void setPartyVotesEarnedVOList(
			List<PartyVotesEarnedVO> partyVotesEarnedVOList) {
		this.partyVotesEarnedVOList = partyVotesEarnedVOList;
	}
	public List<VotersDetailsVO> getCountList() {
		return countList;
	}

	public void setCountList(List<VotersDetailsVO> countList) {
		this.countList = countList;
	}
	
	public ProblemBeanVO getProblemBean() {
		return problemBean;
	}

	public void setProblemBean(ProblemBeanVO problemBean) {
		this.problemBean = problemBean;
	}
	
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	
	public List<SelectOptionVO> getElectionYearsList() {
		return electionYearsList;
	}
	public void setElectionYearsList(List<SelectOptionVO> electionYearsList) {
		this.electionYearsList = electionYearsList;
	}
	public CrossVotingConsolidateVO getCrossVotingConsolidateVO() {
		return crossVotingConsolidateVO;
	}
	public void setCrossVotingConsolidateVO(
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}

	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}

	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public Long getAssemblyLocalEleBodyId() {
		return assemblyLocalEleBodyId;
	}
	public void setAssemblyLocalEleBodyId(Long assemblyLocalEleBodyId) {
		this.assemblyLocalEleBodyId = assemblyLocalEleBodyId;
	}

	public IVoterModificationService getVoterModificationService() {
		return voterModificationService;
	}

	public void setVoterModificationService(
			IVoterModificationService voterModificationService) {
		this.voterModificationService = voterModificationService;
	}

	public String getIsLocalityExist() {
		return isLocalityExist;
	}

	public void setIsLocalityExist(String isLocalityExist) {
		this.isLocalityExist = isLocalityExist;
	}

	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}

	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

	public List<VoterCastInfoVO> getCastList() {
		return castList;
	}

	public void setCastList(List<VoterCastInfoVO> castList) {
		this.castList = castList;
	}

	public Long getLatestPublicationId() {
		return latestPublicationId;
	}

	public void setLatestPublicationId(Long latestPublicationId) {
		this.latestPublicationId = latestPublicationId;
	}

	public List<MandalInfoVO> getMandalInfoVOsList() {
		return mandalInfoVOsList;
	}

	public void setMandalInfoVOsList(List<MandalInfoVO> mandalInfoVOsList) {
		this.mandalInfoVOsList = mandalInfoVOsList;
	}

	
	public List<SelectOptionVO> getResultData() {
		return resultData;
	}

	public void setResultData(List<SelectOptionVO> resultData) {
		this.resultData = resultData;
	}

	public DataVerificationVO getDataVerificationVO() {
		return dataVerificationVO;
	}

	public void setDataVerificationVO(DataVerificationVO dataVerificationVO) {
		this.dataVerificationVO = dataVerificationVO;
	}

	public List<SelectOptionVO> getAgeDetailsList() {
		return ageDetailsList;
	}

	public void setAgeDetailsList(List<SelectOptionVO> ageDetailsList) {
		this.ageDetailsList = ageDetailsList;
	}
	public List<OptionVO> getAgeList() {
		return ageList;
	}

	public void setAgeList(List<OptionVO> ageList) {
		this.ageList = ageList;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	
	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
	
	if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_ANALYSIS))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_ANALYSIS))
			return ERROR;
		constituencyList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		return SUCCESS;
		
	}
	
	public String AjaxHandler()
	{
		String param;
		param = getTask();
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		if(user.getMainAccountId() != null){
			userID = user.getMainAccountId();
		}
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		if(jObj.getString("task").equalsIgnoreCase("getMandalList"))
		{
			String selectedVal=jObj.getString("selected");
			namesList = regionServiceDataImp.getSubRegionsInConstituency(new Long(selectedVal), IConstants.PRESENT_YEAR, null);
			namesList.add(0, new SelectOptionVO(0L,"Select Mandal"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getPublicationDate") || jObj.getString("task").equalsIgnoreCase("getPublicationDateForCast")
				||jObj.getString("task").equalsIgnoreCase("getPublicationDateForParty")
				||jObj.getString("task").equalsIgnoreCase("getPublicationDateForModification"))
		{
			Long selectedId = jObj.getLong("selected");
			namesList = votersAnalysisService.publicationDetailsBasedOnConstituency(selectedId);
			namesList.add(0, new SelectOptionVO(0L,"Select Publication Date"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getWards"))
		{
			Long selectedId = jObj.getLong("selected");
			Long PublicationId = jObj.getLong("publicationValue");
			Long constituencyId = jObj.getLong("constituencyId");
	       namesList = votersAnalysisService.getWards(selectedId,PublicationId,constituencyId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getHamletsList"))
		{
			Long selectedId = jObj.getLong("selected");
			Long PublicationId = jObj.getLong("publicationValue");
			namesList = votersAnalysisService.getHamlets(selectedId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getLocalitiesForWards"))
		{
		
			
			Long selectedId = jObj.getLong("selected");
			Long mainValue = jObj.getLong("mainValue");
			String queryCondition=" model.localElectionBody.localElectionBodyId in(select a.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody a " +
					" where a.assemblyLocalElectionBodyId = :id ) ";
		 namesList = votersAnalysisService.getLocalitiesForWards(mainValue , userID,queryCondition);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getLocalities"))
		{
			Long selectedId = jObj.getLong("selected");
		
		 namesList = votersAnalysisService.getLocalities(selectedId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getConstituencies"))
		{
			
			namesList = votersAnalysisService.getConstituenciesToMapPublicationData(jObj.getLong("fromPublication"),jObj.getLong("toPublication"));
		}
			
		return Action.SUCCESS;
	}

public String getVoterDetails(){
	
	if(log.isDebugEnabled())	
	log.debug("Executing getVoterDetails() Method");	
	try{
		
	
		String param;
		param = getTask();
		Long userId = null;
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO.getParentUserId()!=null)
		{
		  userId = regVO.getMainAccountId();
		}
		else
		{
		  userId = regVO.getRegistrationID();
		}

		//Long userId =  regVO.getRegistrationID();
		
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		Integer maxRecords = Integer.parseInt(request.getParameter("results"));
		List<VoterVO> votersList = null;
		constituencyManagementVO = new ConstituencyManagementVO();
		
		
		
		Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
		Long boothId = request.getParameter("boothId") != null ? Long.parseLong(request.getParameter("boothId")):0L;
		Long panchaytId = request.getParameter("panchaytId") != null? Long.parseLong(request.getParameter("panchaytId")) :0L;
		Long customwardId = request.getParameter("customwardId") != null?Long.parseLong(request.getParameter("customwardId")) :0L;
		Long constiId =request.getParameter("constiId") != null?Long.parseLong(request.getParameter("constiId")) :0L;
		Long hamletId = 0L;
		
		if(request.getParameter("hamletId") != null)
			hamletId = new Long(request.getParameter("hamletId"));
		
			votersList = new ArrayList<VoterVO>();
			
			if(hamletId.longValue() != 0){
				votersList = votersAnalysisService.getVoterDetails(
						publicationId, null, panchaytId,hamletId, startIndex,
						maxRecords, order, columnName,userId,customwardId,constiId);
				
			}else{
			
				if(boothId == 0 && panchaytId != 0)			
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, null, panchaytId,null, startIndex,
							maxRecords, order, columnName,userId,customwardId,constiId);
				else if(boothId != 0 && panchaytId == 0)
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, boothId , null,null, startIndex, maxRecords,
							order, columnName,userId,customwardId,constiId);
				
				else if(customwardId != 0 && panchaytId == 0 && boothId == 0)
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, boothId , null,null, startIndex, maxRecords,
							order, columnName,userId,customwardId,constiId);
			}

		
		constituencyManagementVO.setVoterDetails(votersList);
		
		if(votersList !=null && votersList.size() > 0 )
		constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
		
	}catch(Exception e){
		
		e.printStackTrace();
		log.error("Exception Occured in getVoterDetails() Method,Exception is- "+e);
		
	}
		
		return Action.SUCCESS;
		
	}
		
	public String getVotersCount(){
		String param;
		param = getTask();
		Long userId = 0l;
		Long constituencyId = null;
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO.getParentUserId()!=null)
		{
		  userId=regVO.getMainAccountId();
		}
		else
		{
		  userId = regVO.getRegistrationID();
		}

		
		try{
			jObj = new JSONObject(param);
			constituencyId = jObj.getLong("constituencyId");
		
			String res=	jObj.getString("buildType");
		String type=jObj.getString("type");
		
		if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase(IConstants.CUSTOMWARD) || type.equalsIgnoreCase("mandal"))
		{
			res=jObj.getString("resultFor");
		}
			
			 votersInfo = votersAnalysisService.getVotersCount(userId,type,jObj.getLong("id"),jObj.getLong("publicationDateId"),constituencyId,res);

			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getVotersCount() Method,Exception is- "+e);
		}
		
		// votersInfo = votersAnalysisService.getVotersCount(loggedUserId,jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),constituencyId,jObj.getString("buildType"));
		return Action.SUCCESS;
	}
	
	
	
	public String getVotersCountForAllElections(){
		
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getVotersCount() Method,Exception is- "+e);
		}
		
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long mandalId = jObj.getLong("mandalId");
		Long panchayatId = jObj.getLong("panchayatId");
		Long boothId = jObj.getLong("boothId");
		String type = jObj.getString("type");
		
		
		//previousDetailsList = votersAnalysisService.getAllElectionAndPublicationsForConstituencyId(constituencyId);
		
		previousDetailsList = votersAnalysisService.getPreviousVotersCountDetailsForAllLevels(
				 constituencyId,mandalId, panchayatId, boothId , type);
		
		  // votersInfo = votersAnalysisService.getVotersCount(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"));
	
		
		return Action.SUCCESS;
		
	}

	public VotersInfoForMandalVO getVotersInfo() {
		return votersInfo;
	}

	public void setVotersInfo(VotersInfoForMandalVO votersInfo) {
		this.votersInfo = votersInfo;
	}


public String getVotersCastInfoByConstituency()
{
	String param=null;
	
	param=getTask();
	System.out.println("param:"+param);		
	
	try {
		jObj=new JSONObject(param);
		System.out.println("jObj = "+jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	session = request.getSession();
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	Long userId =  null;
	
	if(regVO.getParentUserId()!=null){
		userId=regVO.getMainAccountId();
	}
	else{
		userId = regVO.getRegistrationID();
	}
	
	if(jObj.getString("task").equalsIgnoreCase("getCastInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");
		
		//VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastDetails(new Long(id), new Long(publicationId),type);
		
		VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastWiseDetailsInALocation(userId,type,id,publicationId,jObj.getLong("constituencyId"),jObj.getString("queryType"));
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	}
	else if(jObj.getString("task").equalsIgnoreCase("getPartyInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");
	VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersPartyDetailsInALocation(userId,type,id,publicationId,jObj.getLong("constituencyId"),jObj.getString("queryType"));
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	}
	else if(jObj.getString("task").equalsIgnoreCase("getPartyCastInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");	
		VoterCastInfoVO votersByCast  = votersAnalysisService.getCastWisePartyCount(userId,type,id,publicationId,jObj.getLong("constituencyId"));
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	}
	else if(jObj.getString("task").equalsIgnoreCase("getCastInfoForsubLevels"))
	{

		constituencyManagementVO = new ConstituencyManagementVO();
		String id = jObj.getString("id");
		String type = jObj.getString("type");
		String publicationId = jObj.getString("publicationDateId");
		String buildType = jObj.getString("buildType");
			
			if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase("customWard") )
		{
			String res=jObj.getString("resultFor");
			if(res.equalsIgnoreCase("booth"))
				buildType=res;
			else buildType="localArea";
		}
		List<VoterCastInfoVO> sublevelCastDetails  = votersAnalysisService.getVotersCastDetailsForSubLevels(new Long(id), new Long(publicationId),type,userId,jObj.getLong("constituencyId"),buildType,jObj.getString("queryType"));
		constituencyManagementVO.setCastPercent(votersAnalysisService.getCasteWisePercentage(sublevelCastDetails));
		constituencyManagementVO.setCastVosList(sublevelCastDetails);
		constituencyManagementVO.setConstituencyManagementVO(votersAnalysisService.getCasteWisePercentsInLocations(sublevelCastDetails));
	}
	
	else if(jObj.getString("task").equalsIgnoreCase("getVotersInACaste"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long hamletId = 0L;
		Long constituencyId =0L;
		String id=jObj.getString("id");
		String publicationDateId = jObj.getString("publicationDateId");
		String casteStateId=jObj.getString("caste");
		String type = jObj.getString("type");
		String buildType = jObj.getString("buildType");
		
		try
		{
			hamletId=jObj.getLong("hamletId");
			constituencyId = jObj.getLong("constituencyId");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		List<VoterHouseInfoVO> votersByHouseNos=votersAnalysisService.getVoterDetailsByCaste(new Long(id),new Long(publicationDateId),new Long(casteStateId),type,buildType,userId,hamletId,constituencyId);
		constituencyManagementVO.setVotersByHouseNos(votersByHouseNos);
		
		
		
	}
	
	return SUCCESS;
}

/*public String getAgewiseVoterDetails(){
	  
	  
    String param;
	param = getTask();
	constituencyManagementVO = new ConstituencyManagementVO();
	
	try{
		jObj = new JSONObject(param);
		
		Long constituencyId = Long.parseLong(jObj.getString("constituencyId"));
		Long mandalId = Long.parseLong(jObj.getString("mandalId"));
		Long boothId = Long.parseLong(jObj.getString("boothId"));
		Long publicationDateId = Long.parseLong(jObj.getString("publicationDateId"));
		String type = jObj.getString("type");
		Long panchayatId = Long.parseLong(jObj.getString("panchayatId"));
		String retrieveType = null;
		
		try{
		
		 retrieveType = jObj.getString("retrieveType");
		}catch(Exception e){
			//e.printStackTrace();
			
		}
		
		List<VotersDetailsVO> tehsilVotersDetails = null;
		List<VotersDetailsVO> panchatyVotersDetails = null;
		List<VotersDetailsVO> boothVotersDetails = null;
		List<VotersDetailsVO> votersDeatailsForConstituency = null;
		
		//VOTER DETAILS OVERVIEW FOR CONSTITUENCY  OR MANDAL
		
		
		if(retrieveType != null){
		
			if(retrieveType.equalsIgnoreCase("brief")){
			   votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,
					panchayatId , boothId, publicationDateId,type);
			}
			
			
			
			if(retrieveType.equalsIgnoreCase("all")){
				
				if(type.equalsIgnoreCase("constituency")){
					 tehsilVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForTehsilsByConstituencyId(constituencyId,publicationDateId);
					 constituencyManagementVO.setMandalsVotersDetails(tehsilVotersDetails);
				}
				else if (type.equalsIgnoreCase("mandal")){
					 panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForPanchayatisByTehsilId(mandalId,publicationDateId);
					 constituencyManagementVO.setPanchayatVotersDetails(panchatyVotersDetails);
				}
				else if (type.equalsIgnoreCase("panchayat")){
					 boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
					 constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
				}
				else if (type.equalsIgnoreCase("localElectionBody")){
					 boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(mandalId,publicationDateId);
					 constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
				}
			}
		}else{
			
			votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,
					panchayatId , boothId, publicationDateId,type);
			

			
			if(type.equalsIgnoreCase("constituency")){
				 tehsilVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForTehsilsByConstituencyId(constituencyId,publicationDateId);
				 constituencyManagementVO.setMandalsVotersDetails(tehsilVotersDetails);
			}
			else if (type.equalsIgnoreCase("mandal")){
				 panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForPanchayatisByTehsilId(mandalId,publicationDateId);
				 constituencyManagementVO.setPanchayatVotersDetails(panchatyVotersDetails);
			}
			else if (type.equalsIgnoreCase("panchayat")){
				 boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
				 constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
			}
			else if (type.equalsIgnoreCase("localElectionBody")){
				 boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(mandalId,publicationDateId);
				 constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
			}
		
			
			
		}

		
	
        //VOTER DETAILS OVERVIEW  FOR BOOTH 
		
		List<VotersDetailsVO> votersDeatailsForBoothist = votersAnalysisService
				.getVotersDetailsForBooth(boothId, publicationDateId);
		
		
		
		
		//MANDAL AGE WISE
		List<VoterVO> mandalAgewiseVotersList = votersAnalysisService
				.getAgewiseVoterDetailsByMandalWise(constituencyId,
						publicationDateId,type);
		
		//MANDALWISE AGE AND GENDER
		List<VotersDetailsVO> mandalAgeAndGenderVotersList = votersAnalysisService
				.getAgeAndGenderwiseVoterDetailsByMandalWise(constituencyId,
						publicationDateId);
		
		
				
		
		constituencyManagementVO.setVoterDetails(votersList);
		constituencyManagementVO.setMandalAgewiseVotersList(mandalAgeAndGenderVotersList);
		
		constituencyManagementVO.setVotersDetailsVO(votersDeatailsForConstituency);
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
	}
  
  return Action.SUCCESS;
  
} */

public String getImportantFamaliesDetails(){
	String param;
	param = getTask();
	
	try{
		jObj = new JSONObject(param);	
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getImportantFamaliesDetails() Method,Exception is- "+e);
	}
	
	session = request.getSession();
	
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	Long userId = null;
	if(regVO.getParentUserId()!=null)
	{
	  userId = regVO.getMainAccountId();
	}
	else
	{
	  userId = regVO.getRegistrationID();
	}

	//Long userId =  regVO.getRegistrationID();
	   importantFamiliesInfoVo = votersAnalysisService.getImportantFamiliesInfo(userId,jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getLong("constituencyId"),jObj.getString("requestFor"),jObj.getString("buildType"));
	return Action.SUCCESS;
}

public String getImportantFamaliesDetailsForHamlet(){
	String param;
	param = getTask();
	
	try{
		jObj = new JSONObject(param);	
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getImportantFamaliesDetails() Method,Exception is- "+e);
	}
	
	session = request.getSession();
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	Long userId =  regVO.getRegistrationID();
	
	
	   importantFamiliesInfoVo = votersAnalysisService.getImportantFamaliesDetailsForPanchayatByHamlet(userId,jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getLong("constituencyId"));
	return Action.SUCCESS;
}

public String getVotersFamilyDetails(){
	try{
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		//Long userId =  regVO.getRegistrationID();
		Long userId = null;
		if(regVO.getParentUserId()!=null)
		{
		  userId = regVO.getMainAccountId();
		}
		else
		{
		  userId = regVO.getRegistrationID();
		}

		if(jObj.getString("task").equalsIgnoreCase("gettotalimpfamlies"))
		{
			String requestFor = "";
			try
			{   
				requestFor = jObj.getString("requestFor");				
			}catch(Exception e){}
		 
			votersFamilyInfo = votersAnalysisService.getVoterHouseInfoDetails(userId,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("type"),jObj.getString("buildType"),requestFor);
		}
		else
			votersFamilyInfo = votersAnalysisService.getFamilyInformation(null,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"),userId,null);
	}catch(Exception e){
		log.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- "+e);
	}
	
	return SUCCESS;
}

public String getUserCategories(){
	
	try{
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		Long userId = null;
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO.getParentUserId()!=null)
		{
		  userId = regVO.getMainAccountId();
		}
		else
		{
		  userId = regVO.getRegistrationID();
		}

		//Long userId =  regVO.getRegistrationID();
		userCategoriesList = votersAnalysisService.getUserCategoryValuesByUserId(userId);
		
	}catch(Exception e){
		e.printStackTrace();
		
	}
	
	return Action.SUCCESS;
	
}

public ImportantFamiliesInfoVo getImportantFamiliesInfoVo() {
	return importantFamiliesInfoVo;
}

public void setImportantFamiliesInfoVo(
		ImportantFamiliesInfoVo importantFamiliesInfoVo) {
	this.importantFamiliesInfoVo = importantFamiliesInfoVo;
}

public List<VoterHouseInfoVO> getVotersFamilyInfo() {
	return votersFamilyInfo;
}

public void setVotersFamilyInfo(List<VoterHouseInfoVO> votersFamilyInfo) {
	this.votersFamilyInfo = votersFamilyInfo;
}



public String getCountsForLevel()
{
	try{
	String param ;
	param = getTask();
	jObj = new JSONObject(param);
	session = request.getSession();
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	//Long userId =  regVO.getRegistrationID();
	Long userId = null;
	if(regVO.getParentUserId()!=null)
	{
	  userId = regVO.getMainAccountId();
	}
	else
	{
	  userId = regVO.getRegistrationID();
	}

	  if(jObj.getString("task").equalsIgnoreCase("getCountForLevel"))
	  {
		Long publicationDateId= jObj.getLong("publicationDateId");
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		
	
		
		countList = votersAnalysisService.getCountList(publicationDateId,id,type, jObj.getLong("constituencyId"),jObj.getLong("tehsilId"),userId);
		}
	   else if(jObj.getString("task").equalsIgnoreCase("getCountForConstituency"))
		  dataVerificationVO = votersAnalysisService.getCountForConstituency(jObj.getString("type"),jObj.getLong("constituencyId"),jObj.getLong("id"),jObj.getLong("publicationDateId"),userId);
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return SUCCESS;
}
public String getPreviousEleVotingTrends()
{
	try{
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		Long id = jObj.getLong("id");
		Long publicationDateId = jObj.getLong("publicationDateId");
		Long constituencyId = jObj.getLong("constituencyId");
		String type = jObj.getString("type");
		partyVotesEarnedVOList = votersAnalysisService.getPreviousElectionVotingTrends(id,publicationDateId,constituencyId,type);
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getPreviousEleVotingTrends() Method, Exception - "+e);
	}
return Action.SUCCESS;
}

public String getProblemsByLocation()
{
	try{
		 problemBeanVO= new ProblemBeanVO();
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  null;
		
		if(regVO.getParentUserId()!=null){
			userId=regVO.getMainAccountId();
		}
		else{
			userId = regVO.getRegistrationID();
		}
		
		Long locationValue = jObj.getLong("locationValue");
		Long locationId = jObj.getLong("locationId");
		
		if(jObj.getString("task").equalsIgnoreCase("getProblemsByLocation"))
		 problemDetails = problemManagementService.getProblemDetailsForVoterPage(userId,locationId,locationValue);
		
		//I Had changed here for getting hamlets problems
		if(jObj.getString("task").equalsIgnoreCase("getProblemsByLocationForHamlet"))
			problemDetails = problemManagementService.getProblemDetailsForVoterPage(userId,locationId,locationValue);	
			
		 if(jObj.getString("task").equalsIgnoreCase("getProblemDetailsByLocation"))
		 {
			 Long informationsrcId = jObj.getLong("srcId");
			 String status = jObj.getString("status");
			 String startIndex = request.getParameter("startIndex");
			 String maxIndex = request.getParameter("maxIndex");
			 
			 problemDetails = problemManagementService.getProblemDetailsInfoVoterPage(userId,locationId,locationValue,status,informationsrcId,Integer.parseInt(startIndex),Integer.parseInt(maxIndex));
		 }
		// problemBeanVO.setProblemBeanVOList(problemDetails);
	}
	catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getProblemsByLocation() Method, Exception - "+e);
	}
return Action.SUCCESS;
}

	public String getAgewiseVoterDetails(){
		
               
	 Long userId1 =null;
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		else 
		{
			if(user.getParentUserId()!=null)
			{
				userId1 = user.getMainAccountId();
			}
			else
			{
				userId1 = user.getRegistrationID();
			}

		}
			//userId1= user.getRegistrationID();
		String param;
		param = getTask();
		constituencyManagementVO = new ConstituencyManagementVO();
		
		try{
			
		jObj = new JSONObject(param);
		
		Long constituencyId = Long.parseLong(jObj.getString("constituencyId"));
		Long mandalId = Long.parseLong(jObj.getString("mandalId"));
		Long boothId = Long.parseLong(jObj.getString("boothId"));
		Long publicationDateId = Long.parseLong(jObj.getString("publicationDateId"));
		String type = jObj.getString("type");
		Long panchayatId = Long.parseLong(jObj.getString("panchayatId"));
		
		List<VotersDetailsVO> tehsilVotersDetails = null;
		List<VotersDetailsVO> panchatyVotersDetails = null;
		List<VotersDetailsVO> boothVotersDetails = null;
		//VOTER DETAILS OVERVIEW FOR CONSTITUENCY OR MANDAL
		
		List<VotersDetailsVO> votersDeatailsForConstituency =null;
		
		String myType ="";
		
		
		
		if(type.equalsIgnoreCase("hamlet") || type.equalsIgnoreCase("hamletLocalArea") || type.equalsIgnoreCase("hamletBooths") || type.equalsIgnoreCase("customWard") || type.equalsIgnoreCase("customWardLocalArea")  )
		{ 	 
			myType=type;
			if(!(myType.equalsIgnoreCase("customWard") || type.equalsIgnoreCase("customWardLocalArea")) )
		  {
				myType = "hamlet";
			  
		  }else if(type.equalsIgnoreCase("customWardLocalArea")) myType = IConstants.CUSTOMWARD;
			 
			votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,panchayatId , userId1, publicationDateId,myType);	
		
		}else 
			if(!(type.equalsIgnoreCase("hamletLocalArea") || type.equalsIgnoreCase("customWardLocalArea")) ){
			
		votersDeatailsForConstituency = votersAnalysisService.getVoterAgeWiseDetails(constituencyId, mandalId,
		panchayatId , boothId, publicationDateId,type);
		if(votersDeatailsForConstituency == null || votersDeatailsForConstituency.size() == 0)
		votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,
		panchayatId , boothId, publicationDateId,type);
		}
		
		if(type.equalsIgnoreCase("constituency")){
		
		tehsilVotersDetails = votersAnalysisService.getAgewiseVotersDetForTehsilsByConstituencyId(constituencyId, publicationDateId, type);
		if(tehsilVotersDetails == null || tehsilVotersDetails.size() == 0)
		tehsilVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForTehsilsByConstituencyId(constituencyId,publicationDateId);
		
		constituencyManagementVO.setMandalsVotersDetails(tehsilVotersDetails);
		}
		else if (type.equalsIgnoreCase("mandal")){
		
		panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetaForPanchayatisByTehsilId(mandalId,publicationDateId, type,constituencyId);
		if(panchatyVotersDetails == null || panchatyVotersDetails.size() == 0)
		panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForPanchayatisByTehsilId(mandalId,publicationDateId);
		constituencyManagementVO.setPanchayatVotersDetails(panchatyVotersDetails);
		}
		else if (type.equalsIgnoreCase("panchayat")){
		       String buildType = jObj.getString("buildType");
			   if(buildType.equalsIgnoreCase("booth")){
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByPanchayatId(panchayatId,publicationDateId, type,constituencyId);
			if(boothVotersDetails == null || boothVotersDetails.size() == 0)
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
			   }else
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForHamletByPanchayatId(panchayatId,publicationDateId,userId1,IConstants.HAMLET);
				constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
				}
		
	    
		else if (type.equalsIgnoreCase("hamletLocalArea") || type.equalsIgnoreCase("customWardLocalArea") ){
			
			//boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByPanchayatId(panchayatId,publicationDateId, type,constituencyId);
			//if(boothVotersDetails == null || boothVotersDetails.size() == 0)
			
	    
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForLocalAreaByHamletId(panchayatId,publicationDateId,userId1,type);
		
			constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
			}
	     
	      else if (type.equalsIgnoreCase("hamletBooths") || type.equalsIgnoreCase("boothHamlets")  ){
				
				boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForHamletByBoothId(constituencyId,panchayatId,publicationDateId,userId1,type);
			
				constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
				} else if (type.equalsIgnoreCase("wardBooths")  ){
					
					boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForHamletByBoothId(constituencyId,panchayatId,publicationDateId,userId1,type);
				
					constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
					}
		
				else if (type.equalsIgnoreCase("muncipalityWards")  ){
		     boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForHamletByPanchayatId(panchayatId,publicationDateId,userId1,IConstants.CUSTOMWARD);
					constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
					}
		else if (type.equalsIgnoreCase("ward")){
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByWardId(panchayatId,publicationDateId,constituencyId);
		if(boothVotersDetails == null || boothVotersDetails.size() == 0)
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(panchayatId,publicationDateId);
		constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
		}
		else if (type.equalsIgnoreCase("localElectionBody")){
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByLocalElectionBodyId(mandalId,publicationDateId, type,constituencyId);
		if(boothVotersDetails == null || boothVotersDetails.size() == 0)
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForWardsByLocalElectionBodyId(mandalId,publicationDateId,constituencyId);
		constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
		}
		
		constituencyManagementVO.setVotersDetailsVO(votersDeatailsForConstituency);
		
		
		
		}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- ",e);
		}
		
		return Action.SUCCESS;
	
	} 
	public String getElectionYearsByMandalId()
	{
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			jObj = new JSONObject(getTask());
			electionYearsList = votersAnalysisService.getElectionYearsByMandalId(jObj.getString("type"),jObj.getLong("id"));
	
		}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getElectionYearsByMandalId() Method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getCrossVotingReportByMandalIdAndEleYear()
	{
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			jObj = new JSONObject(getTask());
			crossVotingConsolidateVO = votersAnalysisService.getCrossVotingReportByMandalIdAndEleYear(jObj.getString("type"),jObj.getLong("id"),jObj.getString("year"), jObj.getString("includeAliance"));
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getCrossVotingReportByMandalId() Method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getParliamentConstituencyIds()
	{
		try{
			jObj = new JSONObject(getTask());
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getParliamentConstituencyId() Method, Exception - "+e); 
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		parliamentConstituencyId = votersAnalysisService.getParliamentConstituencyId(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("eleYear"));
		return Action.SUCCESS;
	}
	
	public String getInfluencingPeopleBySearch(){
		
		try{
			jObj = new JSONObject(getTask());
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getInfluencingPeopleBySearch() Method, Exception - "+e); 
		}
		
		InfluencingPeopleVO influencingPeopleVO = new InfluencingPeopleVO();
		
		influencingPeopleVO.setPersonName(jObj.getString("name"));
		influencingPeopleVO.setFatherOrSpouceName(jObj.getString("fatherOrSpouceName"));
		influencingPeopleVO.setStateId(jObj.getLong("state"));
		influencingPeopleVO.setDistrictId(jObj.getLong("district"));
		influencingPeopleVO.setConstituencyId(jObj.getLong("constituency"));
		influencingPeopleVO.setMandalId(jObj.getLong("mandal"));
		influencingPeopleVO.setMuncipalityId(jObj.getLong("muncipality"));
		influencingPeopleVO.setHamletId(jObj.getLong("hamlet"));
		influencingPeopleVO.setWardId(jObj.getLong("ward"));
		//influencingPeopleVO.setWardId(jObj.getLong("villageOrWard"));
		influencingPeopleVO.setBoothId(jObj.getLong("booth"));
		influencingPeopleVO.setScopeValue(jObj.getLong("scope"));
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		influencingPeopleList = votersAnalysisService.getInfluencingPeopleBySearch(user.getRegistrationID(),influencingPeopleVO);
		
		return Action.SUCCESS;
		
	}
	public String getBoothBasicInfo(){
		try{
			jObj = new JSONObject(getTask());
			userCategoriesList = votersAnalysisService.getBoothBasicInfo(jObj.getLong("boothId"));
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getBoothBasicInfo() Method, Exception ",e); 
		}
		return Action.SUCCESS;
	}
	/**
	 * This Method is used to check for a voter weather he added as a influencing people , cadre or political
	 * @author Prasad Thiragabathina 
	 * @return String
	 */
	public String checkForVoter()
	{
		String param;
		param = getTask();
		try {
			log.debug("Entered into The checkForVoter() method");
			jObj = new JSONObject(getTask());
			
		} catch (Exception e) {
			log.error("Exception Occured in checkForVoter() Method, Exception - "+e); 
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long voterId = jObj.getLong("voterId");
		String type = jObj.getString("type");
		resultStatus = votersAnalysisService.checkForVoter(voterId,type);
		return Action.SUCCESS;
	}
	
	public String mapVoterAsInfluencingPerson(){
		
		try{
			jObj = new JSONObject(getTask());
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in mapVoterAsInfluencingPerson() Method, Exception - "+e); 
		}
		
		
		Long influencePersonId = jObj.getLong("influencePersonId");
		Long voterId = jObj.getLong("voterId");
		
		votersAnalysisService.mapVoterAsInfluencingPerson(influencePersonId,voterId);
		
		
		
		return Action.SUCCESS;
		
	}
	
	public String addVoterToCadre(){
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			Long userId = null;
			if(regVO != null && regVO.getRegistrationID() != null)
				if(regVO.getParentUserId()!=null)
					userId=regVO.getMainAccountId();
				else
					userId = regVO.getRegistrationID();
			else 
			  return "error";
			if(regVO == null){
				task = "notLogged";
				return Action.SUCCESS;
			}
			task = cadreManagementService.updateCadreVoterId(jObj.getLong("cadreId"),jObj.getLong("voterId"),userId);
		}catch (Exception e) {
			log.error("Exception Occured in addVoterToCadre() Method, Exception - ",e);
			task =  "error";
		}
		return Action.SUCCESS;
	}
	
	// updated by gayathri to get HamletLevel VotersBasicInfo
	
	public  String getVotersCountForAllElectionsForHamlet(){
		
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = null;
		//Long userID = user.getRegistrationID();
		if(user.getParentUserId()!=null)
		{
			userID = user.getMainAccountId();
		}
		else
		{
			userID = user.getRegistrationID();
		}

		String param;
		param = getTask();
	
		try{
		jObj = new JSONObject(param);	
		
		}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getVotersCount() Method,Exception is- "+e);
		}
	
	
	Long constituencyId = jObj.getLong("constituencyId");
	Long mandalId = jObj.getLong("mandalId");
	Long panchayatId = jObj.getLong("panchayatId");
	Long boothId = jObj.getLong("boothId");
	//Long publicationId = jObj.getLong("publicationId");
	Long hamletId = jObj.getLong("hamletId");
	String type = jObj.getString("type");
	
	
	
	previousDetailsListForHamlet = votersAnalysisService.getPreviousVotersCountDetailsForHamlet(
			 constituencyId,mandalId, panchayatId, boothId,hamletId,userID,type);
	
	  // votersInfo = votersAnalysisService.getVotersCount(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"));

	
	return Action.SUCCESS;
	
	}
	
	
	public String getInfluencingPeopleCountByLocation()
	{
	try{
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  null;
		
		if(regVO.getParentUserId()!=null){
			userId=regVO.getMainAccountId();
		}
		else{
			userId = regVO.getRegistrationID();
		}
		Long locationValue = jObj.getLong("locationValue");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");
		influencingPeopleCount = votersAnalysisService.getInfluencingPeopleCount(userId,locationValue,type,publicationId);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return SUCCESS;
	}
	
	public String getInfluencingPeopleVotersDetails()
	{
		if(log.isDebugEnabled())	
		log.debug("Executing getVoterDetails() Method");	
		try{
			String param;
			param = getTask();
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			List<VoterVO> votersList = null;
			constituencyManagementVO = new ConstituencyManagementVO();
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			Long userId =  null;
			if(regVO.getParentUserId()!=null){
					userId=regVO.getMainAccountId();
				}
				else{
					userId = regVO.getRegistrationID();
				}
				
			Long locationValue =request.getParameter("locationValue") != null ?Long.parseLong(request.getParameter("locationValue")):0L;
			String type =request.getParameter("type");
			String buttonName =request.getParameter("buttonName");
			Long publicationId = request.getParameter("publicationDateId") != null ?Long.parseLong(request.getParameter("publicationDateId")):0L;
			votersList = new ArrayList<VoterVO>();
			votersList = votersAnalysisService.getInfluencingPeopleVoterDetails(
						userId,locationValue,type,buttonName,
						publicationId,startIndex ,maxRecords,columnName,order);

			constituencyManagementVO.setVoterDetails(votersList);
			if(votersList.size() > 0 )
			constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
			
		}catch(Exception e){
			
			e.printStackTrace();
			log.error("Exception Occured in getVoterDetails() Method,Exception is- "+e);
			
		}
			
		return Action.SUCCESS;
}

	public String getAssemblyLocalEleBodyIdByLocalEleBodyId()
	{
		try{
			
			 String param = getTask();
			jObj = new JSONObject(param);
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return null;
			assemblyLocalEleBodyId = voterModificationService.getLocalElectionBodyIdByAssemblyLocalElectionBodyId(jObj.getLong("localEleBodyId"));
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAssemblyLocalEleBodyIdByLocalEleBodyId() Method,Exception is- "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getVotersFamilyDetailsByConstituencyId(){
		try{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			session = request.getSession();
			Long userId = null;
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO.getParentUserId()!=null)
			{
			  userId = regVO.getMainAccountId();
			}
			else
			{
			  userId = regVO.getRegistrationID();
			}

			//Long userId =  regVO.getRegistrationID();
			Long constituencyId = jObj.getLong("constituencyId");
			
			votersFamilyInfo = votersAnalysisService.getVotersFamilyDetailsByConstituencyId(jObj.getLong("fromPublication"),jObj.getLong("ToPublication"),jObj.getLong("partNo"),jObj.getString("hno"),userId,constituencyId);
			
		}catch(Exception e){
			log.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- "+e);
		}
		
		return SUCCESS;
	}
	
	public String checkLocalityDataExist()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			Long userId = null;
			if(regVO.getParentUserId()!=null)
			{
			  userId = regVO.getMainAccountId();
			}
			else
			{
			  userId = regVO.getRegistrationID();
			}

			//Long userId =  regVO.getRegistrationID();
			isLocalityExist = votersAnalysisService.checkLocalityDataExist(jObj.getLong("id"),userId,jObj.getString("type"),jObj.getLong("publicationDateId"));
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in checkLocalityDataExist() Method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getCategoryWiseDetails(){
	  try{
		jObj = new JSONObject(getTask());
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
		Long userId =  regVO.getRegistrationID();
		if(jObj.getString("task").equalsIgnoreCase("getCategoryWiseDetails")){
		String[] ids = jObj.getString("attributeIds").split(",");
		List<Long> attributeIds = new ArrayList<Long>();
		for(String id:ids){
			attributeIds.add(new Long(id.trim()));
		}
		castList = voterReportService.getVoterAttributeDetails(userId,attributeIds,jObj.getString("locationType"),jObj.getLong("locationId"),jObj.getLong("constituencyId"),jObj.getLong("publicationId"));
		}else if(jObj.getString("task").equalsIgnoreCase("getCategoryWiseSubDetails")){
			List<Long> attributeIds = new ArrayList<Long>();
			attributeIds.add(jObj.getLong("attributeId"));
			castList = voterReportService.getVoterAttributeSubDetails(userId,attributeIds,jObj.getString("locationType"),jObj.getLong("locationId"),jObj.getLong("constituencyId"),jObj.getLong("publicationId"));
		}
		for(int i=0;i<castList.size();i++)
		{
			if(castList.get(i).getPartyWisevoterCastInfoVOList() != null && castList.get(i).getPartyWisevoterCastInfoVOList().size() > 0){
			Collections.sort(castList.get(i).getPartyWisevoterCastInfoVOList(),sortByOrder);
			}
		}
		
	  }catch(Exception e){
		  log.error("Exception Occured in getCategoryWiseDetails() Method, Exception - ",e);
	  }
		return Action.SUCCESS;
	}
	 public static Comparator<VoterCastInfoVO> sortByOrder = new Comparator<VoterCastInfoVO>()
			    {
			   
			        public int compare(VoterCastInfoVO resultList1, VoterCastInfoVO resultList2)
			        {
			        	if(resultList1.getOrderNo() == null || resultList2.getOrderNo() == null){
			        		return 0;
			        	}
			        	else{
			            return (resultList1.getOrderNo()).compareTo(resultList2.getOrderNo());
			        	}
			        }
			    };
			    
	public String getAgeWiseWiseDetails()
	{
	try{
		jObj = new JSONObject(getTask());
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
		Long userId =  regVO.getRegistrationID();
		String[] ids = jObj.getString("attributeIds").split(",");
		List<Long> attributeIds = new ArrayList<Long>();
		for(String id:ids){
			attributeIds.add(new Long(id.trim()));
		}
		ageRangeList = userVoterService.getAgeRangeByUserVoterCategory(userId,attributeIds,jObj.getString("locationType"),jObj.getLong("locationId"),jObj.getLong("constituencyId"),jObj.getLong("publicationId"));
		for(int i=0;i<ageRangeList.size();i++)
		{
			if(ageRangeList.get(i).getVotersDetailsVOList() != null && ageRangeList.get(i).getVotersDetailsVOList().size() > 0){
			Collections.sort(ageRangeList.get(i).getVotersDetailsVOList(),sortByOrderNo);
			}
		}
	}
	catch(Exception e)
	{
		log.error("Exception Occured in getAgeWiseWiseDetails() Method, Exception - ",e);	
	}
	return Action.SUCCESS;
	}
	
	public String getCasteWiseUserVoterCategory()
	{
	try{
		jObj = new JSONObject(getTask());
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
		Long userId =  regVO.getRegistrationID();
		String[] ids = jObj.getString("attributeIds").split(",");
		List<Long> attributeIds = new ArrayList<Long>();
		for(String id:ids){
			attributeIds.add(new Long(id.trim()));
		}
		casteDetailsVO = userVoterService.getCasteWiseUserVoterCategory(userId,attributeIds,jObj.getString("locationType"),jObj.getLong("locationId"),jObj.getLong("constituencyId"),jObj.getLong("publicationId"));
		
	}
	catch(Exception e)
	{
		log.error("Exception Occured in getAgeWiseWiseDetails() Method, Exception - ",e);	
	}
	return Action.SUCCESS;
	}
	
	
	 public static Comparator<VotersDetailsVO> sortByOrderNo = new Comparator<VotersDetailsVO>()
			    {
			   
			        public int compare(VotersDetailsVO resultList1, VotersDetailsVO resultList2)
			        {
			        	if(resultList1.getOrderNo() == null || resultList2.getOrderNo() == null){
			        		return 0;
			        	}
			        	else{
			            return (resultList1.getOrderNo()).compareTo(resultList2.getOrderNo());
			        	}
			        }
			    };
			    
	public String getPublicationId()
	{
		latestPublicationId = votersAnalysisService.getLatestPublicationId();
	   return Action.SUCCESS;
	}
	
	public String getCensusDetailsInALocation()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			
			mandalInfoVOsList = userVoterService.getCensusDetailsInALocation(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("constituencyId"));
		}catch (Exception e) {
			log.error("Exception Occured in getCensusDetailsInALocation() Method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPanchayatAndBoothDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			if(jObj.getString("task").equalsIgnoreCase("getPanchayatsByTehsilId"))
			    namesList = voterReportService.getPanchayatsByTehsilId(jObj.getLong("tehsilId"));
			else if(jObj.getString("task").equalsIgnoreCase("getBoothsByPanchayatID"))
				namesList = voterReportService.getBoothsByPanchayatIDConstiId(jObj.getLong("panchayatId"),jObj.getLong("constituencyId"));
			
		}catch (Exception e) {
			log.error("Exception Occured in getPanchayatAndBoothDetails() Method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getUserCategoeryValues()
	{
		try {
			log.debug("entered into getUserCategoeryValues() method in VotersAnalysisAction Action Class");
			jObj = new JSONObject(getTask());
			/*session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;*/
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			Long userId = null;
			if(regVO != null && regVO.getRegistrationID() != null)
				if(regVO.getParentUserId()!=null)
					userId=regVO.getMainAccountId();
				else
					userId = regVO.getRegistrationID();
			else 
			  return ERROR;
			//Long userId             = regVO.getRegistrationID();
			if(jObj.getString("task").equalsIgnoreCase("getUserCategoeryValues"))
			{
				String status                 = jObj.getString("status");
				String type                   = jObj.getString("type");
				Long constituencyId           = jObj.getLong("constituencyId");
				Long publicationId            = jObj.getLong("publicationId");
				String[] selectedValues       = jObj.getString("selectedValues").split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String parm : selectedValues) {
					ids.add(Long.valueOf(parm.trim()));
				}
				resultData                    = voterReportService.getSelectedUserCategoeryDetails(userId,ids,type,status,constituencyId,publicationId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllWards"))
			{
				Long constituencyId           = jObj.getLong("id");
				Long publicationId            = jObj.getLong("publicationId");
				resultData                    = voterReportService.getAllWardsInUrbanConstituency(constituencyId,publicationId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getUserCatgValuesForWard"))	
			{
				Long constituencyId           = jObj.getLong("constituencyId");
				String status                 = jObj.getString("status");
				Long publicationId            = jObj.getLong("publicationId");
				String[] selectedValues       = jObj.getString("values").split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String parm : selectedValues) {
					ids.add(Long.valueOf(parm.trim()));
				}
				resultData                    = voterReportService.getUserCategoeryValuesForWards(userId,constituencyId,ids,status,publicationId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllBoothsInAWard"))	
			{
				Long publicationId            = jObj.getLong("publicationId");
				String[] selectedValues       = jObj.getString("values").split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String parm : selectedValues) {
					ids.add(Long.valueOf(parm.trim()));
				}
				resultData                    = voterReportService.getAllBoothsForSelectedWards(ids,publicationId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCategValuesForMuncipalWard"))
			{
				Long constituencyId           = jObj.getLong("constituencyId");
				String status                 = jObj.getString("status");
				Long publicationId            = jObj.getLong("publicationId");
				String[] selectedValues       = jObj.getString("selectedValues").split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String parm : selectedValues) {
					ids.add(Long.valueOf(parm.trim()));
				}
				resultData                    = voterReportService.getUserCategoeryValuesForMuncipalWards(userId, constituencyId, status, ids,publicationId);
			}
		
		} catch (Exception e) {
			log.error("Exception raised in getUserCategoeryValues() method in VotersAnalysisAction Action Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTotalVotersDetailsbyLocation(){		
		try {
			log.debug("entered into getTotalVotersDetailsbyLocation() method in VotersAnalysisAction Action Class");
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			//Long userId =  regVO.getRegistrationID();
			Long userId = null;
			if(regVO == null)
				return ERROR;
			if(regVO.getParentUserId()!=null)
			{
			  userId = regVO.getMainAccountId();
			}
			else
			{
			  userId = regVO.getRegistrationID();
			}

			voterInfo = votersAnalysisService.getTotalVotersDetailsbyLocation(userId,jObj.getLong("id"),jObj.getString("reportLevel"),jObj.getLong("publicationDateId"),jObj.getLong("constituencyId"));
		} catch (ParseException e) {
			log.error("Exception raised in getTotalVotersDetailsbyLocation() method in VotersAnalysisAction Action Class",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCustomWardAgeDetails()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		Long userId = user.getRegistrationID();
		try{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getCustomWardAgeDetails"))
			ageDetailsList = votersAnalysisService.getLocalAreaWiseAgeDetailsForCustomWard(jObj.getString("type"),jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"),jObj.getLong("id"),userId);
			
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getCustomWardAgeDetails() method,Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	
}
