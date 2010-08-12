/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;

public class UserDAO extends GenericDaoHibernate<User, Long> implements IUserDAO {

	public UserDAO() {
		super(User.class);
	}

	public List<User> findByCategory(String userCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
