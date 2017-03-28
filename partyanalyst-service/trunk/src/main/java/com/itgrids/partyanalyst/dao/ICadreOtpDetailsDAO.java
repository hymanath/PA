package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreOtpDetails;


public interface ICadreOtpDetailsDAO extends GenericDao<CadreOtpDetails, Long>{
	
	
	public Integer updateIsDeleted(Long mobileNo);
	public CadreOtpDetails checkOTPValid(Long mobileNo,Long refNo,Long  userId);
	public List<String> checkOTP(String otp);
	public List<String> checkForMobile(String mobileNo);
	public Long getOTPTxnCountByDate(Date date);
	public List<Long> getCadreotpDateils(String otpNo);
}
