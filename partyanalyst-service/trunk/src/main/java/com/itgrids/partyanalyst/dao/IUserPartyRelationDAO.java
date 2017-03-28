package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserPartyRelation;

public interface IUserPartyRelationDAO extends GenericDao<UserPartyRelation, Long>{

	public List<Object[]> getUserPartyRelationDetails(Long userId);
	
	public void deleteUserPartyRelation(Long userPartyRelationId);
	
	public Long checkPartyForUser(Long userId,Long partyId);
	
	public List<Object[]> getPartiesByUser(Long userId);
}
