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


	public List<Object[]> dataReConsalationOverView(Long stateId,Long constistuencyId,
			Date fromDate, Date toDate,Long districtId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreSurveyUser.userName," +
				  "  model.imeiNo,"  +
				  " model.cadreSurveyUser.cadreSurveyUserId," +
				  " sum(model.totalRecords),sum(model.pending)," +
				  " sum(model.kafkaPending)," +
				  " sum(model.kafkaSync)," +
				  " model.cadreSurveyUserId," +
				  " sum(model.sync),model.insertedTime " +
				  " from CadreTabRecordsStatus model," +
				  " TabUserInfo model1," +
				  " CadreSurveyUserAssignDetails model2 " +
				  " where model.cadreSurveyUserId = model2.cadreSurveyUserId and " +
				  //" model.cadreSurveyUserId = model1.cadreSurveyUserId  and " +
				  " model.tabUserInfoId = model1.tabUserInfoId and model1.isEnabled = 'Y' "+
				  "  and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y' and model2.cadreSurveyUser.isEnabled = 'Y' ");
		 if(districtId != null && districtId.longValue()>0l)
		 {
			 sb.append(" and model2.constituency.district.districtId =:districtId");
		 }else{
			 	if(stateId != null && stateId.longValue() == 1l){
				 	sb.append(" and model2.constituency.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					sb.append(" and model2.constituency.district.districtId between 1 and 10 ");
				}
		}
		 if(constistuencyId != null && constistuencyId.longValue()>0l){
			 sb.append(" and model2.constituency.constituencyId =:constistuencyId ");
		 }
		 if(fromDate != null && toDate != null){
			sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		 }
		
		 sb.append(" group by model.cadreSurveyUserId ");
		 if (districtId != null && districtId.longValue() > 0l) {
				sb.append(", model2.constituency.constituencyId ");
			} else {
				sb.append(" ,model2.constituency.district.districtId");
			}
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if (constistuencyId != null && constistuencyId.longValue() > 0l) {
			query.setParameter("constistuencyId", constistuencyId);
		}
		if (districtId != null && districtId.longValue() > 0l) {
			query.setParameter("districtId", districtId);
		}

		return query.list();
	}

	public List<Object[]> dataReConsalationTotalOverView(Long stateId,Long constistuencyId,
			Date fromDate, Date toDate, Long districtId) {
		StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.imeiNo," +
	          " sum(model.totalRecords),sum(model.sync),sum(model.pending)," +
	          " sum(model.kafkaSync),sum(model.kafkaPending)," +
	          " model.cadreSurveyUserId " +
	          " from CadreTabRecordsStatus model," +
	          " TabUserInfo model1," +
	          " CadreSurveyUserAssignDetails model2 " +
	          " where model.cadreSurveyUserId = model2.cadreSurveyUserId and " +
	         // " model.cadreSurveyUserId = model1.cadreSurveyUserId and " +
	          " model.tabUserInfoId = model1.tabUserInfoId and model1.isEnabled = 'Y'" +
	          " and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y' " +
	          " and model2.cadreSurveyUser.isEnabled = 'Y'  ");
	     if(districtId != null && districtId.longValue()>0l)
	     {
	       sb.append(" and model2.constituency.district.districtId =:districtId");
	     }else{
	         if(stateId != null && stateId.longValue() == 1l){
	           sb.append(" and model2.constituency.district.districtId between 11 and 23 ");
	        }else if(stateId != null && stateId.longValue() == 36l){
	          sb.append(" and model2.constituency.district.districtId between 1 and 10 ");
	        }
	    }
	     if(constistuencyId != null && constistuencyId.longValue()>0l){
	       sb.append(" and model2.constituency.constituencyId =:constistuencyId ");
	     }
	     if(fromDate != null && toDate != null){
	      sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
	     }
	    
	     sb.append(" group by model.cadreSurveyUserId,");
	     if (districtId != null && districtId.longValue() > 0l) {
	        sb.append(" model2.constituency.constituencyId ");
	      } else {
	        sb.append(" model2.constituency.district.districtId");
	      }
	    Query query = getSession().createQuery(sb.toString());
	    if(fromDate != null && toDate != null){
	      query.setDate("fromDate", fromDate);
	      query.setDate("toDate", toDate);
	    }
	    if (constistuencyId != null && constistuencyId.longValue() > 0l) {
	      query.setParameter("constistuencyId", constistuencyId);
	    }
	    if (districtId != null && districtId.longValue() > 0l) {
	      query.setParameter("districtId", districtId);
	    }

	    return query.list();
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
					  " model.tabUserInfo.imgPath, " +
					  " model.cadreSurveyUserId " +
				  " FROM " +
					  " CadreTabRecordsStatus model," +
					  " CadreSurveyUserAssignDetails model1,TabUserInfo model2  " +
				  " WHERE model.cadreSurveyUserId = model1.cadreSurveyUserId and " +
				  " model.tabUserInfoId = model2.tabUserInfoId and model2.isEnabled = 'Y'" + 
				  " and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y' " +
					  "and  model.cadreSurveyUserId =:cadreSrveyUserId  and model1.cadreSurveyUser.isEnabled = 'Y'  " );
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