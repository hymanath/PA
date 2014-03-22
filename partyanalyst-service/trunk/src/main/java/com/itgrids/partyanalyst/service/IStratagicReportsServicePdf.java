package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.StratagicReportInputVO;


public interface IStratagicReportsServicePdf {
	
	public Object buildPdf(StratagicReportInputVO inputVo);
	public Object buildPdfDelegator(StratagicReportInputVO inputVo);
	
	
}
