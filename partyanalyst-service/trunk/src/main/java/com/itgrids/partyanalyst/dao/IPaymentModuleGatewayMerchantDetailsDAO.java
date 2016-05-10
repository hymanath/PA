package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PaymentModuleGatewayMerchantDetails;

public interface IPaymentModuleGatewayMerchantDetailsDAO extends GenericDao<PaymentModuleGatewayMerchantDetails,Long>{

	public List<Object[]> getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr);
}
