package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserOtpDetails;

public interface ITabUserOtpDetailsDAO extends GenericDao<TabUserOtpDetails, Long>{

	public Long checkOTPDetails(String mobileNo,String otpNo,Date currentTime);
	public TabUserOtpDetails checkValidOTPIsExists(Long tabUserInfoId,Long cadreSurveyUserId,String mobileNo,Date currentTime);
	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime);
	public Integer  getAllOtpsForSameMobile(String mobileNo);
}
