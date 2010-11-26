package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;

public class OpinionPollQuestionsDAO extends GenericDaoHibernate<OpinionPollQuestions, Long> implements IOpinionPollQuestionsDAO {

	
	public OpinionPollQuestionsDAO() {
		super(OpinionPollQuestions.class);
	}

	
	public List getAllPollsForThePresentDay(Date currentDate,String isDelete) {	
		Object[] parms = {currentDate,currentDate,isDelete};
		return getHibernateTemplate().find("select model.opinionPoll," +
				" model.questionsRepository.question,model.questionsRepository,model from OpinionPollQuestions model where " +				
				" date(model.opinionPoll.opinionPollStartDate) <= ? and date(model.opinionPoll.opinionPollEndDate) >= ? " +
				" and model.opinionPoll.is_delete != ? "+"order by model.opinionPollQuestionsId desc",parms);
	}
	
	@SuppressWarnings("unchecked")
	public List  getAllOpinionPolls(String isDelete){		
		Object[] parms = {isDelete};
		return getHibernateTemplate().find("select model.opinionPollQuestionsId," +
				" model.questionsRepository.question,model.opinionPoll.opinionPollStartDate from OpinionPollQuestions model where model.opinionPoll.is_delete != ?" +
				"order by model.opinionPollQuestionsId desc",parms);
	}
	
}
