package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveySurveyorTypeDAO;
import com.itgrids.partyanalyst.model.SurveySurveyorType;

public class SurveySurveyorTypeDAO extends GenericDaoHibernate<SurveySurveyorType, Long> implements ISurveySurveyorTypeDAO {

	public SurveySurveyorTypeDAO() {
		super(SurveySurveyorType.class);
	}

}
