package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyRoundQuestionOptionDAO;
import com.itgrids.partyanalyst.model.IvrSurveyRoundQuestionOption;

public class IvrSurveyRoundQuestionOptionDAO extends GenericDaoHibernate<IvrSurveyRoundQuestionOption, Long> implements IIvrSurveyRoundQuestionOptionDAO{

	public IvrSurveyRoundQuestionOptionDAO() {
		super(IvrSurveyRoundQuestionOption.class);
		// TODO Auto-generated constructor stub
	}

}
