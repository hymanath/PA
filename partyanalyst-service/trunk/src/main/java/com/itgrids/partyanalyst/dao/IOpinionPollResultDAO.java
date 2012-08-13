package com.itgrids.partyanalyst.dao;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollResult;



public interface IOpinionPollResultDAO extends GenericDao<OpinionPollResult, Long>{

	public List getOpinionPollResultByQuestionAndOptionId(Long opinionPollQuestionId,Long opinionPollQuestionOptionsId);
	
	public List getOpinionPollAnswersForAQuestionByQuestionId(Long opinionPollQuestionId);
	
	public List getOpinionPollAnswersForAQuestions();
	
	public List getOpinionPollIds();


	
}