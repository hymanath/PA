package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;

public class QuestionOptionsDAOHibernateTest extends BaseDaoTestCase  {
	private IQuestionOptionsDAO questionOptionsDAO;

	public void setQuestionOptionsDAO(IQuestionOptionsDAO questionOptionsDAO) {
		this.questionOptionsDAO = questionOptionsDAO;
	}
	
	public void testGetAll(){
		questionOptionsDAO.getAll();
	}
}
