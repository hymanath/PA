package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyAgeWiseDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyAnalysisDTO;
import com.itgrids.partyanalyst.dto.SurveyAnalysisVO;
import com.itgrids.partyanalyst.dto.SurveyInfoVO;
import com.itgrids.partyanalyst.dto.SurveyVO;
import com.itgrids.partyanalyst.dto.SurveyorPersonalInfoVO;
import com.itgrids.partyanalyst.dto.SurveyorVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.SurveyAnswerInfo;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
public interface ISurveyAnalysisService {
	
	public ResultStatus saveSurveyorInfo(Long userId,SurveyorPersonalInfoVO surveyorPersonalInfoVO);
	
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
	
	public List<OptionVO> getSurveyAnalyseDetails(Long surveyId);
	
	public List<SurveyAgeWiseDetailsVO> agewiseSurveyAnalysis(Long surveyId);
	
	public List<SurveyAgeWiseDetailsVO> getGenderWiseSurveyAnalysis(Long surveyId);
	
	public List<SurveyAgeWiseDetailsVO> getOptionWiseSurveyAnalysis(Long surveyId);
	
	public List<SurveyAnalysisVO> getCasteWiseSurveyAnalysis(Long surveyId);
	 
	public List<SurveyAnalysisDTO> getAttributesWiseSurveyAnalysis(Long surveyId);
	
	public SurveyorPersonalInfoVO getSurveyorInfoBasedOnSurveyId(Long surveyorId);
}
