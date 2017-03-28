package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommittee;

public interface ICadreCommitteeDAO extends GenericDao<CadreCommittee, Long>{
	public List<Object[]> getAllCadreCommittees();
}
