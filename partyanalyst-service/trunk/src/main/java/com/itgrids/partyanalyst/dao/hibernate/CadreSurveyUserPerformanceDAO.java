package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserPerformanceDAO;
import com.itgrids.partyanalyst.model.ActivityAttributeQuestionnaireInfo;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformance;

public class CadreSurveyUserPerformanceDAO extends  GenericDaoHibernate<CadreSurveyUserPerformance, Long> implements ICadreSurveyUserPerformanceDAO {

	public CadreSurveyUserPerformanceDAO() {
		super(CadreSurveyUserPerformance.class);
		
	}
	
	public List<CadreSurveyUserPerformance> getCadreSurveyUserPerformanceDetails(Long cadreSurveyUserId,Date currentDate){
		Query query = getSession().createQuery("select model"+
				" from CadreSurveyUserPerformance model" +
				" where model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId" +
				" and date(model.surveyTime) = :currentDate");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		query.setDate("currentDate", currentDate);
		return  query.list();
	}

	public List<Object[]> getUserWisePerformanceByDate(Long cadreSurveyUserId,Date today){
		Query query = getSession().createQuery("select " +
								" model.cadreSurveyUserPerformanceType.cadreSurveyUserPerformanceTypeId," +
								" model.comment," +
								" model.cadreSurveyUserPerformanceType.type" +
								" from CadreSurveyUserPerformance model" +
								" where date(model.surveyTime) = :today" +
								" and model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId" +
								" and model.isDeleted = 'false'");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		query.setDate("today", today);
		return query.list();
	}
	
	public List<Object[]> getCadreSurveyUserId(Date today){
		Query query= getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId," +
				" model.cadreSurveyUserPerformanceType.cadreSurveyUserPerformanceTypeId" +
				" from CadreSurveyUserPerformance model" +
				" where date(model.surveyTime) = :today" +
				" and  model.isDeleted = 'false'");
		query.setDate("today", today);
		return query.list();
	}
}
