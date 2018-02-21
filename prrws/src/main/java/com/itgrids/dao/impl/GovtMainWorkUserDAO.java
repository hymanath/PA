package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtMainWorkUserDAO;
import com.itgrids.model.GovtMainWorkUser;

@Repository
public class GovtMainWorkUserDAO extends GenericDaoHibernate<GovtMainWorkUser, Long> implements IGovtMainWorkUserDAO{

	public GovtMainWorkUserDAO() {
		super(GovtMainWorkUser.class);
	}

}
