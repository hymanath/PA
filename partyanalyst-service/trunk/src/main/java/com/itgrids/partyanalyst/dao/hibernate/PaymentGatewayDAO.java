package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPaymentGatewayDAO;
import com.itgrids.partyanalyst.model.PaymentGateway;



public class PaymentGatewayDAO extends GenericDaoHibernate<PaymentGateway,Long> implements IPaymentGatewayDAO{
	
	public PaymentGatewayDAO() 
	{
		super(PaymentGateway.class);
	}
}
