package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateQuestions;

public interface IDebateQuestionsDAO extends GenericDao<DebateQuestions	, Long>{

	public List<DebateQuestions> getDebateQuestionDetails();
}
