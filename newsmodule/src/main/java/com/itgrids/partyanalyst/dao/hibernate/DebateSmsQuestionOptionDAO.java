package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;
import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;

public class DebateSmsQuestionOptionDAO extends GenericDaoHibernate<DebateSmsQuestionOption, Long> implements IDebateSmsQuestionOptionDAO{

	public DebateSmsQuestionOptionDAO() {
		super(DebateSmsQuestionOption.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getDebateSmsQuestionsForSelectedDebate(Long debateId)
	{
		Query query = getSession().createQuery("select model.debateSmsQuestionOptionId , model.option,model.percantage " +
				" model.DebateSmsQuestion.debateSmsQuestionId,model.DebateSmsQuestion.question from DebateSmsQuestionOption model " +
				" where model.DebateSmsQuestion.debate.debateId = :debateId ");
		query.setParameter("debateId", debateId);
		return query.list();
	}

}
