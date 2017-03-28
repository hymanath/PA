package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;

public class OpinionPollQuestionOptionsDAO extends GenericDaoHibernate<OpinionPollQuestionOptions, Long> implements IOpinionPollQuestionOptionsDAO {

	
	public OpinionPollQuestionOptionsDAO() {
		super(OpinionPollQuestionOptions.class);
	}
	public List<Object[]> getOptions(Long questionsRepositoryId)
	{
		return getHibernateTemplate().find("select model.opinionPollQuestionOptionsId,model.questionOption from OpinionPollQuestionOptions model where model.questionsRepository.questionsRepositoryId = ?",questionsRepositoryId);
	}
}
