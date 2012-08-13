package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommentVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;


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
	 
	public void saveOpinionPollComment(Long pollId,RegistrationVO regVO,String commentDscr,String firstName,String lastName);
	
	public List<QuestionsOptionsVO> getAllQuestionAndPercentageOfVotesForChoices();
	
	public List<CommentVO> getCommentsnOpinionPollByQuestionId(Long pollId);
	
	public String saveAbuseCommentDetails(Long cmntId,RegistrationVO regVO);
	
	}

