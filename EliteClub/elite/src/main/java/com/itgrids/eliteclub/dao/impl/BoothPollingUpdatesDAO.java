package com.itgrids.eliteclub.dao.impl;

import org.springframework.stereotype.Component;

import com.itgrids.eliteclub.dao.IBoothPollingUpdatesDAO;
import com.itgrids.eliteclub.model.BoothPollingUpdates;


@Component("boothPollingUpdatesDAO")
public class BoothPollingUpdatesDAO extends AbstractDaoImpl<BoothPollingUpdates, Long> implements IBoothPollingUpdatesDAO{

	public BoothPollingUpdatesDAO() {
		super(BoothPollingUpdates.class);
	}

	
	public void flushSession() {
		getCurrentSession().flush();
		
	}
	
	
}
