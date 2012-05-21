package com.itgrids.partyanalyst.dao;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.QuestionsRepository;

public interface IOpinionPollQuestionsDAO extends GenericDao<OpinionPollQuestions, Long>{

	public List getAllPollsForThePresentDay(Date currentDate,String isDelete) ;
	
	public List  getAllOpinionPolls(String isDelete);
	
	public List<Object> getTitleForQuestion(Long opinionPollQuestionsId);
}

