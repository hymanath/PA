package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemRating;

public interface IProblemRatingDAO extends GenericDao<ProblemRating, Long>{
	
	public Double getAverageRatingOfAProblem(Long problemId);
	
	public List<Object[]> getRatingWiseCountOfAProblem(Long problemId);

}
