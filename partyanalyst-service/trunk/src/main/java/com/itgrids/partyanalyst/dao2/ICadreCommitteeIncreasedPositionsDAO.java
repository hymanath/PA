package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public interface ICadreCommitteeIncreasedPositionsDAO extends GenericDao<CadreCommitteeIncreasedPositions, Long>{
	public Long getAllRecordsCount();
	public List<Object[]> getAllRecordsForApproval(int stIndex, int endIndex);
	public int updateStatus(String status,Date updatedTime, Long increasedPosId, Long approveCount);
	public List<Object[]> getAllRecordsCountStatusWise();
	public List<Object[]> getRequestDetailsForAUser(Long requestUserId);
	
	public List<Object[]> statusForChangeDesignationsApproval();
}
