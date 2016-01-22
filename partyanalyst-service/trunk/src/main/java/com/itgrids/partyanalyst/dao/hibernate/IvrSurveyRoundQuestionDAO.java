package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyRoundQuestionDAO;
import com.itgrids.partyanalyst.model.IvrSurveyRoundQuestion;

public class IvrSurveyRoundQuestionDAO extends GenericDaoHibernate<IvrSurveyRoundQuestion, Long> implements IIvrSurveyRoundQuestionDAO{

	public IvrSurveyRoundQuestionDAO() {
		super(IvrSurveyRoundQuestion.class);
		// TODO Auto-generated constructor stub
	}

}
