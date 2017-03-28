package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventAgeRangeInfoDAO;
import com.itgrids.partyanalyst.model.EventAgeRangeInfo;
import com.itgrids.partyanalyst.model.EventCasteDayInfo;

public class EventAgeRangeInfoDAO extends GenericDaoHibernate<EventAgeRangeInfo, Long> implements
		IEventAgeRangeInfoDAO {

	public EventAgeRangeInfoDAO(){
		super(EventAgeRangeInfo.class);
	}

}
