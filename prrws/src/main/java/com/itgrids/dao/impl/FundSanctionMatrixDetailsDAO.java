package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionMatrixDetailsDAO;
import com.itgrids.model.FundSanctionMatrixDetails;

@Repository
public class FundSanctionMatrixDetailsDAO extends GenericDaoHibernate<FundSanctionMatrixDetails, Long> implements IFundSanctionMatrixDetailsDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionMatrixDetailsDAO() {
		super(FundSanctionMatrixDetails.class);
	}

}
