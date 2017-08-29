package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPredExpenditureDAO;
import com.itgrids.model.PredExpenditure;

@Repository
public class PredExpenditureDAO extends GenericDaoHibernate<PredExpenditure, Long> implements IPredExpenditureDAO {
	public PredExpenditureDAO(){
		super(PredExpenditure.class);
	}
	@Autowired
	SessionFactory sessionFactory;
}
