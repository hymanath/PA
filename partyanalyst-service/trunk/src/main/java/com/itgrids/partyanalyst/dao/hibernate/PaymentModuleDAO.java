package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPaymentModuleDAO;
import com.itgrids.partyanalyst.model.PaymentModule;

public class PaymentModuleDAO extends GenericDaoHibernate<PaymentModule,Long> implements IPaymentModuleDAO{
	
	public PaymentModuleDAO() 
	{
		super(PaymentModule.class);
	}
}
