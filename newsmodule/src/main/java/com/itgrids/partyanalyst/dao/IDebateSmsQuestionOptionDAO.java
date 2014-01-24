package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;

public interface IDebateSmsQuestionOptionDAO extends GenericDao<DebateSmsQuestionOption, Long>{

	public List<Object[]> getDebateSmsQuestionsForSelectedDebate(Long debateId);
}
