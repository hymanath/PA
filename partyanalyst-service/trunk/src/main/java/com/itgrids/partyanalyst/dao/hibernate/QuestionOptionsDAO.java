package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.QuestionOptions;

public class QuestionOptionsDAO extends GenericDaoHibernate<QuestionOptions, Long> implements IQuestionOptionsDAO{

	public QuestionOptionsDAO() {
		super(QuestionOptions.class);
		// TODO Auto-generated constructor stub
	}

}
