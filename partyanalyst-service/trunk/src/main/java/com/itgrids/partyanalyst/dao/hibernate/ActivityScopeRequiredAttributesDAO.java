package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.model.ActivityScopeRequiredAttributes;

public class ActivityScopeRequiredAttributesDAO extends GenericDaoHibernate<ActivityScopeRequiredAttributes, Long> implements IActivityScopeRequiredAttributesDAO{

	public ActivityScopeRequiredAttributesDAO() {
		super(ActivityScopeRequiredAttributes.class);
		
	}
	
public List<Object[]> getRequiredAttributesOfScope(Long scopeId){
		
		Query query = getSession().createQuery(" SELECT model.requiredAttribute.requiredAttributeId, model.requiredAttribute.requiredAttribute " +
				" FROM ActivityScopeRequiredAttributes model " +
				" WHERE " +
				" model.isActive = 'Y' " +
				" and model.requiredAttribute.isActive='Y' and model.isActive = 'Y' and model.activityScope.isDeleted='N' " +
							" AND model.activityScope.activity.isActive = 'Y' and  model.activityScope.isDeleted='N' and model.activityScopeId = :scopeId ");
		
		query.setParameter("scopeId", scopeId);
		return query.list();
	}


	public List<Object[]> getActivityRequiredAttributes(List<Long> scopeIds){
		
		Query query = getSession().createQuery(" select model.requiredAttribute.requiredAttributeId," +
							" model.requiredAttribute.requiredAttribute," +
							" model.activityScope.activityScopeId " +
							" from ActivityScopeRequiredAttributes model " +
							" where model.activityScope.activityScopeId in (:scopeIds) " +
							" and model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' and model.activityScope.isDeleted='N' " +
							" AND model.activityScope.activity.isActive = 'Y' ");
		query.setParameterList("scopeIds", scopeIds);
		return query.list();
	}
	
	public List<Object[]> getActivityRequiredAttributesForScope(Long scopeId){
		
		Query query = getSession().createQuery(" select model.requiredAttribute.requiredAttributeId," +
							" model.requiredAttribute.requiredAttribute " +
							" from ActivityScopeRequiredAttributes model where model.activityScope.activityScopeId = :scopeId " +
							" and model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' and model.activityScope.isDeleted='N' " +
							" AND model.activityScope.activity.isActive = 'Y' ");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}
public List<Object[]> getScopeIds(Long activityLevelId){
		
		Query query = getSession().createQuery(" select distinct model.activityScopeId, model.activityScope.activity.activityName, " +
				" model.activityScope.activityLevel.activityLevelId, model.activityScope.activityLevel.level, model.activityScope.activity.activityId, model.requiredAttributeId " +
							" from ActivityScopeRequiredAttributes model where " +
							"  model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' and model.activityScope.isDeleted='N' AND model.activityScope.activity.isActive = 'Y' " +
							" and model.activityScope.activityLevelId = :activityLevelId group by model.activityScopeId ");
		query.setParameter("activityLevelId", activityLevelId);
		return query.list();
	}
	
	public List<Object[]> getActivityScopeIds(AddressVO addressVO,Long activityLevelId){
		StringBuilder queryStr = new StringBuilder();
		boolean isLocationAvailable =false;
		queryStr.append(" select distinct model.activityScopeId, model.activityScope.activity.activityName, " +
				" model.activityScope.activityLevel.activityLevelId, model.activityScope.activityLevel.level, model.activityScope.activity.activityId, model.requiredAttributeId  " +
							" from ActivityScopeRequiredAttributes model where  model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' and model.activityScope.isDeleted='N' AND model.activityScope.activity.isActive = 'Y' " +
							" and model.activityScope.activityLevelId = :activityLevelId ");
		
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
			queryStr.append(" 	( model.activityScope.scopeId in (6,8) and model.activityScope.scopeValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.activityScope.scopeId in (5,7) and model.activityScope.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append("  ( model.activityScope.scopeId = in (5,7) and model.activityScope.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append("  ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append("  ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append("  ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		
		if(isLocationAvailable)
			queryStr.append(" )   ");
		
		queryStr.append(" group by model.activityScope.activityScopeId");
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
		
		query.setParameter("activityLevelId", activityLevelId);
		
		return query.list();
	}

}
