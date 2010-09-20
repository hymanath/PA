package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.QuestionsRepository;
import com.itgrids.partyanalyst.utils.IConstants;


public class OpinionPollQuestionsDAOHibernateTest extends BaseDaoTestCase {
	
	private IOpinionPollQuestionsDAO opinionPollQuestionsDAO;

	public IOpinionPollQuestionsDAO getOpinionPollQuestionsDAO() {
		return opinionPollQuestionsDAO;
	}

	public void setOpinionPollQuestionsDAO(
			IOpinionPollQuestionsDAO opinionPollQuestionsDAO) {
		this.opinionPollQuestionsDAO = opinionPollQuestionsDAO;
	}
	
	 public void testGetAllPollsForTheCurrentDay(){
		 java.util.List result  = opinionPollQuestionsDAO.getAllPollsForThePresentDay(getCurrentDateAndTime(),IConstants.FALSE);
		 for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			OpinionPoll poll = (OpinionPoll) parms[0];
			System.out.println(poll.getDescription()+"\t"+poll.getOpinionPollEndDate());
			Set<OpinionPollQuestions> qr = (Set<OpinionPollQuestions>) poll.getOpinionPollQuestions();			
			for(OpinionPollQuestions ops : qr){
				QuestionsRepository questionsRepository = (QuestionsRepository) ops.getQuestionsRepository();
				System.out.println(questionsRepository.getQuestion());
				Set<OpinionPollQuestionOptions> opinionPollQuestionOptions = (Set<OpinionPollQuestionOptions>)  questionsRepository.getOpinionPollQuestionOptions();
				for(OpinionPollQuestionOptions options : opinionPollQuestionOptions){
					System.out.println(options.getQuestionOption());
				}
			}
		 }
		
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
}


