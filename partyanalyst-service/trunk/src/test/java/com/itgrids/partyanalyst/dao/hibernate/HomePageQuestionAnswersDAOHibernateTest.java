package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHomePageQuestionAnswersDAO;

public class HomePageQuestionAnswersDAOHibernateTest extends BaseDaoTestCase{
	
	Long questionId=32l;
	private IHomePageQuestionAnswersDAO homePageQuestionAnswersDAO;

	public void setHomePageQuestionAnswersDAO(
			IHomePageQuestionAnswersDAO homePageQuestionAnswersDAO) {
		this.homePageQuestionAnswersDAO = homePageQuestionAnswersDAO;
	}
	
	/*public void test(){
		
		homePageQuestionAnswersDAO.getAll();
	}*/
	
	public void testGetAllAnswersForTheQuestion(){
		List l=homePageQuestionAnswersDAO.getAllAnswersForTheQuestion(questionId);
		System.out.println(l.size());
	}
	
}