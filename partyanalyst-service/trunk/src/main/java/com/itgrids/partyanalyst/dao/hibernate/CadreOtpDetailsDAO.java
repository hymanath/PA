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

		queryStr.append(" select  model.isDeleted, sum(model2.paidAmount), sum(model2.pendingAmount), " +
				" sum(model2.totalAmount),count(model2.cadreTxnDetailsId) from CadreOtpDetails model, CadreTxnDetails model2  where  ");	

		if(todayDate == null) // yesterDay transaction details
		{
			queryStr.append(" date(model2.surveyTime) = :searchDate  and ");
		}
		else if(searchDate ==null) // total transaction details
		{
			queryStr.append(" date(model2.surveyTime) <= :todayDate and ");
		}
		
		else if(todayDate != null) // this week transaction details
		{
			queryStr.append(" (date(model2.surveyTime) >= :searchDate and date(model2.surveyTime) <= :todayDate) and ");
		}		
		
		queryStr.append(" model.cadreOtpDetailsId = model2.cadreOtpDetails.cadreOtpDetailsId  group by  model.isDeleted order by  model.isDeleted ");
		
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
		
		queryStr.append(" select COD.is_deleted, sum(CTD.total_amount),count( COD.cadre_otp_details_id) from  " +
				"   cadre_txn_details CTD RIGHT JOIN cadre_otp_details COD ON CTD.cadre_otp_details_id = COD.cadre_otp_details_id " +
				"  where   ");
		
		if(todayDate == null) // yesterDay transaction details
		{
			queryStr.append(" ( date(CTD.survey_time) = :searchDate or date(COD.inserted_time) )  ");
		}
		else if(searchDate ==null) // total transaction details
		{
			queryStr.append(" ( date(CTD.survey_time) <= :todayDate or date(COD.inserted_time) )  ");
		}
		else if(todayDate != null) // all transaction details
		{
			queryStr.append("  ( ( date(CTD.survey_time) >= :searchDate and date(CTD.survey_time) <= :todayDate ) or " +
					" (date(COD.inserted_time)  >= :searchDate and date(COD.inserted_time) <= :todayDate ) )  ");
		}		
		
		queryStr.append("    group by COD.is_deleted order by COD.is_deleted ");
		
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
		
		queryStr.append(" select distinct model.surveyTime, count(distinct model.cadreSurveyUserId), SUM(model.totalAmount), " +
				"  sum(model.paidAmount)  from CadreTxnDetails model where ");
		queryStr.append(" date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate group by date(model.surveyTime) " +
				" order by date(model.surveyTime) desc ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}	
	/*public List<Object[]> getLocationWiseTransactionsByDates(Date fromDate, Date toDate,List<Long> locationIdList,String queryString)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct model.cadreSurveyUser.cadreSurveyUserId , model.cadreSurveyUser.userName, " +
				" model.constituency.constituencyId, model.constituency.name, " +
				"  COUNT(distinct(date( model.surveyTime))) , " +
				" count(model.cadreTxnDetailsId) ," +
				" SUM(model.totalAmount), " +
				" sum(model.paidAmount) " +
				" from CadreTxnDetails model  ");
		
		queryStr.append(" where (date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate) " );
		
		if(queryString != null && queryString.length()>0)
		{
			queryStr.append(" "+queryString+" ");
		}
		queryStr.append(" group by  model.cadreSurveyUser.cadreSurveyUserId ,model.constituency.constituencyId");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		if(queryString != null && queryString.length()>0)
		{
			query.setParameterList("locationIdList", locationIdList);
		}
		
		return query.list();
	}*/
	
	public List<Object[]> getLocationWiseTransactionsByDates(Date fromDate, Date toDate,List<Long> locationIdList,String queryString)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select CTD.cadre_survey_user_id,CSU.user_name,CTD.constituency_id,C.name,count(distinct date(CTD.survey_time)),count(CTD.cadre_txn_details_id),sum(CTD.total_amount),"+
						" sum(CTD.paid_amount), sum(paid_amount) from cadre_txn_details CTD,cadre_survey_user CSU,constituency C where "+ 
						" CTD.cadre_survey_user_id = CSU.cadre_survey_user_id and CTD.constituency_id = C.constituency_id ");
		
		queryStr.append(" and date(CTD.survey_time) between :fromDate and :toDate " );
		
		if(queryString != null && queryString.length()>0)
		{
			queryStr.append(" "+queryString+" ");
		}
		queryStr.append(" group by CTD.cadre_survey_user_id,CTD.constituency_id ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
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
				" model.otpNo = :otp and model.isDeleted = 'N'");
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
	
	public List<Long> getCadreotpDateils(String otpNo)
	{
		Query query = getSession().createQuery("select model.cadreOtpDetailsId from CadreOtpDetails model where model.otpNo = :otpNo");
		query.setParameter("otpNo", otpNo);
		return query.list();
	}

	
}
