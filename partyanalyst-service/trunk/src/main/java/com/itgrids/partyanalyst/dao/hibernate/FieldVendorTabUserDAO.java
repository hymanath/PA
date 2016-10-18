package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

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
}
