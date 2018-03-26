package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRwsIvrSurveyDAO;
import com.itgrids.partyanalyst.model.RwsIvrSurvey;

public class RwsIvrSurveyDAO extends GenericDaoHibernate<RwsIvrSurvey, Long> implements IRwsIvrSurveyDAO{

	public RwsIvrSurveyDAO() {
		super(RwsIvrSurvey.class);
	}
}
