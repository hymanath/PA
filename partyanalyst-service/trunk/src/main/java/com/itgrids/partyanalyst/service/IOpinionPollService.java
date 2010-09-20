package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;


public interface IOpinionPollService {
	
	public OpinionPollVO getAllPollsForTheDay();
	
	public QuestionsOptionsVO saveSelectionResultOfThePoll(final Long opinionPollQuestionId,final Long opinionPollQuestionOptionsId);
	
}
