package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.StratagicReportInputVO;
import com.itgrids.partyanalyst.dto.StrategyVO;


public interface IStratagicReportsServicePdf {
	
	public Object buildPdf(StrategyVO strategyVO);
	public Object buildPdfDelegator(StrategyVO strategyVO);
	
	
}
