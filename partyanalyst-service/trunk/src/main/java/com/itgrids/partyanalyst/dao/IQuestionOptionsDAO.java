package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Option;
import com.itgrids.partyanalyst.model.QuestionOptions;

public interface IQuestionOptionsDAO  extends GenericDao<QuestionOptions, Long>{

	public List<Option> getOptionsForQuestion(Long surveyQuestionId);
	
	public List<Object[]> getOptionsForQuestionId(Long questionId);
}
