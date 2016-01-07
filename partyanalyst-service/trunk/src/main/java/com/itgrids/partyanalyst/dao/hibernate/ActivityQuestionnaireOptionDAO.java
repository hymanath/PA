package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaireOption;

public class ActivityQuestionnaireOptionDAO extends GenericDaoHibernate<ActivityQuestionnaireOption, Long> implements IActivityQuestionnaireOptionDAO{

	public ActivityQuestionnaireOptionDAO() {
		super(ActivityQuestionnaireOption.class);
		
	}
	
	public List<Object[]> getQuestionnaireForScope(Long scopeId){
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" model.activityOption.activityOptionId, " +//option id
				" model.activityOption.option " +//option
				" from ActivityQuestionnaireOption model " +
				" where model.isDeleted='N' " +
				" and model.activityQuestionnaire.activityScopeId=:scopeId " +
				" order by model.activityQuestionnaire.orderNo ");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}
	
	public List<Object[]> getQuestionnaireOfScope(Long scopeId){
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" model.activityOption.activityOptionId, " +//option id
				" model.activityOption.option," +//option
				" model.activityQuestionnaire.respondentType.respondentTypeId, " +	//	respondent type id
				" model.activityQuestionnaire.respondentType.respondentType " +		//	respondent type
				" from ActivityQuestionnaireOption model " +
				" where model.isDeleted='N' " +
				" and model.activityQuestionnaire.activityScopeId=:scopeId " +
				" order by model.activityQuestionnaire.orderNo ");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}

}
