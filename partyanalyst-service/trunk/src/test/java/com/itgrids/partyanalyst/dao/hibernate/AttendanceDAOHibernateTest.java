package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;

public class AttendanceDAOHibernateTest extends BaseDaoTestCase{

	private IAttendanceDAO attendanceDAO;

	public void setAttendanceDAO(IAttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}
	public void test()
	{
		attendanceDAO.getAll();
	}
}
