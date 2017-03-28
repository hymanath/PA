package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyDAO;
import com.itgrids.partyanalyst.model.IvrSurvey;


public class IvrSurveyDAO extends GenericDaoHibernate<IvrSurvey, Long> implements IIvrSurveyDAO{

	public IvrSurveyDAO() {
		super(IvrSurvey.class);
	}
}
