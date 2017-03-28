package com.itgrids.partyanalyst.dao;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollResult;

public interface IOpinionPollQuestionOptionsDAO extends GenericDao<OpinionPollQuestionOptions, Long>{
	
	public List<Object[]> getOptions(Long questionsRepositoryId);

}
