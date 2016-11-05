package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardCadreRegistrationService {
	
	public CadreRegistratedCountVO showCadreRegistreredCount(String retrieveType);
	public Object getRegistrationCountDtls(String location,Long constId, String scope);
	public Object getCadreRegistrationCountByConstituency(Long constituencyId,String fromDate,String toDate);
	public Object getDaysByCadreRegistrationCount(Long constituencyId,Long cadreSurveyUserId,Long tabUserInfoId,String fromDate,String toDate);
	public CadreRegistratedCountVO getEnumeratorsInfo(String retrieveType);
	public String getCadreLastUpdatedTime();
	public Object getNoRegistrationReceiveTabUserPersonCountByTimeWise(Long constituencyId,String date);
	public Object getTabUserInfoDetails(String tabUserInfoIds);
	public NewCadreRegistrationVO getRegistrationPersonDetails(Long voterId,Long familyVoterId,Long tdpCadreId,String status);
	public CadreRegistratedCountVO getTotalNewRenewalCadreStateWise(Long activityMemberId, Long stateId,String startDate, String endDate);
	public List<IdAndNameVO> getStatewisesCastNames(Long stateId) ;
	public List<IdAndNameVO> getEducationalQualifications( ) ;
	public List<IdAndNameVO> getAllRelationDetails();
	public List<List<UserTypeVO>> getUserTypeWiseTotalCadreRegistrationCount(Long activityMemberId,Long stateId,Long userTypeId,Long userId,String fromDate,String toDate);
	public CadreResponseVO savingCadreDetails(CadreRegistrationVO cadreRegistrationVO);
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreRegistration(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String fromDateStr,String toDateStr);
	public List<CadreReportVO> getCadreDetailsBasedOnUserType(Long activityMemberId,Long stateId,Long userTypeId,String fromDateStr,String toDateStr);
	public List<CadreReportVO> getLocationWiseCadreDetails(Long stateId,String locationType,String fromDateStr,String toDateStr,Long accessLevelId,List<Long> userAccessLevelValues,String isKuppamExcluded);
	public CadreReportVO getApAndTsDistrictList();
	public List<IdAndNameVO> getOccupationList();
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreReg(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String startDate, String endDate);
	public CadreRegistratedCountVO getStateDtls(Long activityMemberId,Long stateId,String startDate, String endDate);
	public List<CadreRegistratedCountVO> getSourceOfRegistrationDtls(Long activityMemberId,Long stateId,String startDate, String endDate);
	public String generatingAndSavingOTPDetails(String mobileNo);
	public List<CadreRegistratedCountVO> getDtlsOfBellowLvlMember(Long activityMemberId,Long stateId,String startDate, String endDate);
	public CadreRegistratedCountVO getEnumerationDtlsForMem(Long activityMemberId,Long stateId,String startDate, String endDate);
	public String getOtpStatus(Long cadreId,String otp);
	public NewCadreRegistrationVO validateUpdateVoterDetails(String voterCardNo);
	public IdAndNameVO getVoterInfo(Long activityMemberId,Long stateId,String startDate, String endDate); 
	public List<CadreReportVO> getConstituencyWiseReportBasedOnUserType(Long activityMemberId,Long stateId,String startDate, String endDate);
	public CadreRegistratedCountVO getTotalNewRenewalCadreStateWiseTS(Long activityMemberId,Long stateId,String startDate, String endDate);
	public CadreRegistratedCountVO getStateDtlsTS(Long activityMemberId,Long stateId,String startDate, String endDate);
	public IdAndNameVO getInFieldCount(String fromDateStr, String toDateStr);
	public List<CadreReportVO> getLocationWiseCadreInfoTodayDetails(Long stateId,List<Long> locationIdsList);
	public List<CadreReportVO> getMandalMuncipalityStatedAndNotStatedDetails(Long stateId,List<Long> locationIdsList);
}
