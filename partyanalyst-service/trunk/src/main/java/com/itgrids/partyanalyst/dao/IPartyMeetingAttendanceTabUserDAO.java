package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAttendanceTabUser;

public interface IPartyMeetingAttendanceTabUserDAO extends GenericDao<PartyMeetingAttendanceTabUser,Long>{

	public List<PartyMeeting> getPartyMeetingsOfAttendanceTabUser(Long attendanceTabUserId);
	public List<Object[]> getPartyMeetingsTabUserNameByDistrict(Long districtId);
	public List<Object[]> getPartyMeetingTabUserDetails(Long partyMeetingId);
	public List<Object[]> getTabuserTotaldetailsFromMeetingId(Long partyMeetingId,List<Long>attendanceTabUserIdList);
	public Integer updatePartyMeetingAttendance(Long meetingId);
}
