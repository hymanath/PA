package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventGenderDayInfoDAO;
import com.itgrids.partyanalyst.model.EventGenderDayInfo;
import com.itgrids.partyanalyst.model.EventGenderInfo;

public class EventGenderDayInfoDAO extends GenericDaoHibernate<EventGenderDayInfo, Long> implements
		IEventGenderDayInfoDAO {

   public EventGenderDayInfoDAO(){
	   super(EventGenderDayInfo.class);
   }

}
