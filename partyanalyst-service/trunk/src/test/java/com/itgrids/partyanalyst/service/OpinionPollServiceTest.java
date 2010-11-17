package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.QuestionsRepository;

public class OpinionPollServiceTest {

	IOpinionPollDAO opinionPollDAO;
	IQuestionsRepositoryDAO questionsReposirtoryDAO ;
	IOpinionPollQuestionsDAO  opinionPollQuestionsDAO ;
	IOpinionPollQuestionOptionsDAO  opinionPollQuestionOptionsDAO ;
	IOpinionPollResultDAO  opinionPollResultDAO ;
	
	OpinionPoll opinionPoll;
	QuestionsRepository questionsRepository;
	OpinionPollQuestions opinionPollQuestions;
	OpinionPollQuestionOptions opinionPollQuestionOptions;
	OpinionPollResult opinionPollResult;
	
	OpinionPollVO opinionPollVo;
	QuestionsOptionsVO questionOptionsVO;
	OptionVO optionVO;
	
	IOpinionPollService opinionPollService;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		opinionPollDAO = EasyMock.createMock(IOpinionPollDAO.class);
		questionsReposirtoryDAO = EasyMock.createMock(IQuestionsRepositoryDAO.class);
		opinionPollQuestionsDAO = EasyMock.createMock(IOpinionPollQuestionsDAO.class);
		opinionPollQuestionOptionsDAO = EasyMock.createMock(IOpinionPollQuestionOptionsDAO.class);
		opinionPollResultDAO = EasyMock.createMock(IOpinionPollResultDAO.class);
		
		opinionPollVo = new OpinionPollVO();
		questionOptionsVO = new QuestionsOptionsVO();
		optionVO = new OptionVO();
		
		opinionPollService =  EasyMock.createMock(IOpinionPollService.class);
		
	}
	
	@Test
	 public void testForCreatePoll() {
		System.out.println("called createPoll() method..........");
		
		
		
	
	
	}
	
}
