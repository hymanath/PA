package com.itgrids.paymentgateway.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.paymentgateway.dao.ITransactionStatusDAO;
import com.itgrids.paymentgateway.model.TransactionStatus;

public class TransactionStatusDAO extends GenericDaoHibernate<TransactionStatus,Long> implements ITransactionStatusDAO{
	
	public TransactionStatusDAO() 
	{
		super(TransactionStatus.class);
	}
}
