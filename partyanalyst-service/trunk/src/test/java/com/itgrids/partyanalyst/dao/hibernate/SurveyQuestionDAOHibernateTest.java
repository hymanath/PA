package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.SurveyQuestion;

public class SurveyQuestionDAOHibernateTest extends BaseDaoTestCase  {

	private ISurveyQuestionDAO surveyQuestionDAO;

	public void setSurveyQuestionDAO(ISurveyQuestionDAO surveyQuestionDAO) {
		this.surveyQuestionDAO = surveyQuestionDAO;
	}
	public void testGetAll(){
		surveyQuestionDAO.getAll();
	}
	
	public void testgetAllQuestionsForSurvey()
	{
		List<SurveyQuestion> values = surveyQuestionDAO.getAllQuestionsForSurvey(1l);
		for (SurveyQuestion surveyQuestion : values) {
			System.out.println(surveyQuestion.getDescription());
		}
	}
}
