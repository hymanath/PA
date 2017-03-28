
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHQuestionOptions;

public interface IHHQuestionOptionsDAO extends GenericDao<HHQuestionOptions, Long>{
	public List<Object[]> getOptionsForQuestions(Long questionId);
	
}