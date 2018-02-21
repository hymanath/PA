package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MobileAppUser;
import com.itgrids.model.MobileAppUserType;

public interface IMobileAppUserDAO extends GenericDao<MobileAppUser,Long>{
	public List<Object[]> checkLogin(String userName,String password);
}
