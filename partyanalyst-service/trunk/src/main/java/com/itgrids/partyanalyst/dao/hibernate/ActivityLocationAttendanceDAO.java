package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;

public class ActivityLocationAttendanceDAO extends GenericDaoHibernate<ActivityLocationAttendance, Long> implements IActivityLocationAttendanceDAO{

	public ActivityLocationAttendanceDAO() {
		super(ActivityLocationAttendance.class);
		// TODO Auto-generated constructor stub
	}

}
