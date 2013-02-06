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
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.ProblemManagementService;
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
		else if(jObj.getString("task").equalsIgnoreCase("getPublicationDate"))
		{
			Long selectedId = jObj.getLong("selected");
			namesList = votersAnalysisService.publicationDetailsBasedOnConstituency(selectedId);
			namesList.add(0, new SelectOptionVO(0L,"Select Publication Date"));
		}
			
		return Action.SUCCESS;
	}

public String getVoterDetails(){
	
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
		
		
		
		Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
		Long boothId = request.getParameter("boothId") != null ? Long.parseLong(request.getParameter("boothId")):0L;
		Long panchaytId = request.getParameter("panchaytId") != null? Long.parseLong(request.getParameter("panchaytId")) :0L;
		
			votersList = new ArrayList<VoterVO>();
			
			if(boothId == 0 && panchaytId != 0)			
				votersList = votersAnalysisService.getVoterDetails(
						publicationId, null, panchaytId, startIndex,
						maxRecords, order, columnName);
			else if(boothId != 0 && panchaytId == 0)
				votersList = votersAnalysisService.getVoterDetails(
						publicationId, boothId , null, startIndex, maxRecords,
						order, columnName);

		
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
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getVotersCount() Method,Exception is- "+e);
		}
		   votersInfo = votersAnalysisService.getVotersCount(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"));
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
		
		VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastWiseDetailsInALocation(userId,type,id,publicationId);
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	}
	
	else if(jObj.getString("task").equalsIgnoreCase("getCastInfoForsubLevels"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		String id = jObj.getString("id");
		String type = jObj.getString("type");
		String publicationId = jObj.getString("publicationDateId");
		List<VoterCastInfoVO> sublevelCastDetails  = votersAnalysisService.getVotersCastDetailsForSubLevels(new Long(id), new Long(publicationId),type,userId);
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
	   importantFamiliesInfoVo = votersAnalysisService.getImportantFamiliesInfo(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"));
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
			votersFamilyInfo = votersAnalysisService.getFamilyInfo(jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"));
	}catch(Exception e){
		log.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- "+e);
	}
	
	return SUCCESS;
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
		countList = votersAnalysisService.getCountList(publicationDateId,id,type);
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
		
		panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetaForPanchayatisByTehsilId(mandalId,publicationDateId, type);
		if(panchatyVotersDetails == null || panchatyVotersDetails.size() == 0)
		panchatyVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForPanchayatisByTehsilId(mandalId,publicationDateId);
		constituencyManagementVO.setPanchayatVotersDetails(panchatyVotersDetails);
		}
		else if (type.equalsIgnoreCase("panchayat")){
		
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByPanchayatId(panchayatId,publicationDateId, type);
		if(boothVotersDetails == null || boothVotersDetails.size() == 0)
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByPanchayatId(panchayatId,publicationDateId);
		constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
		}
		/*else if (type.equalsIgnoreCase("localElectionBody")){
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetForBoothsByLocalElectionBodyId(mandalId,publicationDateId, type);
		if(boothVotersDetails == null || boothVotersDetails.size() == 0)
		boothVotersDetails = votersAnalysisService.getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(mandalId,publicationDateId);
		constituencyManagementVO.setBoothVotersDetails(boothVotersDetails);
		}*/
		
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

}
