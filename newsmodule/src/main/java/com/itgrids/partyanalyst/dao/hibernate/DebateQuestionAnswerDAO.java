package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateQuestionAnswerDAO;
import com.itgrids.partyanalyst.model.DebateQuestionAnswer;

public class DebateQuestionAnswerDAO extends GenericDaoHibernate<DebateQuestionAnswer, Long> implements IDebateQuestionAnswerDAO{

	public DebateQuestionAnswerDAO() {
		super(DebateQuestionAnswer.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateQuestionAndAnswerDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateQuestions.question , model.answer " +
				" from DebateQuestionAnswer model where model.debate.debateId = ? ",debateId);
	}
}
