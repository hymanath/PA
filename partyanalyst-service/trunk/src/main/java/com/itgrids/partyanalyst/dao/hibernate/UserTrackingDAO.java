package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.model.UserTracking;

public class UserTrackingDAO extends GenericDaoHibernate<UserTracking, Long> implements IUserTrackingDAO {

	public UserTrackingDAO() {
		super(UserTracking.class);
	}
	

	@SuppressWarnings("unchecked")
	public Object getUniqueVisitorsBetweenDates(Date fromDate, Date toDate, String userType){
		StringBuilder query = new StringBuilder();
		query.append("select count(distinct model.sessionId) from UserTracking model where DATE(model.time) between ? and ? and model.userType");
		
		if(userType==null)
			query.append(" is null");
		else{
			query.append("= ?");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, fromDate);
		queryObject.setParameter(1, toDate);

		if(userType!=null)
			queryObject.setParameter(2, userType);
		
  		return queryObject.uniqueResult();
	}	
	
	public List<Object> getLoginTimeBetweenDates(Date fromDate, Date toDate, String userType){
		StringBuilder query = new StringBuilder();
		if(userType==null)
			query.append("select model.sessionId, min(model.time) from UserTracking model where DATE(model.time) between ? and ? and model.userType is null group by model.sessionId");
		else{
			query.append("select model.sessionId, model.loginTime from UserLoginDetails model where DATE(model.loginTime) between ? and ? and model.userType=?");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, fromDate);
		queryObject.setParameter(1, toDate);
		
		if(userType!=null)
			queryObject.setParameter(2, userType);	
  		return queryObject.list();
	}
	
	public List<Object> getLogoutTimeBetweenDates(Date fromDate, Date toDate, String userType){
		StringBuilder query = new StringBuilder();
		if(userType==null)
			query.append("select model.sessionId, max(model.time) from UserTracking model where DATE(model.time) between ? and ? and model.userType is null group by model.sessionId");
		else{
			query.append("select model.sessionId, model.logoutTime from UserLoginDetails model where DATE(model.loginTime) between ? and ? and model.userType=?");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, fromDate);
		queryObject.setParameter(1, toDate);
		
		if(userType!=null)
			queryObject.setParameter(2, userType);	
  		return queryObject.list();		
	}
	
	public Object getNoOfPagesAccessedBetweenDates(Date fromDate, Date toDate, String userType){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userTrackingId) from UserTracking model where DATE(model.time) between ? and ? and model.userType");
		
		if(userType==null)
			query.append(" is null");
		else{
			query.append("= ?");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, fromDate);
		queryObject.setParameter(1, toDate);

		if(userType!=null)
			queryObject.setParameter(2, userType);
		
  		return queryObject.uniqueResult();
	}	
	
}
