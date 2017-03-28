package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyCompletedConstituencyStatusDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedConstituencyStatus;

public class SurveyCompletedConstituencyStatusDAO extends GenericDaoHibernate<SurveyCompletedConstituencyStatus, Long> implements ISurveyCompletedConstituencyStatusDAO{

	public SurveyCompletedConstituencyStatusDAO() {
		super(SurveyCompletedConstituencyStatus.class);
	}

}
