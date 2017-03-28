package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserAddressTypeDAO;
import com.itgrids.partyanalyst.model.UserAddressType;

public class UserAddressTypeDAO extends GenericDaoHibernate<UserAddressType, Long> implements IUserAddressTypeDAO{

	public UserAddressTypeDAO() {
		super(UserAddressType.class);
		
	}

}
