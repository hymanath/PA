package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;


public class CadreOtpDetailsDAO extends GenericDaoHibernate<CadreOtpDetails, Long> implements ICadreOtpDetailsDAO{

	public CadreOtpDetailsDAO() {
		super(CadreOtpDetails.class);
	}
	
	public Integer updateIsDeleted(Long mobileNo)
	{
	 
		Query query = getSession().createQuery("update CadreOtpDetails model set model.isDeleted = 'Y' where model.isDeleted = 'N'" +
				" and model.mobileNo like '"+mobileNo+"'");
		//query.setParameter("mobileNo", mobileNo);
		int count = query.executeUpdate();
		return count;
	}
	
	public CadreOtpDetails checkOTPValid(Long mobileNo,Long refNo,Long  userId)
	{
		Query query = getSession().createQuery("select model from CadreOtpDetails model where " +
				" model.mobileNo like '"+mobileNo+"'  and model.user.userId like '"+userId+"' and model.otpReferenceId like '"+refNo+"' and model.isDeleted = 'N' ");
		//query.setParameter("mobileNo", mobileNo);
		//query.setParameter("userId", userId);
		//query.setParameter("refNo", refNo);
		return (CadreOtpDetails) query.uniqueResult();
	}
	
	public List<String> checkOTP(String otp)
	{
		Query query = getSession().createQuery("select model.otpNo from CadreOtpDetails model where " +
				" model.otpNo = :otp and model.isDeleted = 'N'");
		query.setParameter("otp", otp);
		return query.list();
	}
	
	public List<String> checkForMobile(String mobileNo)
	{
		Query query = getSession().createQuery("select model.mobileNo from CadreOtpDetails model where " +
				" model.mobileNo =:mobileNo ");
		query.setParameter("mobileNo", mobileNo);
		return query.list();
	}
	
	public Long getOTPTxnCountByDate(Date searchDate)
	{
		Query query = getSession().createQuery("select count(distinct model.mobileNo) from CadreOtpDetails model where " +
				" date(model.insertedTime) =:searchDate");
		if(searchDate != null)
		query.setDate("searchDate", searchDate);
		return (Long) query.uniqueResult();
	}
	
	public List<Long> getCadreotpDateils(String otpNo)
	{
		Query query = getSession().createQuery("select model.cadreOtpDetailsId from CadreOtpDetails model where model.otpNo = :otpNo");
		query.setParameter("otpNo", otpNo);
		return query.list();
	}

	
}
