package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyAgeWiseDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyAnalysisVO;
import com.itgrids.partyanalyst.dto.SurveyInfoVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.itgrids.partyanalyst.dto.SurveyVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.dto.SurveyorVO;
import com.itgrids.partyanalyst.dto.SurveyAnalysisDTO;
public class SurveyFormAction extends ActionSupport implements ServletRequestAware,ModelDriven<List<QuestionAnswerVO>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private static final Logger LOG = Logger.getLogger(SurveyFormAction.class);
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private ISurveyAnalysisService surveyAnalysisService;
	private CadreManagementService cadreManagementService; 
	private Long surveyId;
	private List<SurveyVO> surveyVO;
	private String name;
	private String task;
	JSONObject jObj;
	private List<SelectOptionVO> stateList,districtList,eduStatus,occupationsList;
	private IStaticDataService staticDataService;
	private Long stateId;
	private Long districtId;
	private Long countryId;
	private List<SelectOptionVO> casteList;
	private List<VoterVO> voterVO;
	private Long constituencyId,mandalId,panchayatId,hamletId,boothId,age,caste,education,occupation = 0l;
	private String voterId,mobileNo,gender,landmark = null;
	private SurveyInfoVO surveyInfoVO;
	private Long locationId;
	private String locationValue,phoneNo,emailId;
	private ResultStatus resultStatus;
	private String wardId ;
	private List<QuestionAnswerVO> questionAnswerVO ;
	private String formString;
	private boolean status;
	private Long surveyor,teamlead;
	private List<SurveyorVO> surveyoList;
	private String questionRemark;
	private List<SurveyAgeWiseDetailsVO> surveyAgeWiseDetailsVO;
	private List<SurveyAnalysisVO> surveyAnalysisVO;
	private List<SurveyAnalysisDTO> surveyAnalysisDTO;
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
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


	public Long getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public ISurveyAnalysisService getSurveyAnalysisService() {
		return surveyAnalysisService;
	}


	public void setSurveyAnalysisService(
			ISurveyAnalysisService surveyAnalysisService) {
		this.surveyAnalysisService = surveyAnalysisService;
	}


	
	public List<SurveyVO> getSurveyVO() {
		return surveyVO;
	}


	public void setSurveyVO(List<SurveyVO> surveyVO) {
		this.surveyVO = surveyVO;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}


	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}


	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}


	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}


	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}


	public List<SelectOptionVO> getEduStatus() {
		return eduStatus;
	}


	public void setEduStatus(List<SelectOptionVO> eduStatus) {
		this.eduStatus = eduStatus;
	}


	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}


	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}


	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	
	public Long getStateId() {
		return stateId;
	}


	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}


	public Long getDistrictId() {
		return districtId;
	}


	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public Long getCountryId() {
		return countryId;
	}


	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	
	public List<SelectOptionVO> getCasteList() {
		return casteList;
	}


	public void setCasteList(List<SelectOptionVO> casteList) {
		this.casteList = casteList;
	}



	public List<VoterVO> getVoterVO() {
		return voterVO;
	}


	public void setVoterVO(List<VoterVO> voterVO) {
		this.voterVO = voterVO;
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


	public Long getHamletId() {
		return hamletId;
	}


	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}


	public Long getBoothId() {
		return boothId;
	}


	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}


	public Long getAge() {
		return age;
	}


	public void setAge(Long age) {
		this.age = age;
	}


	public Long getCaste() {
		return caste;
	}


	public void setCaste(Long caste) {
		this.caste = caste;
	}


	public Long getEducation() {
		return education;
	}


	public void setEducation(Long education) {
		this.education = education;
	}


	public Long getOccupation() {
		return occupation;
	}


	public void setOccupation(Long occupation) {
		this.occupation = occupation;
	}


	public String getVoterId() {
		return voterId;
	}


	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getLandmark() {
		return landmark;
	}


	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	

	public SurveyInfoVO getSurveyInfoVO() {
		return surveyInfoVO;
	}


	public void setSurveyInfoVO(SurveyInfoVO surveyInfoVO) {
		this.surveyInfoVO = surveyInfoVO;
	}


	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}


	public String getLocationValue() {
		return locationValue;
	}


	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}


	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	
	public String getWardId() {
		return wardId;
	}


	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	
	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<QuestionAnswerVO> getQuestionAnswerVO() {
		return questionAnswerVO;
	}


	public void setQuestionAnswerVO(List<QuestionAnswerVO> questionAnswerVO) {
		this.questionAnswerVO = questionAnswerVO;
	}


	public String getFormString() {
		return formString;
	}


	public void setFormString(String formString) {
		this.formString = formString;
	}

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
	public List<SurveyorVO> getSurveyoList() {
		return surveyoList;
	}


	public void setSurveyoList(List<SurveyorVO> surveyoList) {
		this.surveyoList = surveyoList;
	}

	
	public Long getSurveyor() {
		return surveyor;
	}


	public void setSurveyor(Long surveyor) {
		this.surveyor = surveyor;
	}


	public Long getTeamlead() {
		return teamlead;
	}


	public void setTeamlead(Long teamlead) {
		this.teamlead = teamlead;
	}

	
	public String getQuestionRemark() {
		return questionRemark;
	}


	public void setQuestionRemark(String questionRemark) {
		this.questionRemark = questionRemark;
	}

	
	public List<SurveyAgeWiseDetailsVO> getSurveyAgeWiseDetailsVO() {
		return surveyAgeWiseDetailsVO;
	}


	public void setSurveyAgeWiseDetailsVO(
			List<SurveyAgeWiseDetailsVO> surveyAgeWiseDetailsVO) {
		this.surveyAgeWiseDetailsVO = surveyAgeWiseDetailsVO;
	}

	
	public List<SurveyAnalysisVO> getSurveyAnalysisVO() {
		return surveyAnalysisVO;
	}


	public void setSurveyAnalysisVO(List<SurveyAnalysisVO> surveyAnalysisVO) {
		this.surveyAnalysisVO = surveyAnalysisVO;
	}

	
	public List<SurveyAnalysisDTO> getSurveyAnalysisDTO() {
		return surveyAnalysisDTO;
	}


	public void setSurveyAnalysisDTO(List<SurveyAnalysisDTO> surveyAnalysisDTO) {
		this.surveyAnalysisDTO = surveyAnalysisDTO;
	}


	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
		Long accessValue           = Long.valueOf(regVO.getAccessValue());
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID));
		constituencyList           = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		stateList                  = cadreManagementService.findStatesByCountryID(accessValue.toString());
		districtList               = staticDataService.getDistricts(accessValue);
		eduStatus                  = staticDataService.getAllEducationalQualifications();
		occupationsList            = staticDataService.getAllOccupations();
		casteList                  = surveyAnalysisService.getAllCastesForUser(regVO.getRegistrationID());
		surveyoList                = surveyAnalysisService.getServeyorDetails();
		formString                 = surveyAnalysisService.getSurveyForm(surveyId);
		
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		stateList.add(0,new SelectOptionVO(0l,"Select State"));
		districtList.add(0,new SelectOptionVO(0l,"Select District"));
		casteList.add(0,new SelectOptionVO(0l,"Select Caste"));
		eduStatus.add(0,new SelectOptionVO(0l,"Select Education"));
		occupationsList.add(0,new SelectOptionVO(0l,"Select Occupation"));
		//surveyoList.add(0,new SurveyorVO(0l,"Select Surveyor"));
		return Action.SUCCESS;
	}

	public String getSurveyDetails()
	{
		try {
			LOG.debug("entered into getSurveyDetails() method in SurveyFormAction Action Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getSurveyDetails"))
			{
				Long surveyId       = jObj.getLong("surveyId");
				surveyVO            = surveyAnalysisService.getSurveyDetailsBySurveyId(surveyId);
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getLocationDetails"))
			{
				locationId          = jObj.getLong("locationId");
			    locationValue       = jObj.getString("locationValue");
				name                = cadreManagementService.getRegionScopeValues(locationId,locationValue);
				return "name";
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getVoterDetails"))
			{
				String voterCardNo  = jObj.getString("voterId");
				voterVO             = surveyAnalysisService.getVoterDetailsBasedOnVoterId(voterCardNo,regVO.getRegistrationID());
				return "voterVO";
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getAgeWiseSurveyAnalysis") || jObj.getString("task").equalsIgnoreCase("getGenderWiseSurveyAnalysis") || jObj.getString("task").equalsIgnoreCase("getOptionWiseSurveyAnalysis") || jObj.getString("task").equalsIgnoreCase("getCasteWiseSurveyAnalysis"))
			{
				Long surveyId       = jObj.getLong("surveyId");
				surveyAnalysisDTO = surveyAnalysisService.getAttributesWiseSurveyAnalysis(surveyId);
				return "surveyAnalysis";
			}
			
			/*else if(jObj.getString("task").equalsIgnoreCase("getGenderWiseSurveyAnalysis"))
			{
				Long surveyId       = jObj.getLong("surveyId");
				surveyAgeWiseDetailsVO = surveyAnalysisService.getGenderWiseSurveyAnalysis(surveyId);
				return "ageWiseAnalysis";
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getOptionWiseSurveyAnalysis"))
			{
				Long surveyId       = jObj.getLong("surveyId");
				surveyAgeWiseDetailsVO = surveyAnalysisService.getOptionWiseSurveyAnalysis(surveyId);
				return "ageWiseAnalysis";
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getCasteWiseSurveyAnalysis"))
			{
				Long surveyId       = jObj.getLong("surveyId");
				surveyAnalysisVO = surveyAnalysisService.getCasteWiseSurveyAnalysis(surveyId);
				return "casteWiseAnalysis";
			}*/
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in getSurveyDetails() method in SurveyFormAction Action Class", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String saveSurveyFormData()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
	    surveyInfoVO = new SurveyInfoVO();
		surveyInfoVO.setContryId(countryId);
		surveyInfoVO.setStateId(stateId);
		surveyInfoVO.setDistrictId(districtId);
		surveyInfoVO.setConstituencyId(constituencyId);
		String mandal = String.valueOf(mandalId);
		if(mandal.charAt(0) == '1')
		{
			surveyInfoVO.setLocalBodyElectionId(mandalId);
		}
		else
		{
			surveyInfoVO.setMandalId(mandalId);
		}
		
		//surveyInfoVO.setPanchayatId(panchayatId);
		surveyInfoVO.setWardId(Long.valueOf(wardId));
		surveyInfoVO.setBoothId(boothId);
		surveyInfoVO.setHamletId(hamletId);
		surveyInfoVO.setName(name);
		surveyInfoVO.setVoterCardNo(voterId);
		//surveyInfoVO.setVoterId();
		surveyInfoVO.setAge(age);
		surveyInfoVO.setMobileNo(mobileNo);
		surveyInfoVO.setCasteId(caste);
		//surveyInfoVO.setCaste();
		surveyInfoVO.setOccupationId(occupation);
		//surveyInfoVO.setOccupation();
		surveyInfoVO.setEducateionId(education);
		//surveyInfoVO.setEducateion();
		surveyInfoVO.setGender(gender);
		surveyInfoVO.setLandmark(landmark);
		surveyInfoVO.setPhoneNo(phoneNo);
		surveyInfoVO.setEmailId(emailId);
		surveyInfoVO.setSurveyorId(surveyor);
		surveyInfoVO.setTeamleadId(teamlead);
		//surveyInfoVO.setLocationId(locationId);
		//surveyInfoVO.setLocationValue(Long.valueOf(locationValue));
		Long accessValue= Long.valueOf(regVO.getAccessValue());
		if(questionAnswerVO != null && surveyInfoVO.getName() != null)
		{
			resultStatus = surveyAnalysisService.saveSurveyDetails(surveyInfoVO,regVO.getRegistrationID(),questionAnswerVO);
			if(resultStatus.getResultCode() == 1)
			{
				status = false;
			    return Action.SUCCESS;
			}
			else
			{
				status = true;
			}
		}
		/*if(questionAnswerVO != null)
		{
   		 	status = surveyAnalysisService.saveSurveyForm(questionAnswerVO);
		}*/
		userAccessConstituencyList     = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID));
		constituencyList               = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		stateList                      = cadreManagementService.findStatesByCountryID(accessValue.toString());
		districtList                   = staticDataService.getDistricts(accessValue);
		eduStatus                      = staticDataService.getAllEducationalQualifications();
		occupationsList                = staticDataService.getAllOccupations();
		casteList                      = surveyAnalysisService.getAllCastesForUser(regVO.getRegistrationID());
		surveyoList                    = surveyAnalysisService.getServeyorDetails();
		formString                     = surveyAnalysisService.getSurveyForm(surveyId);
		
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		stateList.add(0,new SelectOptionVO(0l,"Select State"));
		districtList.add(0,new SelectOptionVO(0l,"Select District"));
		casteList.add(0,new SelectOptionVO(0l,"Select Caste"));
		eduStatus.add(0,new SelectOptionVO(0l,"Select Education"));
		occupationsList.add(0,new SelectOptionVO(0l,"Select Occupation"));
		
		return Action.SUCCESS;
	}


	public List<QuestionAnswerVO> getModel() {
		
		return questionAnswerVO;
	}

}
