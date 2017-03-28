package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventCasteCategoryDayInfoDAO;
import com.itgrids.partyanalyst.model.DemoRequestActions;
import com.itgrids.partyanalyst.model.EventCasteCategoryDayInfo;

public class EventCasteCategoryDayInfoDAO extends GenericDaoHibernate<EventCasteCategoryDayInfo, Long> implements IEventCasteCategoryDayInfoDAO {

	public EventCasteCategoryDayInfoDAO(){
		super(EventCasteCategoryDayInfo.class);
	}
	

}
