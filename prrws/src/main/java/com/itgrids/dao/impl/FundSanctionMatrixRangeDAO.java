package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionMatrixRangeDAO;
import com.itgrids.model.FundSanctionMatrixRange;

@Repository
public class FundSanctionMatrixRangeDAO extends GenericDaoHibernate<FundSanctionMatrixRange, Long> implements  IFundSanctionMatrixRangeDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public FundSanctionMatrixRangeDAO() {
		super(FundSanctionMatrixRange.class);
	}
}
