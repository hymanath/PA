package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventCasteInfoDAO;

import com.itgrids.partyanalyst.model.EventCasteInfo;

public class EventCasteInfoDAO extends GenericDaoHibernate<EventCasteInfo, Long> implements IEventCasteInfoDAO {
	
	public EventCasteInfoDAO(){
		super(EventCasteInfo.class);
	}

	

}
