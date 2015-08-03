package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IScheduleInviteeStatusDAO;
import com.itgrids.partyanalyst.model.ScheduleInviteeStatus;

public class ScheduleInviteeStatusDAO extends GenericDaoHibernate<ScheduleInviteeStatus, Long> implements IScheduleInviteeStatusDAO{

	public ScheduleInviteeStatusDAO() {
		super(ScheduleInviteeStatus.class);
		// TODO Auto-generated constructor stub
	}

}
