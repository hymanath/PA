package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAddressContactDAO;

public class addressContactDAOHibernateTest extends BaseDaoTestCase{
	private IAddressContactDAO addressContactDAO;

	public void setAddressContactDAO(IAddressContactDAO addressContactDAO) {
		this.addressContactDAO = addressContactDAO;
	}
	
	public void testgetAddressContactId(){
		
		Long value=addressContactDAO.getAddressContactId(128l,98l);
		
		System.out.println(value);
	}

}
