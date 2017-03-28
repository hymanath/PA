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
	
	public List<Object[]> getSurveyCompletedLocations();
	public List<Long> getCompletedStatusBoothsByBoothIds(List<Long> boothIds);
	public List<Long> getProsessingBoothsCountForPanchayatisByConstituencyId(Long constituencyId,List<Long> panchayatIds);
	public List<Long> getCompletedBoothsIdsByConstituencyId(Long constituencyId);
	public List<Long> getVerificationProcessBoothsByConstituencyId(Long constituencyId);
	public List<Long> getCompletedBoothsDetailsByStatusIdAndConstituencyId(Long constituencyId,Long statusId);
	public List<Long> getBoothsOfConstituecyByStatusByPanchayat(Long constituencyId,Long scopeId);
	public void deleteSurveyCompletedDetailsByLocationValueAndScopeForThirdParty(Long locationValue,Long scopeId,List<Long> thirdPartyScopes);
	public List<Long> getThirdPartyVerificationProcessingBoothsByConstituencyId(Long constituencyId);
	
	public List<Long> getBoothsOfTPWithStatus(Long constituencyId,Long statusId);
	
	public List<Long> getAllThirdPartyRelatedBoothByConstituencyId(Long constituencyId,List<Long> thirdPartyStatusIds);
	public List<Object[]> getBoothsAndConstituenciesOfTPWithStatus(Long statusId);
	public List<Object[]> getDistrictWiseCompletedConstituenciesDetails();

	public List<Object[]> getCompletedConstituencyDetails();
	public List<Object[]> getSurveyConstituenciesStatus();
	
}

