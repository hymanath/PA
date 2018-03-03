package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.dto.EventLocationVO;

public interface IDalithaTejamDashBoardService {

	public List<DalithaTejamVO> getLatestImages(DalithaTejamInputVo inputVo);

	public List<DalithaTejamVO> getDateWiseCount(DalithaTejamInputVo inputVo);
	public List<EventLocationVO> DalithTejamLocationWiseData(String fromDate,String toDate,Long locationScopeId,Long activityScopeId,Long locationValue);
}
