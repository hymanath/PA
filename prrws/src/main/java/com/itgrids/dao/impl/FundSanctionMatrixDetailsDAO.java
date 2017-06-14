package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
	@Override
	public List<Object[]> getPreviousYearDtls(Long scopeId,Long previousYearId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select fundSanctionMatrixDetails.fundSanctionMatrixRangeId, fundSanctionMatrixDetails.scopeValue "
				+ " from FundSanctionMatrixDetails fundSanctionMatrixDetails "
				+ " where "
				+ " fundSanctionMatrixDetails.previousYearId = :previousYearId and "
				+ " fundSanctionMatrixDetails.scopeId = :scopeId and "
				+ " fundSanctionMatrixDetails.isDeleted = 'N' "
				+ " order by "
				+ " fundSanctionMatrixDetails.fundSanctionMatrixRangeId ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("scopeId", scopeId);
		query.setParameter("previousYearId", previousYearId);
		return query.list();
	}

}
