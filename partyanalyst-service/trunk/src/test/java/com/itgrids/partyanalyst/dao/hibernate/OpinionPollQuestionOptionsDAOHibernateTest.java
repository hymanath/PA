package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;

public class OpinionPollQuestionOptionsDAOHibernateTest extends BaseDaoTestCase{

	private IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO;

	public IOpinionPollQuestionOptionsDAO getOpinionPollQuestionOptionsDAO() {
		return opinionPollQuestionOptionsDAO;
	}

	public void setOpinionPollQuestionOptionsDAO(
			IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO) {
		this.opinionPollQuestionOptionsDAO = opinionPollQuestionOptionsDAO;
	}
	
	
	public void testGet(){
		opinionPollQuestionOptionsDAO.getAll();
	}
}
