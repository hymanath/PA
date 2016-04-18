package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISmsOtpDetailsDAO;
import com.itgrids.partyanalyst.model.SmsOtpDetails;

public class SmsOtpDetailsDAO extends GenericDaoHibernate<SmsOtpDetails, Long>
		implements ISmsOtpDetailsDAO {

	public SmsOtpDetailsDAO(){
		super(SmsOtpDetails.class);
	}
	
	
	public Long validateOTP(String mobileNo,String refNo,String otp){
		
		Query query = getSession().createQuery("select model.smsOtpDetailsId" +
										" from SmsOtpDetails model" +
										" where model.mobileNo = :mobileNo" +
										" and model.otpReferenceId = :refNo" +
										" and model.otpNo = :otp" +
										" and model.isDeleted = 'N'");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("refNo", refNo);
		query.setParameter("otp", otp);
		
		return (Long) query.uniqueResult();
	}
}
