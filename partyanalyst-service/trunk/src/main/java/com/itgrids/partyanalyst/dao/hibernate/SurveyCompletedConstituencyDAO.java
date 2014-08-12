package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCompletedConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedConstituency;

public class SurveyCompletedConstituencyDAO extends GenericDaoHibernate<SurveyCompletedConstituency, Long> implements
		ISurveyCompletedConstituencyDAO {
	
	public SurveyCompletedConstituencyDAO() {
		super(SurveyCompletedConstituency.class);
	}
	
	public List<Object[]> getSurveyCompletedConstituencyDetails(){
		
		Query query = getSession().createQuery("select SCC.constituency.constituencyId,SCC.constituency.name ," +
				"SCC.surveyCompletedConstituencyStatus.surveyCompletedConstituencyStatusId,SCC.surveyCompletedConstituencyStatus.status" +
				",SCC.queryComment from SurveyCompletedConstituency SCC");
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyCompletionStatusByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select SCC.surveyCompletedConstituencyStatus.status ,SCC.queryComment from " +
				"SurveyCompletedConstituency SCC where  SCC.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
}
