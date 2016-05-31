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
		    
		    Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId , model.activityQuestion.question from ActivityQuestionnaire model " +
		              " where model.activityScope.activityScopeId = :scopeId and model.isDeleted = 'N' ");
		    query.setParameter("scopeId", scopeId);
		    return query.list();
		  }
	
   public List<Object[]> getQuestionIdsByScopeId(Long scopeId){
		
		Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId , model.activityQuestion.question from ActivityQuestionnaire model " +
							" where model.activityScope.activityScopeId = :scopeId and model.isDeleted = 'N' ");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}
   
   public List<Object[]> getQuestionIdsByActivityId(Long activityId){
		
 		Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId , model.activityQuestion.question from ActivityQuestionnaire model " +
 							" where model.activityScope.activity.activityId = :activityId and model.isDeleted = 'N' ");
 		query.setParameter("activityId", activityId);
 		return query.list();
 	}
   
	public List<Long> getActivityQuestionnaireIdByQuestionId(Long scopeId,Long activityQuestionId){
		Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId from ActivityQuestionnaire model " +
				" where model.activityScope.activityScopeId = :scopeId and model.isDeleted = 'N' and model.activityQuestionId =:activityQuestionId ");
		query.setParameter("scopeId", scopeId);
		query.setParameter("activityQuestionId", activityQuestionId);
			return query.list();
	}
	public List<Long> getActivityStatusQuestionnaireIdByQuestionId(Long activityScopeId,Long questionsId){
		Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId from ActivityQuestionnaire model " +
				" where model.activityScope.activityScopeId = :activityScopeId and model.isDeleted = 'N' and model.activityQuestionId =:questionsId ");
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("questionsId", questionsId);
			return query.list();
	}
	public List<Long> getQuestionareId(Long activityScopeId,Long questionId){
		Query query = getSession().createQuery("select distinct model.activityQuestionnaireId " +
				"from ActivityQuestionnaire model  " +
				" where model.activityScope.activityScopeId =:activityScopeId and " +
				" model.activityQuestion.activityQuestionId =:questionId and model.isDeleted = 'N' ");
		
		query.setParameter("questionId", questionId); 
		query.setParameter("activityScopeId", activityScopeId);
		return query.list();
	}
	
public List<Object[]> getQuestionnareDetails(List<Long> questionnairIdsList){
		
		Query query = getSession().createQuery(" select distinct model.activityQuestionnaireId,model.activityQuestion.question from ActivityQuestionnaire model " +
							" where model.activityQuestionnaireId in(:questionnairIdsList) and model.isDeleted = 'N' order by model.orderNo ");
		query.setParameterList("questionnairIdsList", questionnairIdsList);
		return query.list();
	}

	
	public List<Object[]> getActivityQuestionListByScope(Long activityScope){
		Query query = getSession().createQuery("select model.activityQuestionnaireId," +
									" model.activityOptionType.activityOptionTypeId," +
									" model.activityOptionType.type," +
									"model.activityQuestion.question" +
									" from ActivityQuestionnaire model" +
									" where model.activityScope.activityScopeId = :activityScope" +
									" and model.isDeleted = 'N'");
		query.setParameter("activityScope", activityScope);
		
		return query.list();
	}
	
	public List<Object[]> getActivityQuestionOptionTypeList(List<Long> questionIds){
		Query query = getSession().createQuery("select model.activityQuestionnaireId," +
									" model.activityOptionType.activityOptionTypeId," +
									" model.activityOptionType.type, model.activityQuestion.question " +
									" from ActivityQuestionnaire model" +
									" where model.activityQuestionnaireId in (:questionIds)" +
									" and model.isDeleted = 'N'");
		query.setParameterList("questionIds", questionIds);
		
		return query.list();
	}
}
