package com.itgrids.cardprint.service;

import java.util.List;

import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.ResultStatus;

public interface ICardPrintService {
	
	public List<BasicVO>  getAllVendors();
	public List<BasicVO>  getAllPrintStatus();
	
	public ResultStatus saveConstituencyPrintStatus(final Long constituencyId , final Long printVendorId , final Long printStatusId,final String remarks,final Long userId);
}
