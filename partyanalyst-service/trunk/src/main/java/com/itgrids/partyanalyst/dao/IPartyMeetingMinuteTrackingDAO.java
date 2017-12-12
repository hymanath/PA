package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingMinuteTracking;

public interface IPartyMeetingMinuteTrackingDAO extends GenericDao<PartyMeetingMinuteTracking, Long> {

	public List<Object[]> getPartyMeetingMomComments(Long partyMeetingMinuteId);
}
