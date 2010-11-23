package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.HamletsListWithBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupsInfoVO;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IConstituencyManagementService {
	
	public List<VoterVO> getVoterInfo(Long hamletId,String year, Integer startIndex, Integer maxRecords, String order, String columnName);
	
	public VoterCastInfoVO getVotersCastInfoForHamlet(Long hamletId, String year);
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId, String year);
	
	public TotalMPTCMandalLeaderVO getMPTCElectionResultForMandal(Long mandalID);
	
	public HamletsListWithBoothsAndVotersVO getAllHamletBoothInfoForRevenueVillage(Long revenueVillageID, String year, String electionType);
	
	public List<LocalUserGroupsInfoVO> getLocalUserGroupsCandidatesByAccesstypeAndAccessValues(Long userId, String accessType, String accessValue);
	
	public List<UserGroupDetailsVO> findUserGroupsByLocationCategoryAndUserId(Long userId, Long categoryId, String locationType);
	
	//public HamletBoothVotersListVO findAllBoothVotersForHamlet(Long hamletID, String year, String electionType);
}
