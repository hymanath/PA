package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
}
