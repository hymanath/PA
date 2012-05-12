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

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;

public class OpinionPollQuestionOptionsDAO extends GenericDaoHibernate<OpinionPollQuestionOptions, Long> implements IOpinionPollQuestionOptionsDAO {

	
	public OpinionPollQuestionOptionsDAO() {
		super(OpinionPollQuestionOptions.class);
	}
	public List<Object[]> getOptions(Long questionsRepositoryId)
	{
		return getHibernateTemplate().find("select model.opinionPollQuestionOptionsId,model.questionOption from OpinionPollQuestionOptions model where model.questionsRepository.questionsRepositoryId = ?",questionsRepositoryId);
	}
}
