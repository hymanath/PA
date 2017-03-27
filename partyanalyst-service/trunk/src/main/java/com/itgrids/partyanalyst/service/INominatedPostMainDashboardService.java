package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.GeoLevelListVO;
import com.itgrids.partyanalyst.dto.GeoLevelReportVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;

public interface INominatedPostMainDashboardService {

	 public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId,Long stateId);
	 public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId,Long stateId);
	 public List<IdNameVO> getPositions();
	 public NominatedPostDashboardVO getAllPositionWiseStatus(Long positionId);
	 public List<IdAndNameVO> getCastGroupList();
	 public List<IdAndNameVO> getApplicationStatusList();
	 public List<IdAndNameVO> getPositionList();
	 public List<IdAndNameVO> getLocationLevelList();
	 public List<IdAndNameVO> getDepartmentList(Long boardLevelId);
	 public List<IdAndNameVO> getBoardsList(List<Long> deptId,Long boardLevelId);

	 //public List<NominatedPostDashboardVO> getPositionsForDistrict(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId);
	 public CastePositionVO getPositionAndApplicationDetailsCntPositionWise(Long positionId,Long boardLevelId,String reportType,Long stateId);
	 public CastePositionVO getPositionAndApplicationDetailsCntLocationWise(Long positionId,Long boardLevelId,String reportType,Long stateId);
	 
	 public List<NominatedPostVO> getNominatedPostCandidateLocationWiseDetails(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName);
	 public List<NominatedPostVO> getNominatedPostCandidatePositionWiseDetails(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long casteCategoryId,Long positionStatusId,Long stateId,Long districtId, String locationLevelName);
	 
	 public  List<IdNameVO> getNominatedPostCandidateDetils(Long stateId,Long casteStateId,Long positionId,Long boardLevelId,Long casteCategryId,
             Long ageRangeTypeId,Long deptmentId,Long corptionId,
             String genderType,List<Long> postStatusIds,Long locationId,String type);
	 public List<IdAndNameVO> getLocationAndBoardLevelWisePostsData(Long postLevelId,Long casteGrpId,Long casteId,Long ageRangeId,
				Long positionId,String gender,Long stateId,String searchType,List<Long> postStatusIds);
	 public List<NominatedPostDashboardVO> getLocationAndBoardLevelWiseCasteCatgryPostsData(Long postLevelId,Long casteGrpId,Long casteId1,Long ageRangeId,Long positionId,String gender1,
				Long stateId,String searchType,List<Long> locIdsList,String type,List<Long> postStatusIds);
	 public  List<IdNameVO> getCandidateLocationWiseDetails(Long postLevelId,Long casteGrpId,Long casteId1,Long ageRangeId,Long positionId,String gender1,
				Long stateId,String searchType,List<Long> locIdsList,List<Long> postStatusIds,String type);
	 public List<GeoLevelListVO> getGeoLevelReportDetails(GeoLevelReportVO vo);
	 public List<IdAndNameVO> getAllAgeRangesList();
	 public List<IdAndNameVO> getCastListByCasteCatgryId(List<Long> casteCatgryId,Long stateId);
}
