package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.zip.Adler32;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IPaymentAmountDAO;
import com.itgrids.partyanalyst.dao.IPaymentMethodDAO;
import com.itgrids.partyanalyst.dao.IPaymentModuleGatewayMerchantDetailsDAO;
import com.itgrids.partyanalyst.dao.IPaymentTransactionDAO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.PaymentTransactionVO;
import com.itgrids.partyanalyst.model.PaymentMethod;
import com.itgrids.partyanalyst.model.PaymentTransaction;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.MD5Algoritm;

public class PaymentGatewayService implements IPaymentGatewayService{
	
	private static final Logger LOG = Logger.getLogger(PaymentGatewayService.class);
	
	private IPaymentMethodDAO paymentMethodDAO;
	private IPaymentTransactionDAO paymentTransactionDAO;
	private MD5Algoritm md5Algoritm = new MD5Algoritm();
	private TransactionTemplate transactionTemplate;
	private IPaymentAmountDAO paymentAmountDAO;
	private IPaymentModuleGatewayMerchantDetailsDAO paymentModuleGatewayMerchantDetailsDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	
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
	
	
	/* (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IPaymentGatewayService#getPaymentAmountByRegistrationType(java.lang.String, java.lang.String)
	 */
	public PaymentGatewayVO getPaymentAmountByRegistrationType(String moduleStr,String subTypeStr){
		PaymentGatewayVO paymentGatewayVO = new PaymentGatewayVO();
		try {
			Long amount = paymentAmountDAO.getPaymentAmountByRegistrationType(moduleStr, subTypeStr);
			List<Object[]> paymemtGatwayDtls = paymentModuleGatewayMerchantDetailsDAO.getTransactionMerchantDetailsByRegistrationType(moduleStr, subTypeStr);
			UUID uuidStr = UUID.randomUUID();
			 if(paymemtGatwayDtls != null && paymemtGatwayDtls.size()>0){
				 for (Object[] param : paymemtGatwayDtls) {
					 paymentGatewayVO.setAmount(amount.toString());
					 paymentGatewayVO.setPreURL(commonMethodsUtilService.getStringValueForObject(param[4]));
					 paymentGatewayVO.setPostURL(commonMethodsUtilService.getStringValueForObject(param[5]));
					 paymentGatewayVO.setRedirectURL(commonMethodsUtilService.getStringValueForObject(param[6]));
					 paymentGatewayVO.setId(commonMethodsUtilService.getLongValueForObject(param[7]));
					 paymentGatewayVO.setGatewayId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 paymentGatewayVO.setUuid(String.valueOf(uuidStr).substring(0, 33));
				}
			 }
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPaymentAmountByRegistrationType  method in PaymentGatewayService.",e);
		}
		return paymentGatewayVO;
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
					vo.setPostURL(obj[5] != null ? obj[5].toString():"");
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

	public PaymentGatewayVO getPaymentBasicInfoByPaymentGateWayType(Long gateWayId,String randomNo,String enrollId,String moduleStr,String subTypeStr){
		PaymentGatewayVO pamentGatewayVO = new PaymentGatewayVO();
		try {
				String encryptedCodeMN = randomNo;//md5Algoritm.generateMD5Encrypt(randomNo);
				String encryptedCodeEN = enrollId;//md5Algoritm.generateMD5Encrypt(enrollId);
				List<Object[]> paymemtGatwayDtls = paymentModuleGatewayMerchantDetailsDAO.getTransactionMerchantDetailsByRegistrationType(moduleStr, subTypeStr);
				
				String WorkingKey ="";//IConstants.TGNF_ENROLLMENT_WORKING_KEY;
		        String MerchantId ="";//IConstants.TGNF_ENROLLMENT_MERCHANT_ID;
		        String Amount = paymentAmountDAO.getPaymentAmountByRegistrationType(moduleStr, subTypeStr).toString();//IConstants.TGNF_ENROLLMENT_AMOUNT;
		        String redirectUrl = "";//IConstants.TGNF_REGISTRATION_REDIRECTURL
		        		
				if(paymemtGatwayDtls != null && paymemtGatwayDtls.size()>0)
					 for (Object[] param : paymemtGatwayDtls) {
						 redirectUrl = commonMethodsUtilService.getStringValueForObject(param[6]);
						 WorkingKey =commonMethodsUtilService.getStringValueForObject(param[3]);
						 MerchantId=commonMethodsUtilService.getStringValueForObject(param[2]);
					}
				
				redirectUrl = redirectUrl+"?mn="+encryptedCodeMN.trim()+"&en="+encryptedCodeEN.trim()+"";
				Random random = new Random();
				Long randomNum = 0L;
				do{
					randomNum = random.nextLong();
				}while(randomNum<0 && randomNum.toString().length()<7);
				randomNum = Long.valueOf(randomNum.toString().substring(1, 8));
		        String OrderId =IConstants.TGNF_ENROLLMENT_RANDOMNUMBERCODE+randomNum;
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
						paymentTransaction.setUuid(String.valueOf(UUID.randomUUID()));
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
