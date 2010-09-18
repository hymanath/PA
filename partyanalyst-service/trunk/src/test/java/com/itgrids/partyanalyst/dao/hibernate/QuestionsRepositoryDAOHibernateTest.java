package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;

public class QuestionsRepositoryDAOHibernateTest extends BaseDaoTestCase{

	private IQuestionsRepositoryDAO questionsRepositoryDAO;

	public IQuestionsRepositoryDAO getQuestionsRepositoryDAO() {
		return questionsRepositoryDAO;
	}

	public void setQuestionsRepositoryDAO(
			IQuestionsRepositoryDAO questionsRepositoryDAO) {
		this.questionsRepositoryDAO = questionsRepositoryDAO;
	}
	
	public void testGet(){
		questionsRepositoryDAO.getAll();
	}
}
