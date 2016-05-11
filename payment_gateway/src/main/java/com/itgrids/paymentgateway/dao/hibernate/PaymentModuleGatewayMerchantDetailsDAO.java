package com.itgrids.paymentgateway.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.paymentgateway.dao.IPaymentModuleGatewayMerchantDetailsDAO;
import com.itgrids.paymentgateway.model.PaymentModuleGatewayMerchantDetails;

public class PaymentModuleGatewayMerchantDetailsDAO extends GenericDaoHibernate<PaymentModuleGatewayMerchantDetails,Long> implements IPaymentModuleGatewayMerchantDetailsDAO{
	
	public PaymentModuleGatewayMerchantDetailsDAO() 
	{
		super(PaymentModuleGatewayMerchantDetails.class);
	}
	
	public List<Object[]> getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr){
		Query query = getSession().createQuery("select model.paymentGateway.paymentGatewayId," +
									" model.paymentGateway.url," +
									" model.marchantNo," +
									" model.workingKey" +
									" from PaymentModuleGatewayMerchantDetails model" +
									" where model.paymentModule.paymentModule = :moduleStr" +
									" and model.paymentModuleSubType.subType = :subTypeStr" +
									" and model.paymentModuleSubType.isDeleted = 'N' and model.paymentModuleSubType.isActive = 'Y'" +
									" and model.isDeleted = 'N' and model.isActive = 'Y'");
		query.setParameter("moduleStr", moduleStr);
		query.setParameter("subTypeStr", subTypeStr);
		
		return query.list();
	}
}
