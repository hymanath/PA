package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.model.FeedBack;


public interface IOpinionPollService {
	
	public OpinionPollVO getAllPollsForTheDay();
	
	public QuestionsOptionsVO saveSelectionResultOfThePoll(final Long opinionPollQuestionId,final Long opinionPollQuestionOptionsId);
	
	public boolean createPoll(OpinionPollVO dataVO, Long userId);

	public List<QuestionsOptionsVO> getAllPolls();
	
	public QuestionsOptionsVO getQuestionAndPercentageOfVotesForChoices(Long opinionPollQuestionId);

	public OpinionPollVO getDetailsOfTheLatestOpinionPoll();
	
	public ResultStatus saveUserFeedback(UserFeedbackVO feedbackVO);
	
	public List<SelectOptionVO> getAllValuesFromFeedbackComment();

	public List<SelectOptionVO> getAllValuesFromFeedbackTask();
	
	public List<UserFeedbackVO> getAllDetailsForToday();
	
	 public Integer getAllApprovedDetails(List<Long> commentId,String actionType);
}

