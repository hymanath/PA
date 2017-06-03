package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.model.GrantType;

@Repository
public class GrantTypeDAO extends GenericDaoHibernate<GrantType, Long> implements IGrantTypeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public GrantTypeDAO() {
		super(GrantType.class);

	}

}
