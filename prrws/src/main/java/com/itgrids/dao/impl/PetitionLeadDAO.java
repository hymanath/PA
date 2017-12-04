package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionLeadDAO;
import com.itgrids.model.PetitionLead;

@Repository
public class PetitionLeadDAO extends GenericDaoHibernate<PetitionLead, Long> implements IPetitionLeadDAO{
	
@Autowired
SessionFactory sessionFactory;

	PetitionLeadDAO(){
		super(PetitionLead.class);
	}
}
