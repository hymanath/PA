package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.model.FieldVendorTabUser;
import com.itgrids.partyanalyst.utils.IConstants;

public class FieldVendorTabUserDAO extends GenericDaoHibernate<FieldVendorTabUser, Long> implements IFieldVendorTabUserDAO{

	public FieldVendorTabUserDAO() {
		super(FieldVendorTabUser.class);
		
	}
	
	/*public Long getTotalCadreSurveyUserForToday(Date startDate,Date endDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select *" +
							" from CadreSurveyUserAssignDetails model" +
							" where model.");
	}*/

	public Long getTotalDataCollectorsCount(Date startDate,Date endDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
						" from TdpCadreEnrollmentYear model" +
						" where model.tdpCadre.enrollmentYear = 2014");
		if(startDate != null && endDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :startDate and :endDate");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.tdpCadre.userAddress.state.stateId = 1 ");
		}
		
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
	
	public Long getOverAllTotalDataCollectorsCount(Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
						" from CadreSurveyUserAssignDetails model" +
						" where model.cadreSurveyUser.isEnabled = 'Y'" +
						" and model.cadreSurveyUser.isDeleted = 'N'");
		/*if(startDate != null && endDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :startDate and :endDate");*/
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model.constituency.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.constituency.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.constituency.state.stateId = 1 ");
		}
		
		/*sb.append(" and model.tdpCadre.isDeleted = 'N'" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'");*/
		Query query = getSession().createQuery(sb.toString());
		/*if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}*/
		return (Long) query.uniqueResult();
	}
	
	public Long getActiveDataCollectorsCount(Date lastHourTime,Date today,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
						" from TdpCadreEnrollmentYear model" +
						" where model.tdpCadre.enrollmentYear = 2014");
		if(lastHourTime != null && today != null)
			sb.append(" and model.tdpCadre.surveyTime between :lastHourTime and :today");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.tdpCadre.userAddress.state.stateId = 1 ");
		}
		
		
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
		sb.append("select distinct CRI.cadreSurveyUser.cadreSurveyUserId," +
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
	
	public List<Object[]> getStatusWiseIssuesDetailsNew(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct CRI.cadreSurveyUser.cadreSurveyUserId," +
					" CRI.cadreSurveyUser.userName," +
					" CRI.tabUserInfo.tabUserInfoId," +
					" CRI.tabUserInfo.name," +
					" CRI.tabUserInfo.mobileNo," +
					" CRI.userAddress.state.stateId," +
					" CRI.userAddress.district.districtId," +
					" CRI.userAddress.district.districtName," +
					" CRI.userAddress.constituency.constituencyId," +
					" CRI.userAddress.constituency.name," +
					" CRUTU.cadreRegUser.user.userName," +
					" CRI.cadreRegIssueId"+
					" from CadreRegIssue CRI,CadreRegUserTabUser CRUTU" +
					" where CRI.cadreSurveyUser.cadreSurveyUserId = CRUTU.cadreSurveyUser.cadreSurveyUserId" +
					" and CRI.cadreRegIssueStatus.cadreRegIssueStatusId = :statusTypeId" +
					" and CRUTU.isDeleted = 'N' and CRUTU.cadreRegUser.userType = 'FM' " );
		
		if(issueTypeId !=null && issueTypeId.longValue()>0l){
			sb.append(" and CRI.cadreRegIssueType.cadreRegIssueTypeId = :issueTypeId");
		}
		
		if(fromDate != null && toDate != null)
			sb.append(" and date(CRI.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and CRI.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  CRI.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and CRI.userAddress.state.stateId = 1 ");
		}
		
		//sb.append(" and CRUTU.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(issueTypeId !=null && issueTypeId>0l){
			query.setParameter("issueTypeId", issueTypeId);
		}
		query.setParameter("statusTypeId", statusTypeId);
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyIssueWiseOverAllDetails(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct CRI.cadreSurveyUser.cadreSurveyUserId," +
					" CRI.cadreSurveyUser.userName," +
					" CRI.tabUserInfo.tabUserInfoId," +
					" CRI.tabUserInfo.name," +
					" CRI.tabUserInfo.mobileNo," +
					" CRI.userAddress.state.stateId," +
					" CRI.userAddress.district.districtId," +
					" CRI.userAddress.district.districtName," +
					" CRI.userAddress.constituency.constituencyId," +
					" CRI.userAddress.constituency.name," +
					" CRUTU.cadreRegUser.user.userName," +
					" CRI.cadreRegIssueType.cadreRegIssueTypeId," +
					" CRI.cadreRegIssueType.issueType," +
					" CRI.cadreRegIssueStatus.cadreRegIssueStatusId," +
					" CRI.cadreRegIssueStatus.status," +
					" CRI.description," +
					" CRI.insertedTime," +
					" CRI.cadreRegIssueId"+
					" from CadreRegIssue CRI,CadreRegUserTabUser CRUTU" +
					" where CRI.cadreSurveyUser.cadreSurveyUserId = CRUTU.cadreSurveyUser.cadreSurveyUserId" +
					" and CRI.cadreRegIssueStatus.cadreRegIssueStatusId = :statusTypeId " );
		
		if(issueTypeId !=null && issueTypeId.longValue()>0l){
			sb.append(" and CRI.cadreRegIssueType.cadreRegIssueTypeId = :issueTypeId");
		}
		
		if(fromDate != null && toDate != null)
			sb.append(" and date(CRI.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and CRI.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  CRI.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and CRI.userAddress.state.stateId = 1 ");
		}
		
		sb.append(" and CRUTU.isDeleted = 'N'" +
				" and CRUTU.cadreRegUser.userType = 'FM'" +
				" order by CRI.userAddress.state.stateId,CRI.userAddress.district.districtId,CRI.userAddress.constituency.constituencyId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(issueTypeId !=null && issueTypeId>0l){
			query.setParameter("issueTypeId", issueTypeId);
		}
		query.setParameter("statusTypeId", statusTypeId);
		
		return query.list();
	}
	
	public List<Object[]> getUserWiseIssuesCounts(Date fromDate,Date toDate,Long issueTypeId,Long issueStatusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select CRI.cadreSurveyUser.cadreSurveyUserId," +
					" CRI.tabUserInfo.tabUserInfoId," +
					" CRI.cadreRegIssueStatus.cadreRegIssueStatusId," +
					" count(CRI.cadreRegIssueId)," +
					" CRI.userAddress.constituency.constituencyId" +
					" from CadreRegIssue CRI" +
					" where " +
					"  CRI.cadreRegIssueStatusId = :issueStatusId");
		
		if(issueTypeId !=null && issueTypeId.longValue()>0l){
			sb.append(" and  CRI.cadreRegIssueTypeId = :issueTypeId ");
		}
		
		if(fromDate != null && toDate != null)
			sb.append(" and date(CRI.insertedTime) between :fromDate and :toDate");
		
		sb.append(" group by CRI.cadreSurveyUser.cadreSurveyUserId,CRI.tabUserInfo.tabUserInfoId,CRI.cadreRegIssueStatus.cadreRegIssueStatusId,CRI.userAddress.constituency.constituencyId" +
					" order by CRI.cadreRegIssueStatus.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(issueTypeId !=null && issueTypeId.longValue()>0l){
			query.setParameter("issueTypeId", issueTypeId);
		}
		query.setParameter("issueStatusId", issueStatusId);
		
		return query.list();
	}
	
	public String getVendorNameByCadreSurveyUserId(Long cadreSurveyUserId){
		Query query = getSession().createQuery("select model.fieldVendor.vendorName" +
									" from FieldVendorTabUser model" +
									" where model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId" +
									" and model.isDeleted = 'N'");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return (String) query.uniqueResult();
	}
	public List<Object[]> getVendorNameByCadreSurveyUserId(List<Long> cadreSurveyUserIds){
		Query query = getSession().createQuery("select model.fieldVendorId,model.fieldVendor.vendorName" +
									" from FieldVendorTabUser model" +
									" where model.cadreSurveyUser.cadreSurveyUserId in (:cadreSurveyUserIds) " +
									" and model.isDeleted = 'N'");
		query.setParameterList("cadreSurveyUserIds", cadreSurveyUserIds);
		return query.list();
	}
	
	public Long getPassiveDataCollectorsCount(Date today,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
						" from TdpCadreEnrollmentYear model" +
						" where model.tdpCadre.enrollmentYear = 2014");
		if(today != null)
			sb.append(" and date(model.tdpCadre.surveyTime) =:today");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.tdpCadre.userAddress.state.stateId = 1 ");
		}
		
		
		sb.append(" and model.tdpCadre.isDeleted = 'N'" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'");
		/*if(lastHourTime != null)
		sb.append(" and model.tdpCadre.surveyTime not in (:lastHourTime) ");*/
		Query query = getSession().createQuery(sb.toString());
		if(today != null)
			query.setParameter("today", today);
		/*if(lastHourTime != null)
			query.setParameter("lastHourTime", lastHourTime);*/
		return (Long) query.uniqueResult();
	}
	
	public Long getTotalDataCollectorsCountFrMnrtg(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.cadreSurveyUserId) " +
				" from CadreSurveyUser model,CadreSurveyUserAssignDetails model1");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(",CadreRegUserTabUser model2");
		sb.append(" where model.isDeleted = 'N' and model.isEnabled = 'Y'" +
				" and model.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				" and model1.isDeleted = 'N'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model2.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model2.isDeleted = 'N' and model2.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model1.constituency.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model1.constituency.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model1.constituency.state.stateId = 1 ");
		}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and  model1.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and  model1.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :userId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
	
	public Long getTotalRegisteredCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct model.cadreSurveyUserId)" +
				" from CadreSurveyUser model,CadreSurveyUserAssignDetails model1,TabUserInfo model3");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(",CadreRegUserTabUser model2");
		sb.append(" where model.isDeleted = 'N' and model.isEnabled = 'Y'" +
				" and model.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				" and model.cadreSurveyUserId = model3.cadreSurveyUser.cadreSurveyUserId" +
				" and model1.isDeleted = 'N'" +
				" and model3.isEnabled = 'Y'");
				//" and model3.isOtpVerified = 'Y'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model2.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model2.isDeleted = 'N' and model2.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model1.constituency.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model1.constituency.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model1.constituency.state.stateId = 1 ");
		}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and  model1.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and  model1.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :userId");
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
	
	public Long getTodayActiveMbrsCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId,Date today){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.cadreSurveyUserId)" +
						" from TabUserEnrollmentInfo model");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(",CadreRegUserTabUser model1");
		sb.append(" where model.enrollmentYearId = 4");
		
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = model1.cadreSurveyUserId" +
					" and model1.isDeleted = 'N' and model1.cadreRegUserId = :cadreRegUserId");
		if(today != null)
			sb.append(" and date(model.surveyTime) =:today");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.constituency.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.constituency.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.stateId in (1,36)");
		}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and  model.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and  model.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :userId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		if(today != null)
			query.setParameter("today", today);
		return (Long) query.uniqueResult();
	}
	
	public Long getOneHourActiveUsersCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId,Date lastHourTime,Date today){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tabUserInfoId)" +
						" from TabUserEnrollmentInfo model");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(",CadreRegUserTabUser model1");
		sb.append(" where model.enrollmentYearId = 4");
		
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = model1.cadreSurveyUserId" +
					" and model1.isDeleted = 'N' and model1.cadreRegUserId = :cadreRegUserId");
		if(lastHourTime != null && today != null)
			sb.append(" and model.endTime > :lastHourTime ");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.stateId = 1 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.stateId = 36 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.stateId in (1,36)");
		}
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and  model.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and  model.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUserId = :userId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		if(lastHourTime != null && today != null)
			query.setParameter("lastHourTime", lastHourTime);
			//query.setParameter("today", today);
		
		return (Long) query.uniqueResult();
	}
	public Long getAssignedUsersCountForRegUser(Long cadreRegUserId){
	    Query query = getSession().createQuery("select count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
	                    " from CadreRegUserTabUser model" +
	                    " where model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
	                    " and model.isDeleted = 'N'");
	    query.setParameter("cadreRegUserId", cadreRegUserId);
	    return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getDistrictWiseIssuesCount(Long issueTypeId,Long issueStatusId,Long stateId,Date fromDate,Date toDate){
		StringBuilder sb=new StringBuilder();
	     	sb.append("select count(model.cadreRegIssueId)," +
	     		" model.userAddress.district.districtId,model.userAddress.district.districtName" +
	     		" from CadreRegIssue model " +
	     		" where model.locationScopeId = 4");
	     if(issueTypeId != null && issueTypeId.longValue() > 0l)
	    	 sb.append(" and model.cadreRegIssueType.cadreRegIssueTypeId = :issueTypeId");
	     if(issueStatusId != null && issueStatusId.longValue() > 0l)
	    	 sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId = :issueStatusId");
	     if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and model.userAddress.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and  model.userAddress.district.districtId between 1 and 10 ");
			}else if(stateId != null && stateId.longValue() == 0l){
				sb.append(" and model.userAddress.state.stateId = 1");
			}
	     if(fromDate !=null && toDate != null)
	    	 sb.append(" and date(model.updatedTime) between :fromDate and :toDate");
	     sb.append(" group by model.userAddress.district.districtId");
	     
	     Query query = getSession().createQuery(sb.toString());
	     if(issueTypeId != null && issueTypeId.longValue() > 0l)
	    	query.setParameter("issueTypeId", issueTypeId);
	     if(issueStatusId != null && issueStatusId.longValue() > 0l)
	     	query.setParameter("issueStatusId", issueStatusId);
	     
	     if(fromDate !=null && toDate != null){
	    	 query.setParameter("fromDate", fromDate);
	    	 query.setParameter("toDate", toDate);
	     }
		 
		return query.list();
		
	}
	
	public List<Object[]> getConstituencyWiseIssuesCount(Long issueTypeId,Long issueStatusId,Long stateId,Date fromDate,Date toDate){
		StringBuilder sb=new StringBuilder();
	     	sb.append("select count(model.cadreRegIssueId)," +
	     			" model.userAddress.constituency.constituencyId,model.userAddress.constituency.name," +
	     			" model.userAddress.district.districtId" +
	     			" from CadreRegIssue model " +
	     			" where model.locationScopeId = 4");
	     if(issueTypeId != null && issueTypeId.longValue() > 0l)
	    	 sb.append(" and model.cadreRegIssueType.cadreRegIssueTypeId = :issueTypeId");
	     if(issueStatusId != null && issueStatusId.longValue() > 0l)
	    	 sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId = :issueStatusId");
	     if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and model.userAddress.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and  model.userAddress.district.districtId between 1 and 10 ");
			}else if(stateId != null && stateId.longValue() == 0l){
				sb.append(" and model.userAddress.state.stateId = 1");
			}
	     if(fromDate !=null && toDate != null)
	    	 sb.append(" and date(model.updatedTime) between :fromDate and :toDate");
	     sb.append(" group by model.userAddress.constituency.constituencyId");
	     
	     Query query = getSession().createQuery(sb.toString());
	     if(issueTypeId != null && issueTypeId.longValue() > 0l)
	    	query.setParameter("issueTypeId", issueTypeId);
	     if(issueStatusId != null && issueStatusId.longValue() > 0l)
	     	query.setParameter("issueStatusId", issueStatusId);
	    
	     if(fromDate !=null && toDate != null){
	    	 query.setParameter("fromDate", fromDate);
	    	 query.setParameter("toDate", toDate);
	     }
		 
		return query.list();
		
	}
}
