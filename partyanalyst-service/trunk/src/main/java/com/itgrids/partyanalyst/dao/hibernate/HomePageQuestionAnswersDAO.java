package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHomePageQuestionAnswersDAO;
import com.itgrids.partyanalyst.model.HomePageQuestionAnswers;

public class HomePageQuestionAnswersDAO extends GenericDaoHibernate<HomePageQuestionAnswers, Long> implements IHomePageQuestionAnswersDAO{

	public HomePageQuestionAnswersDAO() {
		super(HomePageQuestionAnswers.class);
	}
	
	
	public List getAllAnswersForTheQuestion(Long questionId) {
		return getHibernateTemplate().find("select model.answerKey,model.answerValue from HomePageQuestionAnswers model"+
				" where model.homePageQuestion.homePageQuestionId = "+questionId);
		
	}
}
