package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;

public interface ICtpCasteReportService {
	public VoterHouseInfoVO getVoterDetailsForSearch(VoterHouseInfoVO inputVo ,String locationType,Long id);
	public VoterHouseInfoVO getCTPVoterCount(Long userId);
}
