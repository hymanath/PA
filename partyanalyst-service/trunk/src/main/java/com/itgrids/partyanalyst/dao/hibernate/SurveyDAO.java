package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.model.Survey;

public class SurveyDAO extends GenericDaoHibernate<Survey, Long> implements ISurveyDAO{

	public SurveyDAO() {
		super(Survey.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSurveys(){
		
		return getHibernateTemplate().find("select model.surveyId,model.name from Survey model");
	}
	
	@SuppressWarnings("unchecked")
	public List<Survey> getSurveyDataBySurveyId(Long surveyId)
	{
		return getHibernateTemplate().find("select model from Survey model where model.surveyId = ?",surveyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSurveysUsingIsDeleted(){
		Query queryObject = getSession()
				.createQuery(
						"select model.surveyId,model.name from Survey model where model.isDeleted != :value");
		
		queryObject.setParameter("value","true" );
		return queryObject.list();
	}

	public int updateSurveyDetails(Long surveyId){
		Query queryObject = getSession()
				.createQuery(
						"update Survey model set model.isDeleted = :value where model.surveyId = :surveyId");
		
		queryObject.setParameter("surveyId",surveyId );
		queryObject.setParameter("value","true" );
		return queryObject.executeUpdate();
	}
}
