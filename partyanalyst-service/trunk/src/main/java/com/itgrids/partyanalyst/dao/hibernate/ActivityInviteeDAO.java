package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.model.ActivityInvitee;

public class ActivityInviteeDAO extends
		GenericDaoHibernate<ActivityInvitee, Long> implements
		IActivityInviteeDAO {
	public ActivityInviteeDAO() {
		super(ActivityInvitee.class);

	}
	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId){
		Query query = getSession().createQuery(" select distinct model.activityScope.activityScopeId,count(distinct model.activityDate)," +
				" model.activityDate, model.day " +
				" from ActivityInvitee model where model.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.activityScope.isDeleted = 'N' ");
query.setParameter("tdpCadreId", tdpCadreId);
return query.list();
	}
	
}
