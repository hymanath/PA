package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;

public class AttendanceTabUserDAOHibernateTest extends BaseDaoTestCase{

	private IAttendanceTabUserDAO attendanceTabUserDAO;

	public void setAttendanceTabUserDAO(IAttendanceTabUserDAO attendanceTabUserDAO) {
		this.attendanceTabUserDAO = attendanceTabUserDAO;
	}
	
	public void test()
	{
		attendanceTabUserDAO.getAll();
	}
}
