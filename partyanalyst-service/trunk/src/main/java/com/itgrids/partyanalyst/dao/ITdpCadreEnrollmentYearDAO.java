package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;

public interface ITdpCadreEnrollmentYearDAO extends GenericDao<TdpCadreEnrollmentYear, Long>{

	public List<TdpCadreEnrollmentYear> getOnlineTdpCadreEnrollmentYearDetailsByTdpCadreId(Long tdpCadreId,String dataSourceType);
	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId);
	public Long getMaxRecordFromEnrollmentYear(Long tdpCadreId);
	public List<Object[]> getTotalRenewlCadreLocationWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt);
	public List<Object[]> getTotalRenewlCadreSourceWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt);
	public List<Object[]> getTotalRenewlCadreBasedOnUserType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userType,Date fromDate,Date toDate);
	public List<Object[]> getTotalRenewlCadreCntLocationWise(Long stateId,String locationType,Date fromDate,Date toDate,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getCadreRegistrationCountByDataSourceType(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getCadreRegistrationCountByCadreVerificationStatus(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getTabCadreRegistrationCountLastOneHoursUserWise(Date date,String dataSourceType,String verificationStatus,Long stateId);
	public List<Object[]> getTabCadreRegistrationCountUserWise(Date fromDate,Date toDate,String dataSourceType,String verificationStatus,Long stateId);
	public List<Object[]> getWebAndOnlineCadreRegistrationCountLastOneHoursUserWise(Date date,String dataSourceType,String verificationStatus,Long stateId);
	public List<Object[]> getWebAndOnlineCadreRegistrationCountUserWise(Date fromDate,Date toDate,String dataSourceType,String verificationStatus,Long stateId);
	public List<Object[]> getInFieldCount(Date today);
	public List<Object[]> getTodayFieldCount(Date today);
	public List<Object[]> getHourWiseUserPerformanceInfo(Long cadreSurveyUserId,Long tabUserId,Date dayBfrYes,Date today);
	public List<Object[]> getDistWiseCountList();
	public List<Object[]> getDistWiseRenewCountList();
	public List<Object[]> getConstWiseCountList();
	public List<Object[]> getConstWiseRenewCountList();
	public List<Object[]> getCadreDetailsBySearch(String searchType,Long searchValue,String locationType,Long locationVal,String gender,Long minAge,Long maxAge);
	public List<Object[]> getCadrAddressDetailsByCadred(Long tdpCadreId,Long yearId);
	public List<Object[]> getEnrolledDetailsByTdpCadreId(List<Long> tdpCadreList);
	public List<Object[]> getEnrolledCandidatesRenewelYearDetails(List<Long> tdpCadreIDsList);
	public List<Object[]> getLatestEnrollmentYearForCadreIds(List<Long> cadreIds);
	
	public List<Object[]> getBoothWiseCadreRegistrationCounts(Long districtId,Long constituencyId);
	public List<Object[]> getBoothWiseRegisteredMemberDetails(Long boothId,Long constituencyId,String status,String verificationStatus);
	public List<Object[]> getOverAllCadreRegistrationCounts(Long districtId,Long constituencyId);
	 public Long getTdpCadreIdByMembership(String membershipNo);
	 public List<Object[]> getTdpCadreDetailsByTdpCadreId(Long tdpCadreId);
	 public List<Object[]> getCasteWiseCadreCounts(List<Long> constituencyIds);
	 public List<Object[]> getCasteNGenderWiseCadreCounts(Long locationTypeId,Long locationValue);
	 public List<Object[]> getCadresCasteNAgeGroupWiseCounts(Long locationTypeId,Long locationValue,Long casteGroupId, Long casteId);
	 public List<Object[]> getEnrollmentYearWiseCadres();
	 public List<Object[]> getEnrollmentYearAgeGroupWiseCadres(Long locationTypeId,Long locationValue,Long enrollmentYearId);
	 public List<Object[]> getAgeGenerAndCasteGroupWiseCadresCount(Long locationTypeId,Long locationValue,Long enrollmentYearId);
	 public List<Object[]> getCasteGroupWiseCadreCounts(Long locationTypeId,Long locationValue,Long enrollmentId);
	 public List<Object[]> getGenderAndAgeGroupWiseCadreCount(Long locationTypeId,Long locationValue);
}		
