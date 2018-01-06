package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OtpDetails;

public interface IOtpDetailsDAO extends GenericDao<OtpDetails, Long> {
	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime);
	public List<Object[]> checkOTPDetails(String otp, String memberShipNo,Date currentTime);
	public void flushAndclearSession();
}
