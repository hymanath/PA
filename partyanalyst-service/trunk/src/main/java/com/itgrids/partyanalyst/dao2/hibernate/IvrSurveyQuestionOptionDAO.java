package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyQuestionOptionDAO;
import com.itgrids.partyanalyst.model.IvrSurveyQuestionOption;

public class IvrSurveyQuestionOptionDAO extends GenericDaoHibernate<IvrSurveyQuestionOption,Long> implements IIvrSurveyQuestionOptionDAO{

	public IvrSurveyQuestionOptionDAO()
	{
		super(IvrSurveyQuestionOption.class);
	}
	
	
		public List<Object[]> getOptionsForSurveyQuestion(Long surveyQuestionId)
		{
			Query query = getSession().createQuery("select distinct model.ivrOption.ivrOptionId,model.ivrOption.option from IvrSurveyQuestionOption model where" +
					" model.isDeleted = 'false' and model.ivrSurveyQuestion.ivrSurveyQuestionId=:surveyQuestionId ");
			query.setParameter("surveyQuestionId", surveyQuestionId);
			return query.list();
		}
	}
