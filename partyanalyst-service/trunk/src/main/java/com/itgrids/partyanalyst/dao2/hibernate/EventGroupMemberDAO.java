package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventGroupMemberDAO;
import com.itgrids.partyanalyst.model.EventGroupMember;

public class EventGroupMemberDAO extends GenericDaoHibernate<EventGroupMember, Long> implements IEventGroupMemberDAO{

	public EventGroupMemberDAO() {
		super(EventGroupMember.class);
	}

}
