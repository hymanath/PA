package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingUserAccessLevel;

public interface IPartyMeetingUserAccessLevelDAO extends GenericDao<PartyMeetingUserAccessLevel,Long>{
	public List<Object[]> getrAccessLevelsOfUserId(Long userId);
}
