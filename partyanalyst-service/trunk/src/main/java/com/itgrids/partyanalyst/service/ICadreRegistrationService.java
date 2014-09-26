package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICadreRegistrationService {

	public ResultStatus saveCadreRegistration(final CadreRegistrationVO cadreRegistrationVO);
}
