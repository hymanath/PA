package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;

public interface IPrisSurveyDashBaordService{
	public PrisDataVo getPrisSurveyBasicData(InputVO inputVO);
	public PrisOverviewVo getPIRSSurveyInfo(InputVO inputVO);
	public List<IdNameVO> getAllDistricts();
	public List<IdNameVO> getAllParliaments();
	public List<IdNameVO> getAllConstituenciesForDistrict(InputVO inputVo);
	public List<IdNameVO> getAllConstituenciesForParliament(Long parliamentId);
	public PrisOverviewVo getPIRSSurveyInfoStateLevel(InputVO inputVO);
}
