package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.dao.IPetitionStatusDAO;
import com.itgrids.model.PetitionStatus;

public class PetitionStatusDAO extends GenericDaoHibernate<PetitionStatus,Long> implements IPetitionStatusDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	PetitionStatusDAO(){
		super(PetitionStatus.class);
	}
}
