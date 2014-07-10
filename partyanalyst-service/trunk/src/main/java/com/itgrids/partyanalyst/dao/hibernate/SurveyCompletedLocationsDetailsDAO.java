package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;

public class SurveyCompletedLocationsDetailsDAO extends GenericDaoHibernate<SurveyCompletedLocationsDetails, Long>  implements ISurveyCompletedLocationsDetailsDAO {
	
	public SurveyCompletedLocationsDetailsDAO() {
		super(SurveyCompletedLocationsDetails.class);
	}
	
	public List<Long> getSurveyCompletedLocationDetails(Long scopeId)
	{
		Query query = getSession().createQuery("select SCLD.locationValue from SurveyCompletedLocationsDetails SCLD " +
				"where SCLD.locationScopeId = :scopeId");
		
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
}
