package com.itgrids.partyanalyst.service;

import java.util.List;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;

public interface ICardPrintService {
	public List<CardPrintVO> getVendorNames();
	public List<CardPrintVO> getDstrListByVendor(Long vendorId);
	public List<CardPrintVO> getConstListByVendor(Long vendorId,Long districtId);
	public CardPrintVO getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,String startDateStr,String endDateStr);
	public CardPrintVO getDistrictWiseStatusWiseConstituenciesCounts(Long vendorId,String fromDateStr,String toDateStr);
	public List<CardPrintingDispatchVO> getPrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId);
	public CardPrintVO getVendorWiseStatusWiseConstituenciesDetails(Long stateId,String fromDateStr,String toDateStr);
}
