package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRoles;

public interface ICadreRolesDAO extends GenericDao<CadreRoles, Long>{
	public List<Object[]> getAllCadreRoles();
}
