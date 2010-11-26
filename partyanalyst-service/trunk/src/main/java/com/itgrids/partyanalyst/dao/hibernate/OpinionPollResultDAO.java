package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.QuestionsRepository;

import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;

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
	
}
