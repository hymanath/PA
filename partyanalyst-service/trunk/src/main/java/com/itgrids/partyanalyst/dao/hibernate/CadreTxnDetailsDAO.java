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
	
	public Long getPendingAmountForUser(String uniqueKey,Long constituencyId,Long userId)
	{
		Query query = getSession().createQuery("select model.pendingAmount from CadreTxnDetails model where " +
				" model.uniqueKey = :uniqueKey and model.constituency.constituencyId = :constituencyId and model.cadreSurveyUser.cadreSurveyUserId = :userId");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();	
	}
	public Integer updatePendingAmount(Long pendingAmount,String uniqueKey,Long constituencyId,Long userId)
	{
	 
		Query query = getSession().createQuery("update CadreTxnDetails model set model.pendingAmount = :pendingAmount where " +
				" model.uniqueKey = :uniqueKey and model.constituency.constituencyId = :constituencyId and model.cadreSurveyUser.cadreSurveyUserId = :userId");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameter("pendingAmount", pendingAmount);
		return query.executeUpdate();
	}
	
}
