package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeLevel;

public interface ICadreCommitteeLevelDAO extends GenericDao<CadreCommitteeLevel, Long>{
	public List<Object[]> getAllCadreCommitteeLevels();
}
