package com.itgrids.eliteclub.dao;

import com.itgrids.eliteclub.model.User;

public interface UserDAO extends AbstractDao<User, Integer> {
	public  User getUserByIMEINumber(String imei);


}
