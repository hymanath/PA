package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICadreCommitteeService {
	public String genarateOTP(Long userId, Long mobileNo);
	public String validateOTPForUser(Long userId, Long mobileNo,Long refNo, Long otpNumber);
	public List<String> getAgeRangeDetailsForCadre();
	public List<GenericVO> getAllCasteDetailsForState();
	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId);
	public List<LocationWiseBoothDetailsVO> getLocationsList(Long constituencyId,String level);
	public  List<LocationWiseBoothDetailsVO> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue);
	public LocationWiseBoothDetailsVO getCommitteeMembersInfo(Long committeeId);
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfo(Long levelId,Long levelValue);
	public List<LocationWiseBoothDetailsVO> getAllTdpCommitteeDesignations();
	public ResultStatus saveCadreCommitteDetails(Long userId,Long tdpCadreId,Long tdpCommitteeRoleId);
	public List<CadrePreviousRollesVO> getCadreEligiableRoles(Long tdpCadreId);
	public List<GenericVO> getCadsteDetailsByGroupId(Long casteGroupId);
	public List<GenericVO> getPanchayatDetailsByMandalId(Long MandalId);
	public List<Long> getBoothsInPanchayatId(Long panchayatId);
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails1(Long constituencyId);
	public CadreCommitteeVO searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategoryId,Long fromAge,Long toAge,String houseNo,String gender);
	public List<SelectOptionVO> getBasicCadreCommitteesDetails();
	public List<IdNameVO> getLocationsOfCommitteeLevel(Long levelId,Long constiId);
	public List<IdNameVO> getConstituenciesOfState(Long levelId);
	public ResultStatus saveMandalLevelAffliactedElectrolInfo(Long tdpCadreId,Long tdpBasicCommitteeId);
	public ResultStatus saveMandalLevelElectrolInfo(Long tdpCadreId,List<CadrePreviousRollesVO> eligibleRoles);
	public CadreCommitteeReportVO getCommitteeDetailsByLocation(String state,List<Long> levelIds,String startDateStr,String endDateStr,Long userId,String locationType,Long locationValue);
	
	public String checkIsVacancyForDesignation(Long tdpCommitteeRoleId);
	
	//public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList);
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfoRequest(Long levelId,Long levelValue);
	public LocationWiseBoothDetailsVO getCommitteeMembersInfoRequest(Long committeeId);

	public CadreCommitteeReportVO getTotalCommitteeDetailsByLocation(String state,Long userId,String locationType,Long locationValue);
	
	public List<CadreCommitteeMemberVO> getCommitteeMemberDetails(Long basicCommitteeTypeId,Long locationId,Long levelId,String status);
	public List<CadreCommitteeMemberVO> setCommitteConfirmation(Long basicCommitteeTypeId,Long locationId,Long levelId);
	public List<CadreCommitteeMemberVO> deleteCadreRole(Long tdpCommitteeMemberId);
	public List<CommitteeApprovalVO> getCommitteesForApproval(Long startNo, Long endNo,Long requestUserId);
	public String updateCommitteePosCount(final Long roleId, final Long maxCount, final String type, final Long increasedPosId, final Long approveCount);
	public CommitteeApprovalVO getStatusCountsOfApproval();
	
	
	public List<CommitteeSummaryVO> getSummaryDetails(String accessValue);
	public List<LocationWiseBoothDetailsVO> getStartedCommitteesCountInALocation(String accessValue);
	public List<CommitteeSummaryVO> gettingMandalAndMuncipalAndDivisonSummary(String constituencyId);
	public List<LocationWiseBoothDetailsVO> getMandalMuncipalDivisonTotalCommittees(String accessValue,Map<Long,CommitteeSummaryVO> affilatedMap);
	//public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startdateStr,String endDateStr);
	//public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startdateStr,String endDateStr);
	public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId,String accessValue);
	
	public List<CommitteeSummaryVO> getDistrictWiseCommittesSummary(String state,String startDate, String endDate,Long userId,String accessType, Long accessValue);
	public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long committeeId );
	public Long gettingCommitteeIdForMainCommittee(Long levelId,Long levelValue);
	
	//public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate);
	public List<CommitteeApprovalVO> changeDesignationRecordsForAUser(Long userId,Long startNo,Long endNo);
	public List<IdNameVO> getAllDistricts();
	public List<IdNameVO> getAllConstituencysForADistrict(Long districtId);
	public CommitteeSummaryVO getConstituencySummary(Long reprtType, Long constituencyId);
	public LocationWiseBoothDetailsVO getAffiliatedCommitteMembersInfo(List<Long> affiliIds);
	public List<AccessedPageLoginTimeVO> getElctoralInfoForALocation(Long locationValue);

	public List<LocationWiseBoothDetailsVO> getMandalsByConstituency(Long constituencyId );
	public List<LocationWiseBoothDetailsVO> getPanchayatWardByMandalId(String mandalId);
	public List<CadreCommitteeMemberVO> getElectrolsOfPanchayatAndWards(Long locationId,Long locationType,Long basicCommitteeTypeId);
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoByCommiteTypeAndLocation(Long locationId,Long locationType,Long basicCommitteeTypeId,String status);
	public ResultStatus updateCandidateDesignation(final Long committeeId,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long userId);
	public LocationWiseBoothDetailsVO getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal);
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue);
	public ResultStatus  approvingChangeDesignations(final Long cadreCommitteeIncreasedPositionsId,final String approvedStatus);
	public CommitteeApprovalVO statusForChangeDesignationsApproval();
	public String userAccessTypeDetailsForDashBoard(Long userId, String accessType,Long accessValue);
	public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId);
	public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId);
		
}
