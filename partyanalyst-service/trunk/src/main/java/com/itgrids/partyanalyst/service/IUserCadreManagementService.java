package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreManagementVO;

public interface IUserCadreManagementService {
	public CadreManagementVO getUserData(Long userID, Long partyID);
}
