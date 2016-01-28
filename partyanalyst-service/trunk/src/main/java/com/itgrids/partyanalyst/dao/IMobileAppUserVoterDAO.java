package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserVoter;

public interface IMobileAppUserVoterDAO extends GenericDao<MobileAppUserVoter, Long>{
	public List<Object[]> getUserStartEndTime(Long locationId, String locationType, Date fromDate, Date endDate,String userType);
	public List<Object[]> getUserCollectedDetails(Long locationId, String locationType, Date fromDate, Date toDate,String userType);
	public List<Object[]> getUserCollectedRatingDetails(Long locationId, String locationType, Date fromDate, Date toDate,String userType);
	public List<Object[]> locationWiseOverView(Date StartDate,Date endDate,List<Long> locationIds,String locationType,List<String> userTypes);
	public List<Object[]> voterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<Object[]> overAllDivisionsSummary(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<Object[]> overallVoterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes);
	public List<MobileAppUserVoter> getVoterDataForBooth(List<Long> voterIds,Long boothId);
	public List<Object[]> getLatiLongi(Long userId,Long divisonId,List<Date> datesList);
	public List<Object> getAllAvailableForUser(Long userId,Long divisonId);
	public Long getNumberOfNumsCollected(Long userId,Long divisonId,List<Date> datesList);
}	
