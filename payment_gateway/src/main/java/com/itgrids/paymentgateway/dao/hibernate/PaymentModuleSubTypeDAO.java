package com.itgrids.paymentgateway.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.IPaymentModuleSubTypeDAO;
import com.itgrids.paymentgateway.model.PaymentModuleSubType;

public class PaymentModuleSubTypeDAO extends GenericDaoHibernate<PaymentModuleSubType,Long> implements IPaymentModuleSubTypeDAO{
	
	public PaymentModuleSubTypeDAO() 
	{
		super(PaymentModuleSubType.class);
	}
}
