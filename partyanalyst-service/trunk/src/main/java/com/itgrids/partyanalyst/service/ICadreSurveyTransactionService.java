package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;

public interface ICadreSurveyTransactionService {

	public SurveyTransactionVO getCadreSurveyTransactionDetails();	
	public SurveyTransactionVO getDayWiseReportByDates(String fromDateStr , String toDateStr);	
	public SurveyTransactionVO getLocationWiseTransactionDetails(String fromDateStr , String toDateStr, String searchType, List<Long> locationIdList);	
	public List<SelectOptionVO> getParliamentsForState(String electionType,Long stateTypeId);
	
	public String genarateOTPAndSaveTxnDetails(CadreTransactionVO inputVO);
	public String updateTxnStatus(CadreTransactionVO inputVo);
	public String validateOTPForMobile(CadreTransactionVO inputVo);
	public String updatePendingAmount(CadreTransactionVO inputVo);
	public String saveReconciliationData(ReconciliationVO inputVo);

}
