package com.itgrids.cardprint.service;

import java.util.List;

import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.PrintStatusUpdateVO;
import com.itgrids.cardprint.dto.ResultStatus;

public interface ICardPrintService {
	
	public List<BasicVO>  getAllVendors();
	public List<BasicVO>  getAllPrintStatus();
	public List<BasicVO>  getAllAssemblyConstituencies();
	
	public ResultStatus saveConstituencyPrintStatus(final PrintStatusUpdateVO inputVO);
}
