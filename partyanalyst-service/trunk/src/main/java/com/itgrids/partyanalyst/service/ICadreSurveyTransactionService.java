package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;

public interface ICadreSurveyTransactionService {
	
	public String genarateOTPAndSaveTxnDetails(CadreTransactionVO inputVO);
	public String updateTxnStatus(CadreTransactionVO inputVo);
	public String validateOTPForMobile(CadreTransactionVO inputVo);
	public String updatePendingAmount(CadreTransactionVO inputVo);
	public String saveReconciliationData(ReconciliationVO inputVo);

}
