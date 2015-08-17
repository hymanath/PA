package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingLevel;

public interface IPartyMeetingLevelDAO extends GenericDao<PartyMeetingLevel,Long>{
	public List<Object[]> getPartyMeetingLevels();
}
