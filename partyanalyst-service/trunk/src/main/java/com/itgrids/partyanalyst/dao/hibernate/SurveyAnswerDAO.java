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
	/**
	 * This DAO is used for getting the Caste based survey Analysis
	 * @param List<Long> questionIds
	 * @return List<Object[]> 
	 */
    public List<Object[]> getCasteWiseSurveyInfo(List<Long> surveyQuestionIds){
		Query query = getSession().createQuery("select model.surveyQuestion.surveyQuestionId,model.option.optionsId,model.surveyAnswerInfo.respondent.surveyorProfile.casteState.casteStateId," +
				" model.surveyAnswerInfo.respondent.surveyorProfile.casteState.caste.casteName,count(*) from SurveyAnswer model where model.surveyQuestion.surveyQuestionId in (:surveyQuestionIds) " +
				" and model.isSubOption = :isSubOption group by model.surveyQuestion.surveyQuestionId,model.option.optionsId,model.surveyAnswerInfo.respondent.surveyorProfile.casteState.casteStateId");
		query.setParameterList("surveyQuestionIds", surveyQuestionIds);
		query.setParameter("isSubOption", "false");
		return query.list();
	}
    /**
	 * This DAO is used for getting the Age based survey Analysis 
	 * @param List<Long> questionIds
	 * @return List<Object[]> 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getsurveyDetailsBasedOnGivenAgeRange(List<Long> questionIds,String minAge,String maxAge)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.surveyQuestion.surveyQuestionId,model.surveyQuestion.question," +
				" model.option.optionsId ,model.option.options , count(model.option.optionsId) from SurveyAnswer model " +
				" where model.surveyQuestion.surveyQuestionId in (:questionIds) and " +
				" model.surveyAnswerInfo.respondent.surveyorProfile.age between :minAge and :maxAge  " +
				" group by model.surveyQuestion.surveyQuestionId,model.option.optionsId");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("questionIds", questionIds);
		query.setParameter("minAge", minAge);
		query.setParameter("maxAge", maxAge);
		return query.list();
	}
	/**
	 * This DAO is used for getting the Age based survey Analysis for Above 60 years
	 * @param List<Long> questionIds
	 * @return List<Object[]> 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getsurveyDetailsForAbove60Years(List<Long> questionIds,String age)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.surveyQuestion.surveyQuestionId ,model.surveyQuestion.question," +
				" model.option.optionsId ,model.option.options , count(model.option.optionsId) from SurveyAnswer model " +
				" where model.surveyQuestion.surveyQuestionId in (:questionIds) and " +
				" model.surveyAnswerInfo.respondent.surveyorProfile.age >= :age " +
				" group by model.surveyQuestion.surveyQuestionId,model.option.optionsId");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("questionIds", questionIds);
		query.setParameter("age", age);
		return query.list();
	}
	/**
	 * This DAO is used for getting the Gender based survey Analysis
	 * @param List<Long> questionIds
	 * @return List<Object[]> 
	 */
	public List<Object[]> getGenderWiseSurveyAnalysis(List<Long> questionIds,String gender)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.surveyQuestion.surveyQuestionId ,model.surveyQuestion.question," +
				" model.option.optionsId ,model.option.options ," +
				" count(model.surveyAnswerInfo.respondent.surveyorProfile.gender)  from SurveyAnswer model " +
				" where model.surveyQuestion.surveyQuestionId in (:questionIds) and " +
				" model.surveyAnswerInfo.respondent.surveyorProfile.gender = :gender " +
				" group by model.surveyQuestion.surveyQuestionId,model.option.optionsId, " +
				" model.surveyAnswerInfo.respondent.surveyorProfile.gender");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("gender", gender);
		query.setParameterList("questionIds", questionIds);
		return query.list();
	}
	/**
	 * This DAO is used for getting the option based survey Analysis
	 * @param List<Long> questionIds
	 * @return List<Object[]> 
	 */
	public List<Object[]> getOptionWiseSurveyAnalysis(List<Long> questionIds)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.surveyQuestion.surveyQuestionId ,model.surveyQuestion.question," +
				" model.option.optionsId ,model.option.options ," +
				" count(model.option.options)  from SurveyAnswer model " +
				" where model.surveyQuestion.surveyQuestionId in (:questionIds) " +
				" group by model.surveyQuestion.surveyQuestionId,model.option.optionsId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("questionIds", questionIds);
		return query.list();
	}

 
}
