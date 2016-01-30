package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserSmsStatusDAO;
import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileAppUserSmsStatusDAO extends GenericDaoHibernate<MobileAppUserSmsStatus, Long> implements IMobileAppUserSmsStatusDAO{

	public MobileAppUserSmsStatusDAO() {
		super(MobileAppUserSmsStatus.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<MobileAppUserSmsStatus> getUsersLatestData(List<Long> userIds, Date fromDate, Date toDate,List<String> userType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model from" +
				" MobileAppUserSmsStatus model " +
				" where " +
				" model.mobileAppUserId in(:userIds) ");
				if(fromDate != null && toDate != null)
					sb.append(" and ( model.statusDate between :fromDate and :toDate )");
				if(!(userType.contains("All")))
					sb.append(" and model.mobileAppUser.type in (:userType) ");
				sb.append(" order by model.insertedTime desc ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("userIds", userIds);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(!(userType.contains("All")))
			query.setParameterList("userType", userType);
		return query.list();
	}
	
	public List<Long> getNoOfSmsCountOfUser(Long userId,List<Date> dates){
		/*Query query = getSession().createQuery(" select model.sentSms from MobileAppUserSmsStatus model " +
				" where model.mobileAppUserId=:userId and date(model.statusDate) in (:dates) order by date(model.insertedTime) desc ");
		query.setParameter("userId", userId);
		query.setParameterList("dates", dates);*/
		
		Query query = getSession().createQuery(" select count(model.mobileAppUserVoterId) from MobileAppUserVoter model " +
				" where model.mobileAppUserId=:userId and date(model.surveyTime) in (:dates) and model.smsStatus like '%"+IConstants.SUCCESS+"%' order by date(model.insertedTime) desc ");
		query.setParameter("userId", userId);
		query.setParameterList("dates", dates);
		
		return query.list();
	}
}
