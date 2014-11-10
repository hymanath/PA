package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICadreRegAmountDetailsService {

	public ResultStatus uploadCadreRegAmountDetails(CadreRegAmountUploadVO cadreRegAmountUploadVO);
	public List<CadreAmountDetailsVO> getCadreRegAmountDetails(String fromDt,String toDt,String reportValue);
	public List<CadreAmountDetailsVO> getCadreSummaryAmountDayWise();
}
