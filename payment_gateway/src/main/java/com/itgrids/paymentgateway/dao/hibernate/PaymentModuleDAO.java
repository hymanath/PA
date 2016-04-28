package com.itgrids.paymentgateway.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentModuleDAO;
import com.itgrids.paymentgateway.model.PaymentModule;

public class PaymentModuleDAO extends GenericDaoHibernate<PaymentModule,Long> implements IPaymentModuleDAO{
	
	public PaymentModuleDAO() 
	{
		super(PaymentModule.class);
	}
}
