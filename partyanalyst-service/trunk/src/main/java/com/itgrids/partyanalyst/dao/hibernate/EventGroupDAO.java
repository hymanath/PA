package com.itgrids.partyanalyst.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventGroupDAO;
import com.itgrids.partyanalyst.model.EventGroup;

public class EventGroupDAO extends GenericDaoHibernate<EventGroup, Long> implements IEventGroupDAO{

	public EventGroupDAO() {
		super(EventGroup.class);
	}

	public List<Object[]> getEventGroups(Long userId){
		Query query = getSession().createQuery("select model.eventGroupId,model.groupName from EventGroup model where model.userId = :userId");
		
		query.setParameter("userId", userId);
		return query.list();
	}
}
