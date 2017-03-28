package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserActivityScopeDAO;
import com.itgrids.partyanalyst.model.UserActivityScope;

public class UserActivityScopeDAO extends GenericDaoHibernate<UserActivityScope, Long> implements IUserActivityScopeDAO{

	public UserActivityScopeDAO() {
		super(UserActivityScope.class);
		
	}
	
	public List<Object[]> getUserActivityDetailsByUserId(Long userId){
		
		Query query = getSession().createQuery(" select model.userActivityScopeId," +
				" model.user.userId," +
				" model.activityScope.activityScopeId, " +
				" model.activityScope.activity.activityId, " +
				" model.activityScope.activity.activityName," +
				" model.activityScope.activityLevel.activityLevelId," +
				" model.scopeValue, " +
				" model.activityScope.activityLevel.level," +
				" model.activityScope.startDate, " +
				" model.activityScope.endDate " +
				" from UserActivityScope model " +
				" where model.user.userId = :userId " +
				" and model.isActive = 'Y' ");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}

}
