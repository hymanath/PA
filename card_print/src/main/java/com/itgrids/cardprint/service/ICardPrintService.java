package com.itgrids.cardprint.service;

import java.util.List;

import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.CardPrintVO;
import com.itgrids.cardprint.dto.CardPrintingDispatchVO;
import com.itgrids.cardprint.dto.PrintStatusUpdateVO;
import com.itgrids.cardprint.dto.ResultStatus;

public interface ICardPrintService {
	
	public List<BasicVO>  getAllVendors();
	public List<BasicVO>  getAllPrintStatus();
	public List<BasicVO>  getAllAssemblyConstituencies();
	public List<BasicVO>  getConstituenciesByPrintVendor(Long printVendorId);
	public BasicVO getVendorIdAndConstituenciesByLoggedInUser(Long userId);
	public ResultStatus saveConstituencyPrintStatus(final PrintStatusUpdateVO inputVO);
	public ResultStatus updatePrintDetailsToTdpCadreCardPrint(final Long cardPrintVendorId , final Long constituencyId);
	public List<CardPrintVO> getDstrListByVendor(Long vendorId);
	public List<CardPrintVO> getConstListByVendor(Long vendorId,Long districtId);
	public CardPrintingDispatchVO getPrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId);
}
