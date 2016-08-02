package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;

public interface INominatedPostMainDashboardService {

	 public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId);
	 public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId);
	 public List<IdNameVO> getPositions();
	 public NominatedPostDashboardVO getAllPositionWiseStatus(Long positionId);
	 public List<IdAndNameVO> getCastGroupList();
	 public List<IdAndNameVO> getApplicationStatusList();
	 public List<IdAndNameVO> getPositionList();
	 public List<IdAndNameVO> getLocationLevelList();
	 public List<IdAndNameVO> getDepartmentList();
	 public List<IdAndNameVO> getBoardList(); 
	 public List<NominatedPostVO> getNominatedCandidateGroupByDist(List<Long> positionIdList, List<Long> locationLevelIdList, List<Long> deptIdList, List<Long> corporationIdList, List<Long> castGroupIdList, List<Long> positionStatusIdList, Long stateId);

	 public List<NominatedPostDashboardVO> getPositionsForDistrict(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId);
	 public CastePositionVO getPositionAndApplicationDetailsCntPositionWise(Long positionId,Long boardLevelId,String reportType);
	 public CastePositionVO getPositionAndApplicationDetailsCntLocationWise(Long positionId,Long boardLevelId,String reportType);
}
