package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserTrackingDAO;
import com.itgrids.partyanalyst.model.SurveyUserTracking;

public class SurveyUserTrackingDAO extends GenericDaoHibernate<SurveyUserTracking, Long> implements ISurveyUserTrackingDAO{

	public SurveyUserTrackingDAO() {
		super(SurveyUserTracking.class);
	}

}
