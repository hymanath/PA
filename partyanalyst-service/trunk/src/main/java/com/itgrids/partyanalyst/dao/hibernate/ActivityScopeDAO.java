package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.model.ActivityScope;

public class ActivityScopeDAO extends GenericDaoHibernate<ActivityScope, Long> implements IActivityScopeDAO{

	public ActivityScopeDAO() {
		super(ActivityScope.class);
		
	}

	public List<Object[]> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activity.activityName from ActivityScope model where model.activityLevelId =:activityLevelId and " +
				" model.activity.activitySubTypeId=:activityTypeId and model.activity.isActive = 'Y' order by model.activity.activityId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityLevelId", activityLevelId);
		query.setParameter("activityTypeId", activityTypeId);
		
		return query.list();
	}
	public List<Object[]> getActivityScopeIdByActivityAndLevelId(Long activityId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activityLevelId from ActivityScope model where " +
				" model.activity.activityId=:activityId and model.isDeleted = 'N' order by model.activityScopeId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityId", activityId);
		
		return query.list();
	}
	public Object[] getDatesForActivityByActivityScopeId(Long activityScopeId){ 
		
		Query query=getSession().createQuery(" select date(model.activity.startDate),date(model.activity.endDate) from ActivityScope model where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Object[])query.uniqueResult();
	}
	public Long getNoOFTimesOfAnActivity(Long activityScopeId){
		Query query=getSession().createQuery(" select model.activity.noOfTimes from ActivityScope model where model.activityScopeId =:activityScopeId");
		query.setParameter("activityScopeId", activityScopeId);
		return (Long)query.uniqueResult();
	}
	
	public Date getActivityStartDateByActivityScopeId(Long activityScopeId)
	{
		Query query = getSession().createQuery(" select date(model.activity.startDate) from ActivityScope model where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Date) query.uniqueResult();
	}
	
	public Object[] getDatesForActivityScopeId(Long activityScopeId){ 
		Query query=getSession().createQuery(" select date(model.startDate)," +
				" date(model.endDate) " +
				" from ActivityScope model " +
				" where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Object[])query.uniqueResult();
	}
	
	
	public List<Object[]> getActivityLevelsDetails(Long requiredAtrrId,AddressVO addressVO){
		StringBuilder queryStr = new StringBuilder();
		boolean isLocationAvailable =false;
		
		queryStr.append(" select model.activityLevelId, model.activityScopeId, model.activity.activityName "+
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrId " +
				" and model1.isActive = 'Y' and model.activity.isActive='Y' and model.isDeleted = 'N'  ");
		
		if(!isLocationAvailable && (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && (addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getStateId() != null && addressVO.getStateId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			isLocationAvailable =true;
		
		
		if(isLocationAvailable)
			queryStr.append(" and ( ");
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L)){
			queryStr.append(" 	( model.scopeId in (6,8) and model.scopeValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.scopeId in (5,7) and model.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append("  ( model.scopeId  in (5,7) and model.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append("  ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append(" ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append("  ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		
		if(isLocationAvailable)
			queryStr.append(" ) and  ");
		
		queryStr.append("  model.isDeleted = 'N' and model.activity.isActive='Y' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			query.setParameter("villageORWardId", (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) ? addressVO.getPanchaytId() :(addressVO.getWardId() != null && addressVO.getWardId()>0L)?addressVO.getWardId():null );
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			query.setParameter("mandalORMuncId", addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L ? addressVO.getTehsilId()  : (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L) ?addressVO.getLocalElectionBodyId():null);
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			query.setParameter("districtId",addressVO.getDistrictId() );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			Long id = addressVO.getDistrictId() <=10?36L:1L;
			query.setParameter("stateId", id);
		}
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			query.setParameter("constituencyId",addressVO.getConstituencyId() );

		query.setParameter("requiredAtrrId", requiredAtrrId);
		return query.list();
	}
	
/*	public List<Object[]> getActivityLevelsDetails(Long requiredAtrrId){
		
		Query query = getSession().createQuery(" select model.activityLevelId, model.activityScopeId, model.activity.activityName "+
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrId " +
				" and model1.isActive = 'Y' and model.activity.isActive='Y' and model.isDeleted = 'N' ");
		
		query.setParameter("requiredAtrrId", requiredAtrrId);
		*/
		
		/*Query query = getSession().createQuery(" select model.activityLevelId," +
							" count(model.activityLevelId) " +
							" from ActivityScope model " +
							" where model.isDeleted = 'N' " +
							" group by model.activityLevel.activityLevelId ");*/
		/*Query query = getSession().createQuery(" select model.activityLevelId," +
				" count(model.activityLevelId) " +
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrIds " +
				" and model1.isActive = 'Y' and model1.isActive = 'Y' and model.isDeleted = 'N' " +
				" group by model.activityLevel.activityLevelId ");
		query.setParameter("requiredAtrrIds", requiredAtrrIds);*/
		
		//return query.list();
	//}
	
	public List<Object[]> getActivitiesListByLevelId(Long levelId,Long requiredAtrrIds){
		/*Query query = getSession().createQuery(" select model.activityScopeId," +
							" model.activity.activityId, " +
							" model.activity.activityName, " +
							" model.activityLevelId " +
							" from ActivityScope model " +
							" where model.activityLevel.activityLevelId = :levelId " +
							" and model.isDeleted = 'N' and model.activity.isActive = 'Y' ");*/
		Query query = getSession().createQuery(" select model.activityScopeId," +
				" model.activity.activityId, " +
				" model.activity.activityName, " +
				" model.activityLevelId " +
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrIds " +
				" and model.activityLevel.activityLevelId = :levelId " +
				" and model.isDeleted = 'N' and model.activity.isActive = 'Y' " +
				" and model1.isActive = 'Y' and model1.isActive = 'Y' ");
		query.setParameter("requiredAtrrIds", requiredAtrrIds);
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getActivityLevelAndScopeIdByActivity(Long activityId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activityLevel.activityLevelId,model.activityLevel.level from ActivityScope model " +
				" where " +
				" model.activity.activityId=:activityId and model.isDeleted = 'N' order by model.activityScopeId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityId", activityId);
		
		return query.list();
	}
	public Object[] getRequiredDatesOfScope(Long scopeId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" SELECT date(model.startDate),date(model.endDate) " +
				" FROM ActivityScope model " +
				" WHERE model.isDeleted = 'N' " );
		if(scopeId !=null && scopeId>0){
			queryStr.append(" and model.activityScopeId = :scopeId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(scopeId !=null && scopeId>0){
			query.setParameter("scopeId", scopeId);
		}
		
		return (Object[]) query.uniqueResult();
		
	}
	
	public Long getNoOfTimesCountForActivityScope(Long scopeId){
		Query query = getSession().createQuery("select model.noOfTimes" +
											" from ActivityScope model" +
											" where model.activityScopeId = :scopeId" +
											" and model.isDeleted = 'N'");
		
		query.setParameter("scopeId", scopeId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getActivitiesScopeDetailsByAddress(AddressVO addressVO){
		StringBuilder queryStr = new StringBuilder();
		boolean isLocationAvailable =false;
		queryStr.append(" select model.activityLevelId,count(model.activityLevelId)  from ActivityScope model " +
							" where ");
		if(!isLocationAvailable && (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && (addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getStateId() != null && addressVO.getStateId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			isLocationAvailable =true;
		
		
		if(isLocationAvailable)
			queryStr.append(" ( ");
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L)){
			queryStr.append(" 	( model.scopeId in (6,8) and model.scopeValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.scopeId in (5,7) and model.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append("  ( model.scopeId  in (5,7) and model.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append("  ( model.scopeId = 3 and model.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append(" ( model.scopeId = 2 and model.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append("  ( model.scopeId = 4 and model.scopeValue =:constituencyId)  " );
		
		if(isLocationAvailable)
			queryStr.append(" ) and  ");
		
		queryStr.append("  model.isDeleted = 'N' and model.activity.isActive='Y'  group by model.activityLevel.activityLevelId");
		Query query = getSession().createQuery(queryStr.toString());
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			query.setParameter("villageORWardId", (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) ? addressVO.getPanchaytId() :(addressVO.getWardId() != null && addressVO.getWardId()>0L)?addressVO.getWardId():null );
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			query.setParameter("mandalORMuncId", addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L ? addressVO.getTehsilId()  : (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L) ?addressVO.getLocalElectionBodyId():null);
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			query.setParameter("districtId",addressVO.getDistrictId() );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			Long id = addressVO.getDistrictId() <=10?36L:1L;
			query.setParameter("stateId", id);
		}
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			query.setParameter("constituencyId",addressVO.getConstituencyId() );
		
		return query.list();
	}
	
	public List<Object[]> getActivityDetails(Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.activityScope.activity.activityId," +
						" model.activityScope.activity.activityName" +
						" from ActivityLocationInfo model" +
						" where model.activityScope.isDeleted = 'N'" +
						" and model.activityScope.activity.isActive = 'Y'");
		if(stateId != null && stateId.longValue()>0L)
			sb.append(" and model.address.state.stateId = :stateId ");
		
		if(fromDate != null && toDate != null)
			sb.append(" and ((date(model.activityScope.startDate) between :fromDate and :toDate) or (date(model.activityScope.endDate) between :fromDate and :toDate))");
		sb.append(" order by model.activityScopeId desc");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(stateId != null && stateId.longValue()>0L)
			query.setParameter("stateId", stateId);
		return query.list();
	}
	
	public List<Object[]> getActivityLevelsByActivity(Long activityId){
		Query query = getSession().createQuery("select distinct model.activityScopeId," +
												" model.activityLevel.activityLevelId," +
												" model.activityLevel.level," +
												" model.scopeId,model.scopeValue" +
												" from ActivityScope model" +
												" where model.isDeleted = 'N'" +
												" and model.activity.isActive = 'Y'" +
												" and model.activity.activityId = :activityId");
		query.setParameter("activityId", activityId);
		return query.list();
	}
	public List<Object[]> getScopeNameByActivity(Long activityId,Date fromDate,Date endDate){
		StringBuilder sb =  new StringBuilder();
		sb.append("select distinct model.activityScopeId," +
				" model.activityLevel.activityLevelId," +
				" model.activityLevel.level," +
				" model.activity.activityId," +
				" model.activity.activityName " +
				" where model.isDeleted = 'N'" +
				" and model.activity.isActive = 'Y'" +
				" and model.activity.activityId = :activityId");
		if(fromDate != null && endDate != null)
			sb.append(" and date(model.startDate) = :fromDate and date(model.endDate) = :endDate");
		
		Query query = getSession().createQuery(sb.toString());
		 	query.setParameter("activityId", activityId);
		 if(fromDate != null && endDate != null){
			  query.setDate("fromDate", fromDate);
			  query.setDate("endDate", endDate);
		 }
			 
		 return query.list();
	}
	
}
