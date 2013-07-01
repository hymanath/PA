package com.itgrids.electoralconnect.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.model.UserRoles;


public class UserRolesDAO extends GenericDaoHibernate<UserRoles, Long> implements IUserRolesDAO{

	public UserRolesDAO() {
		super(UserRoles.class);
	}

	/**
	 * This DAO is used for verfying the user entered username and password is correct or wrong
	 * @param  String userName
	 * @param String password
	 * @return List<Object[]>
	 * @date 29-06-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> checkForValidUser(String userName,String password)
	{
		Object[] parms = {userName , password};
		return getHibernateTemplate().find("select model.user,model.roles from UserRoles model " +
				" where model.user.userLogin.userName = ? and model.user.userLogin.password = ? ", parms);
	}
}
