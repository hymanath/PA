package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.model.SmsJobStatus;

public class SmsJobStatusDAO extends GenericDaoHibernate<SmsJobStatus, Long> implements ISmsJobStatusDAO{
	public SmsJobStatusDAO() {
		super(SmsJobStatus.class);
	}
	
	/*public Integer updateSmsJobCode(Long tdpCadreId,String jobCode){
		Query query = getSession().createQuery("update TdpCadre model set model.smsJobCode = :jobCode " +
				" where model.tdpCadreId = :tdpCadreId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("jobCode", jobCode);
		Integer count = query.executeUpdate();
		return count;
	}*/
	
	public Integer updateSmsSentStatus(String mobile,String jobCode,Date doneTime, String status){
		Query query = getSession().createQuery("update SmsJobStatus model set model.smsStatus = :status, " +
				" model.smsSentTime = :doneTime " +
				" where model.mobileNumber = :mobile" +
				" and model.jobCode = :jobCode");
		
		query.setParameter("status",status);
		query.setParameter("jobCode", jobCode);
		query.setParameter("mobile", mobile);
		query.setTimestamp("doneTime", doneTime);
		
		Integer count = query.executeUpdate();
		return count;
	}
}
