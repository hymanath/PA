package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsProblem;

public interface INewsProblemDAO extends GenericDao<NewsProblem,Long> {
	
	public List<Object[]> getProblemIdsByFileIds(List<Long> fileIdsList);
	public List<Long> getCountByProblemId(Long problemId);
	public List<NewsProblem> getNewsProblemByProblemId(Long problemId);

}
