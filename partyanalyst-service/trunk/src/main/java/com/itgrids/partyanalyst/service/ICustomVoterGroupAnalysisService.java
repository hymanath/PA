package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;

public interface ICustomVoterGroupAnalysisService {
	
	public List<VoterCastInfoVO> getCasteWiseCustomVotersCount(Long customVoterGroupId,Long userId);
	public VotersDetailsVO getAgeWiseCustomVotersInGroup(Long userId,Long customGroupId,Long publicationDateId);
}
