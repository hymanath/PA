package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyCompletedStatusDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedStatus;

public class SurveyCompletedStatusDAO extends GenericDaoHibernate<SurveyCompletedStatus, Long>implements ISurveyCompletedStatusDAO {
	
	public SurveyCompletedStatusDAO() {
		super(SurveyCompletedStatus.class);
	}
	
}
