package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.model.SurveyUser;

public class SurveyUserDAO extends GenericDaoHibernate<SurveyUser, Long> implements ISurveyUserDAO{

	public SurveyUserDAO() 
	{
		super(SurveyUser.class);
	}

}
