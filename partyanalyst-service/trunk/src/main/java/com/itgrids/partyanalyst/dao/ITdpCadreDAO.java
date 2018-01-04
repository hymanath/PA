package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.RtcUnionInputVO;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.Voter;

public interface ITdpCadreDAO extends GenericDao<TdpCadre, Long>{

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise();
	
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex);
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencIdRTC(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex,Long memberTypeId);
	
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
	
	public List<TdpCadre> getVoterByVoterId(Long voterId,Long memberTypeId);
	
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
	//public List<Object[]> getDaywiseWebuserDetailsByUserANDType(Long userId, Date fromDate,Date toDate,String type);
	public List<Object[]> inActiveUsersInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList,List<Long> constituencyIds);
	
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type,Date date);
	public List<Object[]> getTotalRecordsUnderDate(List<Long> districtIds,String type,Date date);
	public List<Object[]> getTotalRecords1(List<Long> ids,String type);
	public List<Object[]> getLocationWiseGenderCadreCount(List<Long> Ids,String type);
	public List<Object[]> getLocationWiseAgeRangeCount(List<Long> Ids,String ageRange ,String type);
	public List<Object[]> getLocationWiseTotalRecords(List<Long> districtIds,String type);
	public Long getCadreDetailsForCadreRegistratiobByconstituencIdCount(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre);
	public Long getCadreDetailsForCadreRegistratiobByconstituencIdCountRTC(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre,Long memberTypeId);
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
	//public List<Object[]> searchTdpCadreDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString);
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
	public Long getTdpCadreCountForLocations(String userAccessType,List<Long> constituencyIds);
	public List<Object[]> getLocationWiseCadrePrintedCount(Set<Long> locationIds,String locationType,Long constituencyId);
	public List<Object[]> getTdpCadreCountInALocationForEnrollment(List<Long> locationValue,String type);
	
	public List<Object[]> getMobileNosByMemberShipId(String queryStr);
	public List<Object[]> getMemberDetlsByMembershipNo(String queryStr);
	public List<Object[]> getMemberAddressDetlsByMembershipNo(String queryStr);
	public List<String> checkForMemberExists(String queryStr);
	
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId,String nameStr);
	public List<Object[]> tdpCadreCasteCountDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString);
	  public List<Object[]> searchTdpCadreDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString,int startIndex,int maxIndex,List<Long> constituencyIds,boolean isRemoved,Long enrollmentId,String searchType);
	  public List<Object[]> getMobileNoByTdpCadreIdList(List<Long> tdpCadreIdsList,int firstRecord,int maxResult);
	  public Long getRegisterCadreInfoForState(Long stateId,Long enrollmentYear);
	  public Long getRegisterConstituenciesForState(Long stateId,Long enrollmentYear,List<Long> ids,String type);
	  public List<Object[]> getTotalRecordsByState(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId);
	  public List<Object[]> getTotalRecordsByAccessTypeByState(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId);
	  public Long getRegisterCadreInfoForUserBetweenDatesByIds(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds);
	  public Long checkVoterRegisteredOrNot(Long voterId,Long enrollmentYear);
	  public List<Object[]> getMemberInfoyMembershipNo(String queryStr);
	 // public Long getTdpCadreIdByMembership(String membershipNo);
	 
	  public List<String> getCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId);
	  public List<String> getNonVoterCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId);
	  public List<Object[]> getCadrePartialDetailsByMemberShip(List<String> memberCardNos);
	  public List<Object[]> getCadrePartialDetailsByMemberShipIdForNonVoters(List<String> memberCardNos);
	  public List<Object[]> getMembershipNosByTdpCadreIds(List<Long> tdpCadreIdsList);
	  public List<Object[]> checkCardNumberExists(Long tdpCadreId);
	  public Long getIsAlreadyTempararyRegistered(Long mobile, String cadreName );
	  public List getTdpCadreIdByMembership(String queryStr);
	  public List<String> getOtherSttateCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId);
	  public List<String> getOtherSttateNonVoterCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId);
	  public List<Object[]> getOtherStateCadrePartialDetailsByMemberShipIdForNonVoters(List<String> memberCardNos);
	  public List<Object[]> getOtherStateCadrePartialDetailsByMemberShip(List<String> memberCardNos);
	  public List<Object[]> getOtherStateCadreDetailsByMemberShipId(List<String> memberCardNos);
	  public List<Object[]> getOtherStateCadreDetailsByMemberShipIdForNonVoters(List<String> memberCardNos);
	  public List getNewlyRegistredCadreCnt(Date fromDate,Date toDate,List<Long> enrollmentYearIds);
	  public Object[] cadreFormalDetailedInformation(Long cadreId,Long enrollmentYear,Long memberTypeId);
	  public List<Object[]> complaintDetailsOfCadre(String memberShipId);

	  public Long getMemberShipRegistrationsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId);
	  
	  public String getMemberShipIdByCadreId(Long tdpCadreId);
	  
	  public List<Object[]> getPartyApprovedFundByMembershipId(String membershipId);
	  
	  public List<Object[]> getGovtApprovedFundByMembershipId(String membershipId);
	  
	  public List<Object[]> getRequestedAmountByMembershipId(String membershipId);
	  
	  public List<Object[]> getSurveyPaticipatedCountByVoterIdcardNoList(List<String> voterIdCardNoList);
	  
	  public List<Long> getCadreIdByMembershipId(String memberShipNo,Long constituencyId);
	  
	  public List<Object[]> getCategorywiseStatusCount(String memberShipNo);
	  
	  public List<Long> getTdpCadreIdByMembershipId(String membershipId);
	  
	  public List<Object[]> cadresInformationOfCandidate(List<Long> cadreIds,Long enrollmentYear);
	  
	  public List<Object[]> getEducationalRequestedAmountByMembershipId(String membershipId);
	  
	  public List<Object[]> checkVoterCardNosCadreOrNot(String voterCardNoStr);
	  public List<Object[]> checkVoterCardNosCadreNosOrNot(List<String> voterCardNoList);
	  public List<Long> getAllRemovedCadre();
	  public List<Object[]> getAllCadreDetailsByCadreIds(List<Long> cadreIds);
	  public List<Object[]> getCadreDetailsByMembershipNoOrVoterId(String memberShipNo,String voterId);
	  public Long getTdpCadreIdByVoterId(Long voterId);
	  public Long getTdpCadreCountsForDivisions(List<Long> divisionIds);
	  public List<Object[]> getDivisionWiseCadresCount(List<Long> divisionIds);
	  public List<Object[]> getTdpCadreBoothsForDivision(Long wardId);
	  public Long getUserAddressId(Long tdpCadreId);
	  public List<Object[]> getAffliatedCadreCountDetails(String type,Date today);
	  public List<Object[]> getRtcUnionZoneWiseDetails(String searchType,Date date);
	  public List<Object[]> getRtcUnionAllLocationDetails(String searchType,Date date);
	  public List checkUnionMemberExists(String voterCardNo,Long memberTypeId);
	  public List<Object[]> getRtcUnionDeptDetails(String searchType,Date date);
	  public List<Object[]> getAffiliatedCadreDetails(String type,String searchType,Long locationId);
	  public List<Long> getVoterDetailsByVoterIdsAndRTCAffliatedCadre(List<Long> voterIdList,Long memberTypeId);
	  public Long getTodayTabAndWebUsersCount(String type);
	  public List<TdpCadre> getAffliatedCadreByVoterId(Long voterId,Long tdpMemberTypeId);
	  public List<TdpCadre> getAffliatedCadreByVoterIdAndMemberType(Long voterId,Long memberTypeId);
	  public List<TdpCadre> checkAffliatedCadreOnlineAccountExistsOrNot(String orderId);
	  public List<Long> checkVoterAsAffliatedCadre(Long voterId,Long memberTypeId);
	  public List<TdpCadre> getNormalCadreDetailsByVoterId(Long voterId);
	  
	  public List<TdpCadre> getAffliatedCadreByFamilyVoterId(Long voterId, String refNo);
	  public List<Object[]> getLocationwiseCadreRegistraionDetails(List<Long> membereTypeIdsList,String searchTypeStr,Date fromDate,Date toDate);
	  public List<Object[]> getCadreFormalDetails(List<Long> tdpCadreIds);
	  public List<Object[]> getCadreCountsByTdpMemberType(Date fromDate,Date toDate);
	  public List<Object[]> getAllCountsForUnionMembersRegistered(Date fromDate,Date toDate);
	  public List<Long> getCadreDetailsByTdpMemberType(Date fromDate,Date toDate,RtcUnionInputVO inputVO);
	  public List<Object[]> getCadreFormalDetailsByYear(List<Long> tdpCadreIds,Long enrollmentYear);
	  public Long getUserPresentAddressId(Long tdpCadreId);
	  public Long getUserPermenentAddressId(Long tdpCadreId);
	  public Long getUserWorkAddressId(Long tdpCadreId);
	  public List<Object[]> getDaywiseWebuserDetailsByUserANDType(Long userId, Date fromDate,Date toDate,String type,Long memberTypeId);
	  public List<Object[]>  getmemberShipIdsByVoterIds(Long cadreEnrollmentYear,List<Long> voterIds);
	  public List<Object[]> getTdpCadreIdForMemberShipNums(List<String> membershipNums);
	  public List<Object[]>  searchMemberByCriteria(String searchType,String searchValue,LocationInputVO locationVo);
	  public List<UserAddress> getUserAddress(Long tdpCadreId);
	//  public List<Object[]>  advancedSearchMemberForPublicRepresentative(String searchType,Long searchValue);
	  public List<Object[]> advancedSearchMemberForCadreCommittee(String searchType,LocationInputVO locationVo,String locationType,LocationInputVO inputVo);
	  public List<Object[]>  advancedSearchMemberForPublicRepresentative(String searchType,LocationInputVO locationVo,LocationInputVO inputVo);
	  public List<Object[]> checkVoterCardNumberRegistration(String voterIDCardNo);
	  public List<Object[]> checkAlreayRegistrationByMemberShipNo(List<Long> tdpCadreIdsList);
	  
	  public List<Object[]> getConstituencyForCadreIds(List<Long> tdpcadreIds);
	  public List<Object[]> getAddressForCadreIds(List<Long> tdpcadreIds);
	  public List<Object[]> checkMemberPaymentExistsByTypeId(String memberShipNo,Long tdpMemberTypeId,Long enrollmentYear);
	  public List<Object[]> getPRConstituenciesByCadreIds(List<Long> cadreIds);
	  public List<UserAddress> getUserAddressForPR(List<Long> cadreIds);
	  public List<Long> getCadreDetailsByTdpMemberTypeSourceWise(Date stDate,Date edDate,RtcUnionInputVO inputVO);
	  public List<Object[]> getCadreDetailsByYearSourceWise(List<Long> tdpCadreList,Long year);
	  public List<Object[]> getRegisteredMemberDetails(Long tdpcadreId);
	  public List<Object[]> getCandidatesConstituency(List<Long> tdpCadreIds);
	  public Object[] getCadreDetailsByMmbrShpId(String memberShipNo,Long enrollmentId);
	  public List<Long> getCadreIdByMemberShip(String memberShipNo,Long enrollmentId);
	  public List<Long> getCadreIdsByMemberShip(Long enrollmentId,String searchType,String searchValue);
	  public List<Object[]> getMobileNumberDetailsByTdpCadre(Long tdpCadreId);
	  public List<Object[]> getTotalCadreCountAgeRangeIdWise(Set<Long> ageWiseIds,List<Long> enrollmentYrIds);
	  public List<Object[]> genderWiseTdpCadre(List<Long> enrollmentYrIds);
	  public List<Object[]> getUserAddressForCadre(List<Long> tdpCadreIds);
	  public List<Object[]> getTotalCadreCountByCasteCategoryexcludingMinorities(Set<Long> casteCategoryWiseIds,List<Long> enrollmentYrIds);
	  public Long getTotalCadreCountByForMinorities(List<Long> enrollmentYrIds);
	  public List<Object[]> getCadrAddressDetailsByCadred(Long tdpCadreId);
	  public Long getPublicationDateId(Long tdpCadreId);
	  public Long getVoterIdByTdpCadreId(Long tdpCadreId);
	  public List<Object[]> getApplicationMemberDetails(Long tdpCadreId,String searchType);
	
	  public List<Object[]> getCadreBasicLocationDetails(Long tdpCadreId);
	  public List<Object[]> getCadreDataForSqlite(Long constituencyId);
	  public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO);
	  public List<Object[]> getLocationsUserTrackingDetails(GISVisualizationParameterVO inputVO,String countTimeTypeStr);	  public List<Object[]> getRegisteredDetailsByCadreId(Long tdpCadreId,Long voterId,Long familyVoterId,String status);
	  public List<Object[]> getTdpCadreDetailsBySearch(List<Long> constituencyIds , String searchType,String memberShipNo,String mobileNo,String voterId,List<Long> assignedBoothIds);
	  public List<Object[]> getTotalCadreCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,Long enrlollmentYearId);
	  public List<Object[]> getTotalCadreCountSourceWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);

	  public List<Object[]> getTotalCadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,Long enrollmentYearId,Long userType);
	  public List<Object[]> getTotalCadreCountLocationWiseBasedOnYear(String locationType,Long stateId,Date fromDate,Date toDate,Long enrollmentYearId,Long userAccessLevelId,List<Long> userAccessLevelValues);
	  public Long getTotalTabUserWorkingInField(Long accessLvlId,Set<Long> locationValueId, Long stateId, Date lastOneHourTime, Date today,String status);
	  public List<Object[]> getLocationIdAndName(Long accessLvlId,List<Long> userAccessLevelValues,Long stateId);
	  public Long getTotalConstituencyForCdrRegStarted(Long stateId);
	  public List<Object[]> getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, Date startDate, Date endDate);
	  public List<Object[]> getTotalRegCdrVendorAndTabUserWise(Long stateId, Long vendorId, Long distId, Long constId, Date startDate, Date endDate, String status);
	  public List<Object[]> getActiveUserList(Long stateId,Long vendorId,Long distId,Long constId,Date startDate,Date endDate, Date lastOneHourTime);
	  public List<Object[]> checkVoterCardNosListExists(Set<String> voterCardNosList);
	  public List<Object[]> getVoterCardDtlsList(Long surveyUserId, Long tabUserId, Long webUserId, String startDate, String endDate, String status,Integer minValue,Integer maxValue,String verificationStatus,String dataSourceType,Long stateId);
	  public Integer updateApprovedCadre(Long cadreId, Long statusId);
	  public List<Object[]> getLatestLattitudeLangitudeOfTabUser(Long constituencyId,Date startDate,Date endDate);
	  public TdpCadre getTdpCadreDetailsByOtp(Long tdpCadreId);
	  
	  public List<Object[]> getTdpCadreRecordsCountLocWise(Date date);
	  public List<Object[]> getRenewalTdpCadreRecordsCountLocWise(Date date);
	 
	  public List<Object[]> getTdpCadreDataByDateAndConstituency(Date fromDate);
	  public List<Object[]> getRenewalTdpCadreDataByDateAndConstituency(Date fromDate);
	  public Voter getTdpCadreVoterByvoterId(Long voterId);
	  public Long getCadreWithOwnVoter(Long accessLvlId, List<Long> accessLvlValue);
	  public Long getCadreWithFamilyVoter(Long accessLvlId, List<Long> accessLvlValue);
	  
	  public List<Object[]> levelWiseTdpCareDataByTodayOrTotal(Date date,String levelType);
	  public List<Object[]> levelWiseRenewalTdpCareDataByTodayOrTotal(Date date,String levelType);
	  public List<Object[]> getTdpMembersDetaislBasedOnSearchCriteria(Long locationId,String searchType,String searchValue);
	  public List<Object[]> getActualCountOfCadreSurveyUser(Set<Long> cadreSurveyUsers);
	  public String getCadreImagePathByTdpCadreId(Long tdpCadreId);
	  
	  public List<Object[]> getTotalRegCdrVendorWiseNew(Long cadreRegUserId, Long userId, Long constId, Date startDate, Date endDate);
	  public List<Object[]> getTotalRegCdrVendorAndTabUserWiseNew(Long cadreRegUserId, Long userId, Long constId, Date startDate, Date endDate, String status);
	  public List<Object[]> getActiveUserListNew(Long cadreRegUserId, Long userId, Long constId,Date startDate,Date endDate, Date lastOneHourTime);
	  public List<Object[]> getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO,String type);
	  public Date getLastUpdatedTime(Date today);
	  public List<Object[]> getTabUserInfoDetailsByTdpCadreIds(List<Long> tdpCadreIdsList );
	  public String getCadreMobileNumber(Long tdpCadreId);
	  public List<Object[]> getUserTrackingDetails(Long cadreSurveyUserId,Date fromDate,Date toDate);
	  public List<Object[]> getActualCountOfCadreSurveyUserWiseCount(Set<Long> cadreSurveyUsers,Date fromDate,Date toDate);
	  
	 
	  public List<Object[]> levelWiseRenewalTdpCareDataByAgeRange(String levelType);
	  public List<Object[]> levelWiseTdpCadreDataByAgeRange(String levelType,Long enrollmentYearId);
	  
	  public List<Object[]> levelWiseTdpCadreDataByGender(String levelType,Long enrollmentYearId);
	  public List<Object[]> levelWiseRenewalTdpCareDataByGender(String levelType);
	  
	  public List<Object[]> levelWiseTdpCadreDataByCasteState(String levelType,Long enrollmentYearId);
	  public List<Object[]> levelWiseRenewalTdpCareDataByCasteState(String levelType);
	  public List<Object[]> getCadreImagesByCadreId(Long tdpCadreId);
	  public Integer updateTdpCadreImage(Long tdpCadreId,String image);
	  
	  public List<Object[]> getDistrictWiseCardPrintStatusCounts(Long stateId);
	  public List<Object[]> getConstituencyWiseCardPrintStatusCounts(Long stateId);
	  
	  public List<Object[]> getDistrictWiseCadreCounts(Long stateId);
	  public List<Object[]> getConstituencyWiseCadreCounts(Long stateId);
	  
	  public List<Object[]> getConstNotVerfiedCardPrintStatusCadre(Long constituencyId);
	  public Integer updateCardPrintStatusByTdpCadreIds(List<Long> tdpCadreIdList , Long cardPrintStatusId);
	  public List<Object[]> getConstituencyCadreCardPrintStatusCounts(Long constituencyId);
	  public Long getConstituencyCadreCount(Long constituencyId);
	  public Long getConstituencyCardPrintVerifiedCount(Long constituencyId);
	  public List<String> getMemberShipNumberByVoterNumberOrMobileNo(String voterCardNo,String mobileNo);
	  public List<Object[]> getCadreDetailsByMembershipNo(String memberShipNo,String voterId);
	  public List<Object[]> updateSearchTdpCadreDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString,int startIndex,int maxIndex,List<Long> constituencyIds,boolean isRemoved,Long enrollmentId);
	  public List<Object[]> getCadreNameByTdpCadreIds(List<Long> tdpCadreIds);
	  public List<Object[]> searchTdpCadreDetailsBySearchCriteriaForWebService(Long constituencyId,Long casteStateId,String queryString,int startIndex,int maxIndex,List<Long> constituencyIds,boolean isRemoved,Long enrollmentId);
	  public List<Object[]> getCadreCasteDetailsByTdpCadreIds(List<Long> tdpCadreIds);
	  public List<Object[]> getCadreLocationIdsByTdpCadreIds(List<Long> tdpCadreIds);
	  public List<Object[]> getCandidateDetailsByMembershipId(String membershipId);
	  
	 
	  public List<Object[]> getTdpCadreDetailsByMemberShipId(List<String> membershipNoList);
	  public List<Object[]> getTdpCadreDatailsForMeetings(List<String> memberShipIds);
	  public List<Object[]> getSerialNoInLastestPublicationForCadre(List<Long> tdpCadreIdsList);
	  
	  public List<TdpCadre> isMembershipIdVAlidOrNotValid(String membershipNo);
	  public List<TdpCadre> isMebershipIdValid(String membershipId);
	  public List<Object[]> isVoterDeleted(Set<Long> tdpCadreIds);
	  public List<Object[]> getCadreBasicDetailsByVoterIds(Long constituencyId,Long publicationDateId,Long  boothId,String type);
	  public List<Object[]> getRangeWiseTdpCadreDtlsObjs(Set<Long> voterIds,String searchType);
	  public List<Object[]> getMemberDetailsByMembershipId(String membershipId);
	  public Long filteredTdpCardreIdsCount(List<Long> list,Long userAccessLevelId,List<Long> userAccessLevelValues);
	  public List<Object[]> getMobileNoOfMembership(String membershipId);
} 
