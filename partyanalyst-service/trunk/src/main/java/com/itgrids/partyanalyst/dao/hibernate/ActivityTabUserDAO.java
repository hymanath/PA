package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.model.ActivityTabUser;

import com.itgrids.partyanalyst.dao.IActivityTabUserDAO;

public class ActivityTabUserDAO extends GenericDaoHibernate<ActivityTabUser, Long> implements IActivityTabUserDAO{

	public ActivityTabUserDAO() {
		super(ActivityTabUser.class);
		
	}
	
	public List<Long> getUserByUserNameAndPassword(String userName , String password)
	{
		Query query = getSession().createQuery("select model.activityTabUserId from ActivityTabUser model where model.userName = :userName " +
								" and model.password = :password and model.isActive = 'Y' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
	
	public ActivityTabUser checkActivityTabUserLogin(String userName,String password){
		
		Query query = getSession().createQuery(" select model from ActivityTabUser model where model.userName = :userName " +
							" and model.password = :password and model.isActive = 'Y' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (ActivityTabUser) query.uniqueResult();
	}

}
