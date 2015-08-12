package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingUser;

public interface IPartyMeetingUserDAO extends GenericDao<PartyMeetingUser,Long>{
	public List<Object[]> getTheMeetingLevelDetails(Long userId);
}
