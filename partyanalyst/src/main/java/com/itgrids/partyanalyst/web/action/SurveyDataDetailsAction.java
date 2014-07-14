package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyDetailReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyDataDetailsAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = -707498402789127163L;
	public static final Logger LOG = Logger.getLogger(SurveyDataDetailsAction.class);
	HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private ResultStatus resultStatus;
	private List<GenericVO> returnList;
	@Autowired
	private ISurveyDataDetailsService surveyDataDetailsService;
	@Autowired
	private ISurveyDetailsService surveyDetailsService;
	private List<UserBoothDetailsVO> assgnedBoothsList;
	private List<SurveyReportVO> dayWiseReportList;
	private List<SelectOptionVO> constituenciesList,surveyUserConstituencies,dataAvilableConstituencies;
	private List<SurveyReportVO> boothWiseCountList,assignedUsersList;
	private List<SurveyReportVO> voterVerificationList;
	private List<SurveyResponceVO> responceList;
	private String status;
	private List<Long> countList;
	private GenericVO genericVO;
	private Long userTypeId;
	private ConstituencyDetailReportVO reportVO;
	private Long districtId;
	private Long userId;
	private String date;
	private List<SurveyReportVO> surveyUserDetails;
	private EntitlementsHelper entitlementsHelper;

	
	
	public List<SelectOptionVO> getDataAvilableConstituencies() {
		return dataAvilableConstituencies;
	}

	public void setDataAvilableConstituencies(
			List<SelectOptionVO> dataAvilableConstituencies) {
		this.dataAvilableConstituencies = dataAvilableConstituencies;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public List<SurveyReportVO> getSurveyUserDetails() {
		return surveyUserDetails;
	}

	public void setSurveyUserDetails(List<SurveyReportVO> surveyUserDetails) {
		this.surveyUserDetails = surveyUserDetails;
	}

	public List<SelectOptionVO> getSurveyUserConstituencies() {
		return surveyUserConstituencies;
	}

	public void setSurveyUserConstituencies(
			List<SelectOptionVO> surveyUserConstituencies) {
		this.surveyUserConstituencies = surveyUserConstituencies;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public ConstituencyDetailReportVO getReportVO() {
		return reportVO;
	}

	public void setReportVO(ConstituencyDetailReportVO reportVO) {
		this.reportVO = reportVO;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public GenericVO getGenericVO() {
		return genericVO;
	}

	public void setGenericVO(GenericVO genericVO) {
		this.genericVO = genericVO;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public List<SurveyReportVO> getAssignedUsersList() {
		return assignedUsersList;
	}

	public void setAssignedUsersList(List<SurveyReportVO> assignedUsersList) {
		this.assignedUsersList = assignedUsersList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SurveyReportVO> getVoterVerificationList() {
		return voterVerificationList;
	}

	public void setVoterVerificationList(List<SurveyReportVO> voterVerificationList) {
		this.voterVerificationList = voterVerificationList;
	}

	public List<SurveyReportVO> getBoothWiseCountList() {
		return boothWiseCountList;
	}

	public void setBoothWiseCountList(List<SurveyReportVO> boothWiseCountList) {
		this.boothWiseCountList = boothWiseCountList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<SurveyReportVO> getDayWiseReportList() {
		return dayWiseReportList;
	}

	public void setDayWiseReportList(List<SurveyReportVO> dayWiseReportList) {
		this.dayWiseReportList = dayWiseReportList;
	}

	public List<UserBoothDetailsVO> getAssgnedBoothsList() {
		return assgnedBoothsList;
	}

	public void setAssgnedBoothsList(List<UserBoothDetailsVO> assgnedBoothsList) {
		this.assgnedBoothsList = assgnedBoothsList;
	}
	
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	public List<GenericVO> getReturnList() {
		return returnList;
	}
	public void setReturnList(List<GenericVO> returnList) {
		this.returnList = returnList;
	}
	
	
	public List<SurveyResponceVO> getResponceList() {
		return responceList;
	}

	public void setResponceList(List<SurveyResponceVO> responceList) {
		this.responceList = responceList;
	}

	
	public List<Long> getCountList() {
		return countList;
	}

	public void setCountList(List<Long> countList) {
		this.countList = countList;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.INPUT;
		}
		constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();
		
		dataAvilableConstituencies = surveyDataDetailsService.getSurveyStartedConstituencyList();

		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String saveSurveyUser()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.saveSurveyUser(jObj.getString("firstName"), jObj.getString("lastName"), jObj.getString("userName"), jObj.getString("password"), jObj.getString("address"), jObj.getString("mobileNo"), jObj.getLong("userType"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUser in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String assignTab()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			JSONArray totalTabsInfo = jObj.getJSONArray("tabsArr");
			List<BasicVO> assignTabsInfo = null;
			
			if(totalTabsInfo != null && totalTabsInfo.length()>0){
				assignTabsInfo = new ArrayList<BasicVO>();
				for (int i = 0;i<totalTabsInfo.length();i++) {
					
					JSONObject obj =  totalTabsInfo.getJSONObject(i);
					
						String tabNo = obj.getString("tabNo");
						String assignDate = obj.getString("date");
						
						BasicVO vo = new BasicVO();
						
						vo.setCasteName(tabNo.toString());
						vo.setName(assignDate);
						assignTabsInfo.add(vo);
				}
			}
			
	
			//resultStatus = surveyDataDetailsService.saveSurveyUserTabAssign( jObj.getLong("surveyUserId"), jObj.getString("tabNo"), jObj.getString("remarks"),convertDate);
			resultStatus = surveyDataDetailsService.saveSurveyUserTabAssign( jObj.getLong("surveyUserId"),assignTabsInfo);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in assignTab in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String deactiveSurveyUser()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.deactivateUser( jObj.getLong("userId"),jObj.getString("remarks"),jObj.getLong("deactiveUserType"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in deactiveSurveyUser in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	public String deactiveSurveyLeader()

	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.deactiveSurveyLeader(jObj.getLong("userId"),jObj.getString("remarks"),jObj.getLong("deactiveUserType"),jObj.getLong("leadId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in deactiveSurveyUser in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String saveSurveyUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.saveSurveyUserType( jObj.getString("description"), jObj.getString("userType"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUserType in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getUserTypes();			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUserType in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyConstituencyList(){
		
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyConstituencyList();			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyConstituencyList() in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	
	public String getSurveyConstituencyLeadersList(){
		
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyConstituencyLeadersList(Long.valueOf(jObj.getString("constiId")));			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyConstituencyList() in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}

	
	public String releaseLeadersWithUserandTabsList()
	{
		try {
			
			jObj = new JSONObject(getTask());		
			genericVO = surveyDataDetailsService.releaseLeadersWithUserandTabsList(jObj.getLong("leaderId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in releaseLeadersWithUser in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getSurveyUsersByUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyUsersByUserType(jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUsersByUserType in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getExistedSurveyUsersByUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getExistedSurveyUsersByUserType(jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getExistedSurveyUsersByUserType in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String getExistedConstituenciesDetails()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getExistedConstituenciesDetailsByUserId(jObj.getLong("userId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getExistedConstituenciesDetails in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getNotAssignedSurveyUsersByUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyUsersForAssignToLeader(jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getNotAssignedSurveyUsersByUserType in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	public String saveServeyUserRelationDetails()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			List<Long> userIds = new ArrayList<Long>();
			JSONArray jarray = jObj.getJSONArray("userIds");
			for (Integer i = 0; i < jarray.length(); i++) 
			{
				userIds.add(Long.valueOf(jarray.get(i).toString()));
			}
			resultStatus = surveyDataDetailsService.saveServeyUserRelationDetails(jObj.getLong("userTypeId"),userIds,jObj.getLong("leaderId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveServeyUserRelationDetails in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getSurveyLeaders()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getConstituencyWiseLeaders();
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyLeaders in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyUsersByLeaders()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyUsersByLeades(jObj.getLong("leaderId"),jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUsersByLeaders in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getAssignedBoothsDetailsByConstituencyIdAndUserId()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			Long constituencyId = jObj.getLong("constituencyId");
			Long userId = jObj.getLong("userId");
			
			assgnedBoothsList = surveyDataDetailsService.getAssignedBoothsDetailsByConstituencyIdAndUserId(constituencyId,userId);
					
			
		} catch (Exception e) {
			LOG.error("Exception raised in getAssignedBoothsDetailsByConstituencyIdAndUserId in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String saveUserAssignedBoothsDetails()
	{
		
		try {
			jObj = new JSONObject(getTask());
			
			 JSONArray boothDetails = jObj.getJSONArray("boothIds");
			 
			 Long surveyUserId = jObj.getLong("surveyUserId");
			 Long constituencyId = jObj.getLong("constituencyId");
			 String remainignDataBooths = jObj.getString("remainingDataBooths");
			 
			 List<Long> boothIds = new ArrayList<Long>();
			 
			for(int i = 0 ; i < boothDetails.length() ; i++)
			{
				boothIds.add(Long.valueOf(boothDetails.get(i).toString()));
			}
			
			resultStatus = surveyDataDetailsService.saveSurveyUserBoothAssign(surveyUserId,constituencyId,boothIds,remainignDataBooths);
			
		} catch (Exception e) {
			LOG.error("Exception raised in saveUserAssignedBoothsDetails in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getDayWisereportDetailsByConstituencyId()
	{
		try {
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long userTypeId = jObj.getLong("userTypeId");
			
			dayWiseReportList = surveyDataDetailsService
					.getDayWisereportDetailsByConstituencyId(jObj
							.getLong("constituencyId"),startDate,endDate,userTypeId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getDayWisereportDetailsByConstituencyId in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDayWiseReportByConstituencyIdAndUserType()
	{
		try {
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long userTypeId = jObj.getLong("userTypeId");
			
			JSONArray boothDetails = null;
			 List<Long> boothIds = new ArrayList<Long>();
			
			if(!jObj.get("boothIds").equals(null))
			{
			   boothDetails =(JSONArray) jObj.get("boothIds");
			 
				for(int i = 0 ; i < boothDetails.length() ; i++)
				{
					boothIds.add(Long.valueOf(boothDetails.get(i).toString()));
				}
			}
			
			
			
			dayWiseReportList = surveyDataDetailsService.getDayWiseReportByConstituencyIdAndUserType(jObj.getLong("constituencyId"),  startDate, endDate,userTypeId,boothIds);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseUserSamplesDetailsByDates()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			//String endDate = jObj.getString("endDate");
			Long userId = jObj.getLong("userId");
			
			boothWiseCountList = surveyDataDetailsService.getBoothWiseUserSamplesDetailsByDates(userId,startDate);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothWiseUserSamplesDetailsByDates in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getReportForVerificationByBoothId()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			voterVerificationList = surveyDataDetailsService.getReportForVerification(jObj.getLong("boothId"),jObj.getString("type"));
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getReportForVerificationByBoothId in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}

	
	public String releaseLeadersWithUser()
	{
		try {
			
			jObj = new JSONObject(getTask());		
			returnList = surveyDataDetailsService.releaseLeadersWithUser(jObj.getLong("leaderId"),jObj.getLong("userType"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in releaseLeadersWithUser in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String updateServeyUserRelationDetails()
	{
		try {
			
			jObj = new JSONObject(getTask());
			List<Long> userIds = new ArrayList<Long>();
			JSONArray jarray = jObj.getJSONArray("userIds");
			for (Integer i = 0; i < jarray.length(); i++) 
			{
				userIds.add(Long.valueOf(jarray.get(i).toString()));
			}
			resultStatus = surveyDataDetailsService.updateServeyUserRelationDetails(jObj.getLong("userTypeId"),userIds,jObj.getLong("leaderId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in updateServeyUserRelationDetails in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getBoothDetailsByConstituency()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			//Long constituencyId = jObj.getLong("constituencyId");
			assgnedBoothsList = surveyDataDetailsService.getBoothDetailsByConstituencyId(jObj.getLong("constituencyId"));
					
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothDetailsByConstituency in SurveyDataDetailsAction", e);
		}
		
		return Action.SUCCESS;
		
	}
	
	
	public String saveVerifiedRecordsDetails()
	{
		try {
			
			List<Long> verifiedIds = new ArrayList<Long>();
			jObj = new JSONObject(getTask());
			JSONArray jarray = jObj.getJSONArray("verifiedIds");
			for (Integer i = 0; i < jarray.length(); i++) 
			{
				verifiedIds.add(Long.valueOf(jarray.get(i).toString()));
			}
			
			status = surveyDataDetailsService.saveVerifiedRecordsDetails(verifiedIds);
			
		} catch (Exception e) {
			LOG.error("Exception raised in saveVerifiedRecordsDetails in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getLatLongForUserTrackung()
	{
		try
		{
			jObj = new JSONObject(getTask());
			String dateStr = jObj.getString("dateStr");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = originalFormat.parse(dateStr);
			returnList = surveyDataDetailsService.getLatLongForUserTrackung(jObj.getLong("surveyUserId"),date);
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLatLongForUserTrackung in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getLatLongForSurveyDetails()
	{
		try
		{
			jObj = new JSONObject(getTask());
			String dateStr = jObj.getString("dateStr");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = originalFormat.parse(dateStr);
			responceList = surveyDataDetailsService.getLatLongForSurveyDetails(jObj.getLong("surveyUserId"),date);
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLatLongForSurveyDetails in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getAssignedConstituencyUsers()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			assignedUsersList = surveyDataDetailsService.getAllAssignedConstituenciesUsers(jObj.getLong("userTypeId"));
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAssignedConstituencyUsers in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getAssignedConstituencies()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			constituenciesList = surveyDataDetailsService.getAllAssignedConstituency(jObj.getLong("userTypeId"));
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAssignedConstituencyUsers in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
	
	public String assignConstituencyToUser()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.assignConstituencyForAUser(jObj.getLong("userId"), jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUser in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String assignTabsForUsers(){
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			JSONArray totalTabsInfo = jObj.getJSONArray("userTabsArr");
			List<BasicVO> assignTabsInfo = null;
			
			if(totalTabsInfo != null && totalTabsInfo.length()>0){
				assignTabsInfo = new ArrayList<BasicVO>();
				for (int i = 0;i<totalTabsInfo.length();i++) {
					
					JSONObject obj =  totalTabsInfo.getJSONObject(i);
					
						Long candId = obj.getLong("candId");
						Long tabId = obj.getLong("tabId");
						
						BasicVO vo = new BasicVO();
						
						vo.setId(candId);
						vo.setCount(tabId);
						assignTabsInfo.add(vo);
				}
			}
			
			resultStatus = surveyDataDetailsService.saveSurveyUserTabAssign(assignTabsInfo);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in assignTab in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String constituencyDetailReport(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			//constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();			
			constituenciesList = surveyDataDetailsService.getConstituencyListByDistrictId(districtId);
		} catch (Exception e) {
			LOG.error(" exception occured in constituencyDetailReport() ,ConstituencyDetailsAction Action class",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getCosntituencyWiseReportByContiId(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			
			reportVO = surveyDataDetailsService.getCosntituencyWiseReportByContiId(constituencyId);
			
		} catch (Exception e) {
			LOG.error(" exception occured in getCosntituencyWiseReportByContiId() ,ConstituencyDetailsAction Action class",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getBoothWiseDetails(){
		
		try{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
		{
			return Action.INPUT;
		}
		Long userId = user.getRegistrationID();
		jObj = new JSONObject(getTask());
		Long constituencyId = jObj.getLong("constituencyId");
		Long boothId = jObj.getLong("boothId");
		reportVO = surveyDataDetailsService.getBoothWiseDetails(boothId,constituencyId);
		
	} catch (Exception e) {
		LOG.error(" exception occured in getBoothWiseDetails() ,ConstituencyDetailsAction Action class",e);
	}
	return Action.SUCCESS;
	}
	
	
	public String getLatLongForSurveyUsersByConstituency()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			String dateStr = jObj.getString("dateStr");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = originalFormat.parse(dateStr);
			Long userId = jObj.getLong("userId");
			constituenciesList = surveyDataDetailsService.getLatLongForSurveyUsersByConstituency(jObj.getLong("constituencyId"), date,userId);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUser in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getRespectiveCountForBooth()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			countList = surveyDataDetailsService.getDataCollectedCount(jObj.getLong("userId"), jObj.getLong("boothId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUser in ConstituencyDetailsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String surveyCallCenterPage(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			
			if(session.getAttribute(IConstants.USER) == null && 
					!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CASTE_SURVEY_CALL_CENTER))
				return INPUT;
			if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CASTE_SURVEY_CALL_CENTER))
				return ERROR;
			constituenciesList = 	surveyDataDetailsService.getSurveyStartedConstituencyList();			
		} catch (Exception e) {
			LOG.error(" exception occured in surveyCallCenterPage() ,ConstituencyDetailsAction Action class",e);
		}
		return Action.SUCCESS;
		
	}
	
	
  public String getAssignedBoothsForLeader(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long surveyUserId = jObj.getLong("surveyUserId");
			Long constituencyId = jObj.getLong("constituencyId");
			constituenciesList = 	surveyDataDetailsService.getAssignedBoothDetailsByuserId(constituencyId,surveyUserId);			
		} catch (Exception e) {
			LOG.error(" exception occured in getAssignedBoothsForLeader() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
	


  public String getSurveyVotersList(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long surveyUserId = jObj.getLong("surveyUserId");
			Long boothId = jObj.getLong("boothId");
			Long constituencyId = jObj.getLong("constituencyId");
			voterVerificationList = 	surveyDataDetailsService.getSurveyVotersList(constituencyId,boothId,surveyUserId);			
		} catch (Exception e) {
			LOG.error(" exception occured in getSurveyVotersList() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
  
  public String getSurveyUserNameAndPasswordByLeader(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			Long surveyUserId = jObj.getLong("surveyUserId");
			returnList = 	surveyDataDetailsService.getSurveyUserNameAndPasswordByLeader(surveyUserId);			
		} catch (Exception e) {
			LOG.error(" exception occured in getSurveyVotersList() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
	
  
  public String saveSurveyCallStatusDetils(){
	  
	  try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			JSONArray voterInfoArr = jObj.getJSONArray("voterInfoArr");
			List<SurveyReportVO> verifiedList = new ArrayList<SurveyReportVO>();
			if(voterInfoArr.length()>0){
				for(int i=0;i<voterInfoArr.length();i++){
					
					JSONObject obj = voterInfoArr.getJSONObject(i);
					
					SurveyReportVO vo = new SurveyReportVO();
				
					vo.setMobileNo(obj.getString("isMobileVerified"));
					vo.setMatchedCount(obj.getLong("isMatched"));
					vo.setUserid(obj.getLong("surveyUserId"));
					vo.setVoterId(obj.getLong("voterId"));
					vo.setBoothId(obj.getLong("boothId"));
					verifiedList.add(vo);
					
				}
			}			
			
			resultStatus = surveyDataDetailsService.saveSurveyCallStatusDetils(userId,verifiedList);
			
		} catch (Exception e) {
			LOG.error(" exception occured in saveSurveyCallStatusDetils() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
	  
  }
  public String getsurveyuserConstituencies(){
		
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			surveyUserConstituencies = surveyDataDetailsService.getsurveyuserConstituencies();			
		} catch (Exception e) {
			LOG.error(" exception occured in getSurveyVotersList() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
  
  
  public String getSurveyDetailsReport()
  {
	  try{
		  jObj = new JSONObject(getTask());
		  surveyUserDetails =  surveyDataDetailsService.getSurveyDetailsForConstituency(jObj.getLong("constituencyId") ,jObj.getLong("userTypeId"),jObj.getString("date"));  
	  }
	  catch (Exception e) {
		e.printStackTrace();
		LOG.error(" exception occured in getSurveyDetailsReport() ,ConstituencyDetailsAction class",e);
	}
  
	  return Action.SUCCESS;
  }
  
  public String getSurveyUserDetailsByConstituencyId()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			String date = jObj.getString("date");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = originalFormat.parse(date);
			surveyUserDetails = surveyDataDetailsService.getSurveyUserDetailsByConstituencies(jObj.getLong("constituencyId"), date1);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUserDetailsByConstituencyId", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
  
  public String getSurveyConstituencyUsersList(){
		
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyConstituencyUsersList(Long.valueOf(jObj.getString("constiId")));			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyConstituencyList() in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
  
     public String getConstituencyWiseLeaders()
     {
		
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDetailsService.getConstituencyWiseLeaders(Long.valueOf(jObj.getString("constiId")));			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyWiseLeaders() in SurveyDataDetailsAction", e);
		}
		return Action.SUCCESS;
		
	}
	
  
}
