package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyAnswer;


public class IvrSurveyAnswerDAO extends GenericDaoHibernate<IvrSurveyAnswer, Long> implements IIvrSurveyAnswerDAO{

	public IvrSurveyAnswerDAO() {
		super(IvrSurveyAnswer.class);
	}

}
