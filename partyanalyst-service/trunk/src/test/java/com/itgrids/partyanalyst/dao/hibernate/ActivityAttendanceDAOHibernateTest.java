package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;

public class ActivityAttendanceDAOHibernateTest extends BaseDaoTestCase{

	private IActivityAttendanceDAO activityAttendanceDAO;

	public void setActivityAttendanceDAO(
			IActivityAttendanceDAO activityAttendanceDAO) {
		this.activityAttendanceDAO = activityAttendanceDAO;
	}
	
	public void test()
	{
		List<Object[]> list = activityAttendanceDAO.getActivityScopeAndLevels(10864861l);
		System.out.println(list.get(0));
	}
}
