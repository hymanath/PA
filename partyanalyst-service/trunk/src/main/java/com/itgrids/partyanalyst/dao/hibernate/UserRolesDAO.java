package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.model.UserRoles;

public class UserRolesDAO extends GenericDaoHibernate<UserRoles,Long> implements IUserRolesDAO{

	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserRoles(Long userId) {
		return getHibernateTemplate().find("select model.role.roleId , model.role.roleType from UserRoles model where model.user.userId =?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllFreeuser()
	{
		return getHibernateTemplate().find("select model.user.firstName,user.mobile from UserRoles model where role.roleType='FREE_USER'");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUserRolesOfAUser(Long userId)
	{
		Query query = getSession().createQuery("select model.role.roleType from UserRoles model where model.user.userId =?");
		query.setParameter(0,userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUsersMobile(String roleType)
	{
		return getHibernateTemplate().find("select distinct model.firstName,model.lastName,model.mobile,model.constituency.name,model.userId from UserRoles model where model.role.roleType = ?",roleType);
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllMobilenosAsUnique()
	{
		return getHibernateTemplate().find("select distinct model.user.mobile from UserRoles model where role.roleType='FREE_USER' and model.user.mobile is not null");
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllFreeusertoSendSms()
	{
		return getHibernateTemplate().find("select model.user.firstName,model.user.lastName,model.user.mobile,model.user.constituency.name,model.user.userId from UserRoles model where role.roleType='FREE_USER' and model.user.mobile is not null ");
	}
	
}
