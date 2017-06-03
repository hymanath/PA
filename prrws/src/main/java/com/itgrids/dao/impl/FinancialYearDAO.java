package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFinancialYearDAO;
import com.itgrids.model.FinancialYear;

@Repository
public class FinancialYearDAO extends GenericDaoHibernate<FinancialYear, Long> implements IFinancialYearDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FinancialYearDAO() {
		super(FinancialYear.class);

	}

}
