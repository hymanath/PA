package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventAgeRangeDayInfoDAO;
import com.itgrids.partyanalyst.model.EventAgeRangeDayInfo;
import com.itgrids.partyanalyst.model.EventAgeRangeInfo;

public class EventAgeRangeDayInfoDAO extends GenericDaoHibernate<EventAgeRangeDayInfo, Long> implements IEventAgeRangeDayInfoDAO {

	public EventAgeRangeDayInfoDAO(){
		super(EventAgeRangeDayInfo.class);
	}
	

}
