package com.itgrids.paymentgateway.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentGatewayDAO;
import com.itgrids.paymentgateway.model.PaymentGateway;

public class PaymentGatewayDAO extends GenericDaoHibernate<PaymentGateway,Long> implements IPaymentGatewayDAO{
	
	public PaymentGatewayDAO() 
	{
		super(PaymentGateway.class);
	}
}
