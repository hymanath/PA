package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.ProblemComments;

public interface IProblemCommentsDAO extends GenericDao<ProblemComments,Long>{
	
	public List<Long> getAllApprovedComments(Long problemId);

}
