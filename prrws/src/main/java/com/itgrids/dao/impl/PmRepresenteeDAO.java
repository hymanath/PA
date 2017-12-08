package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.model.PmRepresentee;

@Repository
public class PmRepresenteeDAO extends GenericDaoHibernate<PmRepresentee, Long> implements IPmRepresenteeDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeDAO() {
		super(PmRepresentee.class);
		
	}

	

}
