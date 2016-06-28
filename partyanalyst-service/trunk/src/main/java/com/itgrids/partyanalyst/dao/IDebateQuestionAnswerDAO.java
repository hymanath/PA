package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateQuestionAnswer;

public interface IDebateQuestionAnswerDAO extends GenericDao<DebateQuestionAnswer, Long>{

	public List<Object[]> getDebateQuestionAndAnswerDetails(Long debateId);
}
