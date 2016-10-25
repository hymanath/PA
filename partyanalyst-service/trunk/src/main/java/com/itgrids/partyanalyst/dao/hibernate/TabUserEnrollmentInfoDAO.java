package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;

public class TabUserEnrollmentInfoDAO extends GenericDaoHibernate<TabUserEnrollmentInfo, Long> implements ITabUserEnrollmentInfoDAO {
	public TabUserEnrollmentInfoDAO() {
		super(TabUserEnrollmentInfo.class);
	}
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TUEI.totalRecords) from TabUserEnrollmentInfo TUEI where " +
						" date(TUEI.surveyTime) = (:surveyTime) and " +
						" TUEI.stateId = :stateId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("surveyTime", surveyTime);
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult(); 
	}//SELECT sum(TUEI.total_records) from tab_user_enrollment_info TUEI where date(TUEI.survey_time) = '2016-10-13' and TUEI.state_id = 1;
	public Long getTotalTabUserWorkingInField(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date today){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where model.stateId =:stateId and " +
				" date(model.startTime) = :today ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("stateId", stateId);   
		query.setDate("today",today);
		return (Long) query.uniqueResult();
	}

}

