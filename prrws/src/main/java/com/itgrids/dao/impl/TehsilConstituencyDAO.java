package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITehsilConstituencyDAO;
import com.itgrids.model.TehsilConstituency;

@Repository
public class TehsilConstituencyDAO  extends GenericDaoHibernate<TehsilConstituency, Long> implements ITehsilConstituencyDAO{
	
	@Autowired	
	SessionFactory sessionFactory; 
	
	public TehsilConstituencyDAO() {
		super(TehsilConstituency.class);
	}

}
