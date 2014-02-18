
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HouseHoldVoter;

public interface IHouseHoldVoterDAO extends GenericDao<HouseHoldVoter, Long>{
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds);
}