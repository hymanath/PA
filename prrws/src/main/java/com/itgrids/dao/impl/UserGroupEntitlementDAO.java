package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IUserGroupEntitlementDAO;
import com.itgrids.model.UserGroupEntitlement;

@Repository
public class UserGroupEntitlementDAO extends GenericDaoHibernate<UserGroupEntitlement, Long> implements IUserGroupEntitlementDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public UserGroupEntitlementDAO() {
		super(UserGroupEntitlement.class);
	}
}
