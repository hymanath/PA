package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserRoles;

public interface IUserRolesDAO extends GenericDao<UserRoles,Long>{

	public List<Object[]> getUserRoles(Long userId);

}
