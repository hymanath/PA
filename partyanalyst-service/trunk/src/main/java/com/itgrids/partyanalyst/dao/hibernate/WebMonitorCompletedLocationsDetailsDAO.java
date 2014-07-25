package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.model.WebMonitorCompletedLocationsDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class WebMonitorCompletedLocationsDetailsDAO extends GenericDaoHibernate<WebMonitorCompletedLocationsDetails, Long> implements IWebMonitorCompletedLocationsDetailsDAO{
	
	public WebMonitorCompletedLocationsDetailsDAO() {
		super(WebMonitorCompletedLocationsDetails.class);
	}

	public List<Long> getSurveyWMCompletedCountByConstId(Long scopeId,List<Long> boothIds){
		
		Query query = getSession().createQuery(" select distinct WMCLD.locationValue from WebMonitorCompletedLocationsDetails WMCLD where WMCLD.locationScopeId = :locationScopeId and " +
				" WMCLD.locationValue  in (:boothIds) ");
		
		query.setParameter("locationScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameterList("boothIds", boothIds);
				
		return query.list();
		
	}
	
	public List<Long> getPanchayatBoothsByConstituencyId(Long scopeId)
	{
	
		
		Query query = getSession().createQuery("select distinct model.locationValue from WebMonitorCompletedLocationsDetails model" +
				" where model.locationScopeId = :scopeId");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}

	public void deleteBoothCompletedLocationDetailsByBoothId(Long boothId)
	{

		Query query = getSession().createQuery("delete from WebMonitorCompletedLocationsDetails WCLD  where  " +
				"WCLD.locationValue = :boothId");
		
		query.setParameter("boothId", boothId);		
		 query.executeUpdate();
		
	}
	
	public List<Long> getWebMontringCount(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.locationValue from WebMonitorCompletedLocationsDetails model,Booth model1 where model.locationValue = model1.boothId and " +
				" model.locationScopeId = 9 and model1.constituency.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);	
		return query.list();
	}
}
