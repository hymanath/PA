package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemHistory;

public interface IProblemHistoryDAO extends GenericDao<ProblemHistory, Long>{
	
	public List findProblemsForALocationsByHamletId(Long hamletId);
	
	public List findProblemsForALocationsByTehsilId(Long tehsilId);
	
	public List findProblemsForALocationsByConstituencyId(String constituencyIds);
	
	public List findProblemsByStatusForALocationsByHamletId(Long hamletId,String status);
	
	public List findProblemsByStatusForALocationsByTehsilId(Long tehsilId,String status);
	
	public List findProblemsByStatusForALocationsByConstituencyId(String constituencyIds,String status);
	
	public List<ProblemHistory> findProblemLocationsByUserId(Long registrationId, Long statusId);
	
	public List findCompleteProblems(Long problemLocationId);
	
	public List getProblemsCountInAllStatusByLocationForAUser(Long locationId, Long registrationId);
	
	public List findProblemsByDateAndLocation(String tehsilIds, Date fromDate, Date toDate);
	
	public List findProblemsByStatusDateAndLocation(String tehsilIds, Long statusId, Date fromDate, Date toDate);
}
