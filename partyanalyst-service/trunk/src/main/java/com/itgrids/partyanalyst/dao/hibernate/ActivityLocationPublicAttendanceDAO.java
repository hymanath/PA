package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityLocationPublicAttendanceDAO;
import com.itgrids.partyanalyst.model.ActivityLocationPublicAttendance;

public class ActivityLocationPublicAttendanceDAO extends GenericDaoHibernate<ActivityLocationPublicAttendance, Long> implements IActivityLocationPublicAttendanceDAO{

	public ActivityLocationPublicAttendanceDAO() {
		super(ActivityLocationPublicAttendance.class);
		// TODO Auto-generated constructor stub
	}

}
