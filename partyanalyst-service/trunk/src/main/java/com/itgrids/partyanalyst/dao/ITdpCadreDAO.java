package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
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
	
	public List<Object[]> getCandidateDataCollectionInfo(Date fromDate,Date toDate);

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
	
	public Long checkCardNoExistsOrNot(String cardNo);
	public List<Object[]> getCadreDataByYear(Long constituencyId);
}
