package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.Option;
import com.itgrids.partyanalyst.model.QuestionOptions;

public class QuestionOptionsDAO extends GenericDaoHibernate<QuestionOptions, Long> implements IQuestionOptionsDAO{

	public QuestionOptionsDAO() {
		super(QuestionOptions.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Option> getOptionsForQuestion(Long surveyQuestionId){
		return getHibernateTemplate().find("select model.options from QuestionOptions model where model.surveyQuestion.surveyQuestionId = ?",surveyQuestionId);
	}
	/**
	 * This DAO is used for getting options based on Question
	 * @param Long questionId
	 * @param List<Object[]>
	 */
	public List<Object[]> getOptionsForQuestionId(Long questionId)
	{
		return getHibernateTemplate().find("select model.options.optionsId ,model.options.options " +
				"  from QuestionOptions model where model.surveyQuestion.surveyQuestionId = ?",questionId);
	}
}
