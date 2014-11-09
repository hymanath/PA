package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICadreRegAmountDetailsService {

	public ResultStatus uploadCadreRegAmountDetails(CadreRegAmountUploadVO cadreRegAmountUploadVO);
}
