package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkTypeDAO;
import com.itgrids.model.PetitionWorkType;

@Repository
public class PetitionWorkTypeDAO extends GenericDaoHibernate<PetitionWorkType, Long> implements IPetitionWorkTypeDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	PetitionWorkTypeDAO(){
		super(PetitionWorkType.class);
	}
}
