package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrSurveyCandidateQuestion;

public interface IIvrSurveyCandidateQuestionDAO extends GenericDao<IvrSurveyCandidateQuestion, Long> {
	public Long getIvrSurveyCountByCandiate(Long candidateId,Long cadreId);
	public List<Long> getIvrSurveyQuestionsByCandiate(Long candidateId,Long cadreId);
	public List<Object[]> getIvrSurveyQuestionsOptionsByCandiate(List<Long> questionIds);
	
	public List<Object[]> getIvrSurveyScopeIdsByCandidate(Long candidateId,Long cadreId);
	public List<Object[]> getLocationScopeWiseQuestionOptions(Long surveyId,Long questionId,String locationType,Long locationVal);
}
