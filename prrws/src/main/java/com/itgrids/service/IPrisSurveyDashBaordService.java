package com.itgrids.service;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;

public interface IPrisSurveyDashBaordService{
	public PrisDataVo getPrisSurveyBasicData(InputVO inputVO);
	public PrisOverviewVo getPrisLocationWiseOverview(InputVO inputVO);
}
