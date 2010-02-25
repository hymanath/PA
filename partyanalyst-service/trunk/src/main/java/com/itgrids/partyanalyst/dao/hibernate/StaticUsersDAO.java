package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.model.StaticUsers;

public class StaticUsersDAO extends GenericDaoHibernate<StaticUsers, Long> implements
		IStaticUsersDAO {

	public StaticUsersDAO()
	{
		super(StaticUsers.class);
	}
	@SuppressWarnings("unchecked")
	public List<StaticUsers> findByMobileNo(String mobileNumber) {
		
		System.out.println("inside dAO...");
		return getHibernateTemplate().find("from StaticUsers model where model.mobileNumber= ?",mobileNumber);
	}


}
