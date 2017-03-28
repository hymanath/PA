package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyEntityDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntity;


public class IvrSurveyEntityDAO extends GenericDaoHibernate<IvrSurveyEntity, Long> implements IIvrSurveyEntityDAO{

	public IvrSurveyEntityDAO() {
		super(IvrSurveyEntity.class);
	}
	
	public List<Object[]> getSurveyEntityTypeDetails(List<Long> surveyIds){
		
		Query query = getSession().createQuery("select model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model.ivrSurveyEntityType.type " +
				" from IvrSurveyEntity model" +
				" where " +
				" model.ivrSurveyId  in (:surveyIds)" +
				" and model.isDeleted='false' ");
		
		query.setParameterList("surveyIds", surveyIds);        		
		return query.list();		
	}
	
	public List<Object[]> getSurveyEntityTypeAndCountDetails(List<Long> surveyIds,Long ivrRespondentId){
		
		Query query = getSession().createQuery("select model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model.ivrSurveyEntityType.type,count(model1.ivrRespondentId)," +
				" model1.ivrResponseType.ivrResponseTypeId, model1.ivrResponseType.responseType " +
				" from IvrSurveyEntity model,IvrSurveyAnswer model1 " +
				" where " +
				" model.ivrSurveyId = model1.ivrSurveyId " +
				" and model.ivrSurveyId  in (:surveyIds) " +
				" and model1.ivrRespondentId =:ivrRespondentId " +
				" and model.isDeleted='false'" +
				" and model1.isDeleted = 'false' " +
				" and model1.isValid = 'Y' " +
				" group by model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model1.ivrResponseType.ivrResponseTypeId ");
		
		query.setParameterList("surveyIds", surveyIds);    
		query.setParameter("ivrRespondentId",ivrRespondentId);
		return query.list();		
	}
	
	
	public List<Object[]> getSurveyCountforEntityType(List<Long> surveyIds,Long ivrRespondentId){
		
		Query query = getSession().createQuery("select model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model.ivrSurveyEntityType.type,count(distinct model.ivrSurveyId)" +
			" from IvrSurveyEntity model,IvrSurveyAnswer model1 " +
				" where " +
				" model.ivrSurveyId = model1.ivrSurveyId " +
				" and model.ivrSurveyId  in (:surveyIds) " +
				" and model1.ivrRespondentId =:ivrRespondentId " +
				" and model.isDeleted='false'" +
				" and model1.isDeleted = 'false' " +
				" and model1.isValid = 'Y' " +
				" group by model.ivrSurveyEntityType.ivrSurveyEntityTypeId ");
		query.setParameterList("surveyIds", surveyIds);    
		query.setParameter("ivrRespondentId",ivrRespondentId);
		return query.list();		
	}

	public List<Object[]> getSurveyListByEntityType(Long entityTypeId){
		
		Query query = getSession().createQuery(" select distinct model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.entityValue" +
							" from IvrSurveyEntity model" +
							" where model.ivrSurveyEntityType.ivrSurveyEntityTypeId = :entityTypeId" +
							" and model.isDeleted = 'false' ");
		query.setParameter("entityTypeId", entityTypeId);
		
		return query.list();
	}
	
	public List<Object[]> getSurveyAnswerDetailsForActivity(List<Long> surveyIds,Long respondentId,List<Long> entityValuesList,String searchType){
		
		StringBuilder quereyStr = new StringBuilder();		
		quereyStr.append(" select model.activity.activityId,model.activity.activityName," +
				"  model.activityLevel.activityLevelId, model.activityLevel.level," +
				" model.startDate,model.endDate,isa.ivrSurvey.ivrSurveyId," +
				" isa.ivrSurvey.surveyName," +
				" isa.ivrSurveyRound.ivrSurveyRoundId," +
				" isa.ivrSurveyRound.roundName," +
				" isa.ivrSurveyQuestionId," +
				" question.ivrQuestionId," +
				" question.question," +
				" option.ivrOptionId," +
				" option.option " +				 
				" from ActivityScope model,IvrSurveyEntity ise,IvrSurveyAnswer isa left join isa.ivrSurveyQuestion.ivrQuestion question" +
				" left join isa.ivrOption option " +
				" where " +
				" isa.ivrSurveyId = ise.ivrSurveyId" +
				" and model.activityScopeId = ise.entityValue" +
				" and model.activityScopeId in (:entityValuesList) " +
				" and model.isDeleted = 'N' " +
				" and ise.isDeleted = 'false' " +
				" and isa.isValid = 'Y' " +
				" and isa.isDeleted ='false' " +
				" and isa.ivrSurvey.ivrSurveyId in (:surveyIds) " +
				" and isa.ivrRespondent.ivrRespondentId = :respondentId ");
		
		if(searchType !=null && searchType.equalsIgnoreCase("unAnswered")){
			quereyStr.append(" and isa.ivrOptionId is null");
		}
		if(searchType !=null && searchType.equalsIgnoreCase("Answered")){
			quereyStr.append(" and isa.ivrOptionId is not null ");
		}
		
		Query query = getSession().createQuery(quereyStr.toString());
		
		query.setParameterList("surveyIds", surveyIds);
		query.setParameterList("entityValuesList", entityValuesList);//activityScopeIds
		query.setParameter("respondentId", respondentId);
		
		return query.list();		
	}
}
