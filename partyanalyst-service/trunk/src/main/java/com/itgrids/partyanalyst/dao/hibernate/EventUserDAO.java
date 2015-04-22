package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.model.EventUser;

public class EventUserDAO extends GenericDaoHibernate<EventUser, Long> implements IEventUserDAO{

	public EventUserDAO() {
		super(EventUser.class);
	}
	
	
	public List<Object[]> getEventsByUser(Long userId,Date startDate)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name from EventUser model where date(model.event.eventStartTime) >=:startDate " +
				"and model.userId =:userId  ");
		query.setParameter("startDate", startDate);
		query.setParameter("userId", userId);
		return query.list();
	}

}
