package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityAttendanceAcceptanceDAO;
import com.itgrids.partyanalyst.model.ActivityAttendanceAcceptance;

public class ActivityAttendanceAcceptanceDAO extends GenericDaoHibernate<ActivityAttendanceAcceptance, Long> implements IActivityAttendanceAcceptanceDAO{

	public ActivityAttendanceAcceptanceDAO() {
		super(ActivityAttendanceAcceptance.class);
		
	}
 }
