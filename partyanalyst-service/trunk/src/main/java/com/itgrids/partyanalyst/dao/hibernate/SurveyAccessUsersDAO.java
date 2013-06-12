package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;
import com.itgrids.partyanalyst.model.SurveyAccessUsers;

public class SurveyAccessUsersDAO extends GenericDaoHibernate<SurveyAccessUsers, Long> implements ISurveyAccessUsersDAO{

	public SurveyAccessUsersDAO() {
		super(SurveyAccessUsers.class);
		// TODO Auto-generated constructor stub
	}

}
