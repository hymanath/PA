package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;

public class OpinionPollQuestionsDAO extends GenericDaoHibernate<OpinionPollQuestions, Long> implements IOpinionPollQuestionsDAO {

	
	public OpinionPollQuestionsDAO() {
		super(OpinionPollQuestions.class);
	}

	
	public List getAllPollsForThePresentDay(Date currentDate) {	
		Object[] parms = {currentDate,currentDate};
		return getHibernateTemplate().find("select model.opinionPoll," +
				" model.questionsRepository.question,model.questionsRepository from OpinionPollQuestions model where " +				
				" date(model.opinionPoll.opinionPollStartDate) <= ? and date(model.opinionPoll.opinionPollEndDate) >= ? ",parms);
	}
}
