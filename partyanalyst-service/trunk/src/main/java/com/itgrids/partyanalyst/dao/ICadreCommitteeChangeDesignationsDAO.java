package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;

public interface ICadreCommitteeChangeDesignationsDAO extends GenericDao<CadreCommitteeChangeDesignations, Long>
{
	public List<Object[]> changeDesignationRecordsForAUser(Long requestUserId);
}
