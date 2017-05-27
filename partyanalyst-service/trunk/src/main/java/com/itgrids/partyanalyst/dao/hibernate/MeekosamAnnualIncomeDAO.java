package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamAnnualIncomeDAO;
import com.itgrids.partyanalyst.model.MeekosamAnnualIncome;

public class MeekosamAnnualIncomeDAO extends GenericDaoHibernate<MeekosamAnnualIncome, Long> implements IMeekosamAnnualIncomeDAO {

	public MeekosamAnnualIncomeDAO(){
		super(MeekosamAnnualIncome.class);
	}

}
