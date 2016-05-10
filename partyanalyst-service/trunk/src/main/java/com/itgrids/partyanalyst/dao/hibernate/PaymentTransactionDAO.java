package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentTransactionDAO;
import com.itgrids.paymentgateway.model.PaymentTransaction;

public class PaymentTransactionDAO extends GenericDaoHibernate<PaymentTransaction,Long> implements IPaymentTransactionDAO{
	
	public PaymentTransactionDAO() 
	{
		super(PaymentTransaction.class);
	}
}
