package com.itgrids.eliteclub.dao;

import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.model.User;
public interface UserDAO extends AbstractDao<User, Integer> {
	public  User getUserByIMEINumber(String imei);


}
