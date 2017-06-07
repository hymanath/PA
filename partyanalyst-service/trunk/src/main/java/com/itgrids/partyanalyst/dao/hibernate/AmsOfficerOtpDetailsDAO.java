package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAmsOfficerOtpDetailsDAO;
import com.itgrids.partyanalyst.model.AmsOfficerOtpDetails;

public class AmsOfficerOtpDetailsDAO extends GenericDaoHibernate<AmsOfficerOtpDetails, Long>
implements IAmsOfficerOtpDetailsDAO {

	public AmsOfficerOtpDetailsDAO() {
		super(AmsOfficerOtpDetails.class);
	}

	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime){
		Query query = getSession().createSQLQuery("select  otp_no as otp,reference_id as ref,updated_time as dateStr,ams_officer_otp_details_id as aood"
									+ " from ams_officer_otp_details"
									+ " where  mobile_no = :mobileNo"
									+ " and is_valid = 'Y'"
									+ " and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(updated_time)))/60 < 15 " +
									" order by ams_officer_otp_details_id desc ")
									.addScalar("otp", Hibernate.LONG)
									.addScalar("ref", Hibernate.LONG)
									.addScalar("dateStr", Hibernate.STRING)
									.addScalar("aood", Hibernate.LONG);
		query.setParameter("mobileNo", mobileNo);
		query.setDate("currentTime", currentTime);
		return  query.list();
	}
	public Integer  getAllOtpsForSameMobile(String mobileNo)
	{
		Query query=getSession().createSQLQuery("update ams_officer_otp_details model "
				+ "set model.is_valid = 'N'"
				+ " where model.mobile_no = :mobileNo ");
		query.setParameter("mobileNo", mobileNo);
		return query.executeUpdate();
		
	}
}
