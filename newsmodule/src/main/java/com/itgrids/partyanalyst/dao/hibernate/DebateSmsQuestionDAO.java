package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionDAO;
import com.itgrids.partyanalyst.model.DebateSmsQuestion;

public class DebateSmsQuestionDAO extends GenericDaoHibernate<DebateSmsQuestion, Long> implements IDebateSmsQuestionDAO{

	public DebateSmsQuestionDAO() {
		super(DebateSmsQuestion.class);
		// TODO Auto-generated constructor stub
	}

}
