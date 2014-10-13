package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeRole;

public interface ICadreCommitteeRoleDAO extends GenericDao<CadreCommitteeRole, Long>{

	
	public List<Long> getCadreCommitteRoleIdBySelection(Long cadreCommitteLevelId,Long cadreRoleId,Long cadreCommitteId);
}
