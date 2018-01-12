package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEntitlementGroupDAO;
import com.itgrids.model.EntitlementGroup;

@Repository
public class EntitlementGroupDAO extends GenericDaoHibernate<EntitlementGroup, Long> implements IEntitlementGroupDAO {

	
	@Autowired
	SessionFactory sessionFactory; 
	public EntitlementGroupDAO() {
		super(EntitlementGroup.class);
		
	}
}
