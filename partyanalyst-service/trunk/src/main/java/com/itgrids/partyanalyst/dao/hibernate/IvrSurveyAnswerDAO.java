package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerDAO;
import com.itgrids.partyanalyst.model.IvrSurveyAnswer;


public class IvrSurveyAnswerDAO extends GenericDaoHibernate<IvrSurveyAnswer, Long> implements IIvrSurveyAnswerDAO{

	public IvrSurveyAnswerDAO() {
		super(IvrSurveyAnswer.class);
	}
	
	public List<Long> getIvrSurveyIdsByRespondantId(Long ivrRespondantId){
		
		Query query= getSession().createQuery("select model.ivrSurveyId from IvrSurveyAnswer model " +
				"  where model.ivrRespondentId  = :ivrRespondantId " +
				" and model.isDeleted = 'false' " +
				" and model.ivrSurvey.isDeleted ='false'" +
				" and model.isValid='Y'  ");
		
		query.setParameter("ivrRespondantId", ivrRespondantId);
		
		return query.list(); 
		
	}

	public List<Object[]> getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestion.ivrSurveyQuestionId," +
							" model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId," +
							" model.ivrSurveyQuestion.ivrQuestion.question," +
							" model.ivrOption.ivrOptionId," +
							" model.ivrOption.option" +
							" from IvrSurveyAnswer model" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestionId," +
							" question.ivrQuestionId," +
							" question.question," +
							" option.ivrOptionId," +
							" option.option" +
							" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
							" left join model.ivrOption option" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestionId," +
							" question.ivrQuestionId," +
							" question.question," +
							" option.ivrOptionId," +
							" option.option" +
							" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
							" left join model.ivrOption option" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'" +
							" and model.ivrOptionId is null");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getIvrSurveyAnswerInfoForTdpCadre(Long tdpCadreId)
	{
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
				" model.ivrSurvey.surveyName," +
				" model.ivrSurveyRound.ivrSurveyRoundId," +
				" model.ivrSurveyRound.roundName," +
				" model.ivrSurveyQuestionId," +
				" question.ivrQuestionId," +
				" question.question," +
				" option.ivrOptionId," +
				" option.option," +
				" model.ivrResponseType.ivrResponseTypeId,model.ivrResponseType.responseType" +
				" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
				" left join model.ivrOption option" +
				" where " +
				" and model.ivrRespondent.ivrRespondentId = :respondentId" +
				" and model.isValid = 'Y' and model.isDeleted = 'false'");
		return query.list();
		
	}
	
}
