package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventCasteCategoryInfoDAO;
import com.itgrids.partyanalyst.model.DemoRequestActions;
import com.itgrids.partyanalyst.model.EventCasteCategoryInfo;

public class EventCasteCategoryInfoDAO extends GenericDaoHibernate<EventCasteCategoryInfo, Long>implements IEventCasteCategoryInfoDAO {

	public EventCasteCategoryInfoDAO(){
		super(EventCasteCategoryInfo.class);
	}
	

}
