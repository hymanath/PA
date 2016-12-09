package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CardPrintVO;

public interface ICardPrintService {

	public CardPrintVO getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,String startDateStr,String endDateStr);
	public CardPrintVO getDistrictWiseStatusWiseConstituenciesCounts(Long vendorId,String fromDateStr,String toDateStr);
}
