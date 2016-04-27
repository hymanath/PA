package com.itgrids.paymentgateway.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.paymentgateway.dao.IPaymentMethodDAO;
import com.itgrids.paymentgateway.model.PaymentMethod;
import com.itgrids.paymentgateway.service.IPaymentGatewayService;

public class PaymentGatewayService implements IPaymentGatewayService{
	
	private static final Logger LOG = Logger.getLogger(PaymentGatewayService.class);
	
	private IPaymentMethodDAO paymentMethodDAO;

	public void setPaymentMethodDAO(IPaymentMethodDAO paymentMethodDAO) {
		this.paymentMethodDAO = paymentMethodDAO;
	}
	
	public List<Object[]> getAllPaymentMethodIds()
	{
		try{
			List<Object[]> list = paymentMethodDAO.getAllPaymentMethodIds();
			
			if(list != null && list.size() > 0)
			{
				System.out.println(list.size());
				return list;
			}
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return null;
	}
	
	public boolean savePaymentMethod(String paymentMethodStr)
	{
		try{
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setMethod(paymentMethodStr);
			paymentMethodDAO.save(paymentMethod);
			return true;
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return false;
	}
}
