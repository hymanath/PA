package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.UserDataVO;

public interface ICoreDashboardService {
	
	public List<UserDataVO> getUserAccessLevelAndValues(Long userId);
	public UserDataVO getUserTypeByUserId(Long userId);
}
