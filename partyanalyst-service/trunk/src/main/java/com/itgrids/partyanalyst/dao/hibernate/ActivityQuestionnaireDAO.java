package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaire;

public class ActivityQuestionnaireDAO extends GenericDaoHibernate<ActivityQuestionnaire, Long> implements IActivityQuestionnaireDAO{

	public ActivityQuestionnaireDAO() {
		super(ActivityQuestionnaire.class);
		
	}
	
	public List<Long> getQuestionnaireIdsListByScopeId(Long scopeId){
		
		Query query = getSession().createQuery(" select model.activityQuestionnaireId from ActivityQuestionnaire model " +
							" where model.activityScope.activityScopeId = :scopeId and model.isDeleted = 'N' ");
		
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}


	public List<Object[]> getQuestionnareOptionsDetails(Long questionId){
		
		Query query = getSession().createQuery(" select distinct model.activityOption.activityOptionId , model.activityOption.option from ActivityQuestionnaireOption model " +
							" where model.activityQuestionnaire.activityQuestionnaireId = :questionId and model.activityQuestionnaire.isDeleted = 'N' and model.isDeleted = 'N' ");
		query.setParameter("questionId", questionId);
		return query.list();
	}
	
	public List<Object[]> getQuestionnareForScopeId(Long scopeId){
		    
		    Query query = getSession().createQuery(" select distinct model.activityQuestion.activityQuestionId , model.activityQuestion.question from ActivityQuestionnaire model " +
		              " where model.activityScope.activityScopeId = :scopeId and model.parentActivityQuestionnaireId = null and model.isDeleted = 'N' ");
		    query.setParameter("scopeId", scopeId);
		    return query.list();
		  }
}
