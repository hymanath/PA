package com.itgrids.eliteclub.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.model.User;
import com.itgrids.eliteclub.webservice.components.WebServiceHandler;

@Repository
@Component("userDAO")
public class UserDAOImpl extends AbstractDaoImpl<User, Integer> implements UserDAO {
	Logger log=LogManager.getLogger(UserDAOImpl.class);
	protected UserDAOImpl() {
		super(User.class);
		
	}
	


	public  User getUserByIMEINumber(String imei)
	{
		
	Query query=	getCurrentSession().createQuery("select user from User user where user.imeiNo =:imei");
	query.setParameter("imei", imei);
	return (User) query.uniqueResult();
	}
	
}
