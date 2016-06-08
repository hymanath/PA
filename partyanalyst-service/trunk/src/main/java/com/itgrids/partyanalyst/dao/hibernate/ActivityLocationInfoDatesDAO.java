package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.impl.IActivityDateTypeDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityLocationInfoDatesDAO;
import com.itgrids.partyanalyst.model.ActivityDateType;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public class ActivityLocationInfoDatesDAO extends
		GenericDaoHibernate<ActivityLocationInfoDates, Long> implements
		IActivityLocationInfoDatesDAO {
	public ActivityLocationInfoDatesDAO() {
		super(ActivityLocationInfoDates.class);

	}
}
