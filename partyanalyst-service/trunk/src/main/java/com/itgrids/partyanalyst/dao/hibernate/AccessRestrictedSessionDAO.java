package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IAccessRestrictedSessionDAO;
import com.itgrids.partyanalyst.model.AccessRestrictedSession;

public class AccessRestrictedSessionDAO extends GenericDaoHibernate<AccessRestrictedSession, Long> implements IAccessRestrictedSessionDAO{

	public AccessRestrictedSessionDAO() {
		super(AccessRestrictedSession.class);
		
	}
	
	public String verifySessionIdAvailability(String sessionId)
	{
		Query query = getSession().createQuery("select model.sessionId from AccessRestrictedSession model where model.sessionId = :sessionId ");
		query.setParameter("sessionId", sessionId);
		return (String)query.uniqueResult();
	}
	
	public Integer deleteSessionsFromAccessRestrictedSession(String sessionId)
	{
		Query query = getSession().createQuery("delete from AccessRestrictedSession model where model.sessionId=:sessionId)");
		query.setParameter("sessionId",sessionId);
		return query.executeUpdate();
	}

}
