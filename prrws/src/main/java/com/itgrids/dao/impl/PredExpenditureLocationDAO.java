package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPredExpenditureLocationDAO;
import com.itgrids.model.PredExpenditureLocation;

@Repository
public class PredExpenditureLocationDAO extends GenericDaoHibernate<PredExpenditureLocation,Long> implements IPredExpenditureLocationDAO {

	@Autowired
	SessionFactory sessionFactory;

	 public PredExpenditureLocationDAO() {
		 super(PredExpenditureLocation.class);
	 }
}
