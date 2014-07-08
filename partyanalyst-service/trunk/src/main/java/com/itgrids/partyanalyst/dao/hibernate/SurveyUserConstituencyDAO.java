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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencyList(){
		
		return getHibernateTemplate().find(" select model.constituency.constituencyId , model.constituency.name from SurveyUserConstituency model order by model.constituency.name asc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencyLeadersList(Long constituencyId){
		
		return getHibernateTemplate().find("select model.surveyUser.surveyUserId, model.surveyUser.firstName,model.surveyUser.lastName from SurveyUserConstituency model " +
				" where model.constituency.constituencyId = "+constituencyId+" order by model.surveyUser.firstName asc");
	}
	
}
