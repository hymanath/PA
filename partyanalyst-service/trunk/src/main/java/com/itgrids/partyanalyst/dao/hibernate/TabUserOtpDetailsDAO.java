package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.partyanalyst.dao.ITabUserOtpDetailsDAO;
import com.itgrids.partyanalyst.model.TabUserOtpDetails;

@Repository("tabUserOtpDetailsDAO")
public class TabUserOtpDetailsDAO extends GenericDaoHibernate<TabUserOtpDetails, Long> implements ITabUserOtpDetailsDAO{

	public TabUserOtpDetailsDAO() {
		super(TabUserOtpDetails.class);
	}
	public Long checkOTPDetails(String mobileNo,String otpNo,Date currentTime){
		Query query = getSession().createSQLQuery("select tab_user_otp_details_id as tuso"
									+ " from tab_user_otp_details"
									+ " where  mobile_no = :mobileNo"
									+ " and otp_no = :otpNo"
									+ " and is_valid = 'Y'"
									+ " and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(updated_time)))/60 < 15").addScalar("tuso", Hibernate.LONG);
		
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("otpNo", otpNo);
		query.setDate("currentTime", currentTime);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> isExistOTPDetails(String mobileNo,Date currentTime){
		Query query = getSession().createSQLQuery("select  otp_no as otp,reference_id as ref,updated_time as dateStr,tab_user_otp_details_id as tuso"
									+ " from tab_user_otp_details"
									+ " where  mobile_no = :mobileNo"
									+ " and is_valid = 'Y'"
									+ " and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(updated_time)))/60 < 15 " +
									" order by tab_user_otp_details_id desc ")
									.addScalar("otp", Hibernate.LONG)
									.addScalar("ref", Hibernate.LONG)
									.addScalar("dateStr", Hibernate.STRING)
									.addScalar("tuso", Hibernate.LONG);
		query.setParameter("mobileNo", mobileNo);
		query.setDate("currentTime", currentTime);
		return  query.list();
	}
	
	
	/*public Long checkOTPDetails(String otpNo,String mobileNo,Date today,Date lastOneHourTime){
		Query query = getSession().createQuery("select model.tabUserOtpDetailsId"
									+ " from TabUserOtpDetails model " 
									+ " where model.mobileNo = :mobileNo  " 
									+ " and model.otpNo = :otpNo "
									+ " and model.isValid = 'Y' "
									+ " and model.updatedTime between :lastOneHourTime and :today ");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("otpNo", otpNo);
		query.setParameter("today", today);
		query.setParameter("lastOneHourTime", lastOneHourTime);
		return (Long) query.uniqueResult();
	}*/
	
	public TabUserOtpDetails checkValidOTPIsExists(Long tabUserInfoId,Long cadreSurveyUserId,String mobileNo,Date currentTime){
		Query query = getSession().createSQLQuery("select model"
											+ " from tab_user_otp_details model "
											+ " where model.tab_user_info_id = :tabUserInfoId "
											+ " and model.cadre_survey_user_id = :userId "
											+ " and model.mobile_no = :mobileNo "
											+ " and model.is_valid = 'Y' "
											+ " and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(model.updated_time)))/60 < 15");
		query.setParameter("tabUserInfoId", tabUserInfoId);
		query.setParameter("userId", cadreSurveyUserId);
		query.setParameter("mobileNo", mobileNo);
		query.setDate("currentTime", currentTime);
		
		return (TabUserOtpDetails) query.uniqueResult();
	}
	
	public Integer  getAllOtpsForSameMobile(String mobileNo)
	{
		Query query=getSession().createSQLQuery("update tab_user_otp_details model "
				+ "set model.is_valid = 'N'"
				+ " where model.mobile_no = :mobileNo ");
		query.setParameter("mobileNo", mobileNo);
		return query.executeUpdate();
		
	}
}
