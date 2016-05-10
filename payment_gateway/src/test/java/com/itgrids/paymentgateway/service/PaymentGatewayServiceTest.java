package com.itgrids.paymentgateway.service;

import org.appfuse.dao.BaseDaoTestCase;

public class PaymentGatewayServiceTest extends BaseDaoTestCase{
	
	private IPaymentGatewayService paymentGatewayService;

	public void setPaymentGatewayService(
			IPaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
	}
	
	/*public void testGetAllPaymentMethodIds()
	{
		List<Object[]> list = paymentGatewayService.getAllPaymentMethodIds();
		System.out.println(list.size());
	}*/
	
	public void testAddPaymentMethod()
	{
		System.out.println(paymentGatewayService.getAllPaymentMethodIds().size());
	}

}
