package com.itgrids.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IUserAccessLevelValueDAO;
import com.itgrids.model.UserAccessLevelValue;

@Repository
public class UserAccessLevelValueDAO extends GenericDaoHibernate<UserAccessLevelValue, Long> implements IUserAccessLevelValueDAO{

	public UserAccessLevelValueDAO() {
		super(UserAccessLevelValue.class);
	}

	public List<Object[]> getUserAccessDetails(Long userId){
		 Query query = getSession().createQuery("select model.accessLevel.accessLevelId,"
		 		+ "model.accessLevelValue"
		 		+ " from UserAccessLevelValue model"
		 		+ " where model.user.isEnabled = 'Y' and model.user.isDeleted = 'N'"
		 		+ " and model.user.userId = :userId");
		 
		 query.setParameter("userId", userId);
		 return query.list();
	}
}
