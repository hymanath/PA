package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;

public class CadreTabRecordsStatusDAO extends GenericDaoHibernate<CadreTabRecordsStatus, Long> implements ICadreTabRecordsStatusDAO{

	public CadreTabRecordsStatusDAO() {
		super(CadreTabRecordsStatus.class);
	}
	
	public Integer deleteExstngCadreTdpRecords(Long cadreSurveyUserId,Long tabUserInfoId,Date surveyDate)
	{
		StringBuilder queryStr=new StringBuilder();
		  	queryStr.append(" delete from CadreTabRecordsStatus model" +
				" where model.cadreSurveyUserId = :cadreSurveyUserId" +
				" and model.tabUserInfoId = :tabUserInfoId ");
		if(surveyDate != null)
			queryStr.append(" and date(model.surveyDate) = :surveyDate ");
		
		Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
			query.setParameter("tabUserInfoId", tabUserInfoId);
			if(surveyDate != null)
			query.setDate("surveyDate", surveyDate);
		
			return query.executeUpdate();
	}


	public List<Object[]> dataReConsalationOverView(Long constistuencyId,
			Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreSurveyUser.userName," +
				  " model.imeiNo,"  +
				  " model1.cadreRegUser.user.userName," +
				  " sum(model.totalRecords),sum(model.pending)," +
				  " sum(model.kafkaPending)," +
				  " sum(model.kafkaSync)," +
				  " model.cadreSurveyUserId" +
				  " from CadreTabRecordsStatus model," +
				  " CadreRegUserTabUser model1," +
				  " CadreSurveyUserAssignDetails model2 " +
				  " where model.cadreSurveyUserId = model2.cadreSurveyUserId and " +
				  " model.cadreSurveyUserId = model1.cadreSurveyUserId and " +
				  " model1.cadreRegUser.userType = 'FM' ");
		 if(constistuencyId != null && constistuencyId.longValue()>0l){
			 sb.append(" and model2.constituencyId =:constistuencyId ");
		 }
		 if(fromDate != null && toDate != null){
			sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		 }
		
		   sb.append(" group by model.cadreSurveyUserId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(constistuencyId != null && constistuencyId.longValue()>0l){
			 query.setParameter("constistuencyId", constistuencyId);
		   }
		
		return query.list();
	}

	public Object[] dataReConsalationTotalOverView(Long constistuencyId,
			Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct model.imeiNo)," +
				  " sum(model.totalRecords)," +
				  " sum(model.sync)," +
				  " sum(model.pending) " +
				 " from CadreTabRecordsStatus model,CadreSurveyUserAssignDetails model1" +
				 " where model.cadreSurveyUserId = model1.cadreSurveyUserId");
				
		 if(constistuencyId != null && constistuencyId.longValue()>0l){
			 sb.append(" and model1.constituencyId =:constistuencyId ");
		 }

		if (fromDate != null && toDate != null){
			sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		}
		Query query = getSession().createQuery(sb.toString());

		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(constistuencyId != null && constistuencyId.longValue()>0l){
			 query.setParameter("constistuencyId", constistuencyId);
		   }
		return (Object[])query.uniqueResult();
	}

	public List<Object[]> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,Date fromDate,Date toDate){
		StringBuilder sb =new StringBuilder();
		sb.append(" SELECT date(model.surveyDate)," +
					  " model.tabUserInfo.name," +
					  " model.tabUserInfo.mobileNo," +
					  " min(model.minRecordTime) ," +
					  " max(model.maxRecordTime) , " +
					  " SUM(model.totalRecords) ," +
					  " SUM(model.sync)," +
					  " SUM(model.pending)," +
					  " SUM(model.kafkaPending)," +
					  " SUM(model.kafkaSync)," +
					  " model.tabUserInfo.tabUserInfoId,  " +
					  " model.tabUserInfo.imgPath " +
				  " FROM " +
					  " CadreTabRecordsStatus model," +
					  " CadreSurveyUserAssignDetails model1 " +
				  " WHERE model.cadreSurveyUserId = model1.cadreSurveyUserId and " +
					  " model.cadreSurveyUserId =:cadreSrveyUserId " );
				 if(constituencyId != null && constituencyId.longValue()>0l){
					 sb.append(" and model1.constituencyId =:constituencyId ") ;
				 }
		 
				  if(fromDate != null && toDate != null){
					  sb.append(" and date(model.surveyDate) between :fromDate and  :toDate ") ;
				  }
				  sb.append(" group by date(model.surveyDate), model.tabUserInfo.tabUserInfoId ");
		
		   Query qry = getSession().createQuery(sb.toString());
		   if(cadreSrveyUserId != null && cadreSrveyUserId.longValue()>0l){
				qry.setParameter("cadreSrveyUserId", cadreSrveyUserId);
		   }
		   if(fromDate !=null && toDate !=null){
			   qry.setDate("fromDate", fromDate);
			   qry.setDate("toDate", toDate);
		   }
		   if(constituencyId != null && constituencyId.longValue()>0l){
			   qry.setParameter("constituencyId", constituencyId);
		   }
		
		return qry.list();
	}
	
}