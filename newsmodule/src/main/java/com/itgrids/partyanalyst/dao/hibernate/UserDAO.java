package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;

public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{
	public UserDAO()
	{
		super(User.class);
	}
	@SuppressWarnings("unchecked")
	public List<User> checkForUserNameAvailabiity(String userName)
	{
		return getHibernateTemplate().find("select model.userName from User model where model.userName = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> checkForUserNameAvailabiityForEmail(String userName)
	{
		return getHibernateTemplate().find("select model.email from User model where model.email = ?",userName);
	}
	
	
	public Long checkForUserNameAndPasswordAvaliablity(String userName,String password)
	{
		Query query = getSession().createQuery("select count(*) from User model where" +
				"  (model.userName = :userName and  model.password = :password) and ( model.userAccessType!='' or model.userAccessType is not null)");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (Long)query.uniqueResult();
	}
	public User getUserByUserNameAndPassword(String userName,String password)
	{
		Query query = getSession().createQuery("from User model where" +
				"  (model.userName = :userName and  model.password = :password )and ( model.userAccessType!='' or model.userAccessType is not null)");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (User)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> chechForUserType(Long userId)
	{
		Query query = getSession().createQuery("select model.userId,model.userAccessType from User model where" +
				"  model.userId :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public String checkCurrentPasswordExist(String password,Long userId)
	{
		Query query = getSession().createQuery(" select model.password from User model where model.userId =:userId and model.password =:password  ");
		query.setParameter("userId", userId);
		query.setParameter("password", password);
		
		return (String) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getUserNameAndPassword(String userName,String password)
	{
		Query query = getSession().createQuery("select model.password from User model where" +
				"  (model.userName = :userName and  model.password = :password) and ( model.userAccessType!='' or model.userAccessType is not null) and model.projectType='NewsPortal' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
	
	public List<String> getUserType(Long userId){
		Query query = getSession().createQuery("select model.userAccessType from User model where model.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<String> getAccessValue(Long userId)
	{
		Query query = getSession().createQuery("select model.accessValue from User model where model.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
}
