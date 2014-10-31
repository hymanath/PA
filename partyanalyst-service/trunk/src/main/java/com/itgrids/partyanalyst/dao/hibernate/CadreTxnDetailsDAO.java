package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.model.CadreTxnDetails;

public class CadreTxnDetailsDAO extends GenericDaoHibernate<CadreTxnDetails, Long> implements ICadreTxnDetailsDAO{

	public CadreTxnDetailsDAO() {
		super(CadreTxnDetails.class);
	}

	
	public List<Object[]> getTransactionDetailsByDates(Date todayDate, Date searchDate)
	{
		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select sum(model.paidAmount), sum(model.pendingAmount), sum(model.totalAmount),count(model.cadreTxnDetailsId) from CadreTxnDetails model where  ");	

		if(todayDate == null) // yesterDay transaction details
		{
			queryStr.append(" date(model.surveyTime) = :searchDate  ");
		}
		else if(searchDate ==null) // total transaction details
		{
			queryStr.append("  date(model.surveyTime) <= :todayDate ");
		}
		else if(todayDate != null) // this week transaction details
		{
			queryStr.append("  date(model.surveyTime) >= :searchDate and date(model.surveyTime) <= :todayDate ");
		}
		
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(todayDate == null)
		{
			query.setDate("searchDate", searchDate);
		}
		else if(searchDate ==null)
		{
			query.setDate("todayDate", todayDate);
		}
		else if(todayDate != null)
		{
			query.setDate("searchDate", searchDate);
			query.setDate("todayDate", todayDate);
		}
		
		
		return query.list();
	}
	
	
	public List<Long>  getTotalCadreSurveyTxnTeamSize(Date searchDate)
	{
		Query query = getSession().createQuery("select distinct model.cadreSurveyUserId from CadreTxnDetails model where " +
				" date(model.surveyTime) >= :searchDate   ");	
		query.setDate("searchDate", searchDate);
		return  query.list();
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
