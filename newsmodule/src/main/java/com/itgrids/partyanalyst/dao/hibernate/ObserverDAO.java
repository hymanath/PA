package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IObserverDAO;
import com.itgrids.partyanalyst.model.Observer;

public class ObserverDAO extends GenericDaoHibernate<Observer, Long> implements IObserverDAO{

	public ObserverDAO() {
		super(Observer.class);
	}

}
