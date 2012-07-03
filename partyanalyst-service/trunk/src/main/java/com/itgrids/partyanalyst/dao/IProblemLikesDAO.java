package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ProblemLikes;

public interface IProblemLikesDAO extends GenericDao<ProblemLikes,Long>{
	
	public List<Long> getAllLikes(Long problemId);
	public List<Long> getAllDisLikes(Long problemId);

}
