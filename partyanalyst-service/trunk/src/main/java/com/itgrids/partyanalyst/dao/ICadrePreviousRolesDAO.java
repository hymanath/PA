package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadrePreviousRoles;

public interface ICadrePreviousRolesDAO extends GenericDao<CadrePreviousRoles, Long>{

	public Integer inActiveCadreRollesDetailsById(Long tdpCadreId);
}
