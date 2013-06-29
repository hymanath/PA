package com.itgrids.electoralconnect.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IUserProfileDAO;
import com.itgrids.electoralconnect.model.UserProfile;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserProfileDAO extends GenericDaoHibernate<UserProfile,Long> implements IUserProfileDAO{
	public UserProfileDAO()
	{
		super(UserProfile.class);
	}
	
	public List<Object[]> validateEmail(String emailId){
		Query query=getSession().createQuery("select model.userProfileId from UserProfile model where model.emailId=:emailId");
		query.setParameter("emailId", emailId);
		
		return query.list();
	}
}
