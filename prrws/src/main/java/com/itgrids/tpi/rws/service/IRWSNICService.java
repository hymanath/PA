package com.itgrids.tpi.rws.service;

import java.util.List;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.StatusVO;

public interface IRWSNICService {
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO vo);
	public BasicVO getLabTestDetails(InputVO vo);
	public BasicVO getHabitationSupplyDetails(InputVO vo);
	public List<BasicVO> getSchemesDetails(InputVO VO);
	public List<BasicVO> getSchemeWiseWorkDetails(InputVO VO);
	public List<BasicVO> getAssetsInfo(InputVO vo);
	public List<StatusVO> getAlertDetailsOfCategoryByStatusWise(InputVO vo);
	public List<StatusVO> getAlertFeedbackStatusDetails(InputVO vo);
	public List<StatusVO> getWaterSourceInfo(InputVO vo);
	public List<LocationVO> getKeyPerformanceIndicatorsInfo(InputVO inputVO);
	public StatusVO getPlanofActionForStressedHabitations(InputVO vo);
}
