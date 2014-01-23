package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateQuestionAnswerDAO;
import com.itgrids.partyanalyst.model.DebateQuestionAnswer;

public class DebateQuestionAnswerDAO extends GenericDaoHibernate<DebateQuestionAnswer, Long> implements IDebateQuestionAnswerDAO{

	public DebateQuestionAnswerDAO() {
		super(DebateQuestionAnswer.class);
		// TODO Auto-generated constructor stub
	}

}
