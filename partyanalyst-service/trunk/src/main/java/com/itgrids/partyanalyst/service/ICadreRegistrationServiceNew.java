package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICadreRegistrationServiceNew {
	
	public ResultStatus pushTotalTodayTdpCadreDataToIntermediate();
	public ResultStatus pushTotalTodayTdpCadreDataToIntermediateByLowLevel();
	public ResultStatus pushTdpCadreDataToIntermediateDateWise();
	public void saveCadreImage(ImageCadreVO inputVO);
}
