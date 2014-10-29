package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CadreTxnDetails;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.RandomGenaration;
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

	public CadreTransactionVO genarateOTPAndSaveTxnDetails(final CadreTransactionVO inputVO)
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
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				CadreTransactionVO assignedProblemsFromDB =  (CadreTransactionVO) transactionTemplate
						.execute(new TransactionCallback() {
							public Object doInTransaction(TransactionStatus status) {
				CadreTxnDetails cadreTxnDetails = new CadreTxnDetails();
				cadreTxnDetails.setCadreSurveyUser(cadreSurveyUserDAO.get(inputVO.getId()));
				cadreTxnDetails.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				cadreTxnDetails.setSinkedRecords(inputVO.getSinkedRecords());
				cadreTxnDetails.setPendingRecords(inputVO.getPendingRecords());
				cadreTxnDetails.setTotalAmount(inputVO.getTotalAmount());
				cadreTxnDetails.setPaidAmount(inputVO.getPaidAmount());
				cadreTxnDetails.setPendingAmount(inputVO.getPendingAmount());
				try {
					cadreTxnDetails.setSurveyTime(sdf.parse(inputVO.getSurveyTime()));
				} catch (ParseException e) {
					
				}
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
					e.printStackTrace();
					returnVo.setMessage("Exception");
				}
				
			}
			
		}
		catch (Exception e) {
			
		}
		return returnVo;
	}
	
	public void genarateOTP(CadreTxnDetails cadreTxnDetails,CadreTransactionVO inputVo,CadreTransactionVO returnVo)
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
			cadreOtpDetails.setTxnNumber(rnd.randomStringOfLength(8));
			cadreOtpDetailsDAO.save(cadreOtpDetails);
			String message = "your OTP is "+otpRand+" for Request # " +refRand+" ";
			String[] phoneNumbers = {inputVo.getMobileNo().toString()};
			smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String updateTxnStatus(String uniqueKey,String status,Long constituencyId)
	{
		String msg = "";
		try{
			if(status.equalsIgnoreCase("success"))
			{
				cadreTxnDetailsDAO.updateCompleteStatus(uniqueKey, constituencyId);
				msg = "updated";
			}
			
		}
		catch (Exception e) {
			msg = "Exception";
		}
		return msg;
	}
	
	
	
}
