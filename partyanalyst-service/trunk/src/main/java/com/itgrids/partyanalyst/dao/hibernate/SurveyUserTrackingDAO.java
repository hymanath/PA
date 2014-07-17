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
		Query query = getSession().createQuery("select distinct model.longitude,model.latitude,model.date from SurveyUserTracking model where model.surveyUser.surveyUserId = :surveyUserId " +
				" and date(model.date) =:date and model.longitude != '0' and model.latitude != '0' order by model.date asc");
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("date", date);
		return query.list();
	}
	public void sessionFlush()
	{
		 getSession().flush();
	}
	
	public Long checkWhetherRecordExistingOrNot(String uuid,String imei, Date date)
	{
		Query query = getSession().createQuery("select distinct model.surveyUserTrackingId from SurveyUserTracking model where model.imeiNo = :imeiNo and model.uniqueUUID=:uuid   " +
				" and date(model.date) = :date  group by model.surveyUserTrackingId ");
		query.setParameter("imeiNo", imei);
		query.setParameter("uuid", uuid);
		query.setParameter("date", date);
		return (Long) query.uniqueResult();
		
	}
}
