package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPaymentAmountDAO;
import com.itgrids.partyanalyst.model.PaymentAmount;

public class PaymentAmountDAO extends GenericDaoHibernate<PaymentAmount,Long> implements IPaymentAmountDAO{
	
	public PaymentAmountDAO() 
	{
		super(PaymentAmount.class);
	}
	
	public Long getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr){
		Query query = getSession().createQuery("select model.amount" +
									" from PaymentAmount model" +
									" where model.paymentModule.paymentModule = :moduleStr" +
									" and model.paymentModuleSubType.subType = :subTypeStr" +
									" and model.paymentModuleSubType.isDeleted = 'N' and model.paymentModuleSubType.isActive = 'Y'" +
									" and model.isDeleted = 'N' and model.isActive = 'Y'");
		query.setParameter("moduleStr", moduleStr);
		query.setParameter("subTypeStr", subTypeStr);
		
		return (Long) query.uniqueResult();
	}
}
