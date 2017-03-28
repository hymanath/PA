package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISourceIncomeDAO;
import com.itgrids.partyanalyst.model.SourceIncome;

public class SourceIncomeDAO extends GenericDaoHibernate<SourceIncome, Long> implements ISourceIncomeDAO{

	public SourceIncomeDAO() {
		super(SourceIncome.class);
		// TODO Auto-generated constructor stub
	}

}
