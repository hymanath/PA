package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPaymentAmountDAO;
import com.itgrids.partyanalyst.dao.IPaymentModuleGatewayMerchantDetailsDAO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.service.IPaymenyGatewayService;

public class PaymentGatewayService implements IPaymenyGatewayService{
	private final static Logger LOG =  Logger.getLogger(PaymentGatewayService.class);
	
	private IPaymentAmountDAO paymentAmountDAO;
	private IPaymentModuleGatewayMerchantDetailsDAO paymentModuleGatewayMerchantDetailsDAO;
	
	
	public IPaymentModuleGatewayMerchantDetailsDAO getPaymentModuleGatewayMerchantDetailsDAO() {
		return paymentModuleGatewayMerchantDetailsDAO;
	}
	public void setPaymentModuleGatewayMerchantDetailsDAO(
			IPaymentModuleGatewayMerchantDetailsDAO paymentModuleGatewayMerchantDetailsDAO) {
		this.paymentModuleGatewayMerchantDetailsDAO = paymentModuleGatewayMerchantDetailsDAO;
	}
	public IPaymentAmountDAO getPaymentAmountDAO() {
		return paymentAmountDAO;
	}
	public void setPaymentAmountDAO(IPaymentAmountDAO paymentAmountDAO) {
		this.paymentAmountDAO = paymentAmountDAO;
	}

	public Long getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr){
		Long amount = 0l;
		try {
			amount = paymentAmountDAO.getPaymentAmountByRegistrationType(moduleStr, subTypeStr);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPaymentAmountByRegistrationType  method in PaymentGatewayService.",e);
		}
		return amount;
	}
	
	public PaymentGatewayVO getTransactionMerchantDetailsByRegistrationType(String moduleStr,String subTypeStr){
		PaymentGatewayVO returnVo = new PaymentGatewayVO();
		
		try {
			List<PaymentGatewayVO> voList = new ArrayList<PaymentGatewayVO>(0);
			
			List<Object[]> list = paymentModuleGatewayMerchantDetailsDAO.getTransactionMerchantDetailsByRegistrationType(moduleStr, subTypeStr);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					PaymentGatewayVO vo = new PaymentGatewayVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setPostURL(obj[1] != null ? obj[1].toString():"");
					vo.setMerchantId(obj[2] != null ? obj[2].toString() : "");
					vo.setWorkingKey(obj[3] != null ? obj[3].toString():"");
					
					voList.add(vo);
				}
			}
			
			returnVo.setSubList(voList);
		} catch (Exception e) {
			LOG.error("Exception raised in getTransactionMerchantDetailsByRegistrationType  method in PaymentGatewayService.",e);
		}
		return returnVo;
	}
}
