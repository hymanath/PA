package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAttendanceTabUser;

public class PartyMeetingAttendanceTabUserDAO extends GenericDaoHibernate<PartyMeetingAttendanceTabUser,Long> implements IPartyMeetingAttendanceTabUserDAO{

	public PartyMeetingAttendanceTabUserDAO()
	{
		super(PartyMeetingAttendanceTabUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyMeeting> getPartyMeetingsOfAttendanceTabUser(Long attendanceTabUserId)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting FROM PartyMeetingAttendanceTabUser model where " +
				" model.isDeleted = 'N' and model.attendanceTabUser.attendanceTabUserId = :attendanceTabUserId and model.attendanceTabUser.isEnabled = 'Y'");
		query.setParameter("attendanceTabUserId",attendanceTabUserId);
		return query.list(); 
	}
}
