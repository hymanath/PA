package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.UserLoginDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserLoginDetailsDAO extends GenericDaoHibernate<UserLoginDetails, Long> implements IUserLoginDetailsDAO{
	
	public UserLoginDetailsDAO(){
		super(UserLoginDetails.class);
	}
	
	public UserLoginDetails getBySessionId(String sessionId)
	{
		Query query = getSession().createQuery("select model from UserLoginDetails model where model.sessionId = ?");
		query.setParameter(0,sessionId);
		return (UserLoginDetails)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSessionIdsAndLogoutTimeInWithinDates(Date fromDate,Date toDate)
	{
		Object[] params = {fromDate,toDate};
		return getHibernateTemplate().find("select distinct model2.sessionId, max(model.time) from UserTracking model,UserLoginDetails model2 " +
				" where model.sessionId = model2.sessionId and model2.logoutTime is null and Date(model2.loginTime) >= ? and " +
				" Date(model2.loginTime) <= ? group by model.sessionId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSessionIdsAndLogoutTimeOfTodaysUsers(Date today)
	{
		return getHibernateTemplate().find("select distinct model2.sessionId, max(model.time) from UserTracking model,UserLoginDetails model2 " +
				" where model.sessionId = model2.sessionId and Date(model2.loginTime) >= ? group by model.sessionId",today);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getLandingPageAndExitPageForAUser(String sessionId)
	{ 	Object[] params = {sessionId,sessionId};
		return getHibernateTemplate().find("select model.urlName from UserTracking model where (model.time =(select max (model2.time) from UserTracking model2 where model2.sessionId = ?)) or " +
				" (model.time =(select min (model3.time) from UserTracking model3 where model3.sessionId = ? )) order by model.time desc " ,params);
	}
	public List<String> getAllActiveUsersSessionIds(Long userId)
	{
		return getHibernateTemplate().find("select distinct model.sessionId from UserLoginDetails model where model.loginTime is not null and model.logoutTime is null and model.userId = ? order by model.loginTime desc",userId);
		
	}
	
}
