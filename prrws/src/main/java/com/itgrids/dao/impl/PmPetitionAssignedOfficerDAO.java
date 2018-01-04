package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.model.PmPetitionAssignedOfficer;

@Repository
public class PmPetitionAssignedOfficerDAO extends GenericDaoHibernate<PmPetitionAssignedOfficer, Long> implements IPmPetitionAssignedOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionAssignedOfficerDAO() {
		super(PmPetitionAssignedOfficer.class);
		
	}

}
