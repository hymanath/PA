package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public class SurveyUserConstituencyDAO extends GenericDaoHibernate<SurveyUserConstituency, Long> implements ISurveyUserConstituencyDAO{

	public SurveyUserConstituencyDAO() {
		super(SurveyUserConstituency.class);
		
	}
	
	public List<Long> getAlreadyAssignedUsers()
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId from SurveyUserConstituency model where model.activeStatus = 'Y'");
		return query.list();
	}
	
	public List<Long> getAlreadyAssignedConstituencies()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId from SurveyUserConstituency model where model.activeStatus = 'Y'");
		return query.list();
	}
}
