package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Role;

public interface IRoleDAO extends GenericDao<Role, Long>{

	public Role getRoleByRoleType(String roleType);
}
