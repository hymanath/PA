package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;
import java.util.Date;

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
}