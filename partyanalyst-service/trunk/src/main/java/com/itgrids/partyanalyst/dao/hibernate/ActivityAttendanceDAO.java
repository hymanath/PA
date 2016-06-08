package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.ActivityInvitee;

public class ActivityAttendanceDAO extends
		GenericDaoHibernate<ActivityAttendance, Long> implements
		IActivityAttendanceDAO {
	public ActivityAttendanceDAO() {
		super(ActivityAttendance.class);

	}

}
