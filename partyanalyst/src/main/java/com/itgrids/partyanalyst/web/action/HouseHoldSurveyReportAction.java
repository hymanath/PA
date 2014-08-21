package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHLeaderDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionSummaryReportVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.dto.HouseHoldsReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsSummaryReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IHouseHoldSurveyReportService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
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
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList, districtsList;
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
	private HHSurveyVO votersFamilyInfo;
	
	private List<GenericVO> occupationList;
	private List<GenericVO> educationList;
	private List<GenericVO> socialPositionList;
	private Long houseHoldsId;
	private IRegionServiceData regionServiceDataImp;
	private EntitlementsHelper entitlementsHelper;
	private List<String> voterIdList;
	private ResultStatus resultStatus;
	private VoterDetailsVO resultVO;
	
	private HouseHoldsVO boothsAndBooks;
	private HouseHoldsSummaryReportVO hhSummaryDtls;
	
	private List<GenericVO> hhConstituenies;
	private HHQuestionSummaryReportVO qstnsSummary;
	private List<HHQuestionSummaryReportVO> questions;
	private List<HouseHoldVotersVO> report;
	private List<HouseHoldsReportVO> houseHoldBooks;
	private List<HouseHoldsVO> houseHoldsVOList;
	
	
	public List<HouseHoldsReportVO> getHouseHoldBooks() {
		return houseHoldBooks;
	}

	public void setHouseHoldBooks(List<HouseHoldsReportVO> houseHoldBooks) {
		this.houseHoldBooks = houseHoldBooks;
	}

	public List<HouseHoldVotersVO> getReport() {
		return report;
	}

	public void setReport(List<HouseHoldVotersVO> report) {
		this.report = report;
	}

	public HHQuestionSummaryReportVO getQstnsSummary() {
		return qstnsSummary;
	}

	public void setQstnsSummary(HHQuestionSummaryReportVO qstnsSummary) {
		this.qstnsSummary = qstnsSummary;
	}

	public List<HHQuestionSummaryReportVO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<HHQuestionSummaryReportVO> questions) {
		this.questions = questions;
	}

	public List<GenericVO> getHhConstituenies() {
		return hhConstituenies;
	}

	public void setHhConstituenies(List<GenericVO> hhConstituenies) {
		this.hhConstituenies = hhConstituenies;
	}

	public HouseHoldsSummaryReportVO getHhSummaryDtls() {
		return hhSummaryDtls;
	}

	public void setHhSummaryDtls(HouseHoldsSummaryReportVO hhSummaryDtls) {
		this.hhSummaryDtls = hhSummaryDtls;
	}

	public HouseHoldsVO getBoothsAndBooks() {
		return boothsAndBooks;
	}

	public void setBoothsAndBooks(HouseHoldsVO boothsAndBooks) {
		this.boothsAndBooks = boothsAndBooks;
	}

	public VoterDetailsVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(VoterDetailsVO resultVO) {
		this.resultVO = resultVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<String> getVoterIdList() {
		return voterIdList;
	}

	public void setVoterIdList(List<String> voterIdList) {
		this.voterIdList = voterIdList;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public Long getHouseHoldsId() {
		return houseHoldsId;
	}

	public void setHouseHoldsId(Long houseHoldsId) {
		this.houseHoldsId = houseHoldsId;
	}

	public List<GenericVO> getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(List<GenericVO> occupationList) {
		this.occupationList = occupationList;
	}

	public List<GenericVO> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<GenericVO> educationList) {
		this.educationList = educationList;
	}

	public List<GenericVO> getSocialPositionList() {
		return socialPositionList;
	}

	public void setSocialPositionList(List<GenericVO> socialPositionList) {
		this.socialPositionList = socialPositionList;
	}

	

	public HHSurveyVO getVotersFamilyInfo() {
		return votersFamilyInfo;
	}

	public void setVotersFamilyInfo(HHSurveyVO votersFamilyInfo) {
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

	public List<HouseHoldsVO> getHouseHoldsVOList() {
		return houseHoldsVOList;
	}

	public void setHouseHoldsVOList(List<HouseHoldsVO> houseHoldsVOList) {
		this.houseHoldsVOList = houseHoldsVOList;
	}

	public String execute(){
		session = request.getSession();
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.HOUSEHOLDS_SURVEY_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.HOUSEHOLDS_SURVEY_ENTITLEMENT))
			return ERROR;*/
		
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		userId = user.getRegistrationID();
		
		optiontypes = houseHoldSurveyReportService.getAllOptionTypes();
		
		Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(1l,electionYear,electionTypeId);
		
		
		districtsList = regionServiceDataImp.getDistrictsByStateID(1L);
		districtsList.add(0, new SelectOptionVO(0L,"Select District"));
		
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
		Long voterId=jObj.getLong("voterId");
		
		questionsList=houseHoldSurveyReportService.getHHSurveyQuestionOptions(1l,bthId,hsNo,voterId);
		
		return Action.SUCCESS;
	}
	
	public String getBoothsOrBooksInConstituencyOfLeaderToAssign(){
		
		try {
			jObj = new JSONObject(getTask());
		
		
		String task = jObj.getString("task");
		Long constituencyId = null;
		Long publicationId = null;
		
		if(!task.equalsIgnoreCase("getBooksOfLeader")){
			constituencyId = jObj.getLong("constituency");
			publicationId = jObj.getLong("publication");
		}
		
		if(task.equalsIgnoreCase("assignBooths")){
			Long leaderId = jObj.getLong("leader");
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,publicationId,leaderId,"forBooths",null,null);
			
		}else if(task.equalsIgnoreCase("assignBooks")){
			Long leaderId = jObj.getLong("leader");
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,publicationId,leaderId,"forBooks",null,null);
			
		}else if(task.equalsIgnoreCase("checkBookAvail")){
			String bookNo = jObj.getString("bookNo").trim();
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,null,null,"checkBook",bookNo,null);
			
		}else if(task.equalsIgnoreCase("addGivenBookNo")){
			String bookNo = jObj.getString("bookNo").trim();
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,null,null,"addGivenBookNo",bookNo,null);
			
		}else if(task.equalsIgnoreCase("leadersOfConstituency")){
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,null,null,"leadersOfConstituency",null,null);
			
		}else if(task.equalsIgnoreCase("assignSelectedBooks")){
			Long leaderId = jObj.getLong("leader");
			List<Long> books = new ArrayList<Long>();
			
			JSONArray bookNos= jObj.getJSONArray("books");
			if(bookNos!=null && bookNos.length()>0){
				for(int i=0; i<bookNos.length();i++){
					books.add(Long.parseLong(bookNos.getString(i)));
				}
			}
			
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,publicationId,leaderId,"assignSelectedBooks",null,books);
			
		}else if(task.equalsIgnoreCase("assignSelectedBooths")){
			Long leaderId = jObj.getLong("leader");
			List<Long> booths = new ArrayList<Long>();
			
			JSONArray boothNos= jObj.getJSONArray("booths");
			if(boothNos!=null && boothNos.length()>0){
				for(int i=0; i<boothNos.length();i++){
					booths.add(Long.parseLong(boothNos.getString(i)));
				}
			}
			
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(constituencyId,publicationId,leaderId,"assignSelectedBooths",null,booths);
			
		}if(task.equalsIgnoreCase("getBooksOfLeader")){
			Long leaderId = jObj.getLong("leader");
			boothsAndBooks=houseHoldSurveyReportService.getBoothsOrBooksInConstituencyOfLeaderToAssign(null,null,leaderId,"getBooksOfLeader",null,null);
			
		}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		
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
			
			if(jObj.getString("commentInd").equalsIgnoreCase("true"))
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
		
		status=houseHoldSurveyReportService.saveQuestOptnsOfHH(boothId,houseNo,questionOptionsList,houseHoldsId);
		
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

			Long voterId=jObj.getLong("voterId");
			
			votersFamilyInfo = votersAnalysisService.getFamilyInformationForHHSurvey(null,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"),1l,null,voterId);
			
		}catch(Exception e){
			//LOG.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- ",e);
			LOG.error("Exception Raised",e);
		}
		
		return SUCCESS;
	}
	
	public String saveHouseHoldsVotersDetails()
	{
		HouseHoldVotersVO houseHoldVotersDtls = new HouseHoldVotersVO();
		
		try {
			
			List<HouseHoldVotersVO> houseHoldVoters = new ArrayList<HouseHoldVotersVO>();
			
			jObj = new JSONObject(getTask());
			
			net.sf.json.JSONArray votersDetails = (net.sf.json.JSONArray) JSONSerializer
					.toJSON(jObj.getString("voters"));
			
			for(int i=0;i<votersDetails.length();i++)
			{
				net.sf.json.JSONObject  voter =  (net.sf.json.JSONObject)votersDetails.get(i);
				
				HouseHoldVotersVO  voterDetailsVO = new HouseHoldVotersVO();
				
				if(!voter.getString("isNew").equalsIgnoreCase("true"))
				{
					
					if(voter.has("voterId"))
					{
						voterDetailsVO.setVoterId(Long.parseLong(voter.getString("voterId")));
						voterDetailsVO.setEducationId(Long.parseLong(voter.getString("voterEdctn")));
						voterDetailsVO.setOccupationId(Long.parseLong(voter.getString("voterOccptn")));
						voterDetailsVO.setVoterFamilyRelationId(Long.parseLong(voter.getString("voterFamilyRelationId")));
						voterDetailsVO.setSocialPstnId(Long.parseLong(voter.getString("voterSocialPstn")));
						
					} 
					
					
				}else
				{
					voterDetailsVO.setNewPerson(true);

					if(voter.has("familyMemberId"))
					{
						
						voterDetailsVO.setName(voter.getString("name"));
						voterDetailsVO.setAge(Long.parseLong(voter.getString("age")));
						voterDetailsVO.setGender(voter.getString("gender"));
						voterDetailsVO.setRelativeName(voter.getString("relativeName"));
						voterDetailsVO.setRelationShipType(voter.getString("relationShipType"));
						voterDetailsVO.setEducationId(Long.parseLong(voter.getString("education")));
						voterDetailsVO.setOccupationId(Long.parseLong(voter.getString("occupation")));
						voterDetailsVO.setSocialPstnId(Long.parseLong(voter.getString("socialPosistion")));
						voterDetailsVO.setMobileNo(voter.getString("mobileNo"));
						voterDetailsVO.setVoterFamilyRelationId(Long.parseLong(voter.getString("familyRelationId")));
						voterDetailsVO.setHouseHoldFamilyMemberId(voter.getLong("familyMemberId"));
						
						
					}
					else
					{
					
					voterDetailsVO.setAge(Long.parseLong(voter.getString("age")));
					voterDetailsVO.setName(voter.getString("name"));
					voterDetailsVO.setRelativeName(voter.getString("relativeName"));
					voterDetailsVO.setRelationShipType(voter.getString("relationShipType"));
					voterDetailsVO.setGender(voter.getString("gender"));
					voterDetailsVO.setEducationId(Long.parseLong(voter.getString("education")));
					voterDetailsVO.setOccupationId(Long.parseLong(voter.getString("occupation")));
					voterDetailsVO.setSocialPstnId(Long.parseLong(voter.getString("socialPosistion")));
					voterDetailsVO.setVoterFamilyRelationId(Long.parseLong(voter.getString("familyRelationId")));
					voterDetailsVO.setMobileNo(voter.getString("mobileNo"));
					}
					
				}
				voterDetailsVO.setLeaderId(voter.getLong("leaderId"));
				voterDetailsVO.setLeaderBookNo(voter.getLong("leaderBookNo"));
				voterDetailsVO.setOwnerMobileNo(voter.getString("ownerMobileNo"));
				houseHoldVoters.add(voterDetailsVO);
				
				
			}
				
			houseHoldVotersDtls.setHouseHoldsVoters(houseHoldVoters);
			
			houseHoldVotersDtls.setBoothId(Long.parseLong(jObj.getString("boothId")));
			houseHoldVotersDtls.setHouseNo(jObj.getString("houseNo"));
			houseHoldVotersDtls.setHouseHoldsId(Long.parseLong(jObj.getString("houseHoldsId")));
			houseHoldsId = houseHoldSurveyReportService.saveHouseHoldsVotersDetails(houseHoldVotersDtls);
			
			if(houseHoldsId.equals(0l)){
				return Action.ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
		
	}
	
	public String saveMainQuestion()
	{
		try {		
			jObj = new JSONObject(getTask());						
			String mainQuestion = jObj.getString("mainQtnType");			
			 houseHoldSurveyReportService.saveMainQuestionDetails(mainQuestion);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	} 
	
	public String saveSubQuestion()
	{
		try {		
			jObj = new JSONObject(getTask());
			
			Long mainQtnTypeId = jObj.getLong("mainQtnTypeId");          
			String subQuestion = jObj.getString("subQtnType");
		
			houseHoldSurveyReportService.saveSubQuestionDetails(mainQtnTypeId,subQuestion);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Action.SUCCESS;
		
	} 
	public String getVoterIds()
	{
		try {		
			jObj = new JSONObject(getTask());			

			voterIdList = houseHoldSurveyReportService.getAllVoterIds();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Action.SUCCESS;
		
	} 
	
	public String saveLeaderDetails()
	{
		try {		
			jObj = new JSONObject(getTask());
			   
			String leaderName = jObj.getString("leaderName");
			String mobileNo = jObj.getString("mobileNo"); 
			String voterId = jObj.getString("voterId");			
			String uniqueCode = jObj.getString("uniqueCode");
			Long constituencyId = jObj.getLong("constituencyId");
			Long boothId = jObj.getLong("boothId");
	        String isActive = jObj.getString("isActive");		
			
            HHLeaderDetailsVO leaderDtls = new HHLeaderDetailsVO();
			
            leaderDtls.setName(leaderName);
            leaderDtls.setMobileNo(mobileNo);
            leaderDtls.setVoterId(voterId);
            leaderDtls.setUniqueCode(uniqueCode);
            leaderDtls.setConstId(constituencyId);
            leaderDtls.setBoothId(boothId);
         	leaderDtls.setIsActive(isActive);
			
         	resultStatus = houseHoldSurveyReportService.saveLeaderDetails(leaderDtls);				
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Action.SUCCESS;		
	} 
	
	public String getVoterDetailsById()
	{
		try {		
			jObj = new JSONObject(getTask());
			resultVO = houseHoldSurveyReportService.getVoterDetailsByVoterId(jObj.getString("voterIdCardNo"));
		}catch (Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String getReportsOfHouseHolds(){
		try {		
			jObj = new JSONObject(getTask());
			
			HouseHoldsSummaryReportVO input = new HouseHoldsSummaryReportVO();
			String task = jObj.getString("task");
			
			input.setTask(task);
			if(task.equalsIgnoreCase("constituencySummary")){
				input.setConstituencyId(Long.valueOf(jObj.getString("constituencyId")));
			}else if(task.equalsIgnoreCase("leaderOfPanchayat")){
				input.setPanchayatId(Long.valueOf(jObj.getString("panchayatId")));
			}else if(task.equalsIgnoreCase("familyHeadsUnderLeader")){
				input.setLeaderId(Long.valueOf(jObj.getString("leaderId")));
			}else if(task.equalsIgnoreCase("familyHeadsUnderPanchayat")){
				input.setPanchayatId(Long.valueOf(jObj.getString("panchayatId")));
			}else if(task.equalsIgnoreCase("familyHeadsUnderBook")){
				input.setBookId(Long.valueOf(jObj.getString("bookId")));
			}else if(task.equalsIgnoreCase("familyHeadsUnderOptions")){
				input.setOptionId(Long.valueOf(jObj.getString("optionId")));
				input.setPanchayatId(Long.valueOf(jObj.getString("panchayatId")));
			}
			
			
			hhSummaryDtls = houseHoldSurveyReportService.getReportsOfHouseHolds(input);
		}catch (Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getHouseHoldsSummary(){
		
		hhConstituenies = houseHoldSurveyReportService.getHouseHoldConstituencies();
		
    	return Action.SUCCESS;
    }
	
	public String getQuestionsOfSurvey(){
		try {		
			jObj = new JSONObject(getTask());
			Long surveyId = jObj.getLong("surveyId");
			questions = houseHoldSurveyReportService.getQuestionsOfSurvey(surveyId);
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
	
	public String getQuestionSummary(){
		try {		
			jObj = new JSONObject(getTask());
			Long questionId = jObj.getLong("questionId");
			Long constituencyId = jObj.getLong("constituencyId");
			
			qstnsSummary = houseHoldSurveyReportService.getOptionsCountForQuestion(questionId,constituencyId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
	
	public String familyMembersUnderFamilyHead(){
		try {		
			jObj = new JSONObject(getTask());
			report = houseHoldSurveyReportService.getFamilyMembersUnderFamilyHead(jObj.getLong("householdId"));
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
	
	public String getNonVoterAgeRangeDetailsConstituencyWise(){
		try {		
			jObj = new JSONObject(getTask());
			report = houseHoldSurveyReportService.getNonVoterAgeRangeDetailsInConstituency(jObj.getLong("constituencyId"));
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
	
	public String getBooksDetailsOfHouseHolds(){
		try {		
			jObj = new JSONObject(getTask());
			houseHoldBooks = houseHoldSurveyReportService.getBooksDetailsOfHouseHolds(jObj.getLong("constituencyId"));
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
	
	public String getAgeWiseNonVotersDetails(){
		try {		
			jObj = new JSONObject(getTask());
			Long fromAge = Long.valueOf(jObj.getString("fromAge"));
			Long toAge = Long.valueOf(jObj.getString("toAge"));
			report = houseHoldSurveyReportService.getAgeRangeWiseNonVotersDetails(jObj.getLong("panchayatId"),jObj.getInt("type"),fromAge,toAge);
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return Action.SUCCESS;
	}
public String getBooksWiseSummaryReport()
{
  try{
	   Long constituencyId=Long.parseLong(request.getParameter("constituencyId"));	
	   houseHoldsVOList=(List<HouseHoldsVO>)houseHoldSurveyReportService.getBooksWiseSummaryReport(constituencyId);
     }
  catch(Exception e)
  {
	  e.printStackTrace();  
   }
	
   return Action.SUCCESS;	
}
}


