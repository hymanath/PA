package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;

public interface IDalithaTejamDashBoardService {

	public List<DalithaTejamVO> getLatestImages(DalithaTejamInputVo inputVo);
}
