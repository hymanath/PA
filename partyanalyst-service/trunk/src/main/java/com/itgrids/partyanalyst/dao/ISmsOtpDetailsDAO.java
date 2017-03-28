package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsOtpDetails;

public interface ISmsOtpDetailsDAO extends GenericDao<SmsOtpDetails, Long> {

	public Long validateOTP(String mobileNo,String refNo,String otp);
	public List<Object[]> checkForExpire(String mobileNo);
}
