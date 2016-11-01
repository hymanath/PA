package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPaymentTransactionInfoDAO;
import com.itgrids.partyanalyst.model.PaymentTransactionInfo;

public class PaymentTransactionInfoDAO extends GenericDaoHibernate<PaymentTransactionInfo,Long> implements IPaymentTransactionInfoDAO{

	public PaymentTransactionInfoDAO() {
		super(PaymentTransactionInfo.class);
	}

}
