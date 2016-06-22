package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.impl.IActivityDateTypeDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityLocationInfoDatesDAO;
import com.itgrids.partyanalyst.model.ActivityDateType;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public class ActivityLocationInfoDatesDAO extends
		GenericDaoHibernate<ActivityLocationInfoDates, Long> implements
		IActivityLocationInfoDatesDAO {
	public ActivityLocationInfoDatesDAO() {
		super(ActivityLocationInfoDates.class);

	}
	
	
	public List<Object[]> getActivityDatesByScope(Long activityScopeId,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.activityLocationInfo.locationLevel,model.activityLocationInfo.locationValue," +
				" model.day,date(model.activityDate),model.activityDateType.activityDateTypeId,model.activityDateType.dateType from ActivityLocationInfoDates model," +
				"ActivityLocationInfo model1" +
				" where model.activityLocationInfo.activityLocationInfoId = model1.activityLocationInfoId" +
				" and model.activityLocationInfo.activityScopeId =:activityScopeId ");
		if(startDate != null && endDate != null)
			str.append(" and (date(model.activityLocationInfo.plannedDate) >= :startDate and date(model.activityLocationInfo.plannedDate) <= :endDate ) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("activityScopeId", activityScopeId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
	
	public List<ActivityLocationInfoDates> getActivityLocationInfoDates(Long activityLocationInfoId,Long day)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model from ActivityLocationInfoDates model where model.activityLocationInfo.activityLocationInfoId = :activityLocationInfoId");
		if(day != null && day > 0)
		{
			str.append( " and model.day=:day");
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("activityLocationInfoId", activityLocationInfoId);
		if(day != null && day > 0)
			query.setParameter("day", day);
		return query.list();
		
	}
}
