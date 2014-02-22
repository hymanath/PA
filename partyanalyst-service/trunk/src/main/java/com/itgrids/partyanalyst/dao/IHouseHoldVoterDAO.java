
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HouseHoldVoter;

public interface IHouseHoldVoterDAO extends GenericDao<HouseHoldVoter, Long>{
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds);
	public List<Object[]> getHouseHoldIdOfFamilyHeadForVoter(String houseNo);
	public List<Object[]> getHouseHoldIdOfVoter(String houseNo);
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId(Long houseHoldsId);
	
	public Long getHouseHoldIdForVoter(Long voterId);
}