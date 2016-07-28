package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;

public interface INominatedPostMainDashboardService {

	 public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId);
	 public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId);
	 public List<IdNameVO> getPositions();
	public NominatedPostDashboardVO getAllPositionWiseStatus(Long positionId);
}
