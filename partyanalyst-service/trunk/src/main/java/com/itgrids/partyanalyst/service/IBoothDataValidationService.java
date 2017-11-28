package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;

import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothDataValidationService {

	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(
			File filePath, String electionYear, Long stateId,
			Long electionTypeId ,  String publicationDate) throws CsvException;
	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothCount(InputVO inputVO);
	public List<BoothInchargeDetailsVO> getLocationBasedOnSelection(InputVO inputVO);
	public List<BoothAddressVO> getLocationLevelWiseBoothDetails(InputVO inputVO);
	public List<IdAndNameVO> getBoothInchargeRoles(Long boothId,List<Long> enrollmentYrId);
	public BoothInchargeDetailsVO validateBoothToMakeConfirm(Long boothId,Long boothInchargeEnrollmentId);
	public List<BoothInchargeDetailsVO> gettingBoothInchargeRoleDetails(Long boothId,Long boothInchargeEnrollmentId,Long locationValue);
	public String updateRangeIdsOfBoothIncharge(Long boothId,Long boothIncgRoleId,List<Long> enrollmentIds);
	public String deleteRoleMemberDetails(Long boothInchargeMappingId,Long boothInchargeId,Long userId,Long boothId,Long boothInchargeEnrollementId);
	public List<BoothInchargeDetailsVO> getBoothInchagesMappingRoles();
	public InputVO getLoginUserDtls(Long userId);
	public List<BoothAddressVO> getLocationWiseCadreDetails(InputVO inputVO);
	public List<BoothInchargeDetailsVO> getCommitteeFinalizedBoothsListforUnlock(Long userId,List<Long> locationIdsList);
	public List<BoothInchargeDetailsVO> getUserAccessLocatiosLIst(Long userId,Long accessLevelId, String accessLevelType);
    public String unlockBoothCommitteesByCommitteeIdsList(Long userId,List<Long> boothCommitteeIdsList);
    
    public List<BoothAddressVO> getBoothInchargeCommitteeDetailsByLocation(Long levelId,Long levelValue);
}
