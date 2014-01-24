package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateSmsQuestion;

public interface IDebateSmsQuestionDAO extends GenericDao<DebateSmsQuestion, Long>{

	public List<DebateSmsQuestion> getDebateSmsQuestionDetails();
}
