package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.OpinionPollVO;


public interface IOpinionPollService {
	
	public OpinionPollVO getAllPollsForTheDay();
	
	public void saveSelectionResultOfThePoll(Long opinionPollQuestionId,Long opinionPollQuestionOptionsId);
	
}
