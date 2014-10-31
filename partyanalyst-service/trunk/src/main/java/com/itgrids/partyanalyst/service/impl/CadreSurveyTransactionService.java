package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
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
	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;	
	
	
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

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
			cadreTxnDetails.setSinkedRecords(inputVo.getSinkedRecords() > 0 ? inputVo.getSinkedRecords() : 0);
			cadreTxnDetails.setPendingRecords(inputVo.getPendingRecords() > 0 ? inputVo.getPendingRecords() : 0 );
			cadreTxnDetails.setTotalAmount(inputVo.getTotalAmount() > 0 ? inputVo.getTotalAmount() : 0);
			cadreTxnDetails.setPaidAmount(inputVo.getPaidAmount() > 0 ? inputVo.getPaidAmount()  : 0);
			cadreTxnDetails.setPendingAmount(inputVo.getPendingAmount() > 0 ? inputVo.getPendingAmount() : 0);
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
			cadreOtpDetails.setIsDeleted("N");
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
	
	

	
	public SurveyTransactionVO getCadreSurveyTransactionDetails()
	{
		LOG.info("entered into getCadreSurveyTransactionDetails() in CadreSurveyTransactionService service class. ");
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			
			cal.add(Calendar.DATE, -1);
			Date yesterDay = cal.getTime();
			
			Long totalTeamCount = cadreSurveyUserDAO.getTotalCadreSurveyDetails();
			List<Long> teamIds = cadreTxnDetailsDAO.getTotalCadreSurveyTxnTeamSize(yesterDay);
			
			Long workingTeamSize = teamIds != null && teamIds.size() >0 ? Long.valueOf(String.valueOf(teamIds.size())):0L;
			
			returnVO.setTeamSize(workingTeamSize);
			returnVO.setIdleTeamSize( totalTeamCount - workingTeamSize );
			
			List<Object[]> yesterTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(null,yesterDay);
			
			if(yesterTransactions != null && yesterTransactions.size()>0)
			{
				for (Object[] transaction : yesterTransactions)
				{
					Long paidAmount = transaction[0] != null ?  Long.valueOf(transaction[0].toString()):0L;
					Long pendingAmount = transaction[1] != null ? Long.valueOf(transaction[1].toString()) :0L;
					Long totalAmount = transaction[2] != null ? Long.valueOf(transaction[2].toString()) :0L;
					
					Long teamCount = transaction[3] != null ? Long.valueOf(transaction[3].toString()) :0L;
					
					returnVO.setSubmittedCount(paidAmount );
					returnVO.setNotSubmittedCount(pendingAmount );					
					returnVO.setYesterDayCount(teamCount);
					
					returnVO.setDepositedAmount(paidAmount);
					returnVO.setRemainingAmount(pendingAmount);
					returnVO.setActualAmount(totalAmount);
				}
			}
			
			cal.add(Calendar.DATE, -7); 
			Date oneWeekBackDate = cal.getTime();
			
			List<Object[]> thisWeekTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,oneWeekBackDate);
			
			if(thisWeekTransactions != null && thisWeekTransactions.size()>0)
			{
				for (Object[] transactions : thisWeekTransactions )
				{
					Long totalWeekAmount = transactions[3] != null ? Long.valueOf(transactions[3].toString()) :0L;
					returnVO.setWeekCount(totalWeekAmount);
				}
			}
			
			List<Object[]> totalTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,null);
			
			if(totalTransactions != null && totalTransactions.size()>0)
			{
				for (Object[] totalTransactins : totalTransactions )
				{
					Long totalTransactionsCount = totalTransactins[3] != null ? Long.valueOf(totalTransactins[3].toString()) :0L;
					returnVO.setRecordsCount( totalTransactionsCount);
				}
			}
			
			List<Object[]> yesterDayOtpInfo = cadreOtpDetailsDAO.getOtpDetailsForDates(null,yesterDay);
			Map<String,Long> transactionOtpMap = new TreeMap<String,Long>();
			transactionOtpMap.put("Y", 0L);
			transactionOtpMap.put("N", 0L);
			
			if(yesterDayOtpInfo != null && yesterDayOtpInfo.size()>0)
			{
				for (Object[] otp : yesterDayOtpInfo )
				{
					if(transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"") != null)
					{
						Long count = transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"");
						count = count + ((otp[4] != null ? Long.valueOf(otp[4].toString().trim()):0L) ) ;
						
						transactionOtpMap.put(otp[0] != null ? otp[0].toString().trim():"", count != 0L ? count:0L );
					}
				}
			}
			
			if(transactionOtpMap != null && transactionOtpMap.size()>0)
			{
				for( String status : transactionOtpMap.keySet()) 
				{
					if(status != null && status.trim().length()>0)
					{
						if(status.trim().equalsIgnoreCase("Y"))
						{
							returnVO.setOtpConfirmCount(transactionOtpMap.get(status.trim()));
						}
						if(status.trim().equalsIgnoreCase("N"))
						{
							returnVO.setRemainingOTPCount(transactionOtpMap.get(status.trim()));
						}
					}
				}
			}
			
			Long totalOTPGeneratedCount = returnVO.getOtpConfirmCount() + returnVO.getRemainingOTPCount();
			returnVO.setOtpRequestCount(totalOTPGeneratedCount);
			
			List<Object[]> otpNotRequestedTxnsDetailsList = cadreOtpDetailsDAO.getOfflineTxnDetailsIdsForDates(null,yesterDay);
			
			Map<String,Long> transactionNONOtpMap = new TreeMap<String,Long>();
			transactionNONOtpMap.put("Y", 0L);
			transactionNONOtpMap.put("N", 0L);
			
			if(otpNotRequestedTxnsDetailsList != null && otpNotRequestedTxnsDetailsList.size()>0)
			{
				Long totalRecords = 0L;
				for (Object[] txnInfo : otpNotRequestedTxnsDetailsList)
				{
					if(transactionNONOtpMap.get(txnInfo[0] != null ? txnInfo[0].toString().trim():"") != null)
					{
						Long count = transactionNONOtpMap.get(txnInfo[0] != null ? txnInfo[0].toString().trim():"");
						count = count+((txnInfo[2] != null ? Long.valueOf(txnInfo[2].toString().trim()):0L));
						totalRecords = totalRecords + count;
						
						transactionNONOtpMap.put(txnInfo[0] != null ? txnInfo[0].toString().trim():"",count != 0L ? count:0L );
					}
				}
				
				returnVO.setOtpNonRequestCount(totalRecords != 0L ? totalRecords / 100 :0L );
			}
			
			if(transactionNONOtpMap != null && transactionNONOtpMap.size()>0)
			{
				for( String txnStatus : transactionNONOtpMap.keySet()) 
				{
					if(txnStatus != null && txnStatus.trim().length()>0)
					{
						if(txnStatus.trim().equalsIgnoreCase("Y"))
						{
							returnVO.setNonOTPConfirmCount(transactionOtpMap.get(txnStatus.trim()));
						}
						if(txnStatus.trim().equalsIgnoreCase("N"))
						{
							returnVO.setRemainingNonOTPCount(transactionOtpMap.get(txnStatus.trim()));
						}
					}
				}
			}
			
			Long notPaidRecords = returnVO.getYesterDayCount() - (returnVO.getOtpConfirmCount() + returnVO.getNonOTPConfirmCount());
			
			returnVO.setPendingCount(notPaidRecords);
			
		} catch (Exception e) {
			LOG.error(" exception occured at getCadreSurveyTransactionDetails() in CadreSurveyTransactionService service class. ", e);
		}
		
		return returnVO;
	}
	
	
	public List<SelectOptionVO> getParliamentsForState(String electionType,Long stateTypeId)
	{
		List<SelectOptionVO> constituencyVOList = null;
		try {
			List<Object[]> PconstituencyList = null;
			if(electionType.equalsIgnoreCase("Parliament"))
			{
				if(stateTypeId == 0)
				{
					PconstituencyList = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
				}
				else
				{
					PconstituencyList= delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);
				}
			}
		
			if(PconstituencyList != null && PconstituencyList.size()>0)
			{
				constituencyVOList = new ArrayList<SelectOptionVO>();
				for (Object[] param : PconstituencyList)
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					vo.setName(param[1] != null ? param[1].toString():"");					
					constituencyVOList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getParliamentsForState() in CadreSurveyTransactionService service class. ", e);
		}
		return constituencyVOList;
	}
	
	public SurveyTransactionVO getDayWiseReportByDates(String fromDateStr , String toDateStr)
	{
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		List<SurveyTransactionVO> returnList = null;
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fromDate = format.parse(fromDateStr);
			Date toDate = format.parse(toDateStr);
			List<Object[]> daywiseDetailsList = cadreOtpDetailsDAO.getDayWiseReportForDates(fromDate,toDate);
			
			if(daywiseDetailsList != null && daywiseDetailsList.size()>0)
			{
				returnList = new ArrayList<SurveyTransactionVO>();
				
				for (Object[] transaction : daywiseDetailsList) 
				{
					SurveyTransactionVO vo = new SurveyTransactionVO();

					vo.setSurveyDate(transaction[0] != null ? transaction[0].toString().substring(0, 10):"");
					vo.setTeamSize(transaction[1] != null ? Long.valueOf(transaction[1].toString()):0L);
					vo.setSubmittedCount(transaction[2] != null ? Long.valueOf(transaction[2].toString()):0L);
					vo.setOtpRequestCount(transaction[3] != null ? Long.valueOf(transaction[3].toString()):0L);
					vo.setDepositedAmount(transaction[4] != null ? Long.valueOf(transaction[4].toString()):0L);
					
					returnList.add(vo);
				}
				
				returnVO.setSurveyTransactionVOList(returnList);
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getDayWiseReportByDates() in CadreSurveyTransactionService service class. ", e);
		}
		
		return returnVO;
	}
	
	public SurveyTransactionVO getLocationWiseTransactionDetails(String fromDateStr , String toDateStr, String searchType, List<Long> locationIdList)
	{
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			Date fromDate = format.parse(fromDateStr);
			Date toDate = format.parse(toDateStr);
			StringBuilder query = new StringBuilder();
			
			if(searchType.equalsIgnoreCase("state"))
			{
				Long stateId = (Long) locationIdList.get(0);
				locationIdList.clear();
				if(stateId.longValue() == 1L)
				{
					locationIdList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(1L);
					query.append(" and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList) ");
				}
				else if(stateId.longValue() == 2L)
				{
					locationIdList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(2L);
					query.append(" and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList)  ");
				}
				else
				{
					locationIdList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(0L);
					query.append(" and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList)");
				
				}
			}
			else if(searchType.equalsIgnoreCase("district"))
			{
				query.append(" and model.cadreTxnDetails.constituency.district.districtId in (:locationIdList) ");
			}
			else if(searchType.equalsIgnoreCase("assembly"))
			{
				query.append(" and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList) ");
			}
			else if(searchType.equalsIgnoreCase("parliament"))
			{
				List<Long> assembblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIdList);
				
				locationIdList.clear();
				locationIdList.addAll(assembblyIds);
				
				query.append(" and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList) ");
			}
			
			List<Object[]> locationWiseTransactions = cadreOtpDetailsDAO.getLocationWiseTransactionsByDates(fromDate,toDate,locationIdList,query.toString());
			
			if(locationWiseTransactions != null && locationWiseTransactions.size()>0)
			{
				List<SurveyTransactionVO> returnList = new ArrayList<SurveyTransactionVO>();
				
				for (Object[] transction : locationWiseTransactions) 
				{
					SurveyTransactionVO vo = new SurveyTransactionVO();
					vo.setId(transction[0] != null ? Long.valueOf(transction[0].toString().trim()):0L);
					vo.setName(transction[1] != null ? transction[1].toString().trim():"");
					vo.setLocationId(transction[2] != null ? Long.valueOf(transction[2].toString().trim()):0L);
					vo.setLocationName(transction[3] != null ? transction[3].toString().trim():"");
					vo.setSubmittedCount(transction[4] != null ? Long.valueOf(transction[4].toString().trim()):0L);
					vo.setRecordsCount(transction[5] != null ? Long.valueOf(transction[5].toString().trim()):0L);
					
					returnList.add(vo);
				}
				returnVO.setSurveyTransactionVOList(returnList);
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getDayWiseReportByDates() in CadreSurveyTransactionService service class. ", e);
		}
		
		return returnVO;
	}

}
