/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCategoryDAO;
import com.itgrids.partyanalyst.model.UserCategory;

public class UserCategoryDAO extends GenericDaoHibernate<UserCategory, Long> implements IUserCategoryDAO {

	public UserCategoryDAO() {
		super(UserCategory.class);
		
	}

	
}
