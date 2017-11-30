package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionReffererDAO;
import com.itgrids.model.FundSanctionMatrixRange;
import com.itgrids.model.PetitionRefferer;

@Repository
public class PetitionReffererDAO extends GenericDaoHibernate<PetitionRefferer, Long> implements IPetitionReffererDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionReffererDAO() {
		super(PetitionRefferer.class);
	}
}
