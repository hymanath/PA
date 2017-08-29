package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.IPredExpenditureDAO;
import com.itgrids.model.PredExpenditure;

public class PredExpenditureDAO extends GenericDaoHibernate<PredExpenditure, Long> implements IPredExpenditureDAO {
	public PredExpenditureDAO(){
		super(PredExpenditure.class);
	}
}
