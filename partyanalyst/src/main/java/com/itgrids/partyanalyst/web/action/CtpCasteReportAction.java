package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.ICtpCasteReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CtpCasteReportAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private static final Logger LOG = Logger.getLogger(CtpCasteReportAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	private ICtpCasteReportService ctpCasteReportService;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	@Autowired
	private ICrossVotingEstimationService crossVotingEstimationService;
	@Autowired
	private IVotersAnalysisService votersAnalysisService;
	private VoterHouseInfoVO voterHouseInfoVO;
	@Autowired
    private EntitlementsHelper entitlementsHelper;
	private SurveyCompletionDetailsVO surveyCompletionDetailsVO;
	
	public SurveyCompletionDetailsVO getSurveyCompletionDetailsVO() {
		return surveyCompletionDetailsVO;
	}
	public void setSurveyCompletionDetailsVO(
			SurveyCompletionDetailsVO surveyCompletionDetailsVO) {
		this.surveyCompletionDetailsVO = surveyCompletionDetailsVO;
	}
	public VoterHouseInfoVO getVoterHouseInfoVO() {
		return voterHouseInfoVO;
	}
	public void setVoterHouseInfoVO(VoterHouseInfoVO voterHouseInfoVO) {
		this.voterHouseInfoVO = voterHouseInfoVO;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ICtpCasteReportService getCtpCasteReportService() {
		return ctpCasteReportService;
	}
	public void setCtpCasteReportService(
			ICtpCasteReportService ctpCasteReportService) {
		this.ctpCasteReportService = ctpCasteReportService;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public String execute()
	{
		
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return INPUT;
			if(session.getAttribute(IConstants.USER) == null && 
			!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CTP_CASTE_REPORT_ENTITLEMENT))
		return INPUT;
	if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CTP_CASTE_REPORT_ENTITLEMENT))
		return ERROR;
			
		constituencyList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		return Action.SUCCESS;
	}
	
	
	public String getVoterInfoForCTP()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user =  (RegistrationVO) session.getAttribute(IConstants.USER);
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
				if(user.getParentUserId()!=null)
					userId=user.getMainAccountId();
				else
					userId = user.getRegistrationID();
			else 
			  return "error";
			org.json.JSONArray arr = jObj.getJSONArray("searchArr");
			for(int i=0;i<arr.length();i++)
			{
				JSONObject obj = arr.getJSONObject(i);
				VoterHouseInfoVO searchVo = new VoterHouseInfoVO();
				searchVo.setUserId(userId);
				searchVo.setVoterIdCardNo(obj.getString("voterCardId"));
				searchVo.setName(obj.getString("voterName"));
				searchVo.setSetValue(obj.getString("voterNameType"));
				searchVo.setGaurdian(obj.getString("guardianName"));
				searchVo.setGender(obj.getString("gender"));
				searchVo.setAge(obj.getLong("startAge"));
				searchVo.setToAge(obj.getLong("endAge"));
				searchVo.setFromSno(obj.getLong("fromSno"));
				searchVo.setToSno(obj.getLong("toSno"));
				searchVo.setHouseNo(obj.getString("houseNo"));
				searchVo.setPublicationId(IConstants.VOTER_DATA_PUBLICATION_ID);
				String regionLevel = obj.getString("locationLvl");
				Long id = obj.getLong("id");
				
				voterHouseInfoVO = 	ctpCasteReportService.getVoterDetailsForSearch(searchVo ,regionLevel, id);
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getVoterInfoForCTP() method", e);
		}
		return Action.SUCCESS;
	}
	public String getctpVoterCounts()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user =  (RegistrationVO) session.getAttribute(IConstants.USER);
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
				userId = user.getRegistrationID();
			else 
			  return "error";
			voterHouseInfoVO = 	ctpCasteReportService.getCTPVoterCount(userId);
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getctpVoterCounts() method", e);
		}
		return Action.SUCCESS;
		}
	public String getVotersCountInRegion()
	{
		try{
		jObj = new JSONObject(getTask());	
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		Long userId = null;
		if(user != null && user.getRegistrationID() != null)
			userId = user.getRegistrationID();
		else
			return Action.ERROR;
		voterHouseInfoVO = ctpCasteReportService.getVotersCountInRegion(jObj.getLong("constituencyId"),jObj.getString("locationType"),userId);
		
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getVotersCountInRegion() method", e);
		}
		return Action.SUCCESS;
	}
	public String getCatseVotersCountInRegion()
	{
		try{
		jObj = new JSONObject(getTask());	
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		Long userId = null;
		if(user != null && user.getRegistrationID() != null)
			userId = user.getRegistrationID();
		else
			return Action.ERROR;
		voterHouseInfoVO = ctpCasteReportService.getCatseVotersCountInRegion(jObj.getLong("constituencyId"),jObj.getString("locationType"),userId);
		
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getVotersCountInRegion() method", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyStatusDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			
			surveyCompletionDetailsVO = ctpCasteReportService.getSurveyStatusDetailsInfo();
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getSurveyStatusDetails() method", e);
		}
		return Action.SUCCESS;
	}
}
