package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISmsSenderService {
	public ResultStatus sendSmsToCadre(Integer startIndex,Integer maxIndex);
	public ResultStatus sendSmsToVoter(MobileAppUserVoterVO inputVO);
}
