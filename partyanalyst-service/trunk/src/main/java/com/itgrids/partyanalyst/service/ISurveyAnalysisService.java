package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ISurveyAnalysisService {
	public ResultStatus saveQuestion(final List<QuestionsOptionsVO> questionsOptionsList);
	
	public ResultStatus saveQuestionForMultipleText(final List<QuestionsOptionsVO> questionsOptionsList);
	
	public ResultStatus savesurveyDetails(final String name,final String desc,final Long scopeVal,final Long stateId,final Long districtId,final Long constId,final Long mandalId,final Long userId,final String consType);
	
	public List<SelectOptionVO>  getOptionTypes();
}
