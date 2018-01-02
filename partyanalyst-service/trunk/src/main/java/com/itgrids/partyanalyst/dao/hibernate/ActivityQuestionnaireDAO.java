package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
	public List<Object[]> getActivityQuestionsOptionsDetails(Long activityScopeId) {
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" SELECT " +
		  		" AQ.activity_question_id as activityQuestionId," +
		  		" AQ.question as question," +
		  		" AOT.activity_option_type_id as activityOptionTypeId," +
		  		" AOT.type as type," +
		  		" AQE.has_remark as hasRemark ," +
		  		" AO.activity_option_id as activityOptionId ," +
		  		" AO.option as opt," +
		  		" AQE.activity_questionnaire_id as activityQuestionnaireId," +
		  		" AQE.is_mandatory as isMandatory ");
		  	queryStr.append(" FROM ");
		  	queryStr.append(" activity_question AQ,activity_option_type AOT,");
		  	queryStr.append(" activity_scope ACS,");
		  	queryStr.append(" activity_questionnaire AQE ");
		  	queryStr.append(" LEFT OUTER JOIN activity_questionnaire_option AQO ON AQE.activity_questionnaire_id = AQO.activity_questionnaire_id ");
		  	queryStr.append(" LEFT OUTER JOIN activity_option AO ON AQO.activity_option_id = AO.activity_option_id ");
		  	queryStr.append(" WHERE AQ.activity_question_id = AQE.activity_question_id AND ");
		  	queryStr.append(" AQE.activity_option_type_id = AOT.activity_option_type_id AND ");
		  	queryStr.append(" ACS.activity_scope_id = AQE.activity_scope_id AND AQE.is_deleted = 'N' AND ");
		  	queryStr.append(" AQE.activity_scope_id =:activityScopeId and AQE.question_type != 'DAYWISE QUESTION'" +
  			        " order by AQE.order_no,AQO.order_no ");
		  	 
		  	Session session = getSession();
		    SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		    
		     sqlQuery.addScalar("activityQuestionId", Hibernate.LONG);
		     sqlQuery.addScalar("question", Hibernate.STRING);
		     sqlQuery.addScalar("activityOptionTypeId", Hibernate.LONG);
		     sqlQuery.addScalar("type", Hibernate.STRING);
		     sqlQuery.addScalar("hasRemark", Hibernate.STRING);
		     sqlQuery.addScalar("activityOptionId", Hibernate.LONG);
		     sqlQuery.addScalar("opt", Hibernate.STRING);
		     sqlQuery.addScalar("activityQuestionnaireId",Hibernate.LONG);
		     sqlQuery.addScalar("isMandatory",Hibernate.STRING);
		    
		     sqlQuery.setParameter("activityScopeId", activityScopeId);
		  
		  
		  return sqlQuery.list();
		 
	}
	public List<Object[]> getActivityQuestionsOptionsDetailsDayWise(Long activityScopeId,Date activityDate) {
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" SELECT " +
		  		" AQ.activity_question_id as activityQuestionId," +
		  		" AQ.question as question," +
		  		" AOT.activity_option_type_id as activityOptionTypeId," +
		  		" AOT.type as type," +
		  		" AQE.has_remark as hasRemark ," +
		  		" AO.activity_option_id as activityOptionId ," +
		  		" AO.option as opt," +
		  		" AQE.activity_questionnaire_id as activityQuestionnaireId," +
		  		" AQE.is_mandatory as isMandatory," +
		  		" date(ADQ.activity_date) as activityDate," +
		  		" ADQ.activity_daywise_questionnaire_id as activityDaywiseQuestionnaireId ");
		  	queryStr.append(" FROM ");
		  	queryStr.append(" activity_question AQ,activity_option_type AOT,");
		  	queryStr.append(" activity_scope ACS,activity_daywise_questionnaire ADQ,");
		  	queryStr.append(" activity_questionnaire AQE ");
		  	queryStr.append(" LEFT OUTER JOIN activity_questionnaire_option AQO ON AQE.activity_questionnaire_id = AQO.activity_questionnaire_id ");
		  	queryStr.append(" LEFT OUTER JOIN activity_option AO ON AQO.activity_option_id = AO.activity_option_id ");
		  	queryStr.append(" WHERE AQ.activity_question_id = AQE.activity_question_id AND ");
		  	queryStr.append(" AQE.activity_option_type_id = AOT.activity_option_type_id AND ");
		  	queryStr.append(" ACS.activity_scope_id = AQE.activity_scope_id " );
		  	queryStr.append(" AND ADQ.activity_questionnaire_id=AQE.activity_questionnaire_id AND ADQ.is_deleted = 'true' AND ");
		  	queryStr.append(" AQE.activity_scope_id =:activityScopeId AND date(ADQ.activity_date)=:activityDate " +
		  			        " and AQE.question_type = 'DAYWISE QUESTION' " +
		  			        " order by AQE.order_no,AQO.order_no ");
		  	 
		  	Session session = getSession();
		    SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		    
		     sqlQuery.addScalar("activityQuestionId", Hibernate.LONG);
		     sqlQuery.addScalar("question", Hibernate.STRING);
		     sqlQuery.addScalar("activityOptionTypeId", Hibernate.LONG);
		     sqlQuery.addScalar("type", Hibernate.STRING);
		     sqlQuery.addScalar("hasRemark", Hibernate.STRING);
		     sqlQuery.addScalar("activityOptionId", Hibernate.LONG);
		     sqlQuery.addScalar("opt", Hibernate.STRING);
		     sqlQuery.addScalar("activityQuestionnaireId",Hibernate.LONG);
		     sqlQuery.addScalar("isMandatory",Hibernate.STRING);
		     sqlQuery.addScalar("activityDate",Hibernate.STRING);
		     sqlQuery.addScalar("activityDaywiseQuestionnaireId",Hibernate.LONG);
		     
		    
		     sqlQuery.setParameter("activityScopeId", activityScopeId);
		     sqlQuery.setParameter("activityDate", activityDate);
		  
		  
		  return sqlQuery.list();
		 
	}
}
