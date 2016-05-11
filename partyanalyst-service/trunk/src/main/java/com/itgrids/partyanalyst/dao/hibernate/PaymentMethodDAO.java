package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPaymentMethodDAO;
import com.itgrids.partyanalyst.model.PaymentMethod;

public class PaymentMethodDAO extends GenericDaoHibernate<PaymentMethod,Long> implements IPaymentMethodDAO{
	
	public PaymentMethodDAO() 
	{
		super(PaymentMethod.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPaymentMethodIds()
	{
		Query query = getSession().createQuery("select model.paymentMethodId from PaymentMethod model");
		return query.list();
	}
}
