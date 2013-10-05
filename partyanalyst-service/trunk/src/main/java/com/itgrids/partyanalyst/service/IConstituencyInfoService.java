package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;

public interface IConstituencyInfoService {
	
	
	public List<VotersDetailsVO> getConstituencyBasicInfoById(Long constituencyId,Long publicationId,Long userId);
	
	public List<VotersDetailsVO> getConstituencyBasicCountInfoById(Long constituencyId,Long userId);
}
