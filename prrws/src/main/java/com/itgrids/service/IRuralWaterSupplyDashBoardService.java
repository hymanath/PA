package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;

public interface IRuralWaterSupplyDashBoardService {
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO vo);
}
