package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyInfoVO;
import com.itgrids.partyanalyst.dto.SurveyVO;
import com.itgrids.partyanalyst.dto.SurveyorVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.SurveyAnswerInfo;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
public interface ISurveyAnalysisService {
	
	public ResultStatus saveSurveyorInfo(String name,String age,String mobileNo,String phoneNo,String email,int qualification,int occupation,int caste,Long state,Long district,Long tehsil,Long township,String gender);
	
	public List<SelectOptionVO> getStatesList();
	
	public List<SelectOptionVO> getDistricts(Long stateId);
	
	public ResultStatus saveQuestion(final List<QuestionsOptionsVO> questionsOptionsList);
	
	public ResultStatus saveQuestionForMultipleText(final List<QuestionsOptionsVO> questionsOptionsList);
	
	public ResultStatus savesurveyDetails(final String name,final String desc,final Long scopeVal,final Long stateId,final Long districtId,final Long constId,final Long mandalId,final Long userId,final String consType);
	
	public List<SelectOptionVO>  getOptionTypes();
	
	public List<SurveyVO> getSurveyDetailsBySurveyId(Long surveyId);
	
	public List<SelectOptionVO> getAllCastesForUser(Long userId);
	
	public List<VoterVO> getVoterDetailsBasedOnVoterId(String VoterCardId,Long userId);
	
	public ResultStatus saveSurveyDetails(final SurveyInfoVO surveyInfoVO,final Long userId,final List<QuestionAnswerVO> questionAnswerVO);	
	
	public String getSurveyForm(Long surveyId);
	
	public boolean saveSurveyForm(List<QuestionAnswerVO> questionAnswerVOList,SurveyAnswerInfo surveyAnswerInfo);
	
	public List<SurveyorVO> getServeyorDetails();
	
	public List<SelectOptionVO> getSurveysForUser();
	
	public List<SelectOptionVO> deleteSurveyDetails(Long surveyId);
}
