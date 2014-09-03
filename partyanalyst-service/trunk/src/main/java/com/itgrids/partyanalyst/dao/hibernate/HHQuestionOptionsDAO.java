package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.HHQuestionOptions;
import com.itgrids.partyanalyst.utils.IConstants;


public class HHQuestionOptionsDAO extends GenericDaoHibernate<HHQuestionOptions,Long> implements IHHQuestionOptionsDAO {
	
	public HHQuestionOptionsDAO() {
		super(HHQuestionOptions.class);
	}
	
	public List<Object[]> getOptionsForQuestions(Long questionId){
		Query query = getSession().createQuery(" select model.hhOptions.optionsId," +
				" model.hhOptions.options," +
				" model.hhSurveyQuestion.hhoptionType.optionTypeId,model.hhSurveyQuestion.surveyQuestionId,model.hhSurveyQuestion.question " +
				" from HHQuestionOptions model" +
				" where model.hhOptions.isDelete =:delStatus " +
				" and model.hhSurveyQuestion.surveyQuestionId =:questionId ");
		
		query.setParameter("delStatus", IConstants.FALSE);
		query.setParameter("questionId", questionId);
		
		return query.list();
	}
	
	
}
