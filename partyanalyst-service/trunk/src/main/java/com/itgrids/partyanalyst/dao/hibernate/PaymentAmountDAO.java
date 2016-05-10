package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentAmountDAO;
import com.itgrids.paymentgateway.model.PaymentAmount;

public class PaymentAmountDAO extends GenericDaoHibernate<PaymentAmount,Long> implements IPaymentAmountDAO{
	
	public PaymentAmountDAO() 
	{
		super(PaymentAmount.class);
	}
}
