package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyConstituencyStatusDAO;
import com.itgrids.partyanalyst.model.SurveyConstituencyStatus;

public class SurveyConstituencyStatusDAO extends GenericDaoHibernate<SurveyConstituencyStatus, Long> implements ISurveyConstituencyStatusDAO{

	public SurveyConstituencyStatusDAO() {
		super(SurveyConstituencyStatus.class);
	
	}

}
