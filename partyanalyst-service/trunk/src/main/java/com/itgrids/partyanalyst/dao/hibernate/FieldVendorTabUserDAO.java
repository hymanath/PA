package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.model.FieldVendorTabUser;

public class FieldVendorTabUserDAO extends GenericDaoHibernate<FieldVendorTabUser, Long> implements IFieldVendorTabUserDAO{

	public FieldVendorTabUserDAO() {
		super(FieldVendorTabUser.class);
		
	}

	public Long getTotalDataCollectorsCount(Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
						" from TdpCadreEnrollmentYear model" +
						" where model.tdpCadre.enrollmentYear = 2014");
		if(startDate != null && endDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :startDate and :endDate");
		
		sb.append(" and model.tdpCadre.isDeleted = 'N'" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return (Long) query.uniqueResult();
	}
	
	public Long getActiveDataCollectorsCount(Date lastHourTime,Date today){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
						" from TdpCadreEnrollmentYear model" +
						" where model.tdpCadre.enrollmentYear = 2014");
		if(lastHourTime != null && today != null)
			sb.append(" and model.tdpCadre.surveyTime between :lastHourTime and :today");
		
		sb.append(" and model.tdpCadre.isDeleted = 'N'" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		if(lastHourTime != null && today != null){
			query.setParameter("lastHourTime", lastHourTime);
			query.setParameter("today", today);
		}
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getStatusWiseIssuesDetails(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select CRI.cadreSurveyUser.cadreSurveyUserId," +
					" CRI.cadreSurveyUser.userName," +
					" CRI.tabUserInfo.tabUserInfoId," +
					" CRI.tabUserInfo.name," +
					" CRI.tabUserInfo.mobileNo," +
					" CRI.userAddress.state.stateId," +
					" CRI.userAddress.district.districtId," +
					" CRI.userAddress.district.districtName," +
					" CRI.userAddress.constituency.constituencyId," +
					" CRI.userAddress.constituency.name," +
					" FVTU.fieldVendor.fieldVendorId," +
					" FVTU.fieldVendor.vendorName" +
					" from CadreRegIssue CRI,FieldVendorTabUser FVTU" +
					" where CRI.cadreSurveyUser.cadreSurveyUserId = FVTU.cadreSurveyUser.cadreSurveyUserId" +
					" and CRI.cadreRegIssueType.cadreRegIssueTypeId = :issueTypeId" +
					" and CRI.cadreRegIssueStatus.cadreRegIssueStatusId = :statusTypeId");
		if(fromDate != null && toDate != null)
			sb.append(" and date(CRI.insertedTime) between :fromDate and :toDate");
		
		sb.append(" and FVTU.isDeleted = 'N'" +
					" and FVTU.fieldVendor.isActive = 'Y'");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		query.setParameter("issueTypeId", issueTypeId);
		query.setParameter("statusTypeId", statusTypeId);
		
		return query.list();
	}
	
	public List<Object[]> getUserWiseIssuesCounts(Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select CRI.cadreSurveyUser.cadreSurveyUserId," +
					" CRI.tabUserInfo.tabUserInfoId," +
					" CRI.cadreRegIssueStatus.cadreRegIssueStatusId," +
					" count(CRI.cadreRegIssueId)," +
					" CRI.userAddress.constituency.constituencyId" +
					" from CadreRegIssue CRI");
		if(fromDate != null && toDate != null)
			sb.append(" where date(CRI.insertedTime) between :fromDate and :toDate");
		
		sb.append(" group by CRI.cadreSurveyUser.cadreSurveyUserId,CRI.tabUserInfo.tabUserInfoId,CRI.cadreRegIssueStatus.cadreRegIssueStatusId,CRI.userAddress.constituency.constituencyId" +
					" order by CRI.cadreRegIssueStatus.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
}
