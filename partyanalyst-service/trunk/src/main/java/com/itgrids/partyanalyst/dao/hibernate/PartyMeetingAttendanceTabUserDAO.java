package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendanceTabUser;

public class PartyMeetingAttendanceTabUserDAO extends GenericDaoHibernate<PartyMeetingAttendanceTabUser,Long> implements IPartyMeetingAttendanceTabUserDAO{

	public PartyMeetingAttendanceTabUserDAO()
	{
		super(PartyMeetingAttendanceTabUser.class);
	}
}
