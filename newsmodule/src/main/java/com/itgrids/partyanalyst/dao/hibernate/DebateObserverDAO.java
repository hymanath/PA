package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateObserverDAO;
import com.itgrids.partyanalyst.model.DebateObserver;

public class DebateObserverDAO extends GenericDaoHibernate<DebateObserver, Long> implements IDebateObserverDAO{

	public DebateObserverDAO() {
		super(DebateObserver.class);

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getObsersListForDebate(Long debateId)
	{
		return getHibernateTemplate().find("select model.observer.observerName from DebateObserver model " +
				" where model.debate.debateId = ? ",debateId);
	}
}
