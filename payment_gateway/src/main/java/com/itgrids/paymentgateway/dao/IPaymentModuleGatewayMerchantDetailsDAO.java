package com.itgrids.paymentgateway.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.paymentgateway.model.PaymentModuleGatewayMerchantDetails;

public interface IPaymentModuleGatewayMerchantDetailsDAO extends GenericDao<PaymentModuleGatewayMerchantDetails,Long>{
	public List<Object[]> getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr);
}
