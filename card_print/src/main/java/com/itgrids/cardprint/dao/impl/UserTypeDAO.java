package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IUserTypeDAO;
import com.itgrids.cardprint.model.User;
import com.itgrids.cardprint.model.UserType;

public class UserTypeDAO extends GenericDaoHibernate<UserType, Long> implements IUserTypeDAO {

			public UserTypeDAO(){
				super(UserType.class);
			}

}
