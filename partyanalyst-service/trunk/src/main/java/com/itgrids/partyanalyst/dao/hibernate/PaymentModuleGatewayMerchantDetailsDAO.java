package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentModuleGatewayMerchantDetailsDAO;
import com.itgrids.paymentgateway.model.PaymentModuleGatewayMerchantDetails;

public class PaymentModuleGatewayMerchantDetailsDAO extends GenericDaoHibernate<PaymentModuleGatewayMerchantDetails,Long> implements IPaymentModuleGatewayMerchantDetailsDAO{
	
	public PaymentModuleGatewayMerchantDetailsDAO() 
	{
		super(PaymentModuleGatewayMerchantDetails.class);
	}
}
