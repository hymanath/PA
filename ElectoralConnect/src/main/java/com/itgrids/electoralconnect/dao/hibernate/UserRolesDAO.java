package com.itgrids.electoralconnect.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.model.UserRoles;


public class UserRolesDAO extends GenericDaoHibernate<UserRoles, Long> implements IUserRolesDAO{

	public UserRolesDAO() {
		super(UserRoles.class);
	}

}
