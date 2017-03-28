package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoSourceDAO;
import com.itgrids.partyanalyst.model.TabUserEnrollmentInfoSource;

public class TabUserEnrollmentInfoSourceDAO extends GenericDaoHibernate<TabUserEnrollmentInfoSource, Long> implements
		ITabUserEnrollmentInfoSourceDAO {
	public TabUserEnrollmentInfoSourceDAO(){
		super(TabUserEnrollmentInfoSource.class);  
	}
	public Long getTotalTabUserWorkingInField(Long stateId,Date lastOneHourTime,Date today){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct TUEIS.tabUserInfoId) from TabUserEnrollmentInfoSource TUEIS " +      
						" where " +
						" TUEIS.stateId = :stateId and " +  
						" TUEIS.endTime between (:lastOneHourTime) and (:today) "); 
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("stateId",stateId);  
		query.setParameter("lastOneHourTime",lastOneHourTime);        
		query.setParameter("today",today);
		return (Long)query.uniqueResult();  
	}//select * from tab_user_enrollment_info_source where end_time between '2016-10-12 12:00:00' and '2016-10-12 13:00:00'; 

}
