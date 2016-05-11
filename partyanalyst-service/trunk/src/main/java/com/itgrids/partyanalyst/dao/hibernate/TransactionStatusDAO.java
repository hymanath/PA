package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITransactionStatusDAO;
import com.itgrids.partyanalyst.model.TransactionStatus;

public class TransactionStatusDAO extends GenericDaoHibernate<TransactionStatus,Long> implements ITransactionStatusDAO{
	
	public TransactionStatusDAO() 
	{
		super(TransactionStatus.class);
	}
}
