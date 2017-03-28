package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyCompletedLocationsDetailsDAO extends GenericDaoHibernate<SurveyCompletedLocationsDetails, Long>  implements ISurveyCompletedLocationsDetailsDAO {
	
	public SurveyCompletedLocationsDetailsDAO() {
		super(SurveyCompletedLocationsDetails.class);
	}
	
	public List<Object[]> getSurveyCompletedLocationDetails(Long scopeId)
	{
		Query query = getSession().createQuery("select SCLD.locationValue,SCLD.surveyUserTypeId from SurveyCompletedLocationsDetails SCLD " +
				"where SCLD.locationScopeId = :scopeId");
		
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
	public List<Object[]> getSurveyCompletedBoothsDetails(List<Long> boothIds)
	{
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select SCLD.locationValue,SCLD.surveyUserTypeId " +
				" from SurveyCompletedLocationsDetails SCLD  where SCLD.locationScopeId = :locationScopeId ");
		
		if(boothIds != null)
			queryString.append("and SCLD.locationValue in(:boothIds)");
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("locationScopeId", IConstants.BOOTH_SCOPE_ID);
		
		if(boothIds != null)
			query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getSurveyCompletedConstituencyDetails()
	{
		Query query = getSession()
				.createQuery(
						"select SCLD.locationValue,SCLD.surveyUserTypeId from SurveyCompletedLocationsDetails SCLD where SCLD.locationScopeId = :locationScopeId");
		
		query.setParameter("locationScopeId", IConstants.CONSTITUENCY_SCOPE_ID);
		
		return query.list();
		
	}
	
	public void deleteBoothCompletionDataOfContituency(List<Long> boothIds)
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocationsDetails SCLD  where SCLD.locationValue in( :boothIds) and " +
				"SCLD.locationScopeId = :scopeId");
		
		query.setParameterList("boothIds", boothIds);
		query.setParameter("scopeId", IConstants.BOOTH_SCOPE_ID);
		
		query.executeUpdate();
	}
	
	public void deleteConstituencyCompletionData()
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocationsDetails SCLD  where  " +
				"SCLD.locationScopeId = :scopeId");
		
		query.setParameter("scopeId", IConstants.CONSTITUENCY_SCOPE_ID);
		
		query.executeUpdate();
	}
	
	public void deleteBoothCompletedLocationDetailsByBoothId(Long boothId,Long userTypeId)
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocationsDetails SCLD  where  " +
				"SCLD.locationValue = :boothId and SCLD.surveyUserType.surveyUsertypeId = :surveyUsertypeId");
		
		query.setParameter("boothId", boothId);		
		query.setParameter("surveyUsertypeId", userTypeId);
		 query.executeUpdate();
	}
	
	public List<Long> getSurveyCompletedCountByConstId(Long scopeId,List<Long> boothIds)
	{
		Query query = getSession().createQuery(" select distinct SCLD.locationValue from SurveyCompletedLocationsDetails SCLD where SCLD.locationScopeId = :locationScopeId and " +
				" SCLD.locationValue  in (:boothIds) ");
		
		query.setParameter("locationScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameterList("boothIds", boothIds);
				
		return query.list();
		
	}
	
	
	public List<Long> getCompletedBoothDetailsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select SCLD.locationValue from SurveyCompletedLocationsDetails SCLD where SCLD.locationScopeId = :locationScopeId and " +
				" SCLD.locationValue  in (:boothIds)");
		
		query.setParameter("locationScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
	}
	
	public List<Long> getVerificationCompletedBoothsDetailsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SCLD.locationValue from SurveyCompletedLocationsDetails SCLD,Booth B " +
				"where " +
				"SCLD.locationValue = B.boothId and B.constituency.constituencyId = :constituencyId and SCLD.surveyUserType.surveyUsertypeId = :verifierUserTypeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("verifierUserTypeId", IConstants.VERIFIER_ROLE_ID);
		
		return query.list();
	}
	
}
