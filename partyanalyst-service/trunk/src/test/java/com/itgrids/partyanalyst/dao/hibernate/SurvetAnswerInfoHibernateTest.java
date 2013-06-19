package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyAnswerInfoDAO;

public class SurvetAnswerInfoHibernateTest extends BaseDaoTestCase{
	
	private ISurveyAnswerInfoDAO surveyAnswerInfoDAO;

	public ISurveyAnswerInfoDAO getSurveyAnswerInfoDAO() {
		return surveyAnswerInfoDAO;
	}

	public void setSurveyAnswerInfoDAO(ISurveyAnswerInfoDAO surveyAnswerInfoDAO) {
		this.surveyAnswerInfoDAO = surveyAnswerInfoDAO;
	}
	
	

}
