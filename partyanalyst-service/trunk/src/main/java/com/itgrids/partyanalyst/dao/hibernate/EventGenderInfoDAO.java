package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventGenderInfoDAO;
import com.itgrids.partyanalyst.model.EventAgeRangeDayInfo;
import com.itgrids.partyanalyst.model.EventGenderInfo;

public class EventGenderInfoDAO extends GenericDaoHibernate<EventGenderInfo, Long> implements IEventGenderInfoDAO {

	public EventGenderInfoDAO(){
		super(EventGenderInfo.class);
	}

}
