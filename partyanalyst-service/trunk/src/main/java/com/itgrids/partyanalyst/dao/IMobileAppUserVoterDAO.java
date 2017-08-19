package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserVoter;

public interface IMobileAppUserVoterDAO extends GenericDao<MobileAppUserVoter, Long>{
	public List<Object[]> getUserStartEndTime(Long locationId, String locationType, Date fromDate, Date endDate,List<String> userType);
	public List<Object[]> getUserCollectedDetails(Long locationId, String locationType, Date fromDate, Date toDate,List<String> userType);
	public List<Object[]> getUserCollectedRatingDetails(Long locationId, String locationType, Date fromDate, Date toDate,List<String> userType);
	public List<Object[]> locationWiseOverView(Date StartDate,Date endDate,List<Long> locationIds,String locationType,List<String> userTypes);
	public List<Object[]> voterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<Object[]> overAllDivisionsSummary(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<Object[]> overallVoterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<MobileAppUserVoter> getVoterDataForBooth(List<Long> voterIds,Long boothId);
	public List<Object[]> getLatiLongi(Long userId,Long divisonId,List<Date> datesList);
	public List<Object> getAllAvailableForUser(Long userId,Long divisonId);
	public Long getNumberOfNumsCollected(Long userId,Long divisonId,List<Date> datesList);
	
	public List<Long> getTrackingDivisionIds(List<Long> locationIds);
	public Object[] getTrackingDivisionSummaryCounts(List<Long> locationIds);
	public List<Object[]> getCapturedVoterRatings(List<Long> locationIds);
	public List<Object[]> mobileAppUserVoterId(List<Long> voterIds);
	
	
	public List<Object[]> divisionWiseTotalVotersAndCapturedCadre(List<Long> locationIds);
	public List<Object[]> getPolledVotersAndPolledCadre(List<Long> divisionIds);
	public List<Object[]> getCapturedCadrePolled(List<Long> divisionIds);
	public List<Object[]> getNonCapturedVotersPolled(List<Long> divisionIds);
	public List<Object[]> gettrackedAndPolledratingVoters(List<Long> divisionIds,String type);
	public List<Object[]> getNotYetPolledMembers(String resultType,Long locationId);
	public List<Object[]> getPolledVotersAndPolledCadreForBooth(List<Long> boothIds);
	public List<Object[]> getCapturedCadrePolledForBooth(List<Long> boothIds,String type);
	public List<Object[]> getCapturedVotersForBooth(List<Long> boothIds,String type);
	public List<Object[]> getTrackedAndPolledratingVotersForBooth(List<Long> boothIds,String type);
	public List<Object[]> getBoothWisePolledVoters(List<Long> boothIds);
	public List<Object[]> getBoothsBasedOnRating(List<Long> rating);
	public List<Object[]> getVotersInfo(Long boothId,Long wardId,String isVoted,String resultType);
	public List<Long> mobileAppUserVoterIds(List<String> mobileNos);
	public List<Object[]> getUserStartEndTimeByLevelId(Long locationId, Long levelId, Date fromDate, Date toDate,Long publicationDateId,List<String> userType);
	public List<Object[]> getUserCollectedDetailsByLvelId(Long locationId,Long levelId, Date fromDate, Date toDate,Long publicationDateId,List<String> userType);
	public List<Object[]> getUserCollectedRatingDetailsByLvelId(Long locationId,Long levelId, Date fromDate, Date toDate,Long publicationDateId,List<String> userType);
	public List<Object[]> getLatiLongi(Long userId,Long locationId,Long levelId,List<Date> datesList);
	public List<Object> getAllAvailableForUser(Long userId,Long locationId,Long levelId,List<Date> datesList);
	public Long getNumberOfNumsCollected(Long userId,Long locationId,Long levelId,List<Date> datesList);
}	
