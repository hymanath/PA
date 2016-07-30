package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;

public interface INominatedPostMainDashboardService {

	 public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId);
	 public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId);
	 public List<IdNameVO> getPositions();
	 public NominatedPostDashboardVO getAllPositionWiseStatus(Long positionId);
	 public List<IdAndNameVO> getCastGroupList();
	 public List<IdAndNameVO> getApplicationStatusList();
	 public List<IdAndNameVO> getPositionList();
	 public List<IdAndNameVO> getLocationLevelList();
	 public List<IdAndNameVO> getDepartmentList();
	 public List<IdAndNameVO> getBoardList();
}
