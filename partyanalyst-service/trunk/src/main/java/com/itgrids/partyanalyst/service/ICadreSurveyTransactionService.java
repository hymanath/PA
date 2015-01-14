package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionReportVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;

public interface ICadreSurveyTransactionService {
	
	public List<SelectOptionVO> getParliamentsForState(String electionType,Long stateTypeId);
	public String updatePendingAmount(CadreTransactionVO inputVo);
	public String saveReconciliationData(ReconciliationVO inputVo);
//	public String saveReconsilationFailedDetails(ReconciliationFailureVO inputVo);
	public SurveyTransactionReportVO getBasicTransactionDetails();
	
	public CadreTransactionVO getTdpCadreDetailsBySearchCriteria(String refNo, String mobileNo);

	public void sendTargetBasedSMSforLocationWiseManagers(List<Long> districtIds);
}
