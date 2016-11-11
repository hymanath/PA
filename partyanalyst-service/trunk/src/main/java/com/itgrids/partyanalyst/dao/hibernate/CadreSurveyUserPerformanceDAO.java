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
	
	public List<Object[]> getCadreSurveyUserPerformanceDetails(Long cadreSurveyUserId,Date currentDate){
		Query query = getSession().createQuery("select model.cadreSurveyUserPerformanceId," +
				" model.cadreSurveyUserPerformanceTypeId," +
				" model.insertedTime," +
				" model.updatedTime," +
				" model.createdBy," +
				" model.updatedBy" +
				" from CadreSurveyUserPerformance model" +
				" where model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId" +
				" and date(model.surveyTime) = :currentDate");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		query.setDate("currentDate", currentDate);
		return query.list();
	}

}
