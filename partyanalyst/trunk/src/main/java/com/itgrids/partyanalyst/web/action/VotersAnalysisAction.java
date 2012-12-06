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
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
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
	
	private List<SelectOptionVO> constituencyList;
	
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

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = 2009l;
		Long electionTypeId = 2l;
		constituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
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
		constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
		
		return Action.SUCCESS;
		
	}
		
	public String getVotersCount(){
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		   votersInfo = votersAnalysisService.getVotersCount(jObj.getString("type"),jObj.getLong("id"),jObj.getLong("publicationDateId"));
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
	
	if(jObj.getString("task").equalsIgnoreCase("getCastInfo"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		String id = jObj.getString("id");
		String type = jObj.getString("type");
		String publicationId = jObj.getString("publicationDateId");
		
		VoterCastInfoVO votersByCast  = votersAnalysisService.getVotersCastDetails(new Long(id), new Long(publicationId),type);
		constituencyManagementVO.setVoterCastInfodetails(votersByCast);
	
		
	}
	else if(jObj.getString("task").equalsIgnoreCase("getCastInfoForsubLevels"))
	{
		constituencyManagementVO = new ConstituencyManagementVO();
		String id = jObj.getString("id");
		String type = jObj.getString("type");
		String publicationId = jObj.getString("publicationDateId");
		
		List<VoterCastInfoVO> sublevelCastDetails  = votersAnalysisService.getVotersCastDetailsForSubLevels(new Long(id), new Long(publicationId),type);
		constituencyManagementVO.setCastVosList(sublevelCastDetails);
	
		
	}
	
	return SUCCESS;
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
		
		//VOTER DETAILS OVERVIEW FOR CONSTITUENCY  OR MANDAL
		List<VotersDetailsVO> votersDeatailsForConstituency = votersAnalysisService.getVotersDetailsByAgewise(constituencyId, mandalId,
				panchayatId , boothId, publicationDateId,type);
	
       /* //VOTER DETAILS OVERVIEW  FOR BOOTH 
		
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
		*/
		
				
		
	/*	constituencyManagementVO.setVoterDetails(votersList);
		constituencyManagementVO.setMandalAgewiseVotersList(mandalAgeAndGenderVotersList);*/
		
		constituencyManagementVO.setVotersDetailsVO(votersDeatailsForConstituency);
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
	}
  
  return Action.SUCCESS;
  
} 



}
