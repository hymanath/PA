package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;
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
	 public List<IdAndNameVO> getBoardList(Long deptId); 

	 //public List<NominatedPostDashboardVO> getPositionsForDistrict(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId);
	 public CastePositionVO getPositionAndApplicationDetailsCntPositionWise(Long positionId,Long boardLevelId,String reportType,Long stateId);
	 public CastePositionVO getPositionAndApplicationDetailsCntLocationWise(Long positionId,Long boardLevelId,String reportType,Long stateId);
	 
	 public List<NominatedPostVO> getNominatedPostCandidateLocationWiseDetails(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName);
	 public List<NominatedPostVO> getNominatedPostCandidatePositionWiseDetails(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId, String locationLevelName);

}
