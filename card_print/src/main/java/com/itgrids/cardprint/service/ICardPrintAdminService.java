package com.itgrids.cardprint.service;

import java.util.List;

import com.itgrids.cardprint.dto.PrintStatusVO;

public interface ICardPrintAdminService {
	
	public Long getPrintVendorIdByLoggedInUser(Long userId);
	
	public List<PrintStatusVO> getPrintStatusWiseConstitCountByLoggedUser(Long cardPrintVendorId);
	public List<PrintStatusVO> getPrintStatusWiseRecordCountByLoggedUSer(Long cardPrintVendorId);
	public List<PrintStatusVO> constWisePrintStatusWiseRecordCountByLoggedUSer(Long cardPrintVendorId);
	
	public List<PrintStatusVO> getPrintStatusWiseConstitCount();
	public List<PrintStatusVO> getPrintStatusWiseRecordCount();
	public List<PrintStatusVO> constWisePrintStatusWiseRecordCount();
}
