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

	public Long getSurveyWMCompletedCountByConstId(Long scopeId,List<Long> boothIds){
		
		Query query = getSession().createQuery(" select count(distinct WMCLD.locationValue) from WebMonitorCompletedLocationsDetails WMCLD where WMCLD.locationScopeId = :scopeId and " +
				" WMCLD.locationValue  in (:boothIds) ");
		
		query.setParameter("locationScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameterList("boothIds", boothIds);
				
		return (Long) query.uniqueResult();
		
	}
}
