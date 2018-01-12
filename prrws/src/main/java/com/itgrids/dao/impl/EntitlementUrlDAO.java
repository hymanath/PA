package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEntitlementUrlDAO;
import com.itgrids.model.EntitlementUrl;

@Repository
public class EntitlementUrlDAO extends GenericDaoHibernate<EntitlementUrl, Long> implements IEntitlementUrlDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public EntitlementUrlDAO() {
		super(EntitlementUrl.class);
		
	}

}
