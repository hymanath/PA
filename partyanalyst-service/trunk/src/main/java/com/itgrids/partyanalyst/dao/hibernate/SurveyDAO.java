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
	
}
