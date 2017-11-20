package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaFinancialYearDAO;
import com.itgrids.model.NregaFinancialYear;

@Repository
public class NregaFinancialYearDAO extends GenericDaoHibernate<NregaFinancialYear, Long> implements INregaFinancialYearDAO{

	public NregaFinancialYearDAO() {
		super(NregaFinancialYear.class);
		
	}

}
