package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;

public interface ICustomMessageDAO extends GenericDao<CustomMessage, Long> {

	public List<Object> getRelationShipBetweenTheUsers(List<Long> userIds,Long logedUserId);
	
}
