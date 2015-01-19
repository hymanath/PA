package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

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
	public void saveElectrolInfo(Long tdpCadreId,Long tdpCommitteeLevelId,Long levelValue,Long tdpCommitteeTypeId,Long committeeId,Long cadreRoleId,List<CadrePreviousRollesVO> eligibleRoles);
	public ResultStatus saveCadreCommitteDetails(Long tdpCadreId,Long tdpCommitteeRoleId);
	public List<CadrePreviousRollesVO> getCadreEligiableRoles(Long tdpCadreId);
	public List<GenericVO> getCadsteDetailsByGroupId(Long casteGroupId);
}
