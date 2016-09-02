package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateRoles;

public interface IDebateRolesDAO extends GenericDao<DebateRoles, Long>{

	public List<Object[]> getDebateRoles();
	
	public Long checkForExists(String name);
	
	public List<Object[]> getDebateRolesNew();
}
