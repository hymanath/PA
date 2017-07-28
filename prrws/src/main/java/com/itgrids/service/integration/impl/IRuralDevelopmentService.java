package com.itgrids.service.integration.impl;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;

public interface IRuralDevelopmentService {
	
	public NregsProjectsVO getNtrJalaSiriAbstract(InputVO inputVO);
	public NregsOverviewVO getNtrJalaSiriOverview(InputVO inputVO);
	public List<NregsDataVO> getNtrJalaSiriLvlWiseData(InputVO inputVO);

}
