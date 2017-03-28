package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.ProblemHistory;

public interface ICadreProblemDetailsDAO extends GenericDao<CadreProblemDetails, Long>{
	
	public Integer deleteProblemDetailsByCadre(Long cadreId);
	
	public List getCadreDetailsByProblemHistoryId(Long problemHistoryId);
	
	public List<Object> getCadreProblemsCountInARegion(Long userId,String locationStr);
	
	public List<Object> getCadreProblemsCountForAnUser(Long userId);
	
	public List<ProblemHistory> getCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<ProblemHistory> getCadreProblemsForAnUser(Long userId);
	
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId);
	
	public List<Object> getProblemStatusOfACadre(Long cadreId);
	
	public List<Cadre> getCadreByProblemHistoryId(Long problemHistoryId);
	
	public List<Object[]> getCadreDetailsAndMobileNoByProblemHistoryId(Long problemHistoryId);

}
