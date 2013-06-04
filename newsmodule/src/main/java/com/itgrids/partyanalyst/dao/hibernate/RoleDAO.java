package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.model.Role;

public class RoleDAO extends GenericDaoHibernate<Role, Long> implements IRoleDAO{

	public RoleDAO() {
		super(Role.class);
	}
	
	/*public Role getRoleByRoleType(String roleType)
	{
		Query query = getSession().createQuery("select model from Role model where model.roleType = ?");
		query.setParameter(0,roleType);
		
		return (Role)query.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	public List<Role> getRoleType()
	{
		return getHibernateTemplate().find(" from Role model");
	}*/
}
