package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITirupathiByeleMobileBoothDAO;

public class TirupathiByeleMobileBoothDAOHibernateTest extends BaseDaoTestCase{
	private ITirupathiByeleMobileBoothDAO tirupathiByeleMobileBoothDAO;

	public ITirupathiByeleMobileBoothDAO getTirupathiByeleMobileBoothDAO() {
		return tirupathiByeleMobileBoothDAO;
	}

	public void setTirupathiByeleMobileBoothDAO(
			ITirupathiByeleMobileBoothDAO tirupathiByeleMobileBoothDAO) {
		this.tirupathiByeleMobileBoothDAO = tirupathiByeleMobileBoothDAO;
	}
	
	public void test(){
		System.out.println("Test Case Running  .. TirupathiByeleMobileBoothDAOHibernateTest ");
	}
}
