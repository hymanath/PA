package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.PartyMeetingSession;

public interface IPartyMeetingSessionDAO extends GenericDao< PartyMeetingSession, Long>{
	public List<Object[]> getSessionDetailsForPartyMeetings(Set<Long> partyMeetingsIds);
}
