package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SwachhBharatMissionIHHLDtlsVO;

public interface ISwachhBharatMissionIHHLService {

	public SwachhBharatMissionIHHLDtlsVO getSwachhBharatMissionOverviewDtls(InputVO inputVO);
	public List<SwachhBharatMissionIHHLDtlsVO> getSwachhBharatMissionLocationWiseDetails(InputVO inputVO);
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLAchivementProgressDtls(InputVO inputVO);
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLCategoryWiseAnalysis(InputVO inputVO);
}

