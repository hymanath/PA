package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.model.UserAddress;

public class UserAddressDAOHibernateTest extends BaseDaoTestCase {
	
	private IUserAddressDAO userAddressDAO;

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	
	/*@SuppressWarnings("unchecked")
	public void test(){
		Integer i = userAddressDAO.deleteInfluencingPeopleById(17l);
		System.out.println("=============="+i);
		
	}*/

   public void testgetUserAddressList()
   {
	   userAddressDAO.getUserAddressList();
   }
}
