package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyDetailReportVO;
import com.itgrids.partyanalyst.dto.DcDvCollectedDataVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PanchayatHamletsCountVo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.SurveyThirdPartyReportVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.dto.VerificationCompVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ISurveyCompletedDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
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
	private List<GenericVO> usersList,constituencies,notStartesUsersList,boothsList;
	private RegionServiceDataImp regionServiceDataImp;
	private SurveyReportVO surveyReportVO;
	private SurveyDashBoardVO resultVO;
	private List<SurveyDashBoardVO> resultList;
	private Long constituencyId;
	private Long leaderId;
	private List<VerificationCompVO> verificationCompVOList;
	private PanchayatHamletsCountVo panchayatHamletsCountVo;
	private List<DcDvCollectedDataVO> dcDvCollectedDataVOList;
	private List<SurveyReportVO> reportList;
	
	
	@Autowired
	private ISurveyCompletedDetailsService surveyCompletedDetailsService;
	private List<SurveyThirdPartyReportVO> thirdPartyResultList;
	private SurveyThirdPartyReportVO tpFinalVO;
	
	private List<SurveyThirdPartyReportVO> tpFinalVOList;
	
	
	
	public List<SurveyThirdPartyReportVO> getTpFinalVOList() {
		return tpFinalVOList;
	}

	public void setTpFinalVOList(List<SurveyThirdPartyReportVO> tpFinalVOList) {
		this.tpFinalVOList = tpFinalVOList;
	}

	public SurveyThirdPartyReportVO getTpFinalVO() {
		return tpFinalVO;
	}

	public void setTpFinalVO(SurveyThirdPartyReportVO tpFinalVO) {
		this.tpFinalVO = tpFinalVO;
	}

	public List<SurveyThirdPartyReportVO> getThirdPartyResultList() {
		return thirdPartyResultList;
	}

	public void setThirdPartyResultList(
			List<SurveyThirdPartyReportVO> thirdPartyResultList) {
		this.thirdPartyResultList = thirdPartyResultList;
	}

	public List<SurveyReportVO> getReportList() {
		return reportList;
	}

	public void setReportList(List<SurveyReportVO> reportList) {
		this.reportList = reportList;
	}
	
	
	public List<DcDvCollectedDataVO> getDcDvCollectedDataVOList() {
		return dcDvCollectedDataVOList;
	}

	public void setDcDvCollectedDataVOList(
			List<DcDvCollectedDataVO> dcDvCollectedDataVOList) {
		this.dcDvCollectedDataVOList = dcDvCollectedDataVOList;
	}

	public List<GenericVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<GenericVO> boothsList) {
		this.boothsList = boothsList;
	}

	public PanchayatHamletsCountVo getPanchayatHamletsCountVo() {
		return panchayatHamletsCountVo;
	}

	public void setPanchayatHamletsCountVo(
			PanchayatHamletsCountVo panchayatHamletsCountVo) {
		this.panchayatHamletsCountVo = panchayatHamletsCountVo;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	@Autowired
	private ISurveyDashBoardService surveyDashBoardService;
	
	public List<GenericVO> getNotStartesUsersList() {
		return notStartesUsersList;
	}

	public void setNotStartesUsersList(List<GenericVO> notStartesUsersList) {
		this.notStartesUsersList = notStartesUsersList;
	}

	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	
	public List<SurveyDashBoardVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<SurveyDashBoardVO> resultList) {
		this.resultList = resultList;
	}

	public SurveyDashBoardVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(SurveyDashBoardVO resultVO) {
		this.resultVO = resultVO;
	}

	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public SurveyReportVO getSurveyReportVO() {
		return surveyReportVO;
	}

	public void setSurveyReportVO(SurveyReportVO surveyReportVO) {
		this.surveyReportVO = surveyReportVO;
	}

	public List<GenericVO> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<GenericVO> usersList) {
		this.usersList = usersList;
	}

	public List<GenericVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<GenericVO> constituencies) {
		this.constituencies = constituencies;
	}

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

	
	public List<VerificationCompVO> getVerificationCompVOList() {
		return verificationCompVOList;
	}

	public void setVerificationCompVOList(
			List<VerificationCompVO> verificationCompVOList) {
		this.verificationCompVOList = verificationCompVOList;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return Action.INPUT;
		
		constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();
		
		dataAvilableConstituencies = surveyDataDetailsService.getSurveyStartedConstituencyList();
		//resultVO = surveyDashBoardService.getCompletdConstituenciesDetails();
		//resultList = surveyDashBoardService.getConstituencyWiseCompletionReport(); 
		usersList = surveyDataDetailsService.getAllWebMonitoringUsersDetails();
		//reportList = surveyCompletedDetailsService.getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();
		constituencies = surveyDashBoardService.getConstituencyListForThirdPartyReport();
		
		//resultVO =  surveyCompletedDetailsService.getCompletdConstituenciesDetails();
		
		//resultVO =  surveyCompletedDetailsService.getCompletedLocationsDetails();
		return Action.SUCCESS;
	}

	public String executeDashBoard()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return Action.INPUT;
		return Action.SUCCESS;
	}
	public String getSurveyCompletedDetails()
	{
		resultVO =  surveyCompletedDetailsService.getCompletedLocationsDetails();
		return Action.SUCCESS;
	}
	
	public String dataAvaliableConstituencyes()
	{
		dataAvilableConstituencies = surveyDataDetailsService.getSurveyStartedConstituencyList();
		return Action.SUCCESS;
	}
	
	public String getThirdAvaliableConstituencyes()
	{
		constituencies = surveyDashBoardService.getConstituencyListForThirdPartyReport();
		return Action.SUCCESS;
	}
	
	public String surveyDataDetailsexe()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.SURVEY_USER_CREATION))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.SURVEY_USER_CREATION))
			return INPUT;*/
		List<String> entitlements = null;
	    if(user.getEntitlements() != null && user.getEntitlements().size()>0){
	      entitlements = user.getEntitlements();
	      if(user == null && !entitlements.contains(IConstants.SURVEY_USER_CREATION)){
	        return INPUT;
	      }
	      if(!entitlements.contains(IConstants.SURVEY_USER_CREATION))
	    	  return INPUT;
		constituencies = surveyDashBoardService.getConstituencyListForThirdPartyReport();
		constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();
		dataAvilableConstituencies = surveyDataDetailsService.getSurveyStartedConstituencyList();
		resultVO = surveyDashBoardService.getCompletdConstituenciesDetails();
		//resultList = surveyDashBoardService.getConstituencyWiseCompletionReport(); 
		usersList = surveyDataDetailsService.getAllWebMonitoringUsersDetails();
	    }
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
	
	
	public String getSurveyUsersByUserTypeForDeActive()
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
			returnList = surveyDataDetailsService.getSurveyUsersByUserType(jObj.getLong("userTypeId"),jObj.getLong("constituencyId"));
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
			reportList = surveyDataDetailsService.getSurveyUsersByLeades(jObj.getLong("leaderId"),jObj.getLong("constituencyId"));
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
			
			JSONArray jarray = jObj.getJSONArray("userIds");
			List<Long> userIds = null;
			if(jarray != null && jarray.length() > 0)
			{
				userIds = new ArrayList<Long>();
				for (Integer i = 0; i < jarray.length(); i++) 
				{
					userIds.add(Long.valueOf(jarray.get(i).toString()));
				}
				
			}
			
			dayWiseReportList = surveyDataDetailsService.getDayWiseReportByConstituencyIdAndUserType(jObj.getLong("constituencyId"),  startDate, endDate,userTypeId,boothIds,userIds);
			
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
			resultStatus = surveyDataDetailsService.updateServeyUserRelationDetails(jObj.getLong("userTypeId"),userIds,jObj.getLong("leaderId"),null);
			
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
						String tabId = obj.getString("tabId");
						
						BasicVO vo = new BasicVO();
						
						vo.setId(candId);
						vo.setName(tabId);
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
			
			List<Long> userIds = null;
			jObj = new JSONObject(getTask());
			JSONArray userIdsArray = jObj.getJSONArray("userId");
			String dateStr = jObj.getString("dateStr");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = originalFormat.parse(dateStr);
			//Long userId = jObj.getLong("userId");
			if(userIdsArray != null && userIdsArray.length()>0){
				userIds = new ArrayList<Long>();
				for(int i=0;i<userIdsArray.length();i++){
					
					Long userId = Long.valueOf(userIdsArray.get(i).toString());
					userIds.add(userId);
					
				}
			}	
			constituenciesList = surveyDataDetailsService.getLatLongForSurveyUsersByConstituency(jObj.getLong("constituencyId"), date,userIds);
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
			/*if(session.getAttribute(IConstants.USER) == null && 
					!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CASTE_SURVEY_CALL_CENTER) )
				return INPUT;
			if(user.getEntitlements() != null )
			{
				if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), user.getEntitlements().get(0)))
					return INPUT;
			}*/
			List<String> entitlements = null;
		    if(user.getEntitlements() != null && user.getEntitlements().size()>0){
		      entitlements = user.getEntitlements();
		      if(user == null && !entitlements.contains(IConstants.CASTE_SURVEY_CALL_CENTER)){
		        return INPUT;
		      }
		  
			if(user.getEntitlements() != null )
			{
		      if(!entitlements.contains(user.getEntitlements().get(0)))
		    	  return INPUT;
			}
			
			
				constituenciesList = 	surveyDataDetailsService.getSurveyStartedConstituencyList();
				constituencies = surveyDashBoardService.getConstituencyListForThirdPartyReport();
				usersList = surveyDetailsService.getAssignedSurveyUsersForWebMontringTeam(user.getRegistrationID());
		    }
		} catch (Exception e) {
			LOG.error(" exception occured in surveyCallCenterPage() ,ConstituencyDetailsAction Action class",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getNotActiveUsersDetails()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
	
		notStartesUsersList =  surveyDetailsService.getNotStartedUsersDetails(user.getRegistrationID(),leaderId);

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
			String searchDate = jObj.getString("searchDate");
			Long userType = jObj.getLong("userType");
			voterVerificationList = 	surveyDataDetailsService.getSurveyVotersList(constituencyId,boothId,surveyUserId,searchDate,userType,null);			
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
			LOG.error(" exception occured in getSurveyUserNameAndPasswordByLeader() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
	
  public String saveSurveyCallStatusMobileDetils()
  {

	  try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			Long voterId = jObj.getLong("voterId");
			Long boothId = jObj.getLong("boothId");
			Long surveyUserId = jObj.getLong("surveyUserId");			
			Long userTypeId = jObj.getLong("userType");
			String selectedMobileType = jObj.getString("changetMobileType");
			Long mobileStatusId = jObj.getLong("statusId");
				
			resultStatus = surveyDataDetailsService.saveSurveyCallStatusMobileDetils(userId,voterId,boothId,surveyUserId,userTypeId,selectedMobileType,mobileStatusId);
			
					/*	
					JSONArray ctpMobileArr = obj.getJSONArray("ctpMobileArr");
					
					if(ctpMobileArr != null && ctpMobileArr.length() > 0)
					{
						List<String> mobileStatusList = new ArrayList<String>();
						for(int j=0;j<ctpMobileArr.length();i++)
						{
							mobileStatusList.add(ctpMobileArr.get(j).toString());
						}
						
						if(mobileStatusList != null && mobileStatusList.size()>0)
						{
							vo.setCtpMobileNoList(mobileStatusList);
						}
					}
					
					JSONArray surveyMobileArr = obj.getJSONArray("surveyMobileArr");
					
					if(surveyMobileArr != null && surveyMobileArr.length() > 0)
					{
						List<String> mobileStatusList = new ArrayList<String>();
						for(int j=0;j<surveyMobileArr.length();i++)
						{
							mobileStatusList.add(surveyMobileArr.get(j).toString());
						}
						
						if(mobileStatusList != null && mobileStatusList.size()>0)
						{
							vo.setSurveyMobileNoList(mobileStatusList);
						}
					}
					

					JSONArray dataMobileArr = obj.getJSONArray("dataMobileArr");
					
					if(dataMobileArr != null && dataMobileArr.length() > 0)
					{
						List<String> mobileStatusList = new ArrayList<String>();
						for(int j=0;j<dataMobileArr.length();i++)
						{
							mobileStatusList.add(dataMobileArr.get(j).toString());
						}
						
						if(mobileStatusList != null && mobileStatusList.size()>0)
						{
							vo.setDataMobileNoList(mobileStatusList);
						}
					}
					
					
					JSONArray ceoMobileArr = obj.getJSONArray("ceoMobileArr");
					
					if(ceoMobileArr != null && ceoMobileArr.length() > 0)
					{
						List<String> mobileStatusList = new ArrayList<String>();
						for(int j=0;j<ceoMobileArr.length();i++)
						{
							mobileStatusList.add(ceoMobileArr.get(j).toString());
						}
						
						if(mobileStatusList != null && mobileStatusList.size()>0)
						{
							vo.setCeoMobileNoList(mobileStatusList);
						}
					}
					
					verifiedList.add(vo);
				*/	
		
			
		} catch (Exception e) {
			LOG.error(" exception occured in saveSurveyCallStatusDetils() ,ConstituencyDetailsAction class",e);
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
					vo.setCasteId(obj.getLong("casteId"));
					vo.setUserTypeId(obj.getLong("userType"));
					vo.setHamletCount(obj.getLong("isHamletMatched"));
					vo.setHamletId(obj.getLong("hamletId"));
					vo.setName(obj.getString("updationType"));
					vo.setLocalArea(obj.getString("surveyArea"));
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
			LOG.error(" exception occured in getsurveyuserConstituencies() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
		
	}
  
  
  public String getSurveyDetailsReport()
  {
	  try{
		  jObj = new JSONObject(getTask());
		  JSONArray userIdsArray = jObj.getJSONArray("userIds");
		  List<Long> userIds = null;
		  if(userIdsArray != null && userIdsArray.length()>0){
			  userIds = new ArrayList<Long>();
				for(int i=0;i<userIdsArray.length();i++){
					
					Long userId =  Long.valueOf(userIdsArray.get(i).toString());
					userIds.add(userId);
					
				}
			}	
		  if(jObj.getString("task").equalsIgnoreCase("getBasicInfo"))
			  surveyUserDetails =  surveyDataDetailsService.getSurveyBasicDetailsForConstituency(jObj.getLong("constituencyId") ,jObj.getLong("userTypeId"),jObj.getString("date"),userIds,jObj.getString("toDate"));
		  else
			  surveyUserDetails =  surveyDataDetailsService.getSurveyDetailsForConstituency(jObj.getLong("constituencyId") ,jObj.getLong("userTypeId"),jObj.getString("date"),userIds,jObj.getString("toDate"));  
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
			 JSONArray userIdsArray = jObj.getJSONArray("userIds");
			  List<Long> userIds = null;
			  if(userIdsArray != null && userIdsArray.length()>0)
			  {
				  userIds = new ArrayList<Long>();
					for(int i=0;i<userIdsArray.length();i++){
						
						Long userId =  Long.valueOf(userIdsArray.get(i).toString());
						userIds.add(userId);
						
					}
			  }	
			//surveyUserDetails = surveyDataDetailsService.getSurveyUserDetailsByConstituencies(jObj.getLong("constituencyId"), date1,userIds);
			  
			  surveyUserDetails = surveyDataDetailsService.getAllUsersDetilsByUserIdsForSelectedDate(jObj.getLong("constituencyId"), date1,userIds);
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
	
  
  
  public String getAlreadyAssignTabsListForLeader(){
	  

		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());

			genericVO = surveyDataDetailsService.getAlreadyAssignTabsListForLeader(jObj.getLong("leaderId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAlreadyAssignTabsListForLeader", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
  }
  
  
  public String getDistrictDetailsForConstituency(){
	  

		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());

			constituenciesList = regionServiceDataImp.getStateDistrictByConstituencyID(jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAlreadyAssignTabsListForLeader", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
}

  
  public String assignUsersToWebMonitoringTeam()
  {
	  usersList = surveyDataDetailsService.getAllWebMonitoringUsersDetails();
	  constituenciesList =  surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();

	  return Action.SUCCESS;
  }
  public String getTotalCasteCollectedCounts(){
	  

		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());

			surveyReportVO = surveyDataDetailsService.getTotalCasteCollectedCount();
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAlreadyAssignTabsListForLeader", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
  }
  
  public String getCasteCollectedCountsForDates()
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

			surveyUserDetails = surveyDataDetailsService.getCasteCollectedCountsForDates(jObj.getString("date"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAlreadyAssignTabsListForLeader", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;  
  }
  
  
  public String getAssignedUsersOfAConstituency()
  {
	  constituencies =  surveyDataDetailsService.getAssignedUsersOfAConstituency(constituencyId);
	  return Action.SUCCESS;
  }
  
  public String saveWebMonioringAssignDetails()
  {
	  try {
		jObj = new JSONObject(getTask());
		  
		  List<Long> userIds = new ArrayList<Long>();
			JSONArray jarray = jObj.getJSONArray("userIds");
			for (Integer i = 0; i < jarray.length(); i++) 
			{
				userIds.add(Long.valueOf(jarray.get(i).toString()));
			}
		  
		 status =  surveyDataDetailsService.saveWebMonioringAssignDetails(jObj.getLong("webMonitorUserId"),userIds);
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  return Action.SUCCESS;
  }
  
  public String getConstituencyDetailsForSurveyUser(){
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());

			surveyUserDetails = surveyDetailsService.getSurveyUserConstituencyDetails(jObj.getLong("userId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyDetailsForSurveyUser", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
  }
  
  public String unTagConstituencyOfUser()
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
			resultStatus = surveyDetailsService.unTagConstituencyForAUser(jObj.getLong("userId"), jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser", e);
		}
		return Action.SUCCESS;
	}
 
  public String getSurveyStatusBoothList(){
	  
	  try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			
			genericVO = surveyDetailsService.getSurveyStatusBoothList(jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser()  in SurveyDataDetailsAction class.", e);
		}
		return Action.SUCCESS;
  }
   
  public String getUsersOfALeader(){
	  

		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());

			usersList = surveyDetailsService.getUserForAssignedLeader(jObj.getLong("leaderId"),jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getUsersOfALeader", e);
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
}

public String getSurveyBoothDetails()
{
	  try{
		  jObj = new JSONObject(getTask());
		  
		  Long status = jObj.getLong("status");
		  Long constituency = jObj.getLong("constituency");
		  Long scopeId = jObj.getLong("scope");
		  
		  
		    /*JSONArray booths = jObj.getJSONArray("boothIds");
   		List<Long> boothIds = new ArrayList<Long>();			 
			for(int i = 0 ; i < booths.length() ; i++)
			{
				boothIds.add(Long.valueOf(booths.get(i).toString()));
			}*/
		  
		  
		  
		  boothWiseCountList =  surveyDetailsService.getSurveyDetailsByConstituencyAndStatus(constituency,status,scopeId);  
	  }
	  catch (Exception e) {
		e.printStackTrace();
		LOG.error(" exception occured in getSurveyBoothDetails()",e);
	}
	return Action.SUCCESS;
}
public String getPanchayatsStatus()
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
			surveyReportVO = surveyDetailsService.getPanchayatsStatusCountByConstituency(jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser", e);
		}
		return Action.SUCCESS;
	}

public String getPanchayatsStatusDetails()
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
		surveyUserDetails = surveyDetailsService.getPanchayatsStatusWiseDataByConstituency(jObj.getLong("constituencyId"),jObj.getString("status"));
	} 
	catch (Exception e)
	{
		LOG.error("Exception raised in unTagConstituencyOfUser", e);
	}
	return Action.SUCCESS;
}
  
  public String checkForVerifierData()
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
 			List<Long> boothIds = new ArrayList<Long>();
			JSONArray jarray = jObj.getJSONArray("boothIds");
			for (Integer i = 0; i < jarray.length(); i++) 
			{
				boothIds.add(Long.valueOf(jarray.get(i).toString()));
			}
 			verificationCompVOList = surveyDetailsService.checkForVerifierData(boothIds);
 		} 
 		catch (Exception e)
 		{
 			LOG.error("Exception raised in unTagConstituencyOfUser", e);
 		}
 		return Action.SUCCESS;
 	}
  public String getHamletDetialsByPanchayat()
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
  		panchayatHamletsCountVo = surveyDetailsService.getSurveyDataCountForHamletsByPanchayats(jObj.getLong("panchayatId"));
  	} 
  	catch (Exception e)
  	{
  		LOG.error("Exception raised in unTagConstituencyOfUser", e);
  	}
  	return Action.SUCCESS;
  }
  
  
  public String getBoothsInCallStatus()
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
  		boothsList = surveyDetailsService.getBoothsInCallStatus(jObj.getLong("constituencyId"));
  	} 
  	catch (Exception e)
  	{
  		LOG.error("Exception raised in unTagConstituencyOfUser", e);
  	}
  	return Action.SUCCESS;
  }
  
  
  public String getBoothsInSurveyDetailsInfo()
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
  		boothsList = surveyDetailsService.getBoothsInSurveyDetailsInfo(jObj.getLong("constituencyId"));
  	} 
  	catch (Exception e)
  	{
  		LOG.error("Exception raised in unTagConstituencyOfUser", e);
  	}
  	return Action.SUCCESS;
  }
  
    
  public String checkForWebMonitorData()
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
			
			
			verificationCompVOList = surveyDetailsService.checkForWebMonitorData(jObj.getLong("boothId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser", e);
		}
		return Action.SUCCESS;
	}
  
  public String getDcAndDvByConstituencyByUser()
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
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			JSONArray useridsArr = jObj.getJSONArray("surveyUserId");
			
			List<Long> userIds = new ArrayList<Long>();
			if(useridsArr != null && useridsArr.length() > 0)
			{
				for(int i = 0 ; i<useridsArr.length();i++)
				{
					Long userId = Long.valueOf(useridsArr.get(i).toString().replace("\"", "").replace("[", "").replace("]", "").trim());
					userIds.add(userId);
				}
			}
			
			
			JSONArray constitiencyArr = jObj.getJSONArray("constitiencyIds");
			List<Long> constitiencyIds = new ArrayList<Long>();
			if(constitiencyArr != null && constitiencyArr.length() > 0)
			{
				for(int i = 0 ; i<constitiencyArr.length();i++)
				{
					Long userId = Long.valueOf(constitiencyArr.get(i).toString().replace("\"", "").replace("[", "").replace("]", "").trim());
					constitiencyIds.add(userId);
				}
			}
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			dcDvCollectedDataVOList = surveyDetailsService.getDcAndDvByConstituencyByUser(constitiencyIds,userIds,jObj.getLong("userTypeId"),originalFormat.parse(fromDateStr),originalFormat.parse(toDateStr));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser", e);
		}
		return Action.SUCCESS;
	}
  public String getDcorDvUsersByConstituency()
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
			
			
			usersList = surveyDetailsService.getDcorDvUsersByConstituency(jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in unTagConstituencyOfUser", e);
		}
		return Action.SUCCESS;
	}
  
  public String getVerifierCollectedDetails()
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
 			
 			
 			dcDvCollectedDataVOList = surveyDetailsService.getVerifierCollectedDetails(jObj.getLong("surveyUserId"),jObj.getLong("boothId"));
 		} 
 		catch (Exception e)
 		{
 			LOG.error("Exception raised in unTagConstituencyOfUser", e);
 		}
 		return Action.SUCCESS;
 	}
  /*public String saveBoothsPercentageForCasteSurvey(){
	  try{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null){
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			String percentage = jObj.getString("percentage");
			
			status = surveyDetailsService.savePercentageOfBoothForCasteSurvey(boothId,percentage);
		} 
		catch (Exception e)	{
			LOG.error("Exception raised in saveBoothsPercentageForCasteSurvey", e);
		}
	  
	  return Action.SUCCESS;
  }*/
  
  public String saveBoothsPercentageForCasteSurvey(){
	  try{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null){
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			List<Long> boothIds=new ArrayList();
			JSONArray jarray = jObj.getJSONArray("boothIds");
 			for (Integer i = 0; i < jarray.length(); i++){
 				boothIds.add(Long.valueOf(jarray.get(i).toString()));
 			}
			
			String percentage = jObj.getString("percentage");
			
			status = surveyDetailsService.savePercentageOfBoothForCasteSurvey(boothIds,percentage);
		} 
		catch (Exception e)	{
			LOG.error("Exception raised in saveBoothsPercentageForCasteSurvey", e);
		}
	  
	  return Action.SUCCESS;
  }
  
  public String getCastewiseSurveyVotersList(){
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
			String searchDate = jObj.getString("searchDate");
			Long casteStateId = jObj.getLong("casteId");
			Long userType = jObj.getLong("userType");
			voterVerificationList = 	surveyDataDetailsService.getSurveyVotersList(constituencyId,boothId,surveyUserId,searchDate,userType,casteStateId);	
			
		} catch (Exception e) {
			LOG.error(" exception occured in getCastewiseSurveyVotersList() ,ConstituencyDetailsAction class",e);
		}
		return Action.SUCCESS;
  }
  
  
  public String getLeaderUsers()
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
			countList = surveyDataDetailsService.getUsersForLeader(jObj.getLong("leaderId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLeaderUsers", e);
		}
		return Action.SUCCESS;
	}
  public String getConstituencySummaryReport()
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
			dcDvCollectedDataVOList = surveyDetailsService.getConstituencySummaryReport();
					
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLeaderUsers", e);
		}
		return Action.SUCCESS;
	}
	public String getThirdPartyVerificationDetails()
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
			responceList = surveyDetailsService.getThirdPartyVerificationDetails(jObj.getLong("boothId"),null);
					
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLeaderUsers", e);
		}
		return Action.SUCCESS;
	}
  

  	public String getConstituencyLeadersAndUsers()
	{
		try
		{
			jObj = new JSONObject(getTask());
			String dateStr = jObj.getString("dateStr");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = originalFormat.parse(dateStr);
			surveyUserDetails = surveyDetailsService.getConstituencyWiseLeadersAndUsersDetails(jObj.getLong("constituencyId"),date);
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyLeadersAndUsers", e);
		}
		return Action.SUCCESS;
		
	}
  	
  	public String getConstituencyFieldSummary()
  	{
  		try{
  			reportList = surveyDetailsService.getConstituencyWiseFieldSummary();	
  		}
  		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyFieldSummary", e);
		}
		return Action.SUCCESS;
  	}
  	
  	public String getVerifiersOfBooths(){
  		try{
  			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			
			List<Long> boothIds = new ArrayList<Long>();
			boothIds.add(boothId);
			
			verificationCompVOList = surveyDetailsService.getVerifiersOfBooths(boothIds);
  				
  		}
  		catch (Exception e){
			LOG.error("Exception raised in getVerifiersOfBooths", e);
		}
		return Action.SUCCESS;
  	}
  	
  	 public String checkForVerifierDataWithVerifier(){
  		try	{
  			HttpSession session = request.getSession();
  			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
  			if(user == null){
  				return Action.INPUT;
  			}
  			jObj = new JSONObject(getTask());
  			List<Long> boothIds = new ArrayList<Long>();
 			JSONArray jarray = jObj.getJSONArray("boothIds");
 			for (Integer i = 0; i < jarray.length(); i++){
 				boothIds.add(Long.valueOf(jarray.get(i).toString()));
 			}
 			
 			Long veriferId = jObj.getLong("verifierId");
 			
  			verificationCompVOList = surveyDetailsService.checkForVerifierDataWithBoothAndVerifierId(boothIds,veriferId);
  			
  		} 
  		catch (Exception e){
  			LOG.error("Exception raised in unTagConstituencyOfUser", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getUsersForConstituencies()
  	{
  		try
		{
			jObj = new JSONObject(getTask());
			List<Long> constiIds = new ArrayList<Long>();
			JSONArray ids = jObj.getJSONArray("constituencyIds");
			Long userTypeId = jObj.getLong("userTypeId");
			for(int i=0;i<ids.length();i++)
				constiIds.add(Long.valueOf(ids.get(i).toString()));
			returnList = surveyDetailsService.getUsersList(userTypeId,constiIds);		
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyLeadersAndUsers", e);
		}
		return Action.SUCCESS;	
  	}
  	
  	public String getFinalReportWithTP(){
		LOG.debug("Entered Into getFinalReportWithTP");
		try	{
			jObj = new JSONObject(getTask());
			thirdPartyResultList = surveyCompletedDetailsService.finalReportWithThirdParty(jObj.getLong("constituencyId"));
			tpFinalVO = surveyCompletedDetailsService.getTPCompleteBoothsDetails(jObj.getLong("constituencyId"),thirdPartyResultList);
			
		}catch(Exception e){
			LOG.error("Exception Raised in getFinalReportWithTP"+e);
		}
		return Action.SUCCESS;
  	}
  	
  	public String getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies()
  	{
  		try
		{
  		 reportList = surveyCompletedDetailsService.getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();
		
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies", e);
		}
		return Action.SUCCESS;	
  	} 
  	
  	public String getFinalReportWithTPConstituencyWise(){
		LOG.debug("Entered Into getFinalReportWithTP");
		tpFinalVOList = new ArrayList<SurveyThirdPartyReportVO>();
		try	{
			jObj = new JSONObject(getTask());
			List<Long> constituencies = new ArrayList<Long>();
			Map<Long,String> constiMap = new HashMap<Long, String>();
			Map<Long,String> constiTypeMap = new HashMap<Long, String>();
			List<SurveyThirdPartyReportVO> constList = surveyCompletedDetailsService.thirdPartyReadyForReviewConstBooths();
			if(constList!=null && constList.size()>0){
				for(SurveyThirdPartyReportVO sv:constList){
					if(!constituencies.contains(sv.getConstituencyId())){
						constituencies.add(sv.getConstituencyId());
						constiMap.put(sv.getConstituencyId(), sv.getConstituency());
						constiTypeMap.put(sv.getConstituencyId(), sv.getConstituencyType());
					}
				}
			}
			
			if(constituencies!=null && constituencies.size()>0){
				for(Long sv:constituencies){
					thirdPartyResultList = surveyCompletedDetailsService.finalReportWithThirdParty(sv);
					SurveyThirdPartyReportVO fnlVO = surveyCompletedDetailsService.getTPCompleteBoothsDetails(sv,thirdPartyResultList);
					
					tpFinalVO = new SurveyThirdPartyReportVO();
					
					tpFinalVO.setConstituencyId(sv);
					tpFinalVO.setConstituencyDetails(fnlVO);
					tpFinalVO.setConstituency(constiMap.get(sv));
					tpFinalVO.setConstituencyType(constiTypeMap.get(sv));
					tpFinalVOList.add(tpFinalVO);
				}
			}
			
			
			
		}catch(Exception e){
			LOG.error("Exception Raised in getFinalReportWithTP"+e);
		}
		return Action.SUCCESS;
  	}
  	
  	public String getConstituencyWiseReportForDashBoard(){
  		
  		LOG.debug("Entered Into getFinalReportWithTP");
		tpFinalVOList = new ArrayList<SurveyThirdPartyReportVO>();
		try	{
			jObj = new JSONObject(getTask());
			
			String constituencies  = jObj.getString("constituencyIds");
			
			String  constituencyIds[] = constituencies.split(",");
			
			List<Long> constiIds  = new ArrayList<Long>(0);
			
			if(constituencyIds != null && constituencyIds.length >0)
			{
				for (int i = 0; i < constituencyIds.length; i++) {
					constiIds.add(Long.valueOf(String.valueOf(constituencyIds[i]).trim().replace("[", "").replace("]", "")));
				}
			}
			reportList = surveyCompletedDetailsService.getConstituencyWiseReportForDashBoard(constiIds);
		}catch(Exception e){
			LOG.error("Exception Raised in getConstituencyWiseReportForDashBoard"+e);
		}
		return Action.SUCCESS;
  	}

  	public String getConstituencysReport()
  	{
  		LOG.debug("Entered Into getConstituencysReport() in SurveyDataDetailsAction class. ");
  		
		try	{
			jObj = new JSONObject(getTask());
			
			reportList =  surveyCompletedDetailsService.getConstituencysReportByStatus(jObj.getString("searchType"));
			
		}catch(Exception e){
			LOG.error("Exception Raised in getConstituencysReport() in SurveyDataDetailsAction class."+e);
		}
		return Action.SUCCESS;
  		
  	}
  	
	
}
