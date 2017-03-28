package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblems;
import com.itgrids.partyanalyst.model.Problem;

public interface ICadreProblemsDAO extends GenericDao<CadreProblems,Long>{
	public List<Object[]> getCadreDetailsAndMobileNoByProblemId(Long problemId);
	public List<Object> getCadreProblemsCountForAnUser(Long userId);
	
	public List<Object> getCadreProblemsCountInARegion(Long userId,String locationStr);
	
	public List<Problem> getCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Problem> getCadreProblemsForAnUser(Long userId);
	
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId);
	
	public List<Object> getProblemStatusOfACadre(Long cadreId);

	public List<Object[]> getProblemPostedCadreName(Long problemId);
	
	public List<CadreProblems> getCadreProblemDetailsByProblemId(Long problemId);
	
	public Integer  deleteCadreProblem(Long problemId);
	
	public List<Object[]> getProblemPostedCadreNameAndId(Long problemId);
}
