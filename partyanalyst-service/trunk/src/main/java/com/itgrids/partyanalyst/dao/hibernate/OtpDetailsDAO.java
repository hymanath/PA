package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IOtpDetailsDAO;
import com.itgrids.partyanalyst.model.OtpDetails;

public class OtpDetailsDAO extends GenericDaoHibernate<OtpDetails, Long> implements IOtpDetailsDAO {

	public OtpDetailsDAO() {
		super(OtpDetails.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime){
		Query query = getSession().createSQLQuery("select  otp as otp,updated_time as dateStr,tdp_cadre_id as cadreId"
									+ " from otp_details"
									+ " where  mobile_no = :mobileNo"
									+ " and is_valid = 'Y'"
									+ " and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(updated_time)))/60 < 15 " +
									" order by otp_details_id desc ")
									.addScalar("otp", Hibernate.LONG)
									.addScalar("dateStr", Hibernate.STRING)
									.addScalar("cadreId", Hibernate.LONG);
		query.setParameter("mobileNo", mobileNo);
		query.setDate("currentTime", currentTime);
		return  query.list();
	}


	@Override
	public List<Object[]> checkOTPDetails(String otp, String memberShipNo, Date currentTime) {

			Query query = getSession().createSQLQuery("select otp_details_id,generated_time "
					+ " from otp_details"
					+ " where otp = :otp"
					+ " and membership_id = :memberShipNo"
					+ " and is_valid = 'Y' ");
			
				query.setParameter("otp", otp);
				query.setParameter("memberShipNo", memberShipNo);
											
				return query.list();
		}
	
	public void flushAndclearSession(){
	      getSession().flush();
	      getSession().clear();
	    }
}
