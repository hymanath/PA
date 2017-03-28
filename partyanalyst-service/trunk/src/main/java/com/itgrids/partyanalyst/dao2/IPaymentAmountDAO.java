package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PaymentAmount;

public interface IPaymentAmountDAO extends GenericDao<PaymentAmount,Long>{

	public Long getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr);
}
