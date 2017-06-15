package com.itgrids.tpi.rws.service;

import java.util.List;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;

public interface IRWSNICService {
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO vo);
	public BasicVO getLabTestDetails(InputVO vo);
	public BasicVO getHabitationSupplyDetails(InputVO vo);
	public List<BasicVO> getSchemesDetails(InputVO VO);
	public List<BasicVO> getSchemeWiseWorkDetails(InputVO VO);
	public List<BasicVO> getAssetsInfo(InputVO vo);
}
