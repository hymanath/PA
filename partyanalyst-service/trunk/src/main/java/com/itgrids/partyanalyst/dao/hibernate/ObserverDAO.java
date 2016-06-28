package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IObserverDAO;
import com.itgrids.partyanalyst.model.Observer;

public class ObserverDAO extends GenericDaoHibernate<Observer, Long> implements IObserverDAO{

	public ObserverDAO() {
		super(Observer.class);
	}
	
	 @SuppressWarnings("unchecked")
	public List<Observer> getObserverDetails(){
		Query query = getSession().createQuery("select model from Observer model");
		 
		return query.list();
	 }
	 
	 public Long checkForExists(String name)
	 {
		Query query = getSession().createQuery("select count(model.observerId) from Observer model " +
				" where model.observerName = :name");
		query.setParameter("name", name);
		return (Long)query.uniqueResult();
	}
}
