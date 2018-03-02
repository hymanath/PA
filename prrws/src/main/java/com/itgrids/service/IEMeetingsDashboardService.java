package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.EMeetingsVO;
import com.itgrids.dto.InputVO;

public interface IEMeetingsDashboardService {

	public EMeetingsVO getEMeetingsOverViewDetails(InputVO inputVO);
	public List<EMeetingsVO> getEMeetingsLevelWiseOverViewDetails(InputVO inputVO);
	public EMeetingsVO getLevelsWiseConductedMeetingDetails(InputVO inputVO);
	public EMeetingsVO getMeetingDetails(InputVO inputVO);
}
