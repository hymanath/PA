package com.itgrids.paymentgateway.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.paymentgateway.model.PaymentMethod;

public interface IPaymentMethodDAO extends GenericDao<PaymentMethod,Long>{

	public List<Object[]> getAllPaymentMethodIds();
}
