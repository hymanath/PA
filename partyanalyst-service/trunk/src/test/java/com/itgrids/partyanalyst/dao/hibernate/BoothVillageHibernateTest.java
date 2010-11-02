package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothVillageDAO;

public class BoothVillageHibernateTest extends BaseDaoTestCase {

	private IBoothVillageDAO  boothVillageDAO;

	public IBoothVillageDAO getBoothVillageDAO() {
		return boothVillageDAO;
	}

	public void setBoothVillageDAO(IBoothVillageDAO boothVillageDAO) {
		this.boothVillageDAO = boothVillageDAO;
	}
	
	
}
