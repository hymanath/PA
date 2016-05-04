package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrSurveyCandidateQuestion;

public interface IIvrSurveyCandidateQuestionDAO extends GenericDao<IvrSurveyCandidateQuestion, Long> {
	public Long getIvrSurveyCountByCandiate(Long candidateId);
	public List<Long> getIvrSurveyQuestionsByCandiate(Long candidateId);
	public List<Object[]> getIvrSurveyQuestionsOptionsByCandiate(List<Long> questionIds);
	

}
