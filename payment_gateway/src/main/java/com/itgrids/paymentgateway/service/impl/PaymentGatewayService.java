package com.itgrids.paymentgateway.service.impl;

import java.util.List;
import java.util.zip.Adler32;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.paymentgateway.dao.IPaymentMethodDAO;
import com.itgrids.paymentgateway.dao.IPaymentTransactionDAO;
import com.itgrids.paymentgateway.dto.PamentGatewayVO;
import com.itgrids.paymentgateway.dto.PaymentTransactionVO;
import com.itgrids.paymentgateway.model.PaymentMethod;
import com.itgrids.paymentgateway.model.PaymentTransaction;
import com.itgrids.paymentgateway.service.IPaymentGatewayService;
import com.itgrids.paymentgateway.utils.IConstants;
import com.itgrids.paymentgateway.utils.MD5Algoritm;

public class PaymentGatewayService implements IPaymentGatewayService{
	
	private static final Logger LOG = Logger.getLogger(PaymentGatewayService.class);
	
	private IPaymentMethodDAO paymentMethodDAO;
	private IPaymentTransactionDAO paymentTransactionDAO;
	private MD5Algoritm md5Algoritm = new MD5Algoritm();
	private TransactionTemplate transactionTemplate;
		
	public IPaymentMethodDAO getPaymentMethodDAO() {
		return paymentMethodDAO;
	}

	public IPaymentTransactionDAO getPaymentTransactionDAO() {
		return paymentTransactionDAO;
	}

	public void setPaymentTransactionDAO(
			IPaymentTransactionDAO paymentTransactionDAO) {
		this.paymentTransactionDAO = paymentTransactionDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setPaymentMethodDAO(IPaymentMethodDAO paymentMethodDAO) {
		this.paymentMethodDAO = paymentMethodDAO;
	}
	
	public PamentGatewayVO getPaymentBasicInfoByPaymentGateWayType(Long gateWayId,String randomNo,String enrollId){
		PamentGatewayVO pamentGatewayVO = new PamentGatewayVO();
		try {
				String encryptedCodeMN = md5Algoritm.generateMD5Encrypt(randomNo);
				String encryptedCodeEN = md5Algoritm.generateMD5Encrypt(enrollId);
				
				String WorkingKey =IConstants.TGNF_ENROLLMENT_WORKING_KEY;
		        String MerchantId =IConstants.TGNF_ENROLLMENT_MERCHANT_ID;
		        String Amount = IConstants.TGNF_ENROLLMENT_AMOUNT;
		        String redirectUrl = IConstants.TGNF_REGISTRATION_REDIRECTURL+"?mn="+encryptedCodeMN.trim()+"&en="+encryptedCodeEN.trim()+"";
		        String OrderId =IConstants.TGNF_ENROLLMENT_RANDOMNUMBERCODE+randomNo;
		        String str = MerchantId + "|" + OrderId + "|" + Amount + "|" + redirectUrl + "|" + WorkingKey;
		        Adler32  adl = new Adler32();
		        adl.update(str.getBytes());
		        //return (Long.valueOf(adl.getValue()).toString());

		        pamentGatewayVO.setCheckSum(Long.valueOf(adl.getValue()).toString());
		        pamentGatewayVO.setWorkingKey(WorkingKey);
		        pamentGatewayVO.setAmount(Amount);
		        pamentGatewayVO.setMerchantId(MerchantId);
		        pamentGatewayVO.setRedirectURL(redirectUrl);
		        pamentGatewayVO.setOrderNo(OrderId);
		        
		} catch (Exception e) {
			LOG.error("error occured while generating payment gateway basic details.");
		}
		return pamentGatewayVO;
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
	
	public Long addPaymentMethod(String method)
	{
		try{
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setMethod(method);
			paymentMethod = paymentMethodDAO.save(paymentMethod);
			return paymentMethod.getPaymentMethodId();
		}catch(Exception e)
		{
			LOG.error(e);
			return null;
		}
	}
	public String savePaymenyTransactionDetails(final PaymentTransactionVO paymentTransactionVO){
		try{
			if(paymentTransactionVO != null)
			{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						PaymentTransaction paymentTransaction = new PaymentTransaction();
						paymentTransaction.setPaymentModuleGatewayMerchantDetailsId(paymentTransactionVO.getPaymentModuleGatewayMerchantDetailsId());
						paymentTransaction.setPaymentGatewayId(paymentTransactionVO.getPaymentGatewayId());
						paymentTransaction.setPaymentMethodId(paymentTransactionVO.getPaymentMethodId());
						paymentTransaction.setTransactionId(paymentTransactionVO.getTransactionId());
						paymentTransaction.setTransactionStatusId(paymentTransactionVO.getTransactionStatusId());
						paymentTransaction.setTransactionTime(paymentTransactionVO.getTransactionTime());
						paymentTransaction.setUuid(paymentTransactionVO.getUuid());
						paymentTransaction.setAmount(paymentTransactionVO.getAmount());
						paymentTransaction.setIpAddress(paymentTransactionVO.getIpAddress());
						paymentTransaction.setStatusCode(paymentTransactionVO.getStatusCode());
						paymentTransaction.setPreUrl(paymentTransactionVO.getPreUrl());
						paymentTransaction.setPostUrl(paymentTransactionVO.getPostUrl());
						paymentTransaction.setRedirectUrl(paymentTransactionVO.getRedirectUrl());
						paymentTransaction.setReferenceUserId(paymentTransactionVO.getReferenceUserId());
						paymentTransaction.setPaymentModuleId(paymentTransactionVO.getPaymentModuleId());
						paymentTransactionDAO.save(paymentTransaction);
					}});
				return "success";
			}
		}catch(Exception e){
			LOG.error("error occured while saving payment transaction details.");
		}
		return "failure";
	}
}
