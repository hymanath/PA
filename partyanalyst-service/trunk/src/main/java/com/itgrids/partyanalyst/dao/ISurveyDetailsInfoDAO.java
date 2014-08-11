package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyDetailsInfo;

public interface ISurveyDetailsInfoDAO  extends GenericDao<SurveyDetailsInfo, Long>
{
	public List<Object[]> getDayWisereportDetailsByConstituencyId(Long constituencyId,Date startDate,Date endDate,Long userTypeId);
	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate);

	public List<SurveyDetailsInfo> getLatLongForSurveyDetails(Long surveyUserId,Date date);
	
	public List<SurveyDetailsInfo> getSurveyDetilsForAssibnedBooths(Long boothIds);
	public SurveyDetailsInfo checkUserForVoter(long userId,String uuid,Long voterId );

	public List<Object[]> getVoterDetailsForbooths(List<Long> boothIds);
	public List<SurveyDetailsInfo> getVerifiedVotersDetailsBySurveyDetailsInfoId(Long voterId);
	public List<Long> getDataCollectedVoterIdsByBoothIds(List<Long> boothIds);
	public List<Object[]> getDayWisereportDetailsByConstituencyIdAndUserTypeId(
			Long constituencyId, Date startDate, Date endDate, Long userTypeId,List<Long> boothIds);
	
	public List<Object[]> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date);
	
	public Long getCasteCountByBooth(Long userId,Long boothId);
	
	public Long getHamletCountByBooth(Long userId,Long boothId);

	public Long getLocalAreaCountByBooth(Long userId,Long boothId);
	
	public Long getCadreCountByBooth(Long userId,Long boothId);
	
	public Long getInfluencingPeopleCountByBooth(Long userId,Long boothId);
	
	public List<Object[]> getsurveyDetailsInfoByConstituencyId(Long constituencyId,Long surveyUsertypeId);
	
	public List<Object[]> getBoothDetailsByConstituencyId(Long constituencyId);
	
	public List<Object[]> getsurveyDetailsInfoByboothId(Long boothId,Long surveyUsertypeId);
	
	public List<Object[]> getVoterDetailsByBoothId(Long boothId, List<Long> assignUsers,Date searchDate);
	
	public List<Long> getSurveyStartedConstituenciesDetails();
	
	public List<Object[]> getSurveyStartedConstituencyInfo();
	
	//public List<Object[]> getSurveyDetailsByConstituency(Long constituencyId,Long userTypeId);
	public List<Object[]> getBoothCount(Long constituencyId,Long userTypeId);
	public List<Object[]> getHamletCount(Long constituencyId,Long userTypeId);
	public List<Object[]> getCasteCount(Long constituencyId,Long userTypeId);
	

	public List<Object[]> getSurveyBooths();
	
	
	public List<Object[]> getAllUserDetailsByConstituency(Long constituencyId,Date date);
	
	//public List<Object[]> getSurveyDetailsByConstituency(Long constituencyId,Long userTypeId,Date date);
	public List<Object[]> getHamletCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate);
	public List<Object[]> getCasteCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate);
	public List<Object[]> getMbileNoCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate);
	public List<Object[]> getLatLongForSurveyUsersByConstituencyByUser(Long constituencyId,Date date,List<Long> userId);
	
	
	public List<Object[]> getCasteWiseCountInBooth(Long boothId,List<Long> surveyUserIds);
	
	public List<Object[]> getVerifierCollectedDetails(Long surveyUserId,Long boothId);
	
	public Long getTotalVotersinBooth(Long boothId);
	public Long getTotalCastecollectedCount();
	public Long getTotalCastecollectedCountForToday(Date date);
	
	public List<Object[]> findConstituenciesByDistrictId(Long districtId);
	public List<Object[]> getTotalCastecollectedCountForDates();
	public List<Object[]> getTotalCastecollectedCountForTodayForDates(Date date);
	//public List<Object[]> getSurveyDetailsByConstituencyByUsers(Long constituencyId,Long userTypeId,Date date,List<Long> userIds);
	
	public List<Object[]> getAllUserDetailsByConstituencyByUsers(Long constituencyId,Date date,List<Long> userIds);
	public List<Object[]> getDayWisereportDetailsByConstituencyIdAndUserIds(Long constituencyId,Date startDate,Date endDate,Long userTypeId,List<Long> boothIds,List<Long> userIds);
	public List<Long> getPresentDayUserWiseSamplesCountByUserIds(List<Long> userIds,Date presentDate);
	public List<Object[]> getBoothWiseDcAndDvDetails(List<Long> boothId);

	public List<Object[]> getVotersDetailsByBoothId(Long boothId,List<Long> assignUsers,Date searchDate,Long casteStateId);
	
	public List<Object[]> getBoothWiseUser(List<Long> boothIds,Long typeId);
	public List<Object[]> getProcecingBoothCountByConstId(Long constituencyId);
	public List<?> getHamletCountBasedOnPanchayIds(List<?> panchayatIds);
	
	public List<Object[]> getHamletCountByListOfBooths(List<Long> boothIds);
	public List<Object[]> getCasteCountByListOfBooths(List<Long> boothIds);
	public List<Object[]> getMobileNoCountByListOfBooths(List<Long> boothIds);
	public Long getHamletCountByBooths(List<Long> boothIds);
	public Long getMbileNoCountByBooths(List<Long> boothIds);
	public Long getCasteCountByBooths(List<Long> boothIds);
	public List<Object[]> getBoothWiseDcDetails(Long boothId);
	public List<Object[]> getBooths(Long constituencyId,Long surveyUserType);
	
	public List<Object[]> getUsersCompleteReportForSameDate(Date startDate,Date endDate);
	public List<Object[]> getStartAndEndTimesByUserIds(Date startdate,Date endDate );
	public List<Object[]> getUsersReportDetailsForBetweenDates(Date startDate,Date endDate);
	public List<Object[]> getDCPerformanceBoothWise(Long surveyUserId,Long userTypeId,Date FromDate,Date toDate);
	public List<Object[]> getDcorDvUsersByConstituency(Long userTypeId);
	public List<String> getCasteCollectedDatesByConstituencyId(Long constituencyId);
	public List<Object[]> getUserReportForADate(Long userId,Date surveyDate);
	public List<Object[]> getSurveyDetailsByConstituency(Long constituencyId,Long userTypeId,Date date,Date todate);
	public List<Object[]> getSurveyDetailsByConstituencyByUsers(Long constituencyId,Long userTypeId,Date date,List<Long> userIds,Date todate);
	public List<Long> getUserIdsForConstituency(Long constituencyId,Long userTypeId,Date date,Date todate);
	public List<Long> getUserIdsForConstituencyByUser(Long constituencyId,Long userTypeId,Date date,List<Long> userIds,Date todate);
	
	public Long getCasteCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getHamletCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getLocalAreaCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getCadreCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getInfluencingPeopleCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getTotalSurveyVotersByconstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	public Long getMobileCountByBoothByConstituency(Long constituencyId,Long surveyUsertypeId, String searchType);
	
	public List<Object[]> getDataCollectedCountForConstituency(List<Long> constituenycIds);

	public List<Long> getVerificationStartedBoothsDetailsByConstituencyId(Long constituencyId);
	public List<Object[]> getCasteTaggedVotersForAllConstituencies(Long surveyUsertypeId);
	public List<Object[]> getMobileTaggedVotersForAllConstituencies(Long surveyUsertypeId);
	
	public List<SurveyDetailsInfo> getVerifierSurveyDetails(Long boothId);
	
	public List<Long> getUsersByDate(Long constituencyId,Date date);
	public List<Object[]> getStartedBoothsDetailsByConstituencyIds(List<Long> constituencyIds);
	public List<Long> getBoothsInProcessByConstituencyId(Long constituencyId);
	
	public List<Object[]> getProcessingConstituencyes();
	
	public List<String> getCasteCollectedDatesByUserId(Long userId);

	public List<Long> getUsersByDate(List<Long> userIds,Date date);
	
	public List<Object[]> getBoothWiseVerifier(List<Long> boothIds);
	public Long getWardsCountByBooth(Long userId,Long boothId);
	public List<Object[]> getStartAndEndTimesByUserIdsAndConstituencyIdAndDates(Long constituencyId,Date date,List<Long> userIds);
	public List<Object[]> getAllUsersDetilsByUserIdsForSelectedDate(Long constituencyId,Date date,List<Long> userIds);
	
	public List<String> getCasteCollectedDates();
	public List<Object[]> getHouseNosMappedCount(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate);
	
	public List<Object[]> getTotalSamplesInBoothsOfUserType(Long constituencyId,Long surveyUserType);
	public List<Object[]> getThirdPartyCollectedDetails(Long boothId,Long surveyUserId);
	public List<Object[]> getBoothWiseCollectedDetailsForConstituency(Long constituencyId,Long userTypeId,String attribute,Long statusId);
	public List<Object[]> getTotalDataCollectedCount(List<Long> userIds,List<Long> boothIds);
	public List<Long> getThirdPartyStartedConstituencies();
	public List<Object[]> getBoothDetailsByForThirtyPartyVerifiers(Long constituencyId,Long surveyUserTypeId);
	public List<SurveyDetailsInfo> getsurveyDetailsInfoByVoterId(Long surveyUserId, Long voterId);	
}

