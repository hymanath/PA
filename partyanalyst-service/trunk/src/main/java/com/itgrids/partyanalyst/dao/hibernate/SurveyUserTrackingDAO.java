package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserTrackingDAO;
import com.itgrids.partyanalyst.model.SurveyUserTracking;

public class SurveyUserTrackingDAO extends GenericDaoHibernate<SurveyUserTracking, Long> implements ISurveyUserTrackingDAO{

	public SurveyUserTrackingDAO() {
		super(SurveyUserTracking.class);
	}
	
	public List<Object[]> getLatLongForUserTracking(Long surveyUserId,Date date)
	{
		Query query = getSession().createQuery("select model.longitude,model.latitude from SurveyUserTracking model where model.surveyUser.surveyUserId = :surveyUserId and model.date =:date");
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("date", surveyUserId);
		return query.list();
	}

}
