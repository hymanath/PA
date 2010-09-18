package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;

public class OpinionPollResultDAOHibernateTest extends BaseDaoTestCase{

	private IOpinionPollResultDAO opinionPollResultDAO;

	public IOpinionPollResultDAO getOpinionPollResultDAO() {
		return opinionPollResultDAO;
	}

	public void setOpinionPollResultDAO(IOpinionPollResultDAO opinionPollResultDAO) {
		this.opinionPollResultDAO = opinionPollResultDAO;
	}
	
	public void testGet(){
		opinionPollResultDAO.getAll();
	}
}
