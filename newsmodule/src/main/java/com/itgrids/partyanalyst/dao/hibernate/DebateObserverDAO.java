package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateObserverDAO;
import com.itgrids.partyanalyst.model.DebateObserver;

public class DebateObserverDAO extends GenericDaoHibernate<DebateObserver, Long> implements IDebateObserverDAO{

	public DebateObserverDAO() {
		super(DebateObserver.class);

	}

}
