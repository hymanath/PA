package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.model.Survey;

public class SurveyDAO extends GenericDaoHibernate<Survey, Long> implements ISurveyDAO{

	public SurveyDAO() {
		super(Survey.class);
		// TODO Auto-generated constructor stub
	}

}
