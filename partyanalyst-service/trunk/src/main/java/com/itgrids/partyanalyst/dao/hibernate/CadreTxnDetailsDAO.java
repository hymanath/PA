package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.model.CadreTxnDetails;

public class CadreTxnDetailsDAO extends GenericDaoHibernate<CadreTxnDetails, Long> implements ICadreTxnDetailsDAO{

	public CadreTxnDetailsDAO() {
		super(CadreTxnDetails.class);
	}
	public Integer updateCompleteStatus(String uniqueKey,Long constituencyId)
	{
	 
		Query query = getSession().createQuery("update CadreTxnDetails model set model.completeStatus = 'Y' where " +
				" model.completeStatus = 'N' and model.uniqueKey = :uniqueKey and model.constituency.constituencyId = :constituencyId");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("constituencyId", constituencyId);
		return query.executeUpdate();
	}
}
