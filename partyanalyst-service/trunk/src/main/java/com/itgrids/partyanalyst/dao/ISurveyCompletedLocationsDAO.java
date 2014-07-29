package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCompletedLocations;

public interface ISurveyCompletedLocationsDAO extends GenericDao<SurveyCompletedLocations, Long>{
	
	public List<Object[]> getCompletedBoothsDetailsByConstituencyIds(List<Long> constituencyIds);
	public List<Object[]> getBoothsStatusDetailsByConstituencyId(Long constituencyId);
	public List<Object[]> getCompletedBoothsCountForPanchayatisByConstituencyId(Long constituencyId);
	public void deleteSurveyCompletedDetailsByLocationValueAndScope(Long locationValue,Long scopeId);
	public List<Long> getBoothsOfConstituecyByStatus(Long constituencyId,Long statusId,Long scopeId);
}
