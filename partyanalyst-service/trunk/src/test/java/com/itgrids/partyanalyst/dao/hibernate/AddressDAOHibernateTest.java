package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAddressDAO;

public class AddressDAOHibernateTest extends BaseDaoTestCase {
	
	private IAddressDAO addressDAO;

	

	public void setAddressDAO(IAddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	
	@SuppressWarnings("rawtypes")
	public void testgetAddressDetailsByAddressId(){
		
		List list= addressDAO.getAddressDetailsByAddressId((long)1);
		
		System.out.println(list.size());
	}

}
