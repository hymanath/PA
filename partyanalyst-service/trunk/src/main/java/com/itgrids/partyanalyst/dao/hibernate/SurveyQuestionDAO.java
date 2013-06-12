package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.SurveyQuestion;

public class SurveyQuestionDAO extends GenericDaoHibernate<SurveyQuestion, Long> implements ISurveyQuestionDAO{

	public SurveyQuestionDAO() {
		super(SurveyQuestion.class);
		// TODO Auto-generated constructor stub
	}

}
