package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public class SurveyUserConstituencyDAO extends GenericDaoHibernate<SurveyUserConstituency, Long> implements ISurveyUserConstituencyDAO{

	public SurveyUserConstituencyDAO() {
		super(SurveyUserConstituency.class);
		
	}

}
