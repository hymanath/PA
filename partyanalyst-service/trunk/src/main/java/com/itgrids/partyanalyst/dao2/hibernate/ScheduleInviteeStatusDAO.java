package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IScheduleInviteeStatusDAO;
import com.itgrids.partyanalyst.model.ScheduleInviteeStatus;

public class ScheduleInviteeStatusDAO extends GenericDaoHibernate<ScheduleInviteeStatus, Long> implements IScheduleInviteeStatusDAO{

	public ScheduleInviteeStatusDAO() {
		super(ScheduleInviteeStatus.class);
		// TODO Auto-generated constructor stub
	}
			
	public List<Object[]> getAllStatusList()
	{
		return getHibernateTemplate().find("select model.scheduleInviteeStatusId,model.status from ScheduleInviteeStatus model");
	}
}
