package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocations;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyCompletedLocationsDAO extends GenericDaoHibernate<SurveyCompletedLocations, Long>implements
		ISurveyCompletedLocationsDAO {
	
	public SurveyCompletedLocationsDAO()
	{
		super(SurveyCompletedLocations.class);
		
	}
	
	public List<Object[]> getCompletedBoothsDetailsByConstituencyIds(List<Long> constituencyIds)
	{
		
		Query query = getSession().createQuery("select  count(SCL.locationValue),B.constituency.constituencyId,B.constituency.name" +
				" from SurveyCompletedLocations SCL , Booth B " +
				"where SCL.locationValue = B.boothId and SCL.locationScopeId = :boothScopeId " +
				"and B.constituency.constituencyId in(:constituencyIds) and " +
				"SCL.statusId = :dvCompletedStatus  " +
				"group by  B.constituency.constituencyId");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("boothScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameter("dvCompletedStatus", IConstants.DV_COMPLETED_STATUS_ID);
		
		return query.list();
	}
	
	
	public List<Object[]> getBoothsStatusDetailsByConstituencyId(Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count(SCL.locationValue), SCL.surveyCompletedStatus.surveyCompletedStatusId" +
				" from SurveyCompletedLocations SCL  , Booth B " +
				"where " +
				" SCL.locationValue = B.boothId and B.constituency.constituencyId = :constituencyId group by" +
				" SCL.surveyCompletedStatus.surveyCompletedStatusId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getCompletedBoothsCountForPanchayatisByConstituencyId(Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count(SCL.locationValue),B.panchayat.panchayatId,B.panchayat.panchayatName from " +
				"SurveyCompletedLocations SCL  , Booth B where " +
				"B.boothId = SCL.locationValue and B.constituency.constituencyId = :constituencyId group by  B.panchayat.panchayatId ");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public void deleteSurveyCompletedDetailsByLocationValueAndScope(Long locationValue,Long scopeId)
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocations SCL where " +
				"SCL.locationValue = :locationValue and SCL.locationScopeId = :scopeId ");
		
		query.setParameter("locationValue", locationValue);
		query.setParameter("scopeId", scopeId);
		
		 query.list();
	}
	
	
	public List<Long> getBoothsOfConstituecyByStatus(Long constituencyId,Long statusId,Long scopeId){
		Query query = getSession().createQuery(" select model.locationValue " +
				" from SurveyCompletedLocations model, Booth model1 " +
				" where " +
				" model.locationValue = model1.boothId " +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.surveyCompletedStatus.surveyCompletedStatusId = :statusId" +
				" and model.locationScopeId = :scopeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("statusId", statusId);
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
}
