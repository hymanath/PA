package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;

public interface ICadreSurveyTransactionService {
	
	public CadreTransactionVO genarateOTPAndSaveTxnDetails(CadreTransactionVO inputVO);
	public String updateTxnStatus(CadreTransactionVO inputVo);
	public String validateOTPForMobile(CadreTransactionVO inputVo);
	public String updatePendingAmount(CadreTransactionVO inputVo);

}
