package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreOtpDetails;


public interface ICadreOtpDetailsDAO extends GenericDao<CadreOtpDetails, Long>{
	public Integer updateIsDeleted(String mobileNo);
	public List<String> checkOTPValid(String mobileNo,String otp,Long userId);
	public List<String> checkOTP(String otp);
	public List<String> checkForMobile(String mobileNo);
}
