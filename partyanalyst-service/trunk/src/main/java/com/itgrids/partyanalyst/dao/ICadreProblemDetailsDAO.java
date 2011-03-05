package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.ProblemHistory;

public interface ICadreProblemDetailsDAO extends GenericDao<CadreProblemDetails, Long>{
	
	public Integer deleteProblemDetailsByCadre(Long cadreId);
	
	public List getCadreDetailsByProblemHistoryId(Long problemHistoryId);
	
	public List<Object> getCadreProblemsCountInARegion(Long userId,Long impactedRegionId,Long locationId);
	
	public List<Object> getCadreProblemsCountForAnUser(Long userId);
	
	public List<ProblemHistory> getCadreProblemsInARegion(Long userId,Long impactedRegionId,Long locationId);
	
	public List<ProblemHistory> getCadreProblemsForAnUser(Long userId);

}
