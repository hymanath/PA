package com.itgrids.eliteclub.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.model.User;

@Repository
@Component("userDAO")
public class UserDAOImpl extends AbstractDaoImpl<User, Integer> implements UserDAO {
	
	protected UserDAOImpl() {
		super(User.class);
		
	}
	
	Logger log=LogManager.getLogger();	
}
