
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHBoothLeader;

public interface IHHBoothLeaderDAO extends GenericDao<HHBoothLeader, Long>{
	
	public List<HHBoothLeader> getAllLeaderModelByBoothId(Long boothId);
	public List getLeaderIdForBoothId(String name,Long boothId);
	public List<Object[]> getBoothsOfLeader(Long leaderId);
	public List<Object[]> getLeadersOfConstituency(Long constituencyId);
	public Integer deleteLeaderWithBooths(List<Long> boothIds,Long leaderId);
	
	public List<Object[]> getConstituenciesOfHouseHolds();
	//public List<Object[]> getActiveLeadersOfConstituency(Long constituencyId);	
}