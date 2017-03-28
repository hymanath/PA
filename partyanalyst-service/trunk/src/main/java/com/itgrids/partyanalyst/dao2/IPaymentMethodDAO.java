package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PaymentMethod;

public interface IPaymentMethodDAO extends GenericDao<PaymentMethod,Long>{

	public List<Object[]> getAllPaymentMethodIds();
}
