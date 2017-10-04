package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaEventsDAO;
import com.itgrids.partyanalyst.model.KaizalaEvents;

public class KaizalaEventsDAO extends GenericDaoHibernate<KaizalaEvents, Long> implements IKaizalaEventsDAO{

	public KaizalaEventsDAO() {
		super(KaizalaEvents.class);
		
	}
	
}
