package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreOtpDetails;


public interface ICadreOtpDetailsDAO extends GenericDao<CadreOtpDetails, Long>{
	public Integer updateIsDeleted(String mobileNo);
	public String checkOTPValid(String mobileNo,String otp,String refNo);
}
