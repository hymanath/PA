package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerifyAccessUsersDAO;
import com.itgrids.partyanalyst.model.VerifyAccessUsers;

public class VerifyAccessUsersDAO extends GenericDaoHibernate<VerifyAccessUsers, Long> implements IVerifyAccessUsersDAO {

	public VerifyAccessUsersDAO() {
		super(VerifyAccessUsers.class);
	}

	
	public List<String> getUserStatus(List<Long> userIds)
	{
		Query query = getSession().createQuery("select model.status from VerifyAccessUsers model where model.userId in(:userIds)");
		query.setParameterList("userIds", userIds);
		return query.list();
	}
}
