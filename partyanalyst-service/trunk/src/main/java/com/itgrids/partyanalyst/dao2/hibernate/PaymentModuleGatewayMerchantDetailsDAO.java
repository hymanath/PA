package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPaymentModuleGatewayMerchantDetailsDAO;
import com.itgrids.partyanalyst.model.PaymentModuleGatewayMerchantDetails;

public class PaymentModuleGatewayMerchantDetailsDAO extends GenericDaoHibernate<PaymentModuleGatewayMerchantDetails,Long> implements IPaymentModuleGatewayMerchantDetailsDAO{
	
	public PaymentModuleGatewayMerchantDetailsDAO() 
	{
		super(PaymentModuleGatewayMerchantDetails.class);
	}
	
	public List<Object[]> getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr){
		Query query = getSession().createQuery("select model.paymentGateway.paymentGatewayId," +
									" model.paymentGateway.url," +
									" model.marchantNo," +
									" model.workingKey," +
									"model.preURL," +
									"model.postURL," +
									"model.redirectURL," +
									"model.paymentModuleGatewayMerchantDetailsId " +
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
