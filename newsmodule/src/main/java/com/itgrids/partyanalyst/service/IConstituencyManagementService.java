package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.HamletsListWithBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupsInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IConstituencyManagementService {/*
	
	public List<VoterVO> getVoterInfo(Long hamletId,String year, Integer startIndex, Integer maxRecords, String order, String columnName);
	
	public VoterCastInfoVO getVotersCastInfoForHamlet(Long hamletId, String year);
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId, String year);
	
	public TotalMPTCMandalLeaderVO getMPTCElectionResultForMandal(Long mandalID);
	
	public HamletsListWithBoothsAndVotersVO getAllHamletBoothInfoForRevenueVillage(Long revenueVillageID, String year, String electionType);
	
	public List<LocalUserGroupsInfoVO> getLocalUserGroupsCandidatesByAccesstypeAndAccessValues(Long userId, String accessType, String accessValue);
	
	public List<UserGroupDetailsVO> findUserGroupsByLocationCategoryAndUserId(Long userId, Long categoryId, String locationType);
	
	public Boolean getIsSubscribed(Long userId,Long constituencyId);
	
	public ResultStatus subscriberDetails(Long id,Long userId);
	
	public ResultStatus unSubscriptionDetails(Long id,Long userId);
	
	public List<Long> getVoterHouseDetailsForPanchayat(Long panchayatId, String year,String checkedEle);
	
	public VoterCastInfoVO getVotersCastInfoForPanchayat(Long panchayatId, String year);
	
	public VoterCastInfoVO getVotersCastInfoForPollingStation(Long panchayatId, String year);
	
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long hamletId, String year,String checkedele);
	//constituency wise
	public VoterCastInfoVO getVotersCastInfoForAssembly(Long panchayatId, String year);
	
	public VoterCastInfoVO getVotersCastInfoForMandal(Long panchayatId, String year);
	
	public List<VoterVO> getVoterInfoForPollingStation(Long hamletId,String year, Integer startIndex, Integer maxRecords, String order, String columnName);
	
	//public Long getProblemVisibility(Long problemId);
	
	//public HamletBoothVotersListVO findAllBoothVotersForHamlet(Long hamletID, String year, String electionType);
	
	public List<Long> getVoterAgeDetailsForPanchayat(Long panchayatId, String year,String checkedEle);
	
	public VotersInfoForMandalVO getBasicVotersInfo(Long constituencyId, String year,String checkedEle,Long flag);
	
	public VoterCastInfoVO getVotersCastInfoForUrban(Long assemblyLocalBodyId, String year);
	
*/}
