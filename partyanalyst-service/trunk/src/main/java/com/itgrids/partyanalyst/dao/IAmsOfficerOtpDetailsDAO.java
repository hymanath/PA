package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AmsOfficerOtpDetails;

public interface IAmsOfficerOtpDetailsDAO extends GenericDao<AmsOfficerOtpDetails, Long> {

	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime);
	public Integer  getAllOtpsForSameMobile(String mobileNo);
}
