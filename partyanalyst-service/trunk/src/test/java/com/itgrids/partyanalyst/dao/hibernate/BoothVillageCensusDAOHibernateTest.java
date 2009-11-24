package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;

public class BoothVillageCensusDAOHibernateTest extends BaseDaoTestCase{

	private IBoothVillageCensusDAO boothVillageCensusDAO;

	public IBoothVillageCensusDAO getBoothVillageCensusDAO() {
		return boothVillageCensusDAO;
	}

	public void setBoothVillageCensusDAO(
			IBoothVillageCensusDAO boothVillageCensusDAO) {
		this.boothVillageCensusDAO = boothVillageCensusDAO;
	}
	
	
}
