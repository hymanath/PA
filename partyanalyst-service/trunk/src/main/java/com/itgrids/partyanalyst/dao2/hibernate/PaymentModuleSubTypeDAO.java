package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPaymentModuleSubTypeDAO;
import com.itgrids.partyanalyst.model.PaymentModuleSubType;

public class PaymentModuleSubTypeDAO extends GenericDaoHibernate<PaymentModuleSubType,Long> implements IPaymentModuleSubTypeDAO{
	
	public PaymentModuleSubTypeDAO() 
	{
		super(PaymentModuleSubType.class);
	}
}
