package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateQuestionsDAO;
import com.itgrids.partyanalyst.model.DebateQuestions;

public class DebateQuestionsDAO extends GenericDaoHibernate<DebateQuestions, Long> implements IDebateQuestionsDAO{

	public DebateQuestionsDAO() {
		super(DebateQuestions.class);
		// TODO Auto-generated constructor stub
	}

}
