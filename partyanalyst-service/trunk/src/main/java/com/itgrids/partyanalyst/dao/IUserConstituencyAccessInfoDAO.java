package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserConstituencyAccessInfo;

public interface IUserConstituencyAccessInfoDAO extends GenericDao<UserConstituencyAccessInfo, Long>{
	
	public List findByUser(Long userId);
	
	public List findByElectionScopeUserState(Long electionScope,Long userId,Long stateId);
	
	public List findByElectionScopeUser(Long electionScope,Long userId);
	
	public void deleteAllAssemblyAccessByScopeStateIdUserId(Long electionScope,Long userId,Long stateId);
	
	public void deleteAllParliamentAccessByScopeUserId(Long electionScope,Long userId);
	
	public List<Object[]> findByElectionTypeUserState(Long electionType,Long userId,Long stateId);
	
	public void deleteAllConstituencyAccessByUserId(Long userId);
	
	public void deleteAllConstituencyAccessByUserIdStateId(Long userId,Long stateId);
	
	public void deleteAllAssemblyAccessByScopeStateIdUserIdDistrictId(Long electionScope,Long userId,Long stateId,Long districtId);
	
	public List<Object[]> getAllUserAccessConstituencies(Long userId);
	
	public List<Object[]> getLocationIdList(Long userId);
	
	public List<Long> getConstituenciesByUser(List<Long> userIds);
	
	public List<Object[]> getConstituencyByUserId(Long userId);

	public List<Object[]> findAssembliesConstituenciesByParliaments(List<Long> parliamentConstituencyIds);
	
}
