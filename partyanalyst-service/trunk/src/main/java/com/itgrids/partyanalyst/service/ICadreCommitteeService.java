package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
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
	public CadreCommitteeReportVO getCommitteeDetailsByLocation(String state,List<Long> levelIds);
	
	public String checkIsVacancyForDesignation(Long tdpCommitteeRoleId);
	
	public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList);
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfoRequest(Long levelId,Long levelValue,Long designation);
	public LocationWiseBoothDetailsVO getCommitteeMembersInfoRequest(Long committeeId,Long designation);

	public CadreCommitteeReportVO getTotalCommitteeDetailsByLocation(String state);
	public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId);
	public List<CadreCommitteeMemberVO> getCommitteeMemberDetails(Long basicCommitteeTypeId,Long locationId,Long levelId);
	public List<CadreCommitteeMemberVO> setCommitteConfirmation(Long basicCommitteeTypeId,Long locationId,Long levelId);
	public List<CadreCommitteeMemberVO> updateCadreRole(Long tdpCommitteeMemberId);
	public List<CommitteeApprovalVO> getCommitteesForApproval(Long startNo, Long endNo);
	
}
