package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.model.PartyMeetingSession;

public interface IPartyMeetingSessionDAO extends GenericDao< PartyMeetingSession, Long>{
	public List<Object[]> getSessionDetailsForPartyMeetings(Set<Long> partyMeetingsIds);
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds);
	public List<Object[]> getPartyMeetingSession(Long partyMeetingId);
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds,Date startDate,Date endDate);
	public List<Object[]> getLateTimeList(Set<Long> meetingIds);
	public List<Object[]> getLateTimeListForMultiMeetings(Set<Long> meetingIds);
	public List<Object[]> getLateTimeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,List<Long> levelIdsList,Long partyMeetngGrpId,Long locationId);
	public List<Object[]> getSessionsDetailsByMeetingId(Long meetingId);
	 public Integer updatePartyMeetingSession(Long sessionId);
		
}
