package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;

public class PartyMeetingAttendanceDAO extends GenericDaoHibernate<PartyMeetingAttendance,Long> implements IPartyMeetingAttendanceDAO{

	public PartyMeetingAttendanceDAO()
	{
		super(PartyMeetingAttendance.class);
	}
}
