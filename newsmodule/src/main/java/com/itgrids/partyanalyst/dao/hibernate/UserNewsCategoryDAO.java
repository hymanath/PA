package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserNewsCategoryDAO;
import com.itgrids.partyanalyst.model.UserNewsCategory;

public class UserNewsCategoryDAO extends GenericDaoHibernate<UserNewsCategory , Long> implements IUserNewsCategoryDAO{

	public UserNewsCategoryDAO()
	{
		super(UserNewsCategory.class);
	}
	
}
