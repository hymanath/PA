package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrSurveyQuestionOption;



public interface IIvrSurveyQuestionOptionDAO extends GenericDao<IvrSurveyQuestionOption, Long>{
	public List<Object[]> getOptionsForSurveyQuestion(Long surveyQuestionId);
}
