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
	
	public List<Object[]> getTextboxQuestionaireForScope(Long scopeId,Long questionId,Long optionId,List<Long> questionnaireIds){
		
		  StringBuilder sb=new StringBuilder();
		  sb.append(" select model.activityQuestionnaire.activityQuestionnaireId, " +//question id
				    " model.activityQuestionnaire.activityQuestion.question, " +//question
				    " optionType.activityOptionTypeId, " +//option type id
				    " optionType.type, " +//option type
				    " '', " +//option id
				    " '', " +//option
				    " model.activityQuestionnaire.hasRemark , model.activityQuestionnaire.orderNo " + //remarks
				    " from ActivityQuestionnaireOption model " +
				    " left join model.activityQuestionnaire.activityOptionType optionType " +
				    " where model.activityQuestionnaire.activityQuestionnaireId in(:questionnaireIds) and " +
				    " model.isDeleted='N' and model.activityQuestionnaire.activityScopeId=:scopeId and model.activityOptionId is null ");
		  if(questionId == null || questionId.longValue()<=0){
			  sb.append(" and model.activityQuestionnaire.parentActivityQuestionnaireId is null");
		  }
		  else if(questionId!=null && questionId>0l && optionId!=null && optionId>0l){
			    sb.append(" and model.activityQuestionnaire.parentActivityQuestionnaireId=:questionId");
			    sb.append(" and model.activityQuestionnaire.parentActivityOptionId=:optionId");
		  }
		  sb.append(" order by model.activityQuestionnaire.orderNo");
		  
		  Query query=getSession().createQuery(sb.toString());
		  
		  query.setParameter("scopeId", scopeId);
		  query.setParameterList("questionnaireIds", questionnaireIds);
		  
		  if(questionId!=null && questionId>0l && optionId!=null && optionId>0l){
			  query.setParameter("questionId", questionId);
			  query.setParameter("optionId", optionId);
		  }
		  return query.list();
		
	}
	
	public List<Object[]> getQuestionnaireForScope(Long scopeId,Long questionId,Long optionId,List<Long> questionnaireIds){
		
		  StringBuilder sb=new StringBuilder();
		  sb.append(" select model.activityQuestionnaire.activityQuestionnaireId, " +//question id
				    " model.activityQuestionnaire.activityQuestion.question, " +//question
				    " model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				    " model.activityQuestionnaire.activityOptionType.type, " +//option type
				    " model.activityOption.activityOptionId, " +//option id
				    " model.activityOption.option, " +//option
				    " model.activityQuestionnaire.hasRemark , model.activityQuestionnaire.orderNo" + //remarks
				    " from ActivityQuestionnaireOption model " +
				    " where model.activityQuestionnaire.activityQuestionnaireId in(:questionnaireIds) and " +
				    " model.isDeleted='N' and model.activityQuestionnaire.activityScopeId=:scopeId ");
		    
		  if(questionId == null || questionId.longValue()<=0){
			  sb.append(" and model.activityQuestionnaire.parentActivityQuestionnaireId is null");
		  }
		  else if(questionId!=null && questionId>0l && optionId!=null && optionId>0l){
			    sb.append(" and model.activityQuestionnaire.parentActivityQuestionnaireId=:questionId");
			    sb.append(" and model.activityQuestionnaire.parentActivityOptionId=:optionId");
		  }
		  sb.append(" order by model.activityQuestionnaire.orderNo");
		  
		  Query query=getSession().createQuery(sb.toString());
		  
		  query.setParameter("scopeId", scopeId);
		  query.setParameterList("questionnaireIds", questionnaireIds);
		  if(questionId!=null && questionId>0l && optionId!=null && optionId>0l){
			  query.setParameter("questionId", questionId);
			  query.setParameter("optionId", optionId);
		  }
		  return query.list();
		  
		/*Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" model.activityOption.activityOptionId, " +//option id
				" model.activityOption.option, " +//option
				" model.activityQuestionnaire.hasRemark " + //remarks
				" from ActivityQuestionnaireOption model " +
				" where model.isDeleted='N' " +
				" and model.activityQuestionnaire.activityScopeId=:scopeId " +
				" order by model.activityQuestionnaire.orderNo ");
		query.setParameter("scopeId", scopeId);
		return query.list();*/
	}
	
	public List<Object[]> getQuestionnaireOfScope(List<Long> scopeIds){
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//0--question id
				" model.activityQuestionnaire.activityQuestion.question, " +		//question -- 1
				" model.activityQuestionnaire.orderNo, " +							//question order no -- 2
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id -- 3
				" model.activityQuestionnaire.activityOptionType.type, " +			//option type -- 4
				" model.activityOption.activityOptionId, " +						//option id -- 5
				" model.activityOption.option," +									//option -- 6
				" model.orderNo," +													//Order No --7
				" model.activityQuestionnaire.respondentType.respondentTypeId, " +	//	respondent type id -- 8 
				" model.activityQuestionnaire.respondentType.respondentType, " +	//	respondent type -- 9
				" model.activityQuestionnaire.activityScopeId, " +				//	Activity Scope Id -- 10
				" model.activityQuestionnaire.activityQuestionnaireId, " +		//	QuestionnairId --11
				" model.activityQuestionnaire.parentActivityQuestionnaireId " +		//	Parent QuestionnairId --12
				" from ActivityQuestionnaireOption model " +
				" where model.isDeleted='N' " +
				" and model.activityQuestionnaire.activityScopeId in(:scopeIds) " +
				" order by model.activityQuestionnaire.orderNo,model.orderNo ");
		query.setParameterList("scopeIds", scopeIds);
		return query.list();
	}
	public List<Object[]> getQuestionnaireForScopeAndRespondentTypeIds(Long scopeId,Long requiredAttributeId,List<Long> questionnaireIds){
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" model.activityOption.activityOptionId, " +//option id
				" model.activityOption.option,  model.activityQuestionnaire.orderNo " +//option
				" from ActivityQuestionnaireOption model,RequiredAttributeRespondentType model2  " +
				" where model.activityQuestionnaire.activityQuestion.activityQuestionId in(:questionnaireIds) and " +
				" model.isDeleted='N' " +
				" and model.activityQuestionnaire.activityScopeId=:scopeId and " +
				" model.activityQuestionnaire.respondentTypeId = model2.respondentTypeId and model2.requiredAttributeId =:requiredAttributeId " +
				" order by model.activityQuestionnaire.orderNo ");
		query.setParameter("scopeId", scopeId);
		query.setParameter("requiredAttributeId", requiredAttributeId);
		query.setParameterList("questionnaireIds", questionnaireIds);
		return query.list();
	}
	
	public List<Object[]> getTextBoxQuestionnaireForScopeAndRespondentTypeIds(Long scopeId,Long requiredAttributeId,List<Long> questionnaireIds){
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" '', " +//option id
				" '', model.activityQuestionnaire.orderNo  " +//option
				" from ActivityQuestionnaireOption model,RequiredAttributeRespondentType model2  " +
				" where model.activityQuestionnaire.activityQuestion.activityQuestionId in(:questionnaireIds) and " +
				" model.isDeleted='N' and model.activityOptionId is null " +
				" and model.activityQuestionnaire.activityScopeId=:scopeId and " +
				" model.activityQuestionnaire.respondentTypeId = model2.respondentTypeId and model2.requiredAttributeId =:requiredAttributeId " +
				" order by model.activityQuestionnaire.orderNo ");
		query.setParameter("scopeId", scopeId);
		query.setParameter("requiredAttributeId", requiredAttributeId);
		query.setParameterList("questionnaireIds", questionnaireIds);
		return query.list();
	}
	
	public List<Object[]> getOptionsForQuestions(Long questionId){
		
		Query query = getSession().createQuery(" select distinct model.activityOptionId,model.activityOption.option " +
				"from ActivityQuestionnaireOption model " +
				" where model.activityQuestionnaire.activityQuestionId =:questionId ");
		query.setParameter("questionId", questionId);
		return query.list();
	}
public List<Object[]> getOptionsByQuesttionareIds(Long questionnareId){
		
		Query query = getSession().createQuery(" select distinct model.activityOptionId,model.activityOption.option " +
				"from ActivityQuestionnaireOption model " +
				" where model.activityQuestionnaire.activityQuestionnaireId =:questionnareId ");
		query.setParameter("questionnareId", questionnareId);
		return query.list();
	}

public List<Object[]> getQuestionnaireOptionsDetailsOfScope(List<Long> scopeIds){
	Query query = getSession().createQuery(" select activityQuestion.activityQuestionId, " +//0--question id
			" activityQuestion.question, " +		//question -- 1
			" activityQuestionnaire.orderNo, " +							//question order no -- 2
			" activityOptionType.activityOptionTypeId, " +//option type id -- 3
			" activityOptionType.type, " +			//option type -- 4
			" activityOption.activityOptionId, " +						//option id -- 5
			" activityOption.option," +									//option -- 6
			" model.orderNo," +													//Order No --7
			" respondentType.respondentTypeId, " +	//	respondent type id -- 8 
			" respondentType.respondentType, " +	//	respondent type -- 9
			" activityQuestionnaire.activityScopeId, " +				//	Activity Scope Id -- 10
			" activityQuestionnaire.activityQuestionnaireId " +		//	QuestionnairId --11
			" from ActivityQuestionnaireOption model " +
			" left join model.activityQuestionnaire  activityQuestionnaire " +
			" left join model.activityQuestionnaire.activityQuestion activityQuestion " +			
			" left join model.activityQuestionnaire.activityOptionType  activityOptionType " +
			" left join model.activityOption  activityOption " +
			" left join model.activityQuestionnaire.respondentType respondentType " +
			" where model.isDeleted='N' " +
			" and model.activityQuestionnaire.activityScopeId in(:scopeIds) " +
			" order by model.activityQuestionnaire.orderNo,model.orderNo ");
	query.setParameterList("scopeIds", scopeIds);
	return query.list();
}


public List<Object[]> getActivityQuestionOptions(List<Long> activityQuestionnaireIds)
{
	Query query = getSession().createQuery("select model.activityQuestionnaire.activityQuestion.activityQuestionId, " +//question id
				" model.activityQuestionnaire.activityQuestion.question, " +//question
				" model.activityQuestionnaire.activityOptionType.activityOptionTypeId, " +//option type id
				" model.activityQuestionnaire.activityOptionType.type, " +//option type
				" model.activityOption.activityOptionId, " +//option id
				" model.activityOption.option,model.activityQuestionnaire.hasRemark,model.activityQuestionnaire.orderNo " +//option
			"from ActivityQuestionnaireOption model" +
			" where model.activityQuestionnaire.activityQuestionnaireId in(:activityQuestionnaireIds)" +
			"  group by model.activityQuestionnaire.activityQuestion.activityQuestionId,model.activityOption.activityOptionId");
	query.setParameterList("activityQuestionnaireIds", activityQuestionnaireIds);
	return query.list();
}
public List<Object[]> getQuesAndOptionsByScopeIds(List<Long> scopeIdsList){
	Query query = getSession().createQuery("select model.activityQuestionnaire.activityScope.activityScopeId," +
			" model.activityQuestionnaire.activityQuestion.activityQuestionId," +
			" model.activityQuestionnaire.activityQuestion.question," +
			" model.activityOption.activityOptionId," +
			" model.activityOption.option , " +
			" model.activityQuestionnaire.activityQuestionnaireId,model.activityQuestionnaire.activityScope.scopeValue " +
			" from ActivityQuestionnaireOption model" +
			" where model.activityQuestionnaire.activityScope.activityScopeId in (:scopeIdsList)");
	query.setParameterList("scopeIdsList", scopeIdsList);
	return query.list();
}
}
