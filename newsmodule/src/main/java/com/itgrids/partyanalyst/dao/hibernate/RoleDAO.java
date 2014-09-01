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
	
	public List<Object[]> getAllEntitlementsByRoleId(Long roleId){
		Query query = getSession().createQuery("select entitlement.entitlementId,entitlement.name from Entitlement entitlement, Role role  where " +
				" role.projectType=entitlement.projectType and role.roleId = :roleId and role.entitlement.entitlementId != entitlement.entitlementId ");
		query.setParameter("roleId",roleId);
		
		return query.list();
	}
	
	public List<Object[]> getAllRoles(){
		Query query = getSession().createQuery("select model.roleId,model.roleType from Role model ");
		
		return query.list();
	}
}
