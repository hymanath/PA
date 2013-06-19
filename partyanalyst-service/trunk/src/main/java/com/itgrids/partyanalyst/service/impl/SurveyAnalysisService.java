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
import com.itgrids.partyanalyst.dto.SurveyorVO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Option;
import com.itgrids.partyanalyst.model.QuestionOptions;
import com.itgrids.partyanalyst.model.Respondent;
import com.itgrids.partyanalyst.model.SurveyAnswer;
import com.itgrids.partyanalyst.model.SurveyAnswerInfo;
import com.itgrids.partyanalyst.model.SurveyQuestion;
import com.itgrids.partyanalyst.model.Surveyor;
import com.itgrids.partyanalyst.model.SurveyorProfile;
import com.itgrids.partyanalyst.model.User;
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
import com.itgrids.partyanalyst.dao.ISurveyAnswerDAO;
import com.itgrids.partyanalyst.dao.ISurveyAnswerInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.dao.ISurveyorDAO;
import com.itgrids.partyanalyst.dao.ISurveyorProfileDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
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
	private ITownshipDAO townshipDAO;
	private ISurveyAnswerDAO surveyAnswerDAO;
	private ISurveyorDAO surveyorDAO;
	private ISurveyAnswerInfoDAO surveyAnswerInfoDAO;
	
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}
	public ISurveyAnswerInfoDAO getSurveyAnswerInfoDAO() {
		return surveyAnswerInfoDAO;
	}
	public void setSurveyAnswerInfoDAO(ISurveyAnswerInfoDAO surveyAnswerInfoDAO) {
		this.surveyAnswerInfoDAO = surveyAnswerInfoDAO;
	}
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
	public ISurveyorDAO getSurveyorDAO() {
		return surveyorDAO;
	}
	public void setSurveyorDAO(ISurveyorDAO surveyorDAO) {
		this.surveyorDAO = surveyorDAO;
	}
	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}
	
	public ISurveyAnswerDAO getSurveyAnswerDAO() {
		return surveyAnswerDAO;
	}
	public void setSurveyAnswerDAO(ISurveyAnswerDAO surveyAnswerDAO) {
		this.surveyAnswerDAO = surveyAnswerDAO;
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
	public ResultStatus saveSurveyorInfo(String name,String age,String mobileNo,String phoneNo,String email,int qualification,int occupation,int caste,Long state,Long district,Long tehsil,Long township,String gender){
		  ResultStatus resultStatus=new ResultStatus();
		  
		  try {
			UserAddress userAddress=new UserAddress();
			  userAddress.setState(stateDAO.get(state));
			  userAddress.setDistrict(districtDAO.get(district));
			  userAddress.setTehsil(tehsilDAO.get(tehsil));
			  userAddress.setTownship(townshipDAO.get(township));
			  
			  userAddress=userAddressDAO.save(userAddress);
			  
			  SurveyorProfile surveyorProfile=new SurveyorProfile();
			  surveyorProfile.setName(name);
			  surveyorProfile.setMobileNo(mobileNo);
			  surveyorProfile.setPhoneNo(phoneNo);
			  surveyorProfile.setGender(gender);
			  surveyorProfile.setEmailId(email);
			  surveyorProfile.setUserAddress(userAddress);
			  surveyorProfile.setEducationalQualifications(educationalQualificationsDAO.get(new Long(qualification)));
			  surveyorProfile.setOccupation(occupationDAO.get(new Long(occupation)));
			  surveyorProfile.setCasteStateId(caste);
			  surveyorProfile.setAge(age);
			  
			  surveyorProfileDAO.save(surveyorProfile);
			  
			  resultStatus.setMessage("Surveyor Profile Saved Successfully..");
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  } catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			e.printStackTrace();
		}
		  
		  
		  return resultStatus;
	  }

	public List<SelectOptionVO> getStatesList(){
		List<SelectOptionVO> states=new ArrayList<SelectOptionVO>();
		
		List list=new ArrayList(0);
		
		list= stateDAO.getAllStatesByCountryIdOrderByStateId(1L);
		for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i); 				
				states.add(new SelectOptionVO(new Long(parms[0].toString()),parms[1].toString()));
			} 
		return states;
	}
	public List<SelectOptionVO> getDistricts(Long stateId) {
		
		List list=new ArrayList(0);
		
		list = districtDAO.findByStateId(stateId);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		
		//return districts;
		return null;
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
		     survey.setIsDeleted("false");
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
			surveyQuestion.setSurvey(surveyDAO.get(questionsOptionsList.get(0).getSurvey()));
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
		
		if(voter != null)
		{
			Long voterId = voter.getVoterId();
			List<Object[]> voterData = userVoterDetailsDAO.getVoterDetailsForSurveyForm(voterId,userId);
			VoterVO voterVO = new VoterVO();
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
			if(voterData != null && voterData.size() > 0)
			{
				voterList = new ArrayList<VoterVO>();
				for (Object[] parms : voterData) {
					
					//Voter voterInfo      = (Voter) parms[0];
					CasteState casteInfo =  (CasteState) parms[1];
				   if(casteInfo != null){
					voterVO.setCast(casteInfo.getCaste().getCasteName());
					voterVO.setCategoryValuesId(casteInfo.getCasteStateId());
					
				   }else{
					    voterVO.setCast("");
						voterVO.setCategoryValuesId(0l);
				   }
				}
			}
			
			voterList.add(voterVO);
		}
		return voterList;
	}
	
	public ResultStatus saveSurveyDetails(final SurveyInfoVO surveyInfoVO,final Long userId,final List<QuestionAnswerVO> questionAnswerVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		SurveyAnswerInfo surveyAnswerInfo = (SurveyAnswerInfo) transactionTemplate.execute(new TransactionCallback() {
		public Object doInTransaction(TransactionStatus status) {
		SurveyAnswerInfo surveyAnswerInfo = new SurveyAnswerInfo();
		try {
		LOG.debug("entered into saveSurveyDetails() method in SurveyAnalysisService Service");
		UserAddress userAddress = new UserAddress();
		SurveyorProfile surveyorProfile = new SurveyorProfile();
		Respondent respondent = new Respondent();
		Voter voter = null;
		
		if(surveyInfoVO.getVoterCardNo() != null && surveyInfoVO.getVoterCardNo().trim().length() >0){
		 voter = voterDAO.getVoterByVoterIDCardNo(surveyInfoVO.getVoterCardNo());
		}
		
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
		surveyorProfile.setPhoneNo(surveyInfoVO.getPhoneNo()!=null?surveyInfoVO.getPhoneNo():"");
		surveyorProfile.setEmailId(surveyInfoVO.getEmailId()!= null?surveyInfoVO.getEmailId():"");
		
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
		
		
		respondent.setSurveyorProfile(surveyorProfile);
		
		if(voter != null && voter.getVoterId() != null && voter.getVoterId() > 0)
		{
			respondent.setVoter(voterDAO.get(voter.getVoterId()));
		}
		UpdationDetails updationDetails = new UpdationDetails();
		User user = userDAO.get(userId);
		if(user != null)
		{
			updationDetails.setCreatedBy(user);
			updationDetails.setUpdatedBy(user);
		}
		updationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		updationDetailsDAO.save(updationDetails);
		
		respondent.setLandmark(surveyInfoVO.getLandmark());
		respondent.setUpdationDetails(updationDetails);
		respondent  = respondentDAO.save(respondent);
		
		/*Surveyor surveyor = new Surveyor();
		if(surveyInfoVO.getSurveyorId() != null && surveyInfoVO.getSurveyorId() > 0)
		{
			surveyor.setTeamLead(surveyorDAO.get(surveyInfoVO.getTeamleadId()));
		}
		surveyor.setSurveyorProfile(surveyorProfile);
		surveyor.setUpdationDetails(updationDetails);
		surveyor = surveyorDAO.save(surveyor);*/

		/*if(surveyorProfile.getRegionScopes().getRegionScopesId() != null && surveyorProfile.getRegionScopes().getRegionScopesId() > 0)
		{
			surveyAnswerInfo.setRegionScopes(regionScopesDAO.get(surveyorProfile.getRegionScopes().getRegionScopesId()));
		}*/
		if(surveyInfoVO.getSurveyorId() != null && surveyInfoVO.getSurveyorId() > 0)
		{
			surveyAnswerInfo.setSurveyor(surveyorDAO.get(surveyInfoVO.getSurveyorId()));
		}
		if(surveyInfoVO.getTeamleadId() != null && surveyInfoVO.getTeamleadId() > 0)
		{
			surveyAnswerInfo.setTeamLead(surveyorDAO.get(surveyInfoVO.getTeamleadId()));
		}
		surveyAnswerInfo.setRespondent(respondent);
		surveyAnswerInfo.setUpdationDetails(updationDetails);
		surveyAnswerInfo.setUserAddress(userAddress);
		surveyAnswerInfo = surveyAnswerInfoDAO.save(surveyAnswerInfo);
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch (Exception e) {
			LOG.error("exception raised in  saveSurveyDetails() method in SurveyAnalysisService Service",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return null;
		}
		if(questionAnswerVO != null && questionAnswerVO.size() > 0)
		{
			boolean responce = saveSurveyForm(questionAnswerVO,surveyAnswerInfo);
			if(responce == false)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
				
			else
			{
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
		}
		return surveyAnswerInfo;
		} });
		
		return resultStatus;
	}
	
	
    public String getSurveyForm(Long surveyId){
    	StringBuilder questions = new StringBuilder("<input type='hidden' name='surveyId' value='"+surveyId+"'/>");
    	List<SurveyQuestion> surveyQuestions = surveyQuestionDAO.getAllQuestionsForSurvey(surveyId);
    	
    	if(surveyQuestions != null && surveyQuestions.size() > 0){
    		int questionNo = 0;
    		for(SurveyQuestion surveyQuestion : surveyQuestions){
    			questionNo = questionNo+1;
    			questions.append("<div class='span12 well'>");
    			questions.append(getSurveyQuestion(surveyQuestion,questionNo));
    			questions.append("</div>");
    		}
    	}
    	
    	return questions.toString();
    }
    
    public String getSurveyQuestion(SurveyQuestion surveyQuestion,int questionNo){
    	
    	Long optionType = surveyQuestion.getOptionType().getOptionTypeId();
    	Long surveyQuestionId = surveyQuestion.getSurveyQuestionId();
    	String question = surveyQuestion.getQuestion();
    			
    	switch (optionType.intValue()) {
    	
	        case 1:  return getQuestionWithMultipleChoiceWithSingleSelect(surveyQuestionId,question,questionNo); 	                 
	                 
	        case 2:  return getQuestionWithMultipleChoiceWithMultiSelect(surveyQuestionId,question,questionNo); 	            
	                 
	        case 3:  return getQuestionWithMultipleOptionsWithSingleTextBox(surveyQuestionId,question,questionNo); 
	                 
	        case 4:  return getQuestionWithMultipleOptionsWithMultipleTextBox(surveyQuestionId,question,questionNo); 
	                 
	        case 5:  return getQuestionWithTextBox(surveyQuestionId,question,questionNo); 
	                 
	        case 6:  return getQuestionWithTextArea(surveyQuestionId,question,questionNo); 
        
        }
    	return "";
    }
	
	 public String getQuestionWithMultipleChoiceWithSingleSelect(Long surveyQuestionId,String question,int questionNo){
		 StringBuilder questionStr = new StringBuilder("");
		 boolean hasSubOption = false;
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 
		 for(Option option : options){
			 if(option.getOptionType() != null){
				 hasSubOption = true;
				 break;
			 }
		 }
		 
		 if(!hasSubOption){
			 questionStr.append("<table class='mainoptmargin'>");
			 int i = 0;
			 for(Option option : options){
					 if(i%4 == 0)
						 questionStr.append("<tr>");
					 
					 questionStr.append("<td  valign='top' style='width:25%;'><input type='radio' name='questionAnswerVO["+questionNo+"].options[0].optionId' value='"+option.getOptionsId()+"' />"+option.getOptions()+"</td>");
					   
					 if((i+1)%4 == 0)
						 questionStr.append("</tr>");
					 
					 i=i+1;
			 }
			 if(i == 1 || (i-1)%4 != 0)
				 questionStr.append("</tr>"); 
			 
			 questionStr.append("</table>");
			
		 }else{
			 int i = 0;
			 for(Option option : options){
				
				 questionStr.append("<div  class='mainoptmargin'><input type='radio' class='singleChoiceClass singleChoiceClass"+questionNo+"' key='"+questionNo+"' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' />"+option.getOptions()+"</div>");
				 if(option.getOptionType() != null){
					 questionStr.append(getSurveySubQuestion(option.getOptionsId(),option.getOptionType().getOptionTypeId(),option.getSubOptionName(),questionNo,i));
				 }
				 i++;
			 }
		 }
		 
		return questionStr.toString();
	 }
	
	 public String getQuestionWithMultipleChoiceWithMultiSelect(Long surveyQuestionId,String question,int questionNo){
		 boolean hasSubOption = false;	
		 StringBuilder questionStr = new StringBuilder("");
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 
		 for(Option option : options){
			 if(option.getOptionType() != null){
				 hasSubOption = true;
				 break;
			 }
		 }
		 
		 if(!hasSubOption){
			 questionStr.append("<table class='mainoptmargin'>");
			 int i = 0;
			 for(Option option : options){
					 if(i%4 == 0)
						 questionStr.append("<tr>");
					 
					 questionStr.append("<td  valign='top' style='width:25%;'><input type='checkbox' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' />"+option.getOptions()+"</td>");
					   
					 if((i+1)%4 == 0)
						 questionStr.append("</tr>");
					 
					 i=i+1;
			 }
			 if(i == 1 || (i-1)%4 != 0)
				 questionStr.append("</tr>"); 
			 
			 questionStr.append("</table>");	
		 }
		 else{
			 int i = 0;
			 for(Option option : options){
				 questionStr.append("<div  class='mainoptmargin'><input type='checkbox' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' />"+option.getOptions()+"</div>");
				 if(option.getOptionType() != null){
					 questionStr.append(getSurveySubQuestion(option.getOptionsId(),option.getOptionType().getOptionTypeId(),option.getSubOptionName(),questionNo,i));
				 }
				 i = i+1;
			 }
		 }
		return questionStr.toString();
	 }
	 
	 public String getQuestionWithMultipleOptionsWithSingleTextBox(Long surveyQuestionId,String question,int questionNo){
		 StringBuilder questionStr = new StringBuilder("");
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 int i = 0;
		 for(Option option : options){
			 questionStr.append("<div class='mainoptmargin'>"+option.getOptions()+" <input type='text' name='questionAnswerVO["+questionNo+"].options["+i+"].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /></div>");
			 if(option.getOptionType() != null){
				 questionStr.append(getSurveySubQuestion(option.getOptionsId(),option.getOptionType().getOptionTypeId(),option.getSubOptionName(),questionNo,i));
			 }
			 i++;
		 }
		return questionStr.toString();
	 }
	 
	 public String getQuestionWithMultipleOptionsWithMultipleTextBox(Long surveyQuestionId,String question,int questionNo){
		 StringBuilder questionStr = new StringBuilder("");
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 questionStr.append("<table class='mainoptmargin'>");
		 int j = 0;
		 for(Option option : options){
			 questionStr.append("<tr>");
			  questionStr.append("<td  valign='top' style='width:25%;'> "+option.getOptions()+" <input type='text' name='questionAnswerVO["+questionNo+"].options["+j+"].options[0].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+j+"].options[0].optionId' value='"+option.getOptionsId()+"' /></td>");
			   
			 
			 List<Option> subOptions = optionDAO.getSubOptionsByParentOptionId(option.getOptionsId());
			 
			 int i = 1;
			 for( Option subOption: subOptions){
				 if(i%4 == 0)
					 questionStr.append("<tr>");
				 
				 questionStr.append("<td  valign='top' style='width:25%;'> "+subOption.getOptions()+" <input type='text' name='questionAnswerVO["+questionNo+"].options["+j+"].options["+i+"].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+j+"].options["+i+"].optionId' value='"+subOption.getOptionsId()+"' /></td>");
				   
				 if((i+1)%4 == 0)
					 questionStr.append("</tr>");
				 i++; 
			 }
			 
			 if( i == 1 || (i-1)%4 != 0)
				 questionStr.append("</tr>"); 
			 j++;
			 
		 }
		 questionStr.append("</table>");
		return questionStr.toString();
	 }
	 public String getQuestionWithTextBox(Long surveyQuestionId,String question,int questionNo){
		 StringBuilder questionStr = new StringBuilder("");
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 int i = 0;
		 for(Option option : options){
			 questionStr.append("<div class='mainoptmargin'>"+option.getOptions()+" <input type='text' name='questionAnswerVO["+questionNo+"].options["+i+"].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /></div>");
			 if(option.getOptionType() != null){
				 questionStr.append(getSurveySubQuestion(option.getOptionsId(),option.getOptionType().getOptionTypeId(),option.getSubOptionName(),questionNo,i));
			 }
			 i++;
		 }
		return questionStr.toString();
	 }
	 public String getQuestionWithTextArea(Long surveyQuestionId,String question,int questionNo){
		 StringBuilder questionStr = new StringBuilder("");
		 List<Option> options = questionOptionsDAO.getOptionsForQuestion(surveyQuestionId);
		 questionStr.append("<div style='font-size:15px;font-weight:bold;'>"+questionNo+")&nbsp;"+question+"<input type='hidden' name='questionAnswerVO["+questionNo+"].questionId' value='"+surveyQuestionId+"' /></div>");
		 int i = 0;
		 for(Option option : options){
			 questionStr.append("<div class='mainoptmargin'>"+option.getOptions()+" <textarea  name='questionAnswerVO["+questionNo+"].options["+i+"].optionVal' ></textarea><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /></div>");
			 if(option.getOptionType() != null){
				 questionStr.append(getSurveySubQuestion(option.getOptionsId(),option.getOptionType().getOptionTypeId(),option.getSubOptionName(),questionNo,i));
			 }
			 i++;
		 }
		return questionStr.toString();
	 }
	 
	 public String getSurveySubQuestion(Long parentOptionsId,Long optionType,String question,int questionNo,int optionNo){
	    	
	    	switch (optionType.intValue()) {
	    	
		        case 1:  return getSubQuestionWithMultipleChoiceWithSingleSelect(parentOptionsId,question,questionNo,optionNo); 
		                 
		                 
		        case 2:  return getSubQuestionWithMultipleChoiceWithMultiSelect(parentOptionsId,question,questionNo,optionNo); 
		                 
		                   
		        case 5:  return getSubQuestionWithTextBox(parentOptionsId,question,questionNo,optionNo); 
		                
		                 
		        case 6:  return getSubQuestionWithTextArea(parentOptionsId,question,questionNo,optionNo); 
	                     
	        
	        }
	    	return "";
	 }
	 
	 public String getSubQuestionWithMultipleChoiceWithSingleSelect(Long parentOptionsId,String question,int questionNo,int optionNo){
		 
		 StringBuilder subQuestion = new StringBuilder("");
		 List<Option> subOptions = optionDAO.getSubOptionsByParentOptionId(parentOptionsId);
		 
		 if(subOptions !=null && subOptions.size() > 0){
			 subQuestion.append("<div class='subquestclass'>"+question+" </div>");
			 int i = 0;
			 subQuestion.append("<table class='suboptionclass' style='width:99%'>");
			 for(Option option : subOptions){
				 if(i%4 == 0)
				   subQuestion.append("<tr>");
				 
				 subQuestion.append("<td  valign='top' style='width:25%;'><input type='radio' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options[0].optionId' value='"+option.getOptionsId()+"'  /> "+option.getOptions()+" </td>");
				   
				 if((i+1)%4 == 0)
				   subQuestion.append("</tr>");
				 
				 i=i+1;
			 }
			 
			 if(i == 1 || (i-1)%4 != 0)
			   subQuestion.append("</tr>"); 
			 
			 subQuestion.append("</table>");
		 }
		return subQuestion.toString();
	 }
	
	 public String getSubQuestionWithMultipleChoiceWithMultiSelect(Long parentOptionsId,String question,int questionNo,int optionNo){
	    	
		 StringBuilder subQuestion = new StringBuilder("");
		 List<Option> subOptions = optionDAO.getSubOptionsByParentOptionId(parentOptionsId);
		 
		 if(subOptions !=null && subOptions.size() > 0){
			 subQuestion.append("<div class='subquestclass'> "+question+" </div></td></tr>");
			 int i = 0;
			 subQuestion.append("<table class='suboptionclass' style='width:99%'>");
			 for(Option option : subOptions){
				 if(i%4 == 0)
				   subQuestion.append("<tr>");
				 
				 subQuestion.append("<td  valign='top' style='width:25%;'><input type='checkbox' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /> "+option.getOptions()+"  </td>");
				   
				 if((i+1)%4 == 0)
				   subQuestion.append("</tr>");
				 
				 i=i+1;
			 }
			 
			 if(i == 1 || (i-1)%4 != 0)
			   subQuestion.append("</tr>"); 
			 
			 subQuestion.append("</table>");
		 }
		return subQuestion.toString();
	 }
	 
	 public String getSubQuestionWithTextBox(Long parentOptionsId,String question,int questionNo,int optionNo){
	 	
		 StringBuilder subQuestion = new StringBuilder("");
		 List<Option> subOptions = optionDAO.getSubOptionsByParentOptionId(parentOptionsId);
		 
		 if(subOptions !=null && subOptions.size() > 0){
			if(subOptions.size() == 1){
				subQuestion.append("<div class='subquestclass'> "+question+" "+subOptions.get(0).getOptions()+" <input type='text' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options[0].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options[0].optionId' value='"+subOptions.get(0).getOptionsId()+"' /></div>");
			}else{
				 subQuestion.append("<div class='subquestclass'> "+question+" </div>");
				 int i = 0;
				 subQuestion.append("<table class='suboptionclass' style='width:99%'>");
				 for(Option option : subOptions){
					 if(i%4 == 0)
					   subQuestion.append("<tr>");
					 
					 subQuestion.append("<td  valign='top' style='width:25%;'>"+option.getOptions()+"  <input type='text' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options["+i+"].optionVal' /><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /> </td>");
					   
					 if((i+1)%4 == 0)
					   subQuestion.append("</tr>");
					 
					 i=i+1;
				 }
				 
				 if(i == 1 || (i-1)%4 != 0)
				   subQuestion.append("</tr>"); 
				 
				 subQuestion.append("</table>");
			 }
		 }
		return subQuestion.toString();
	 }
	 
	 public String getSubQuestionWithTextArea(Long parentOptionsId,String question,int questionNo,int optionNo){
		 StringBuilder subQuestion = new StringBuilder("");
		 List<Option> subOptions = optionDAO.getSubOptionsByParentOptionId(parentOptionsId);
		 
		 if(subOptions !=null && subOptions.size() > 0){
			if(subOptions.size() == 1){
				subQuestion.append("<div class='subquestclass'> "+question+" "+subOptions.get(0).getOptions()+" <textarea  name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options[0].optionVal'></textarea><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options[0].optionId' value='"+subOptions.get(0).getOptionsId()+"' /></div>");
			}else{
				 subQuestion.append("<div class='subquestclass'> "+question+" </div>");
				 int i = 0;
				 subQuestion.append("<table class='suboptionclass' style='width:99%'>");
				 for(Option option : subOptions){
					 if(i%2 == 0)
					   subQuestion.append("<tr>");
					 
					 subQuestion.append("<td  valign='top' style='width:50%;'>"+option.getOptions()+"  <textarea  name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options["+i+"].optionVal'></textarea><input type='hidden' name='questionAnswerVO["+questionNo+"].options["+optionNo+"].options["+i+"].optionId' value='"+option.getOptionsId()+"' /> </td>");
					   
					 if((i+1)%2 == 0)
					   subQuestion.append("</tr>");
					 
					 i=i+1;
				 }
				 
				 if(i == 1 || (i-1)%2 != 0)
				   subQuestion.append("</tr>"); 
				 
				 subQuestion.append("</table>");
			 }
		 }
		return subQuestion.toString();
	 }
	 
	 public boolean saveSurveyForm(List<QuestionAnswerVO> questionAnswerVOList,SurveyAnswerInfo surveyAnswerInfo){
		  try{
			 for(QuestionAnswerVO questionAnswerVO : questionAnswerVOList){
				 if(questionAnswerVO != null && questionAnswerVO.getQuestionId() != null){
					 SurveyQuestion surveyQuestion = surveyQuestionDAO.get(questionAnswerVO.getQuestionId());
					 saveSurveyQuestion(surveyQuestion,questionAnswerVO,surveyAnswerInfo);
				 }
			 }
		  }catch(Exception e){
				LOG.error("Exception rised in saveSurveyForm ",e);
			  return false;	
			}
		  return true;
	 }
	 public void saveSurveyQuestion(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
	    	
	    	Long optionType = surveyQuestion.getOptionType().getOptionTypeId();
		
	    	switch (optionType.intValue()) {
	    	
		        case 1:   saveQuestionWithMultipleChoiceWithSingleSelect(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 	                 
		                  break;         
		        
		        case 2:   saveQuestionWithMultipleChoiceWithMultiSelect(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 	            
		                  break;
		        
		        case 3:   saveQuestionWithMultipleOptionsWithSingleTextBox(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 
		                  break;  
		        
		        case 4:   saveQuestionWithMultipleOptionsWithMultipleTextBox(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 
		                  break;
		        
		        case 5:   saveQuestionWithTextBox(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 
		                  break;
		        
		        case 6:   saveQuestionWithTextArea(surveyQuestion,questionAnswerVO,surveyAnswerInfo); 
		                  break;
	        }
	    	
	    }
		
		 public void saveQuestionWithMultipleChoiceWithSingleSelect(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			
			if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
			 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
				 if(option != null && option.getOptionId() != null && option.getOptionId().trim().length() > 0){
					 Option questionOption = optionDAO.get(Long.valueOf(option.getOptionId().trim()));
					 SurveyAnswer surveyAnswer = new SurveyAnswer();
					 surveyAnswer.setSurveyQuestion(surveyQuestion);
					 surveyAnswer.setOption(questionOption);
					 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
					 surveyAnswer.setIsSubOption("false");
					 surveyAnswerDAO.save(surveyAnswer);
					 if(option.getOptions() != null && option.getOptions().size() > 0 && questionOption.getOptionType() != null){
						 saveSurveySubQuestion(surveyQuestion,option.getOptions(),questionOption.getOptionType().getOptionTypeId(), surveyAnswerInfo);
					 }
					 break;
				 }
			 }
			} 
			
		 }
		
		 public void saveQuestionWithMultipleChoiceWithMultiSelect(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			 if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
				 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
					 if(option != null && option.getOptionId() != null && option.getOptionId().trim().length() > 0){
						 Option questionOption = optionDAO.get(Long.valueOf(option.getOptionId().trim()));
						 SurveyAnswer surveyAnswer = new SurveyAnswer();
						 surveyAnswer.setSurveyQuestion(surveyQuestion);
						 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
						 surveyAnswer.setOption(questionOption);
						 surveyAnswer.setIsSubOption("false");
						 surveyAnswerDAO.save(surveyAnswer);
						 if(option.getOptions() != null && option.getOptions().size() > 0 && questionOption.getOptionType() != null){
							 saveSurveySubQuestion(surveyQuestion,option.getOptions(),questionOption.getOptionType().getOptionTypeId(), surveyAnswerInfo);
						 }
					 }
				 }
				} 
		 }
		 
		 public void saveQuestionWithMultipleOptionsWithSingleTextBox(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			 if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
				 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
					 if(option != null && option.getOptionId() != null && option.getOptionId().trim().length() > 0 && option.getOptionVal() != null && option.getOptionVal().trim().length() > 0){
						 Option questionOption = optionDAO.get(Long.valueOf(option.getOptionId().trim()));
						 SurveyAnswer surveyAnswer = new SurveyAnswer();
						 surveyAnswer.setSurveyQuestion(surveyQuestion);
						 surveyAnswer.setOption(questionOption);
						 surveyAnswer.setIsSubOption("false");
						 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
						 surveyAnswer.setOptionValue(option.getOptionVal());
						 surveyAnswerDAO.save(surveyAnswer);
						 if(option.getOptions() != null && option.getOptions().size() > 0 && questionOption.getOptionType() != null){
							 saveSurveySubQuestion(surveyQuestion,option.getOptions(),questionOption.getOptionType().getOptionTypeId(), surveyAnswerInfo);
						 }
					 }
				 }
				} 
		 }
		 
		 public void saveQuestionWithMultipleOptionsWithMultipleTextBox(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			 if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
				 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
					 if(option != null && option.getOptions() != null && option.getOptions().size() > 0 ){
						 for(QuestionAnswerVO mainOption : option.getOptions()){
						   if(mainOption.getOptionId() != null  && mainOption.getOptionId().trim().length() > 0 && mainOption.getOptionVal() != null && mainOption.getOptionVal().trim().length() > 0){
							 Option questionOption = optionDAO.get(Long.valueOf(mainOption.getOptionId().trim()));
							 SurveyAnswer surveyAnswer = new SurveyAnswer();
							 surveyAnswer.setSurveyQuestion(surveyQuestion);
							 surveyAnswer.setOption(questionOption);
							 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
							 surveyAnswer.setIsSubOption("false");
							 surveyAnswer.setOptionValue(mainOption.getOptionVal());
							 surveyAnswerDAO.save(surveyAnswer);
						   }
						 }
					 }
				 }
				} 
		 }
		 
		 public void saveQuestionWithTextBox(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			 if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
				 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
					 if(option != null && option.getOptionId() != null && option.getOptionId().trim().length() > 0 && option.getOptionVal() != null && option.getOptionVal().trim().length() > 0){
						 Option questionOption = optionDAO.get(Long.valueOf(option.getOptionId().trim()));
						 SurveyAnswer surveyAnswer = new SurveyAnswer();
						 surveyAnswer.setSurveyQuestion(surveyQuestion);
						 surveyAnswer.setOption(questionOption);
						 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
						 surveyAnswer.setIsSubOption("false");
						 surveyAnswer.setOptionValue(option.getOptionVal());
						 surveyAnswerDAO.save(surveyAnswer);
						 if(option.getOptions() != null && option.getOptions().size() > 0 && questionOption.getOptionType() != null){
							 saveSurveySubQuestion(surveyQuestion,option.getOptions(),questionOption.getOptionType().getOptionTypeId(), surveyAnswerInfo);
						 }
					 }
				 }
				} 
		 }
		 
		 public void saveQuestionWithTextArea(SurveyQuestion surveyQuestion,QuestionAnswerVO questionAnswerVO,SurveyAnswerInfo surveyAnswerInfo){
			 if(questionAnswerVO.getOptions() != null && questionAnswerVO.getOptions().size() > 0){
				 for(QuestionAnswerVO option : questionAnswerVO.getOptions()){
					 if(option != null && option.getOptionId() != null && option.getOptionId().trim().length() > 0 && option.getOptionVal() != null && option.getOptionVal().trim().length() > 0){
						 Option questionOption = optionDAO.get(Long.valueOf(option.getOptionId().trim()));
						 SurveyAnswer surveyAnswer = new SurveyAnswer();
						 surveyAnswer.setSurveyQuestion(surveyQuestion);
						 surveyAnswer.setOption(questionOption);
						 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
						 surveyAnswer.setIsSubOption("false");
						 surveyAnswer.setOptionValue(option.getOptionVal());
						 surveyAnswerDAO.save(surveyAnswer);
						 if(option.getOptions() != null && option.getOptions().size() > 0 && questionOption.getOptionType() != null){
							 saveSurveySubQuestion(surveyQuestion,option.getOptions(),questionOption.getOptionType().getOptionTypeId(), surveyAnswerInfo);
						 }
					 }
				 }
				} 
		 }
		 
		 public void saveSurveySubQuestion(SurveyQuestion surveyQuestion,List<QuestionAnswerVO> subAnswerList,Long optionType,SurveyAnswerInfo surveyAnswerInfo){
		    	
		    	switch (optionType.intValue()) {
		    	
			        case 1:   saveSubQuestionWithMultipleChoiceWithSingleSelect(surveyQuestion,subAnswerList,surveyAnswerInfo); 
			                  break;
			                 
			        case 2:   saveSubQuestionWithMultipleChoiceWithMultiSelect(surveyQuestion,subAnswerList,surveyAnswerInfo); 
			                  break;
			                   
			        case 5:   saveSubQuestionWithTextBox(surveyQuestion,subAnswerList,surveyAnswerInfo); 
			                  break;
			                 
			        case 6:   saveSubQuestionWithTextArea(surveyQuestion,subAnswerList,surveyAnswerInfo); 
			                  break;   
		        
		        }
		    	
		 }
		 
		 public void saveSubQuestionWithMultipleChoiceWithSingleSelect(SurveyQuestion surveyQuestion,List<QuestionAnswerVO> subAnswerList,SurveyAnswerInfo surveyAnswerInfo){
			for(QuestionAnswerVO subOption : subAnswerList){
				 if(subOption != null && subOption.getOptionId() != null && subOption.getOptionId().trim().length() > 0){
					 SurveyAnswer surveyAnswer = new SurveyAnswer();
					 surveyAnswer.setSurveyQuestion(surveyQuestion);
					 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
					 surveyAnswer.setOption(optionDAO.get(Long.valueOf(subOption.getOptionId().trim())));
					 surveyAnswer.setIsSubOption("true");
					 surveyAnswerDAO.save(surveyAnswer);
					 break;
				 }
			}
		 }
		
		 public void saveSubQuestionWithMultipleChoiceWithMultiSelect(SurveyQuestion surveyQuestion,List<QuestionAnswerVO> subAnswerList,SurveyAnswerInfo surveyAnswerInfo){
			 for(QuestionAnswerVO subOption : subAnswerList){
				 if(subOption != null && subOption.getOptionId() != null && subOption.getOptionId().trim().length() > 0){
					 SurveyAnswer surveyAnswer = new SurveyAnswer();
					 surveyAnswer.setSurveyQuestion(surveyQuestion);
					 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
					 surveyAnswer.setOption(optionDAO.get(Long.valueOf(subOption.getOptionId().trim())));
					 surveyAnswer.setIsSubOption("true");
					 surveyAnswerDAO.save(surveyAnswer);
				 }
			}
		 }
		 
		 public void saveSubQuestionWithTextBox(SurveyQuestion surveyQuestion,List<QuestionAnswerVO> subAnswerList,SurveyAnswerInfo surveyAnswerInfo){
		 	
			 for(QuestionAnswerVO subOption : subAnswerList){
				 if(subOption != null && subOption.getOptionId() != null && subOption.getOptionId().trim().length() > 0 && subOption.getOptionVal() != null && subOption.getOptionVal().trim().length() > 0){
					 SurveyAnswer surveyAnswer = new SurveyAnswer();
					 surveyAnswer.setSurveyQuestion(surveyQuestion);
					 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
					 surveyAnswer.setOption(optionDAO.get(Long.valueOf(subOption.getOptionId().trim())));
					 surveyAnswer.setIsSubOption("true");
					 surveyAnswer.setOptionValue(subOption.getOptionVal());
					 surveyAnswerDAO.save(surveyAnswer);
				 }
			}
		 }
		 
		 public void saveSubQuestionWithTextArea(SurveyQuestion surveyQuestion,List<QuestionAnswerVO> subAnswerList,SurveyAnswerInfo surveyAnswerInfo){
			 for(QuestionAnswerVO subOption : subAnswerList){
				 if(subOption != null && subOption.getOptionId() != null && subOption.getOptionId().trim().length() > 0 && subOption.getOptionVal() != null && subOption.getOptionVal().trim().length() > 0){
					 SurveyAnswer surveyAnswer = new SurveyAnswer();
					 surveyAnswer.setSurveyQuestion(surveyQuestion);
					 surveyAnswer.setSurveyAnswerInfo(surveyAnswerInfo);
					 surveyAnswer.setOption(optionDAO.get(Long.valueOf(subOption.getOptionId().trim())));
					 surveyAnswer.setIsSubOption("true");
					 surveyAnswer.setOptionValue(subOption.getOptionVal());
					 surveyAnswerDAO.save(surveyAnswer);
				 }
			}
		 }
			
			public List<SelectOptionVO> getSurveysForUser(){
				List<SelectOptionVO> surveyList =new ArrayList<SelectOptionVO>();
				
				List<Object[]> allSurveys = surveyDAO.getAllSurveysUsingIsDeleted();
				for(Object[] survey:allSurveys){
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)survey[0]);
					selectOptionVO.setName((String)survey[1]);
					surveyList.add(selectOptionVO);
				}
				
				return surveyList;
			}
			
			public List<SelectOptionVO> deleteSurveyDetails(Long surveyId){
				ResultStatus resultStatus = new ResultStatus();
				List<SelectOptionVO> surveyList = new ArrayList<SelectOptionVO>();
				int value = surveyDAO.updateSurveyDetails(surveyId);
				surveyList = getSurveysForUser();
				
				return surveyList;
			}
				
	public List<SurveyorVO> getServeyorDetails()
	{
		List<SurveyorVO> returnList = new ArrayList<SurveyorVO>();
		List<Surveyor> surveyorList = surveyorDAO.getSurveyorDetails();
		if(surveyorList != null && surveyorList.size() > 0)
		{
			
			for (Surveyor surveyor : surveyorList) {
				SurveyorVO surveyorVO = new SurveyorVO();
				surveyorVO.setSurveyorId(surveyor.getSurveyorId());
				surveyorVO.setSurveyorName(surveyor.getSurveyorProfile().getName());
				returnList.add(surveyorVO);
			}
		}
		return returnList;
	}
}
