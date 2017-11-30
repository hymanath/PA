package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkDetailsDAO;
import com.itgrids.model.PetitionWorkDetails;

@Repository
public class PetitionWorkDetailsDAO extends GenericDaoHibernate<PetitionWorkDetails, Long> implements IPetitionWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionWorkDetailsDAO() {
		super(PetitionWorkDetails.class);

	}
}
