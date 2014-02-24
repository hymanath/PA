
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.model.HouseHoldsFamilyDetails;

public interface IHouseHoldVoterDAO extends GenericDao<HouseHoldVoter, Long>{
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds);
	public List<Object[]> getHouseHoldIdOfFamilyHeadForVoter(String houseNo,Long panchayatId,Long localBodyId);
	public List<Object[]> getHouseHoldIdOfVoter(String houseNo);
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId(Long houseHoldsId);
	
	public Long getHouseHoldIdForVoter(Long voterId);
	public List<HouseHoldsFamilyDetails> getFamilyMembersDetailsByHouseHoldsId(Long houseHoldsId);
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId1(Long houseHoldsId);
	public List<HouseHoldVoter> getHouseHoldVoterDetailsByFamilyMemberId(Long houseHoldFamilyMemberId);
	public List<HouseHoldVoter> checkExistanceOfVoterInHouseHoldsVoter(Long voterId);
}