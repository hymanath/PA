package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;

public class ActivityLocationInfoDAO extends GenericDaoHibernate<ActivityLocationInfo, Long> implements IActivityLocationInfoDAO{

	public ActivityLocationInfoDAO() {
		super(ActivityLocationInfo.class);
		
	}

	
	public List<Object[]> getUpdatedLocationsListForScope(Long activityScopeId,Date startDate,Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationValue,date(model.plannedDate),date(model.conductedDate),model.locationLevel from ActivityLocationInfo model where " +
				"model.activityScopeId =:activityScopeId ");
		if(startDate != null && endDate != null)
			queryStr.append(" and (date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityScopeId", activityScopeId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getAssemblyConstWiseDetails(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("" +
		" select   model.constituency.constituencyId,count(*),count(conductedDate)" +
		" from     ActivityLocationInfo model " +
		" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) ");
		if(startDate!=null){
			sb.append(" and date(model.plannedDate) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.plannedDate) <=:endDate ");
		}
		sb.append(" group by model.constituency.constituencyId ");
		
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameterList("constIds",constIds);
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		return query.list();
	}
}
