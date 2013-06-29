package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.UserLogin;
import com.itgrids.electoralconnect.model.UserProfile;

public interface IUserProfileDAO  extends GenericDao<UserProfile, Long> {
	public List<Object[]> validateEmail(String emailId);
	
}
