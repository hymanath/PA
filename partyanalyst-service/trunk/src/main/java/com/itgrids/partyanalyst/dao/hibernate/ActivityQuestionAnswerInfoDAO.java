package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerInfoDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswerInfo;

public class ActivityQuestionAnswerInfoDAO extends GenericDaoHibernate<ActivityQuestionAnswerInfo, Long>
		implements IActivityQuestionAnswerInfoDAO {

	public ActivityQuestionAnswerInfoDAO() {
		super(ActivityQuestionAnswerInfo.class);
	}

	@Override
	public List<Object[]> getInchargeMLAAttendCount(Long activityScopeId,Long locationScopeId, Date startDate, Date endDate) {
		
		Query query = getSession().createQuery("select model.count,model.constituencyId from ActivityQuestionAnswerInfo model where model.activityScopeId=:activityScopeId ");
		
		query.setParameter("activityScopeId", activityScopeId);
		
		return query.list();
	}
	
	
}
