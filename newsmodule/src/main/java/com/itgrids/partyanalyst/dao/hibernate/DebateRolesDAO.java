package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateRolesDAO;
import com.itgrids.partyanalyst.model.DebateRoles;

public class DebateRolesDAO extends GenericDaoHibernate<DebateRoles, Long> implements IDebateRolesDAO{

	public DebateRolesDAO() {
		super(DebateRoles.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateRoles()
	{
		return getHibernateTemplate().find("select model.debateRolesId ,model.name from DebateRoles model " +
				" where model.isDeleted = 'N'");
	}
	
	public Long checkForExists(String name)
	{
		Query query = getSession().createQuery("select count(model.debateRolesId) from DebateRoles model " +
				" where model.name = :name");
		query.setParameter("name", name);
		return (Long)query.uniqueResult();
	}
}
