package com.itgrids.cardprint.service;

import java.util.List;

import com.itgrids.cardprint.dto.PrintStatusVO;

public interface ICardPrintAdminService {
	
	public Long getPrintVendorIdByLoggedInUser(Long userId);
	
	public List<PrintStatusVO> getPrintStatusWiseConstitCountByLoggedUser(Long cardPrintVendorId);
}
