package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPartyPresidentsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionReportVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CadreTxnDetails;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
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
	
	private IDistrictDAO districtDAO;
	
	private ITdpCadreDAO tdpCadreDAO;
	
	private IBoothDAO boothDAO;
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	private DateUtilService dateService = new DateUtilService();
	
	private IPartyPresidentsDAO partyPresidentsDAO;
	
	
	public void setPartyPresidentsDAO(IPartyPresidentsDAO partyPresidentsDAO) {
		this.partyPresidentsDAO = partyPresidentsDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

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
					statusMag  = genarateOTP(inputVO,returnVo);
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
			cadreTxnDetails.setAgentMobileNo(inputVo.getAgentMobileNo());
			cadreTxnDetails.setAgentName(inputVo.getAgentName());
			cadreTxnDetails.setAgentReconConstyName(inputVo.getAgentReconConstyName());
			cadreTxnDetails.setAgentVillage(inputVo.getAgentVillage());
			
			if(inputVo.getOtpNumber() != null && inputVo.getOtpNumber().trim().length() > 0)
			{
				List<Long> cadreOtpDetails = cadreOtpDetailsDAO.getCadreotpDateils(inputVo.getOtpNumber().trim());
				if(cadreOtpDetails != null && cadreOtpDetails.size() > 0)
				{
					cadreTxnDetails.setCadreOtpDetailsId(cadreOtpDetails.get(0));
				}
				
			}
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
	
/*	public String saveReconsilationFailedDetails(ReconciliationFailureVO inputVo)
	{
		
		String msg = "EXCEPTION";
		try 
		{
			ReconciliationFailureDetails reconciliationFailureDetails = new ReconciliationFailureDetails();
			reconciliationFailureDetails.setAgentMobile(inputVo.getAgentMobile());
			reconciliationFailureDetails.setCadreAmount(inputVo.getCadreAmount());
			reconciliationFailureDetails.setCadreConstituency(inputVo.getCadreConstituency());
			reconciliationFailureDetails.setCadreName(inputVo.getCadreName());
			reconciliationFailureDetails.setCadreVillage(inputVo.getCadreVillage());
			reconciliationFailureDetails.setUserId(inputVo.getUserId());
			ReconciliationFailureDetails successStatus = reconciliationFailureDetailsDAO.save(reconciliationFailureDetails);
			if(successStatus != null)
			{
				msg = "SUCCESS";
			}
			else
			{
				msg = "FAILURE";
			}
		} catch (Exception e) {
			LOG.error("Exception occured in saveReconsilationFailedDetails() in CadreSurveyTransactionService class.",e);
		}
		return msg;
	}*/
	
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
	
	

	
	public SurveyTransactionVO getCadreSurveyTransactionDetails(String searchDate)
	{
		LOG.info("entered into getCadreSurveyTransactionDetails() in CadreSurveyTransactionService service class. ");
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date today = null;
			
			if(searchDate == null)
			{
				today = new Date();
			}
			else
			{
				today = format.parse(searchDate);
			}
			
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
					
					returnVO.setSubmittedCount(paidAmount /100);
					returnVO.setNotSubmittedCount(pendingAmount/100 );					
					returnVO.setYesterDayCount(totalAmount/100);
					
					returnVO.setDepositedAmount(paidAmount);
					returnVO.setRemainingAmount(pendingAmount);
					returnVO.setActualAmount(totalAmount);
				}
			}
			
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -7); 
			Date oneWeekBackDate = cal1.getTime();
			
			List<Object[]> thisWeekTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,oneWeekBackDate);
			
			if(thisWeekTransactions != null && thisWeekTransactions.size()>0)
			{
				for (Object[] transactions : thisWeekTransactions )
				{
					Long totalWeekAmount = transactions[2] != null ? Long.valueOf(transactions[2].toString()) :0L;
					returnVO.setWeekCount(totalWeekAmount / 100);
				}
			}
			
			List<Object[]> totalTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,null);
			
			if(totalTransactions != null && totalTransactions.size()>0)
			{
				for (Object[] totalTransactins : totalTransactions )
				{
					Long totalTransactionsCount = totalTransactins[2] != null ? Long.valueOf(totalTransactins[2].toString()) :0L;
					returnVO.setRecordsCount( totalTransactionsCount/100);
				}
			}
			
			
			List<Object[]> yesterDayOtpInfo = cadreOtpDetailsDAO.getOtpDetailsForDates(null,yesterDay);
			Map<String,Long> transactionOtpMap = new TreeMap<String,Long>();
			transactionOtpMap.put("Y", 0L);
			transactionOtpMap.put("N", 0L);
			Long pendingAmount = 0L;
			if(yesterDayOtpInfo != null && yesterDayOtpInfo.size()>0)
			{
				for (Object[] otp : yesterDayOtpInfo )
				{
					if(transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"") != null)
					{
						Long count = transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"");
						count = count + ((otp[1] != null ? Long.valueOf(otp[1].toString().trim()):0L) ) ;
						pendingAmount = otp[2] != null ? Long.valueOf(otp[2].toString().trim()):0L;
						
						transactionOtpMap.put("Y", count != 0L ? count/100:0L );
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
							returnVO.setRemainingOTPCount(pendingAmount/100);
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
						count = count+((txnInfo[1] != null ? Long.valueOf(txnInfo[1].toString().trim()):0L));
						totalRecords = totalRecords + count;
						
						transactionNONOtpMap.put(txnInfo[0] != null ? txnInfo[0].toString().trim():"",count != 0L ? count/100:0L );
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
			
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -2);
			Date beforeYesterDay = cal2.getTime();
			
			Long beforeYesterDayUsers = cadreTxnDetailsDAO.getUsersCount(beforeYesterDay,null);
			
			returnVO.setRemainingNonOTPCount(beforeYesterDayUsers != null ? beforeYesterDayUsers : 0l);// day before yesterday team count
			
		} catch (Exception e) {
			LOG.error(" exception occured at getCadreSurveyTransactionDetails() in CadreSurveyTransactionService service class. ", e);
		}
		
		return returnVO;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getParliamentsForState(String searchType,Long stateTypeId)
	{
 		List<SelectOptionVO> constituencyVOList = null;
		try {
			List<Object[]> locationsList = null;
			StringBuilder queryStr = new StringBuilder();
			
			if(searchType.equalsIgnoreCase("district"))
			{
				if(stateTypeId == 1L)
				{
					locationsList = districtDAO.getDistrictIdAndNameByStateForRegion(1L,"Andhra Pradesh");
				}
				else if(stateTypeId == 2L)
				{
					locationsList = districtDAO.getDistrictIdAndNameByStateForRegion(1L,"Telangana");
				}
				else
				{
					locationsList = districtDAO.getDistrictIdAndNameByStateForRegion(1L,"All");
				}
				
				queryStr.append("  select distinct model.constituency.district.districtId , model.constituency.district.districtName  from CadreTxnDetails model where model.constituency.district.districtId in (:locationIds) order by model.constituency.district.districtName asc  ");
			}
			if(searchType.equalsIgnoreCase("assembly"))
			{
				queryStr.append(" select distinct model.constituency.constituencyId , model.constituency.name  from CadreTxnDetails model where model.constituency.constituencyId in (:locationIds) order by model.constituency.name asc ");
				if(stateTypeId == 1L)
				{
					locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
				}
				else if(stateTypeId == 2L)
				{
					locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
				}
				else
				{
					locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
				}
			}
			if(searchType.equalsIgnoreCase("Parliament"))
			{
				queryStr.append(" select distinct model.constituency.constituencyId , model.constituency.name  from CadreTxnDetails model where model.constituency.constituencyId in (:locationIds) order by model.constituency.name asc  ");
				
				if(stateTypeId == 0L )
				{
					locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
				}
				else
				{
					locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);
				}
			}
			/*	
			List<Long> locationIds = new ArrayList<Long>();
			if(locationsList != null && locationsList.size()>0)
			{
				for (Object[] param : locationsList)
				{
					locationIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
				}
			}
			
			if(searchType.equalsIgnoreCase("Parliament"))
			{				
				
				locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIds);
			
				locationIds.clear();
				
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						locationIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					}
				}
				
				locationsList = cadreTxnDetailsDAO.findLocationDetailsByAssemblyIds(locationIds,queryStr.toString());
				locationIds.clear();
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						locationIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					}
				}
				
				locationsList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsForAssembly(locationIds);
			}
			else
			{
				locationsList = cadreTxnDetailsDAO.findLocationDetailsByAssemblyIds(locationIds,queryStr.toString());
			}
*/
			
			if(locationsList != null && locationsList.size()>0)
			{
				constituencyVOList = new ArrayList<SelectOptionVO>();
				for (Object[] param : locationsList)
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
		SimpleDateFormat  format1 = new SimpleDateFormat("dd-MM-yyyy");
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

					vo.setSurveyDate(transaction[0] != null ? format1.format(format.parse(transaction[0].toString().substring(0, 10))):"");
					vo.setTeamSize(transaction[1] != null ? Long.valueOf(transaction[1].toString()):0L);
					vo.setSubmittedCount(transaction[2] != null ? Long.valueOf(transaction[2].toString()) != 0L ? Long.valueOf(transaction[2].toString()) /100:0L :0L);
					vo.setActualAmount(transaction[2] != null ? Long.valueOf(transaction[2].toString()):0L);
					vo.setDepositedAmount(transaction[3] != null ? Long.valueOf(transaction[3].toString()):0L);
					
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
					query.append(" and C.constituency_id in (:locationIdList) ");
				}
				else if(stateId.longValue() == 2L)
				{
					locationIdList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(2L);
					query.append(" and C.constituency_id in (:locationIdList)  ");
				}
				else
				{
					locationIdList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(0L);
					query.append(" and C.constituency_id in (:locationIdList) ");
				
				}
			}
			else if(searchType.equalsIgnoreCase("district"))
			{
				query.append(" and C.district_id in (:locationIdList) ");
			}
			else if(searchType.equalsIgnoreCase("assembly"))
			{
				query.append(" and C.constituency_id in (:locationIdList) ");
			}
			else if(searchType.equalsIgnoreCase("parliament"))
			{
				List<Long> assembblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIdList);
				
				locationIdList.clear();
				locationIdList.addAll(assembblyIds);
				
				query.append(" and C.constituency_id in (:locationIdList) ");
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
					vo.setTotalCount(transction[4] != null ? Long.valueOf(transction[4].toString().trim()):0L);					
					vo.setSubmittedCount(transction[5] != null ? Long.valueOf(transction[5].toString().trim()):0L);					
					vo.setRecordsCount(transction[6] != null ? Long.valueOf(transction[6].toString().trim()) != 0L? Long.valueOf(transction[6].toString().trim()) /100:0L:0L);
					vo.setOtpConfirmCount(transction[7] != null ? Long.valueOf(transction[7].toString().trim()) != 0L? Long.valueOf(transction[7].toString().trim()) /100:0L:0L);
					vo.setDepositedAmount(transction[7] != null ? Long.valueOf(transction[7].toString().trim()):0L);
					
					returnList.add(vo);
				}
				returnVO.setSurveyTransactionVOList(returnList);
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getDayWiseReportByDates() in CadreSurveyTransactionService service class. ", e);
		}
		
		return returnVO;
	}
	
	public SurveyTransactionReportVO getBasicTransactionDetails()
	{
		SurveyTransactionReportVO returnVo = new SurveyTransactionReportVO();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.DATE, -1);
			Date yesterDay = cal.getTime();
			cal.setTime(yesterDay);
			cal.add(Calendar.DATE, -1);
			Date beforeYesterDay = cal.getTime();
			// Total Transactions	
			List<Object[]> list = cadreTxnDetailsDAO.getTransactionDetailsByDates(today, null);
			if(list !=null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					returnVo.setTotalRecords((Long)params[3] != null ? (Long)params[3] : 0l);
				}
				
			}
			// yesterday Transactions 
			List<Object[]> todayRecords = cadreTxnDetailsDAO.getTransactionDetailsByDates(null, yesterDay);
			if(todayRecords != null && todayRecords.size() > 0)
			{
				for(Object[] params : todayRecords)
				{
					returnVo.setTodayRecords((Long)params[3] != null ? (Long)params[3] : 0l);
				}
			}
			List<Object[]> list1 = cadreTxnDetailsDAO.getCompletedTransactionDetailsByDates(null, yesterDay);
				if(list1 !=null && list1.size() > 0)
				  {
							for(Object[] params : list1)
							{
								
								returnVo.setTodayMoneyCollected((Long)params[2] != null ? (Long)params[2] :0l);
								returnVo.setTodayPaidAmount((Long)params[0] != null ? (Long)params[0] : 0l);
								returnVo.setTodayPendingAmount((Long)params[1] !=null ? (Long)params[1] : 0l);
								returnVo.setTodayotpTransactionCompleted((Long)params[3] != null ? (Long)params[3] : 0l);
								
							}
					}
				
				Long otpPending = cadreTxnDetailsDAO.getNotCompletedTransactionDetailsByDates(null, yesterDay);
				returnVo.setTodayotpTransactionPending(otpPending != null ? otpPending : 0l);
			// Day before Transactions
					List<Object[]> list2 = cadreTxnDetailsDAO.getCompletedTransactionDetailsByDates(null, beforeYesterDay);	
					if(list2 !=null && list2.size() > 0)
					{
						for(Object[] params : list2)
						{
							returnVo.setYesterDayMoneyCollected((Long)params[2] != null ? (Long)params[2]  : 0l );
							returnVo.setYesterDayPaidAmount((Long)params[0] != null ? (Long)params[0] : 0l);
							returnVo.setYesterDayPendingAmount((Long)params[1] != null ? (Long)params[1] :0l);
						}
					}
			   // total users 
				Long totalUsers = cadreTxnDetailsDAO.getUsersCount(yesterDay,null);
				returnVo.setTotalUsers(totalUsers != null ? totalUsers : 0l); 
				// yesterday users 
				Long todayUsers = cadreTxnDetailsDAO.getUsersCount(yesterDay,null);
				returnVo.setTodayUsers(todayUsers != null ? todayUsers : 0l);
				// beforeYesterDay users 
				Long beforeYesterDayUsers = cadreTxnDetailsDAO.getUsersCount(beforeYesterDay,null);
				returnVo.setYesterDayUsers(beforeYesterDayUsers != null ? beforeYesterDayUsers : 0l);
				Long toDayOtpTxn =cadreOtpDetailsDAO.getOTPTxnCountByDate(yesterDay);
				returnVo.setOtpReqCount(toDayOtpTxn != null ? toDayOtpTxn : 0l);
		}
		catch (Exception e) {
			LOG.error(" exception occured at getBasicTransactionDetails() in CadreSurveyTransactionService service class. ", e);
		}
		return returnVo;
	}

	public CadreTransactionVO getTdpCadreDetailsBySearchCriteria( String refNo, String mobileNo)
	{
 		CadreTransactionVO returnVO = new CadreTransactionVO();
		try {
			List<Object[]> tdpCadreInfoList = tdpCadreDAO.getTdpCadreDetailsBySearchCriteria(refNo,mobileNo);
			List<CadreRegistrationVO> tdpCadreList = null;
			if(tdpCadreInfoList!=null && tdpCadreInfoList.size()>0)
			{
				tdpCadreList = new ArrayList<CadreRegistrationVO>();
				
				for (Object[] tdpCadre : tdpCadreInfoList)
				{
					CadreRegistrationVO cadreRegistrationVO= new  CadreRegistrationVO();
					
					cadreRegistrationVO.setEnrollmentNumber(tdpCadre[10] != null ?Long.valueOf(tdpCadre[10].toString().trim()):0L); // tdpcadreId
					
					cadreRegistrationVO.setPreviousEnrollmentNumber(tdpCadre[0] != null ?tdpCadre[0].toString():"");					
					cadreRegistrationVO.setRefNo(tdpCadre[1] != null ?tdpCadre[1].toString():" -- ");	
					
					String firstName = tdpCadre[2] != null ? tdpCadre[2].toString().trim() :"";
					String lastName = tdpCadre[3] != null ? tdpCadre[3].toString().trim():"" ;
					
					cadreRegistrationVO.setVoterName(firstName+" "+lastName);
					
					cadreRegistrationVO.setRelativeName(tdpCadre[4] != null ?tdpCadre[4].toString():" -- ");
					cadreRegistrationVO.setGender(tdpCadre[5] != null ?tdpCadre[5].toString():" -- ");
					cadreRegistrationVO.setConstituencyId(tdpCadre[6] != null ?tdpCadre[6].toString():" -- ");
					cadreRegistrationVO.setMobileNumber(tdpCadre[7] != null ?tdpCadre[7].toString():" -- ");
					cadreRegistrationVO.setUploadImageFileName(tdpCadre[8] != null ?tdpCadre[8].toString():" -- ");					
					cadreRegistrationVO.setCasteName(tdpCadre[9] != null ? " Dispatched ":" Pending "); //dispatch status
					cadreRegistrationVO.setCadreType(tdpCadre[11] != null ?tdpCadre[11].toString():" -- "); // voterIdcard No
					tdpCadreList.add(cadreRegistrationVO);
				}
			
				if(tdpCadreList != null && tdpCadreList.size()>0)
				{
					returnVO.setCadreRegistrationVOList(tdpCadreList);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getTdpCadreDetailsBySearchCriteria() in CadreSurveyTransactionService service class. ", e);
		}
		return returnVO;
	}
	
	public void sendTargetBasedSMSforLocationWiseManagers()
	{
		SurveyTransactionVO finalVO = new SurveyTransactionVO();
		try {
			
			List<SurveyTransactionVO> finalList = new ArrayList<SurveyTransactionVO>();
			List<Long> constituencyIdsList = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(0L);
			
			/*List<Long> constituencyIdsList = new ArrayList<Long>();
			constituencyIdsList.add(232L);
			constituencyIdsList.add(282L);
			constituencyIdsList.add(65L);
			*/
			Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
			Long totalConstituencyCount = 0L;
			
			Date today = new Date();					
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			
			cal.add(Calendar.DATE, -1);
			Date yesterDay = cal.getTime();
					
			if(constituencyIdsList != null && constituencyIdsList.size()>0)
			{
				Long daywiseTarget = 0L;
				
				for (Long constituencyId : constituencyIdsList)
				{
					totalConstituencyCount = totalConstituencyCount+1;
					Constituency constituency = constituencyDAO.get(constituencyId);				
					Long constiteuncyVotersCount =boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation("constituency", constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
					System.out.println(constituency.getName()+" Constituency Start sending SMS... ");
					double consituencyVoters = Double.valueOf(constiteuncyVotersCount.toString());
					Double targetCount ;
					 if(constituency.getDistrict().getDistrictId() > 10)
					 {
						 Double aa =   ( consituencyVoters / Double.valueOf(String.valueOf(IConstants.AP_VOTERS_2014)));
						 int bb = IConstants.TARGET_CADRE_AP;
						 
						 targetCount =  (double) (aa * bb);
					 }
					 else
					 {
						 Double aa =   ( consituencyVoters / Double.valueOf(String.valueOf(IConstants.TG_VOTERS_2014)));
						 int bb = IConstants.TARGET_CADRE_TG;
						 
						 targetCount =  (double) (aa * bb);
					 }
					 
					 daywiseTarget =  Math.round(targetCount/noOfDays);
					 
					List<Object[]> boothsList = boothDAO.findBoothInfoByConstituencyAndPublication(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
					List<SurveyTransactionVO> boothsBasicList = null;
					if(boothsList != null && boothsList.size()>0)
					{
						 boothsBasicList = new LinkedList<SurveyTransactionVO>();
						for (Object[] booth : boothsList) 
						{
							SurveyTransactionVO boothVO = new SurveyTransactionVO();
							
							boothVO.setId(booth[0] != null ? Long.valueOf(booth[0].toString().trim()):0L);
							boothVO.setName(booth[1] != null ? booth[1].toString().trim():"");
							boothVO.setLocationId(booth[2] != null ? Long.valueOf(booth[2].toString().trim()):0L);
							boothVO.setLocationName(booth[3] != null ? booth[3].toString().trim():"");
							boothVO.setParliamentName(booth[4] != null ? booth[4].toString().trim():null);
							if(boothVO.getParliamentName() != null)
							{
								LocalElectionBody localBody = localElectionBodyDAO.get(Long.valueOf(boothVO.getParliamentName()));
								boothVO.setLocationType(boothVO.getLocationName()+" "+localBody.getElectionType().getElectionType());
							}
							else
							{
								boothVO.setLocationType(boothVO.getLocationName()+" Mandal ");
							}
							
							boothsBasicList.add(boothVO);
						}
					}
					
					finalList = getLocationWiseRegistereDetailsByDates(boothsBasicList,constituency,yesterDay,yesterDay,noOfDays,yesterDay);
					
					if(finalList != null && finalList.size()>0)
					{
						Long totalRegisteredCount = 0L;
						Long registerdBooths=0L;
						Long notRegisteredBooths = 0L;
						Long below10Booths = 0L;
						String below10count ="";
						String notSubmitted = "";
						
						for (SurveyTransactionVO reportVO : finalList) 
						{
							List<SurveyTransactionVO> reportVOList = reportVO.getSurveyTransactionVOList();
							
							if(reportVOList != null && reportVOList.size()>0)
							{
								
								for (SurveyTransactionVO mandalsVO : reportVOList)
								{
									totalRegisteredCount = totalRegisteredCount + mandalsVO.getTotalCount();
									
									if(mandalsVO.getBelow10CountLocations() != null)
									{
									below10count = below10count+mandalsVO.getBelow10CountLocations();
									}
									if(mandalsVO.getNotSumbittedLocations() != null)
									{
										notSubmitted = notSubmitted+mandalsVO.getNotSumbittedLocations();
									}
									if(mandalsVO.getSubmittedCount() != null)
									{
										registerdBooths = registerdBooths + mandalsVO.getSubmittedCount();
									}
								
									if(mandalsVO.getNotSubmittedCount() != null)
									{
										notRegisteredBooths = notRegisteredBooths + mandalsVO.getNotSubmittedCount();
									}
									if(mandalsVO.getPendingCount() != null)
									{
										below10Booths = below10Booths + mandalsVO.getPendingCount();
									}
								}
								
								SurveyTransactionVO returnVO = new SurveyTransactionVO();
								returnVO.setId(reportVO.getId());
								returnVO.setName(reportVO.getName());
								returnVO.setDayWiseTarget(daywiseTarget);
								returnVO.setArcheivedTarget(totalRegisteredCount);
								returnVO.setSubmittedCount(registerdBooths);
								returnVO.setNotSubmittedCount(notRegisteredBooths);
								returnVO.setPendingCount(below10Booths);
								returnVO.setBelow10CountLocations(below10count.length()>0?below10count.substring(1):"");
								returnVO.setNotSumbittedLocations(notSubmitted.length()>0?notSubmitted.substring(1):"");	
								returnVO.setSurveyTransactionVOList(reportVOList);
								
								finalVO.getSurveyTransactionVOList().add(returnVO);
								
								StringBuilder assemblyMessage = new StringBuilder();
								assemblyMessage.append( returnVO.getName() +" Constituency Overview \n");
								assemblyMessage.append("Date : "+new SimpleDateFormat("dd-MM-yyyy").format(yesterDay));
								assemblyMessage.append(",\nTarget : "+ (new Double(targetCount)).longValue());
								assemblyMessage.append(",\nAchieved : "+returnVO.getArcheivedTarget());
								
								if(constituency.getDistrict().getDistrictId()>10)
								{
									if(returnVO.getBelow10CountLocations() != null)
									{
										assemblyMessage.append(", Below 10 Registrations Booths ("+below10Booths+"): "+returnVO.getBelow10CountLocations().substring(1));
									}
								}
								else
								{
									Long remainingTarget = targetCount.longValue() - totalRegisteredCount.longValue();
									remainingTarget = targetCount.longValue() + ( remainingTarget );
									assemblyMessage.append(",\nToday Target : "+remainingTarget);
								}
								if(returnVO.getNotSubmittedCount() != null)
								{
									assemblyMessage.append(", Registration not started Booths  ("+notRegisteredBooths+"): "+returnVO.getNotSumbittedLocations().substring(1));
								}
								
								List<Long> mobileNumbers = partyPresidentsDAO.getMobileNumebrsBylocation(constituency.getConstituencyId(), 0L, IConstants.CONSTITUENCY);
								
								if(mobileNumbers != null && mobileNumbers.size()>0)
								{
									String phoneNumbersStr = "";
									for (int i = 0; i < mobileNumbers.size(); i++) {
										phoneNumbersStr = phoneNumbersStr + ","+mobileNumbers.get(i).toString();
									}
									String[] phoneNumbersArr = phoneNumbersStr.split(",");
									
									/* Sending SMS for Mandal wise managers*/
									try {
										//String[] phoneNumbers = {"919959796608".toString()};								
										ResultStatus status = smsCountrySmsService.sendSmsFromAdmin(assemblyMessage.toString(), true, phoneNumbersArr);
									} catch (Exception e) {
										//LOG.error(" exception occured when sending SMS for mandal at getLocationWiseRegistereDetailsByDates() in CadreSurveyTransactionService service class. ", e);
									}
									/* Sending SMS for Mandal wise managers*/
								}
								
							}
						}
						System.out.println("\n\n total constituency sms sent : "+totalConstituencyCount+"\n\n");
						System.out.println(constituency.getName()+" Constituency  SMS Sent Successfully... \n\n\n\n");
					}
				}
			}
			System.out.println("\n\n\n total constituency sms sent : "+totalConstituencyCount);
		} catch (Exception e) {
			LOG.error(" exception occured at sendTargetBasedSMSforLocationWiseManagers() in CadreSurveyTransactionService service class. ", e);
		}
	}
	
	private List<SurveyTransactionVO> getLocationWiseRegistereDetailsByDates( List<SurveyTransactionVO> boothsBasicList, Constituency constituency,Date fromDate, Date toDate,Long noOfDays,Date yesterDay)
	{
		List<SurveyTransactionVO> finalList = null;
		try {
			List<SurveyTransactionVO> returnList = new ArrayList<SurveyTransactionVO>();
			List<Object[]> overAllboothWiseCountList = tdpCadreDAO.getTotalRecordsBoothWise(constituency.getConstituencyId(),null,null);
			Map<Long,Long> boothWiseTotalCountMap = new HashMap<Long, Long>();
			
			if(overAllboothWiseCountList != null && overAllboothWiseCountList.size()>0)
			{
				for (Object[] booth : overAllboothWiseCountList) 
				{
					Long boothId = booth[1] != null ? Long.valueOf(booth[1].toString().trim()):0L;
					Long count =  booth[1] != null ? Long.valueOf(booth[1].toString().trim()):0L;
					Long registrationCount = 0L;
					if(boothWiseTotalCountMap.get(boothId) != null)
					{
						boothWiseTotalCountMap.get(boothId);
					}
					registrationCount = registrationCount + count;
					
					boothWiseTotalCountMap.put(boothId, registrationCount);
					
				}
			}
			List<Object[]> boothWiseCountList = tdpCadreDAO.getTotalRecordsBoothWise(constituency.getConstituencyId(),fromDate,toDate);
			if(boothWiseCountList != null && boothWiseCountList.size()>0)
			{
				Map<Long, List<SurveyTransactionVO>> tehsilMap = new HashMap<Long, List<SurveyTransactionVO>>(0);
				Map<Long, Long> tehsilWiseTotalCount = new HashMap<Long, Long>(0);
				Map<Long,String> tehsilWiseRegisteredBoothsMap = new HashMap<Long, String>(0);
				Map<Long,String> tehsilBelow10CountBoothsMap = new HashMap<Long, String>(0);
				
				List<SurveyTransactionVO> boothList = null;
				Long boothWiseTotalRegisteredCount = 0L;
				
				for (Object[] booth : boothWiseCountList)
				{
					boothList = new ArrayList<SurveyTransactionVO>();
					
					Long count = booth[0] != null ? Long.valueOf(booth[0].toString().trim()):0L;
					Long boothId = booth[1] != null ? Long.valueOf(booth[1].toString().trim()):0L;
					
					SurveyTransactionVO reportVo = getMatchedVOById(boothsBasicList,boothId);
					
					if(reportVo != null)
					{
						if(reportVo.getParliamentName() != null) // Muncipality
						{
							Long munci_OR_GHMC_OR_CORP_ID = Long.valueOf(reportVo.getParliamentName());
							String boothNo = reportVo.getName().trim();
							reportVo.setTotalCount(count);
							reportVo.setLocationName(reportVo.getLocationName());
							/* Start Below 10  registered booth Info  */ 
							
							if(boothWiseTotalCountMap.get(boothId) < 10)
							{
								String below10BoothsInTehsil = "";
								if(tehsilBelow10CountBoothsMap.get(munci_OR_GHMC_OR_CORP_ID)!= null)
								{
									below10BoothsInTehsil = tehsilBelow10CountBoothsMap.get(munci_OR_GHMC_OR_CORP_ID);
								}
								
								below10BoothsInTehsil = below10BoothsInTehsil+", "+ boothNo.toString()+" ("+count+")";
								
								tehsilBelow10CountBoothsMap.put(munci_OR_GHMC_OR_CORP_ID, below10BoothsInTehsil);
							}
							
							/* end Below 10  registered booth Info  */ 
							
							/* Start registered booth Info*/
							
							String registeredBoothsInTehsil = "";
							if(tehsilWiseRegisteredBoothsMap.get(munci_OR_GHMC_OR_CORP_ID)!= null)
							{
								registeredBoothsInTehsil = tehsilWiseRegisteredBoothsMap.get(munci_OR_GHMC_OR_CORP_ID);
							}
							
							registeredBoothsInTehsil = registeredBoothsInTehsil+", "+boothId.toString();
							
							tehsilWiseRegisteredBoothsMap.put(munci_OR_GHMC_OR_CORP_ID, registeredBoothsInTehsil);
							
							/* end registered booth Info  */
															
							if(tehsilMap.get(munci_OR_GHMC_OR_CORP_ID) != null)
							{
								boothList = tehsilMap.get(munci_OR_GHMC_OR_CORP_ID);
							}
							
							boothWiseTotalRegisteredCount = boothWiseTotalRegisteredCount + count;
							
							Long tehsilWiseCount = 0L;
							if(tehsilWiseTotalCount.get(munci_OR_GHMC_OR_CORP_ID) != null)
							{
								tehsilWiseCount = tehsilWiseTotalCount.get(munci_OR_GHMC_OR_CORP_ID);
							}
							tehsilWiseCount = tehsilWiseCount+count;
							tehsilWiseTotalCount.put(munci_OR_GHMC_OR_CORP_ID, tehsilWiseCount);
							
							boothList.add(reportVo);
														
							tehsilMap.put(munci_OR_GHMC_OR_CORP_ID, boothList);
						}
						else
						{
							String boothNo = reportVo.getName().trim();
							reportVo.setTotalCount(count);
							reportVo.setLocationName(reportVo.getLocationName());
							/* Start Below 10  registered booth Info  */ 
							
							if(count < 10)
							{
								String below10BoothsInTehsil = "";
								if(tehsilBelow10CountBoothsMap.get(reportVo.getLocationId())!= null)
								{
									below10BoothsInTehsil = tehsilBelow10CountBoothsMap.get(reportVo.getLocationId());
								}
								
								below10BoothsInTehsil = below10BoothsInTehsil+", "+ boothNo.toString()+" ("+count+")";
								
								tehsilBelow10CountBoothsMap.put(reportVo.getLocationId(), below10BoothsInTehsil);
							}
							
							/* end Below 10  registered booth Info  */ 
							
							/* Start registered booth Info*/
							
							String registeredBoothsInTehsil = "";
							if(tehsilWiseRegisteredBoothsMap.get(reportVo.getLocationId())!= null)
							{
								registeredBoothsInTehsil = tehsilWiseRegisteredBoothsMap.get(reportVo.getLocationId());
							}
							
							registeredBoothsInTehsil = registeredBoothsInTehsil+", "+boothId.toString();
							
							tehsilWiseRegisteredBoothsMap.put(reportVo.getLocationId(), registeredBoothsInTehsil);
							
							/* end registered booth Info  */
															
							if(tehsilMap.get(reportVo.getLocationId()) != null)
							{
								boothList = tehsilMap.get(reportVo.getLocationId());
							}
							
							boothWiseTotalRegisteredCount = boothWiseTotalRegisteredCount + count;
							
							Long tehsilWiseCount = 0L;
							if(tehsilWiseTotalCount.get(reportVo.getLocationId()) != null)
							{
								tehsilWiseCount = tehsilWiseTotalCount.get(reportVo.getLocationId());
							}
							tehsilWiseCount = tehsilWiseCount+count;
							tehsilWiseTotalCount.put(reportVo.getLocationId(), tehsilWiseCount);
							
							boothList.add(reportVo);
														
							tehsilMap.put(reportVo.getLocationId(), boothList);
						}
						
					}							
				}
				
				if(tehsilMap != null && tehsilMap.size()>0)
				{
					finalList = new ArrayList<SurveyTransactionVO>();
					List<SurveyTransactionVO> tehslList = new ArrayList<SurveyTransactionVO>();
					for (Long tehsilId : tehsilMap.keySet()) 
					{
						List<SurveyTransactionVO> tehsiVOList = tehsilMap.get(tehsilId);
						
						if(tehsiVOList != null && tehsiVOList.size()>0)
						{
							SurveyTransactionVO tehsilVO = tehsiVOList.get(0);
						
							String teshilWiseRegisteredBooths = tehsilWiseRegisteredBoothsMap.get(tehsilId);
							String[] registeredBooths = teshilWiseRegisteredBooths.split(",");
							List<Long> boothIdsList = new ArrayList<Long>();
									
							if(registeredBooths != null && registeredBooths.length>0)
							{
								for (String boothId : registeredBooths) 
								{
									if(boothId != null && boothId.toString().trim().length()>0)
									{
										boothIdsList.add(Long.valueOf(boothId.toString().trim()));
									}
								}
								
							}
							
							String notRegisteredBoothsStr = "";
							Long mandalVoters = 0L;
							List<String> notRegisteredBooths = null;
							if(boothIdsList != null && boothIdsList.size()>0)
							{								
								Booth booth = boothDAO.get(boothIdsList.get(0));
								if(booth.getLocalBody() != null)
								{
									notRegisteredBooths = boothDAO.getUnregisteredBoothsByBooths(boothIdsList,constituency.getConstituencyId(),IConstants.VOTER_DATA_PUBLICATION_ID,tehsilId,"notRural");
									 mandalVoters = boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation("localElectionBody", tehsilId, IConstants.VOTER_DATA_PUBLICATION_ID);
								}
								else
								{
									notRegisteredBooths = boothDAO.getUnregisteredBoothsByBooths(boothIdsList,constituency.getConstituencyId(),IConstants.VOTER_DATA_PUBLICATION_ID,tehsilId,"rural");
									 mandalVoters = boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation("mandal", tehsilId, IConstants.VOTER_DATA_PUBLICATION_ID);
									
								}
								
								if(notRegisteredBooths != null && notRegisteredBooths.size()>0)
								{
									for (String boothNo : notRegisteredBooths) 
									{
										notRegisteredBoothsStr = notRegisteredBoothsStr+", "+boothNo.trim();
									}
								}
							}

							double mandalVotersCount = Double.valueOf(mandalVoters.toString());
							Double targetCount ;
							 if(constituency.getDistrict().getDistrictId() > 10)
							 {
								 Double apConstPerc =   ( mandalVotersCount / Double.valueOf(String.valueOf(IConstants.AP_VOTERS_2014)));
								 int apTarget = IConstants.TARGET_CADRE_AP;
								 
								 targetCount =  (double) (apConstPerc * apTarget);
							 }
							 else
							 {
								 Double tgConstPerc =   ( mandalVotersCount / Double.valueOf(String.valueOf(IConstants.TG_VOTERS_2014)));
								 int tgTarget = IConstants.TARGET_CADRE_TG;
								 
								 targetCount =  (double) (tgConstPerc * tgTarget);
							 }
							 
							SurveyTransactionVO finalTehsilVO = new SurveyTransactionVO();
							if(boothIdsList != null && boothIdsList.size()>0)
							{
								finalTehsilVO.setSubmittedCount(Long.valueOf(String.valueOf(boothIdsList.size())));
							}							
							if(notRegisteredBooths != null && notRegisteredBooths.size()>0)
							{
								finalTehsilVO.setNotSubmittedCount(Long.valueOf(String.valueOf(notRegisteredBooths.size())));
							}
							if(tehsilBelow10CountBoothsMap != null && tehsilBelow10CountBoothsMap.size()>0)
							{
								finalTehsilVO.setBelow10CountLocations(tehsilBelow10CountBoothsMap.get(tehsilVO.getLocationId())); // below 10 count booths List
							}
							if(finalTehsilVO.getBelow10CountLocations() != null)
							{
								finalTehsilVO.setPendingCount(Long.valueOf(String.valueOf(finalTehsilVO.getBelow10CountLocations().split(",").length - 1)));
							}
							
							finalTehsilVO.setId(tehsilVO.getLocationId());
							finalTehsilVO.setName(tehsilVO.getLocationName());																	
							finalTehsilVO.setTotalCount(tehsilWiseTotalCount.get(tehsilId));							
							finalTehsilVO.setNotSumbittedLocations(notRegisteredBoothsStr.length()>0? notRegisteredBoothsStr:null);
							finalTehsilVO.setSurveyTransactionVOList(tehsiVOList);
							finalTehsilVO.setArcheivedTarget(Math.round(targetCount/noOfDays)); // daywise target
							tehslList.add(finalTehsilVO);
							
							StringBuilder mandalMessage = new StringBuilder();
							mandalMessage.append( tehsilVO.getLocationType() +" Registrations Overview ");
							mandalMessage.append("\nDate : "+new SimpleDateFormat("dd-MM-yyyy").format(yesterDay));
							mandalMessage.append(",\nTarget : "+ (new Double(targetCount)).longValue());
							mandalMessage.append(",\nAchieved : "+finalTehsilVO.getTotalCount());
							
							if(constituency.getDistrict().getDistrictId()>10)
							{
								if(finalTehsilVO.getBelow10CountLocations() != null)
								{
									mandalMessage.append(",\nBelow 10 Registrations Booths  ("+finalTehsilVO.getPendingCount()+"): "+finalTehsilVO.getBelow10CountLocations().substring(1));
								}
							}
							else
							{
								Long remainingTarget = targetCount.longValue() - finalTehsilVO.getTotalCount().longValue();
								remainingTarget = targetCount.longValue() + ( remainingTarget );
								mandalMessage.append(",\nToday Target : "+remainingTarget);
							}
							if(finalTehsilVO.getNotSubmittedCount() != null)
							{
								mandalMessage.append(",\nRegistration not started Booths ("+finalTehsilVO.getNotSubmittedCount()+"): "+finalTehsilVO.getNotSumbittedLocations().substring(1));
							}
							
							List<Long> mobileNumbers = partyPresidentsDAO.getMobileNumebrsBylocation(constituency.getConstituencyId(), tehsilId, IConstants.TEHSIL);
							
							if(mobileNumbers != null && mobileNumbers.size()>0)
							{
								String phoneNumbersStr = "";
								for (int i = 0; i < mobileNumbers.size(); i++) {
									phoneNumbersStr = phoneNumbersStr + ","+mobileNumbers.get(i).toString();
								}
								String[] phoneNumbersArr = phoneNumbersStr.split(",");
							
								
								/* Sending SMS for Mandal wise managers*/
								try {
									//String[] phoneNumbers = {"919959796608".toString()};								
									ResultStatus status = smsCountrySmsService.sendSmsFromAdmin(mandalMessage.toString(), true, phoneNumbersArr);
								} catch (Exception e) {
									//LOG.error(" exception occured when sending SMS for mandal at getLocationWiseRegistereDetailsByDates() in CadreSurveyTransactionService service class. ", e);
								}
								/* Sending SMS for Mandal wise managers*/
							}
							
						}
						
					}
					SurveyTransactionVO finalVO = new SurveyTransactionVO();
					finalVO.setId(constituency.getConstituencyId());
					finalVO.setName(constituency.getName());
					returnList.addAll(tehslList);
					finalVO.setSurveyTransactionVOList(returnList);
					
					finalList.add(finalVO);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getLocationWiseRegistereDetailsByDates() in CadreSurveyTransactionService service class. ", e);
		}
		
		return finalList;
	}
	
	private SurveyTransactionVO getMatchedVOById(List<SurveyTransactionVO> reportList, Long boothId) 
	{
		SurveyTransactionVO returnVO = null;
		try {
			
			if(reportList != null && reportList.size()>0)
			{
				for (SurveyTransactionVO reportVO : reportList)
				{
					if(reportVO.getId().longValue() == boothId.longValue())
					{
						return reportVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured at getMatchedVOById() in CadreSurveyTransactionService service class. ", e);
		}
		return returnVO;
	}
}
