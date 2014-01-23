package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;
import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;

public class DebateSmsQuestionOptionDAO extends GenericDaoHibernate<DebateSmsQuestionOption, Long> implements IDebateSmsQuestionOptionDAO{

	public DebateSmsQuestionOptionDAO() {
		super(DebateSmsQuestionOption.class);
		// TODO Auto-generated constructor stub
	}

}
