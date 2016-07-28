package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastePositionVO;

public interface INominatedPostMainDashboardService {

	 public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId);
	 public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId);
}
