package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Role;

public interface IRoleDAO extends GenericDao<Role, Long>{
	
	public List<Object[]> getAllEntitlementsByRoleId(Long roleId);
	
	public List<Object[]> getAllRoles();
}
