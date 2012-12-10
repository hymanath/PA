package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAddressTypeDAO;


public class AddressTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IAddressTypeDAO addressTypeDAO;

	

	public void setAddressTypeDAO(IAddressTypeDAO addressTypeDAO) {
		this.addressTypeDAO = addressTypeDAO;
	}

	public void testGetAddressTypeByAddressTypeId(){
		
		List  list = addressTypeDAO.getAddressTypeByAddressTypeId(1l);
		System.out.println(list);
		
	}


}
