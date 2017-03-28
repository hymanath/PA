package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRole;

public interface ICadreRoleDAO extends GenericDao<CadreRole,Long>{
	
	public List<Object[]> getCadreRoles();
	
	public List<CadreRole> findByRoleDesc(String roleDesc);
}
