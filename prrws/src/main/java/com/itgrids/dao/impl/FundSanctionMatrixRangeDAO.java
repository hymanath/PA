package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
	@Override
	public List<Object[]> getFundSanctionRangeList(Long scopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select fundSanctionMatrixRange.fundSanctionMatrixRangeId, fundSanctionMatrixRange.rangeValue "
				+ " from "
				+ " FundSanctionMatrixRange fundSanctionMatrixRange where fundSanctionMatrixRange.scopeId = :scopeId "
				+ " order by "
				+ " fundSanctionMatrixRange.orderNo ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("scopeId", scopeId);
		return query.list();
	}
}
