package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CadreTxnDetails;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;


public class CadreSurveyTransactionService implements ICadreSurveyTransactionService{

	private final static Logger LOG = Logger.getLogger(CadreSurveyTransactionService.class);
	
	private ICadreTxnUserDAO cadreTxnUserDAO;
	
	private ICadreTxnDetailsDAO cadreTxnDetailsDAO;
	
	private ICadreSurveyUserDAO cadreSurveyUserDAO ;
	
	private IConstituencyDAO constituencyDAO;
	
	private TransactionTemplate transactionTemplate;
	
	private ICadreOtpDetailsDAO cadreOtpDetailsDAO;
	
	private ISmsService smsCountrySmsService;
	
	
	
	public ICadreTxnUserDAO getCadreTxnUserDAO() {
		return cadreTxnUserDAO;
	}

	public void setCadreTxnUserDAO(ICadreTxnUserDAO cadreTxnUserDAO) {
		this.cadreTxnUserDAO = cadreTxnUserDAO;
	}

	public ICadreTxnDetailsDAO getCadreTxnDetailsDAO() {
		return cadreTxnDetailsDAO;
	}

	public void setCadreTxnDetailsDAO(ICadreTxnDetailsDAO cadreTxnDetailsDAO) {
		this.cadreTxnDetailsDAO = cadreTxnDetailsDAO;
	}

	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ICadreOtpDetailsDAO getCadreOtpDetailsDAO() {
		return cadreOtpDetailsDAO;
	}

	public void setCadreOtpDetailsDAO(ICadreOtpDetailsDAO cadreOtpDetailsDAO) {
		this.cadreOtpDetailsDAO = cadreOtpDetailsDAO;
	}

	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	/*public CadreTransactionVO genarateOTPAndSaveTxnDetails(final CadreTransactionVO inputVO)
	{
		final CadreTransactionVO returnVo = new CadreTransactionVO();
		try{
			List list = cadreTxnUserDAO.checkUserExists(inputVO.getUserId(),inputVO.getMobileNo().toString().trim());
			if(list == null || list.size() == 0)
			{
				returnVo.setMessage("INVALID_USER");
				return returnVo;
			}
			else
			{
				try{
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					CadreTransactionVO assignedProblemsFromDB =  (CadreTransactionVO) transactionTemplate.execute(new TransactionCallback() 
						{
							public Object doInTransaction(TransactionStatus status) 
							{
								CadreTxnDetails cadreTxnDetails = new CadreTxnDetails();
								cadreTxnDetails.setCadreSurveyUserId(inputVO.getUserId());
								cadreTxnDetails.setConstiteuncyId(inputVO.getConstituencyId());
								cadreTxnDetails.setSinkedRecords(inputVO.getSinkedRecords());
								cadreTxnDetails.setPendingRecords(inputVO.getPendingRecords());
								cadreTxnDetails.setTotalAmount(inputVO.getTotalAmount());
								cadreTxnDetails.setPaidAmount(inputVO.getPaidAmount());
								cadreTxnDetails.setPendingAmount(inputVO.getPendingAmount());
								try {
									cadreTxnDetails.setSurveyTime(sdf.parse(inputVO.getSurveyTime()));
								} catch (ParseException e) {}
								
								cadreTxnDetails.setInsertedTime(new Date());
								cadreTxnDetails.setUpdatedTime(new Date());
								cadreTxnDetails.setCompleteStatus("N");
								cadreTxnDetails.setUniqueKey(inputVO.getUniqueKey());
								cadreTxnDetails = cadreTxnDetailsDAO.save(cadreTxnDetails);
								genarateOTP(cadreTxnDetails,inputVO,returnVo);
								returnVo.setMessage("SUCCESS");
								
								return returnVo;						
							}
						});
				}
				catch (Exception e) {
					returnVo.setMessage("Exception");
				}
				
			}
			
		}
		catch (Exception e) {
		LOG.error("Exception occured in genarateOTPAndSaveTxnDetails() in CadreSurveyTransactionService class.",e);	
		}
		return returnVo;
	}*/
	
	public String genarateOTPAndSaveTxnDetails(final CadreTransactionVO inputVO)
	{
		String statusMag = "EXCEPTION";
		final CadreTransactionVO returnVo = new CadreTransactionVO();
		try{
			
			List<String> existMobileNos = cadreTxnUserDAO.checkForExistsMobileNo(inputVO.getMobileNo().toString().trim());
			if(existMobileNos != null && existMobileNos.size() > 0)
			{
				List list = cadreTxnUserDAO.checkUserExists(inputVO.getUserId(),inputVO.getMobileNo().toString().trim());
				if(list == null || list.size() == 0)
				{
					statusMag = "INVALID_USER";
					return statusMag;
				}
				else
				{
					
					try{
						statusMag  = genarateOTP(inputVO,returnVo);
					}
					catch (Exception e) {
						
					}
					
				}
			}
			else
			{
				statusMag = "INVALID_MOBILENO";
				return statusMag;
			}
			
		}
		catch (Exception e) {
		LOG.error("Exception occured in genarateOTPAndSaveTxnDetails() in CadreSurveyTransactionService class.",e);	
		}
		return statusMag;
	}
	
	public String saveReconciliationData(ReconciliationVO inputVo)
	{
		String status = "EXCEPTION";
		try 
		{
			
			DateUtilService dateUtil = new DateUtilService();
			CadreTxnDetails cadreTxnDetails = new CadreTxnDetails();
			cadreTxnDetails.setCadreSurveyUserId(inputVo.getCadreSurveyUserId().longValue() > 0 ? inputVo.getCadreSurveyUserId() : null);
			cadreTxnDetails.setConstiteuncyId(inputVo.getConstituencyId().longValue() > 0 ? inputVo.getConstituencyId() : null);
			cadreTxnDetails.setSinkedRecords(inputVo.getSinkedRecords() > 0 ? inputVo.getSinkedRecords() : null);
			cadreTxnDetails.setPendingRecords(inputVo.getPendingRecords() > 0 ? inputVo.getPendingRecords() : null );
			cadreTxnDetails.setTotalAmount(inputVo.getTotalAmount() > 0 ? inputVo.getTotalAmount() : null);
			cadreTxnDetails.setPaidAmount(inputVo.getPaidAmount() > 0 ? inputVo.getPaidAmount()  : null);
			cadreTxnDetails.setPendingAmount(inputVo.getPendingAmount() > 0 ? inputVo.getPendingAmount() : null);
			cadreTxnDetails.setUniqueKey(inputVo.getUniqueKey() != null ? inputVo.getUniqueKey() : null);
			cadreTxnDetails.setInsertedTime(dateUtil.getCurrentDateAndTime());
			cadreTxnDetails.setMobileNo(inputVo.getMobileNo());
			cadreTxnDetails.setUpdatedTime(dateUtil.getCurrentDateAndTime());
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS);
			cadreTxnDetails.setSurveyTime(sdf.parse(inputVo.getInsertedTime()));
			CadreTxnDetails savedStatus = cadreTxnDetailsDAO.save(cadreTxnDetails);
			if(savedStatus != null)
			{
				status = "SUCCESS";
			}
			else
			{
				status = "FAILURE";
			}
		} catch (Exception e)
		{
			LOG.error("Exception occured in saveReconciliationData() in CadreSurveyTransactionService class.",e);	
		}
		return status;
	}
	
	/*public void genarateOTP(CadreTxnDetails cadreTxnDetails,CadreTransactionVO inputVo,CadreTransactionVO returnVo)
	{
		try{
			cadreOtpDetailsDAO.updateIsDeleted(inputVo.getMobileNo());
			RandomNumberGeneraion rnd = new RandomNumberGeneraion();
			int otpRand =rnd.randomGenerator(6); 
			int refRand = rnd.randomGenerator(6);
			CadreOtpDetails cadreOtpDetails = new CadreOtpDetails();
			cadreOtpDetails.setCadreTxnDetails(cadreTxnDetails);
			cadreOtpDetails.setMobileNo(inputVo.getMobileNo());
			cadreOtpDetails.setOtpNo(String.valueOf(otpRand));
			cadreOtpDetails.setOtpReferenceId(String.valueOf(refRand));
			cadreOtpDetails.setIsDeleted("N");
			cadreOtpDetails.setInsertedTime(new Date());
			cadreOtpDetails.setUpdatedTime(new Date());
			 String txnNo = rnd.randomStringOfLength(8);
			cadreOtpDetails.setTxnNumber(txnNo);
			cadreOtpDetailsDAO.save(cadreOtpDetails);
			String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" ";
			String[] phoneNumbers = {inputVo.getMobileNo().toString()};
			smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
			returnVo.setOtpNo(String.valueOf(otpRand));
			returnVo.setRefNo(String.valueOf(refRand));
			returnVo.setMobileNo(inputVo.getMobileNo());
			returnVo.setUniqueKey(inputVo.getUniqueKey());
			returnVo.setTxnNo(txnNo);
		}
		catch (Exception e) {
			LOG.error("Exception occured in genarateOTP() in CadreSurveyTransactionService class.",e);
		}
	}*/
	public String genarateOTP(CadreTransactionVO inputVo,CadreTransactionVO returnVo)
	{
		try
		{
			cadreOtpDetailsDAO.updateIsDeleted(inputVo.getMobileNo());
			RandomNumberGeneraion rnd = new RandomNumberGeneraion();
			int otpRand =rnd.randomGenerator(6); 
			int refRand = rnd.randomGenerator(6);
			String txnNo = rnd.randomStringOfLength(8);
			String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" ";
			String[] phoneNumbers = {inputVo.getMobileNo().toString()};
			smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
			returnVo.setOtpNo(String.valueOf(otpRand));
			returnVo.setRefNo(String.valueOf(refRand));
			returnVo.setMobileNo(inputVo.getMobileNo());
			returnVo.setTxnNo(txnNo);
			
			DateUtilService dateUtilService = new DateUtilService();
			CadreOtpDetails cadreOtpDetails  = new CadreOtpDetails();
			cadreOtpDetails.setOtpNo(String.valueOf(otpRand));
			cadreOtpDetails.setOtpReferenceId(String.valueOf(refRand));
			cadreOtpDetails.setTxnNumber(txnNo);
			cadreOtpDetails.setMobileNo(inputVo.getMobileNo());
			cadreOtpDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			cadreOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			cadreOtpDetails.setCadreSurveyUserId(inputVo.getUserId());
			CadreOtpDetails savedStatus = cadreOtpDetailsDAO.save(cadreOtpDetails);
			if(savedStatus != null)
			{
				returnVo.setMessage("SUCCESS");
			}
			else
			{
				returnVo.setMessage("FAILURE");
			}
			
		}
		catch (Exception e) {
			returnVo.setMessage("EXCEPTION");
			LOG.error("Exception occured in genarateOTP() in CadreSurveyTransactionService class.",e);
		}

		return returnVo.getMessage();
	}
	public String updateTxnStatus(CadreTransactionVO inputVo)
	{
		String msg = "";
		try{
			if(inputVo.getMessage().equalsIgnoreCase("success"))
			{
				cadreTxnDetailsDAO.updateCompleteStatus(inputVo.getUniqueKey(), inputVo.getConstituencyId());
				msg = "updated";
			}
			
		}
		catch (Exception e) {
			msg = "Exception";
			LOG.error("Exception occured in updateTxnStatus() in CadreSurveyTransactionService class.",e);
		}
		return msg;
	}
	/*public String validateOTPForMobile(CadreTransactionVO inputVo)
	{
		String msg ="";
		try{
			String otpNo = cadreOtpDetailsDAO.checkOTPValid(inputVo.getMobileNo().toString().trim(),inputVo.getOtpNo().toString().trim(),inputVo.getRefNo().toString().trim());
			if(otpNo != null)
				msg = "success";
			else
				msg = "failure";
		}
		catch (Exception e) {
			LOG.error("Exception occured in validateOTPForMobile() in CadreSurveyTransactionService class.",e);
			msg = "EXCEPTION";
		}
		return msg;
		
	}*/
	
	public String validateOTPForMobile(CadreTransactionVO inputVo)
	{
		String msg ="";
		try{
			List<String> checkOtps =cadreOtpDetailsDAO.checkOTP(inputVo.getOtpNo().toString().trim());
			if(checkOtps != null && checkOtps.size() > 0)
			{
				List<String> mobilesCheck = cadreOtpDetailsDAO.checkForMobile(inputVo.getMobileNo().toString().trim());
				if(mobilesCheck != null && mobilesCheck.size() > 0)
				{
					List<String> otpNos = cadreOtpDetailsDAO.checkOTPValid(inputVo.getMobileNo().toString().trim(),inputVo.getOtpNo().toString().trim(),inputVo.getUserId());
					if(otpNos != null && otpNos.size() > 0)
					{
						String otpNo = otpNos.get(0);
						msg = "SUCCESS";
					}
					else
					{
						msg = "INVALID_USER";
					}
				}
				else
				{
					msg = "INVALID_MOBILENO";
				}
				
			}
			else
			{
				msg = "INVALID_OTP";
			}
			
				
		}
		catch (Exception e) {
			LOG.error("Exception occured in validateOTPForMobile() in CadreSurveyTransactionService class.",e);
			msg = "EXCEPTION";
		}
		return msg;
		
	}
	
	
	public String updatePendingAmount(CadreTransactionVO inputVo)
	{
		String msg = "";
		try{
		 Long pendingAmount = cadreTxnDetailsDAO.getPendingAmountForUser(inputVo.getUniqueKey(),inputVo.getConstituencyId(),inputVo.getUserId());
		 if(pendingAmount == null)
			 return "notexists";
		 if(pendingAmount < inputVo.getPendingAmount())
			msg = "Invalid";
		 else
		 {
			Long amount =  pendingAmount - inputVo.getPendingAmount();
			cadreTxnDetailsDAO.updatePendingAmount(amount,inputVo.getUniqueKey(),inputVo.getConstituencyId(),inputVo.getUserId());
			 msg ="updated";
		 }
		
		}
		catch (Exception e) {
			LOG.error("Exception occured in updatePendingAmount() in CadreSurveyTransactionService class.",e);
			msg = "EXCEPTION";
		}
		return msg;
	}
	
}
