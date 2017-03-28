package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventCasteDayInfoDAO;
import com.itgrids.partyanalyst.model.EventCasteDayInfo;
import com.itgrids.partyanalyst.model.EventCasteInfo;

public class EventCasteDayInfoDAO extends GenericDaoHibernate<EventCasteDayInfo, Long> implements
		IEventCasteDayInfoDAO {

	public EventCasteDayInfoDAO(){
		super(EventCasteDayInfo.class);
	}

}
