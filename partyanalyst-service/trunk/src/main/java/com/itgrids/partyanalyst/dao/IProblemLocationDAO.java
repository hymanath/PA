package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemLocation;

public interface IProblemLocationDAO extends GenericDao<ProblemLocation, Long>{

	public List<ProblemLocation> findByHamletId(Long hamletId);
	
}
