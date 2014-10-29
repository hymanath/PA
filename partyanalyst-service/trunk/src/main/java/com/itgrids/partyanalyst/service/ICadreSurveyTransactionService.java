package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;

public interface ICadreSurveyTransactionService {
	
	public CadreTransactionVO genarateOTPAndSaveTxnDetails(CadreTransactionVO inputVO);
	public String updateTxnStatus(String uniqueKey,String status,Long constituencyId);

}
