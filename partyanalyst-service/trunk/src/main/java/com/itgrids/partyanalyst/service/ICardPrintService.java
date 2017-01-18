package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreValidateVO;
import com.itgrids.partyanalyst.dto.CardPrintStatusVO;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;
import com.itgrids.partyanalyst.dto.CardsValidateVO;
import com.itgrids.partyanalyst.dto.PrintStatusUpdateVO;
import com.itgrids.partyanalyst.dto.PrintUpdateDetailsStatusVO;
import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SmallVO;

public interface ICardPrintService {
	public List<CardPrintVO> getVendorNames();
	public List<CardPrintVO> getDstrListByVendor(Long vendorId);
	public List<CardPrintVO> getConstListByVendor(Long vendorId,Long districtId);
	public CardPrintVO getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,String startDateStr,String endDateStr);
	public CardPrintVO getDistrictWiseStatusWiseConstituenciesCounts(Long vendorId,String fromDateStr,String toDateStr);
	public CardPrintingDispatchVO getPrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId);
	public CardPrintVO getVendorWiseStatusWiseConstituenciesDetails(Long stateId,String fromDateStr,String toDateStr);
	
	public PrintUpdateDetailsStatusVO updatePrintDetailsToTdpCadreCardPrint(final List<PrintVO> printList);
	
	public List<CardPrintStatusVO> cardPrinStatusByLocation(String type , Long stateId );
	public List<CardPrintVO> getEnrollmentDetailsByConstituency();
	
	public CadreValidateVO getConstNotVerfiedCardPrintStatusCadreAndValidate(Long constituencyId);
	
	public List<SmallVO> getPrintPushedConstituencies();
	public List<String> postVerificationCadreData(Long constituencyId);
	
	public ResultStatus saveConstituencyPrintStatus(final PrintStatusUpdateVO inputVO);
	public CardsValidateVO constWiseValidatedCadreByUser(Long userId , String fromDateStr ,String  toDateStr);
	public CardsValidateVO boxWiseValidatedCadreByUser(Long userId , String fromDateStr ,String  toDateStr);
}
