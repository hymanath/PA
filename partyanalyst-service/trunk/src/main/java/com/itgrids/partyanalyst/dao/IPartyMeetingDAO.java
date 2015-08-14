package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeeting;

public interface IPartyMeetingDAO extends GenericDao<PartyMeeting,Long>{
	public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,Long stateId,Long districtId,Long constituencyId,Long mandalId,Long townId,Long divisonId,Long villageId,Long wardId,Date startDate,Date endDate);
	public List<Object[]> getPartyMeetingDetailsByMeetingIdList(List<Long> patyMeetingIdsList);
}
