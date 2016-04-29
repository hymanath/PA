package com.itgrids.paymentgateway.service;

import java.util.List;

import com.itgrids.paymentgateway.dto.PamentGatewayVO;

public interface IPaymentGatewayService {
	public List<Object[]> getAllPaymentMethodIds();
	public PamentGatewayVO getPaymentBasicInfoByPaymentGateWayType(Long gateWayId,String randomNo,String enrollId);
}
