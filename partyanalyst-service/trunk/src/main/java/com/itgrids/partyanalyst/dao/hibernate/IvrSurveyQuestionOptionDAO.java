package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyQuestionOptionDAO;
import com.itgrids.partyanalyst.model.IvrSurveyQuestionOption;

public class IvrSurveyQuestionOptionDAO extends GenericDaoHibernate<IvrSurveyQuestionOption,Long> implements IIvrSurveyQuestionOptionDAO{

	public IvrSurveyQuestionOptionDAO()
	{
		super(IvrSurveyQuestionOption.class);
	}
	}
