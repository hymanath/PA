package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.model.OpinionPollResult;

public class OpinionPollResultDAO extends GenericDaoHibernate<OpinionPollResult, Long> implements IOpinionPollResultDAO {

	
	public OpinionPollResultDAO() {
		super(OpinionPollResult.class);
	}

	
	@SuppressWarnings("unchecked")
	public List getOpinionPollResultByQuestionAndOptionId(
			Long opinionPollQuestionId, Long opinionPollQuestionOptionsId) {
		
		 Object[] parms = {opinionPollQuestionId,opinionPollQuestionOptionsId};
		return getHibernateTemplate().find("select model.count,model.opinionPollResultID from OpinionPollResult model where" +
				" model.opinionPollQuestions.opinionPollQuestionsId  = ? and model.opinionPollQuestionOptions.opinionPollQuestionOptionsId = ?",parms);
	}
	
	@SuppressWarnings("unchecked")
	public List getOpinionPollAnswersForAQuestionByQuestionId(
			Long opinionPollQuestionId) {		
		 Object[] parms = {opinionPollQuestionId};
		return getHibernateTemplate().find("select model.count,model.opinionPollQuestionOptions.questionOption," +
				"model.opinionPollQuestionOptions.questionsRepository.question,DATEDIFF(CURDATE(),model.opinionPollQuestions.opinionPoll.opinionPollEndDate) from OpinionPollResult model where" +
				" model.opinionPollQuestions.opinionPollQuestionsId  = ? ",parms);
	}
	
	public List getOpinionPollAnswersForAQuestions(){
		
		return getHibernateTemplate().find("select model.count,model.opinionPollQuestionOptions.questionOption," +
				"model.opinionPollQuestionOptions.questionsRepository.question,DATEDIFF(CURDATE(),model.opinionPollQuestions.opinionPoll.opinionPollEndDate) from OpinionPollResult model");
		
	}
	
	public List getOpinionPollIds(){
		
		return getHibernateTemplate().find("select model.opinionPollId from OpinionPoll model order by model.opinionPollEndDate desc");
		
		
	}
	
}
