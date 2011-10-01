package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemFile;

public interface IProblemFileDAO extends GenericDao<ProblemFile, Long> {
 
	
	public List<Object[]> getProblemImagesBasedHistoryId(Long probHistoryId);
}
