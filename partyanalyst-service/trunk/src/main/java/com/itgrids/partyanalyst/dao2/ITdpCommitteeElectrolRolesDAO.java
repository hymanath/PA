package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;

public interface ITdpCommitteeElectrolRolesDAO  extends GenericDao<TdpCommitteeElectrolRoles, Long>{
	public List<Object[]> getAllRolesForACadre(Long cadreId);
}
