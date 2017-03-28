package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.ProblemAssignedCadre;
import com.itgrids.partyanalyst.model.UserProblem;

public interface IProblemAssignedCadreDAO extends GenericDao<ProblemAssignedCadre,Long>{
	public List<ProblemAssignedCadre> getProblemAssignedCadreByUserProblemId(Long userProblemId);
	
	public List<UserProblem> getAssignedCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Object> getAssignedCadreProblemsCountForAnUser(Long userId);
	
	public List<Object> getAssignedCadreProblemsCountInARegion(Long userId,String locationStr);
	
	public List<UserProblem> getAssignedCadreProblemsForAnUser(Long userId);
	
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId);
	
	public List<Object[]> getProblemIds(Long userId,boolean userProbOnly);
}
