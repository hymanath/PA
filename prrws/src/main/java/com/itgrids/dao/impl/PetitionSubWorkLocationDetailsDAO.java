package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionSubWorkLocationDetailsDAO;
import com.itgrids.model.PetitionSubWorkLocationDetails;

@Repository
public class PetitionSubWorkLocationDetailsDAO extends GenericDaoHibernate<PetitionSubWorkLocationDetails, Long>
		implements IPetitionSubWorkLocationDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionSubWorkLocationDetailsDAO() {
		super(PetitionSubWorkLocationDetails.class);

	}

}
