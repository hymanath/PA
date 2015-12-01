package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;

public class ActivityLocationInfoDAO extends GenericDaoHibernate<ActivityLocationInfo, Long> implements IActivityLocationInfoDAO{

	public ActivityLocationInfoDAO() {
		super(ActivityLocationInfo.class);
		
	}

	
	public List<Long> getUpdatedLocationsListForScope(Long activityScopeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationValue from ActivityLocationInfo model where " +
				"model.activityScopeId =:activityScopeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityScopeId", activityScopeId);
		
		return query.list();
	}
	
	
}
