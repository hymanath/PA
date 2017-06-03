package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IUserDAO;
import com.itgrids.model.User;

@Repository
public class UserDAO extends GenericDaoHibernate<User, Long> implements IUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAO() {
		super(User.class);

	}

}
