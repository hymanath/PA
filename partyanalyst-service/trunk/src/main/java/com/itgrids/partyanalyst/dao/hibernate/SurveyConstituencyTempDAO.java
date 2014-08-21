package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.model.SurveyConstituencyTemp;

public class SurveyConstituencyTempDAO extends GenericDaoHibernate<SurveyConstituencyTemp, Long> implements ISurveyConstituencyTempDAO{

	public SurveyConstituencyTempDAO() {
		super(SurveyConstituencyTemp.class);
		
	}

}
