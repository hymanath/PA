package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionSubjectDAO;
import com.itgrids.model.PetitionSubject;


@Repository
public class PetitionSubjectDAO extends GenericDaoHibernate<PetitionSubject, Long> implements IPetitionSubjectDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionSubjectDAO() {
		super(PetitionSubject.class);
	}

}
