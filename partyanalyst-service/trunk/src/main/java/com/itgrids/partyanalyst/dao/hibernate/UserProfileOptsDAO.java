package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserProfileOptsDAO;
import com.itgrids.partyanalyst.model.UserProfileOpts;

public class UserProfileOptsDAO extends GenericDaoHibernate<UserProfileOpts, Long> implements IUserProfileOptsDAO{

	public UserProfileOptsDAO(){
		super(UserProfileOpts.class);
	}
	
	public Integer removeOptsOfExistingUser(Long userId){
		String queryInfo = "delete from UserProfileOpts model where model.user.userId = ?";
		
		Query query = getSession().createQuery(queryInfo);
		query.setParameter(0, userId);
		return query.executeUpdate();
	}
	
}
