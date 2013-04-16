package com.itgrids.partyanalyst.dao.hibernate;

import static org.junit.Assert.*;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IUserAcessIpAddressDAO;
import com.itgrids.partyanalyst.model.UserAcessIpAddress;

public class UserAcessIpAddressDAOTest extends BaseDaoTestCase  {

	

	private IUserAcessIpAddressDAO userAcessIpAddressDAO;
	
	/*public IUserAcessIpAddressDAO getUserAcessIpAddressDAO() {
		return userAcessIpAddressDAO;
	}*/

	public void setUserAcessIpAddressDAO(
			IUserAcessIpAddressDAO userAcessIpAddressDAO) {
		this.userAcessIpAddressDAO = userAcessIpAddressDAO;
	}

	@Test
	public void testGet() {
		UserAcessIpAddress obj=	userAcessIpAddressDAO.get(1l);
		System.out.println(obj.getIpAddress());
		fail("Not yet implemented");
	}

}
