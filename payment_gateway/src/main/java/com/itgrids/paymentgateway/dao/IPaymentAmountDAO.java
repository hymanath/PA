package com.itgrids.paymentgateway.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.paymentgateway.model.PaymentAmount;

public interface IPaymentAmountDAO extends GenericDao<PaymentAmount,Long>{
	public Long getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr);
}
