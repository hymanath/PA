package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.model.UserAccessLevelValue;

public class UserAccessLevelValueDAO extends GenericDaoHibernate<UserAccessLevelValue,Long> implements IUserAccessLevelValueDAO{

	public UserAccessLevelValueDAO(){
		super(UserAccessLevelValue.class);
	}
	
	public List<Object[]> getAccessValuesOfUserId(Long userId){
		Query query = getSession().createQuery(" select model.accessLevel.accessLevelId," +
				" model.accessLevelValue," +
				" model.accessLevel.accessLevel " +
				" from UserAccessLevelValue model " +
				" where model.user.userId =:userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
}
