package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAnnualIncomeDAO;
import com.itgrids.partyanalyst.model.AnnualIncome;

public class AnnualIncomeDAO extends GenericDaoHibernate<AnnualIncome, Long> implements IAnnualIncomeDAO{

	public AnnualIncomeDAO() {
		super(AnnualIncome.class);
		// TODO Auto-generated constructor stub
	}

}
