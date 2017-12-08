package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubjectDAO;
import com.itgrids.model.PmSubject;
@Repository
public class PmSubjectDAO extends GenericDaoHibernate<PmSubject, Long> implements IPmSubjectDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmSubjectDAO() {
		super(PmSubject.class);
		
	}

	

}
