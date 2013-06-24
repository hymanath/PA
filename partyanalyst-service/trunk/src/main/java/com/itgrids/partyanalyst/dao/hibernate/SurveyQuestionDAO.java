package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.SurveyQuestion;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyQuestionDAO extends GenericDaoHibernate<SurveyQuestion, Long> implements ISurveyQuestionDAO{

	public SurveyQuestionDAO() {
		super(SurveyQuestion.class);
		// TODO Auto-generated constructor stub
	}
  
	@SuppressWarnings("unchecked")
	public List<SurveyQuestion> getAllQuestionsForSurvey(Long surveyId){
		return getHibernateTemplate().find("from SurveyQuestion model where model.survey.surveyId = ? ",surveyId);
	}
	public List<Long> getSurveyQuestionIdsById(Long surveyId)
	{
		return getHibernateTemplate().find("select model.surveyQuestionId from SurveyQuestion model where model.survey.surveyId = ? and model.isAnalyse='false' and model.optionType.optionTypeId =1 ",surveyId);
	}
	/**
	 * This DAO is used for getting Question for selected survey
	 * @param Long surveyId
	 * @return List<Long>
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getSurveyQuestionsForSelectedSurvey(Long surveyId)
	{
		return getHibernateTemplate().find("select model.surveyQuestionId from SurveyQuestion model " +
				" where model.survey.surveyId = ? and model.isAnalyse = 'true' " +
				" and model.optionType.optionTypeId = 1",surveyId);
	}
}
