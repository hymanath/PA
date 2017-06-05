package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.itgrids.dao.IFundSanctionLocationDAO;
import com.itgrids.model.FundSanctionLocation;
@Repository
public class FundSanctionLocationDAO extends GenericDaoHibernate<FundSanctionLocation, Long> implements IFundSanctionLocationDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionLocationDAO() {
		super(FundSanctionLocation.class);

	}

}
