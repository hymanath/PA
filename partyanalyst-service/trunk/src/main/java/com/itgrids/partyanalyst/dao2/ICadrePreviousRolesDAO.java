package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadrePreviousRoles;

public interface ICadrePreviousRolesDAO extends GenericDao<CadrePreviousRoles, Long>{

	public Integer inActiveCadreRollesDetailsById(List<Long> tdpCadreIdList);
	
	public List<Object[]> getexistingRolesForTdpCadreByTdpCadreId(Long tdpCadreId);
	
	public List<Object[]> getexistingRolesForTdpCadreByTdpCadreIdForCommittee(Long tdpCadreId);
}
