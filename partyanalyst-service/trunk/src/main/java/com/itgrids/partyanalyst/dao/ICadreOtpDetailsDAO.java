package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreOtpDetails;


public interface ICadreOtpDetailsDAO extends GenericDao<CadreOtpDetails, Long>{
	
	public List<Object[]> getOtpDetailsForDates(Date todayDate, Date searchDate);
	
	public List<Object[]> getOfflineTxnDetailsIdsForDates(Date todayDate, Date searchDate);
	
	public List<Object[]> getDayWiseReportForDates(Date fromDate, Date toDate);
	
	public List<Object[]> getLocationWiseTransactionsByDates(Date fromDate, Date toDate,List<Long> locationIdList,String queryString);
	
	public Integer updateIsDeleted(String mobileNo);
	public List<String> checkOTPValid(String mobileNo,String otp,Long userId);
	public List<String> checkOTP(String otp);
	public List<String> checkForMobile(String mobileNo);
}
