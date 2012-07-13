package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemStatus;

public interface IProblemDAO extends GenericDao<Problem,Long>{
	
	public Integer deleteProblemDetails(Long problemId,Date currentDate);
	
	public Long getCountOfNewlyPostedProblemsByFreeUser();
	
}
