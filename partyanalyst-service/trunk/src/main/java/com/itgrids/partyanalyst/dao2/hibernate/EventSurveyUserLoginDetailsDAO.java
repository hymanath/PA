package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventSurveyUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.EventSurveyUserLoginDetails;

public class EventSurveyUserLoginDetailsDAO extends GenericDaoHibernate<EventSurveyUserLoginDetails, Long> implements IEventSurveyUserLoginDetailsDAO{

	public EventSurveyUserLoginDetailsDAO() {
		super(EventSurveyUserLoginDetails.class);
		// TODO Auto-generated constructor stub
	}

	
	public List checkUserExistence(Long userId,String imei)
	{
		
		Query query = getSession().createQuery("select model.eventSurveyUserLoginDetailsId from EventSurveyUserLoginDetails model" +
				" where model.eventSurveyUser.eventSurveyUserId = :userId and model.imei = :imei");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		return query.list();
		
	}
}
