package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.UserLoginDetails;

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
	public List<Object[]> getUserLoginLogoutDtls(Date startDate, Date endDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
				" ULD.user_id as userId, " +
				" min(ULD.login_time) as minLoginTime, " +
				" max(ULD.logout_time) as maxLogoutTime, " +
				" SEC_TO_TIME(sum(TIME_TO_SEC(TIMEDIFF(ULD.logout_time,ULD.login_time)))) as workingHour " +
				" from " +
				" user_login_details ULD " +
				" where " );
		if(startDate != null && endDate != null){
			queryStr.append(" Date(login_time) between  :startDate and :endDate ");
		}
		queryStr.append(" group by ULD.user_id ");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("userId", Hibernate.LONG);
		query.addScalar("minLoginTime", Hibernate.STRING);
		query.addScalar("maxLogoutTime", Hibernate.STRING);
		query.addScalar("workingHour", Hibernate.STRING);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	public List<Object[]> getAttendanceForMultiDate(Date fromDate, Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ULD.user_id as userId, count(distinct Date(ULD.login_time)) as count " +
				" from user_login_details ULD  " +
				" where ");
				if(fromDate != null && toDate != null){
					queryStr.append(" Date(login_time) between  :fromDate and :toDate ");
				}
		queryStr.append(" group by ULD.user_id ");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("userId", Hibernate.LONG);
		query.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	
}
