package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyAnswerDAO;
import com.itgrids.partyanalyst.model.SurveyAnswer;

public class SurveyAnswerDAO extends GenericDaoHibernate<SurveyAnswer, Long> implements ISurveyAnswerDAO{

	public SurveyAnswerDAO() {
		super(SurveyAnswer.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getSurveyAnalyseData(List<Long> surveyQuestionIds)
	{
		Query query = getSession().createQuery("select count(model.surveyAnswerId),model.option.options,model.surveyQuestion.surveyQuestionId,model.surveyQuestion.question from SurveyAnswer model where model.surveyQuestion.surveyQuestionId in(:surveyQuestionIds) and model.isSubOption='false' group by  model.surveyQuestion.surveyQuestionId,model.option.optionsId");
		query.setParameterList("surveyQuestionIds", surveyQuestionIds);
		return query.list();
		
	}
	public List<Object[]> getCountForSurveyQuestion(List<Long> surveyQuestionIds)
	{
		Query query = getSession().createQuery("select count(model.surveyAnswerId),model.surveyQuestion.surveyQuestionId from SurveyAnswer model where model.surveyQuestion.surveyQuestionId in(:surveyQuestionIds)  and model.isSubOption='false' group by model.surveyQuestion.surveyQuestionId");
		query.setParameterList("surveyQuestionIds", surveyQuestionIds);
		return query.list();
		
	}
    public List<Object[]> getCasteWiseSurveyInfo(List<Long> surveyQuestionIds){
		Query query = getSession().createQuery("select model.surveyQuestion.surveyQuestionId,model.option.optionsId,model.surveyAnswerInfo.respondent.surveyorProfile.casteState.casteStateId," +
				" model.surveyAnswerInfo.respondent.surveyorProfile.casteState.caste.casteName,count(*) from SurveyAnswer model where model.surveyQuestion.surveyQuestionId in (:surveyQuestionIds) " +
				" and model.isSubOption = :isSubOption group by model.surveyQuestion.surveyQuestionId,model.option.optionsId,model.surveyAnswerInfo.respondent.surveyorProfile.casteState.casteStateId");
		query.setParameterList("surveyQuestionIds", surveyQuestionIds);
		query.setParameter("isSubOption", "false");
		return query.list();
	}
}
