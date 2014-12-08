package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;

public interface ICadreRegistrationForOtherStatesService {
	public void tdpCadreSavingLogic(final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar);
}
