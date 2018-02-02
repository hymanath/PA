package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmActionTypeDAO;
import com.itgrids.model.PmActionType;
@Repository
public class PmActionTypeDAO extends GenericDaoHibernate<PmActionType, Long> implements IPmActionTypeDAO {
	
	@Autowired
	SessionFactory sessionFactory; 
	public PmActionTypeDAO() {
		super(PmActionType.class);
		
	}

	

}
