package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.model.Attendance;

public class AttendanceDAO extends GenericDaoHibernate<Attendance,Long> implements IAttendanceDAO{

	public AttendanceDAO()
	{
		super(Attendance.class);
	}
}
