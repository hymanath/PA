package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCategoryValuesDAO;
import com.itgrids.partyanalyst.model.UserCategoryValues;

public class UserCategoryValuesDAO extends GenericDaoHibernate<UserCategoryValues, Long> implements IUserCategoryValuesDAO{

	public UserCategoryValuesDAO() {
		super(UserCategoryValues.class);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("unchecked")
	public List<UserCategoryValues> getUserCategoryValues(){
		
		return getHibernateTemplate().find("from UserCategoryValues model");
		
	}
}
