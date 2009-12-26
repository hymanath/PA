package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;

public interface IUserCadreManagementService {
	public CadreManagementVO getUserData(RegistrationVO user);
}
