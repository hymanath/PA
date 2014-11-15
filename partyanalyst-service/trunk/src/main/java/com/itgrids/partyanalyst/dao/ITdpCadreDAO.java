package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.model.TdpCadre;

public interface ITdpCadreDAO extends GenericDao<TdpCadre, Long>{

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise();
	
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered);
	
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
	
	public Integer saveRuralConstituencyDataType1(String prevDate);
	public Integer saveRuralUrbanConstituencyDataType2(String prevDate);
	public Integer saveRuralUrbanConstituencyDataType(String prevDate);
	public Integer saveUrbanConstituencyDataType1(String prevDate);
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
	
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type);
	
	public List<Object[]> getCandidateDataCollectionInfoForOnline(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,String queryStr);
	
	public List<String> getCardNumbers(String query,Long constiId,String mobileNo,String trNo,Date surveyDate);
	public List<Object[]> getCadreDetailsByMemberShipId(List<String> memberCardNos);
	public List<Long> lastHoursActiveUsers(Date presentTime,Date lastHoursTime);
	public List<Long> inActiveUsersCountInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList);
	public List<Object[]> inActiveUsersInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList);
}
