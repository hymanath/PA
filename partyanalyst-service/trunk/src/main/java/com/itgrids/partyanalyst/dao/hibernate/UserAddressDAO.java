package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.model.UserAddress;

public class UserAddressDAO extends GenericDaoHibernate<UserAddress, Long> implements IUserAddressDAO {

	public UserAddressDAO() {
		super(UserAddress.class);		
	}

}
