package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Observer;

public interface IObserverDAO extends GenericDao<Observer, Long>{

	public List<Observer> getObserverDetails();
	
	 public Long checkForExists(String name);
}
