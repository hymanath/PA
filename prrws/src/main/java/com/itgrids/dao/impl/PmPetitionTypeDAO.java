package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionTypeDAO;
import com.itgrids.model.PmPetitionType;

@Repository
public class PmPetitionTypeDAO extends GenericDaoHibernate<PmPetitionType, Long> implements IPmPetitionTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionTypeDAO() {
		super(PmPetitionType.class);
		
	}

	
}
