package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;


public class CadreOtpDetailsDAO extends GenericDaoHibernate<CadreOtpDetails, Long> implements ICadreOtpDetailsDAO{

	public CadreOtpDetailsDAO() {
		super(CadreOtpDetails.class);
	}
	
	public List<Object[]> getOtpDetailsForDates(Date todayDate, Date searchDate)
	{

		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select  model.cadreTxnDetails.completeStatus, sum(model.cadreTxnDetails.paidAmount), sum(model.cadreTxnDetails.pendingAmount), " +
				" sum(model.cadreTxnDetails.totalAmount),count(model.cadreTxnDetails.cadreTxnDetailsId) from CadreOtpDetails model where  ");	

		if(todayDate == null) // yesterDay transaction details
		{
			queryStr.append(" date(model.cadreTxnDetails.surveyTime) = :searchDate  ");
		}
		else if(searchDate ==null) // total transaction details
		{
			queryStr.append("   date(model.cadreTxnDetails.surveyTime) <= :todayDate ");
		}
		
		else if(todayDate != null) // this week transaction details
		{
			queryStr.append(" date(model.cadreTxnDetails.surveyTime) >= :searchDate and date(model.cadreTxnDetails.surveyTime) <= :todayDate ");
		}		
		
		queryStr.append(" group by  model.cadreTxnDetails.completeStatus order by  model.cadreTxnDetails.completeStatus ");
		
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
	
	public List<Object[]> getOfflineTxnDetailsIdsForDates(Date todayDate, Date searchDate)
	{

		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct CTD.complete_status, sum(CTD.total_amount),count( CTD.cadre_txn_details_id) from  " +
				"   cadre_txn_details CTD left join cadre_otp_details COD ON COD.cadre_txn_details_id = CTD.cadre_txn_details_id " +
				" where   ");
		
		if(todayDate == null) // yesterDay transaction details
		{
			queryStr.append(" date(CTD.survey_time) = :searchDate and ");
		}
		else if(searchDate ==null) // total transaction details
		{
			queryStr.append("   date(CTD.survey_time) <= :todayDate and ");
		}
		else if(todayDate != null) // this week transaction details
		{
			queryStr.append("   ( date(CTD.survey_time) >= :searchDate and date(CTD.survey_time) <= :todayDate ) and  ");
		}		
		
		queryStr.append("   COD.cadre_txn_details_id is null group by CTD.complete_status ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		
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
	
	public List<Object[]> getDayWiseReportForDates(Date fromDate, Date toDate)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.cadreTxnDetails.surveyTime, count(distinct model.cadreTxnDetails.cadreSurveyUserId), count(model.cadreTxnDetails.cadreTxnDetailsId), " +
				" SUM(CASE WHEN model.isDeleted IN ('Y') THEN 1 else 0 END) , sum(model.cadreTxnDetails.paidAmount)  from CadreOtpDetails model where ");
		queryStr.append(" date(model.cadreTxnDetails.surveyTime) >= :fromDate and date(model.cadreTxnDetails.surveyTime) <= :toDate group by model.cadreTxnDetails.surveyTime " +
				" order by model.cadreTxnDetails.surveyTime ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}	
	public List<Object[]> getLocationWiseTransactionsByDates(Date fromDate, Date toDate,List<Long> locationIdList,String queryString)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.cadreTxnDetails.cadreSurveyUser.cadreSurveyUserId , model.cadreTxnDetails.cadreSurveyUser.userName, " +
				" model.cadreTxnDetails.constituency.constituencyId, model.cadreTxnDetails.constituency.name, " +
				" count(distinct model.cadreTxnDetails.surveyTime ), " +
				" sum(case when model.cadreTxnDetails.completeStatus in('Y') then 1 else 0 end) ," +
				" sum(case when model.cadreTxnDetails.completeStatus in('Y','N') then 1 else 0 end), " +
				" sum(model.cadreTxnDetails.totalAmount) " +
				" from CadreOtpDetails model  ");
		
		queryStr.append(" where (date(model.cadreTxnDetails.surveyTime) >= :fromDate and date(model.cadreTxnDetails.surveyTime) <= :toDate) " );
		
		if(queryString != null && queryString.length()>0)
		{
			queryStr.append(" "+queryString+" ");
		}
		queryStr.append(" group by model.cadreTxnDetails.cadreSurveyUserId,model.cadreTxnDetails.constituency.constituencyId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		if(queryString != null && queryString.length()>0)
		{
			query.setParameterList("locationIdList", locationIdList);
		}
		
		return query.list();
	}	
	
	public Integer updateIsDeleted(String mobileNo)
	{
	 
		Query query = getSession().createQuery("update CadreOtpDetails model set model.isDeleted = 'Y' where model.isDeleted = 'N'" +
				" and model.mobileNo = :mobileNo");
		query.setParameter("mobileNo", mobileNo);
		return query.executeUpdate();
	}
	
	/*public String checkOTPValid(String mobileNo,String otp,String refNo)
	{
		Query query = getSession().createQuery("select model.otpNo from CadreOtpDetails model where model.otpNo = :otp and model.otpReferenceId =:refNo " +
				" and model.mobileNo = :mobileNo");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("otp", otp);
		query.setParameter("refNo", refNo);
		return (String) query.uniqueResult();
	}*/
	
	public List<String> checkOTPValid(String mobileNo,String otp,Long  userId)
	{
		Query query = getSession().createQuery("select model.otpNo from CadreOtpDetails model where " +
				" model.otpNo = :otp and model.mobileNo =:mobileNo " +
				" and model.cadreSurveyUserId = :userId order by model.cadreOtpDetailsId");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("otp", otp);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<String> checkOTP(String otp)
	{
		Query query = getSession().createQuery("select model.otpNo from CadreOtpDetails model where " +
				" model.otpNo = :otp ");
		query.setParameter("otp", otp);
		return query.list();
	}
	
	public List<String> checkForMobile(String mobileNo)
	{
		Query query = getSession().createQuery("select model.mobileNo from CadreOtpDetails model where " +
				" model.mobileNo =:mobileNo ");
		query.setParameter("mobileNo", mobileNo);
		return query.list();
	}
	
	public Long getOTPTxnCountByDate(Date searchDate)
	{
		Query query = getSession().createQuery("select count(distinct model.mobileNo) from CadreOtpDetails model where " +
				" date(model.insertedTime) =:searchDate");
		if(searchDate != null)
		query.setDate("searchDate", searchDate);
		return (Long) query.uniqueResult();
	}
	
	
}
