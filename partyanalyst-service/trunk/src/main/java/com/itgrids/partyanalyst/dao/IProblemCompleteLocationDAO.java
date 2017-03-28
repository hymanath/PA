package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemCompleteLocation;

public interface IProblemCompleteLocationDAO extends GenericDao<ProblemCompleteLocation, Long> {

	public List<ProblemCompleteLocation> getAll();
	
}
