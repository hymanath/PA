package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyorProfileDAO;
import com.itgrids.partyanalyst.model.SurveyorProfile;

public class SurveyorProfileDAO extends GenericDaoHibernate<SurveyorProfile, Long> implements ISurveyorProfileDAO{

	public SurveyorProfileDAO() {
		super(SurveyorProfile.class);
		// TODO Auto-generated constructor stub
	}

}
