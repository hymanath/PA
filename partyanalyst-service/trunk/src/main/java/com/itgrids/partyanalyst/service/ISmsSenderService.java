package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISmsSenderService {
	public ResultStatus sendSmsToCadre(MobileAppUserVoterVO inputVO);
	public ResultStatus sendSmsToVoter(MobileAppUserVoterVO inputVO);
	//public ResultStatus sendSmsToCadre(Integer startIndex,Integer maxIndex);
}
