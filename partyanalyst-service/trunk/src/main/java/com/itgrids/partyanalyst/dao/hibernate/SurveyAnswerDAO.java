package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyAnswerDAO;
import com.itgrids.partyanalyst.model.SurveyAnswer;

public class SurveyAnswerDAO extends GenericDaoHibernate<SurveyAnswer, Long> implements ISurveyAnswerDAO{

	public SurveyAnswerDAO() {
		super(SurveyAnswer.class);
		// TODO Auto-generated constructor stub
	}

}
