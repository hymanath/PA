package com.itgrids.electoralconnect.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IUserProfileDAO;
import com.itgrids.electoralconnect.model.UserProfile;

public class UserProfileDAO extends GenericDaoHibernate<UserProfile,Long> implements IUserProfileDAO{
	public UserProfileDAO()
	{
		super(UserProfile.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> validateEmail(String emailId){
		Query query=getSession().createQuery("select model.userProfileId from UserProfile model where model.emailId=:emailId");
		query.setParameter("emailId", emailId);
		
		return query.list();
	}
}
