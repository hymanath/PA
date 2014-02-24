
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHBoothLeader;

public interface IHHBoothLeaderDAO extends GenericDao<HHBoothLeader, Long>{
	
	public List<HHBoothLeader> getAllLeaderModelByBoothId(Long boothId);
}