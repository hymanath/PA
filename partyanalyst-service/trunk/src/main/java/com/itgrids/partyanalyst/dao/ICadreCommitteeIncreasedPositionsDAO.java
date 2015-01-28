package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public interface ICadreCommitteeIncreasedPositionsDAO extends GenericDao<CadreCommitteeIncreasedPositions, Long>{
	public Long getAllRecordsCount();
	public List<Object[]> getAllRecordsCount(int stIndex, int endIndex);
	public List<Object[]> getRequestDetailsForAUser(Long requestUserId);
}
