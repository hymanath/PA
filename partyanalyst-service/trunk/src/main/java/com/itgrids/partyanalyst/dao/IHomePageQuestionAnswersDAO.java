package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HomePageQuestionAnswers;

public interface IHomePageQuestionAnswersDAO extends GenericDao<HomePageQuestionAnswers, Long> {

	List getAllAnswersForTheQuestion(Long questionId);
	
}
