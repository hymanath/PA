package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataVerification;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TdpCadreDataVerificationDAO extends GenericDaoHibernate<TdpCadreDataVerification, Long> implements ITdpCadreDataVerificationDAO{

	public TdpCadreDataVerificationDAO() {
		super(TdpCadreDataVerification.class);
		
	}
	public Long getActiveTeamMemberCnt(Long stateId){
		
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append("select  count(distinct model.cadreRegUser.cadreRegUserId)  from TdpCadreDataVerification model where model.tdpCadre.isDeleted='N' ");
		  
		  	if(stateId != null && stateId.longValue() == 1l){
	    	    queryStr.append("  and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append("  and model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
			}else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
			} 
		  
		  queryStr.append(" and date(model.tdpCadre.surveyTime) = :today ");	 
		 
	    
		 Query query = getSession().createQuery(queryStr.toString());
	     query.setDate("today", new DateUtilService().getCurrentDateAndTime());
		
	     return (Long) query.uniqueResult();
	}
	
	public Long getTotalRegistered(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.totalRecords)" +
					" from TabUserEnrollmentInfo model" +
					" where model.enrollmentYearId = 4");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.surveyTime) between :fromDate and :toDate");
		
		 if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and model.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and model.constituency.district.districtId between 1 and 10 ");
			}

		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.constituency.constituencyId = :constituencyId");
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
		
		sb.append(" ");
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			sb.append(" group by model.cadreSurveyUserId");
		else if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" group by model.constituency.constituencyId");
		else if(districtId != null && districtId.longValue() > 0l)
			sb.append(" group by model.constituency.district.districtId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		
		return (Long) query.uniqueResult();
		
	}
	
	public Long getVerifiedPassedCount(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
		StringBuilder sb =  new StringBuilder();
		sb.append("select count(distinct model.tdpCadreId)" +
				" from TdpCadreDataVerification model" +
				" where");
		if(fromDate != null && toDate != null)
			sb.append(" date(model.verifiedTime) between :fromDate and :toDate");
		
		 if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and model.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and model.districtId between 1 and 10 ");
			}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.constituencyId = :constituencyId");
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
		
		sb.append(" and model.dataRejectReason.dataRejectReasonId is null");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		
		return (Long) query.uniqueResult();
		
	}
	
	public Long getVerifiedRejectedCount(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
		StringBuilder sb =  new StringBuilder();
		sb.append("select count(distinct model.tdpCadreId)" +
				" from TdpCadreDataVerification model" +
				" where");
		if(fromDate != null && toDate != null)
			sb.append(" date(model.verifiedTime) between :fromDate and :toDate");
		
		 if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and model.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and model.districtId between 1 and 10 ");
			}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.constituencyId = :constituencyId");
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
		
		sb.append(" and model.dataRejectReason.dataRejectReasonId is not null");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
			query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		
		return (Long) query.uniqueResult();
		
	}
	
public 	List<Object[]> getCadreSurveyUserDetails(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select model.cadreSurveyUserId," +
			" model.mobileNo," +
			" model.username," +
			" model.tabUserName," +
			" sum(model.totalRecords)," +
			" model.tabUserInfo.tabUserInfoId" +
			" from TabUserEnrollmentInfo model" +
			" where model.enrollmentYearId = 4");
	if(fromDate != null && toDate != null)
		sb.append(" and date(model.surveyTime) between :fromDate and :toDate");
	
	 if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.constituency.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.constituency.district.districtId between 1 and 10 ");
		}

	if(districtId != null && districtId.longValue() > 0l)
		sb.append(" and model.constituency.district.districtId = :districtId");
	if(constituencyId != null && constituencyId.longValue() > 0l)
		sb.append(" and model.constituency.constituencyId = :constituencyId");
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
	sb.append(" group by model.tabUserInfo.tabUserInfoId,model.cadreSurveyUserId");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(districtId != null && districtId.longValue() > 0l)
		query.setParameter("districtId", districtId);
	if(constituencyId != null && constituencyId.longValue() > 0l)
		query.setParameter("constituencyId", constituencyId);
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
	
	return  query.list();
}
	
public List<Object[]> getCadreVerfPassedDetails(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
	StringBuilder sb =  new StringBuilder();
	sb.append("select model.cadreSurveyUser.cadreSurveyUserId," +
			" count(distinct model.tdpCadreId)," +
			" model.tabUserInfo.tabUserInfoId" +
			" from TdpCadreDataVerification model" +
			" where");
	if(fromDate != null && toDate != null)
		sb.append(" date(model.verifiedTime) between :fromDate and :toDate");
	
	 if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.districtId between 1 and 10 ");
		}
	if(districtId != null && districtId.longValue() > 0l)
		sb.append(" and model.districtId = :districtId");
	if(constituencyId != null && constituencyId.longValue() > 0l)
		sb.append(" and model.constituencyId = :constituencyId");
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
	
	sb.append(" and model.dataRejectReason.dataRejectReasonId is null ");
	sb.append(" group by model.tabUserInfo.tabUserInfoId,model.cadreSurveyUserId");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(districtId != null && districtId.longValue() > 0l)
		query.setParameter("districtId", districtId);
	if(constituencyId != null && constituencyId.longValue() > 0l)
		query.setParameter("constituencyId", constituencyId);
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
	
	return query.list();
	
}

	public List<Object[]> getDataVerifiedDetailsOverView(Date fromDate,Date toDate,String locationType,Long locationVal,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" model.districtId,");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("constituency"))
			sb.append(" model.constituencyId,");
		sb.append(" count(distinct model.tdpCadreId) from TdpCadreDataVerification model" +
					" where");
		if(type != null && type.trim().equalsIgnoreCase("approved"))
			sb.append(" model.dataRejectReasonId is null");
		else if(type != null && type.trim().equalsIgnoreCase("rejected"))
			sb.append(" model.dataRejectReasonId is not null");
		if(locationType != null && locationType.trim().equalsIgnoreCase("district")){
			if(locationVal != null && locationVal.longValue() == 1l)
				sb.append(" and model.constituency.district.districtId between 11 and 23");
			else if(locationVal != null && locationVal.longValue() == 36l)
				sb.append(" and model.constituency.district.districtId between 1 and 10");
		}
		else if(locationType != null && locationType.trim().equalsIgnoreCase("constituency") && locationVal != null && locationVal.longValue() > 0l)
			sb.append(" and model.constituency.district.districtId = :locationVal");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.verifiedTime) between :fromDate and :toDate"); 
		
		if(locationType != null && locationType.equalsIgnoreCase("district"))
			sb.append(" group by model.districtId");
		 else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
			sb.append(" group by model.constituencyId");
		
		Query query = getSession().createQuery(sb.toString());
		if(locationType != null && locationType.trim().equalsIgnoreCase("constituency") && locationVal != null && locationVal.longValue() > 0l)
			 query.setParameter("locationVal", locationVal);
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate", fromDate);
			 query.setDate("toDate", toDate);
		 }
		
		return query.list();
	}
public List<Object[]> getCadreVerfRejectedDetails(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate){
	StringBuilder sb =  new StringBuilder();
	sb.append("select model.cadreSurveyUser.cadreSurveyUserId," +
			" count(distinct model.tdpCadreId)," +
			" model.tabUserInfo.tabUserInfoId" +
			" from TdpCadreDataVerification model" +
			" where");
	if(fromDate != null && toDate != null)
		sb.append(" date(model.verifiedTime) between :fromDate and :toDate");
	
	 if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.districtId between 1 and 10 ");
		}
	if(districtId != null && districtId.longValue() > 0l)
		sb.append(" and model.districtId = :districtId");
	if(constituencyId != null && constituencyId.longValue() > 0l)
		sb.append(" and model.constituencyId = :constituencyId");
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		sb.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
	
	sb.append(" and model.dataRejectReason.dataRejectReasonId is not null ");
	sb.append(" group by model.tabUserInfo.tabUserInfoId,model.cadreSurveyUserId");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(districtId != null && districtId.longValue() > 0l)
		query.setParameter("districtId", districtId);
	if(constituencyId != null && constituencyId.longValue() > 0l)
		query.setParameter("constituencyId", constituencyId);
	if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
	
	return query.list();
	
}
}
