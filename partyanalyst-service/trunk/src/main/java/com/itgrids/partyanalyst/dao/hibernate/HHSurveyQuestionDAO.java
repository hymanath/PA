package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHSurveyQuestionDAO;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;
import com.itgrids.partyanalyst.utils.IConstants;


public class HHSurveyQuestionDAO extends GenericDaoHibernate<HHSurveyQuestion,Long> implements IHHSurveyQuestionDAO {
	
	public HHSurveyQuestionDAO() {
		super(HHSurveyQuestion.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<HHSurveyQuestion> getModelBySurveyId(Long surveyId){
		Query qry=getSession().createQuery(" from HHSurveyQuestion model where model.hhSurvey.surveyId =:surveyId and model.isDeleted = :deleteStatus order by orderId");
		qry.setParameter("surveyId", surveyId);
		qry.setParameter("deleteStatus", IConstants.FALSE);
		return qry.list();
	}
	
	public List<Object[]> getAllQuestionInSurvey(Long surveyId){
		Query query = getSession().createQuery("select model.surveyQuestionId," +//0 -- Quest Id
				" model.question, " +//1 -- Quest
				" model.hhoptionType.optionTypeId " +//2 -- Answer Type
				" from HHSurveyQuestion model" +
				" where model.hhSurvey.surveyId =:surveyId" +
				" and model.isDeleted =:delStatus");
		
		query.setParameter("surveyId",surveyId);
		query.setParameter("delStatus", IConstants.FALSE);
		
		return query.list();
	}
	
	
	
}
