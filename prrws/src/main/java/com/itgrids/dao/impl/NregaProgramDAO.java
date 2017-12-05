package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.model.NregaProgram;

import com.itgrids.dao.INregaProgramDAO;

@Repository
public class NregaProgramDAO extends GenericDaoHibernate<NregaProgram, Long> implements INregaProgramDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	public NregaProgramDAO() {
		super(NregaProgram.class);
		
	}

}
