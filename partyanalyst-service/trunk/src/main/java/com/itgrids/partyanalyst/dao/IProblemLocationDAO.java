package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemBackup;
import com.itgrids.partyanalyst.model.ProblemLocation;

public interface IProblemLocationDAO extends GenericDao<ProblemLocation, Long>{

	public List<ProblemLocation> findByHamletId(Long hamletId);
	
	public List<ProblemBackup> findByHamletIdAndYear(Long hamletId,String year);
	
	@SuppressWarnings("unchecked")
	public List findByHamletandYear(Long hamletId,String year);
	
	public List<ProblemLocation> findByHamletIdandYear(Long hamletId,String year);
	public List<ProblemLocation> findProblemsByUserId(Long userId);
	
	public List<ProblemLocation> findByLevel(Long levelId,Long levelValue);
	
	@SuppressWarnings("unchecked")
	public List findByLevel(Long levelId,List<Long> levelValues);
	
}
