package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamAnnualIncomeDAO;
import com.itgrids.partyanalyst.model.MeekosamAnnualIncome;

public class MeekosamAnnualIncomeDAO extends GenericDaoHibernate<MeekosamAnnualIncome, Long> implements IMeekosamAnnualIncomeDAO {

	public MeekosamAnnualIncomeDAO(){
		super(MeekosamAnnualIncome.class);
	}
	public List<Object[]> getMeekosamAnnualIncomeList(){
		Query query = getSession().createQuery(" select model.meekosamAnnualIncomeId, model.meekosamAnnualIncome from MeekosamAnnualIncome model where model.isActive = 'Y'");
		return query.list();
	}
}
