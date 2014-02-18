package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IHouseHoldSurveyReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class HouseHoldSurveyReportAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	JSONObject jObj = null;
	private String task;
	
	private Long userId;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private IHouseHoldSurveyReportService houseHoldSurveyReportService;
	private List<HHSurveyVO> questionsList;
	
	private List<GenericVO> resultList;
	private List<GenericVO> optiontypes;
	private List<GenericVO> mainSurveyTypes;
	
	private List<HHSurveyVO> questionOptionsList;
	private String houseNo;
	private Long boothId;
	private String status;
	private List<VoterHouseInfoVO> votersFamilyInfo;
	
	
	
	public List<VoterHouseInfoVO> getVotersFamilyInfo() {
		return votersFamilyInfo;
	}

	public void setVotersFamilyInfo(List<VoterHouseInfoVO> votersFamilyInfo) {
		this.votersFamilyInfo = votersFamilyInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public List<HHSurveyVO> getQuestionOptionsList() {
		return questionOptionsList;
	}

	public void setQuestionOptionsList(List<HHSurveyVO> questionOptionsList) {
		this.questionOptionsList = questionOptionsList;
	}

	public List<GenericVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<GenericVO> resultList) {
		this.resultList = resultList;
	}

	public List<GenericVO> getOptiontypes() {
		return optiontypes;
	}

	public void setOptiontypes(List<GenericVO> optiontypes) {
		this.optiontypes = optiontypes;
	}

	public List<GenericVO> getMainSurveyTypes() {
		return mainSurveyTypes;
	}

	public void setMainSurveyTypes(List<GenericVO> mainSurveyTypes) {
		this.mainSurveyTypes = mainSurveyTypes;
	}

	public List<HHSurveyVO> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<HHSurveyVO> questionsList) {
		this.questionsList = questionsList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	public IHouseHoldSurveyReportService getHouseHoldSurveyReportService() {
		return houseHoldSurveyReportService;
	}

	public void setHouseHoldSurveyReportService(
			IHouseHoldSurveyReportService houseHoldSurveyReportService) {
		this.houseHoldSurveyReportService = houseHoldSurveyReportService;
	}

	public String execute(){
		session = request.getSession();
		
		optiontypes = houseHoldSurveyReportService.getAllOptionTypes();
		
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error";
		
		userId = user.getRegistrationID();
		
		
		
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userId,electionYear,electionTypeId);
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		
		
		return Action.SUCCESS;
	}
	
	public String getHouseHoldQuestions(){
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		
		Long surveyId=jObj.getLong("surveyId");
		
		Long bthId=jObj.getLong("boothId");
		String hsNo=jObj.getString("houseNo");
		
		questionsList=houseHoldSurveyReportService.getHHSurveyQuestionOptions(1l,bthId,hsNo);
		
		return Action.SUCCESS;
	}
	
	public String houseHoldCreationAction()
	{
		optiontypes = houseHoldSurveyReportService.getAllOptionTypes();
		mainSurveyTypes = houseHoldSurveyReportService.getSurveyTypes();
		
		return Action.SUCCESS;
	}
	
	public String saveQuestionDetails()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			Long surveyTypeId = jObj.getLong("surveyTypeId");
			Long surveySubTypeId = jObj.getLong("surveySubTypeId");
			
			String question = jObj.getString("question");
			String options = jObj.getString("options");
			Long optnTypeId = jObj.getLong("optnTypeId");
			
			
			
			HHQuestionDetailsVO questionDtls = new HHQuestionDetailsVO();
			
			questionDtls.setSurveyTypeId(surveyTypeId);
			questionDtls.setSurveySubTypeId(surveySubTypeId);
			questionDtls.setQuestion(question);
			questionDtls.setOptions(options);
			questionDtls.setOptnTypeId(optnTypeId);
			
			if(jObj.getString("remarks").equalsIgnoreCase("true"))
				questionDtls.setRemarks(true);
			else
				questionDtls.setRemarks(false);
			
			houseHoldSurveyReportService.saveHouseHoldQuestionDetails(questionDtls);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	} 
	
	public String getSubSurveyTypes()
	{
		
		resultList = houseHoldSurveyReportService.getSubSurveyTypesByMainTypeId(Long.parseLong(request.getParameter("mainTypeId")));
		
		return Action.SUCCESS;
	}
	
	public String saveHouseHoldQuesOptns(){
		System.out.println("hello sasi");	
		
		status=houseHoldSurveyReportService.saveQuestOptnsOfHH(boothId,houseNo,questionOptionsList);
		
		return Action.SUCCESS;
	}
	
	public String getVotersFamilyDetailsForHHSurvey(){
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
				votersFamilyInfo = votersAnalysisService.getFamilyInformationForHHSurvey(null,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"),userId,null);
		}catch(Exception e){
			//log.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- ",e);
			System.out.println("Exception Raised"+e);
		}
		
		return SUCCESS;
	}
	
}
