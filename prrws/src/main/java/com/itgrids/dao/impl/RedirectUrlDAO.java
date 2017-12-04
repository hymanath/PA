package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRedirectUrlDAO;
import com.itgrids.model.RedirectUrl;
@Repository
public class RedirectUrlDAO extends GenericDaoHibernate<RedirectUrl, Long> implements IRedirectUrlDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public RedirectUrlDAO() {
		super(RedirectUrl.class);
		
	}
}
