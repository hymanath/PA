package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.PaymentGatewayVO;

public interface IPaymenyGatewayService {
	
	public Long getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr);
	public PaymentGatewayVO getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr);
}
