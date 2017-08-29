package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityAttribute;
import com.itgrids.partyanalyst.model.DebateParticipantLocation;

public interface IDebateParticipantLocationDAO extends GenericDao<DebateParticipantLocation, Long>{
	public int removeDebateParticipantId(Integer debateId);
	
}
