package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.SurveyQuestion;

public class SurveyQuestionDAO extends GenericDaoHibernate<SurveyQuestion, Long> implements ISurveyQuestionDAO{

	public SurveyQuestionDAO() {
		super(SurveyQuestion.class);
		// TODO Auto-generated constructor stub
	}
  
	@SuppressWarnings("unchecked")
	public List<SurveyQuestion> getAllQuestionsForSurvey(Long surveyId){
		return getHibernateTemplate().find("from SurveyQuestion model where model.survey.surveyId = ? ",surveyId);
	}
}
