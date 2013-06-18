package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IOptionDAO;
import com.itgrids.partyanalyst.dao.IOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyInfoVO;
import com.itgrids.partyanalyst.dto.SurveyVO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Option;
import com.itgrids.partyanalyst.model.QuestionOptions;
import com.itgrids.partyanalyst.model.Respondent;
import com.itgrids.partyanalyst.model.SurveyQuestion;
import com.itgrids.partyanalyst.model.SurveyorProfile;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRespondentDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.dao.ISurveyorProfileDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Survey;
import com.itgrids.partyanalyst.model.UpdationDetails;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyAnalysisService implements ISurveyAnalysisService {
	
	private static Logger LOG = Logger.getLogger(SurveyAnalysisService.class);
	
	private ISurveyQuestionDAO surveyQuestionDAO;
	private IOptionDAO optionDAO;
	private IOptionTypeDAO optionTypeDAO;
	private IQuestionOptionsDAO questionOptionsDAO;
	private TransactionTemplate transactionTemplate;
	private ISurveyDAO surveyDAO;
	private IRegionScopesDAO regionScopesDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserDAO userDAO; 
	private IUpdationDetailsDAO updationDetailsDAO;
	private IUserAddressDAO userAddressDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO; 
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IVoterDAO voterDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPanchayatDAO panchayatDAO;
	private IBoothDAO boothDAO ;
	private IHamletDAO hamletDAO;
	private ISurveyorProfileDAO surveyorProfileDAO;
	private IRespondentDAO  respondentDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IOccupationDAO  occupationDAO;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IQuestionOptionsDAO getQuestionOptionsDAO() {
		return questionOptionsDAO;
	}
	public void setQuestionOptionsDAO(IQuestionOptionsDAO questionOptionsDAO) {
		this.questionOptionsDAO = questionOptionsDAO;
	}

	public IOptionTypeDAO getOptionTypeDAO() {
		return optionTypeDAO;
	}

	public void setOptionTypeDAO(IOptionTypeDAO optionTypeDAO) {
		this.optionTypeDAO = optionTypeDAO;
	}

	public ISurveyQuestionDAO getSurveyQuestionDAO() {
		return surveyQuestionDAO;
	}

	public void setSurveyQuestionDAO(ISurveyQuestionDAO surveyQuestionDAO) {
		this.surveyQuestionDAO = surveyQuestionDAO;
	}

	public IOptionDAO getOptionDAO() {
		return optionDAO;
	}

	public void setOptionDAO(IOptionDAO optionDAO) {
		this.optionDAO = optionDAO;
	}

    public ISurveyDAO getSurveyDAO() {
		return surveyDAO;
	}
	public void setSurveyDAO(ISurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}
    public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}
	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public IUpdationDetailsDAO getUpdationDetailsDAO() {
		return updationDetailsDAO;
	}
	public void setUpdationDetailsDAO(IUpdationDetailsDAO updationDetailsDAO) {
		this.updationDetailsDAO = updationDetailsDAO;
	}
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	
	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}
	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}
	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	public ISurveyorProfileDAO getSurveyorProfileDAO() {
		return surveyorProfileDAO;
	}
	public void setSurveyorProfileDAO(ISurveyorProfileDAO surveyorProfileDAO) {
		this.surveyorProfileDAO = surveyorProfileDAO;
	}
	public IRespondentDAO getRespondentDAO() {
		return respondentDAO;
	}
	public void setRespondentDAO(IRespondentDAO respondentDAO) {
		this.respondentDAO = respondentDAO;
	}
	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}
	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}
	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}
	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}
	public List<SelectOptionVO>  getOptionTypes(){
		List<SelectOptionVO>  optionTypes = new ArrayList<SelectOptionVO>();
		SelectOptionVO valuesVo = null;
		List<Object[]> list = null;
		try{
		LOG.debug("Enterd into getOptionTypes Method in SurveyAnalysis service");
		list =  optionTypeDAO.getOptionTypes();
		if(list != null){
			for(Object[] params : list){
				valuesVo = new SelectOptionVO();
				valuesVo.setId((Long)params[0]);
				valuesVo.setName(params[1].toString());
				
				optionTypes.add(valuesVo);
			}
		}
		
		}
		catch (Exception e) {
			LOG.error("Exception in getOptionTypes Method in SurveyAnalysis service",e);
			
		}
		
		return optionTypes;
	}
	public ResultStatus savesurveyDetails(final String name,final String desc,final Long scopeVal,final Long stateId,final Long districtId,final Long constId,final Long mandalId,final Long userId,final String consType)
    {
		ResultStatus resultStatus = new ResultStatus();
		try{
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		  protected void doInTransactionWithoutResult(TransactionStatus status) 
		 {
		     Survey survey = new Survey();
		     survey.setName(name);
		     survey.setDescription(desc);
		     survey.setLocationScopes(regionScopesDAO.get(scopeVal));
		     survey.setIsDeleted(IConstants.FALSE);
		     survey.setStartTime(dateUtilService.getCurrentDateAndTime());
		     
		     UpdationDetails updationDetails = new UpdationDetails();
		     updationDetails.setCreatedBy(userDAO.get(userId));
		     updationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		     
		     updationDetails = updationDetailsDAO.save(updationDetails);
		     survey.setUpdationDetails(updationDetails);
		     
		     UserAddress userAddress = new UserAddress();
		     if(scopeVal.equals(1L))
		     {
		    	 userAddress.setCountry(countryDAO.get(scopeVal));
		    	 survey.setLocationScopeValue(scopeVal);
		     }
		     else if(scopeVal.equals(2L))
		     {
		    	 userAddress.setState(stateDAO.get(stateId));
		    	 survey.setLocationScopeValue(stateId); 
		     }
		     else if(scopeVal.equals(3L))
		     {
		    	 userAddress.setState(stateDAO.get(stateId));
		    	 userAddress.setDistrict(districtDAO.get(districtId));
		    	 survey.setLocationScopeValue(districtId); 
		     }
		     else if(scopeVal.equals(4L))
		     {
		    	 if(consType.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE))
		    	 {
		    	   userAddress.setState(stateDAO.get(stateId));
		    	   userAddress.setDistrict(districtDAO.get(districtId));
		    	   userAddress.setConstituency(constituencyDAO.get(constId));
		    	 }
		    	 else
		    		userAddress.setParliamentConstituency(constituencyDAO.get(constId));
		    	 survey.setLocationScopeValue(constId); 
		     }
		     else if(scopeVal.equals(5L))
		     {
		    	 userAddress.setState(stateDAO.get(stateId));
		    	 userAddress.setDistrict(districtDAO.get(districtId));
		    	 userAddress.setConstituency(constituencyDAO.get(constId));
		    	 userAddress.setTehsil(tehsilDAO.get(mandalId));
		    	 survey.setLocationScopeValue(mandalId); 
		     }
		     else if(scopeVal.equals(7L))
		     {
		    	 userAddress.setState(stateDAO.get(stateId));
		    	 userAddress.setDistrict(districtDAO.get(districtId));
		    	 userAddress.setConstituency(constituencyDAO.get(constId));
		    	 Long localEleBodyId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandalId.toString().substring(1))).get(0);
		    	 userAddress.setLocalElectionBody(localElectionBodyDAO.get(localEleBodyId));
		    	 survey.setLocationScopeValue(localEleBodyId); 
		     }
		     
		     userAddress = userAddressDAO.save(userAddress);
		     survey.setUserAddress(userAddress);
		     surveyDAO.save(survey);
		  
		 }});
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			LOG.error("Exception Occured in savesurveyDetails() method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}

	public ResultStatus saveQuestion(final List<QuestionsOptionsVO> questionsOptionsList)
	{
		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();

						try {

			if(questionsOptionsList!= null && questionsOptionsList.size() > 0)
			{
			SurveyQuestion surveyQuestion = new SurveyQuestion();
			surveyQuestion.setQuestion(questionsOptionsList.get(0).getQuestion());
			surveyQuestion.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getQuestionType())));
			
			surveyQuestion.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getHasRemark()));
			surveyQuestion.setIsAnalyse(String.valueOf(questionsOptionsList.get(0).getIsAnalyse()));
			surveyQuestion = surveyQuestionDAO.save(surveyQuestion);
			
				if(questionsOptionsList.get(0).getOptions() != null && questionsOptionsList.get(0).getOptions().size() > 0)
					{
							for(int i=0;i<questionsOptionsList.get(0).getOptions().size();i++)
							{
							Option option = new Option();
							option.setOptions(questionsOptionsList.get(0).getOptions().get(i).getOption());
							option.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getOptions().get(i).getHasRemark()));
							if(questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion()!= null && questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion() == true)
							{
							option.setSubOptionName(questionsOptionsList.get(0).getOptions().get(i).getSubquestion());
							option.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getOptions().get(i).getSubquestionType())));
							}
							
							option = optionDAO.save(option);
							
							if(questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion()!= null && questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion() == true)
							{
								if(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList() != null && questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size() > 0)
								{
								for(int j=0;j< questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size();j++)
								{
									
									Option option1 = new Option();
									option1.setParentOption(option);
									option1.setOptions(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getOption());
									option1.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getHasRemark()));
									
									option1 = optionDAO.save(option1);
									}
								}	
							}
							QuestionOptions	questionOptions = new QuestionOptions();
							questionOptions.setSurveyQuestion(surveyQuestion);
							questionOptions.setOptions(option);
							questionOptionsDAO.save(questionOptions);
							rs.setResultCode(ResultCodeMapper.SUCCESS);
						}
							
					}
				
				
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in saveQuestion() method - Exception",e);
		}
						return rs;
					}
				});

		return resultStatus;
	}
	
	public ResultStatus saveQuestionForMultipleText(final List<QuestionsOptionsVO> questionsOptionsList)
	{
		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();

						try {
			if(questionsOptionsList!= null && questionsOptionsList.size() > 0)
			{
			SurveyQuestion surveyQuestion = new SurveyQuestion();
			surveyQuestion.setQuestion(questionsOptionsList.get(0).getQuestion());
			surveyQuestion.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getQuestionType())));
			surveyQuestion.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getHasRemark()));
			surveyQuestion.setIsAnalyse(String.valueOf(questionsOptionsList.get(0).getIsAnalyse()));
			surveyQuestion = surveyQuestionDAO.save(surveyQuestion);
			if(questionsOptionsList.get(0).getOptions() != null && questionsOptionsList.get(0).getOptions().size() > 0)
			{
					for(int i=0;i<questionsOptionsList.get(0).getOptions().size();i++)
					{
					Option option = new Option();
					option.setOptions(questionsOptionsList.get(0).getOptions().get(i).getOption());
					option = optionDAO.save(option);
					
					if(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList() != null && questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size() > 0)
					{
					for(int j=0;j< questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size();j++)
						{
						Option option1 = new Option();
						option1.setParentOption(option);
						option1.setOptions(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getSubquestion());
						option1 = optionDAO.save(option1);
						}
					}	
					
					QuestionOptions	questionOptions = new QuestionOptions();
					questionOptions.setSurveyQuestion(surveyQuestion);
					questionOptions.setOptions(option);
					questionOptionsDAO.save(questionOptions);	
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					}
					
			}
			
					
		}
						
	}
			catch (Exception e) {
				LOG.error("Exception Occured in saveQuestion() method - Exception",e);
			}
							return rs;
						}
					});

			return resultStatus;
		}
	
	public List<SurveyVO> getSurveyDetailsBySurveyId(Long surveyId) {
		List<SurveyVO> returnData = null;
		List<Survey> survey = surveyDAO.getSurveyDataBySurveyId(surveyId);
		if(survey != null && survey.size()> 0);
		{
			returnData = new ArrayList<SurveyVO>();
			Survey surveyData = survey.get(0); 
			SurveyVO surveyVO = new SurveyVO();
			surveyVO.setSurveyId(surveyData.getSurveyId());
			surveyVO.setName(surveyData.getName());
			surveyVO.setDescription(surveyData.getDescription());
			surveyVO.setLocationScopeId(surveyData.getLocationScopes().getRegionScopesId());
			surveyVO.setLocationValue(surveyData.getLocationScopeValue());
			surveyVO.setConstituencyId(surveyData.getUserAddress()!=null?surveyData.getUserAddress().getConstituency().getConstituencyId():0l);
			returnData.add(surveyVO);
		}
		
		return returnData;
	}
	
	public List<SelectOptionVO> getAllCastesForUser(Long userId)
	{
		List<SelectOptionVO> returnList = null;
		List<Object[]> casteList = voterCastInfoDAO.getAllCasteInfoByUserId(userId);
		if(casteList != null && casteList.size() > 0)
		{
			returnList = new ArrayList<SelectOptionVO>();
			SelectOptionVO selectOptionVO = null;
			for (Object[] parms : casteList) {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)parms[0]);
				selectOptionVO.setName(parms[1].toString());
				returnList.add(selectOptionVO);
			}
		}
		return returnList;
	}
	public List<VoterVO> getVoterDetailsBasedOnVoterId(String VoterCardId,Long userId)
	{
		List<VoterVO> voterList = null;
		Voter voter = voterDAO.getVoterByVoterIDCardNo(VoterCardId);
		Long voterId = voter.getVoterId();
		if(voter != null)
		{
			List<Object[]> voterData = userVoterDetailsDAO.getVoterDetailsForSurveyForm(voterId,userId);
			if(voterData != null && voterData.size() > 0)
			{
				voterList = new ArrayList<VoterVO>();
				for (Object[] parms : voterData) {
					VoterVO voterVO = new VoterVO();
					//Voter voterInfo      = (Voter) parms[0];
					CasteState casteInfo =  (CasteState) parms[1];
					voterVO.setVoterIds(voter.getVoterId());
					voterVO.setFirstName(voter.getName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					if(voter.getGender().equalsIgnoreCase("m"))
					{
						voterVO.setGender("Male");
					}
					else
					{
						voterVO.setGender("Female");
					}
					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
					voterVO.setMobileNo(voter.getMobileNo()!=null ? voter.getMobileNo() :" ");
					voterVO.setCast(casteInfo.getCaste().getCasteName());
					voterVO.setCategoryValuesId(casteInfo.getCasteStateId());
					voterList.add(voterVO);
				}
			}
			
			
		}
		return voterList;
	}
	
	public ResultStatus saveSurveyDetails(final SurveyInfoVO surveyInfoVO)
	{
		ResultStatus resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
		public Object doInTransaction(TransactionStatus status) {
		ResultStatus resultStatus = new ResultStatus();
		try {
		LOG.debug("entered into saveSurveyDetails() method in SurveyAnalysisService Service");
		UserAddress userAddress = new UserAddress();
		SurveyorProfile surveyorProfile = new SurveyorProfile();
		Respondent respondent = new Respondent();
		Voter voter = voterDAO.getVoterByVoterIDCardNo(surveyInfoVO.getVoterCardNo());
		
		if(surveyInfoVO.getStateId()!= null && surveyInfoVO.getStateId() > 0)
		{
			userAddress.setState(stateDAO.get(surveyInfoVO.getStateId()));
		}
		
		if(surveyInfoVO.getContryId() != null && surveyInfoVO.getContryId() > 0)
		{
			userAddress.setCountry(countryDAO.get(surveyInfoVO.getContryId()));
		}
		if(surveyInfoVO.getDistrictId() != null && surveyInfoVO.getDistrictId() > 0)
		{
			userAddress.setDistrict(districtDAO.get(surveyInfoVO.getDistrictId()));
		}
		if(surveyInfoVO.getConstituencyId() != null && surveyInfoVO.getConstituencyId() > 0)
		{
			userAddress.setConstituency(constituencyDAO.get(surveyInfoVO.getConstituencyId()));
		}
		if(surveyInfoVO.getMandalId() != null && surveyInfoVO.getMandalId() > 0)
		{
			userAddress.setTehsil(tehsilDAO.get(Long.valueOf(surveyInfoVO.getMandalId().toString().substring(1))));
		}
		if(surveyInfoVO.getBoothId() != null && surveyInfoVO.getBoothId() > 0)
		{
			userAddress.setBooth(boothDAO.get(surveyInfoVO.getBoothId()));
		}
		if(surveyInfoVO.getHamletId() != null && surveyInfoVO.getHamletId() > 0)
		{
			userAddress.setHamlet(hamletDAO.get(surveyInfoVO.getHamletId()));
		}
		if(surveyInfoVO.getWardId() != null && surveyInfoVO.getWardId() > 0)
		{
			userAddress.setWard(constituencyDAO.get(Long.valueOf(surveyInfoVO.getWardId().toString().substring(1))));
		}
		if(surveyInfoVO.getLocalBodyElectionId() != null && surveyInfoVO.getLocalBodyElectionId() > 0)
		{
			AssemblyLocalElectionBody assemblyLocalElection = assemblyLocalElectionBodyDAO.get(Long.valueOf(surveyInfoVO.getLocalBodyElectionId().toString().substring(1)));
			if(assemblyLocalElection != null)
			{
				Long id = assemblyLocalElection.getLocalElectionBody().getLocalElectionBodyId();
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(id));
			}
			
		}
		/*if(surveyInfoVO.getLocalBodyElectionId() != null && surveyInfoVO.getLocalBodyElectionId() > 0)
		{
			userAddress.setLocalElectionBody(localElectionBodyDAO.get(surveyInfoVO.getLocalBodyElectionId()));
		}*/
		if(surveyInfoVO.getParlemtId() != null && surveyInfoVO.getParlemtId() > 0)
		{
			userAddress.setParliamentConstituency(constituencyDAO.get(surveyInfoVO.getParlemtId()));
		}
		surveyorProfile.setName(surveyInfoVO.getName()!=null ?surveyInfoVO.getName():"");
		surveyorProfile.setMobileNo(surveyInfoVO.getMobileNo()!=null ?surveyInfoVO.getMobileNo():"");
		surveyorProfile.setAge(surveyInfoVO.getAge()!=null?surveyInfoVO.getAge().toString():"");
		if(surveyInfoVO.getEducateionId() != null && surveyInfoVO.getEducateionId() > 0)
		{
			surveyorProfile.setEducationalQualifications(educationalQualificationsDAO.get(surveyInfoVO.getEducateionId()));
		}
		if(surveyInfoVO.getOccupationId() != null && surveyInfoVO.getOccupationId() > 0)
		{
			surveyorProfile.setOccupation(occupationDAO.get(surveyInfoVO.getOccupationId()));
		}
		if(surveyInfoVO.getCasteId() != null && surveyInfoVO.getCasteId() > 0)
		{
			surveyorProfile.setCasteStateId(surveyInfoVO.getCasteId());
		}
		
		surveyorProfile.setGender(surveyInfoVO.getGender()!=null ? surveyInfoVO.getGender():"");
	
		userAddress = userAddressDAO.save(userAddress);
		surveyorProfile.setUserAddress(userAddress);
		surveyorProfile = surveyorProfileDAO.save(surveyorProfile);
		System.out.println(voter.getVoterId());
		respondent.setSurveyorProfile(surveyorProfile);
		if(voter.getVoterId() != null && voter.getVoterId() > 0)
		{
			respondent.setVoter(voterDAO.get(voter.getVoterId()));
		}
		
		respondent.setLandmark(surveyInfoVO.getLandmark());
		respondent  = respondentDAO.save(respondent);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch (Exception e) {
			LOG.error("exception raised in  saveSurveyDetails() method in SurveyAnalysisService Service",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
		} });
		return resultStatus;
	}
}
