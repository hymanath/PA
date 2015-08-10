package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.AttendanceTabUser;

public class AttendanceTabUserDAO extends GenericDaoHibernate<AttendanceTabUser,Long> implements IAttendanceTabUserDAO{

	public AttendanceTabUserDAO()
	{
		super(AttendanceTabUser.class);
	}
}
