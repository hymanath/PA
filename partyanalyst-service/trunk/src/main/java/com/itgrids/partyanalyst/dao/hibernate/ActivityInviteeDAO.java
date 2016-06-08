package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityLocationInfoDatesDAO;
import com.itgrids.partyanalyst.model.ActivityInvitee;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public class ActivityInviteeDAO extends
		GenericDaoHibernate<ActivityInvitee, Long> implements
		IActivityInviteeDAO {
	public ActivityInviteeDAO() {
		super(ActivityInvitee.class);

	}
}
