package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserSmsStatusDAO;
import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;

public class MobileAppUserSmsStatusDAO extends GenericDaoHibernate<MobileAppUserSmsStatus, Long> implements IMobileAppUserSmsStatusDAO{

	public MobileAppUserSmsStatusDAO() {
		super(MobileAppUserSmsStatus.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<MobileAppUserSmsStatus> getUsersLatestData(List<Long> userIds, Date fromDate, Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model from" +
				" MobileAppUserSmsStatus model " +
				" where " +
				" model.mobileAppUserId in(:userIds)" +
				" and model.statusDate between :fromDate and :toDate " +
				" order by model.insertedTime desc");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("userIds", userIds);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}

}
