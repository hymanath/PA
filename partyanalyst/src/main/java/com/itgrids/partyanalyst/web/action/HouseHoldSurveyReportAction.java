package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.HHLeaderDetailsVO;
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
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList, districtsList, boothsList;
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
	private List<String> voterIdList;
	private ResultStatus resultStatus;
	
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

	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
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
			
			votersFamilyInfo = votersAnalysisService.getFamilyInformationForHHSurvey(null,jObj.getLong("id"),jObj.getLong("publicationDateId"),jObj.getString("hno"),userId,null,voterId);
			
		}catch(Exception e){
			//log.error("Exception Occured in getVotersFamilyDetails() Method,Exception is- ",e);
			System.out.println("Exception Raised"+e);
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
				voterDetailsVO.setOwnerMobileNo(voter.getString("ownerMobileNo"));
				houseHoldVoters.add(voterDetailsVO);
				
				
			}
				
			houseHoldVotersDtls.setHouseHoldsVoters(houseHoldVoters);
			
			houseHoldVotersDtls.setBoothId(Long.parseLong(jObj.getString("boothId")));
			houseHoldVotersDtls.setHouseNo(jObj.getString("houseNo"));
			houseHoldVotersDtls.setHouseHoldsId(Long.parseLong(jObj.getString("houseHoldsId")));
			houseHoldsId = houseHoldSurveyReportService.saveHouseHoldsVotersDetails(houseHoldVotersDtls);
			
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
		
	public String getBoothIdsForAConstituency()
	{
		try {		
			jObj = new JSONObject(getTask());			

			boothsList = houseHoldSurveyReportService.getBoothIdsByConstituencyId(jObj.getLong("constituencyId"));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Action.SUCCESS;
		
	} 
	
}
