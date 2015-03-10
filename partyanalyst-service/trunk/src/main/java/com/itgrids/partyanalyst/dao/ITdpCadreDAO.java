package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.model.TdpCadre;

public interface ITdpCadreDAO extends GenericDao<TdpCadre, Long>{

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise();
	
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex);
	
	public List<Object[]> getRegisterCadreInfoDistrictWise();
	
	public String getLatestMemberNumber();
	
	public Long getWorkStartedConstituencyCount(String state);
	
	public Long getWorkStartedConstituencyYearCount(Long year,String state,Date fromDate, Date toDate);
		
	public List<Object[]> getNewlyRegisterCadreInfo();
	
	public List<Object[]> getRecentlyRegisteredCadres();
	
	public List<Long> getCadreAvailableConstituencies(Long stateId);
	
	public List<Object[]> getCadreInfoConstituencytWise(List<Long> constituencyIds,Date fromDate, Date toDate,Long year);
	
	public List<Object[]> getCadreInfoDistrictWise(List<Long> districtIds,Date fromDate, Date toDate,Long year);
	
	public List<Long> getCadreAvailableDistricts(Long stateId);
	
	public Long getWorkingMembersCount(Date date);
	
	public List<TdpCadre> getVoterByVoterId(Long voterId);
	
	public Long checkRandomNoExistsOrNot(String dataSource,String randomNo);
	
	public List<Object[]> getexistringCadreInfoByLocation(String candidateName, Long constid, Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo);
	
	public List<Object[]> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate);

	public Long checkMemberShipExistsOrNot(String randomNo);
	
	public List<Object[]> getCadreDetailsByMemberId(String memberCardNo);
	
	public List<Object[]> getPanchayatWiseCadreDetails1(Long panchayatId,String type);
	
	public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId);
	
	public Integer updateNFCCardNumberByVoterId(Long voterId , String nfcCardNo);	
	
	public Integer updateNFCCardNumberByTdpCadreId(Long tdpCadreId,String nfcCardNo);
		
	public Long getAgeRangeTotalCount(Long Id,Long enrollmentYear,String type);
		
	public List<Object[]> getAgeRangeCadreCount(Long Id,String ageRange,String type);	
		
	public Long getGenderTotalCount(Long Id, Long enrollmentYear, String type);	
		
	public List<Object[]> getGenderWiseCadreCount(Long Id,String type);
		
	public Long getCasteTotalCount(Long Id,Long enrollmentYear,String type);
		
	public List<Object[]> getCastWiseCadreCount(Long Id,String type);
	
	public List<Object[]> getCadreInfoPanchayatWise(List<Long> panchayatIds,Date fromDate, Date toDate,Long year); 
	
	public List<Object[]> getCadreInfoBoothWise(List<Long> boothIds,Date fromDate, Date toDate,Long year);
	
	public List<Object[]> getCadreInfoMandalWise(List<Long> tehsilIds,Date fromDate, Date toDate,Long year);
	
	public List<Object[]> getCadreInfoLocalBodyWise(List<Long> localBdyIds,Date fromDate, Date toDate,Long year);
	
	public List<String> chechForCardNumber(String cardNo);
	
	public Long getLastHoursWorkingMemberCount(Date presentDate, Date lastHours);
	
	public Integer inActiveTdpCadreByCadreIds(List<Long> tdpCadreIdList);
	
	public List<Long> getVoterDetailsByVoterIds(List<Long> voterIdList);
	
	public List<String> getCadreImageByPreviousEnrolId(String previousEnrollmentNo);
	
	public List<Object[]> getCastGroupWiseCadreCount(Long Id,String type);
	
	public List<Object[]> getCasteGroupTotalCount(Long Id, String type);
	
	public List<Object[]> getAgeTotalCount(Long Id, String type);
	
	public List<Object[]> getGenderTotalCount(Long Id, String type);
	
	public List<Object[]> getBoothWiseCadreInfo(List<Long> boothIds,int startIndex,int maxIndex,String orderBy,String orderType);
	
	public List<Object[]> getPanchayatWiseCadreInfo(List<Long> panchayatIds,int startIndex,int maxIndex,String orderBy,String orderType);
	public Integer updateNFCCardNumberByVoterIdForDelink(Long voterId , String nfcCardNo);
	public String checkNFCnumberForVoter(Long voterId);
	
	public Long getBoothWiseCadreInfoCount(List<Long> boothIds);
	
	public List<Object[]> getEnrollmentYearWiseDetails(Long locationId, Date fromDate,Date toDate,Long enrollmentYear);
	
	public Long getPanchayatWiseCadreInfoCount(List<Long> panchayatIds);
	
	public Integer updateDispatchStatus(List<Long> cadreIds);
	public List<Object[]> getTdpCadreDetailsBySearchCriteria(String refNo, String mobileNo);
	public Long checkCardNoExistsOrNot(String cardNo);
	public List<Object[]> getCadreDataByYear(Long constituencyId);
	public List<String> getExistingCadreMemberDetails(String preEnrollmentNo);
	public List<Object[]> getDistrictsByStateWiseAction(Long stateId);
	public List<Object[]> getConstsByStateWiseAction(Long stateId);	
	
	//DAO methods to get the Survey Working Members details based on input datetime
	public List<Object[]> getLastHoursWorkingMembersDetails(Date presentDate, Date lastHours);
	public List<Object[]> getWorkingMembersDetails(Date date);
	public List<Object[]> getCadreDetailsForSelection(CadrePrintInputVO input);
	public List<Object[]> getRecentlyRegisteredCadres(Integer startIndex,Integer maxIndex);
	public List<Object[]> getCadreInfoDetails(Long locationId,String locationType,int startIndex,int maxIndex);
	public List<Object[]> getLocationWiseUsersDetails(List<Long> locationIdsList,Date fromDate, Date toDate,String queryString);
	public List<Object[]> getCadreDetailsForSelectionByFamilyVoterId(CadrePrintInputVO input);
	public Long getCadreInfoDetailsCount(Long locationId,String locationType);
	public List<Object[]> getCadreInfoDistrictConstiWise(List<Long> districtIds,Date fromDate, Date toDate,Long year,List<Long> constiIds);
	
	public List<TdpCadre> checkOnlineAccountExistsOrNot(String orderId);
	public List<Object[]> getCandidateDataCollected(Date fromDate,Date toDate, List<Long> userIds);
	public List<Object[]> getCandidateDataCollectedByDate(Date fromDate,Date toDate, List<Long> userIds);
	public List<Object[]> getUserBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getTotalRecordsDayWise(List<String> sourceTypes);
	public List<Object[]> getRegisterCadreInfoForUserBetweenDates(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds);
	public List<Object[]> getNewlyRegisterCadreInfo1(List<Long> constiIds,List<Long> districtIds);
	public Long getWorkStartedConstituencyCount1(String state,List<Long> constituencyIds);
	public Long getWorkStartedConstituencyYearCount1(Long year,String state,Date fromDate, Date toDate,List<Long> constituencyIds);
	public List<Object[]> getRecentlyRegisteredCadresByConstituencies(Integer startIndex,Integer maxIndex,List<Long> constituencyIds);
	
	public Long getWorkingMembersCountOfAccessLevel(Date date,List<Long> constiIds);
	public Long getLastHoursWorkingMemberCountOfAccessLevel(Date presentDate, Date lastHours,List<Long> constiIds);
	
	public Integer saveRuralConstituencyDataType1(String prevDate,String table ,List<Long> constiIds ,Long limit);
	public Integer saveRuralUrbanConstituencyDataType2(String prevDate,String table ,List<Long> constiIds ,Long limit );
	public Integer saveRuralUrbanConstituencyDataType(String prevDate,String table ,List<Long> constiIds ,Long limit);
	public Integer saveUrbanConstituencyDataType1(String prevDate,String table ,List<Long> constiIds ,Long limit);
	
	
	public List<Long> getCadreSurveyUsersStartedByLocation(List<Long> assignedUsersList,Date date);	
	public List<Object[]> getRegisterCadreInfoForUserBetweenDates1(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds);
	public List<Object[]> getAnalysisData(String reqDate);
	public List<Object[]> getUserData();
	public List<Object[]> getCandidateDataCollectionInfo1(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType);
	public Long getLastHoursWorkingMemberCountOfAccessLevelForWeb(Date presentDate, Date lastHours,List<Long> constiIds);
	
	public Long getLastHoursWorkingMemberCountForWeb(Date presentDate, Date lastHours);
	
	public Long getWorkingMembersCountOfAccessLevelForWeb(Date date,List<Long> constiIds);
	
	public Long getWorkingMembersForWebCount(Date date);	
	public List<Object[]> getUserBetweenDatesForWeb(Date fromDate,Date toDate);
	public List<Object[]> getCandidateDataCollectedWeb(Date fromDate,Date toDate, List<Long> userIds);
	public List<Object[]> getCandidateDataCollectedWebParty(Date fromDate,Date toDate, Long userIds);
	public List<Object[]> getCandidateDataCollectedByDateWeb(Date fromDate,Date toDate, List<Long> userIds);
	public List<Object[]> getCandidateDataCollectedByDateWebParty(Date fromDate,Date toDate, Long userId);
	public List<Object[]> getWebUserConstituecny(List<Long> webUserIds);
	
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type,Date fromDate,Date toDate);
	
	public List<Object[]> getCandidateDataCollectionInfoForOnline(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,String queryStr);
	
	public List<String> getCardNumbers(String query,Long constiId,String mobileNo,String trNo,Date surveyDate);
	public List<Object[]> getCadreDetailsByMemberShipId(List<String> memberCardNos);
	public List<Long> lastHoursActiveUsers(Date presentTime,Date lastHoursTime,List<Long> consituencyIdsList);
	public List<Long> inActiveUsersCountInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList,List<Long> consituencyIdsList);
	public List<Object[]> getDaywiseWebuserDetailsByUserANDType(Long userId, Date fromDate,Date toDate,String type);
	public List<Object[]> inActiveUsersInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList,List<Long> constituencyIds);
	
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type,Date date);
	public List<Object[]> getTotalRecordsUnderDate(List<Long> districtIds,String type,Date date);
	public List<Object[]> getTotalRecords1(List<Long> ids,String type);
	public List<Object[]> getLocationWiseGenderCadreCount(List<Long> Ids,String type);
	public List<Object[]> getLocationWiseAgeRangeCount(List<Long> Ids,String ageRange ,String type);
	public List<Object[]> getLocationWiseTotalRecords(List<Long> districtIds,String type);
	public Long getCadreDetailsForCadreRegistratiobByconstituencIdCount(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre);
	public void flushAndclearSession();
	public List<Object[]> getLocationWiseCount(List<Long> ids,String type);
	public List<Object[]> getTdpCadreAgeRangeByConstituency(List<Long> constituencyIds);
	public List<Object[]> getTdpCadregenderWiseByConstituency(List<Long> constituencyIds);
	public List<Object[]> getDuplicateUsersInConstituencies(Date startDate,Date endDate,String type);
	public List<Object[]> getDuplicateUsersCountInConstituencies(Date startDate,Date endDate,String type,List<Long> Ids);
	
	public List<Object[]> getTotalFemaleRecords(List<Long> districtIds,String type,Date fromDate,Date toDate);
	public List<Object[]> getTotalYouthRecords(List<Long> districtIds,String type,Date fromDate,Date toDate);
	public Integer insertPrintCompanyType(Long id,String table);
	public List<Object[]> getCadreCountInMinorities(Long Id,String type);
	public List<Object[]> getCasteGroupWiseCadreCountExcludingMinorities(Long Id,String type);

	public List<Object[]> gettingRegisteredVotersForConstituencys(List<Long> constituencyIds);

	public List<Object[]> getRegisteredCadreCountIn2012(List<Long> constituencyIds);

	public List<Object[]> getCastGroupWiseCadreCountExcludeminority(List<Long> Ids,String type);
	public List<Object[]> getCastGroupWiseCadreCountMinority(List<Long> Ids,String type);
	public List<Object[]> getTotalRecordsByIds(List<Long> Ids,String type,Date fromDate,Date toDate);
	
	public List<Object[]> getTotalRecordsForALocation(List<Long> districtIds,String type,Date fromDate,Date toDate);

	public List<Object[]> getDuplicateUsersByUserId(Date startDate,Date endDate,Long userId,Long locationId,Long constituencyId,String type);
	
	public List<Object[]> getRegisteredCountByHourWise(Date fromDate, Date toDate);
	
	public Long getTotalRegisteredCadreCountByLocation(List<Long> locationIds, String queryStr);
	public List<Object[]> getRegistrationStartedLocations(List<Long> Ids);
	public List<Object[]> getBelowCadresBooths(List<Long> Ids);
	public List<Object[]> getLocationWiseGenderCadreCount1(List<Long> Ids,String type);
	
	public List<Object[]> getMissingDetails(Set<Long> voterIds);
	
	public Long checkForExists(String uniqueKey);
	
	public List<String> getCardNumbersForNonVoters(String query,Long constiId,String mobileNo,String trNo,Date surveyDate);
	
	public List<Object[]> getCadreDetailsByMemberShipIdForNonVoters(List<String> memberCardNos);
	public List<Object[]> getBoothWiseGenderCadres(List<Long> Ids,Long constituencyId);
		
	public Integer updateDetails(List<String> uniqueKey);
	public Long checkForFamilyExists(String uniqueKey);
	
	public List<Object[]> getTotalRecordsByAccessType(List<Long> districtIds,String type,Date fromDate,Date toDate);
	public List<Object[]> getTotalRecordsByAccessTypeUnderDate(List<Long> districtIds,String type,Date date);
	public List<Object[]> getTotalRecordsByAccessTypeByDate(List<Long> districtIds,String type,Date date);
	
	public List<Object[]> getCasteGroupTotalCountByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getGenderTotalCountByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getGenderWiseCadreCountByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getAgeTotalCountByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getAgeRangeCadreCountByAccessType(Long districtId, String ageRange,List<Long> constituencyIds);
	public List<Object[]> getCasteGroupWiseCadreCountExcludingMinoritiesByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getCadreCountInMinoritiesByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getCastWiseCadreCountByAccessType(Long districtId, List<Long> constituencyId);
	public List<Object[]> getTotalRecordsBoothWise(Long constituencyId,Date fromDate,Date toDate);
	public Long getRegisteredVotersForConstituencys(List<Long> constituencyIds);
	
	public List<Object[]> getTotalRecordsByBoothWise(Long constituencyId);
	
	public List<Object[]> gettingRegisteredVotersForDistricts(List<Long> districtsIdsList);
	
	public List<Object[]> gettingRegisteredVotersForParliaments(List<Long> ParliamentsList);
	
	 public List<Object[]> getTotalRegisterCadreInfo();
	
	 public List<Object[]> getTeluguVoterNames(List<Long> tdpCadreId);
	 public List<Object[]> getCadreDetails(String queryStr);
	 
	 public List<Object[]> getTdpCadreDetailsBySearchCriteriaForCallCenter(Long constituencyId,String queryString);
	 
	 public List<String> getCardNumbersForOnlineCadre(String query,Long constiId,String mobileNo,String trNo,Date surveyDate);
	 public List<String> getCardNumbersForNonVotersForOnlineCadre(String query,Long constiId,String mobileNo,String trNo,Date surveyDate);
	public Integer updateFamilyDetailsWithHistory(List<String> familyVoterIds,List<Long> usersIds);
	public Integer updateDetailsToDuplicate(List<String> uniqueKeys);
	public List<Object[]> getFamilyDetails(List<String> uniqueKeys);
	public List<String> getExistingRecordsInfo(List<String> uniqueKeys,List<Long> userIds);
	
	public List<Object[]> getRegisterCadreInfoForVolunteerUserBetweenDates(Date fromDate,Date toDate,List<Long> tabUserIds,List<Long> webUserIds);
	public Long getTotalRegisterCadreInfoByState(String state,List<Long> accessLocationIds);
	public List<Object[]> getLocationWiseCadreRegisterInfo(Set<Long> locationIds,String locationType,Long constituencyId);
	public Long checkMemberExists(String memberShipNo);
	public String getMobileNoByMemberShipNo(String memberShipNo);
	public List<Object[]> getMemberDataByMembershipNo(String memberShipNo);
	public List<Object[]> getMemberAddressByMembershipNo(String memberShipNo);
	
	public String getMembershipNoByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTdpCadreDetailsByVoterCardNoForCallCenter(String queryString);
	public List<Object[]> searchTdpCadreDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString);
	public List<Object[]> getexistringCadreInfoByLocationForCommittee(String candidateName, Long constid, Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo,Long areaId);
	public List<TdpCadre> getTdpCadreDetails(String uuid);
	public List<Object[]> getReqDetailsForIMageChecking(Long districtId,Long constituencyId);
	
	public List<Long> getSingleMemberMobileNosCount(List<String> mobileNos,Long stateId);
	public List<Long> getMultipleMemberMobileNosCount(List<String> mobileNos,Long stateId);
	public Long getMissedCallsCountByState(Date startDate, Date endDate,Long stateId);
	public Long getMatchedMobileNosByState(List<String> mobileNos);
	public List<String> getMissedCallMobileNosByState(Date startDate, Date endDate,Long stateId);
	public List<Object[]> getMissedCallsCountByDistrict(List<String> mobileNos,Long stateId);
	public List<Object[]> getMissedCallsSingleMemberCountByDistrict(List<String> mobileNos,Long stateId);
	
	public List<Object[]> constituencyWiseRegCountForDistrict(Long districtId);
	public List<Object[]> constituencyWiseRecivingMissedCallsCount(Long districtId,Date startDate,Date endDate);
	public List<Object[]> multiMemberRegisteredForMobile(Long districtId,Date startDate,Date endDate);
	
	public List<Object[]> getMemberMobileNumbersCount(Date startDate, Date endDate,Long stateId);
	public List<Object[]> getDistrictWiseMemberMobileNumbersCount(Date startDate, Date endDate,Long stateId);
	
	public List<Object[]> districtWiseRegCountForDistrict(Long stateId);
	
	public List<Object[]> getConstituencyWiseMemberMobileNumbersCount(Long districtId,Date startDate,Date endDate);
	public Long getTdpCadreCountInALocation(List<Long> locationValue,String type,Long year);
	public List<Object[]> getTdpCadreVoterIDs(List<Long> locationValue,String type,Integer startIndex,
			Integer maxRecords,String columnName ,String order,Long year);
	
	public Long getVoterIdExistCadreInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public Long getFamilyVoterIdExistCadreInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<String> getVoterIdExistCadreFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<String> getFamilyVoterIdExistCadreFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getCadreAvailableFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo,List<String> houseNosList);
	public List<Object[]> getVoterAndCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getCadreBasicDetailsByVoterId(Long constituencyId,Long publicationDateId,String partNo,Long voterId);
	public List<Object[]> getFamilyCadreBasicDetailsByVoterId(Long constituencyId,Long publicationDateId,String partNo,Long voterId);
	public List<Object[]> getCadreBasicDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getFamilyCadreBasicDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getFamilyCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);
	public List<Object[]> getVoterHouseWiseDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo);

	public List<Object[]> getLocationWiseCadrePrintedCount(Set<Long> locationIds,String locationType,Long constituencyId);
	public List<Object[]> getTdpCadreCountInALocationForEnrollment(List<Long> locationValue,String type);
}
