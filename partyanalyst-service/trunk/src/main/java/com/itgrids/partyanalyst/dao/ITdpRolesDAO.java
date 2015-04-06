package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpRoles;

public interface ITdpRolesDAO  extends GenericDao<TdpRoles, Long>{
	public List<Object[]> getRoles();

}
