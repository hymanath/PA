package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityDaywiseQuestionnaireDAO;
import com.itgrids.partyanalyst.model.Activity;
import com.itgrids.partyanalyst.model.ActivityDaywiseQuestionnaire;

public class ActivityDaywiseQuestionnaireDAO extends GenericDaoHibernate<ActivityDaywiseQuestionnaire, Long> implements  IActivityDaywiseQuestionnaireDAO {
	public ActivityDaywiseQuestionnaireDAO() {
		super(ActivityDaywiseQuestionnaire.class);
		
	}
	public List<Long> getActivityQuestionIds(Date activityDate,Long day,Long activityScopeId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.activityQuestionnaireId  from ActivityDaywiseQuestionnaire model" +
				" where model.isDeleted = 'false' ");
		if(activityDate != null)
			str.append(" and date(model.activityDate) = :activityDate  and model.day = :day and model.activityQuestionnaire.activityScopeId = :activityScopeId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("activityDate", activityDate);
		query.setParameter("day", day);
		query.setParameter("activityScopeId", activityScopeId);
		return query.list();
	}
	public List<Long> getSelectedDayWiseQuestionWithOptions(Long scopeId,List<Long> seletedDays)
	{
	Query query = getSession().createQuery(" select model.activityQuestionnaireId " +
		      " from ActivityDaywiseQuestionnaire model " +
		      " where model.isDeleted= 'false' " +
		      " and model.day=:seletedDays " +
		      " and model.activityQuestionnaire.activityScopeId =:scopeId " );
	
		      query.setParameterList("seletedDays", seletedDays);
		      query.setParameter("scopeId", scopeId);
		      return query.list();
	}

}
