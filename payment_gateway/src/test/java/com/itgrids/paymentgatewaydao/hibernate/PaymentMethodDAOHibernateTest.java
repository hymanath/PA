package com.itgrids.paymentgatewaydao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.paymentgateway.dao.IPaymentMethodDAO;

public class PaymentMethodDAOHibernateTest extends BaseDaoTestCase{

	private IPaymentMethodDAO paymentMethodDAO;

	public void setPaymentMethodDAO(IPaymentMethodDAO paymentMethodDAO) {
		this.paymentMethodDAO = paymentMethodDAO;
	}
	
	/*public void test()
	{
		paymentMethodDAO.getAll();
	}*/
	
	public void testGetAllPaymentMethodIds()
	{
		List<Object[]> list = paymentMethodDAO.getAllPaymentMethodIds();
		System.out.println(list.size());
	}
}
