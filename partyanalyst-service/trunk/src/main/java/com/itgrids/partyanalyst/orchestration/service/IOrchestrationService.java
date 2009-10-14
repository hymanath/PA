package com.itgrids.partyanalyst.orchestration.service;

import com.itgrids.partyanalyst.dto.SIInput;
import com.itgrids.partyanalyst.dto.SIResult;

@Deprecated
public interface IOrchestrationService {

	SIResult invoke(SIInput oInput);

}
