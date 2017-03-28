package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISubEventDAO;
import com.itgrids.partyanalyst.model.SubEvent;

public class SubEventDAO extends GenericDaoHibernate<SubEvent, Long> implements ISubEventDAO{

	public SubEventDAO() {
		super(SubEvent.class);
	}

	public List<Object[]> getGeneralMeetingDetailsForMainEvents()
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.eventId, model.name,model.startTime,model.endTime from SubEvent model order by model.eventId,model.orderId");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
}
