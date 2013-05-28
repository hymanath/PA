package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomVoter;

public interface ICustomVoterDAO extends GenericDao<CustomVoter,Long>{
	
	public List<Object[]> getVoterGroupNamesByVoterIdsList(List<Long> voterIdsList);
	public void removeCustomVoterDetails(Long customVoterId);
	public List<CustomVoter> getCustomVoterByVoterIdAndUserId(Long voterId , Long customGroupId);
	public List<Long> getCustomGroupIdByVoterIdAndUserId(Long voterId , Long userId);
	public List<Object[]> getAllVotersGroups(List<Long> voterIds , Long userId);
	public List<Long> getCustomVoterIdByVoterIdAndUserId(Long voterId , Long userId);


		
}
