package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.model.UserRoles;

public class UserRolesDAO extends GenericDaoHibernate<UserRoles,Long> implements IUserRolesDAO{

	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserRoles(Long userId) {
		return getHibernateTemplate().find("select model.role.roleId , model.role.roleType from UserRoles model where model.user.registrationId =?",userId);
	}
	
	public List<Object[]> getAllFreeuser()
	{
		return getHibernateTemplate().find("select model.user.firstName,user.mobile from UserRoles model where role.roleType='FREE_USER'");
	}
	
}
