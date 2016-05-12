package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.PaymentTransactionVO;

public interface IPaymentGatewayService {
	
	public PaymentGatewayVO getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr);
	public PaymentGatewayVO getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr);
	public String savePaymenyTransactionDetails(final PaymentTransactionVO paymentTransactionVO);
	public PaymentGatewayVO getPaymentBasicInfoByPaymentGateWayType(Long gateWayId,String randomNo,String enrollId,String moduleStr,String subTypeStr);
}
