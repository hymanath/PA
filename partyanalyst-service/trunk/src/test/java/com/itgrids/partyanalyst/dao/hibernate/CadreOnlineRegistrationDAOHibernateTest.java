package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.CadreOnlineRegistration;

public class CadreOnlineRegistrationDAOHibernateTest extends BaseDaoTestCase{

	public ICadreOnlineRegistrationDAO cadreOnlineRegistrationDAO;

	public void setCadreOnlineRegistrationDAO(
			ICadreOnlineRegistrationDAO cadreOnlineRegistrationDAO) {
		this.cadreOnlineRegistrationDAO = cadreOnlineRegistrationDAO;
	}
	
	/*public void test()
	{
		cadreOnlineRegistrationDAO.getAll();
	}*/
	
	public void testGetAllDetailsBasedOnOnlineRegId()
	{
		CadreOnlineRegistration values = cadreOnlineRegistrationDAO.getAllDetailsBasedOnOnlineRegId(1l);
	
			System.out.println(values.getUser().getFirstName());
		
	}
}
