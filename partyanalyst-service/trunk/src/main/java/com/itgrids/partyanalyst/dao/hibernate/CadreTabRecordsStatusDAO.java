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
				" where model.cadreSurveyUserId := cadreSurveyUserId" +
				" and model.tabUserInfoId := tabUserInfoId");
		if(surveyDate != null)
			queryStr.append("and date(model.surveyDate) :=surveyDate");
		
		Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
			query.setParameter("tabUserInfoId", tabUserInfoId);
			if(surveyDate != null)
			query.setParameter("surveyDate", surveyDate);
		
			return query.executeUpdate();
	}


	public List<Object[]> dataReConsalationOverView(Long constistuencyId,
			Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("select model.cadreSurveyUser.userName,model.imeiNo,"
				 + " model1.cadreRegUser.user.userName,count(model.totalRecords),count(model.pending)," +
				 " count(model.kafkaPending),count(model.serverPending)" +
				" from CadreTabRecordsStatus model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
				 " where model.cadreSurveyUserId = model2.cadreSurveyUserId and model.cadreSurveyUserId = model1.cadreSurveyUserId and  " +
				 " model2.constituencyId = :constistuencyId ");
				
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		
		   sb.append(" group by model.cadreSurveyUser.userName");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		query.setParameter("constistuencyId", constistuencyId);
		
		return query.list();
	}

	public List<Object[]> dataReConsalationTotalOverView(Long constistuencyId,
			Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.imeiNo),count(model.totalRecords),count(model.sync),count(model.pending)"
				+ " from CadreTabRecordsStatus model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2"
				+ " where model.cadreSurveyUserId = model2.cadreSurveyUserId and model.cadreSurveyUserId = model1.cadreSurveyUserId and  "
				+ " model2.constituencyId = :constistuencyId ");

		if (fromDate != null && toDate != null)
			sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		Query query = getSession().createQuery(sb.toString());

		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		query.setParameter("constistuencyId", constistuencyId);

		return query.list();
	}

	public List<Object[]> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,Date fromDate,Date toDate){
		StringBuilder sb =new StringBuilder();
		sb.append(" select date(model.surveyDate)," +
				  " model.tabUserInfo.name," +
				  " model.tabUserInfo.mobileNo," +
				  " model.minRecordTime ," +
				  " model.maxRecordTime , " +
				  " model.totalRecords ," +
				  " model.sync ," +
				  " model.pending  from " +
				  " CadreTabRecordsStatus model," +
				  " CadreSurveyUserAssignDetails model1 " +
				  " where model.cadreSurveyUserId = model1.cadreSurveyUserId and " +
				  " model.cadreSurveyUser.cadreSurveyUserId =:cadreSrveyUserId and " +
				  " model1.constituencyId =:constituencyId ") ;
		
				  if(fromDate != null && toDate != null){
				  sb.append(" and date(model.surveyDate) between :fromDate and  :toDate ") ;
				  }
				  sb.append(" group by model.surveyDate, model.tabUserInfo.tabUserInfoId ");
		
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