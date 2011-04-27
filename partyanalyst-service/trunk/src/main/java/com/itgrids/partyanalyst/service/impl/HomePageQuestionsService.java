package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.itgrids.partyanalyst.dao.IHomePageQuestionAnswersDAO;
import com.itgrids.partyanalyst.dao.IHomePageQuestionDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.HomePageQuestionsVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.model.HomePageQuestion;
import com.itgrids.partyanalyst.model.HomePageQuestionAnswers;
import com.itgrids.partyanalyst.service.IHomePageQuestionsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class HomePageQuestionsService implements IHomePageQuestionsService {

	private IRegistrationDAO registrationDAO;
	private IHomePageQuestionDAO homePageQuestionDAO;
	private IHomePageQuestionAnswersDAO homePageQuestionAnswersDAO;
	
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	public IHomePageQuestionDAO getHomePageQuestionDAO() {
		return homePageQuestionDAO;
	}
	public void setHomePageQuestionDAO(IHomePageQuestionDAO homePageQuestionDAO) {
		this.homePageQuestionDAO = homePageQuestionDAO;
	}
	
	
	
	public void setHomePageQuestionAnswersDAO(IHomePageQuestionAnswersDAO homePageQuestionAnswersDAO) {
		this.homePageQuestionAnswersDAO = homePageQuestionAnswersDAO;
	}
	public IHomePageQuestionAnswersDAO getHomePageQuestionAnswersDAO() {
		return homePageQuestionAnswersDAO;
	}
	public boolean saveHomePageQuestions(
			HomePageQuestionsVO homePageQuestionsVO, Long userId) {
		
		boolean isSaved=false;
		HomePageQuestion homePageQuestion = new HomePageQuestion();
		homePageQuestion.setQuestion(homePageQuestionsVO.getQuestion());
		homePageQuestion.setQuestionStartDate(homePageQuestionsVO.getQuestionStartDate());
		homePageQuestion.setQuestionEndDate(homePageQuestionsVO.getQuestionEndDate());
		homePageQuestion.setIsDelete("false");
		homePageQuestion.setRegistration(registrationDAO.get(userId));
		HomePageQuestion homePageQuestionobj = homePageQuestionDAO.save(homePageQuestion);
		
		for(QuestionsOptionsVO questionsOptionsVO:homePageQuestionsVO.getAnswerOptionsVOs()){
			HomePageQuestionAnswers homePageQuestionAnswers=new HomePageQuestionAnswers();
			homePageQuestionAnswers.setAnswerKey(questionsOptionsVO.getAnswerKey());
			homePageQuestionAnswers.setAnswerValue(questionsOptionsVO.getAnswerValue());
			homePageQuestionAnswers.setHomePageQuestion(homePageQuestionobj);
			homePageQuestionAnswersDAO.save(homePageQuestionAnswers);
		}
		
		isSaved = true;
		return isSaved;
	}
	
	
	public Date getCurrentDateAndTime(){
		try {
				java.util.Date now = new java.util.Date();
		        String DATE_FORMAT = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		        String strDateNew = sdf.format(now);        
				now = sdf.parse(strDateNew);
			return now;
	    } catch (ParseException e) {
	    		e.printStackTrace();
			return null;
		}
}
	
	
	public List<HomePageQuestionsVO> getQuestionsForTheDay(){
		List results = homePageQuestionDAO.getQuestionsForThePresentDay(getCurrentDateAndTime(), IConstants.TRUE);
		HomePageQuestionsVO homePageQuestionsVO;
		QuestionsOptionsVO questionsOptionsVO;
		List<QuestionsOptionsVO> questionsOptionsVOs;
		List<HomePageQuestionsVO> homePageQuestionVOs = new ArrayList<HomePageQuestionsVO>();
		
		for(int i = 0;i < results.size();i++){
			
			homePageQuestionsVO = new HomePageQuestionsVO();
			
			Object[] questionObj = (Object[])results.get(i);
			
			homePageQuestionsVO.setQuestion((String)questionObj[1]);
			
			Long questionId = (Long)questionObj[0];
			List<Object[]> answersList = homePageQuestionAnswersDAO.getAllAnswersForTheQuestion(questionId);
			questionsOptionsVOs=new ArrayList<QuestionsOptionsVO>();
			
			for(Object[] answers : answersList){
				
				questionsOptionsVO = new QuestionsOptionsVO();
				questionsOptionsVO.setAnswerKey((String)answers[0]);
				questionsOptionsVO.setAnswerValue((String)answers[1]);
				questionsOptionsVOs.add(questionsOptionsVO);
			}
			
			homePageQuestionsVO.setAnswerOptionsVOs(questionsOptionsVOs);
			
			homePageQuestionVOs.add(homePageQuestionsVO);
		}
		return homePageQuestionVOs;
}
}
