package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyQuestionDAO;
import com.itgrids.partyanalyst.model.IvrSurveyQuestion;

public class IvrSurveyQuestionDAO extends GenericDaoHibernate<IvrSurveyQuestion, Long> implements IIvrSurveyQuestionDAO{

	public IvrSurveyQuestionDAO() {
		super(IvrSurveyQuestion.class);
	}

}
