package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.dao.IPetitionGrantDAO;
import com.itgrids.model.PetitionGrant;

public class PetitionGrantDAO extends GenericDaoHibernate<PetitionGrant, Long> implements IPetitionGrantDAO {

	@Autowired
	SessionFactory sessionFactory;

	PetitionGrantDAO(){
		super(PetitionGrant.class);
	}
	
}
