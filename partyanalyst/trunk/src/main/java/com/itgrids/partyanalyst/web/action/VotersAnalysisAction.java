package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
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
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
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
	

	public List<InfluencingPeopleBeanVO> getInfluencingPeopleCount() {
		return influencingPeopleCount;
	}

	public void setInfluencingPeopleCount(
			List<InfluencingPeopleBeanVO> influencingPeopleCount) {
		this.influencingPeopleCount = influencingPeopleCount;
	}


	private SelectOptionVO selectOptionVO;
	private CadreManagementService cadreManagementService;
	
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

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return SUCCESS;
		
	}
	
	public String AjaxHandler()
	{
		String param;
		param = getTask();

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
		else if(jObj.getString("task").equalsIgnoreCase("getPublicationDate") || jObj.getString("task").equalsIgnoreCase("getPublicationDateForCast"))
		{
			Long selectedId = jObj.getLong("selected");
			namesList = votersAnalysisService.publicationDetailsBasedOnConstituency(selectedId);
			namesList.add(0, new SelectOptionVO(0L,"Select Publication Date"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getWards"))
		{
			Long selectedId = jObj.getLong("selected");
			Long PublicationId = jObj.getLong("publicationValue");
	       namesList = votersAnalysisService.getWards(selectedId,PublicationId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getHamletsList"))
		{
			Long selectedId = jObj.getLong("selected");
			Long PublicationId = jObj.getLong("publicationValue");
			namesList = votersAnalysisService.getHamlets(selectedId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getLocalities"))
		{
			Long selectedId = jObj.getLong("selected");
		
		 namesList = votersAnalysisService.getLocalities(selectedId);
			//namesList.add(0, new SelectOptionVO(0L,"Select"));
		}
			
		return Action.SUCCESS;
	}

public String getVoterDetails(){
	
	if(log.isDebugEnabled())	
	log.debug("Executing getVoterDetails() Method");	
	try{
		
	
		String param;
		param = getTask();
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  regVO.getRegistrationID();
		
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		Integer maxRecords = Integer.parseInt(request.getParameter("results"));
		List<VoterVO> votersList = null;
		constituencyManagementVO = new ConstituencyManagementVO();
		
		
		
		Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
		Long boothId = request.getParameter("boothId") != null ? Long.parseLong(request.getParameter("boothId")):0L;
		Long panchaytId = request.getParameter("panchaytId") != null? Long.parseLong(request.getParameter("panchaytId")) :0L;
		
		Long hamletId = 0L;
		
		if(request.getParameter("hamletId") != null)
			hamletId = new Long(request.getParameter("hamletId"));
		
			votersList = new ArrayList<VoterVO>();
			
			if(hamletId.longValue() != 0){
				votersList = votersAnalysisService.getVoterDetails(
						publicationId, null, panchaytId,hamletId, startIndex,
						maxRecords, order, columnName,userId);
				
			}else{
			
				if(boothId == 0 && panchaytId != 0)			
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, null, panchaytId,null, startIndex,
							maxRecords, order, columnName,userId);
				else if(boothId != 0 && panchaytId == 0)
					votersList = votersAnalysisService.getVoterDetails(
							publicationId, boothId , null,null, startIndex, maxRecords,
							order, columnName,userId);
			}

		
		constituencyManagementVO.setVoterDetails(votersList);
		
		if(votersList.size() > 0 )
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
		Long constituencyId = null;
		try{
			jObj = new JSONObject(param);
			constituencyId = jObj.getLong("constituencyId");
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getVotersCount() Method,Exception is- "+e);
		}
		
		   votersInfo = votersAnalysisService.getVotersCount(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),constituencyId);
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
	Long userId =  regVO.getRegistrationID();
	
	if(jObj.getString("task").equalsIgnoreCase("getCastInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");
		
		//VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastDetails(new Long(id), new Long(publicationId),type);
		
		VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastWiseDetailsInALocation(userId,type,id,publicationId,jObj.getLong("constituencyId"));
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	}
	else if(jObj.getString("task").equalsIgnoreCase("getPartyInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		Long publicationId = jObj.getLong("publicationDateId");
	VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersPartyDetailsInALocation(userId,type,id,publicationId,jObj.getLong("constituencyId"));
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
		List<VoterCastInfoVO> sublevelCastDetails  = votersAnalysisService.getVotersCastDetailsForSubLevels(new Long(id), new Long(publicationId),type,userId,jObj.getLong("constituencyId"));
		constituencyManagementVO.setCastVosList(sublevelCastDetails);
	
		
	}
	
	else if(jObj.getString("task").equalsIgnoreCase("getVotersInACaste"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		String id=jObj.getString("id");
		String publicationDateId = jObj.getString("publicationDateId");
		String casteStateId=jObj.getString("caste");
		String type = jObj.getString("type");
		List<VoterHouseInfoVO> votersByHouseNos=votersAnalysisService.getVoterDetailsByCaste(new Long(id),new Long(publicationDateId),new Long(casteStateId),type);
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
	   importantFamiliesInfoVo = votersAnalysisService.getImportantFamiliesInfo(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getLong("constituencyId"));
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
	
	   importantFamiliesInfoVo = votersAnalysisService.getImportantFamaliesDetailsForHamlet(userId,jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getLong("constituencyId"));
	return Action.SUCCESS;
}

public String getVotersFamilyDetails(){
	try{
		String param;
		param = getTask();
		jObj = new JSONObject(param);
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  regVO.getRegistrationID();
		
		if(jObj.getString("task").equalsIgnoreCase("gettotalimpfamlies"))
		   votersFamilyInfo = votersAnalysisService.getVoterHouseInfoDetails(userId,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("type"));
		else
			//votersFamilyInfo = votersAnalysisService.getFamilyInfo(jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"));
			votersFamilyInfo = votersAnalysisService.getFamilyInformation(jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"),userId);
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
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  regVO.getRegistrationID();
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
	
	if(jObj.getString("task").equalsIgnoreCase("getCountForLevel"))
	{
		Long publicationDateId= jObj.getLong("publicationDateId");
		Long id = jObj.getLong("id");
		String type = jObj.getString("type");
		countList = votersAnalysisService.getCountList(publicationDateId,id,type, jObj.getLong("constituencyId"));
		}
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
		Long userId =  regVO.getRegistrationID();
		
		Long locationValue = jObj.getLong("locationValue");
		Long locationId = jObj.getLong("locationId");
		
		if(jObj.getString("task").equalsIgnoreCase("getProblemsByLocation"))
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
		
		List<VotersDetailsVO> votersDeatailsForConstituency = votersAnalysisService.getVoterAgeWiseDetails(constituencyId, mandalId,
		panchayatId , boothId, publicationDateId,type);
		if(votersDeatailsForConstituency == null || votersDeatailsForConstituency.size() == 0)
		votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,
		panchayatId , boothId, publicationDateId,type);
		
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
		
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByPanchayatId(panchayatId,publicationDateId, type,constituencyId);
		if(boothVotersDetails == null || boothVotersDetails.size() == 0)
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
		constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
		}
		else if (type.equalsIgnoreCase("hamlet")){
			
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByPanchayatId(panchayatId,publicationDateId, type,constituencyId);
			if(boothVotersDetails == null || boothVotersDetails.size() == 0)
			boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
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
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null){
				task = "notLogged";
				return Action.SUCCESS;
			}
			task = cadreManagementService.updateCadreVoterId(jObj.getLong("cadreId"),jObj.getLong("voterId"),user.getRegistrationID());
		}catch (Exception e) {
			log.error("Exception Occured in addVoterToCadre() Method, Exception - ",e);
			task =  "error";
		}
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
		Long userId =  regVO.getRegistrationID();
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
			Long userId =  regVO.getRegistrationID();
			Long locationValue =request.getParameter("locationValue") != null ?Long.parseLong(request.getParameter("locationValue")):0L;
			String type =request.getParameter("type");
			String buttonName =request.getParameter("buttonName");
			Long publicationId = request.getParameter("publicationDateId") != null ?Long.parseLong(request.getParameter("publicationDateId")):0L;
			votersList = new ArrayList<VoterVO>();
			votersList = votersAnalysisService.getInfluencingPeopleVoterDetails(
						userId,locationValue,type,buttonName,
						publicationId,startIndex ,maxRecords);

			constituencyManagementVO.setVoterDetails(votersList);
			if(votersList.size() > 0 )
			constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
			
		}catch(Exception e){
			
			e.printStackTrace();
			log.error("Exception Occured in getVoterDetails() Method,Exception is- "+e);
			
		}
			
		return Action.SUCCESS;
}
}