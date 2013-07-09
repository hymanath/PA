package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserLoginSessionDAO;
import com.itgrids.partyanalyst.model.UserLoginSession;

public class UserLoginSessionDAO extends GenericDaoHibernate<UserLoginSession, Long> implements IUserLoginSessionDAO{

	public UserLoginSessionDAO() {
		super(UserLoginSession.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getSessionIds(Long userId,String sessionId)
	{
		Query query = getSession().createQuery("select model.sessionId from UserLoginSession model,UserLoginDetails model1 where model.sessionId = model1.sessionId and model.sessionId=:sessionId and model1.userId =:userId and model1.logoutTime is null and model1.loginTime is not null");
		query.setParameter("userId", userId);
		query.setParameter("sessionId", sessionId);
		return query.list();
		
		
	}
	
	public Integer deleteSessionsFromUserLoginSession(String sessionId)
	{
		Query query = getSession().createQuery("delete from UserLoginSession model where model.sessionId=:sessionId)");
		query.setParameter("sessionId",sessionId);
		return query.executeUpdate();
	}

}
